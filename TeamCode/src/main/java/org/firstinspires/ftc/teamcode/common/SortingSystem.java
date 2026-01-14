package org.firstinspires.ftc.teamcode.common;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.opMode;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;


import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

/**
 * Fully autonomous ball feed controller
 * Manages up to 3 balls using sensors + encoder pusher
 */
@Config
public class SortingSystem extends Component {

    /* ---------------- CONFIG (TUNE LIVE) ---------------- */

    public static int TICKS_INTAKE_TO_TOP = 420;
    public static int TICKS_TOP_TO_SHOT  = 380;
    public static int TICKS_JAM_REVERSE  = -200;

    public static long TOP_WAIT_MS = 500;
    public static long MOVE_TIMEOUT_MS = 1200;
    public static long JAM_REVERSE_MS = 350;

    public static double SENSOR_MM = 100.0;

    /* ---------------- HARDWARE ---------------- */

    private final BlackWheel pusher;
    private final DistanceSensor intakeSensor;
    private final DistanceSensor topSensor;
    private final DistanceSensor shotSensor;

    /* ---------------- STATE ---------------- */

    private enum FeedState {
        IDLE,
        MOVE_TO_TOP,
        WAIT_AT_TOP,
        MOVE_TO_SHOT,
        HOLDING,
        JAM_RECOVERY,
        DISABLED
    }

    private FeedState feedState = FeedState.IDLE;

    private final ElapsedTime feedTimer = new ElapsedTime();
    private final ElapsedTime jamTimer  = new ElapsedTime();

    private boolean autoEnabled = true;
    private boolean manualOverride = false;

    /* ---------------- BALL TRACKING ---------------- */

    private int ballCount = 0;

    private boolean intakeLast = false;
    private boolean shotLast   = false;

    /* ---------------- CONSTRUCTOR ---------------- */

    public SortingSystem(OpMode opMode, Telemetry telemetry) {
        super(telemetry);
        intakeSensor = opMode.hardwareMap.get(DistanceSensor.class, "intakeSensor");
        topSensor = opMode.hardwareMap.get(DistanceSensor.class, "topSensor");
        shotSensor = opMode.hardwareMap.get(DistanceSensor.class, "shotSensor");
        pusher = new BlackWheel(opMode.hardwareMap, telemetry);
    }

    /* ---------------- PUBLIC API ---------------- */

    /** Call once per loop */
    public void update() {
        updateBallCount();
        updateStateMachine();
        log();
    }

    /** Driver panic button */
    public void reset() {
        pusher.stop();
        feedState = FeedState.IDLE;
        ballCount = 0;
        feedTimer.reset();
        jamTimer.reset();
    }

    /** Disable automation completely */
    public void disable() {
        autoEnabled = false;
        reset();
        feedState = FeedState.DISABLED;
    }

    public void enable() {
        autoEnabled = true;
        feedState = FeedState.IDLE;
    }

    /** Manual override (driver wins) */
    public void setManualOverride(boolean enabled) {
        manualOverride = enabled;
        if (enabled) pusher.stop();
    }

    /** Manual pusher control (only works if override enabled) */
    public void manualForward() {
        if (manualOverride) pusher.forward();
    }

    public void manualReverse() {
        if (manualOverride) pusher.reverse();
    }

    public void manualStop() {
        if (manualOverride) pusher.stop();
    }

    /* ---------------- CORE LOGIC ---------------- */

    private void updateStateMachine() {

        if (!autoEnabled || manualOverride) {
            pusher.stop();
            return;
        }

        boolean shot = shotHasBall();

        switch (feedState) {

            case IDLE:
                pusher.stop();

                if (!shot && ballCount > 0) {
                    pusher.runForwardInTicks(
                            pusher.getCurrentPosition() + TICKS_INTAKE_TO_TOP
                    );
                    feedTimer.reset();
                    feedState = FeedState.MOVE_TO_TOP;
                }
                break;

            case MOVE_TO_TOP:
                if (!pusher.isBusy()) {
                    feedTimer.reset();
                    feedState = FeedState.WAIT_AT_TOP;
                } else if (feedTimer.milliseconds() > MOVE_TIMEOUT_MS) {
                    startJamRecovery();
                }
                break;

            case WAIT_AT_TOP:
                if (feedTimer.milliseconds() >= TOP_WAIT_MS) {
                    pusher.runForwardInTicks(
                            pusher.getCurrentPosition() + TICKS_TOP_TO_SHOT
                    );
                    feedTimer.reset();
                    feedState = FeedState.MOVE_TO_SHOT;
                }
                break;

            case MOVE_TO_SHOT:
                if (!pusher.isBusy()) {
                    feedState = shot ? FeedState.HOLDING : FeedState.JAM_RECOVERY;
                    jamTimer.reset();
                } else if (feedTimer.milliseconds() > MOVE_TIMEOUT_MS) {
                    startJamRecovery();
                }
                break;

            case HOLDING:
                pusher.stop();
                if (!shot) feedState = FeedState.IDLE;
                break;

            case JAM_RECOVERY:
                if (jamTimer.milliseconds() > JAM_REVERSE_MS) {
                    pusher.stop();
                    feedState = FeedState.IDLE;
                }
                break;

            case DISABLED:
                pusher.stop();
                break;
        }
    }

    private void startJamRecovery() {
        pusher.runForwardInTicks(
                pusher.getCurrentPosition() + TICKS_JAM_REVERSE
        );
        jamTimer.reset();
        feedState = FeedState.JAM_RECOVERY;
    }

    /* ---------------- BALL COUNT LOGIC ---------------- */

    private void updateBallCount() {

        boolean intakeNow = intakeHasBall();
        boolean shotNow   = shotHasBall();

        // New ball entered system
        if (intakeNow && !intakeLast && ballCount < 3) {
            ballCount++;
        }

        // Ball exited system (shot fired)
        if (!shotNow && shotLast && ballCount > 0) {
            ballCount--;
        }

        ballCount = clamp(ballCount, 0, 3);

        intakeLast = intakeNow;
        shotLast   = shotNow;
    }

    /* ---------------- SENSOR HELPERS ---------------- */

    private boolean intakeHasBall() {
        return intakeSensor.getDistance(DistanceUnit.MM) < SENSOR_MM;
    }

    private boolean topHasBall() {
        return topSensor.getDistance(DistanceUnit.MM) < SENSOR_MM;
    }

    private boolean shotHasBall() {
        return shotSensor.getDistance(DistanceUnit.MM) < SENSOR_MM;
    }

    /* ---------------- UTIL ---------------- */

    private int clamp(int val, int min, int max) {
        return Math.max(min, Math.min(max, val));
    }

    private void log() {
        telemetry.addData("Feed State", feedState);
        telemetry.addData("Ball Count", ballCount);
        telemetry.addData("Intake", intakeHasBall());
        telemetry.addData("Top", topHasBall());
        telemetry.addData("Shot", shotHasBall());
    }
}

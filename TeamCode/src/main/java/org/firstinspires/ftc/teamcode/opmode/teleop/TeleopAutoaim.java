package org.firstinspires.ftc.teamcode.pedroPathing;
import com.bylazar.configurables.annotations.Configurable;
import com.bylazar.telemetry.PanelsTelemetry;
import com.bylazar.telemetry.TelemetryManager;
import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.BezierLine;
import com.pedropathing.geometry.Pose;
import com.pedropathing.paths.HeadingInterpolator;
import com.pedropathing.paths.Path;
import com.pedropathing.paths.PathChain;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import java.util.function.Supplier;

@Configurable
@TeleOp(name = "TeleopPedro", group = "Teleop")
public class TeleopAutoaim extends OpMode {

    private Follower follower;

    public static Pose startingPose = new Pose(0, 0, 0);

    // ===== AUTO AIM CONFIG =====
    public static double GOAL_X = 144;          // inches (TUNE)
    public static double GOAL_Y = 144;         // inches (TUNE)
    public static double AIM_KP = 2.5;         // turning strength
    public static double MAX_TURN = 0.6;       // safety cap
    public static double AIM_DEADBAND = Math.toRadians(1.5);

    private boolean autoAim = false;

    private boolean slowMode = false;
    private double slowModeMultiplier = 0.5;

    private TelemetryManager telemetryM;

    @Override
    public void init() {
        follower = Constants.createFollower(hardwareMap);
        follower.setStartingPose(startingPose);
        follower.update();

        telemetryM = PanelsTelemetry.INSTANCE.getTelemetry();
    }

    @Override
    public void start() {
        follower.startTeleopDrive();
    }

    @Override
    public void loop() {
        follower.update();

        Pose pose = follower.getPose();

        // =========================
        // AUTO AIM TO GOAL
        // =========================
        if (gamepad1.aWasPressed()) {
            autoAim = !autoAim;   // toggle
        }

        double turn = 0;

        if (autoAim) {
            double dx = GOAL_X - pose.getX();
            double dy = GOAL_Y - pose.getY();

            double targetHeading = Math.atan2(dy, dx);
            double error = angleWrap(targetHeading - pose.getHeading());

            if (Math.abs(error) < AIM_DEADBAND) {
                turn = 0;
                autoAim = false;   // stop once aligned
            } else {
                turn = error * AIM_KP;
                turn = clamp(turn, -MAX_TURN, MAX_TURN);
            }
        } else {
            turn = -gamepad1.right_stick_x;
        }

        // =========================
        // TRANSLATION (DRIVER)
        // =========================
        double y = -gamepad1.left_stick_y;
        double x = -gamepad1.left_stick_x;

        if (slowMode) {
            y *= slowModeMultiplier;
            x *= slowModeMultiplier;
            turn *= slowModeMultiplier;
        }

        follower.setTeleOpDrive(
                y,
                x,
                turn,
                true // Robot Centric
        );

        // =========================
        // SLOW MODE
        // =========================
        if (gamepad1.rightBumperWasPressed()) {
            slowMode = !slowMode;
        }

        telemetryM.addData("Pose", pose);
        telemetryM.addData("AutoAim", autoAim);
        telemetryM.addData("GoalX", GOAL_X);
        telemetryM.addData("GoalY", GOAL_Y);
        telemetryM.update();
    }

    // =========================
    // HELPERS
    // =========================
    private double angleWrap(double radians) {
        while (radians > Math.PI) radians -= 2 * Math.PI;
        while (radians < -Math.PI) radians += 2 * Math.PI;
        return radians;
    }

    private double clamp(double v, double min, double max) {
        return Math.max(min, Math.min(max, v));
    }
}

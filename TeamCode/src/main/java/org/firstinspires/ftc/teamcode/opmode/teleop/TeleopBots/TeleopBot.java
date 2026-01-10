package org.firstinspires.ftc.teamcode.opmode.teleop.TeleopBots;


import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.common.Bot;
import org.firstinspires.ftc.teamcode.common.Drivetrain;
import org.firstinspires.ftc.teamcode.common.hardwareConfiguration.data.DrivetrainData;
import org.firstinspires.ftc.teamcode.common.hardwareConfiguration.data.GoBilda435DcMotorData;

public class TeleopBot extends Bot {
    private final Drivetrain drivetrain;

    private double driveAxial = 0.0;
    private double driveStrafe = 0.0;
    private double driveYaw = 0.0;
    private boolean intakeMoving = false;
    private boolean pusherMoving = false;
    private boolean pusherButtonPressedLast = false;
    private boolean pusherButtonPressedReverse = false;
    private ElapsedTime pusherTimer = new ElapsedTime(ElapsedTime.Resolution.MILLISECONDS);
    private int pusherDebounceMs = 300; // 300 ms debounce
    private ElapsedTime buttonTimer = new ElapsedTime(ElapsedTime.Resolution.MILLISECONDS);
    private int buttonDelay = 350;

    public TeleopBot(OpMode opMode, Telemetry telemetry) {
        super(opMode, telemetry);
        drivetrain = new Drivetrain(opMode, opMode.hardwareMap, telemetry, new DrivetrainData(), new GoBilda435DcMotorData());
        buttonTimer.reset();
    }

    private boolean buttonPushable() {
        return (buttonTimer.milliseconds() > buttonDelay);
    }

    // driveaxial is forward back pos is forward neg is back 0-1
    // drivestrafe is left right, left is positive and rihgt is neg 1 to -1
    // driveyaw is rotation negtive is left, -1 to 1
    public void moveBot(double driveAxial, double driveStrafe, double driveYaw) {
        drivetrain.moveDirection(driveAxial, driveStrafe, driveYaw);
    }

    public void fullStop() {
        drivetrain.moveDirection(0,0,0);
    }
    public void launcherTurnOn() {
        setLauncherShortShot();
    }

    public void stickL() {
        stickLaunch();
    }
    public void stickReset() {
        stickLoad();
    }



    public void turnOnBlackWheel() {
        pusherStart();
    }

    public void turnOffBlackWheel() {
        stopPusher();
    }

    public void autoAim() {

    }

    public void processGamepadInput(Gamepad gamepad) {
        if (gamepad.dpad_up) {
            drivetrain.creepDirection(1.0, 0.0, 0.0);
        } else if (gamepad.dpad_down) {
            drivetrain.creepDirection(-1.0, 0.0, 0.0);
        } else if (gamepad.dpad_left) {
            drivetrain.creepDirection(0.0, -1.0, 0.0);
        } else if (gamepad.dpad_right) {
            drivetrain.creepDirection(0.0, 1.0, 0.0);
        } else {
            driveAxial = -gamepad.left_stick_y;
            driveStrafe = gamepad.left_stick_x;
            driveYaw = gamepad.right_stick_x;
            if ((Math.abs(driveAxial) < 0.2) && (Math.abs(driveStrafe) < 0.2) && (Math.abs(driveYaw) < 0.2)) {
                drivetrain.stop();
            } else
                drivetrain.moveDirection(driveAxial, driveStrafe, driveYaw);
        }


        if (gamepad.left_bumper)
        {
            setLauncherShortShot();
        }

        if (gamepad.right_bumper)
        {
            setLauncherLongShot();
        }

        if (gamepad.left_trigger > 0.2 && buttonPushable()) {
            reverseLauncer();
        }

        if (gamepad.right_trigger > 0.2 && buttonPushable())
        {
            launcherStop();
            buttonTimer.reset();
        }

        if (gamepad.x && !pusherButtonPressedLast && pusherTimer.milliseconds() > pusherDebounceMs) {
            if (!pusherMoving) {
                pusherStart();      // Turn ON
                pusherMoving = true;
            } else {
                stopPusher();       // Turn OFF
                pusherMoving = false;
            }
            pusherTimer.reset();    // Reset debounce timer
        }

        if (gamepad.b && !pusherButtonPressedLast && pusherTimer.milliseconds() > pusherDebounceMs) {
            if (!pusherMoving) {
                pusherReverse();      // Turn ON
                pusherMoving = true;
            } else {
                stopPusher();       // Turn OFF
                pusherMoving = false;
            }
            pusherTimer.reset();    // Reset debounce timer
        }

        pusherButtonPressedLast = gamepad.x;
        pusherButtonPressedReverse = gamepad.b;

        if (gamepad.a && buttonPushable()) {
            if (!intakeMoving) {
                intakeForward();      // turn ON
                intakeMoving = true;
            } else {
                intakeStop();         // turn OFF
                intakeMoving = false;
            }
            buttonTimer.reset();
        }


        if (gamepad.y)
        {
            stickLaunch();
        } else
        {
            stickLoad();
        }
    }

    //h
}

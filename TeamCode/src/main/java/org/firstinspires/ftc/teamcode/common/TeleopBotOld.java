package org.firstinspires.ftc.teamcode.common;

import com.pedropathing.geometry.Pose;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.common.hardwareConfiguration.data.DrivetrainData;
import org.firstinspires.ftc.teamcode.common.hardwareConfiguration.data.GoBilda435DcMotorData;

public class TeleopBotOld extends BotNew {
    private final Drivetrain drivetrain;
    private double driveAxial = 0.0;
    private double driveStrafe = 0.0;
    private double driveYaw = 0.0;
    private ElapsedTime buttonTimer = new ElapsedTime(ElapsedTime.Resolution.MILLISECONDS);
    private int buttonDelay = 350;
    boolean red;

    public TeleopBotOld(OpMode opMode, Telemetry telemetry) {
        super(opMode, telemetry);
        drivetrain = new Drivetrain(opMode, opMode.hardwareMap, telemetry, new DrivetrainData(), new GoBilda435DcMotorData());
        buttonTimer.reset();
    }

    public TeleopBotOld(OpMode opMode, Telemetry telemetry, Pose startPose, boolean red) {
        super(opMode, telemetry);
        drivetrain = new Drivetrain(opMode, opMode.hardwareMap, telemetry, new DrivetrainData(), new GoBilda435DcMotorData());
        drivetrain.setOdoStartingPose(startPose);
        drivetrain.loop();
        buttonTimer.reset();
    }

    private boolean buttonPushable() {
        return (buttonTimer.milliseconds() > buttonDelay);
    }

    public void launcherPreload(){
        launcher.preloadFromDistance(distanceFromTarget());
    }

    public double distanceFromTarget(){
        return 0; //temp
    }

    public void log(){
        launcher.log();
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
            launcherPreload();
        } else if (gamepad.right_bumper)
        {
            launcherStop();
        }

        if (gamepad.y)
        {
            intakeReverse();
            topRollerForward();
            bottomRollerReverse();
        } else if (gamepad.b)
        {
            intakeForward();
            topRollerReverse();
            bottomRollerReverse();
        } else if (gamepad.a)
        {
            intakeStop();
            topRollerStop();
            bottomRollerStop();
        }
        drivetrain.loop();
        telemetry.addData("Current Pose", drivetrain.getPose().toString());
        telemetry.addData("Distance from Target", distanceFromTarget());
//
//        if (gamepad.x)
//        {
//            kickerLaunch();
//        } else
//        {
//            kickerLoad();
//        }
    }
}

package org.firstinspires.ftc.teamcode.common;

import com.qualcomm.hardware.gobilda.GoBildaPinpointDriver;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;
import org.firstinspires.ftc.teamcode.common.hardwareConfiguration.data.DrivetrainData;
import org.firstinspires.ftc.teamcode.common.hardwareConfiguration.data.GoBilda435DcMotorData;

public class TeleopBot extends Bot {
    private final Drivetrain drivetrain;
    private final GoBildaPinpointDriver pinpoint;
    private double driveAxial = 0.0;
    private double driveStrafe = 0.0;
    private double driveYaw = 0.0;


    public TeleopBot(OpMode opMode, Telemetry telemetry) {
        super(opMode, telemetry);
        drivetrain = new Drivetrain(opMode, opMode.hardwareMap, telemetry, new DrivetrainData(), new GoBilda435DcMotorData());
        pinpoint = opMode.hardwareMap.get(GoBildaPinpointDriver.class, "pinpoint");
        pinpoint.setOffsets(-2.5,-4.5, DistanceUnit.INCH);
        pinpoint.setEncoderResolution(GoBildaPinpointDriver.GoBildaOdometryPods.goBILDA_4_BAR_POD);
        pinpoint.setEncoderDirections(GoBildaPinpointDriver.EncoderDirection.FORWARD,
                GoBildaPinpointDriver.EncoderDirection.FORWARD);
        pinpoint.resetPosAndIMU();
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
//
//        if (gamepad.left_bumper)
//        {
//            setLauncherShortShot();
//        } else if (gamepad.right_bumper)
//        {
//            setLauncherLongShot();
//        } else if (gamepad.right_trigger > 0.2)
//        {
//            launcherStop();
//        }


//
//        if (gamepad.x)
//        {
//            kickerLaunch();
//        } else
//        {
//            kickerLoad();
//        }
    }

    public void setOdoPosition(Pose2D position){
        pinpoint.setPosition(position);
    }

    public void updateLauncher(boolean red, boolean changeTarget){
        launcher.update(pinpoint.getPosition(), red, changeTarget);
    }

    public void teleopUpdate(boolean red){
        update();
        pinpoint.update();
        launcher.teleopDistanceLog(pinpoint.getPosition(), red);
    }

    public Pose2D getPosition(){
        return pinpoint.getPosition();
    }
}

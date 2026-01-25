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
    private int kickerState = 1;
    private ElapsedTime kickerTimer = new ElapsedTime(ElapsedTime.Resolution.MILLISECONDS);
    private ElapsedTime buttonTimer = new ElapsedTime(ElapsedTime.Resolution.MILLISECONDS);
    private int buttonDelay = 350;

    public TeleopBot(OpMode opMode, Telemetry telemetry) {
        super(opMode, telemetry);
        drivetrain = new Drivetrain(opMode.hardwareMap, telemetry, new DrivetrainData(), new GoBilda435DcMotorData());
        pinpoint = opMode.hardwareMap.get(GoBildaPinpointDriver.class, "pinpoint");
        pinpoint.setOffsets(2.25,-7, DistanceUnit.INCH);
        pinpoint.setEncoderResolution(GoBildaPinpointDriver.GoBildaOdometryPods.goBILDA_4_BAR_POD);
        pinpoint.setEncoderDirections(GoBildaPinpointDriver.EncoderDirection.FORWARD,
                GoBildaPinpointDriver.EncoderDirection.FORWARD);
        pinpoint.resetPosAndIMU();
        buttonTimer.reset();
    }

    private boolean buttonPushable() {
        return (buttonTimer.milliseconds() > buttonDelay);
    }

    public void move(double axial, double strafe, double yaw) {
        drivetrain.moveDirection(axial, strafe, yaw);
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

        if (gamepad.right_bumper) {
            setLauncherShortShot();
        } else if (gamepad.right_trigger > 0.2) {
            setLauncherMediumShot();
        } else if (gamepad.left_bumper) {
            setLauncherLongShot();
        } else if (gamepad.left_trigger > 0.2) {
            launcherStop();
        }

        if (gamepad.a) {
            intakeReverse();
        } else if (gamepad.b) {
            intakeForward();
        } else if (gamepad.y) {
            intakeStop();
        }
        
        if ((gamepad.x) && (kickerState == 1)) {
            kickerLoad();
            kickerState = 2;
            kickerTimer.reset();
        } else if ((gamepad.x) && (kickerState == 2) && (kickerTimer.milliseconds() > 250)) {
            kickerLaunch();
            kickerTimer.reset();
            kickerState = 3;
        } else if ((kickerTimer.milliseconds() > 3000) && (kickerState == 3)) {
            kickerGate();
            kickerState = 1;
        }


        if (gamepad.options) {
            kickerGate();
            intakeReverse();
            setLauncherShortShot();
        }

    }

}


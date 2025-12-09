package org.firstinspires.ftc.teamcode.opmode.tuning;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.bylazar.gamepad.Gamepad;
import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.Pose;
import com.pedropathing.localization.Localizer;
import com.qualcomm.hardware.gobilda.GoBildaPinpointDriver;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.robotcore.external.Const;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;
import org.firstinspires.ftc.teamcode.common.Drivetrain;
import org.firstinspires.ftc.teamcode.common.LauncherDouble;
import org.firstinspires.ftc.teamcode.common.hardwareConfiguration.data.DrivetrainData;
import org.firstinspires.ftc.teamcode.common.hardwareConfiguration.data.GoBilda435DcMotorData;
import org.firstinspires.ftc.teamcode.opmode.FieldLocations;
import org.firstinspires.ftc.teamcode.pedroPathing.Constants;

@Config
@TeleOp(name = "LauncherDoubleTuner - Odo", group = "Tuning")

public class LauncherDoubleTunerWIthOdo extends LinearOpMode {

    private LauncherDouble launcher;
    private double velocityFactorIncrement = 0.1;
    private double targetVelocityFactor = 0.0;
    private double positionIncrement = 0.05;
    private double targetLaunchPosition = 0.5;
    private GoBildaPinpointDriver pinpoint;
    private Drivetrain drivetrain;
    private DcMotorEx intake, lowerRoller;
    private CRServo upperRoller;

    @Override
    public void runOpMode() {
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
        launcher = new LauncherDouble(hardwareMap, telemetry);
        launcher.setVelocityFactor(targetVelocityFactor);
        launcher.setLaunchPosition(targetLaunchPosition);
        drivetrain = new Drivetrain(hardwareMap, telemetry, new DrivetrainData(), new GoBilda435DcMotorData());
        pinpoint = hardwareMap.get(GoBildaPinpointDriver.class, "pinpoint");
        pinpoint.setOffsets(-2.5,-4.5, DistanceUnit.INCH);
        pinpoint.setEncoderResolution(GoBildaPinpointDriver.GoBildaOdometryPods.goBILDA_4_BAR_POD);
        pinpoint.setEncoderDirections(GoBildaPinpointDriver.EncoderDirection.FORWARD,
                GoBildaPinpointDriver.EncoderDirection.FORWARD);
        pinpoint.resetPosAndIMU();
        pinpoint.setPosition(new Pose2D(DistanceUnit.INCH, 0,0, AngleUnit.DEGREES, -45));
        pinpoint.update();
        intake = hardwareMap.get(DcMotorEx.class, "intake");
        lowerRoller = hardwareMap.get(DcMotorEx.class, "lowerRoller");
        upperRoller = hardwareMap.get(CRServo.class, "upperRoller");
        lowerRoller.setDirection(DcMotorSimple.Direction.REVERSE);
        waitForStart();

        while (opModeIsActive() && !isStopRequested()) {
            if (gamepad1.y) {
                targetVelocityFactor += velocityFactorIncrement;
            } else if (gamepad1.a) {
                targetVelocityFactor -= velocityFactorIncrement;
            }
            if (gamepad1.b) {
                targetLaunchPosition += positionIncrement;
            } else if (gamepad1.x) {
                targetLaunchPosition -= positionIncrement;
            }
            if (gamepad1.right_bumper){
                intake.setPower(1);
                lowerRoller.setPower(1);
                upperRoller.setPower(1);
            }
            else if (gamepad1.left_bumper){
                intake.setPower(0);
                lowerRoller.setPower(0);
                upperRoller.setPower(0);
            }
            drivetrain.moveDirection(-gamepad1.left_stick_y, gamepad1.left_stick_x, gamepad1.right_stick_x);
            pinpoint.update();
            Pose2D pose = pinpoint.getPosition();
            Pose2D target = new Pose2D(DistanceUnit.INCH, 1,1, AngleUnit.DEGREES, 0);

            telemetry.addData("X (inches)", pose.getX(DistanceUnit.INCH));
            telemetry.addData("Y (inches)", pose.getY(DistanceUnit.INCH));
            telemetry.addData("Heading (degree)", pose.getHeading(AngleUnit.DEGREES));
            double distance = Math.sqrt(Math.pow(pose.getX(DistanceUnit.INCH)-target.getX(DistanceUnit.INCH),2)+Math.pow(pose.getY(DistanceUnit.INCH)-target.getY(DistanceUnit.INCH),2));
            telemetry.addData("Distance from target: ", distance);
            launcher.setVelocityFactor(targetVelocityFactor);
            launcher.setLaunchPosition(targetLaunchPosition);
            telemetry.addData("targetVelocityFactor in launcherDoubleTest: ", targetVelocityFactor);
            telemetry.addData("targetLaunchPosition in launcherDoubleTest: ", targetLaunchPosition);
            launcher.log();
            telemetry.update();
            sleep(100);
        }
    }
}

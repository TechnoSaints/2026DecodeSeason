package org.firstinspires.ftc.teamcode.opmode.teleop;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.hardware.gobilda.GoBildaPinpointDriver;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;
import org.firstinspires.ftc.teamcode.common.Drivetrain;
import org.firstinspires.ftc.teamcode.common.Launcher;
import org.firstinspires.ftc.teamcode.common.Storage;
import org.firstinspires.ftc.teamcode.common.hardwareConfiguration.data.DrivetrainData;
import org.firstinspires.ftc.teamcode.common.hardwareConfiguration.data.GoBilda435DcMotorData;

@Config
@TeleOp(name = "Old Teleop (tuner)", group = "1 Game")

public class TeleopOld extends LinearOpMode {
    private Launcher launcher;
    private double velocityFactorIncrement = 0.005;
    private double targetVelocityFactor = 0.0;
    private double positionIncrement = 0.05;
    private double targetLaunchPosition = 0.2;
    private GoBildaPinpointDriver pinpoint;
    private Drivetrain drivetrain;
    private Storage storage;
    private ElapsedTime timer;

    @Override
    public void runOpMode() {
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
        launcher = new Launcher(telemetry, hardwareMap);
        launcher.setVelocity(targetVelocityFactor);
        launcher.setPosition(targetLaunchPosition);
        Servo gate = hardwareMap.get(Servo.class, "gate");
        drivetrain = new Drivetrain(this, hardwareMap, telemetry, new DrivetrainData(), new GoBilda435DcMotorData());
        pinpoint = hardwareMap.get(GoBildaPinpointDriver.class, "pinpoint");
        pinpoint.setOffsets(-2.5,-4.5, DistanceUnit.INCH);
        pinpoint.setEncoderResolution(GoBildaPinpointDriver.GoBildaOdometryPods.goBILDA_4_BAR_POD);
        pinpoint.setEncoderDirections(GoBildaPinpointDriver.EncoderDirection.FORWARD,
                GoBildaPinpointDriver.EncoderDirection.FORWARD);
        pinpoint.resetPosAndIMU();
        pinpoint.setPosition(new Pose2D(DistanceUnit.INCH, 0,0, AngleUnit.DEGREES, -45));
        pinpoint.update();
        storage = new Storage(telemetry, hardwareMap);
        timer = new ElapsedTime();
        waitForStart();
        timer.reset();

        while (opModeIsActive() && !isStopRequested()) {
            if (gamepad1.right_trigger > 0.7) {
                gate.setPosition(0.69);
            } else {
                gate.setPosition(0.5);
            }
            if (gamepad1.right_trigger > 0.7 && targetVelocityFactor < 1 && timer.milliseconds() >= 100) {
                targetVelocityFactor += velocityFactorIncrement;
                timer.reset();
            } else if (gamepad1.left_trigger > 0.7 && targetVelocityFactor > 0 && timer.milliseconds() >= 100) {
                targetVelocityFactor -= velocityFactorIncrement;
                timer.reset();
            }
            if (gamepad1.rightBumperWasPressed() && targetLaunchPosition < 1) {
                targetLaunchPosition += positionIncrement;
            } else if (gamepad1.leftBumperWasPressed() && targetLaunchPosition > 0) {
                targetLaunchPosition -= positionIncrement;
            }
            if (gamepad1.left_stick_button){
                storage.intakeBalls();
            }
            else if (gamepad1.right_stick_button){
                storage.shootBalls();
            }
            else if (gamepad1.psWasPressed()){
                storage.stop();
            }
            if (gamepad1.options){
                storage.manualForward();
            }
            else if (gamepad1.share){
                storage.manualBackward();
            }
            storage.updateStorage();
            if (gamepad1.dpad_left){
                drivetrain.moveDirection(0, -0.3, 0);
            }
            else if (gamepad1.dpad_right){
                drivetrain.moveDirection(0, 0.3, 0);
            }
            else if (gamepad1.dpad_down){
                drivetrain.moveDirection(-0.25, 0, 0);
            }
            else if (gamepad1.dpad_up){
                drivetrain.moveDirection(0.25, 0, 0);
            }
            else {
                drivetrain.moveDirection(-gamepad1.left_stick_y, gamepad1.left_stick_x, gamepad1.right_stick_x);
            }
            pinpoint.update();
            Pose2D pose = pinpoint.getPosition();
            Pose2D target = new Pose2D(DistanceUnit.INCH, 1,1, AngleUnit.DEGREES, 0);

            telemetry.addData("Launcher is busy?", launcher.motorBusy());
            telemetry.addData("X (inches)", pose.getX(DistanceUnit.INCH));
            telemetry.addData("Y (inches)", pose.getY(DistanceUnit.INCH));
            telemetry.addData("Heading (degree)", pose.getHeading(AngleUnit.DEGREES));
            double distance = Math.sqrt(Math.pow(pose.getX(DistanceUnit.INCH)-target.getX(DistanceUnit.INCH),2)+Math.pow(pose.getY(DistanceUnit.INCH)-target.getY(DistanceUnit.INCH),2));
            telemetry.addData("Distance from target: ", distance);
            launcher.setVelocity(targetVelocityFactor);
            launcher.setPosition(targetLaunchPosition);
            telemetry.addData("targetVelocityFactor in launcherDoubleTest: ", targetVelocityFactor);
            telemetry.addData("targetLaunchPosition in launcherDoubleTest: ", targetLaunchPosition);
            launcher.log();
            telemetry.update();
        }
    }
}

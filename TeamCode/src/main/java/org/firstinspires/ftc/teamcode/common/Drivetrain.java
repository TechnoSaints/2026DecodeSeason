package org.firstinspires.ftc.teamcode.common;

import com.arcrobotics.ftclib.controller.PIDFController;
import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.Pose;
import com.qualcomm.hardware.gobilda.GoBildaPinpointDriver;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.hardware.TouchSensor;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.teamcode.common.hardwareConfiguration.data.DrivetrainData;
import org.firstinspires.ftc.teamcode.common.hardwareConfiguration.data.MotorData;
import org.firstinspires.ftc.teamcode.opmode.FieldLocations;
import org.firstinspires.ftc.teamcode.pedroPathing.Constants;

public class Drivetrain extends Component {
    private final DcMotorEx leftFrontDrive;
    private final DcMotorEx rightFrontDrive;
    private final DcMotorEx leftBackDrive;
    private final DcMotorEx rightBackDrive;
    private final double maxFastPower;
    private final double maxMediumPower;
    private final double maxSlowPower;
    private Follower follower;
    private HardwareMap hardwareMap;
    private GoBildaPinpointDriver pinpoint;
    private final double driveGain = 0.03;
    private double ticksPerInch;
    private static OpMode opMode;

    public Drivetrain(OpMode opMode, HardwareMap hardwareMap, Telemetry telemetry, DrivetrainData drivetrainData, MotorData motorData) {
        super(telemetry);
        maxFastPower = drivetrainData.maxFastTeleopPower;
        maxMediumPower = drivetrainData.maxMediumTeleopPower;
        maxSlowPower = drivetrainData.maxSlowTeleopPower;
        follower = Constants.createFollower(hardwareMap);
        follower.update();
        this.hardwareMap = hardwareMap;
        this.opMode = opMode;

        leftFrontDrive = hardwareMap.get(DcMotorEx.class, drivetrainData.leftFrontMotorName);
        leftBackDrive = hardwareMap.get(DcMotorEx.class,drivetrainData.leftRearMotorName);
        rightFrontDrive = hardwareMap.get(DcMotorEx.class, drivetrainData.rightFrontMotorName);
        rightBackDrive = hardwareMap.get(DcMotorEx.class, drivetrainData.rightRearMotorName);

        leftFrontDrive.setDirection(drivetrainData.leftFrontMotorDirection);
        leftBackDrive.setDirection(drivetrainData.leftRearMotorDirection);
        rightFrontDrive.setDirection(drivetrainData.rightFrontMotorDirection);
        rightBackDrive.setDirection(drivetrainData.rightRearMotorDirection);
        pinpoint = hardwareMap.get(GoBildaPinpointDriver.class, "pinpoint");
        pinpoint.resetPosAndIMU();
        setBrakingOn();
    }

    public void pinpointUpdate(){
        pinpoint.update();
    }

    public void creepDirection(double axial, double strafe, double yaw) {
        moveDirection(axial * maxSlowPower, strafe * maxSlowPower, yaw * maxSlowPower);
    }

    public void moveDirection(double axial, double strafe, double yaw) {
        // Calculate wheel powers.
        double leftFrontPower = axial + strafe + yaw;
        double rightFrontPower = axial - strafe - yaw;
        double leftBackPower = axial - strafe + yaw;
        double rightBackPower = axial + strafe - yaw;

        // Normalize wheel powers to be less than 1.0
        double max = Math.max(Math.abs(leftFrontPower), Math.abs(rightFrontPower));
        max = Math.max(max, Math.abs(leftBackPower));
        max = Math.max(max, Math.abs(rightBackPower));

        if (max > 1.0) {
            leftFrontPower /= max;
            rightFrontPower /= max;
            leftBackPower /= max;
            rightBackPower /= max;
        }

        leftFrontDrive.setPower(leftFrontPower * maxFastPower);
        rightFrontDrive.setPower(rightFrontPower * maxFastPower);
        leftBackDrive.setPower(leftBackPower * maxFastPower);
        rightBackDrive.setPower(rightBackPower * maxFastPower);
//        log();
    }

    public void stop() {
        leftFrontDrive.setPower(0.0);
        leftBackDrive.setPower(0.0);
        rightFrontDrive.setPower(0.0);
        rightBackDrive.setPower(0.0);
    }

    private void setBrakingOn() {
        leftFrontDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftBackDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightFrontDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightBackDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    private void setBrakingOff() {
        leftFrontDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        leftBackDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        rightFrontDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        rightBackDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
    }

    private void setRunToPosition(){
        leftFrontDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightFrontDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftBackDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightBackDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

    private void setRunUsingEncoder(){
        leftFrontDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightBackDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftBackDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightFrontDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public void setOdoStartingPose(Pose startPose){
        follower.setStartingPose(startPose);
        follower.update();
    }

    public void resetOdo(){
        follower = Constants.createFollower(hardwareMap);
        setOdoStartingPose(FieldLocations.redBaseStartPose);
    }

    public void startTeleopDrive(){
        follower.startTeleopDrive();
    }

    public void loop(){
        follower.update();
    }

    public void processTeleopDrive(Gamepad gamepad){
        if (gamepad.dpad_up){
            follower.setTeleOpDrive(maxSlowPower, 0, 0, true);
        }
        else if (gamepad.dpad_down){
            follower.setTeleOpDrive(-maxSlowPower, 0, 0, true);
        }
        else if (gamepad.dpad_left){
            follower.setTeleOpDrive(0, -maxSlowPower, 0, true);
        }
        else if (gamepad.dpad_right){
            follower.setTeleOpDrive(0, maxSlowPower, 0, true);
        }
        else {
            follower.setTeleOpDrive(gamepad.left_stick_y, gamepad.left_stick_x, gamepad.right_stick_x, true);
        }
        loop();
    }

    public Pose getPose(){
        return follower.getPose();
    }

    public double getHeading(){
        return pinpoint.getHeading(AngleUnit.DEGREES);
    }

    public void moveForwardForDistance(double distance){
        moveForwardForDistance(distance, maxMediumPower);
    }

    public double getHeadingError(double targetHeading) {
        return (targetHeading - getHeading());
    }

    public double getSteeringCorrection(double headingError, double gain) {
        // Determine the heading current error

        // Normalize the error to be within +/- 180 degrees
        while (headingError > 180) headingError -= 360;
        while (headingError <= -180) headingError += 360;

        // Multiply the error by the gain to determine the required steering correction/  Limit the result to +/- 1.0
        return Range.clip(headingError * gain, -1, 1);
    }

    private void moveForwardForDistance(double distance, double driveSpeed) {
        int targetCounts = (int) (-distance * ticksPerInch);
        int leftFrontTarget = 0;
        int leftBackTarget = 0;
        int rightFrontTarget = 0;
        int rightBackTarget = 0;
        double turnSpeed = 0;
        double headingError = 0;
        double targetHeading = pinpoint.getHeading(AngleUnit.DEGREES);

        leftFrontTarget = leftFrontDrive.getCurrentPosition() + targetCounts;
        leftBackTarget = leftBackDrive.getCurrentPosition() + targetCounts;
        rightFrontTarget = rightFrontDrive.getCurrentPosition() + targetCounts;
        rightBackTarget = rightBackDrive.getCurrentPosition() + targetCounts;

        leftFrontDrive.setTargetPosition(leftFrontTarget);
        leftBackDrive.setTargetPosition(leftBackTarget);
        rightFrontDrive.setTargetPosition(rightFrontTarget);
        rightBackDrive.setTargetPosition(rightBackTarget);

        setRunToPosition();

        while (leftFrontDrive.isBusy() && leftBackDrive.isBusy() && rightFrontDrive.isBusy() && rightBackDrive.isBusy() && !(opMode instanceof LinearOpMode && ((LinearOpMode) opMode).isStopRequested())) {
            //while (leftFrontDrive.isBusy()) {
            headingError = getHeadingError(targetHeading);
            // Determine required steering to keep on heading
            turnSpeed = getSteeringCorrection(headingError, driveGain);

            // if driving in reverse, the motor correction also needs to be reversed
            if (distance < 0)
                turnSpeed *= -1.0;

            // Apply the turning correction to the current driving speed.
            moveDirection(driveSpeed, 0.0, 0.0);
            //           log();
        }
        stop();
        setRunUsingEncoder();
    }

    public void strafeRightForDistance(double distance) {
        int targetCounts = (int) (-distance * ticksPerInch);
        int leftFrontTarget = 0;
        int leftBackTarget = 0;
        int rightFrontTarget = 0;
        int rightBackTarget = 0;
        double strafeSpeed = maxMediumPower;

        leftFrontTarget = leftFrontDrive.getCurrentPosition() + targetCounts;
        leftBackTarget = leftBackDrive.getCurrentPosition() - targetCounts;
        rightFrontTarget = rightFrontDrive.getCurrentPosition() - targetCounts;
        rightBackTarget = rightBackDrive.getCurrentPosition() + targetCounts;

        leftFrontDrive.setTargetPosition(leftFrontTarget);
        leftBackDrive.setTargetPosition(leftBackTarget);
        rightFrontDrive.setTargetPosition(rightFrontTarget);
        rightBackDrive.setTargetPosition(rightBackTarget);

        setRunToPosition();

        while (leftFrontDrive.isBusy() && leftBackDrive.isBusy() && rightFrontDrive.isBusy() && rightBackDrive.isBusy() && !(opMode instanceof LinearOpMode && ((LinearOpMode) opMode).isStopRequested())) {
            moveDirection(0, strafeSpeed, 0);
        }
        stop();
        setRunUsingEncoder();
    }

    private void log() {
        telemetry.addData("leftFrontDrive Position: ", leftFrontDrive.getCurrentPosition());
        telemetry.addData("leftFrontDrive Target: ", leftFrontDrive.getTargetPosition());
        telemetry.addData("leftFrontDrive Velocity: ", leftFrontDrive.getVelocity());
    }
}
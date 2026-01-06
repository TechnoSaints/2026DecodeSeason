package org.firstinspires.ftc.teamcode.common;

import androidx.annotation.NonNull;

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
    private final double headingThreshold = 0.5;
    private final double driveGain = 0.03;
    private final double turnGain = 0.02;
    private static OpMode opMode;

    // *** ADDED CONSTANTS FOR ENCODER CALCULATIONS ***
    // These values are placeholders; you must verify them for your specific robot hardware.
    static final double COUNTS_PER_MOTOR_REV = 537.7;
    static final double DRIVE_GEAR_REDUCTION = 1.0;     // No external gear reduction
    static final double WHEEL_DIAMETER_INCHES = 4.09448819;   // Diameter of your robot's wheels
    static final double ticksPerInch = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) /
            (WHEEL_DIAMETER_INCHES * 3.1415);
    static final double DRIVE_SPEED = 0.6; // Default autonomous drive speed

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
        leftBackDrive = hardwareMap.get(DcMotorEx.class, drivetrainData.leftRearMotorName);
        rightFrontDrive = hardwareMap.get(DcMotorEx.class, drivetrainData.rightFrontMotorName);
        rightBackDrive = hardwareMap.get(DcMotorEx.class, drivetrainData.rightRearMotorName);

        leftFrontDrive.setDirection(drivetrainData.leftFrontMotorDirection);
        leftBackDrive.setDirection(drivetrainData.leftRearMotorDirection);
        rightFrontDrive.setDirection(drivetrainData.rightFrontMotorDirection);
        rightBackDrive.setDirection(drivetrainData.rightRearMotorDirection);
        this.opMode = opMode;

        pinpoint = hardwareMap.get(GoBildaPinpointDriver.class, "pinpoint");
        pinpoint.resetPosAndIMU();
        setBrakingOn();

        // *** ENABLE ENCODERS FOR AUTONOMOUS MODES ***
        setRunMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        setRunMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    // Helper method to set run mode for all motors
    private void setRunMode(DcMotor.RunMode mode) {
        leftFrontDrive.setMode(mode);
        rightFrontDrive.setMode(mode);
        leftBackDrive.setMode(mode);
        rightBackDrive.setMode(mode);
    }

    // *** ADDED METHOD 1: Move Straight (Axial) ***
    public void moveStraight(double inches) {
        // Simple implementation assuming forward is positive axial for all wheels
        moveByEncoder(inches, 0, 0, DRIVE_SPEED);
    }

    // *** ADDED METHOD 2: Strafe (Lateral) ***
    public void strafe(double inches) {
        // Simple implementation for strafing
        moveByEncoder(0, inches, 0, DRIVE_SPEED);
    }

    // *** ADDED METHOD 3: Turn (Yaw) ***
    public void turn(double inches) {
        // Turning is treated as an arc/distance for simplicity in this encoder example
        // (You might want a separate angular PID turn for actual field navigation)
        moveByEncoder(0, 0, inches, DRIVE_SPEED);
    }

    // Core method for encoder movements
    private void moveByEncoder(double axialInches, double strafeInches, double yawInches, double speed) {
        int leftFrontTarget;
        int rightFrontTarget;
        int leftBackTarget;
        int rightBackTarget;

        // Calculate targets based on the current position and desired change in inches
        leftFrontTarget = leftFrontDrive.getCurrentPosition() + (int) ((axialInches + strafeInches + yawInches) * ticksPerInch);
        rightFrontTarget = rightFrontDrive.getCurrentPosition() + (int) ((axialInches - strafeInches - yawInches) * ticksPerInch);
        leftBackTarget = leftBackDrive.getCurrentPosition() + (int) ((axialInches - strafeInches + yawInches) * ticksPerInch);
        rightBackTarget = rightBackDrive.getCurrentPosition() + (int) ((axialInches + strafeInches - yawInches) * ticksPerInch);


        telemetry.addData("leftFrontTarget", leftFrontTarget);
        // Set Target Position
        leftFrontDrive.setTargetPosition(leftFrontTarget);
        rightFrontDrive.setTargetPosition(rightFrontTarget);
        leftBackDrive.setTargetPosition(leftBackTarget);
        rightBackDrive.setTargetPosition(rightBackTarget);

        // Switch to RUN_TO_POSITION mode
        setRunMode(DcMotor.RunMode.RUN_TO_POSITION);

        // Start motion
        leftFrontDrive.setPower(Math.abs(speed));
        rightFrontDrive.setPower(Math.abs(speed));
        leftBackDrive.setPower(Math.abs(speed));
        rightBackDrive.setPower(Math.abs(speed));

        // Note: In a real autonomous OpMode, you must add a loop
        // to wait for the motors to reach the target position.
        // The `while (opModeIsActive() && (leftFrontDrive.isBusy() || ...))` loop
        // is typically included in the LinearOpMode that calls this method.

        while ((leftBackDrive.isBusy() || rightBackDrive.isBusy() || leftFrontDrive.isBusy() || rightFrontDrive.isBusy()) && !(opMode instanceof LinearOpMode && ((LinearOpMode) opMode).isStopRequested())) {

        }
    }


    public void pinpointUpdate(){
        pinpoint.update();
    }

    public void creepDirection(double axial, double strafe, double yaw) {
        moveDirection(axial * maxSlowPower, strafe * maxSlowPower, yaw * maxSlowPower);
    }

    public void moveDirection(double axial, double strafe, double yaw) {
        // Ensure that manual movement methods put motors back into the correct mode for teleop
        setRunMode(DcMotor.RunMode.RUN_USING_ENCODER); // Or RUN_WITHOUT_ENCODER if that's preferred for teleop control
        axial = -axial;
        strafe = -strafe;
        yaw = -yaw;

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
        // Stop also typically involves setting the mode back to RUN_USING_ENCODER in autonomous
        setRunMode(DcMotor.RunMode.RUN_USING_ENCODER);
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

    public void turnToHeading(double targetHeading) {
        double turnSpeed = maxMediumPower;
        double headingError = getHeadingError(targetHeading);

        // keep looping while we are still active, and not on heading.
        while (Math.abs(headingError) > headingThreshold) {
            headingError = getHeadingError(targetHeading);

            // This section doesn't seem to work...


            // Determine required steering to keep on heading
            turnSpeed = getSteeringCorrection(headingError, turnGain);

            // Clip the speed to the maximum permitted value.
            turnSpeed = Range.clip(turnSpeed, -maxMediumPower, maxMediumPower);

            // Pivot in place by applying the turning correction
            moveDirection(0, 0, -turnSpeed);

            /*if (getHeadingError(targetHeading) > headingError){
                moveDirection(0, 0, -turnSpeed);
            }
            while (Math.abs(headingError) > headingThreshold && opMode.opModeIsActive() && !opMode.isStopRequested())
            {*/
            telemetry.addData("headingError: ", headingError);
            telemetry.addData("turnSpeed: ", turnSpeed);
            telemetry.addData("targetHeading", targetHeading);
            telemetry.addData("currentPosition", getHeading());
            telemetry.update();
            //}
        }
        leftFrontDrive.setVelocity(0.0);
        leftBackDrive.setVelocity(0.0);
        rightFrontDrive.setVelocity(0.0);
        rightBackDrive.setVelocity(0.0);
    }

    public GoBildaPinpointDriver getPinpoint(){
        return pinpoint;
    }

    public void log() {
        telemetry.addData("leftFrontDrive Position: ", leftFrontDrive.getCurrentPosition());
        telemetry.addData("leftFrontDrive Target: ", leftFrontDrive.getTargetPosition());
        telemetry.addData("leftFrontDrive Velocity: ", leftFrontDrive.getVelocity());
        telemetry.update();
    }
}
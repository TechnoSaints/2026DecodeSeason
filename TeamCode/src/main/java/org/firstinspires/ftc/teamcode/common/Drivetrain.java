package org.firstinspires.ftc.teamcode.common;

import com.arcrobotics.ftclib.controller.PIDFController;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.hardware.TouchSensor;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.robotcore.external.navigation.YawPitchRollAngles;

import com.qualcomm.robotcore.util.Range;
import org.firstinspires.ftc.robotcore.external.Telemetry;
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
    private static OpMode opMode;

    private IMU imu;

    private double robotHeading = 0;

    // *** ADDED CONSTANTS FOR ENCODER CALCULATIONS ***
    // These values are placeholders; you must verify them for your specific robot hardware.
    static final double COUNTS_PER_MOTOR_REV = 537.7;  // Gobilda 19.2:1 Yellow Jacket motors
    static final double DRIVE_GEAR_REDUCTION = 1.0;     // No external gear reduction
    static final double WHEEL_DIAMETER_INCHES = 4.09448819;   // Diameter of your robot's wheels
    static final double COUNTS_PER_INCH = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) /
            (WHEEL_DIAMETER_INCHES * 3.1415);
    static final double DRIVE_SPEED = 0.6; // Default autonomous drive speed
    static final double Drive_Slow_Speed = 0.125;

    static final double ROBOT_TRACK_WIDTH_INCHES = 14.5; // Example: distance between left and right wheels

    // Calculated constant: Inches traveled by one wheel to turn 1 full degree (2 * PI * R / 360)
    static final double INCHES_PER_DEGREE = (Math.PI * ROBOT_TRACK_WIDTH_INCHES) / 360.0;


    public Drivetrain(OpMode opMode, HardwareMap hardwareMap, Telemetry telemetry, DrivetrainData drivetrainData, MotorData motorData) {
        super(telemetry);
        maxFastPower = drivetrainData.maxFastTeleopPower;
        maxMediumPower = drivetrainData.maxMediumTeleopPower;
        maxSlowPower = drivetrainData.maxSlowTeleopPower;
        leftFrontDrive = hardwareMap.get(DcMotorEx.class, drivetrainData.leftFrontMotorName);
        leftBackDrive = hardwareMap.get(DcMotorEx.class, drivetrainData.leftRearMotorName);
        rightFrontDrive = hardwareMap.get(DcMotorEx.class, drivetrainData.rightFrontMotorName);
        rightBackDrive = hardwareMap.get(DcMotorEx.class, drivetrainData.rightRearMotorName);
        leftFrontDrive.setDirection(drivetrainData.leftFrontMotorDirection);
        leftBackDrive.setDirection(drivetrainData.leftRearMotorDirection);
        rightFrontDrive.setDirection(drivetrainData.rightFrontMotorDirection);
        rightBackDrive.setDirection(drivetrainData.rightRearMotorDirection);
        this.opMode = opMode;

        setBrakingOn();

        // *** ENABLE ENCODERS FOR AUTONOMOUS MODES ***
        setRunMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        setRunMode(DcMotor.RunMode.RUN_USING_ENCODER);

        imu = hardwareMap.get(IMU.class, "imu");
        RevHubOrientationOnRobot.LogoFacingDirection logoDirection = RevHubOrientationOnRobot.LogoFacingDirection.LEFT; // Adjust based on actual hub mounting
        RevHubOrientationOnRobot.UsbFacingDirection usbDirection = RevHubOrientationOnRobot.UsbFacingDirection.BACKWARD; // Adjust based on actual hub mounting
        RevHubOrientationOnRobot orientationOnRobot = new RevHubOrientationOnRobot(logoDirection, usbDirection);

        imu.initialize(new IMU.Parameters(orientationOnRobot));
        imu.resetYaw(); // Reset the heading at the start of autonomous
    }

    // Method to retrieve the current robot heading (yaw)
    public double getHeading() {
        // Get the robot's current orientation
        YawPitchRollAngles orientation = imu.getRobotYawPitchRollAngles();

        // Extract the yaw angle in the desired unit (e.g., DEGREES or RADIANS)
        // Counter-clockwise is typically positive for yaw in the robot coordinate system
        double yawAngle = orientation.getYaw(AngleUnit.DEGREES);

        // Store the heading if needed elsewhere in the class (optional)
        this.robotHeading = yawAngle;

        return yawAngle;
    }

    // Inside the Drivetrain class
    public void turnToAngle(double targetAngle, double power) {
        double currentAngle = getHeading();
        double angleError = targetAngle - currentAngle;

        // Normalize the angle error to be within -180 to 180 degrees
        while (angleError > 180) angleError -= 360;
        while (angleError <= -180) angleError += 360;

        // Use a proportional constant (Kp) to determine motor power
        // This value (e.g., 0.05) needs tuning for your specific robot
        final double Kp = 0.05;

        // Keep turning while the OpMode is active and the error is significant
        while (/*opMode.opModeIsActive() && */ Math.abs(angleError) > 2.0) { // Tolerance of 2 degrees
            double turnPower = angleError * Kp;

            // Cap the power to a reasonable value
            if (Math.abs(turnPower) > power) {
                turnPower = Math.signum(turnPower) * power;
            }

            // Set motor powers for turning in place (adjust signs if necessary for your chassis)
            leftFrontDrive.setPower(-turnPower);
            rightFrontDrive.setPower(turnPower);
            leftBackDrive.setPower(-turnPower);
            rightBackDrive.setPower(turnPower);

            // Update the angle error
            currentAngle = getHeading();
            angleError = targetAngle - currentAngle;
            while (angleError > 180) angleError -= 360;
            while (angleError <= -180) angleError += 360;

            // Optional: Add telemetry for tuning
            opMode.telemetry.addData("Target Angle", targetAngle);
            opMode.telemetry.addData("Current Angle", currentAngle);
            opMode.telemetry.addData("Angle Error", angleError);
            opMode.telemetry.update();
        }

        // Stop all motors after the turn is complete
        leftFrontDrive.setPower(0);
        rightFrontDrive.setPower(0);
        leftBackDrive.setPower(0);
        rightBackDrive.setPower(0);
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
        // This is the code to turn in degrees

        /* double turnInches = degrees * INCHES_PER_DEGREE;

        // Pass this calculated arc length to the core movement method as yawInches
        moveByEncoder(0, 0, turnInches, Drive_Speed); */


        moveByEncoder(0, 0, inches, DRIVE_SPEED);
    }

    public void moveStraightSlow(double inches) {
        // Simple implementation assuming forward is positive axial for all wheels
        moveByEncoder(inches, 0, 0, Drive_Slow_Speed);
    }

    // *** ADDED METHOD 2: Strafe (Lateral) ***
    public void strafeSlow(double inches) {
        // Simple implementation for strafing
        moveByEncoder(0, inches, 0, Drive_Slow_Speed);
    }

    // *** ADDED METHOD 3: Turn (Yaw) ***
    public void turnSlow(double inches) {
        // Turning is treated as an arc/distance for simplicity in this encoder example
        // (You might want a separate angular PID turn for actual field navigation)
        moveByEncoder(0, 0, inches, Drive_Slow_Speed);
    }

    // Core method for encoder movements
    private void moveByEncoder(double axialInches, double strafeInches, double yawInches, double speed) {
        int leftFrontTarget;
        int rightFrontTarget;
        int leftBackTarget;
        int rightBackTarget;

        // Calculate targets based on the current position and desired change in inches
        leftFrontTarget = leftFrontDrive.getCurrentPosition() + (int) ((axialInches + strafeInches + yawInches) * COUNTS_PER_INCH);
        rightFrontTarget = rightFrontDrive.getCurrentPosition() + (int) ((axialInches - strafeInches - yawInches) * COUNTS_PER_INCH);
        leftBackTarget = leftBackDrive.getCurrentPosition() + (int) ((axialInches - strafeInches + yawInches) * COUNTS_PER_INCH);
        rightBackTarget = rightBackDrive.getCurrentPosition() + (int) ((axialInches + strafeInches - yawInches) * COUNTS_PER_INCH);


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

        leftFrontDrive.setTargetPositionTolerance(10);
        rightFrontDrive.setTargetPositionTolerance(10);
        leftBackDrive.setTargetPositionTolerance(10);
        rightBackDrive.setTargetPositionTolerance(10);
        // Note: In a real autonomous OpMode, you must add a loop
        // to wait for the motors to reach the target position.
        // The `while (opModeIsActive() && (leftFrontDrive.isBusy() || ...))` loop
        // is typically included in the LinearOpMode that calls this method.

//        abstract void	setTargetPositionTolerance(int tolerance)	Sets the target positioning tolerance of this motor
//        abstract int	getTargetPositionTolerance()	Returns the current target positioning tolerance of this motor

        while ((leftBackDrive.isBusy() && rightBackDrive.isBusy() && leftFrontDrive.isBusy() && rightFrontDrive.isBusy()) && !(opMode instanceof LinearOpMode && ((LinearOpMode) opMode).isStopRequested())) {

        }
    }




    public void creepDirection(double axial, double strafe, double yaw) {
        moveDirection(axial * maxSlowPower, strafe * maxSlowPower, yaw * maxSlowPower);
    }

    public void moveDirection(double axial, double strafe, double yaw) {
        // Ensure that manual movement methods put motors back into the correct mode for teleop
        setRunMode(DcMotor.RunMode.RUN_USING_ENCODER); // Or RUN_WITHOUT_ENCODER if that's preferred for teleop control

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
        // log();


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


    public void log() {
        telemetry.addData("leftFrontDrive Position: ", leftFrontDrive.getCurrentPosition());
        telemetry.addData("leftFrontDrive Target: ", leftFrontDrive.getTargetPosition());
        telemetry.addData("leftFrontDrive Velocity: ", leftFrontDrive.getVelocity());
        telemetry.addData("Tolerance Level: ", leftFrontDrive.getTargetPositionTolerance());
        telemetry.update();
    }
}

package org.firstinspires.ftc.teamcode.common;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.common.hardwareConfiguration.data.DrivetrainData;
import org.firstinspires.ftc.teamcode.common.hardwareConfiguration.data.MotorData;

public class Drivetrain extends Component {
    private final DcMotorEx leftFrontDrive;
    private final DcMotorEx rightFrontDrive;
    private final DcMotorEx leftBackDrive;
    private final DcMotorEx rightBackDrive;
    private final double maxFastPower;
    private final double maxMediumPower;
    private final double maxSlowPower;

    private double currentPower;

    // *** ADDED CONSTANTS FOR ENCODER CALCULATIONS ***
    // These values are placeholders; you must verify them for your specific robot hardware.
    static final double COUNTS_PER_MOTOR_REV = 537.7;  // Gobilda 19.2:1 Yellow Jacket motors
    static final double DRIVE_GEAR_REDUCTION = 0.66;     // No external gear reduction
    static final double WHEEL_DIAMETER_INCHES = 4.09448819;   // Diameter of your robot's wheels
    static final double COUNTS_PER_INCH = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) /
            (WHEEL_DIAMETER_INCHES * 3.1415);
    static final double DRIVE_SPEED = 0.6; // Default autonomous drive speed

    public Drivetrain(HardwareMap hardwareMap, Telemetry telemetry, DrivetrainData drivetrainData, MotorData motorData) {
        super(telemetry);
        maxFastPower = drivetrainData.maxFastTeleopPower;
        maxMediumPower = drivetrainData.maxMediumTeleopPower;
        maxSlowPower = drivetrainData.maxSlowTeleopPower;

        leftFrontDrive = hardwareMap.get(DcMotorEx.class, drivetrainData.leftFrontMotorName);
        leftBackDrive = hardwareMap.get(DcMotorEx.class,drivetrainData.leftRearMotorName);
        rightFrontDrive = hardwareMap.get(DcMotorEx.class, drivetrainData.rightFrontMotorName);
        rightBackDrive = hardwareMap.get(DcMotorEx.class, drivetrainData.rightRearMotorName);

        leftFrontDrive.setDirection(drivetrainData.leftFrontMotorDirection);
        leftBackDrive.setDirection(drivetrainData.leftRearMotorDirection);
        rightFrontDrive.setDirection(drivetrainData.rightFrontMotorDirection);
        rightBackDrive.setDirection(drivetrainData.rightRearMotorDirection);
        setBrakingOn();

        setRunMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        setRunMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public void creepDirection(double axial, double strafe, double yaw) {
        moveDirection(axial * maxSlowPower, strafe * maxSlowPower, yaw * maxSlowPower);
    }
    private void setRunMode(DcMotor.RunMode mode) {
        leftFrontDrive.setMode(mode);
        rightFrontDrive.setMode(mode);
        leftBackDrive.setMode(mode);
        rightBackDrive.setMode(mode);
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
        // Stop also typically involves setting the mode back to RUN_USING_ENCODER in autonomous
        setRunMode(DcMotor.RunMode.RUN_USING_ENCODER);
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

    private void log() {
        telemetry.addData("leftFrontDrive Position: ", leftFrontDrive.getCurrentPosition());
        telemetry.addData("leftFrontDrive Target: ", leftFrontDrive.getTargetPosition());
        telemetry.addData("leftFrontDrive Velocity: ", leftFrontDrive.getVelocity());
        telemetry.update();
    }

    public void setToFastTeleopPower() {
        currentPower = maxFastPower;
    }

    public void setToMediumTeleopPower(){currentPower = maxMediumPower;}

    public void setToSlowTeleopPower() {
        currentPower = maxSlowPower;
    }
}
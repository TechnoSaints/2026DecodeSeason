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
import com.qualcomm.robotcore.util.Range;

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
    }

    public void creepDirection(double axial, double strafe, double yaw) {
        moveDirection(axial * maxSlowPower, strafe * maxSlowPower, yaw * maxSlowPower);
    }

    protected void moveDirection(double axial, double strafe, double yaw) {
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

    private void log() {
        telemetry.addData("leftFrontDrive Position: ", leftFrontDrive.getCurrentPosition());
        telemetry.addData("leftFrontDrive Target: ", leftFrontDrive.getTargetPosition());
        telemetry.addData("leftFrontDrive Velocity: ", leftFrontDrive.getVelocity());
        telemetry.update();
    }
}
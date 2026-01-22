
package org.firstinspires.ftc.teamcode.opmode.teleop;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.util.ElapsedTime;


@TeleOp(name="Decode TeleOp", group="TeleOp")
public class DecodeTeleop extends LinearOpMode
{
    // Data for GoBilda 312 RPM motor
    final double maxMotorRPM = 6000;
    final double ticksPerMotorRev = 28;
    final double maxTicksPerSec = Math.round((maxMotorRPM * ticksPerMotorRev)/60.0f);
    double targetTicksPerSec = 0;
    int ticksPerSecondIncrement = 100;



    public void runOpMode() throws InterruptedException {
        // Init hardware
        DcMotor frontLeftMotor = hardwareMap.get(DcMotorEx.class, "frontLeftMotor");
        DcMotor frontRightMotor = hardwareMap.get(DcMotorEx.class, "frontRightMotor");
        DcMotor backLeftMotor = hardwareMap.get(DcMotorEx.class, "backLeftMotor");
        DcMotor backRightMotor = hardwareMap.get(DcMotorEx.class, "backRightMotor");
        DcMotor wheelMotor = hardwareMap.get(DcMotorEx.class, "wheelMotor");
        Servo leftServo = hardwareMap.get(Servo.class, "leftServo");
        Servo rightServo = hardwareMap.get(Servo.class, "rightServo");

        //Encoders
        // Setting encoders
        frontLeftMotor.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        frontRightMotor.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        backLeftMotor.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        backRightMotor.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        wheelMotor.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);

        ((DcMotorEx) frontLeftMotor).setVelocity(targetTicksPerSec);
        ((DcMotorEx) frontRightMotor).setVelocity(targetTicksPerSec);
        ((DcMotorEx) backLeftMotor).setVelocity(targetTicksPerSec);
        ((DcMotorEx) backRightMotor).setVelocity(targetTicksPerSec);
        ((DcMotorEx) wheelMotor).setVelocity(targetTicksPerSec);

        telemetry.addData("maxTicksPerSec: ", maxTicksPerSec);
        telemetry.update();
        waitForStart();

        frontLeftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        wheelMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        // Reversing back left motor
        backLeftMotor.setDirection(DcMotor.Direction.REVERSE);
        waitForStart();

        boolean isShooting = false;

        while (opModeIsActive())
        {
            // Read the inputs
            double drive = gamepad1.left_stick_y;
            double strafe = -gamepad1.left_stick_x;
            double turn = gamepad1.right_stick_x;
            double shoot = gamepad1.right_trigger;


            double speed = 0.8;

            if (gamepad1.left_bumper)
            {
                speed = 0.2;
            }
            if (gamepad1.left_trigger > 0.5)
            {
                speed = 1.5;
            }

            // Multiply by speed
            drive *= speed;
            strafe *= speed;
            turn *= speed;

            //Arcade drive math
            double leftFrontPower = drive + strafe + turn;
            double leftBackPower = drive - strafe + turn;
            double rightFrontPower = drive - strafe - turn;
            double rightBackPower = drive + strafe - turn;

            // Servos
            double leftServoPosition = 1;
            double rightServoPosition = 1;

            // No value exceeds 1.0
            double max = Math.max(
                    Math.max(Math.abs(leftFrontPower), Math.abs(rightFrontPower)),
                    Math.max(Math.abs(leftBackPower), Math.abs(rightBackPower))
            );

            if (max > 1.0)
            {
                leftFrontPower /= max;
                rightFrontPower /= max;
                leftBackPower /= max;
                rightBackPower /= max;
            }

            //Apply power to motors
            frontLeftMotor.setPower(leftFrontPower);
            frontRightMotor.setPower(rightFrontPower);
            backLeftMotor.setPower(leftBackPower);
            backRightMotor.setPower(rightBackPower);

            // Shooter Logic
            if (gamepad1.right_trigger > 0.5)
            {
                // Short shot
                isShooting = true;
                wheelMotor.setPower(0.65);
                leftServo.setPosition(0.0);
                rightServo.setPosition(0.4);
                // Revs up the motor and sets the servos to their right positions
            }
            if (gamepad1.y)
            {
                // Long shot
                isShooting = true;
                wheelMotor.setPower(0.80);
                leftServo.setPosition(0.0);
                rightServo.setPosition(0.4);
                // Also revs up motor and sets servos to correct position
            }
            if (gamepad1.right_bumper && isShooting)
            {
                // The fire button for both short and long shot
                leftServo.setPosition(0.4);
                rightServo.setPosition(0.0);
            }
            if (gamepad1.x)
            {
                // Turns off the motor
                wheelMotor.setPower(0);
                isShooting = false;
            }

            //Telemetry
            telemetry.addData("Left Front Power", "%.2f", leftFrontPower);
            telemetry.addData("Right Front Power", "%.2f", rightFrontPower);
            telemetry.addData("Back Left Motor","%.2f", leftBackPower);
            telemetry.addData("Back Right Motor","%.2f", rightBackPower);
            telemetry.addData("Shooter Power","%.2f", shoot);
            telemetry.addData("Left Servo Position", "%.2f", leftServoPosition);
            telemetry.addData("Right Servo Position", "%.2f", rightServoPosition);
            telemetry.update();
        }


    }

}





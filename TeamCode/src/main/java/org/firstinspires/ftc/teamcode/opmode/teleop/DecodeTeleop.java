package org.firstinspires.ftc.teamcode.opmode.teleop;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name="Decode TeleOp", group="TeleOp")
public class DecodeTeleop extends LinearOpMode
{

    public void runOpMode()
    {
        // Init hardware
        DcMotor frontLeftMotor = hardwareMap.get(DcMotor.class, "frontLeftMotor");
        DcMotor frontRightMotor = hardwareMap.get(DcMotor.class, "frontRightMotor");
        DcMotor backLeftMotor = hardwareMap.get(DcMotor.class, "backLeftMotor");
        DcMotor backRightMotor = hardwareMap.get(DcMotor.class, "backRightMotor");
        DcMotor wheelMotor = hardwareMap.get(DcMotor.class, "wheelMotor");
        Servo leftServo = hardwareMap.get(Servo.class, "leftServo");
        Servo rightServo = hardwareMap.get(Servo.class, "rightServo");

        frontLeftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        // Setting encoders
        frontLeftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontRightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backLeftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backRightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        wheelMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

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
            double leftServoPosition = 0;
            double rightServoPosition = 0;

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


            if (gamepad1.right_trigger > 0.5 && !isShooting)
            {
                isShooting = true;
                wheelMotor.setPower(0.67);
                leftServo.setPosition(1.0);
                rightServo.setPosition(1.0);
                leftServoPosition = 1.0;
                rightServoPosition = 1.0;
            }
            if (gamepad1.right_bumper)
            {
                wheelMotor.setPower(0.0);
                leftServo.setPosition(0.0);
                rightServo.setPosition(0.0);
                isShooting = false;
                rightServoPosition = 0.0;
                leftServoPosition = 0.0;
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


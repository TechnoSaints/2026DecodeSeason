package org.firstinspires.ftc.teamcode.opmode.auto;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.Servo;

@Autonomous(name = "AutoOneBallBack", group = "Autonomous")
public class BasicAuto extends LinearOpMode {

    private DcMotorEx leftFront, rightFront, leftBack, rightBack;
    private DcMotorEx launcherMotorL, launcherMotorR;
    private CRServo pusher;
    private Servo stick;

    private final double wheelDiameterInches = 4.0;
    private final double ticksPerRev = 537.6;
    private final double wheelCircumference = Math.PI * wheelDiameterInches;

    @Override
    public void runOpMode() {
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());

        // Drive motors
        leftFront = hardwareMap.get(DcMotorEx.class, "leftFrontDrive");
        leftBack = hardwareMap.get(DcMotorEx.class, "leftRearDrive");
        rightFront = hardwareMap.get(DcMotorEx.class, "rightFrontDrive");
        rightBack = hardwareMap.get(DcMotorEx.class, "rightRearDrive");

        // Launcher motors
        launcherMotorL = hardwareMap.get(DcMotorEx.class, "launcherMotorL");
        launcherMotorR = hardwareMap.get(DcMotorEx.class, "launcherMotorR");

        // Servos
        stick = hardwareMap.get(Servo.class, "stick");
        pusher = hardwareMap.get(CRServo.class, "pusher");

        // Set directions
        leftFront.setDirection(DcMotorEx.Direction.REVERSE);
        leftBack.setDirection(DcMotorEx.Direction.REVERSE);
        rightFront.setDirection(DcMotorEx.Direction.FORWARD);
        rightBack.setDirection(DcMotorEx.Direction.FORWARD);

        launcherMotorL.setDirection(DcMotorEx.Direction.FORWARD);
        launcherMotorR.setDirection(DcMotorEx.Direction.REVERSE);

        // Reset drive encoders
        resetDriveEncoders();

        waitForStart();

        if (opModeIsActive()) {

            // --- Step 1: Move backwards to line up with goal ---
            int backTicks = inchesToTicks(12); // move 12 inches backward
            leftFront.setTargetPosition(-backTicks);
            leftBack.setTargetPosition(-backTicks);
            rightFront.setTargetPosition(-backTicks);
            rightBack.setTargetPosition(-backTicks);

            setDriveRunToPosition();
            setDrivePower(0.5);

            while (opModeIsActive() &&
                    (leftFront.isBusy() || rightFront.isBusy() || leftBack.isBusy() || rightBack.isBusy())) {
                telemetry.addData("LF", leftFront.getCurrentPosition());
                telemetry.addData("RF", rightFront.getCurrentPosition());
                telemetry.update();
            }

            setDrivePower(0);
            sleep(200);

            // --- Step 2: Spin up launcher (short shot) ---
            int maxTicksPerSecond = 5376; // example for GoBilda 435
            double velocityFactor = 0.6; // short shot factor
            int targetVelocity = (int)(velocityFactor * maxTicksPerSecond);

            launcherMotorL.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
            launcherMotorR.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);

            launcherMotorL.setVelocity(targetVelocity);
            launcherMotorR.setVelocity(targetVelocity);
            sleep(1000); // wait for spin-up

            // --- Step 3: Launch one ball ---
            stick.setPosition(0.55); // launch position
            sleep(300);

            pusher.setPower(1.0); // push ball
            sleep(500);
            pusher.setPower(0);   // stop servo

            stick.setPosition(0.45); // reset stick

            // Stop launcher
            launcherMotorL.setPower(0);
            launcherMotorR.setPower(0);

            telemetry.addLine("Autonomous complete!");
            telemetry.update();
        }
    }

    private void resetDriveEncoders() {
        leftFront.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        rightFront.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        leftBack.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        rightBack.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);

        leftFront.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        rightFront.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        leftBack.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        rightBack.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
    }

    private void setDriveRunToPosition() {
        leftFront.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        rightFront.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        leftBack.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        rightBack.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
    }

    private void setDrivePower(double power) {
        leftFront.setPower(power);
        rightFront.setPower(power);
        leftBack.setPower(power);
        rightBack.setPower(power);
    }

    private int inchesToTicks(double inches) {
        return (int)((inches / wheelCircumference) * ticksPerRev);
    }
}

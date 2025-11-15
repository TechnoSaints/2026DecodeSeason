package org.firstinspires.ftc.teamcode.opmode.auto;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.Servo;

@Autonomous(name = "AutoThreeBall", group = "Autonomous")
public class BasicAuto extends LinearOpMode {

    private DcMotorEx leftFrontDrive, rightFrontDrive, leftBackDrive, rightBackDrive;
    private DcMotorEx launcherMotorL, launcherMotorR;
    private CRServo pusher;
    private Servo stick;

    private final double wheelDiameterInches = 4.09449;
    //ticksPerRev is declared here, should be later modified to pull from motor data
    private final double ticksPerRev = 537.6;
    private final double wheelCircumference = Math.PI * wheelDiameterInches;

    @Override
    public void runOpMode() {
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());

        //This is where our drive motors are declared
        leftFrontDrive = hardwareMap.get(DcMotorEx.class, "leftFrontDrive");
        leftBackDrive = hardwareMap.get(DcMotorEx.class, "leftRearDrive");
        rightFrontDrive = hardwareMap.get(DcMotorEx.class, "rightFrontDrive");
        rightBackDrive = hardwareMap.get(DcMotorEx.class, "rightRearDrive");

        //Launcher motors are declared
        launcherMotorL = hardwareMap.get(DcMotorEx.class, "launcherMotorL");
        launcherMotorR = hardwareMap.get(DcMotorEx.class, "launcherMotorR");

        //The Different Servos are Declared Here. Stick refers to the servo that pushes the ball into the launcher. Pusher refers to the black wheel.
        stick = hardwareMap.get(Servo.class, "stick");
        pusher = hardwareMap.get(CRServo.class, "pusher");

        //Setting directions
        leftFrontDrive.setDirection(DcMotorEx.Direction.REVERSE);
        leftBackDrive.setDirection(DcMotorEx.Direction.REVERSE);
        rightFrontDrive.setDirection(DcMotorEx.Direction.FORWARD);
        rightBackDrive.setDirection(DcMotorEx.Direction.FORWARD);

        launcherMotorL.setDirection(DcMotorEx.Direction.FORWARD);
        launcherMotorR.setDirection(DcMotorEx.Direction.REVERSE);

        //Reset drive encoders
        resetDriveEncoders();

        waitForStart();

        if (opModeIsActive()) {

            //Drive Up
            firstMovement();

            //Launch Position
            alignForLaunch();

            //Start Launcher
            startLauncher();

            //Launch Balls
            launchBalls();

            telemetry.addLine("Autonomous complete!");
            telemetry.update();
        }
    }


    //Function to reset all Drive Encoders
    private void resetDriveEncoders() {
        leftFrontDrive.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        rightFrontDrive.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        leftBackDrive.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        rightBackDrive.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);

        leftFrontDrive.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        rightFrontDrive.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        leftBackDrive.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        rightBackDrive.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
    }

    //Set's drivetrain to run
    private void setDriveRunToPosition() {
        leftFrontDrive.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        rightFrontDrive.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        leftBackDrive.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        rightBackDrive.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
    }

    //Set's same power to all wheels
    private void setDrivePower(double power) {
        leftFrontDrive.setPower(power);
        rightFrontDrive.setPower(power);
        leftBackDrive.setPower(power);
        rightBackDrive.setPower(power);
    }

    private int inchesToTicks(double inches) {
        return (int)((inches / wheelCircumference) * ticksPerRev);
    }

    private void firstMovement() {
        moveForward(100); //Arbitrary Number that Needs to be Changed
    }

    private void moveForward(int ticks) {
        leftFrontDrive.setTargetPosition(ticks);
        leftBackDrive.setTargetPosition(ticks);
        rightFrontDrive.setTargetPosition(ticks);
        rightBackDrive.setTargetPosition(ticks);
        setDriveRunToPosition();
        setDrivePower(0.5);

        while (opModeIsActive() && (leftFrontDrive.isBusy() || rightFrontDrive.isBusy() || leftBackDrive.isBusy() || rightBackDrive.isBusy())) {
            telemetry.addData("LF", leftFrontDrive.getCurrentPosition());
            telemetry.addData("RF", rightFrontDrive.getCurrentPosition());
            telemetry.update();
        }

        setDrivePower(0);
        sleep(200);
    }
/*
    private void intakeForward() {
        // Start the intake motors
    }

    private void intakeStop() {
        //Stop the intake motors
    }
*/
    //Align For Launch Function
    private void alignForLaunch() {
        //Move robot to optimal shooting position (adjust distance as necessary)
        int alignTicks = inchesToTicks(12); // Move forward for 12 inches to align
        moveForward(alignTicks);
    }

    private void startLauncher() {
        int maxTicksPerSecond = 5376; //Example needs to be changed
        double velocityFactor = 0.6; //Short shot factor
        int targetVelocity = (int)(velocityFactor * maxTicksPerSecond);

        launcherMotorL.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        launcherMotorR.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);

        launcherMotorL.setVelocity(targetVelocity);
        launcherMotorR.setVelocity(targetVelocity);
        sleep(1000); //Wait for launch motors to stop
    }

    // --- Step 4: Launch Balls ---
    private void launchBalls() {
        stick.setPosition(0.55); //Launch position
        sleep(300);

        pusher.setPower(1.0); //Push ball
        sleep(500);           //Wait for ball to be pushed
        pusher.setPower(0);   //Stop pusher

        // Repeat for all balls
        stick.setPosition(0.45); //Reset stick
    }
}

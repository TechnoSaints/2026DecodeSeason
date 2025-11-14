package org.firstinspires.ftc.teamcode.opmode.auto;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotor;

@Autonomous(name = "BasicAuto", group = "Linear OpMode")
public class BasicAuto extends LinearOpMode {

    private DcMotorEx leftFront, rightFront, leftBack, rightBack;
    private final double wheelDiameterInches = 4.0; // adjust for your bot
    private final double ticksPerRev = 537.6; // GoBilda 435 RPM, adjust if needed
    private final double wheelCircumference = Math.PI * wheelDiameterInches;

    @Override
    public void runOpMode() {
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());

        // Initialize motors
        leftFront = hardwareMap.get(DcMotorEx.class, "leftFrontDrive");
        rightFront = hardwareMap.get(DcMotorEx.class, "rightFrontDrive");
        leftBack = hardwareMap.get(DcMotorEx.class, "leftRearDrive");
        rightBack = hardwareMap.get(DcMotorEx.class, "rightRearDrive");

        // Motor directions
        leftFront.setDirection(DcMotor.Direction.REVERSE);
        leftBack.setDirection(DcMotor.Direction.REVERSE);
        rightFront.setDirection(DcMotor.Direction.FORWARD);
        rightBack.setDirection(DcMotor.Direction.FORWARD);

        // Reset encoders
        leftFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        leftFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        // Wait for start
        waitForStart();

        if (opModeIsActive()) {

            // Example: drive forward ~48 inches
            int ticks = (int)((48 / wheelCircumference) * ticksPerRev);

            leftFront.setTargetPosition(ticks);
            rightFront.setTargetPosition(ticks);
            leftBack.setTargetPosition(ticks);
            rightBack.setTargetPosition(ticks);

            // Set power
            leftFront.setPower(0.6);
            rightFront.setPower(0.6);
            leftBack.setPower(0.6);
            rightBack.setPower(0.6);

            // Wait until motors reach target
            while (opModeIsActive() &&
                    (leftFront.isBusy() || rightFront.isBusy() || leftBack.isBusy() || rightBack.isBusy())) {
                telemetry.addData("LF", leftFront.getCurrentPosition());
                telemetry.addData("RF", rightFront.getCurrentPosition());
                telemetry.addData("LB", leftBack.getCurrentPosition());
                telemetry.addData("RB", rightBack.getCurrentPosition());
                telemetry.update();
            }

            // Stop all motors
            leftFront.setPower(0);
            rightFront.setPower(0);
            leftBack.setPower(0);
            rightBack.setPower(0);

            telemetry.addLine("Move complete!");
            telemetry.update();
            sleep(500);
        }
    }
}

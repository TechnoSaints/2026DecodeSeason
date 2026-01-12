package org.firstinspires.ftc.teamcode.opmode.test;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;

import org.firstinspires.ftc.teamcode.common.hardwareConfiguration.data.GoBilda6000DcMotorData;
/*
* Tuned Coefficients:
* F: 12.29
* P: */

@TeleOp(name = "PIDF Test", group = "1PID")
public class PIDFTest extends LinearOpMode {
    DcMotorEx leftLauncher, rightLauncher;
    double highVelocity = 2500;
    double lowVelocity = 1800;
    double curTargetVelocity = highVelocity;
    double F = 0;
    double P = 0;
    double[] stepSizes = {10, 1, 0.1, 0.01, 0.001, 0.0001};
    int stepIndex = 1;
    public void runOpMode(){
        leftLauncher = hardwareMap.get(DcMotorEx.class, "leftLauncher");
        rightLauncher = hardwareMap.get(DcMotorEx.class, "rightLauncher");
        leftLauncher.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightLauncher.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        PIDFCoefficients pidfCoefficients = new PIDFCoefficients(P, 0, 0, F);
        leftLauncher.setPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER, pidfCoefficients);
        rightLauncher.setPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER, pidfCoefficients);
        telemetry.addLine("init complete");
        telemetry.update();
        waitForStart();
        while (opModeIsActive() && !isStopRequested()){
            if (gamepad1.yWasPressed()){
                if (curTargetVelocity == highVelocity){
                    curTargetVelocity = lowVelocity;
                }
                else {
                    curTargetVelocity = highVelocity;
                }
            }

            if (gamepad1.bWasPressed()){
                stepIndex = (stepIndex + 1) % stepSizes.length;
            }

            if (gamepad1.dpadLeftWasPressed()){
                F -= stepSizes[stepIndex];
            }
            if (gamepad1.dpadRightWasPressed()){
                F += stepSizes[stepIndex];
            }
            if (gamepad1.dpadUpWasPressed()){
                P += stepSizes[stepIndex];
            }
            if (gamepad1.dpadDownWasPressed()){
                P -= stepSizes[stepIndex];
            }
            pidfCoefficients = new PIDFCoefficients(P, 0, 0, F);
            leftLauncher.setPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER, pidfCoefficients);
            rightLauncher.setPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER, pidfCoefficients);

            leftLauncher.setVelocity(curTargetVelocity);
            rightLauncher.setVelocity(curTargetVelocity);
            double curLeftVelocity = leftLauncher.getVelocity();
            double leftError = curTargetVelocity - curLeftVelocity;
            double curRightVelocity = rightLauncher.getVelocity();
            double rightError = -curTargetVelocity - curRightVelocity;

            telemetry.addData("Target Velocity", curTargetVelocity);
            telemetry.addData("Left Current Velocity", curLeftVelocity);
            telemetry.addData("Left Error", "%.2f", leftError);
            telemetry.addData("Right Current Velocity", curRightVelocity);
            telemetry.addData("Right Error", "%.2f", rightError);
            telemetry.addLine("---------------------------------------");
            telemetry.addData("Tuning P", "%.4f (D-Pad U/D", P);
            telemetry.addData("Tuning F", "%.4f (D-Pad L/R", F);
            telemetry.addData("Step Size", "%.4f (B Button", stepSizes[stepIndex]);
            telemetry.update();
        }
    }
}

package org.firstinspires.ftc.teamcode.opmode.test;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;

import org.firstinspires.ftc.teamcode.common.Launcher;
import org.firstinspires.ftc.teamcode.common.hardwareConfiguration.data.GoBilda6000DcMotorData;
import org.firstinspires.ftc.teamcode.common.hardwareConfiguration.setPoints.LauncherSettings;
/*
* Tuned Coefficients:
* F: 12.29
* P: */

@TeleOp(name = "PIDF Test", group = "1PID")
public class PIDFTest extends LinearOpMode {
    Launcher launcher;
    double highVelocity = LauncherSettings.longShotVelocityFactor;
    double lowVelocity = LauncherSettings.shortShotVelocityFactor;
    double curTargetVelocity = highVelocity;
    double F = 0;
    double P = 0;
    double I = 0;
    double D = 0;
    double[] stepSizes = {10, 1, 0.1, 0.01, 0.001, 0.0001};
    int stepIndex = 1;
    public void runOpMode(){
        launcher = new Launcher(telemetry, hardwareMap);
        PIDFCoefficients pidfCoefficients = new PIDFCoefficients(P, I, D, F);
        launcher.setPIDFCoefficients(pidfCoefficients);
        telemetry.addLine("init complete");
        telemetry.update();
        waitForStart();
        while (opModeIsActive() && !isStopRequested()){
            if (gamepad1.shareWasPressed()){
                if (curTargetVelocity == highVelocity){
                    curTargetVelocity = lowVelocity;
                }
                else {
                    curTargetVelocity = highVelocity;
                }
            }

            if (gamepad1.optionsWasPressed()){
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
            if (gamepad1.crossWasPressed()){
                I -= stepSizes[stepIndex];
            }
            if (gamepad1.triangleWasPressed()){
                I += stepSizes[stepIndex];
            }
            if (gamepad1.circleWasPressed()){
                D += stepSizes[stepIndex];
            }
            if (gamepad1.squareWasPressed()){
                D -= stepSizes[stepIndex];
            }
            pidfCoefficients = new PIDFCoefficients(P, I, D, F);
            launcher.setPIDFCoefficients(pidfCoefficients);
            launcher.setVelocity(curTargetVelocity);
            launcher.log();

            telemetry.addData("Target Velocity", curTargetVelocity);
            telemetry.addLine("---------------------------------------");
            telemetry.addData("Tuning P", "%.4f (D-Pad U/D)", P);
            telemetry.addData("Tuning I", "%.4f (D-Pad U/D)", I);
            telemetry.addData("Tuning D", "%.4f (Y/A", D);
            telemetry.addData("Tuning F", "%.4f (B/X", F);
            telemetry.addData("Step Size", "%.4f (PS", stepSizes[stepIndex]);
            telemetry.update();
        }
    }
}

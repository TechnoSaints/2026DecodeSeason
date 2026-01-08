package org.firstinspires.ftc.teamcode.opmode.tuning;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.opMode;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.common.Bot;
import org.firstinspires.ftc.teamcode.common.LauncherDouble;
import org.firstinspires.ftc.teamcode.common.ServoSimple;

@Config
@TeleOp(name = "LauncherDoubleTuner", group = "Tuning")

public class LauncherDoubleTuner extends LinearOpMode {

    private LauncherDouble launcher;

    private ServoSimple stick;

    private double kickerLaunchPosition = 0.55;

    private double velocityFactorIncrement = 0.025;
    private double targetVelocityFactor = 0.0;
    private double kickerLoadPosition = 0.35;
    private double positionIncrement = 0.05;
    private double targetLaunchPosition = 0.5;

    @Override
    public void runOpMode() {
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
        stick = new ServoSimple(hardwareMap, telemetry,"stick");
        launcher = new LauncherDouble(hardwareMap, telemetry);
        launcher.setVelocityFactor(targetVelocityFactor);
        launcher.setLaunchPosition(targetLaunchPosition);
        waitForStart();

        while (opModeIsActive() && !isStopRequested()) {
            if (gamepad1.y) {
                targetVelocityFactor += velocityFactorIncrement;
            } else if (gamepad1.a) {
                targetVelocityFactor -= velocityFactorIncrement;
            }
            if (gamepad1.b) {
                targetLaunchPosition += positionIncrement;
            } else if (gamepad1.x) {
                targetLaunchPosition -= positionIncrement;
            }

            if (gamepad1.left_bumper) {
                stick.setPositionTicks(kickerLaunchPosition);
            }


            if (gamepad1.right_bumper) {
                stick.setPositionTicks(kickerLoadPosition);
            }



            launcher.setVelocityFactor(-targetVelocityFactor);
            launcher.setLaunchPosition(targetLaunchPosition);
            telemetry.addData("targetVelocityFactor in launcherDoubleTest: ", targetVelocityFactor);
            telemetry.addData("targetLaunchPosition in launcherDoubleTest: ", targetLaunchPosition);
            launcher.log();
            sleep(100);
        }
    }
}

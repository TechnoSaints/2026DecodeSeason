package org.firstinspires.ftc.teamcode.opmode.tuning;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.common.Launcher;
import org.firstinspires.ftc.teamcode.common.LauncherDouble;
import org.firstinspires.ftc.teamcode.common.hardwareConfiguration.data.GoBilda6000DcMotorData;
import org.firstinspires.ftc.teamcode.common.hardwareConfiguration.data.MotorData;
import org.firstinspires.ftc.teamcode.opmode.test.LauncherTest;

@Config
@TeleOp(name = "LauncherDoubleTuner", group = "Tuning")

public class LauncherDoubleTuner extends LinearOpMode {

    private Launcher launcher;
    private double velocityFactorIncrement = 0.01;
    private double targetVelocityFactor = 0.0;
    private MotorData motorData = new GoBilda6000DcMotorData();
    private final int maxTicksPerSecond = motorData.maxTicksPerSec;

    private double positionIncrement = 0.05;
    private double targetLaunchPosition = 0.5;

    @Override
    public void runOpMode() {
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
        launcher = new Launcher(telemetry, hardwareMap);
        launcher.setVelocity(targetVelocityFactor);
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

            launcher.setVelocity(targetVelocityFactor);
            telemetry.addData("targetVelocityFactor in launcherDoubleTest: ", targetVelocityFactor);
            telemetry.addData("targetLaunchPosition in launcherDoubleTest: ", targetLaunchPosition);
            launcher.log();
            telemetry.update();
            sleep(100);
        }
    }
}

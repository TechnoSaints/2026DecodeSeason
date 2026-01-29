package org.firstinspires.ftc.teamcode.opmode.tuning;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.common.LauncherDouble;
import org.firstinspires.ftc.teamcode.common.hardwareConfiguration.data.GoBilda6000DcMotorData;
import org.firstinspires.ftc.teamcode.common.hardwareConfiguration.data.MotorData;

@Config
@Disabled
@TeleOp(name = "LauncherDoubleTuner", group = "Tuning")

public class LauncherDoubleTuner extends LinearOpMode {

    private LauncherDouble launcher;
    private double velocityFactorIncrement = 0.01;
    private double targetVelocityFactor = 0.0;
    private MotorData motorData = new GoBilda6000DcMotorData();
    private final int maxTicksPerSecond = motorData.maxTicksPerSec;

    private double positionIncrement = 0.05;
    private double targetLaunchPosition = 0.5;

    @Override
    public void runOpMode() {
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
        launcher = new LauncherDouble(hardwareMap, telemetry);
        launcher.setVelocityFactor(targetVelocityFactor);
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

            launcher.setVelocityFactor(targetVelocityFactor);
            telemetry.addData("targetVelocityFactor in launcherDoubleTest: ", targetVelocityFactor);
            telemetry.addData("targetLaunchPosition in launcherDoubleTest: ", targetLaunchPosition);
            launcher.log();
            sleep(100);
        }
    }
}

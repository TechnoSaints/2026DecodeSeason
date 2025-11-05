package org.firstinspires.ftc.teamcode.opmode.test;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.common.LauncherDouble;
import org.firstinspires.ftc.teamcode.common.hardwareConfiguration.data.GoBilda6000DcMotorData;

@Config
@TeleOp(name = "LauncherDoubleTest", group = "Test")

public class LauncherDoubleTest extends LinearOpMode {

    private LauncherDouble shooter;
    private double velocityFactorIncrement = 0.1;
    private double targetVelocityFactor = 0.0;

    @Override
    public void runOpMode() {
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
        shooter = new LauncherDouble(hardwareMap, telemetry, "shooterL", "shooterR", new GoBilda6000DcMotorData());
        shooter.setVelocityFactorVoltageCompensated(targetVelocityFactor);

        waitForStart();
        while (opModeIsActive() && !isStopRequested()) {
            if (gamepad1.y) {
                targetVelocityFactor += velocityFactorIncrement;
            } else if (gamepad1.a) {
                targetVelocityFactor -= velocityFactorIncrement;
            }
            shooter.setVelocityFactorVoltageCompensated(targetVelocityFactor);
            telemetry.addData("targetVelocityFactor in ShooterDoubleTest: ", targetVelocityFactor);
            shooter.log();
            sleep(100);
        }
    }
}

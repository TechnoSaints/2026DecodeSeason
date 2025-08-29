package org.firstinspires.ftc.teamcode.opmode.teleop;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.common.Modes;
import org.firstinspires.ftc.teamcode.common.TeleopBot;
import org.firstinspires.ftc.teamcode.common.TeleopBotSimple;

@Config
@TeleOp(name = "Teleop - No Odo Simple", group = "Linear OpMode")
public class TeleopNoOdoSimple extends LinearOpMode {
    private TeleopBotSimple bot;

    @Override
    public void runOpMode() {
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
        bot = new TeleopBotSimple(this, telemetry);

        waitForStart();
        while (opModeIsActive() && !isStopRequested()) {
            bot.processDrivetrainInput(gamepad1);
        }
    }
}

package org.firstinspires.ftc.teamcode.opmode.teleop;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.common.Modes;
import org.firstinspires.ftc.teamcode.common.TeleopBot;

@Config
@TeleOp(name = "Teleop - No Odo", group = "Linear OpMode")
public class TeleopNoOdo extends LinearOpMode {
    private TeleopBot bot;

    @Override
    public void runOpMode() {
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
        bot = new TeleopBot(this, telemetry);

        waitForStart();
        bot.setMode(Modes.TELEOP_START_POS);
        while (opModeIsActive() && !isStopRequested()) {
            bot.processSpecimenInput(gamepad1);
            bot.update();
        }
    }
}

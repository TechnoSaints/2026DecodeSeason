package org.firstinspires.ftc.teamcode.opmode.tuning;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.common.Modes;
import org.firstinspires.ftc.teamcode.common.TeleopBot;

@Config
@TeleOp(name = "BrickSweepTuner", group = "Tuning")
@Disabled

public class BrickSweepTuner extends LinearOpMode {

    private TeleopBot bot;
    private ElapsedTime timer = new ElapsedTime(ElapsedTime.Resolution.MILLISECONDS);

    @Override
    public void runOpMode() {
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
        bot = new TeleopBot(this, telemetry);

        bot.setMode(Modes.AUTO_START_POS);
        while (!opModeIsActive()) {
            bot.update();
        }
        waitForStart();
//        bot.setMode(Modes.INTAKE_BRICK);
        while (opModeIsActive() && !isStopRequested()) {
            if (gamepad1.right_bumper) {
                bot.setMode(Modes.HANDLER_HIGH_SPECIMEN_POS);
            } else if (gamepad1.left_bumper) {
                bot.setMode(Modes.HANG_SPECIMEN);
            }
            bot.update();
        }
    }
}

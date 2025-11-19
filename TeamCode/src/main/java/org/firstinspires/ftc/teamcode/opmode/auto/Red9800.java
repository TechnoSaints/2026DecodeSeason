package org.firstinspires.ftc.teamcode.opmode.auto;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.common.TeleopBot;

    @Config
    @Autonomous(name = "Red9800", group = "Linear OpMode")
    public class Red9800 extends LinearOpMode {
        private TeleopBot bot;

        @Override
        public void runOpMode() {
            telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
            bot = new TeleopBot(this, telemetry);

            waitForStart();
            while (opModeIsActive() && !isStopRequested()) {
                bot.move(-0.5,0,0);
                sleep(1000);
                bot.move(0,0,0);
                bot.move(0,0.5,0);
                sleep(1500);
                bot.move(0,0,0);
         /*       bot.move(-0.5,0,0);
                sleep(1000);
                bot.move(0,0,0);
                bot.setLauncherShortShot();
                sleep(1000);
                bot.kickerLoad();
                sleep(2000);
                bot.move(0,0.5,0);
                sleep(750);
                bot.move(0,0,0); */


                bot.update();
            }}}

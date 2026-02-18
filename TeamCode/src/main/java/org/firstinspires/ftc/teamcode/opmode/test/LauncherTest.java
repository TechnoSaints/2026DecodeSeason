package org.firstinspires.ftc.teamcode.opmode.test;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.common.AutoBot;
import org.firstinspires.ftc.teamcode.common.Bot;

@TeleOp(name = "Test Launcher", group = "Test")
public class LauncherTest extends LinearOpMode
{
    private Bot bot;
    public void runOpMode() throws InterruptedException {
        bot = new AutoBot(this, telemetry);
        bot.setLauncherSpeed(-1);
        waitForStart();
        while (opModeIsActive()){
            bot.setLauncherSpeed(-gamepad1.right_trigger);
        }
    }
}

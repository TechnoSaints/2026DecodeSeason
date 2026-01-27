package org.firstinspires.ftc.teamcode.opmode.test;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.common.AutoBot;

@Autonomous(name = "Launcher Auto Test", group = "Test")
public class LauncherAutoTest extends OpMode {
    AutoBot bot;
    @Override
    public void init(){
        bot = new AutoBot(this, telemetry);
    }

    int pathState = 0;
    @Override
    public void loop(){
        switch (pathState){
            case 0:
                bot.setLauncherSpeed(-1);
                pathState = 1;
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                break;
            case 1:
                bot.setLauncherSpeed(-0.85);
                pathState = -1;
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                break;
            case -1:
                requestOpModeStop();
                break;
        }
    }
}

package org.firstinspires.ftc.teamcode.opmode.auto;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.common.TeleopBot;

@Config
@Autonomous(name="Blue Base 3x + Park")
public class BaseBlue3xAndPark extends OpMode {
    private TeleopBot bot;
    private boolean isStopRequested;
    private int autoState = 0;

    @Override
    public void init() {
        isStopRequested = false;
        bot = new TeleopBot(this, telemetry);
    }

    @Override
    public void loop(){
        switch (autoState){
            case 0:
                bot.updateLauncher(true, true);
                bot.moveForwardForDistance(-4);
                autoState = 1;
                break;
            case 1:
                bot.strafeRightForDistance(-5);
                autoState = 2;
                break;
            case 2:
                bot.turnToHeading(23);
                autoState = 3;
                break;
            case 3:
                if (bot.getBalls()[0] == 'O' && bot.getBalls()[1] == 'O' && bot.getBalls()[2] == 'O' && bot.getState() == 0){
                    autoState = 4;
                }
                else if (bot.getState() == 0){
                    bot.shootBalls();
                }
                break;
        }
        bot.updateLauncher(true, false);
        bot.teleopUpdate(true);
        bot.odoUpdate();

    }

    @Override
    public void stop(){
        isStopRequested = true;
    }

    public boolean isStopRequested(){
        return isStopRequested;
    }
}

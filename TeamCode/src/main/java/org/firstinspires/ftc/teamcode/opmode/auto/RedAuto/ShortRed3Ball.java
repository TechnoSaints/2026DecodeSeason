package org.firstinspires.ftc.teamcode.opmode.auto.RedAuto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.opmode.FieldLocations;
import org.firstinspires.ftc.teamcode.opmode.Paths;
import org.firstinspires.ftc.teamcode.opmode.auto.AutoOpMode;

@Autonomous(name = "\uD83D\uDD34Short Red 3 Ball", group = "Red")
public class ShortRed3Ball extends AutoOpMode {

    @Override
    public void init() {
        FieldLocations.buildPoses("red", "short");
        super.init();
    }

    protected void autonomousPathUpdate() {
        bot.pusherUpdate();
        switch (pathState) {

            //Move From Start To Short Shot

            case 0:
                bot.setLauncherShortShot();
                bot.stickLoad();
                bot.followPath(Paths.startToShortShot, 1f, true);
                setPathState(1);
                break;

            //Shooting The First Shot
            case 1:
                if (!bot.followerIsBusy()) {
                    controlTimer.reset();
                    setPathState(2);
                }
                break;

            //Turning on Black Wheel and Resetting Stick
            case 2:
                if (controlTimer.milliseconds() > 500) {
                    bot.stickLaunchLoad();
                    controlTimer.reset();
                    setPathState(3);
                }
                break;

            //Clearing Jam
            case 3:
                if (controlTimer.milliseconds() > 150) {
                    controlTimer.reset();
                    setPathState(4);
                }
                break;

            //Shooting Second Ball
            case 4:
                if (controlTimer.milliseconds() > 1250) {
                    bot.stickLaunchLoad();
                    controlTimer.reset();
                    setPathState(5);
                }
                break;

            //Resetting Stick
            case 5:
                if (controlTimer.milliseconds() > 1) {
                    controlTimer.reset();
                    setPathState(6);
                }
                break;

            //Shooting Third Ball
            case 6:
                if (controlTimer.milliseconds() > 1250) {
                    bot.stickLaunchLoad();
                    controlTimer.reset();
                    setPathState(7);
                }
                break;

                // move to shortshot to stack 1 setup
            case 7:
                bot.followPath(Paths.shortShotToStack2Setup, 1f, true);
                setPathState(33);
                break;

            //Stop Opmode
            case 8:
                if (!bot.followerIsBusy()) {
                    setPathState(-1);
                    requestOpModeStop();
                }
                break;
        }
    }
}


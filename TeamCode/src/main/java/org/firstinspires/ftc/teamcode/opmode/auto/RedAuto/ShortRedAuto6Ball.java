package org.firstinspires.ftc.teamcode.opmode.auto.RedAuto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.opmode.FieldLocations;
import org.firstinspires.ftc.teamcode.opmode.Paths;
import org.firstinspires.ftc.teamcode.opmode.auto.AutoOpMode;

@Autonomous(name = "\uD83D\uDD34Short Red Auto 6 Ball", group = "Red")
public class ShortRedAuto6Ball extends AutoOpMode {

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
                bot.followPath(Paths.startToShortShot, 0.7f, true);
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
                if (controlTimer.milliseconds() > 250) {
                    bot.stickLaunchLoad();
                    controlTimer.reset();
                    setPathState(3);
                }
                break;

                //Clearing Jam
            case 3:
                if (controlTimer.milliseconds() > 750) {
                    controlTimer.reset();
                    setPathState(4);
                }
                break;

                //Shooting Second Ball
            case 4:
                if (controlTimer.milliseconds() > 500) {
                    bot.stickLaunchLoad();
                    controlTimer.reset();
                    setPathState(5);
                }
                break;

                //Resetting Stick
            case 5:
                if (controlTimer.milliseconds() > 250) {
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

                //Move to Stack 1 Setup and Clear Launcher
            case 7:
                if (controlTimer.milliseconds() > 250) {
                    bot.stickLaunchLoad(); //Just In Case A Extra Ball Was There
                    bot.followPath(Paths.shortShotToStack1Setup, 0.8f, true);
                    bot.intakeForward();
                    setPathState(8);
                }
                break;

                //Turn on Intake and Move to Stack 1 Finish after Stack 1 Setup Move is Finished
            case 8:
                if (!bot.followerIsBusy()) {
                    bot.followPath(Paths.stack1SetupToStack1Finish, 0.6f, true);
                    controlTimer.reset();
                    setPathState(9);
                }
                break;

                //Pause To Left the Intake Finish
            case 9:
                if (controlTimer.milliseconds() > 3000) {
                    controlTimer.reset();
                    setPathState(10);
                }
                break;

                //Returning To Shooting Position
            case 10:
                bot.followPath(Paths.stack1FinishToShortShot, 0.8f, true);
                setPathState(11);
                break;

                //Clear Jam
            case 11:
                if (!bot.followerIsBusy()) {
                    controlTimer.reset();
                   // bot.pusherReverse();
                    setPathState(12);
                    }
                break;

                //Launch 4th Ball
            case 12:
                if (controlTimer.milliseconds() > 750) {
                    controlTimer.reset();
                    bot.stickLaunchLoad();
                  //  ();
                    setPathState(13);
                }
                break;

                //Reset Stick and Start Pusher
            case 13:
                if (controlTimer.milliseconds() > 500) {
                    controlTimer.reset();
                    setPathState(14);
                }
                break;

                //Clear The Jam
            case 14:
                if (controlTimer.milliseconds() > 1250) {
                    controlTimer.reset();
                    setPathState(15);
                }
                break;

                //Launch 5th Ball
            case 15:
                if (controlTimer.milliseconds() > 750) {
                    bot.stickLaunchLoad();
                    controlTimer.reset();
                    setPathState(16);
                }
                break;

                //Reset Stick
            case 16:
                if (controlTimer.milliseconds() > 250) {
                    controlTimer.reset();
                    setPathState(17);
                }
                break;

                //Launch 6th Ball and Travel for Move Bonus
            case 17:
                if (controlTimer.milliseconds() > 250) {
                    controlTimer.reset();
                    setPathState(18);
                }
                break;

            case 18:
                if (controlTimer.milliseconds() > 1500) {
                    bot.stickLaunchLoad();
                    controlTimer.reset();
                    setPathState(19);
                }
                break;

            case 19:
                if (controlTimer.milliseconds() > 250) {
                    controlTimer.reset();
                    bot.followPath(Paths.shortShotToStack2Setup, 0.8f, true);
                    setPathState(19);
                }
                break;

            //Stop Opmode
            case 20:
                if (!bot.followerIsBusy()) {
                    setPathState(-1);
                    requestOpModeStop();
                }
                break;
        }
    }
}


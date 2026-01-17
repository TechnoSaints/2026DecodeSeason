package org.firstinspires.ftc.teamcode.opmode.auto.BlueAuto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.opmode.FieldLocations;
import org.firstinspires.ftc.teamcode.opmode.Paths;
import org.firstinspires.ftc.teamcode.opmode.auto.AutoOpMode;

@Autonomous(name = "\uD83D\uDD34shortBlueAuto6Ball", group = "Blue")
public class ShortBlueAuto6Ball extends AutoOpMode {

    @Override
    public void init() {
        FieldLocations.buildPoses("blue", "short");
        super.init();
    }

    protected void autonomousPathUpdate() {
        switch (pathState) {

                //Move From Start To Short Shot

            case 0:
                bot.setLauncherShortShot();
                bot.stickLoad();
                bot.stopPusher();
                bot.followPath(Paths.startToShortShot, 0.7f, true);
                controlTimer.reset();
                setPathState(1);
                break;

                //Shooting The First Shot
            case 1:
                if (!bot.followerIsBusy()) {
                    if(controlTimer.milliseconds() > 750) {
                        bot.stickLaunch();
                        controlTimer.reset();
                        setPathState(2);
                    }
                }
                break;

                //Turning on Black Wheel and Resetting Stick
            case 2:
                if (controlTimer.milliseconds() > 250) {
                    controlTimer.reset();
                    bot.stickLoad();
                    bot.pusherStart();
                    setPathState(3);
                }
                break;

                //Launching Second Ball
            case 3:
                if (controlTimer.milliseconds() > 750) {
                    controlTimer.reset();
                    bot.pusherReverse();
                    bot.stickLaunch();
                    setPathState(4);
                }
                break;

                //Resetting Stick and Turning On Black Wheel
            case 4:
                if (controlTimer.milliseconds() > 250) {
                    controlTimer.reset();
                    bot.stickLoad();
                    bot.pusherStart();
                    setPathState(5);
                }
                break;


                //Launching Third Ball
            case 5:
                if (controlTimer.milliseconds() > 2000) {
                    bot.stickLaunch();
                    controlTimer.reset();
                    setPathState(6);
                }
                break;

                //Resetting Stick
            case 6:
                if (controlTimer.milliseconds() > 1000) {
                    bot.stickLoad();
                    controlTimer.reset();
                    setPathState(7);
                }
                break;


                //Move to Stack 1 Setup and Clear Launcher
            case 7:
                if (controlTimer.milliseconds() > 250) {
                    bot.stickLaunch();
                    bot.intakeForward();
                    bot.followPath(Paths.shortShotToStack1Setup, 0.7f, false);
                    setPathState(8);
                }
                break;

                //Turn on Intake and Move to Stack 1 Finish after Stack 1 Setup Move is Finished
            case 8:
                if (!bot.followerIsBusy()) {
                    bot.stickLoad();
                    bot.pusherStart();
                    bot.followPath(Paths.stack1SetupToStack1Finish, 0.6f, false);
                    controlTimer.reset();
                    setPathState(9);
                }
                break;

                //Stop The Intake Reverse the Pusher to Clear Jam
            case 9:
                if (controlTimer.milliseconds() > 4000) {
                    controlTimer.reset();
                    setPathState(10);
                }
                break;

            case 10:
                bot.followPath(Paths.stack1FinishToShortShot, 0.6f, false);
                controlTimer.reset();
                setPathState(11);
                break;

                //Launch the 4th Ball and Stop Pusher
            case 11:
                if (!bot.followerIsBusy()) {
                    if(controlTimer.milliseconds() > 2000) {
                        controlTimer.reset();
                        bot.stickLoad();
                        bot.pusherReverse();
                        setPathState(12);
                    }
                }
                break;

                //Turn on Intake In the Case Ball Left and Reset Stick
            case 12:
                if (controlTimer.milliseconds() > 1200) {
                    controlTimer.reset();
                    bot.stickLaunch();
                    bot.intakeForward();
                    bot.pusherStart();
                    setPathState(13);
                }
                break;

                //Reverse Pusher to Clear Jam and Stop Intake
            case 13:
                if (controlTimer.milliseconds() > 250) {
                    controlTimer.reset();
                    bot.pusherStart();
                    bot.stickLoad();
                    setPathState(14);
                }
                break;

                //Shoot 5th Ball, Start the Pusher, and Turn On Intake in Case Ball Left
            case 14:
                if (controlTimer.milliseconds() > 1250) {
                    controlTimer.reset();
                    bot.pusherReverse();
                    bot.intakeForward();
                    setPathState(15);
                }
                break;

                //Resetting the Stick, Reverse the Pusher to Clear Jam, and Turn Off Intake
            case 15:
                if (controlTimer.milliseconds() > 1000) {
                    bot.stickLaunch();
                    bot.pusherStart();
                    bot.intakeForward();
                    controlTimer.reset();
                    setPathState(16);
                }
                break;

                //Launch The 6th Ball and Start the Pusher and Intake in Case of Leftover Ball
            case 16:
                if (controlTimer.milliseconds() > 3000) {
                    bot.stickLaunch();
                    controlTimer.reset();
                    setPathState(17);
                }
                break;
/*
                //Clear Jam Just in Case and Resetting the Stick
            case 17:
                if (controlTimer.milliseconds() > 750) {
                    bot.stickLoad();
                    bot.pusherReverse();
                    controlTimer.reset();
                    setPathState(18);
                }
                break;

                //Launch a Leftover Ball and Travel for Move Bonus
            case 18:
                if (controlTimer.milliseconds() > 250) {
                    bot.stickLaunch();
                    controlTimer.reset();
                    bot.followPath(Paths.shortShotToStack1Setup, false);
                    setPathState(19);
                }
                break; */

                //Stop Opmode
            case 17:
                if (!bot.followerIsBusy()) {
                    setPathState(-1);
                    requestOpModeStop();
                }
                break;
        }
    }
}


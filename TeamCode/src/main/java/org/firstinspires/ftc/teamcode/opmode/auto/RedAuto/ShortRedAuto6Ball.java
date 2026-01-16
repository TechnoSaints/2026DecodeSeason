package org.firstinspires.ftc.teamcode.opmode.auto.RedAuto;

import static android.os.SystemClock.sleep;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.opmode.FieldLocations;
import org.firstinspires.ftc.teamcode.opmode.Paths;
import org.firstinspires.ftc.teamcode.opmode.auto.AutoOpMode;

@Autonomous(name = "\uD83D\uDD34shortRedAuto6Ball", group = "Red")
public class ShortRedAuto6Ball extends AutoOpMode {

    @Override
    public void init() {
        FieldLocations.buildPoses("red", "short");
        super.init();
    }

    public void shootBalls() {
        // Make sure everything is default positions
        bot.stickLoad();
        bot.stopPusher();

        // launch first ball
        bot.stickLaunch();
        sleep(100);
        bot.stickLoad();

        // launch 2nd ball
        bot.pusherStart();
        sleep(750);
        bot.stopPusher();
        bot.stickLaunch();
        sleep(100);
        bot.stickLoad();

        // launch 3rd ball
        sleep(100);
        bot.pusherStart();
        sleep(750);
        bot.stickLaunch();
        sleep(100);
        bot.stickLoad();
    }

    protected void autonomousPathUpdate() {
        switch (pathState) {

                //Move From Start To Short Shot

            case 0:
                bot.setLauncherShortShot();
                bot.stickLoad();
                bot.stopPusher();
                bot.followPath(Paths.startToShortShot, false);
                setPathState(1);
                break;

                //Shooting The First Shot
            case 1:
                if (!bot.followerIsBusy()) {
                    controlTimer.reset();
                    bot.stickLaunch();
                    setPathState(2);
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
                if (controlTimer.milliseconds() > 750) {
                    controlTimer.reset();
                    bot.stickLaunch();
                    setPathState(6);
                }
                break;

                //Resetting Stick
            case 6:
                if (controlTimer.milliseconds() > 250) {
                    controlTimer.reset();
                    bot.stickLoad();
                }
                break;

                //Move to Stack 1 Setup and Clear Launcher
            case 7:
                if (controlTimer.milliseconds() > 250) {
                    bot.stickLaunch();
                    bot.followPath(Paths.shortShotToStack1Setup, false);
                    setPathState(8);
                }
                break;

                //Turn on Intake and Move to Stack 1 Finish after Stack 1 Setup Move is Finished
            case 8:
                if (!bot.followerIsBusy()) {
                    bot.stickLoad();
                    bot.intakeForward();
                    bot.pusherStart();
                    bot.followPath(Paths.stack1SetupToStack1Finish, false);
                    controlTimer.reset();
                    setPathState(9);
                }
                break;

                //Stop The Intake Reverse the Pusher to Clear Jam
            case 9:
                if (controlTimer.milliseconds() > 1500) {
                    controlTimer.reset();
                    bot.pusherReverse();
                    bot.intakeStop();
                    setPathState(10);
                }
                break;

            case 10:
                bot.followPath(Paths.stack1FinishToShortShot, false);
                setPathState(11);
                break;

                //Launch the 4th Ball and Stop Pusher
            case 11:
                if (!bot.followerIsBusy()) {
                    controlTimer.reset();
                    if(controlTimer.milliseconds() > 250) {
                        controlTimer.reset();
                        bot.stickLoad();
                        bot.stopPusher();
                        bot.stickLaunch();
                        setPathState(12);
                    }
                }
                break;

                //Turn on Intake In the Case Ball Left and Reset Stick
            case 12:
                if (controlTimer.milliseconds() > 250) {
                    controlTimer.reset();
                    bot.stickLoad();
                    bot.intakeForward();
                    bot.pusherStart();
                    setPathState(13);
                }
                break;

                //Reverse Pusher to Clear Jam and Stop Intake
            case 13:
                if (controlTimer.milliseconds() > 750) {
                    controlTimer.reset();
                    bot.pusherReverse();
                    bot.intakeStop();
                    setPathState(14);
                }
                break;

                //Shoot 5th Ball, Start the Pusher, and Turn On Intake in Case Ball Left
            case 14:
                if (controlTimer.milliseconds() > 250) {
                    bot.stickLaunch();
                    controlTimer.reset();
                    bot.pusherStart();
                    bot.intakeForward();
                    setPathState(15);
                }
                break;

                //Resetting the Stick, Reverse the Pusher to Clear Jam, and Turn Off Intake
            case 15:
                if (controlTimer.milliseconds() > 1000) {
                    bot.stickLoad();
                    bot.pusherReverse();
                    bot.intakeStop();
                    controlTimer.reset();
                    setPathState(16);
                }
                break;

                //Launch The 6th Ball and Start the Pusher and Intake in Case of Leftover Ball
            case 16:
                if (controlTimer.milliseconds() > 250) {
                    bot.stickLaunch();
                    controlTimer.reset();
                    bot.pusherStart();
                    bot.intakeForward();
                    setPathState(17);
                }
                break;

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
                break;

                //Stop Opmode
            case 19:
                if (!bot.followerIsBusy()) {
                    setPathState(-1);
                    requestOpModeStop();
                }
                break;
        }
    }
}


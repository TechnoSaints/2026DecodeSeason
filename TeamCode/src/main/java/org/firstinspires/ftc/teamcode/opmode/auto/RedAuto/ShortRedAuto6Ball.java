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
        bot.setLauncherShortShot();
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
            // Move start to short shot
            // Move start to short shot
            case 0:
                bot.followPath(Paths.startToShortShot, false);
                setPathState(1);
                break;

            case 1:
                if (!bot.followerIsBusy()) {
                    bot.setLauncherShortShot();
                    controlTimer.reset();
                    bot.stickLoad();
                    bot.stopPusher();
                    setPathState(2);
                }
                break;

                //Shoot First Ball
            case 2:
                if (controlTimer.milliseconds() > 2000) {
                    bot.stickLaunch();
                    controlTimer.reset();
                    bot.stickLoad();
                    bot.pusherStart();
                    setPathState(3);
                }
                break;

                //Shoot Second Ball
            case 3:
                if (controlTimer.milliseconds() > 500) {
                    bot.stopPusher();
                    bot.stickLaunch();
                    controlTimer.reset();
                    setPathState(4);
                }
                break;
            case 4:
                if (controlTimer.milliseconds() > 250) {
                    controlTimer.reset();
                    bot.stickLoad();
                    bot.pusherStart();
                    setPathState(4);
                }
                break;

                //Shoot Third Ball
            case 5:
                if (controlTimer.milliseconds() > 750) {
                    controlTimer.reset();
                    bot.stickLaunch();
                    bot.stopPusher();
                    setPathState(6);
                }
                break;
            case 6:
                if (controlTimer.milliseconds() > 250) {
                    controlTimer.reset();
                    bot.stickLoad();
                    setPathState(7);
                }
                break;
                // Move to stack1 setup
            case 7:
                bot.followPath(Paths.shortShotToStack1Setup, false);
                setPathState(8);
                break;

            // Turn on rollers and move to stack1 finish after move is finished
            case 8:
                if (!bot.followerIsBusy()) {
                    controlTimer.reset();
                    // Turn on rollers
                    bot.intakeForward();
                    bot.pusherStart();
                    bot.followPath(Paths.stack1SetupToStack1Finish, false);
                    setPathState(9);
                }
                break;

            case 9:
                if (controlTimer.milliseconds() > 1500) {
                    controlTimer.reset();
                    bot.stopPusher();
                    bot.intakeStop();
                    setPathState(10);
                }
                break;
/*
            case 10:
                bot.followPath(Paths.stack1FinishToShortShot, false);
                setPathState(11);
                break;

            case 11:
                if (!bot.followerIsBusy()) {
                    controlTimer.reset();
                    bot.stickLoad();
                    bot.stopPusher();
                    bot.stickLaunch();
                    setPathState(12);
                }
                break;

            case 12:
                if (controlTimer.milliseconds() > 250) {
                    controlTimer.reset();
                    bot.stickLoad();
                    bot.pusherStart();
                    setPathState(13);
                }
                break;

            case 13:
                if (controlTimer.milliseconds() > 750) {
                    controlTimer.reset();
                    bot.stopPusher();
                    bot.stickLaunch();
                    setPathState(14);
                }
                break;
            case 14:
                if (controlTimer.milliseconds() > 250) {
                    controlTimer.reset();
                    bot.stickLoad();
                    bot.pusherStart();
                    setPathState(15);
                }
                break;
            case 15:
                if (controlTimer.milliseconds() > 750) {
                    controlTimer.reset();
                    bot.stickLaunch();
                    setPathState(16);
                }
                break;
            case 16:
                if (controlTimer.milliseconds() > 250) {
                    controlTimer.reset();
                    bot.stickLoad();
                    setPathState(17);
                }
                break;
            // Stop opmode */
            case 10:
                setPathState(-1);
                requestOpModeStop();
                break;
        }
    }
}


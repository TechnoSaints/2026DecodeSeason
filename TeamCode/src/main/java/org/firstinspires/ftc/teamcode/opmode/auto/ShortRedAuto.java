package org.firstinspires.ftc.teamcode.opmode.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

import org.firstinspires.ftc.teamcode.opmode.FieldLocations;
import org.firstinspires.ftc.teamcode.opmode.Paths;

@Autonomous(name = "\uD83D\uDD34shortRedAuto", group = "Red")
//@Disabled
public class ShortRedAuto extends AutoOpMode {

    @Override
    public void init() {
        FieldLocations.buildPoses("red", "short");
        super.init();
    }

    protected void autonomousPathUpdate() {
        switch (pathState) {
            // Move start to short shot
            case 0:
                bot.followPath(Paths.startToShortShot, false);
                bot.setLauncherShortShot();
                controlTimer.reset();
                setPathState(1);
                break;

            // Shoot three balls after move is finished
            case 1:
                if (!bot.followerIsBusy()) {
                    if (controlTimer.milliseconds() > 350) {
                        telemetry.addLine("I Have REACHED this CASE!");
                        telemetry.update();
                        bot.stickLaunch();
                        controlTimer.reset();
                        setPathState(2);
                    }
                }
                break;

            case 2:
                if (!bot.followerIsBusy()) {
                    if (controlTimer.milliseconds() > 1000) {
                        bot.stickLoad();
                        bot.pusherStart();
                        controlTimer.reset();
                        setPathState(3);
                    }
                }
                break;

            case 3:
                if (!bot.followerIsBusy()) {
                    if (controlTimer.milliseconds() > 1500) {
                        bot.stopPusher();
                        bot.stickLaunch();
                        controlTimer.reset();
                        setPathState(4);
                    }
                }
                break;

            case 4:
                if (!bot.followerIsBusy()) {
                    if (controlTimer.milliseconds() > 1000) {
                        bot.stickLoad();
                        bot.pusherStart();
                        controlTimer.reset();
                        setPathState(5);
                    }
                }
                break;

            case 5:
                if (!bot.followerIsBusy()) {
                    if (controlTimer.milliseconds() > 1500) {
                        bot.stopPusher();
                        bot.stickLaunch();
                        controlTimer.reset();
                        setPathState(6);
                    }
                }
                break;
            // Move to stack1 setup
            case 6:
                bot.followPath(Paths.shortShotToStack1Setup, false);
                setPathState(7);
                break;

            // Turn on rollers and move to stack1 finish after move is finished
            case 7:
                if (!bot.followerIsBusy()) {
                    bot.pusherStart();
                    bot.intakeForward();
                    bot.followPath(Paths.stack1SetupToStack1Finish, false);
                    setPathState(8);
                }
                break;
/*
            // Do additional stuff, if needed, after move is finished
            case 4:
                if (!bot.followerIsBusy()) {
                    setPathState(5);
                }
                break;

            // Move to short shot
            case 5:
                bot.followPath(Paths.stack1FinishToShortShot, false);
                setPathState(6);
                break;

            // Shoot three balls
            case 6:
                if (!bot.followerIsBusy()) {
                    // Shoot three balls
                    setPathState(7);
                }
                break;

            // Do more stuff
            case 7:

                setPathState(8);

                break;

            // Stop opmode
            case 8:

                setPathState(-1);

                requestOpModeStop();
                break; */
        }
    }
}


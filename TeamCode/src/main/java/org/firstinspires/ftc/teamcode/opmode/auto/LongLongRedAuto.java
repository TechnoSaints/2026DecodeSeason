package org.firstinspires.ftc.teamcode.opmode.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.opmode.FieldLocations;
import org.firstinspires.ftc.teamcode.opmode.Paths;

@Autonomous(name = "\uD83D\uDD34LongLongRedAuto", group = "Red")
//@Disabled
public class LongLongRedAuto extends AutoOpMode {

    @Override
    public void init() {
        FieldLocations.buildPoses("red", "long");
        super.init();
    }

    protected void autonomousPathUpdate() {
        switch (pathState) {
            // Move start to short shot
            case 0:
                bot.kickerGate();
                bot.intakeReverse();
                bot.setLauncherLongShot();
                bot.followPath(Paths.longStartToLongShot, 0.85f, true);
                setPathState(1);
                break;

            // Shoot three balls after move is finished
            case 1:
                if (!bot.followerIsBusy()) {
                    bot.kickerLoad();
                    controlTimer.reset();
                    setPathState(2);
                }
                break;
            case 2:
                if (controlTimer.milliseconds() > 2000) {
                    bot.kickerLaunch();
                    controlTimer.reset();
                    setPathState(3);
                }

                // Move to stack1 setup
            case 3:
                if (controlTimer.milliseconds() > 2250) {
                    bot.followPath(Paths.longShotToStack2Setup, 0.85f, true);
                    bot.kickerGate();
                    setPathState(4);
                }
                break;

            // Turn on rollers and move to stack1 finish after move is finished
            case 4:
                if (!bot.followerIsBusy()) {
                    // Turn on rollers
                    bot.followPath(Paths.stack2SetupToStack2Finish, 0.85f, true);
                    setPathState(5);
                }
                break;

            // Do additional stuff, if needed, after move is finished
            case 5:
                if (!bot.followerIsBusy()) {
                    setPathState(6);
                }
                break;

            // Move to short shot
            case 6:
                bot.followPath(Paths.stack2FinishToLongShot, 0.7f, true);
                setPathState(7);
                break;

            // Shoot three balls
            case 7:
                if (!bot.followerIsBusy()) {
                    bot.kickerLoad();
                    controlTimer.reset();
                    setPathState(8);
                }
                break;

            case 8:
                if (controlTimer.milliseconds() > 2000) {
                    bot.kickerLaunch();
                    controlTimer.reset();
                    setPathState(9);
                }
                break;

            // Do more stuff
            case 9:
                if (controlTimer.milliseconds() > 1500) {
                    bot.followPath(Paths.longShotToStack3Setup, 0.85f, true);
                    bot.kickerGate();
                    setPathState(10);
                }
                break;

            case 10:
                bot.followPath(Paths.stack3SetupToStack3Finish, 0.85f, true);
                setPathState(11);
                break;

            case 11:
                if (!bot.followerIsBusy()) {
                    setPathState(12);
                }
                break;

            case 12:
                bot.followPath(Paths.stack3FinishToLongShot, 0.85f, true);
                setPathState(13);
                break;

            case 13:
                if (!bot.followerIsBusy()) {
                    bot.kickerLoad();
                    controlTimer.reset();
                    setPathState(14);
                }
                break;

            case 14:
                if (controlTimer.milliseconds() > 2000) {
                    bot.kickerLaunch();
                    controlTimer.reset();
                    setPathState(15);
                }
                break;

            case 15:
                if (controlTimer.milliseconds() > 1500) {
                    bot.followPath(Paths.longShotToHumanPlayerSetup, 0.85f, true);
                    bot.kickerGate();
                    setPathState(16);
                }
            case 16:
                bot.followPath(Paths.humanPlayerSetupToHumanPlayerFinish, 0.85f, true);
                setPathState(17);
                break;

            case 17:
                if (!bot.followerIsBusy()) {
                    setPathState(18);
                }
                break;

            case 18:
                bot.followPath(Paths.humanPlayerFinishToLongShot, 0.85f, true);
                setPathState(19);
                break;

            case 19:
                if (!bot.followerIsBusy()) {
                    bot.kickerLoad();
                    controlTimer.reset();
                    setPathState(20);
                }
                break;

            case 20:
                if (controlTimer.milliseconds() > 2000) {
                    bot.kickerLaunch();
                    controlTimer.reset();
                    setPathState(21);
                }
                break;



            case 21:
                if (controlTimer.milliseconds() > 1500) {
                    bot.followPath(Paths.longShotToEndFar, 0.85f, true);
                    bot.kickerGate();
                    bot.intakeStop();
                    bot.launcherStop();
                    setPathState(22);
                }
                break;

            case 22:
                if (!bot.followerIsBusy()) {
                    setPathState(23);
                }
                break;

            // Stop opmode
            case 23:

                setPathState(-1);

                requestOpModeStop();
                break;
        }
    }
}

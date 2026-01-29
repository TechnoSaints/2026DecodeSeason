package org.firstinspires.ftc.teamcode.opmode.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.opmode.FieldLocations;
import org.firstinspires.ftc.teamcode.opmode.Paths;

@Autonomous(name = "\uD83D\uDD35ShortBlueAuto6", group = "Blue")

public class ShortBlueAuto6 extends AutoOpMode {

    @Override
    public void init() {
        FieldLocations.buildPoses("blue", "short");
        super.init();
    }

    protected void autonomousPathUpdate() {
        switch (pathState) {
            // Move start to short shot
            case 0:
                bot.kickerGate();
                bot.intakeReverse();
                bot.setLauncherShortShot();
                bot.followPath(Paths.goalStartToShortShot, 0.85f, true);
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
                if (controlTimer.milliseconds() > 2000) {
                    bot.followPath(Paths.shortShotToStack1Setup, 0.85f, true);
                    bot.kickerGate();
                    setPathState(4);
                }
                break;

            // Turn on rollers and move to stack1 finish after move is finished
            case 4:
                if (!bot.followerIsBusy()) {
                    // Turn on rollers
                    bot.followPath(Paths.stack1SetupToStack1Finish, 0.85f, true);
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
                bot.followPath(Paths.stack1FinishToShortShot, 0.85f, true);
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

            case 9:
                if (controlTimer.milliseconds() > 1250) {
                    bot.followPath(Paths.shortShotToEndClose, 0.85f, true);
                    bot.kickerGate();
                    bot.intakeStop();
                    bot.launcherStop();
                    setPathState(10);
                }
                break;

            case 10:
                if (!bot.followerIsBusy()) {
                    setPathState(11);
                }
                break;

            // Stop opmode
            case 11:

                setPathState(-1);

                requestOpModeStop();
                break;
        }
    }
}

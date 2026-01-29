package org.firstinspires.ftc.teamcode.opmode.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.opmode.FieldLocations;
import org.firstinspires.ftc.teamcode.opmode.Paths;

@Autonomous(name = "\uD83D\uDD35ShortBlueAuto3", group = "Blue")
public class ShortBlueAuto3 extends AutoOpMode {

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

            case 3:
                if (controlTimer.milliseconds() > 2000) {
                    bot.followPath(Paths.shortShotToEndClose, 0.85f, true);
                    bot.kickerGate();
                    bot.intakeStop();
                    bot.launcherStop();
                    setPathState(4);
                }
                break;

            case 4:
                if (!bot.followerIsBusy()) {
                    setPathState(5);
                }
                break;

            // Stop opmode
            case 5:

                setPathState(-1);

                requestOpModeStop();
                break;
        }
    }
}

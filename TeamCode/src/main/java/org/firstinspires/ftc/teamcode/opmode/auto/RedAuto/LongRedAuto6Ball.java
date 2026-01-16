package org.firstinspires.ftc.teamcode.opmode.auto.RedAuto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

import org.firstinspires.ftc.teamcode.opmode.FieldLocations;
import org.firstinspires.ftc.teamcode.opmode.Paths;
import org.firstinspires.ftc.teamcode.opmode.auto.AutoOpMode;
@Disabled
@Autonomous(name = "\uD83D\uDD34longRedAuto6Ball", group = "Red")
public class LongRedAuto6Ball extends AutoOpMode {

    @Override
    public void init() {
        FieldLocations.buildPoses("red", "long");
        super.init();
    }

    protected void autonomousPathUpdate() {
        switch (pathState) {
            // Move start to short shot
            case 0:
                bot.followPath(Paths.startToLongShot, false);
                setPathState(1);
                break;

            // Shoot three balls after move is finished
            case 1:
                if (!bot.followerIsBusy()) {
                    // Shoot balls
                    setPathState(2);
                }
                break;

            // Move to stack1 setup
            case 2:
                bot.followPath(Paths.longShotToStack3Setup, false);
                setPathState(3);
                break;

            // Turn on rollers and move to stack1 finish after move is finished
            case 3:
                if (!bot.followerIsBusy()) {
                    // Turn on rollers
                    bot.followPath(Paths.stack3SetupToStack3Finish, false);
                    setPathState(4);
                }
                break;

            // Do additional stuff, if needed, after move is finished
            case 4:
                if (!bot.followerIsBusy()) {
                    setPathState(5);
                }
                break;

            // Move to short shot
            case 5:
                bot.followPath(Paths.stack3FinishTolongShot, false);
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
                break;
        }
    }
}


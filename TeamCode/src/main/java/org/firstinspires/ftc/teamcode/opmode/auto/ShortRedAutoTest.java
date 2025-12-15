package org.firstinspires.ftc.teamcode.opmode.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.opmode.FieldLocationsTest;
import org.firstinspires.ftc.teamcode.opmode.PathsTest;

@Autonomous(name = "\uD83D\uDD34shortRedAutoTest", group = "Red")
//@Disabled
public class ShortRedAutoTest extends AutoOpMode {

    @Override
    public void init() {
        FieldLocationsTest.buildPoses("red", "short");
        super.init();
    }

    protected void autonomousPathUpdate() {
        switch (pathState) {
            // Move start to short shot
            case 0:
                bot.followPath(PathsTest.startToShortShot, false);
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
                bot.followPath(PathsTest.shortShotToStack1Setup, false);
                setPathState(3);
                break;

            // Turn on rollers and move to stack1 finish after move is finished
            case 3:
                if (!bot.followerIsBusy()) {
                    // Turn on rollers
                    bot.followPath(PathsTest.stack1SetupToStack1Finish, false);
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
                bot.followPath(PathsTest.stack1FinishToShortShot, false);
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


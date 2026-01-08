package org.firstinspires.ftc.teamcode.opmode.auto.RedAuto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.opmode.FieldLocations;
import org.firstinspires.ftc.teamcode.opmode.Paths;
import org.firstinspires.ftc.teamcode.opmode.auto.AutoOpMode;

@Autonomous(name = "\uD83D\uDD34shortRedAuto6Ball", group = "Red")
public class ShortRedAuto9Ball extends AutoOpMode {

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
                bot.followPath(Paths.shortShotToStack1Setup, false);
                setPathState(3);
                break;

            // Turn on rollers and move to stack1 finish after move is finished
            case 3:
                if (!bot.followerIsBusy()) {
                    // Turn on rollers
                    bot.followPath(Paths.stack1SetupToStack1Finish, false);
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

            //Go To Stack 2
            case 8:
                bot.followPath(Paths.shortShotToStack2Setup, false);
                setPathState(9);
                break;


            // Turn on rollers and move to stack1 finish after move is finished
            case 9:
                if (!bot.followerIsBusy()) {
                    // Turn on rollers
                    bot.followPath(Paths.stack2SetupToStack2Finish, false);
                    setPathState(10);
                }
                break;

            case 10:
                if (!bot.followerIsBusy()) {
                    setPathState(11);
                }
                break;

            // Move to short shot
            case 11:
                bot.followPath(Paths.stack2FinishToShortShot, false);
                setPathState(12);
                break;

            // Do more stuff
            case 12:

                setPathState(13);
                break;

            // Stop opmode
            case 13:
                setPathState(-1);
                requestOpModeStop();
                break;

        }
    }
}


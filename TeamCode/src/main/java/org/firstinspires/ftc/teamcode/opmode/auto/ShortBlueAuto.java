package org.firstinspires.ftc.teamcode.opmode.auto;

import com.pedropathing.geometry.Pose;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.opmode.FieldLocations;
import org.firstinspires.ftc.teamcode.opmode.Paths;

@Autonomous(name = "\uD83D\uDD34shortBlueAuto", group = "Blue")
public class ShortBlueAuto extends AutoOpMode {
    private Pose launchPose = FieldLocations.shortShotPose;

    @Override
    public void init() {
        super.init();
        FieldLocations.buildPoses("blue", "short");
        Paths.buildPaths(bot.getFollower());
    }

    protected void autonomousPathUpdate() {
        switch (pathState) {
            // Move start to short shot
            case 0:
                bot.followPath(Paths.startToShortShot, 0.8);
                setPathState(1);
                break;

            // Shoot three balls after move is finished
            case 1:
                /*if (!bot.followerIsBusy() && bot.getState() == 0) {
                    // Shoot balls
                    if (bot.getBalls()[0] == 'X' && bot.getBalls()[1] == 'X' && bot.getBalls()[3] == 'X') {
                        setPathState(2);
                    }
                    else {
                        bot.shootBalls();
                    }
                }*/
                if (!bot.followerIsBusy()){
                    telemetry.addLine("first move finished");
                    setPathState(2);
                }
                break;


            // Move to stack1 setup
            case 2:
                bot.followPath(Paths.shortShotToStack1Setup, 0.8);
                setPathState(3);
                break;

            // Turn on rollers and move to stack1 finish after move is finished
            case 3:
                if (!bot.followerIsBusy()) {
                    // Turn on rollers
                    bot.followPath(Paths.stack1SetupToStack1Finish, 0.8);
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
                bot.followPath(Paths.stack1FinishToShortShot, 0.8);
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
        //bot.updateLauncher(launchPose, true, 0.8);
        bot.update();
        telemetry.addData("Path State", pathState);
    }
}


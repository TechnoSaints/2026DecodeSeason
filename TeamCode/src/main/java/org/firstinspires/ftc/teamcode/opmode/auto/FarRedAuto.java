package org.firstinspires.ftc.teamcode.opmode.auto;

import com.pedropathing.geometry.Pose;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.opmode.FieldLocations;
import org.firstinspires.ftc.teamcode.opmode.Paths;

@Autonomous(name = "\uD83D\uDD34Far Red Auto", group = "Red")
public class FarRedAuto extends AutoOpMode {
    private Pose launchPose = FieldLocations.longShotPose;
    private ElapsedTime timer;

    @Override
    public void init() {
        FieldLocations.buildPoses("red", "long");
        super.init();
        timer = new ElapsedTime();
        timer.reset();
        bot.setPosition(0.15);
    }

    protected void autonomousPathUpdate(){
        switch (pathState) {
            // Move start to short shot
            case 0:
                bot.setSpeed(0.8);
                bot.followPath(Paths.startToLongShot, false);
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
                if (!bot.isBusy()){
                    bot.intakeBalls();
                    timer.reset();
                    setPathState(2);
                }
                break;



            // Move to stack1 setup
            case 2:
                if (timer.milliseconds() > 1000) {
                    bot.stopStorage();
                    bot.stopLauncher();
                    bot.followPath(Paths.longShotToStack3Setup, false);
                    setPathState(3);
                }
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

            // Move to long shot
            case 5:
                bot.followPath(Paths.stack3FinishToLongShot, false);
                setPathState(6);
                break;

            // Shoot three balls
            case 6:
                if (!bot.followerIsBusy()) {
                    // Shoot three balls
                    setPathState(10);
                }
                break;

            // Do more stuff
            case 7:

                setPathState(8);
                break;

            // Move off launch line
            case 8:
                bot.followPath(Paths.longShotToBase, false);
                setPathState(9);
                break;

            case 9:
                if (!bot.followerIsBusy()){
                    setPathState(10);
                }
                break;

            // Stop opmode
            case 10:
                setPathState(-1);
                requestOpModeStop();
                break;
        }
        telemetry.addData("Path State", pathState);
    }
}


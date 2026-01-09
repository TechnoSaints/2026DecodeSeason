package org.firstinspires.ftc.teamcode.opmode.auto;

import com.pedropathing.geometry.Pose;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.opmode.FieldLocations;
import org.firstinspires.ftc.teamcode.opmode.Paths;

@Autonomous(name = "\uD83D\uDD35Far Blue Auto", group = "Blue")
public class FarBlueAuto extends AutoOpMode {
    private Pose launchPose = FieldLocations.longShotPose;
    private ElapsedTime timer;

    @Override
    public void init() {
        FieldLocations.buildPoses("blue", "long");
        super.init();
        timer = new ElapsedTime();
        timer.reset();
        bot.setPosition(0.225);
    }

    protected void autonomousPathUpdate(){
        switch (pathState) {
            // Move start to short shot
            case 0:
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
                if (/*!bot.isBusy()*/false){
                    bot.setSpeed(0.85);
                    setPathState(2);
                }
                break;



            // Move to stack1 setup
            case 2:
                if (!bot.isBusy()){
                    bot.intakeBalls();
                    timer.reset();
                    setPathState(3);
                }
                break;

            // Turn on rollers and move to stack1 finish after move is finished
            case 3:
                if (timer.milliseconds() > 1000) {
                    bot.stopStorage();
                    bot.stopLauncher();
                    bot.followPath(Paths.longShotToStack3Setup, false);
                    setPathState(4);
                }
                break;

            // Do additional stuff, if needed, after move is finished
            case 4:
                if (!bot.followerIsBusy()) {
                    // Turn on rollers
                    bot.followPath(Paths.stack3SetupToStack3Finish, false);
                    setPathState(5);
                }
                break;

            // Move to long shot
            case 5:
                if (!bot.followerIsBusy()) {
                    bot.followPath(Paths.stack3FinishToLongShot, false);
                    setPathState(6);
                }
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

            // Move off launch line
            case 8:
                bot.followPath(Paths.longShotToStack2Setup, false);
                setPathState(9);
                break;

            case 9:
                if (!bot.followerIsBusy()) {
                    // Turn on rollers
                    bot.followPath(Paths.stack2SetupToStack2Finish, false);
                    setPathState(10);
                }
                break;

            // Do additional stuff, if needed, after move is finished
            case 10:
                if (!bot.followerIsBusy()) {
                    setPathState(11);
                }
                break;

            // Move to long shot
            case 11:
                bot.followPath(Paths.stack2FinishToLongShot, false);
                setPathState(12);
                break;

            // Shoot three balls
            case 12:
                if (!bot.followerIsBusy()) {
                    // Shoot three balls
                    setPathState(13);
                }
                break;

            case 13:
                if (!bot.followerIsBusy()) {
                    // Turn on rollers
                    bot.followPath(Paths.stack1SetupToStack1Finish, false);
                    setPathState(14);
                }
                break;

            // Do additional stuff, if needed, after move is finished
            case 14:
                if (!bot.followerIsBusy()) {
                    setPathState(15);
                }
                break;

            // Move to long shot
            case 15:
                bot.followPath(Paths.stack1FinishToLongShot, false);
                setPathState(16);
                break;

            // Shoot three balls
            case 16:
                if (!bot.followerIsBusy()) {
                    // Shoot three balls
                    setPathState(17);
                }
                break;

            // Stop opmode
            case 17:
                setPathState(-1);
                requestOpModeStop();
                break;
        }
        telemetry.addData("Path State", pathState);
    }
}


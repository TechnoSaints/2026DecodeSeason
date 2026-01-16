package org.firstinspires.ftc.teamcode.opmode.auto;

import com.pedropathing.geometry.Pose;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.opmode.FieldLocations;
import org.firstinspires.ftc.teamcode.opmode.Paths;

@Autonomous(name = "\uD83D\uDD34Far Red Auto", group = "Red", preselectTeleOp = "Red Far Teleop - After Auto")
public class FarRedAuto extends AutoOpMode {
    private Pose launchPose = FieldLocations.longShotPose;
    private ElapsedTime timer;
    private static final double launcherSpeed = 0.8;

    @Override
    public void init() {
        FieldLocations.buildPoses("red", "long");
        super.init();
        timer = new ElapsedTime();
        timer.reset();
        bot.setPosition(0.1);
    }

    protected void autonomousPathUpdate(){
        switch (pathState) {
            // Move start to short shot
            case 0:
                bot.followPath(Paths.startToLongShot, 0.8);
                bot.setSpeed(launcherSpeed);
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
                    bot.shootBalls();
                    timer.reset();
                    setPathState(2);
                }
                break;

            // Move to stack1 setup
            case 2:
                if (timer.milliseconds() > 2000) {
                    bot.setSpeed(0);
                    bot.followPath(Paths.longShotToStack3Setup, 0.8);
                    setPathState(3);
                }
                break;

            // Turn on rollers and move to stack1 finish after move is finished
            case 3:
                if (!bot.followerIsBusy()) {
                    // Turn on rollers
                    bot.intakeBalls();
                    bot.followPath(Paths.stack3SetupToStack3Finish, 0.25);
                    setPathState(4);
                }
                break;

            // Do additional stuff, if needed, after move is finished
            case 4:
                if (!bot.followerIsBusy() && bot.getState() == 0) {
                    setPathState(5);
                }
                break;

            // Move to long shot
            case 5:
                bot.followPath(Paths.stack3FinishToLongShot, 0.8);
                bot.setSpeed(launcherSpeed);
                setPathState(6);
                break;

            // Shoot three balls
            case 6:
                if (!bot.isBusy()) {
                    // Shoot three balls
                    bot.shootBalls();
                    timer.reset();
                    setPathState(7);
                }
                break;

            // Do more stuff
            case 7:
                if (timer.milliseconds() > 2000){
                    bot.stopLauncher();
                    setPathState(8);
                }
                break;

            case 8:
                bot.followPath(Paths.longShotToStack2Setup, 0.8);
                setPathState(9);
                break;

            case 9:
                if (!bot.followerIsBusy()) {
                    // Turn on rollers
                    bot.intakeBalls();
                    bot.followPath(Paths.stack2SetupToStack2Finish,0.25);
                    setPathState(11);
                }
                break;

            // Move to long shot
            case 11:
                if (!bot.followerIsBusy() && bot.getState() == 0){
                    bot.followPath(Paths.stack2FinishToLongShot, 0.8);
                    bot.setSpeed(launcherSpeed);
                    setPathState(12);
                }
                break;

            // Shoot three balls
            case 12:
                if (!bot.isBusy()) {
                    // Shoot three balls
                    bot.shootBalls();
                    timer.reset();
                    setPathState(13);
                }
                break;
            case 13:
                if (timer.milliseconds() > 2000){
                    bot.stopLauncher();
                    bot.followPath(Paths.longShotToBase);
                    setPathState(14);
                }
                break;
            case 14:
                if (!bot.followerIsBusy()){
                    setPathState(15);
                }
                break;

            // Stop opmode
            case 15:
                setPathState(-1);
                requestOpModeStop();
                break;
        }
        telemetry.addData("Path State", pathState);
        telemetry.update(); mjhgfdw1
    }
}


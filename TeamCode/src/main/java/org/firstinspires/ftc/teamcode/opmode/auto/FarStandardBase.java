package org.firstinspires.ftc.teamcode.opmode.auto;

import com.pedropathing.geometry.Pose;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.common.hardwareConfiguration.setPoints.LauncherSettings;
import org.firstinspires.ftc.teamcode.opmode.FieldLocations;
import org.firstinspires.ftc.teamcode.opmode.Paths;

public class FarStandardBase extends AutoOpMode {
    private Pose launchPose = FieldLocations.longShotPose;
    private ElapsedTime timer;

    @Override
    public void init() {
        super.init();
        timer = new ElapsedTime();
        timer.reset();
        bot.setPosition(LauncherSettings.longShotPosition);
    }

    protected void autonomousPathUpdate(){
        switch (pathState) {
            // Move start to short shot
            case 0:
                bot.followPath(Paths.startToLongShot, 1);
                bot.setLauncherSpeed(LauncherSettings.longShotVelocityFactor);
                setPathState(1);
                break;

            // Shoot three balls after move is finished
            case 1:
                if (!bot.isBusy()){
                    bot.openGate();
                    bot.storageManualIntake();
                    timer.reset();
                    setPathState(2);
                }
                break;

            // Move to stack1 setup
            case 2:
                if (timer.milliseconds() > 2000 ) {
                    bot.closeGate();
                    bot.setLauncherSpeed(0);
                    bot.followPath(Paths.longShotToStack1Setup, 1);
                    setPathState(3);
                }
                break;

            // Turn on rollers and move to stack1 finish after move is finished
            case 3:
                if (!bot.followerIsBusy()) {
                    // Turn on rollers
                    bot.intakeBalls();
                    bot.followPath(Paths.stack1SetupToStack1Finish, 0.8);
                    timer.reset();
                    setPathState(4);
                }
                break;

            // Do additional stuff, if needed, after move is finished
            case 4:
                if (!bot.followerIsBusy() && (bot.getState() == 0 || timer.milliseconds() > 3000)) {
                    setPathState(5);
                }
                break;

            // Move to short shot
            case 5:
                bot.followPath(Paths.stack1FinishToShortShot, 1);
                bot.setLauncherSpeed(LauncherSettings.shortShotVelocityFactor);
                setPathState(6);
                break;

            // Shoot three balls
            case 6:
                if (!bot.isBusy()) {
                    // Shoot three balls
                    bot.openGate();
                    bot.storageManualIntake();
                    timer.reset();
                    setPathState(7);
                }
                break;


            // Move to stack1 setup
            case 7:
                if (timer.milliseconds() > 2000 ) {
                    bot.closeGate();
                    bot.setLauncherSpeed(0);
                    bot.followPath(Paths.shortShotToStack2Setup, 1);
                    setPathState(8);
                }
                break;

            // Turn on rollers and move to stack1 finish after move is finished
            case 8:
                if (!bot.followerIsBusy()) {
                    // Turn on rollers
                    bot.intakeBalls();
                    bot.followPath(Paths.stack2SetupToStack2Finish, 0.8);
                    timer.reset();
                    setPathState(9);
                }
                break;

            // Do additional stuff, if needed, after move is finished
            case 9:
                if (!bot.followerIsBusy() && (bot.getState() == 0 || timer.milliseconds() > 3000)) {
                    setPathState(10);
                }
                break;

            // Move to short shot
            case 10:
                bot.followPath(Paths.stack2FinishToShortShot, 1);
                bot.setLauncherSpeed(LauncherSettings.shortShotVelocityFactor);
                setPathState(11);
                break;

            // Shoot three balls
            case 11:
                if (!bot.isBusy()) {
                    // Shoot three balls
                    bot.openGate();
                    bot.storageManualIntake();
                    timer.reset();
                    setPathState(12);
                }
                break;

            // Do more stuff
            case 12:
                if (timer.milliseconds() > 2000 ){
                    bot.closeGate();
                    bot.stopLauncher();
                    setPathState(13);
                }
                break;

            case 13:
                bot.followPath(Paths.shortShotToStack3Setup, 1);
                setPathState(14);
                break;

            case 14:
                if (!bot.followerIsBusy()) {
                    // Turn on rollers
                    bot.intakeBalls();
                    bot.followPath(Paths.stack3SetupToStack3Finish,0.8);
                    timer.reset();
                    setPathState(15);
                }
                break;

            // Move to gate & open it
            case 15:
                if (!bot.followerIsBusy() && (bot.getState() == 0 || timer.milliseconds() > 3000)){
                    bot.followPath(Paths.stack3FinishToLongShot);
                    bot.setLauncherSpeed(LauncherSettings.longShotVelocityFactor);
                    timer.reset();
                    setPathState(17);
                }
                break;

            case 16:
                if (!bot.followerIsBusy() && timer.milliseconds() > 2000){
                    bot.followPath(Paths.gateToShortShot, 1);
                    bot.setLauncherSpeed(LauncherSettings.longShotVelocityFactor);
                    setPathState(17);
                }
                break;

            // Shoot three balls
            case 17:
                if (!bot.isBusy()) {
                    // Shoot three balls
                    bot.openGate();
                    bot.storageManualIntake();
                    timer.reset();
                    setPathState(22);
                }
                break;
            case 22:
                if (timer.milliseconds() > 2000) {
                    bot.closeGate();
                    bot.followPath(Paths.longShotToBase);
                    setPathState(23);
                }
                break;
            case 23:
                if (!bot.followerIsBusy()){
                    setPathState(30);
                }
                break;

            // Stop opmode
            case 30:
                setPathState(-1);
                requestOpModeStop();
                break;
        }
        telemetry.addData("Path State", pathState);
        telemetry.update();
    }
}


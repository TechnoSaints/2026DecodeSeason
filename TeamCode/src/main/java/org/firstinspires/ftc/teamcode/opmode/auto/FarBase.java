package org.firstinspires.ftc.teamcode.opmode.auto;

import com.pedropathing.geometry.Pose;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.common.hardwareConfiguration.setPoints.LauncherSettings;
import org.firstinspires.ftc.teamcode.opmode.FieldLocations;
import org.firstinspires.ftc.teamcode.opmode.Paths;

public class FarBase extends AutoOpMode {
    private Pose launchPose = FieldLocations.longShotPose;
    private ElapsedTime timer;
    private final static double INTAKE_DELAY_MS = 2200;
    private final static double SHOOT_DELAY_MS = 1750;
    private final static double INTAKE_SPEED = 0.9;

    // Set to -1 to disable skipping. If set to N, the autonomous will jump to case (N - 1).
    // Using N-1 means the skip will land one case earlier than the configured case.
    protected int skipFromState = -1;

    @Override
    public void init() {
        super.init();
        timer = new ElapsedTime();
        timer.reset();
        bot.setPosition(LauncherSettings.longShotPosition);
    }

    protected void autonomousPathUpdate(){
        switch (pathState) {
            // Move start to long shot
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
                    if (skipFromState == 1){
                        setPathState(22);
                    }
                    else {
                        setPathState(2);
                    }
                }
                break;

            // Move to stack3 setup
            case 2:
                if (timer.milliseconds() > SHOOT_DELAY_MS ) {
                    bot.closeGate();
                    bot.setLauncherSpeed(0);
                    bot.followPath(Paths.longShotToStack3Setup, 1);
                    setPathState(3);
                }
                break;

            // Turn on rollers and move to stack3 finish after move is finished
            case 3:
                if (!bot.followerIsBusy()) {
                    // Turn on rollers
                    bot.intakeBalls();
                    bot.followPath(Paths.stack3SetupToStack3Finish, INTAKE_SPEED);
                    timer.reset();
                    setPathState(4);
                }
                break;

            // Do additional stuff, if needed, after move is finished
            case 4:
                if ((bot.getState() == 0 || timer.milliseconds() > INTAKE_DELAY_MS)) {
                    setPathState(5);
                }
                break;

            // Move to long shot
            case 5:
                bot.followPath(Paths.stack3FinishToLongShot, 1);
                bot.setLauncherSpeed(LauncherSettings.longShotVelocityFactor);
                setPathState(6);
                break;

            // Shoot three balls
            case 6:
                if (!bot.isBusy()) {
                    // Shoot three balls
                    bot.openGate();
                    bot.storageManualIntake();
                    timer.reset();
                    if (skipFromState == 6){
                        setPathState(22);
                    }
                    else {
                        setPathState(7);
                    }
                }
                break;


            // Move to stack2 setup
            case 7:
                if (timer.milliseconds() > SHOOT_DELAY_MS ) {
                    bot.closeGate();
                    bot.setLauncherSpeed(0);
                    bot.followPath(Paths.longShotToStack2Setup, 1);
                    setPathState(8);
                }
                break;

            // Turn on rollers and move to stack2 finish after move is finished
            case 8:
                if (!bot.followerIsBusy()) {
                    // Turn on rollers
                    bot.intakeBalls();
                    bot.followPath(Paths.stack2SetupToStack2Finish, INTAKE_SPEED);
                    timer.reset();
                    setPathState(9);
                }
                break;

            // Do additional stuff, if needed, after move is finished
            case 9:
                if ((bot.getState() == 0 || timer.milliseconds() > INTAKE_DELAY_MS)) {
                    setPathState(10);
                }
                break;

            // Move to long shot
            case 10:
                bot.followPath(Paths.stack2FinishToLongShot, 1);
                bot.setLauncherSpeed(LauncherSettings.longShotVelocityFactor);
                setPathState(11);
                break;

            // Shoot three balls
            case 11:
                if (!bot.isBusy()) {
                    // Shoot three balls
                    bot.openGate();
                    bot.storageManualIntake();
                    timer.reset();
                    if (skipFromState == 11){
                        setPathState(22);
                    }
                    else {
                        setPathState(12);
                    }
                }
                break;

            // Do more stuff
            case 12:
                if (timer.milliseconds() > SHOOT_DELAY_MS ){
                    bot.closeGate();
                    bot.stopLauncher();
                    setPathState(13);
                }
                break;

            case 13:
                bot.followPath(Paths.longShotToStack1Setup, 1);
                setPathState(14);
                break;

            case 14:
                if (!bot.followerIsBusy()) {
                    // Turn on rollers
                    bot.intakeBalls();
                    bot.followPath(Paths.stack1SetupToStack1Finish,INTAKE_SPEED);
                    timer.reset();
                    setPathState(15);
                }
                break;

            // Move to gate & open it
            case 15:
                if ((bot.getState() == 0 || timer.milliseconds() > INTAKE_DELAY_MS)){
                    bot.followPath(Paths.stack1FinishToLongShot);
                    bot.setLauncherSpeed(LauncherSettings.longShotVelocityFactor);
                    timer.reset();
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
                if (timer.milliseconds() > SHOOT_DELAY_MS) {
                    bot.closeGate();
                    bot.setLauncherSpeed(0);
                    bot.followPath(Paths.longShotToEnd);
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


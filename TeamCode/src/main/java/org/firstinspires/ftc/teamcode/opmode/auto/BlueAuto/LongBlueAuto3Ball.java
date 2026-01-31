package org.firstinspires.ftc.teamcode.opmode.auto.BlueAuto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.opmode.FieldLocations;
import org.firstinspires.ftc.teamcode.opmode.Paths;
import org.firstinspires.ftc.teamcode.opmode.auto.AutoOpMode;

@Autonomous(name = "\uD83D\uDD35longBlueAuto3Ball", group = "Blue")
public class LongBlueAuto3Ball extends AutoOpMode {

    @Override
    public void init() {
        FieldLocations.buildPoses("blue", "long");
        super.init();
    }

    protected void autonomousPathUpdate() {
        bot.pusherUpdate();

        switch (pathState) {
            case 0:
                bot.followPath(Paths.startToLongShot, 0.6f, false);
                bot.setLauncherLongShot();
                bot.stickLoad();
                setPathState(1);
                break;

            case 1:
                if (controlTimer.milliseconds() > 1500) {
                    controlTimer.reset();
                    setPathState(2);
                }
                break;
            // Shoot three balls after move is finished
            case 2:
                if (!bot.followerIsBusy()) {
                    bot.stickLaunch();
                    setPathState(3);
                }
                break;
            //Turning on Black Wheel and Resetting Stick
            case 3:
                if (controlTimer.milliseconds() > 500) {
                    bot.stickLaunchLoad();
                    controlTimer.reset();
                    setPathState(4);
                }
                break;

            //Clearing Jam
            case 4:
                if (controlTimer.milliseconds() > 150) {
                    controlTimer.reset();
                    setPathState(5);
                }
                break;

            //Shooting Second Ball
            case 5:
                if (controlTimer.milliseconds() > 1250) {
                    bot.stickLaunchLoad();
                    controlTimer.reset();
                    setPathState(6);
                }
                break;

            //Resetting Stick
            case 6:
                if (controlTimer.milliseconds() > 1) {
                    controlTimer.reset();
                    setPathState(7);
                }
                break;

            //Shooting Third Ball
            case 7:
                if (controlTimer.milliseconds() > 1250) {
                    bot.stickLaunchLoad();
                    controlTimer.reset();
                    setPathState(8);
                }
                break;

            // Move to stack1 setup
            case 8:
                bot.stickLaunchLoad();
                bot.followPath(Paths.longShotToParking, 0.8f, false);
                setPathState(9);
                break;

            // Stop opmode
            case 9:
                if (!bot.followerIsBusy()) {
                    setPathState(-1);
                    requestOpModeStop();
                    break;
                }
        }
    }
}


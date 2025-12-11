package org.firstinspires.ftc.teamcode.opmode.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

import org.firstinspires.ftc.teamcode.opmode.FieldLocations;
import org.firstinspires.ftc.teamcode.opmode.Paths;

@Autonomous(name = "\uD83D\uDD34shortRedAuto", group = "Red")
//@Disabled
public class ShortRedAuto extends AutoOpMode {

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
                bot.setLauncherShortShot();
                controlTimer.reset();
                setPathState(1);
                break;

            // Shoot three balls after move is finished
            case 1:
<<<<<<< HEAD
                if (!bot.followerIsBusy()) {
                    if (controlTimer.milliseconds() > 350) {
                        telemetry.addLine("I Have reached this CASE 1");
                        telemetry.update();
                        bot.stickLaunch();
                        controlTimer.reset();
                        setPathState(2);
                    }
=======
                if (!bot.followerIsBusy() && (controlTimer.milliseconds() > 350)) {
                    bot.stickLaunch();
                    controlTimer.reset();
                    setPathState(2);
>>>>>>> 05607e604007f8f22c19ad34b4f91891de52ff9a
                }
                break;

            case 2:
<<<<<<< HEAD
                if (!bot.followerIsBusy()) {
                    if (controlTimer.milliseconds() > 1000) {
                        bot.stickLoad();
                        bot.pusherStart();
                        telemetry.addLine("I Have reached this CASE 2");
                        telemetry.update();
                        controlTimer.reset();
                        setPathState(3);
                    }
=======
                if (controlTimer.milliseconds() > 1000) {
                    bot.stickLoad();
                    bot.pusherStart();
                    controlTimer.reset();
                    setPathState(3);
>>>>>>> 05607e604007f8f22c19ad34b4f91891de52ff9a
                }
                break;

            case 3:
<<<<<<< HEAD
                if (!bot.followerIsBusy()) {
                    if (controlTimer.milliseconds() > 1500) {
                        bot.stopPusher();
                        telemetry.addLine("I Have reached this CASE 3");
                        telemetry.update();
                        bot.stickLaunch();
                        controlTimer.reset();
                        setPathState(4);
                    }
=======
                if (controlTimer.milliseconds() > 1500) {
                    bot.stopPusher();
                    bot.stickLaunch();
                    controlTimer.reset();
                    setPathState(4);
>>>>>>> 05607e604007f8f22c19ad34b4f91891de52ff9a
                }
                break;

            case 4:
<<<<<<< HEAD
                if (!bot.followerIsBusy()) {
                    if (controlTimer.milliseconds() > 1000) {
                        bot.stickLoad();
                        bot.pusherStart();
                        telemetry.addLine("I Have reached this CASE 4");
                        telemetry.update();
                        controlTimer.reset();
                        telemetry.update();
                        telemetry.update();
                        setPathState(5);
                    }
=======
                if (controlTimer.milliseconds() > 1000) {
                    bot.stickLoad();
                    bot.pusherStart();
                    controlTimer.reset();
                    setPathState(5);
>>>>>>> 05607e604007f8f22c19ad34b4f91891de52ff9a
                }
                break;

            case 5:
<<<<<<< HEAD
                if (!bot.followerIsBusy()) {
                    if (controlTimer.milliseconds() > 1500) {
                        bot.stopPusher();
                        bot.stickLaunch();
                        telemetry.addLine("I Have reached this CASE 5");
                        telemetry.update();
                        controlTimer.reset();
                        setPathState(6);
                    }
=======
                if (controlTimer.milliseconds() > 1500) {
                    bot.stopPusher();
                    bot.stickLaunch();
                    controlTimer.reset();
                    setPathState(6);
>>>>>>> 05607e604007f8f22c19ad34b4f91891de52ff9a
                }
                break;

            // Move to stack1 setup
            case 6:
                if (!bot.followerIsBusy()) {
                    bot.followPath(Paths.shortShotToStack1Setup, false);
                    telemetry.addLine("I Have reached this CASE 6");
                    telemetry.update();
                    setPathState(7);
                }
                break;

            // Turn on rollers and move to stack1 finish after move is finished
            case 7:
                if (!bot.followerIsBusy()) {
                    bot.pusherStart();
                    bot.intakeForward();
                    telemetry.addLine("I Have reached this CASE 7");
                    telemetry.update();
                    bot.followPath(Paths.stack1SetupToStack1Finish, false);
                    setPathState(-1);
                }
                break;
/*
            // Do additional stuff, if needed, after move is finished
            case 8:
                if (!bot.followerIsBusy()) {
                    bot.stopPusher();
                    setPathState(9);
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
*/
            // Stop opmode
            case 8:
                setPathState(-1);
                requestOpModeStop();
                break;
        }
    }
}


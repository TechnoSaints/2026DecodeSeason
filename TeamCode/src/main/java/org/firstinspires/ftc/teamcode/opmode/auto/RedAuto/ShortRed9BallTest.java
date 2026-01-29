package org.firstinspires.ftc.teamcode.opmode.auto.RedAuto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.opmode.FieldLocations;
import org.firstinspires.ftc.teamcode.opmode.Paths;
import org.firstinspires.ftc.teamcode.opmode.auto.AutoOpMode;

@Autonomous(name = "\uD83D\uDD34Short Red Auto 9 Ball Test", group = "Red")
public class ShortRed9BallTest extends AutoOpMode {

    @Override
    public void init() {
        FieldLocations.buildPoses("red", "short");
        super.init();
    }

    protected void autonomousPathUpdate() {
        bot.pusherUpdate();
        switch (pathState) {

            //Move From Start To Short Shot

            case 0:
                bot.setLauncherShortShot();
                bot.stickLoad();
                bot.followPath(Paths.startToShortShot, 1f, true);
                setPathState(1);
                break;

            //Shooting The First Shot
            case 1:
                if (!bot.followerIsBusy()) {
                    controlTimer.reset();
                    setPathState(2);
                }
                break;

            //Turning on Black Wheel and Resetting Stick
            case 2:
                if (controlTimer.milliseconds() > 250) {
                    bot.stickLaunchLoad();
                    controlTimer.reset();
                    setPathState(3);
                }
                break;

            //Clearing Jam
            case 3:
                if (controlTimer.milliseconds() > 150) {
                    controlTimer.reset();
                    setPathState(4);
                }
                break;

            //Shooting Second Ball
            case 4:
                if (controlTimer.milliseconds() > 750) {
                    bot.stickLaunchLoad();
                    controlTimer.reset();
                    setPathState(5);
                }
                break;

            //Resetting Stick
            case 5:
                if (controlTimer.milliseconds() > 1) {
                    controlTimer.reset();
                    setPathState(6);
                }
                break;

            //Shooting Third Ball
            case 6:
                if (controlTimer.milliseconds() > 1250) {
                    bot.stickLaunchLoad();
                    controlTimer.reset();
                    setPathState(7);
                }
                break;

            //Move to Stack 1 Setup and Clear Launcher
            case 7:
                if (controlTimer.milliseconds() > 250) {
                    bot.stickLaunchLoad(); //Just In Case A Extra Ball Was There
                    bot.followPath(Paths.shortShotToStack1Setup, 1f, true);
                    bot.intakeForward();
                    setPathState(8);
                }
                break;

            //Turn on Intake and Move to Stack 1 Finish after Stack 1 Setup Move is Finished
            case 8:
                if (!bot.followerIsBusy()) {
                    bot.followPath(Paths.stack1SetupToStack1Finish, 0.6f, true);
                    controlTimer.reset();
                    setPathState(9);
                }
                break;

            //Pause To Left the Intake Finish
            case 9:
                if (controlTimer.milliseconds() > 1500) {
                    controlTimer.reset();
                    setPathState(10);
                }
                break;

            //Returning To Shooting Position
            case 10:
                bot.followPath(Paths.stack1FinishToShortShot, 1f, true);
                setPathState(11);
                break;

            //Clear Jam
            case 11:
                if (!bot.followerIsBusy()) {
                    controlTimer.reset();
                    // bot.pusherReverse();
                    setPathState(12);
                }
                break;

            //Launch 4th Ball
            case 12:
                if (controlTimer.milliseconds() > 750) {
                    controlTimer.reset();
                    bot.stickLaunchLoad();
                    //  ();
                    setPathState(13);
                }
                break;

            //Reset Stick and Start Pusher
            case 13:
                if (controlTimer.milliseconds() > 250) {
                    controlTimer.reset();
                    setPathState(14);
                }
                break;

            //Clear The Jam
            case 14:
                if (controlTimer.milliseconds() > 1250) {
                    controlTimer.reset();
                    setPathState(15);
                }
                break;

            //Launch 5th Ball
            case 15:
                if (controlTimer.milliseconds() > 750) {
                    bot.stickLaunchLoad();
                    controlTimer.reset();
                    setPathState(16);
                }
                break;

            //Reset Stick
            case 16:
                if (controlTimer.milliseconds() > 250) {
                    controlTimer.reset();
                    setPathState(17);
                }
                break;

            //Launch 6th Ball and Travel for Move Bonus
            case 17:
                if (controlTimer.milliseconds() > 250) {
                    controlTimer.reset();
                    setPathState(18);
                }
                break;

            case 18:
                if (controlTimer.milliseconds() > 1500) {
                    bot.stickLaunchLoad();
                    controlTimer.reset();
                    setPathState(19);
                }
                break;

            //Move to Stack 1 Setup and Clear Launcher
            case 19:
                if (controlTimer.milliseconds() > 250) {
                    bot.stickLaunchLoad(); //Just In Case A Extra Ball Was There
                    bot.followPath(Paths.shortShotToStack2Setup, 1f, true);
                    bot.intakeForward();
                    telemetry.addLine("Moved to stack2 Setup");
                    telemetry.update();
                    setPathState(20);
                }
                break;

            //Turn on Intake and Move to Stack 1 Finish after Stack 1 Setup Move is Finished
            case 20:
                if (!bot.followerIsBusy()) {
                    bot.followPath(Paths.stack2SetupToStack2Finish, 0.6f, true);
                    telemetry.addLine("Intaked Balls");
                    telemetry.update();
                    controlTimer.reset();
                    setPathState(21);
                }
                break;

            //Pause To Left the Intake Finish
            case 21:
                if (controlTimer.milliseconds() > 2250) {
                    controlTimer.reset();
                    setPathState(22);
                }
                break;

            //Returning To Shooting Position
            case 22:
                bot.followPath(Paths.stack2FinishToShortShot, 1f, true);
                telemetry.addLine("Moved to shoot.");
                telemetry.update();
                setPathState(23);
                break;

            //Clear Jam
            case 23:
                if (!bot.followerIsBusy()) {
                    controlTimer.reset();
                    // bot.pusherReverse();
                    setPathState(24);
                }
                break;

            //Launch 4th Ball
            case 24:
                if (controlTimer.milliseconds() > 500) {
                    controlTimer.reset();
                    bot.stickLaunchLoad();
                    //  ();
                    setPathState(25);
                }
                break;

            //Reset Stick and Start Pusher
            case 25:
                if (controlTimer.milliseconds() > 250) {
                    controlTimer.reset();
                    setPathState(26);
                }
                break;

//            //Clear The Jam
            case 26:
                if (controlTimer.milliseconds() > 1250) {
                    controlTimer.reset();
                    setPathState(27);
                }
                break;

            //Launch 5th Ball
            case 27:
                if (controlTimer.milliseconds() > 500) {
                    bot.stickLaunchLoad();
                    controlTimer.reset();
                    setPathState(28);
                }
                break;

            //Reset Stick
            case 28:
                if (controlTimer.milliseconds() > 250) {
                    controlTimer.reset();
                    setPathState(29);
                }
                break;

            //Launch 6th Ball and Travel for Move Bonus
            case 29:
                if (controlTimer.milliseconds() > 1) {
                    controlTimer.reset();
                    setPathState(30);
                }
                break;

            case 30:
                if (controlTimer.milliseconds() > 500) {
                    bot.stickLaunchLoad();
                    controlTimer.reset();
                    setPathState(31);
                }
                break;

            case 31:
                if (controlTimer.milliseconds() > 250) {
                    controlTimer.reset();
                   // bot.followPath(Paths.shortShotToStack2Setup, 0.8f, true);
                    setPathState(32);
                }
                break;

            //Stop Opmode
            case 32:
                if (!bot.followerIsBusy()) {
                    setPathState(-1);
                    requestOpModeStop();
                }
                break;
        }
    }
}


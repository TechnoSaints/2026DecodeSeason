package org.firstinspires.ftc.teamcode.opmode.auto.Encoder_Autos;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.common.Drivetrain;
import org.firstinspires.ftc.teamcode.common.hardwareConfiguration.data.DrivetrainData;
import org.firstinspires.ftc.teamcode.common.hardwareConfiguration.data.GoBilda312DcMotorData;
import org.firstinspires.ftc.teamcode.opmode.teleop.TeleopBots.TeleopBot;

@Disabled
@Autonomous(name = "BlueShortEncoderAuto", group = "Linear OpMode")
public class ShortBlueEncoderAuto extends LinearOpMode {
    private static Drivetrain drivetrain;
    protected ElapsedTime controlTimer = new ElapsedTime(ElapsedTime.Resolution.MILLISECONDS);
    private static TeleopBot bot;
    @Override
    public void runOpMode() {

        drivetrain = new Drivetrain(this, hardwareMap, telemetry, new DrivetrainData(), new GoBilda312DcMotorData());
        bot = new TeleopBot(this, telemetry);
        waitForStart();
        if(opModeIsActive() && !isStopRequested()) {

            //Turn on Motors
            bot.setLauncherShortShot();

            //Move To Launch Spot
            drivetrain.moveStraight(-30);
            drivetrain.log();
            drivetrain.turn(1.75);
            telemetry.update();
            drivetrain.strafe(-10);
            drivetrain.moveStraight(-27);

            //Launches First Ball
            drivetrain.log();
            telemetry.update();
            controlTimer.reset();
            while(controlTimer.milliseconds() < 1000 && opModeIsActive()) {
                idle();
            }
            bot.stickLaunch();
            while(controlTimer.milliseconds() < 2000 && opModeIsActive()) {
                idle();
            }

            //Loads Second Ball
            bot.stickLoad();
            bot.turnOnBlackWheel();
            controlTimer.reset();
            while(controlTimer.milliseconds() < 1000 && opModeIsActive()) {
                idle();
            }

            //Launches Second  Ball
            bot.turnOffBlackWheel();
            bot.stickLaunch();
            controlTimer.reset();
            while(controlTimer.milliseconds() < 2000 && opModeIsActive()) {
                idle();
            }

            //Loads Third Ball
            bot.stickLoad();
            bot.turnOnBlackWheel();
            controlTimer.reset();
            while(controlTimer.milliseconds() < 1000 && opModeIsActive()) {
                idle();
            }

            //Launches Third Ball
            bot.turnOffBlackWheel();
            bot.stickLaunch();
            controlTimer.reset();
            while(controlTimer.milliseconds() < 2000 && opModeIsActive()) {
                idle();
            }

            //Moves To First Line
            drivetrain.turn(33);
            bot.intakeForward();
            bot.stickLoad();
            drivetrain.strafe(-7.5);
            bot.turnOnBlackWheel();
            controlTimer.reset();
            while(controlTimer.milliseconds() < 1000 && opModeIsActive()) {
                idle();
            }
            drivetrain.moveStraightSlow(-36);
            while(controlTimer.milliseconds() < 5000 && opModeIsActive()) {
                idle();
            }
            bot.turnOffBlackWheel();

            //Return from Spike 1 to Shoot
            drivetrain.moveStraight(36);
            drivetrain.strafe(7.5);
            drivetrain.log();
            telemetry.update();
            drivetrain.turn(-33);
            drivetrain.log();
            telemetry.update();

            //Launches First Ball from Spike
            drivetrain.log();
            telemetry.update();
            controlTimer.reset();
            while(controlTimer.milliseconds() < 1000 && opModeIsActive()) {
                idle();
            }
            bot.stickLaunch();
            while(controlTimer.milliseconds() < 2000 && opModeIsActive()) {
                idle();
            }

            //Loads Second Ball
            bot.stickLoad();
            bot.turnOnBlackWheel();
            controlTimer.reset();
            while(controlTimer.milliseconds() < 1000 && opModeIsActive()) {
                idle();
            }

            //Launches Second  Ball
            bot.turnOffBlackWheel();
            bot.stickLaunch();
            controlTimer.reset();
            while(controlTimer.milliseconds() < 2000 && opModeIsActive()) {
                idle();
            }

            //Loads Third Ball
            bot.stickLoad();
            bot.turnOnBlackWheel();
            controlTimer.reset();
            while(controlTimer.milliseconds() < 1000 && opModeIsActive()) {
                idle();
            }

            //Launches Third Ball
            bot.turnOffBlackWheel();
            bot.stickLaunch();
            controlTimer.reset();
            while(controlTimer.milliseconds() < 2000 && opModeIsActive()) {
                idle();
            }
            //launch balls
            /*
            drivetrain.moveStraight(-36);
            drivetrain.turn(-25);
            drivetrain.strafe(3);
            // turn on intake
            // bot.intakeForward();
            // bot.turnOnBlackWheel();
            // sleep some time
            // bot.turnOnBlackWheel();
            drivetrain.strafe(3);
            drivetrain.strafe(-6);
            drivetrain.moveStraight(36); */

        }

    }
}

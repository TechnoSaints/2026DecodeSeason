package org.firstinspires.ftc.teamcode.opmode.auto.Encoder_Autos;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.common.Drivetrain;
import org.firstinspires.ftc.teamcode.common.hardwareConfiguration.data.DrivetrainData;
import org.firstinspires.ftc.teamcode.common.hardwareConfiguration.data.GoBilda312DcMotorData;
import org.firstinspires.ftc.teamcode.opmode.teleop.TeleopBots.TeleopBotBasic;

@Disabled
@Autonomous(name = "BlueLongEncoderAuto", group = "Linear OpMode")
public class LongBlueEncoderAuto extends LinearOpMode {
    private static Drivetrain drivetrain;
    protected ElapsedTime controlTimer = new ElapsedTime(ElapsedTime.Resolution.MILLISECONDS);
    private static TeleopBotBasic bot;
    @Override
    public void runOpMode() {

        drivetrain = new Drivetrain(this, hardwareMap, telemetry, new DrivetrainData(), new GoBilda312DcMotorData());
        bot = new TeleopBotBasic(this, telemetry);
        waitForStart();
        if(opModeIsActive() && !isStopRequested()) {

            //Turn on Motors
            bot.setLauncherLongShot();

            //Move To Launch Spot
            drivetrain.moveStraight(3);
            drivetrain.log();
            drivetrain.turn(-5.9);
            telemetry.update();
         //   drivetrain.strafe(10);

            //Launches First Ball
            drivetrain.log();
            telemetry.update();
            controlTimer.reset();
            while(controlTimer.milliseconds() < 10000 && opModeIsActive()) {
                idle();
            }
            bot.stickLaunch();
            controlTimer.reset();
            while(controlTimer.milliseconds() < 1000 && opModeIsActive()) {
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
            while(controlTimer.milliseconds() < 1000 && opModeIsActive()) {
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
            drivetrain.moveStraight(24);
/*
            //Moves To First Line
            drivetrain.turn(-35);
            bot.intakeForward();
            drivetrain.strafe(-6);
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

            /*
            drivetrain.moveStraight(30);
            drivetrain.log();
            telemetry.update();
            drivetrain.turn(33);
            drivetrain.log();
            telemetry.update();
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


package org.firstinspires.ftc.teamcode.opmode.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.common.Drivetrain;
import org.firstinspires.ftc.teamcode.common.hardwareConfiguration.data.DrivetrainData;
import org.firstinspires.ftc.teamcode.common.hardwareConfiguration.data.GoBilda312DcMotorData;
import org.firstinspires.ftc.teamcode.opmode.teleop.TeleopBot;


@Autonomous(name = "RedShortEncoderAuto", group = "Linear OpMode")
public class EmergencyAuto extends LinearOpMode {
    private static Drivetrain drivetrain;
    protected ElapsedTime controlTimer = new ElapsedTime(ElapsedTime.Resolution.MILLISECONDS);
    private static TeleopBot bot;
    @Override
    public void runOpMode() {

        drivetrain = new Drivetrain(this, hardwareMap, telemetry, new DrivetrainData(), new GoBilda312DcMotorData());
        bot = new TeleopBot(this, telemetry);
        waitForStart();
        if(opModeIsActive() && !isStopRequested()) {
            // moves to launch spot
            bot.setLauncherShortShot();
            //moves back 57 inches
            drivetrain.moveStraight(-57);
            drivetrain.log();
            // corrects for drift
            drivetrain.turn(-1);
            telemetry.update();
            //strafes right to aim
            drivetrain.strafe(10);
            drivetrain.log();
            telemetry.update();
            controlTimer.reset();
            bot.stickLaunch();
            while(controlTimer.milliseconds() < 1000 && opModeIsActive()) {
                idle();
            }

            // launches first 3 preloaded balls

            /*
            sleep(200);
            bot.stickLoad();
            bot.turnOnBlackWheel();
            sleep(500);
            bot.turnOffBlackWheel();
            bot.stickLaunch();
            sleep(200);
            drivetrain.turn(-33);
            /*
            drivetrain.log();
            telemetry.update();
            // bot.intakeForward();
            // bot.turnOnBlackWheel();
            // sleep some time
            // bot.turnOnBlackWheel();
            drivetrain.moveStraight(-30);
            drivetrain.log();
            telemetry.update();
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

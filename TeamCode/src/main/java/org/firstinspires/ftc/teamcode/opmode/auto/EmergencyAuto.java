package org.firstinspires.ftc.teamcode.opmode.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.common.Drivetrain;
import org.firstinspires.ftc.teamcode.common.hardwareConfiguration.data.DrivetrainData;
import org.firstinspires.ftc.teamcode.common.hardwareConfiguration.data.GoBilda312DcMotorData;
import org.firstinspires.ftc.teamcode.opmode.teleop.TeleopBot;


@Autonomous(name = "RedShortEncoderAuto", group = "Linear OpMode")
public class EmergencyAuto extends LinearOpMode {
    private static Drivetrain drivetrain;
    private static TeleopBot bot;
    @Override
    public void runOpMode() {

        drivetrain = new Drivetrain(this, hardwareMap, telemetry, new DrivetrainData(), new GoBilda312DcMotorData());
        bot = new TeleopBot(this, telemetry);
        waitForStart();
        if(opModeIsActive() && !isStopRequested()) {
            //bot.setLauncherShortShot();
            drivetrain.moveStraight(-57);
            drivetrain.strafe(9);
            //bot.stickLaunch();
            drivetrain.turn(-33);
            // bot.intakeForward();
            // bot.turnOnBlackWheel();
            // sleep some time
            // bot.turnOnBlackWheel();
            drivetrain.moveStraight(-30);
            drivetrain.moveStraight(30);
            drivetrain.turn(33);
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

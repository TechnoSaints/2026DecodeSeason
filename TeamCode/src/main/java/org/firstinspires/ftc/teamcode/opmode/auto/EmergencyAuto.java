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
            bot.setLauncherShortShot();
            drivetrain.moveStraight(-60);
            drivetrain.turn(-1);
            bot.stickLaunch();
        }

    }
}

package org.firstinspires.ftc.teamcode.opmode.teleop;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.pedropathing.geometry.Pose;
import com.qualcomm.hardware.gobilda.GoBildaPinpointDriver;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;
import org.firstinspires.ftc.teamcode.common.TeleopBot;

@Config
public class TeleopOdoBase extends LinearOpMode {
    private TeleopBot bot;
    protected boolean red;
    protected Pose2D startPose;
    private ElapsedTime buttonTimer;

    @Override
    public void runOpMode() {
        buttonTimer = new ElapsedTime();
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
        bot = new TeleopBot(this, telemetry);

        waitForStart();
        buttonTimer.reset();
        while (opModeIsActive() && !isStopRequested()) {
            bot.processGamepadInput(gamepad1);
            if (gamepad1.left_bumper){
                bot.intakeBalls();
            }
            else if (gamepad1.right_bumper){
                bot.shootBalls();
            }
            else if (gamepad1.left_trigger > 0.2){
                bot.stopStorage();
            }
            boolean changeTarget = gamepad1.right_trigger > 0.7 && buttonTimer.milliseconds() > 250;
            if (changeTarget){
                buttonTimer.reset();
            }
            bot.updateLauncher(red, changeTarget);
            if (gamepad1.touchpad){
                bot.setOdoPosition(new Pose2D(DistanceUnit.INCH, 48,-52, AngleUnit.DEGREES, 45));
            }

            Pose2D pose = bot.getPosition();
            Pose2D target = new Pose2D(DistanceUnit.INCH, 1,1, AngleUnit.DEGREES, 0);
            telemetry.addData("X (inches)", pose.getX(DistanceUnit.INCH));
            telemetry.addData("Y (inches)", pose.getY(DistanceUnit.INCH));
            telemetry.addData("Heading (degree)", pose.getHeading(AngleUnit.DEGREES));
            bot.teleopUpdate(red);
            telemetry.update();
        }
    }
}

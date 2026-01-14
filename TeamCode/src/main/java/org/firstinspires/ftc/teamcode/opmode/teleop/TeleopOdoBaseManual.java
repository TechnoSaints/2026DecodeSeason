package org.firstinspires.ftc.teamcode.opmode.teleop;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;
import org.firstinspires.ftc.teamcode.common.TeleopBotOld;

@Config
public class TeleopOdoBaseManual extends LinearOpMode {
    private TeleopBotOld bot;
    protected boolean red;
    protected Pose2D startPose;
    private ElapsedTime buttonTimer, buttonTimer2;
    private double speed = 0;
    private double angle = 0;

    @Override
    public void runOpMode() {
        buttonTimer = new ElapsedTime();
        buttonTimer2 = new ElapsedTime();
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
        bot = new TeleopBotOld(this, telemetry);

        waitForStart();
        buttonTimer.reset();
        buttonTimer2.reset();
        while (opModeIsActive() && !isStopRequested()) {
            bot.processGamepadInput(gamepad1);
            if (gamepad1.left_bumper){
                bot.intakeBalls();
            }
            else if (gamepad1.right_bumper){
                bot.shootBalls();
            }
            if (gamepad1.left_trigger > 0.7 && buttonTimer.milliseconds() > 250){
                speed += 0.1;
                buttonTimer.reset();
            }
            else if (gamepad1.right_trigger > 0.7 && buttonTimer.milliseconds() > 250){
                speed -= 0.1;
                buttonTimer.reset();
            }
            bot.setSpeed(speed);
            if (gamepad1.left_stick_button && buttonTimer2.milliseconds() > 250){
                angle -= 0.1;
                buttonTimer2.reset();
            }
            else if (gamepad1.right_stick_button && buttonTimer2.milliseconds() > 250){
                angle += 0.1;
                buttonTimer2.reset();
            }

            if (gamepad1.touchpad){
                bot.setOdoPosition(new Pose2D(DistanceUnit.INCH, 48,-52, AngleUnit.DEGREES, 45));
            }
            if (gamepad1.ps){
                bot.stopStorage();
            }

            Pose2D pose = bot.getPosition();
            Pose2D target = new Pose2D(DistanceUnit.INCH, 1,1, AngleUnit.DEGREES, 0);
            telemetry.addData("Teleop speed", -speed);
            telemetry.addData("Teleop angle", angle);
            telemetry.addData("X (inches)", pose.getX(DistanceUnit.INCH));
            telemetry.addData("Y (inches)", pose.getY(DistanceUnit.INCH));
            telemetry.addData("Heading (degree)", pose.getHeading(AngleUnit.DEGREES));
            bot.update(red);
            telemetry.update();
        }
    }
}

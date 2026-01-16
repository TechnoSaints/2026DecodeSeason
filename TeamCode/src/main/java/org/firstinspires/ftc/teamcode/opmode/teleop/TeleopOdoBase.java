package org.firstinspires.ftc.teamcode.opmode.teleop;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.hardware.gobilda.GoBildaPinpointDriver;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;
import org.firstinspires.ftc.teamcode.common.Drivetrain;
import org.firstinspires.ftc.teamcode.common.Launcher;
import org.firstinspires.ftc.teamcode.common.Storage;
import org.firstinspires.ftc.teamcode.common.TeleopBot;
import org.firstinspires.ftc.teamcode.common.hardwareConfiguration.data.DrivetrainData;
import org.firstinspires.ftc.teamcode.common.hardwareConfiguration.data.GoBilda435DcMotorData;
import org.firstinspires.ftc.teamcode.opmode.FieldLocations;

@Config

public class TeleopOdoBase extends OpMode {
    private double velocityFactorIncrement = 0.05;
    private double targetVelocityFactor = 0.0;
    private double positionIncrement = 0.05;
    private double targetLaunchPosition = 0.3;
    private TeleopBot bot;
    private ElapsedTime timer;
    private boolean autonomous = false;
    protected boolean noStart = false;

    @Override
    public void init(){
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
        bot = new TeleopBot(this, telemetry);
        bot.setSpeed(targetVelocityFactor);
        bot.setPosition(targetLaunchPosition);
        timer = new ElapsedTime();
    }

    @Override
    public void start() {
        bot.startTeleopDrive();
        timer.reset();
    }

    @Override
    public void loop(){
        if (gamepad1.right_trigger > 0.7 && targetVelocityFactor < 1 && timer.milliseconds() >= 100) {
            targetVelocityFactor += velocityFactorIncrement;
            timer.reset();
        } else if (gamepad1.left_trigger > 0.7 && targetVelocityFactor > 0 && timer.milliseconds() >= 100) {
            targetVelocityFactor -= velocityFactorIncrement;
            timer.reset();
        }
        if (gamepad1.rightBumperWasPressed() && targetLaunchPosition < 0.25) {
            targetLaunchPosition += positionIncrement;
        } else if (gamepad1.leftBumperWasPressed() && targetLaunchPosition > 0.05) {
            targetLaunchPosition -= positionIncrement;
        }
        if (gamepad1.leftStickButtonWasPressed()){
            bot.intakeBalls();
        }
        else if (gamepad1.rightStickButtonWasPressed()){
            bot.shootBalls();
        }
        else if (gamepad1.psWasPressed()){
            bot.stopStorage();
        }
        if (gamepad1.optionsWasPressed()){
            bot.storageManualIntake();
        }
        else if (gamepad1.shareWasPressed()){
            bot.storageManualEject();
        }
        if (!autonomous){
            if (gamepad1.dpad_up){
                bot.handleTeleopDrive(-0.25, 0, 0);
            }
            else if (gamepad1.dpad_down){
                bot.handleTeleopDrive(0.25, 0, 0);
            }
            else if (gamepad1.dpad_left){
                bot.handleTeleopDrive(0, -0.25, 0);
            }
            else if (gamepad1.dpad_right){
                bot.handleTeleopDrive(0, 0.25, 0);
            }
            else {
                bot.handleTeleopDrive(gamepad1.left_stick_y, gamepad1.left_stick_x, gamepad1.right_stick_x);
            }
        }
        if (gamepad1.touchpad){
            if (!noStart){
                bot.resetOdo(FieldLocations.goalPose);
            }
            else if (gamepad1.triangle){
                bot.resetOdo(FieldLocations.goalLeftPose);
                FieldLocations.buildPoses("blue", "long", true);
                bot.buildPaths();
            }
            else if (gamepad1.square){
                bot.resetOdo(FieldLocations.goalRightPose);
                FieldLocations.buildPoses("red", "long", true);
                bot.buildPaths();
            }
        }
        else {
            if (gamepad1.triangleWasPressed()){
                // In case touchpad wasn't pressed & triangle does something else
            }
            if (gamepad1.squareWasPressed()){
                // In case touchpad wasn't pressed & square does something else
            }
            if (gamepad1.crossWasPressed()){
                bot.moveToFarShot();
                autonomous = true;
            }
            if (gamepad1.circleWasPressed()){
                bot.startTeleopDrive();
                autonomous = false;
            }
        }

        bot.setSpeed(targetVelocityFactor);
        bot.setPosition(targetLaunchPosition);
        telemetry.addData("Target Velocity in Teleop: ", targetVelocityFactor);
        telemetry.addData("Target Velocity in Teleop: ", targetLaunchPosition);
        bot.update();
        telemetry.update();
    }
}

package org.firstinspires.ftc.teamcode.opmode.teleop;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.common.TeleopBotSimple;
import org.firstinspires.ftc.teamcode.common.hardwareConfiguration.data.GoBilda6000DcMotorData;

@Config
@TeleOp(name = "Teleop - No Odo Simple", group = "Linear OpMode")
public class TeleopNoOdoSimple extends LinearOpMode {
    private TeleopBotSimple bot;
    DcMotorEx intake, leftLauncher, rightLauncher;
    Servo leftAimer, rightAimer, pusher;
    double launcherTicksPerSecond;
    ElapsedTime aimerTimer = new ElapsedTime();
    private final static double ADJUSTMENT_DELAY = 50;
    final static double INCREASE_CONSTANT = 0.05;
    boolean pressed;

    @Override
    public void runOpMode() {
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
        bot = new TeleopBotSimple(this, telemetry);
        intake = hardwareMap.get(DcMotorEx.class, "intake");
        leftLauncher = hardwareMap.get(DcMotorEx.class, "leftLauncher");
        rightLauncher = hardwareMap.get(DcMotorEx.class, "rightLauncher");
        leftAimer = hardwareMap.get(Servo.class, "leftAimer");
        rightAimer = hardwareMap.get(Servo.class, "rightAimer");
        pusher = hardwareMap.get(Servo.class, "pusher");
        launcherTicksPerSecond = GoBilda6000DcMotorData.ticksPerGearboxRev;
        leftLauncher.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightLauncher.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        waitForStart();
        aimerTimer.reset();
        leftAimer.setPosition(0);
        rightAimer.setPosition(0);
        while (opModeIsActive() && !isStopRequested()) {
            bot.processDrivetrainInput(gamepad1);
            // Launcher
            leftLauncher.setPower(gamepad1.right_trigger-gamepad1.left_trigger);
            rightLauncher.setPower(gamepad1.right_trigger-gamepad1.left_trigger);

            // Aimer
            double leftPosition = leftAimer.getPosition();
            double rightPosition = rightAimer.getPosition();

            if (aimerTimer.milliseconds() > ADJUSTMENT_DELAY) {
                if (gamepad1.right_bumper && !pressed && leftPosition < 1 && rightPosition < 1){
                    leftAimer.setPosition(leftPosition+INCREASE_CONSTANT);
                    rightAimer.setPosition(rightPosition+INCREASE_CONSTANT);
                    aimerTimer.reset();
                    pressed = true;
                }
                else if (gamepad1.left_bumper && !pressed && leftPosition > 0 && rightPosition > 0) {
                    leftAimer.setPosition(leftPosition - INCREASE_CONSTANT);
                    rightAimer.setPosition(rightPosition - INCREASE_CONSTANT);
                    aimerTimer.reset();
                    pressed = true;
                }
                else if (!gamepad1.right_bumper && !gamepad1.left_bumper && pressed){
                    pressed = false;
                }
            }

            // Intake
            if (gamepad1.a){
                intake.setPower(-1);
            }
            else if (gamepad1.x){
                intake.setPower(1);
            }
            else if (gamepad1.b){
                intake.setPower(0);
            }

            // Pusher (temp)
            double pushPosition = pusher.getPosition();
            if (gamepad2.right_bumper){
                pusher.setPosition(pushPosition+INCREASE_CONSTANT);
            }
            else if (gamepad2.left_bumper){
                pusher.setPosition(pushPosition-INCREASE_CONSTANT);
            }

            telemetry.addData("Left Motor RPM", leftLauncher.getVelocity()/launcherTicksPerSecond);
            telemetry.addData("Right Motor RPM", rightLauncher.getVelocity()/launcherTicksPerSecond);
            telemetry.addData("Left Aimer Position", leftAimer.getPosition());
            telemetry.addData("Right Aimer Position", rightAimer.getPosition());
            telemetry.update();
        }
    }
}

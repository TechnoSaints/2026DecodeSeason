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
    double launcherTicksPerSecond, maxRPM;
    ElapsedTime aimerTimer = new ElapsedTime();
    ElapsedTime pusherTimer = new ElapsedTime();
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
        maxRPM = GoBilda6000DcMotorData.maxMotorRpm;

        leftLauncher.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightLauncher.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftAimer.setPosition(0);
        rightAimer.setPosition(0);

        waitForStart();
        aimerTimer.reset();
        pusherTimer.reset();
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
            if (gamepad1.y){

                pusher.setPosition(0.1);
                pusherTimer.reset();
                while (pusherTimer.milliseconds() < 500){

                }
                pusher.setPosition(0.72);

            }

            telemetry.addData("Left Motor RPM", leftLauncher.getVelocity()/launcherTicksPerSecond);
            telemetry.addData("Right Motor RPM", rightLauncher.getVelocity()/launcherTicksPerSecond);
            if (leftLauncher.getVelocity()/launcherTicksPerSecond == maxRPM && rightLauncher.getVelocity()/launcherTicksPerSecond == maxRPM){
                telemetry.addLine("Launcher is Ready");
            }
            else {
                telemetry.addLine("Launcher is not Ready");
            }
            telemetry.addData("Left Aimer Position", leftAimer.getPosition());
            telemetry.addData("Right Aimer Position", rightAimer.getPosition());
            telemetry.addData("Pusher Position", pusher.getPosition());
            telemetry.update();
        }
    }
}

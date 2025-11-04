package org.firstinspires.ftc.teamcode.opmode.teleop;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.LLResultTypes;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.common.TeleopBotSimple;
import org.firstinspires.ftc.teamcode.common.hardwareConfiguration.data.GoBilda6000DcMotorData;

import java.util.ArrayList;
import java.util.List;

@Config
@TeleOp(name = "Teleop - With Odo Simple", group = "Linear OpMode")
public class TeleopWithOdoSimple extends LinearOpMode {
    private TeleopBotSimple bot;
    DcMotorEx intake, leftLauncher, rightLauncher;
    Servo leftAimer, rightAimer, pusher;
    double launcherTicksPerSecond, maxVelocity;
    ElapsedTime aimerTimer = new ElapsedTime();
    ElapsedTime pusherTimer = new ElapsedTime();
    ElapsedTime speedTimer = new ElapsedTime();
    private final static double ADJUSTMENT_DELAY = 50;
    final static double INCREASE_CONSTANT = 0.05;
    boolean pressed, rumble, pusherActive;
    Limelight3A limelight;

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
        maxVelocity = GoBilda6000DcMotorData.maxTicksPerSec;
        rumble = false;
        pusherActive = false;
        boolean startPressed = false;
        double limiter = 1;
        List<Character> motif = new ArrayList<>();

        leftLauncher.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightLauncher.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftAimer.setPosition(0);
        rightAimer.setPosition(0);

        limelight = hardwareMap.get(Limelight3A.class, "limelight"); // Change "limelight" to what it was in config
        limelight.pipelineSwitch(8); // Change to our pipeline, setup tutorial -> https://www.youtube.com/watch?v=slt0fIq-a2E

        waitForStart();
        limelight.start();
        leftAimer.setPosition(0.5);
        rightAimer.setPosition(0.5);
        aimerTimer.reset();
        pusherTimer.reset();
        speedTimer.reset();
        // 1580 tps far
        while (opModeIsActive() && !isStopRequested()) {
            bot.odoDrivetrainInput(gamepad1);

            // Launcher
            leftLauncher.setPower((gamepad1.right_trigger-gamepad1.left_trigger));
            rightLauncher.setPower((gamepad1.right_trigger-gamepad1.left_trigger));

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

            telemetry.addData("Launcher Velocity", leftLauncher.getVelocity());
            if (leftLauncher.getVelocity() >= 1500 && leftLauncher.getVelocity() <=1600){
                telemetry.addLine("Launcher is Ready");
                gamepad1.setLedColor(0, 255, 0, 120000);
                if (rumble){
                    gamepad1.rumble(100);
                    rumble = false;
                }
            }
            else {
                telemetry.addLine("Launcher is not Ready");
                gamepad1.setLedColor(255, 0, 0, 120000);
                rumble = true;
            }
            telemetry.addData("Aimer Position", leftAimer.getPosition());
            telemetry.addData("Pusher Position", pusher.getPosition());
            LLResult llResult = limelight.getLatestResult(); // pulls data from limelight
            if (llResult != null && llResult.isValid()) { // FYI: First time we pull the limelight it will likely return a null
                List<LLResultTypes.BarcodeResult> tags = llResult.getBarcodeResults();
                for (int i = 0; i < tags.size(); i++){
                    if (tags.get(i).getData() == "21"){
                        motif.add('g');
                        motif.add('p');
                        motif.add('p');
                    }
                    else if (tags.get(i).getData() == "22"){
                        motif.add('p');
                        motif.add('g');
                        motif.add('p');
                    }
                    else if (tags.get(i).getData() == "23"){
                        motif.add('p');
                        motif.add('p');
                        motif.add('g');
                    }
                    else {
                        telemetry.addData("Motif ID", tags.get(i).getData());
                    }
                }
            }

            telemetry.update();
        }
    }
}

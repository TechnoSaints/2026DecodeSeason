package org.firstinspires.ftc.teamcode.opmode.teleop;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.pedropathing.geometry.Pose;
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
import java.util.Objects;

public abstract class TeleopWithOdoSimple extends LinearOpMode {
    private TeleopBotSimple bot;
    ElapsedTime aimerTimer = new ElapsedTime();
    ElapsedTime pusherTimer = new ElapsedTime();
    ElapsedTime speedTimer = new ElapsedTime();
    private final static double ADJUSTMENT_DELAY = 50;
    final static double INCREASE_CONSTANT = 0.05;
    boolean rumble, pusherActive;
    protected boolean red;
    protected Pose startPose;
    Limelight3A limelight;

    public abstract void initializeOpMode();


    @Override
    public void runOpMode() {
        initializeOpMode();
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
        bot = new TeleopBotSimple(this, telemetry, startPose);
        rumble = false;
        pusherActive = false;
        telemetry.addData("Start Pose", startPose.toString());
        telemetry.update();

        //List<Character> motif = new ArrayList<>();

        /*limelight = hardwareMap.get(Limelight3A.class, "limelight"); // Change "limelight" to what it was in config
        limelight.pipelineSwitch(8); // Change to our pipeline, setup tutorial -> https://www.youtube.com/watch?v=slt0fIq-a2E
         */
        waitForStart();
        bot.startOdoTeleop();
        //limelight.start();
        aimerTimer.reset();
        pusherTimer.reset();
        speedTimer.reset();
        // 1580 tps far
        while (opModeIsActive() && !isStopRequested()) {
            bot.odoDrivetrainInput(gamepad1);

            if (gamepad1.right_bumper){
                bot.preloadLauncher(red);
            }

            else if (gamepad1.left_bumper){
                bot.stopLauncher();
            }

            if (gamepad1.left_trigger > 0.2){
                bot.intake();
            }

            else if (gamepad1.right_trigger > 0.2){
                bot.shoot();
            }

            else if (gamepad1.left_stick_button){
                bot.
            }

            // Adds lights to indicate launcher status and rumble when launcher is ready
            if (bot.launcherIsReady()){
                gamepad1.setLedColor(0, 255, 0, 120000);
                gamepad1.rumble(100);
            }
            else {
                gamepad1.setLedColor(255, 0 , 0, 120000);
            }

            if (gamepad1.ps){
                bot.resetOdo();
            }

            bot.updateStorage();

            // Detects motif (not for now)
            /*
            if (limelight.isRunning()){
                LLResult llResult = limelight.getLatestResult(); // pulls data from limelight
                if (llResult != null && llResult.isValid()) { // FYI: First time we pull the limelight it will likely return a null
                List<LLResultTypes.BarcodeResult> tags = llResult.getBarcodeResults();
                for (int i = 0; i < tags.size(); i++){
                    if (Objects.equals(tags.get(i).getData(), "21")){
                        motif.add('g');
                        motif.add('p');
                        motif.add('p');
                        telemetry.addData("Motif ID", tags.get(i).getData());
                        limelight.stop();
                    }
                    else if (Objects.equals(tags.get(i).getData(), "22")){
                        motif.add('p');
                        motif.add('g');
                        motif.add('p');
                        telemetry.addData("Motif ID", tags.get(i).getData());
                        limelight.stop();
                    }
                    else if (Objects.equals(tags.get(i).getData(), "23")){
                        motif.add('p');
                        motif.add('p');
                        motif.add('g');
                        telemetry.addData("Motif ID", tags.get(i).getData());
                        limelight.stop();
                    }
                }
            }
            }
            */

            bot.log();
            telemetry.update();
        }
    }
}

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
    DcMotorEx intake, launchTest1, launchTest2;
    Servo pusher;
    double launcherTicksPerSecond;
    ElapsedTime aimerTimer = new ElapsedTime();
    private final static double ADJUSTMENT_DELAY = 50;
    boolean pressed;

    @Override
    public void runOpMode() {
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
        bot = new TeleopBotSimple(this, telemetry);
        intake = hardwareMap.get(DcMotorEx.class, "intake");
        launchTest1 = hardwareMap.get(DcMotorEx.class, "launchTest1");
        launchTest2 = hardwareMap.get(DcMotorEx.class, "launchtest2");
        pusher = hardwareMap.get(Servo.class, "pusher");
        launcherTicksPerSecond = GoBilda6000DcMotorData.ticksPerGearboxRev;
        launchTest1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        launchTest2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        waitForStart();
        aimerTimer.reset();
        while (opModeIsActive() && !isStopRequested()) {
            bot.processDrivetrainInput(gamepad1);
            // Launcher
            if (gamepad1.right_trigger >= .2)
            {
                launchTest1.setPower(0);
                launchTest2.setPower(0);
            }

            if (gamepad1.left_trigger >= .2)
            {
                launchTest1.setPower(.7);
                launchTest2.setPower(.7);
            }

            // Aimer



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
            if (gamepad1.right_bumper){
                pusher.setPosition(.5);
            }
            else if (gamepad1.left_bumper){
                pusher.setPosition(0);
            }

            telemetry.addData("Left Motor RPM", launchTest1.getVelocity()/launcherTicksPerSecond);
            telemetry.addData("Right Motor RPM", launchTest2.getVelocity()/launcherTicksPerSecond);
            telemetry.update();
        }
    }
}

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
    DcMotorEx launchTest1, launchTest2;
    Servo launchServo;
    double launcherTicksPerSecond;
    ElapsedTime aimerTimer = new ElapsedTime();
    private final static double ADJUSTMENT_DELAY = 50;
    boolean pressed;

    @Override
    public void runOpMode() {
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
        bot = new TeleopBotSimple(this, telemetry);
        launchTest1 = hardwareMap.get(DcMotorEx.class, "launchTest1");
        launchTest2 = hardwareMap.get(DcMotorEx.class, "launchtest2");
        double constantAdd = .3;
        double currentPosition = 0;
        double position = .5;

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
                launchTest1.setPower(-.6);
                launchTest2.setPower(-.6);
            }

            // Aimer

            if (gamepad1.a)
            {
                launchServo.setPosition(.5);
                position = .5;
            }

            if (gamepad1.b)
            {
                launchServo.setPosition(position + .05);
            }


            // Intake

            // Pusher (temp)
            boolean pressedOnce = false;

            telemetry.update();
        }
    }
}

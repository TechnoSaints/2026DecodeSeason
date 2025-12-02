
package org.firstinspires.ftc.teamcode.opmode.teleop;

import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import org.firstinspires.ftc.robotcore.external.navigation.Pose3D;
import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.common.TeleopBotSimple;
import org.firstinspires.ftc.teamcode.common.hardwareConfiguration.data.GoBilda6000DcMotorData;

@Disabled
@Config
@TeleOp(name = "GameTeleop", group = "Linear OpMode")
public class TeleopNoOdoSimple extends LinearOpMode {
    private TeleopBotSimple bot;
    DcMotorEx intake, launchTest1, launchTest2;
    Servo pusher, launchServo;
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
        double constantAdd = .3;
        double currentPosition = 0;

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



            // Intake
            else if (gamepad1.x){
                intake.setPower(-1);
            }
            else if (gamepad1.b){
                intake.setPower(0);
            }

            // Pusher (temp)
            double pushPosition = pusher.getPosition();
            boolean pressedOnce = false;
            if (gamepad1.right_bumper){
                pusher.setPosition(.65);
            }
            if (gamepad1.left_bumper){
                pusher.setPosition(0);
                currentPosition = 0;

            }

            telemetry.update();
        }
    }
}

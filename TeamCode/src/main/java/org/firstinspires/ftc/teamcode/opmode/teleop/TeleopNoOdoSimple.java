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

import org.firstinspires.ftc.teamcode.common.Bot;
import org.firstinspires.ftc.teamcode.common.TeleopBotSimple;
import org.firstinspires.ftc.teamcode.common.hardwareConfiguration.data.GoBilda6000DcMotorData;

@Config
@TeleOp(name = "Teleop - No Odo Simple", group = "Linear OpMode")
public class TeleopNoOdoSimple extends LinearOpMode {
    private TeleopBotSimple bot;

    private Bot b;

    double launcherTicksPerSecond;
    ElapsedTime aimerTimer = new ElapsedTime();
    private final static double ADJUSTMENT_DELAY = 50;
    boolean pressed;

    @Override
    public void runOpMode() {
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
        bot = new TeleopBotSimple(this, telemetry);
         b=new Bot(telemetry);
         b.init(this);

        double constantAdd = .3;
        double currentPosition = 0;
        double position;

        waitForStart();
        aimerTimer.reset();
        while (opModeIsActive() && !isStopRequested()) {
            bot.processDrivetrainInput(gamepad1);

            // Launcher
            if (gamepad1.right_trigger >= .2)
            {
                b.stopLaunchMotors();
            }


            if (gamepad1.left_trigger >= .2)
            {
                b.startLaunchMotors();
            }


            if (gamepad1.a)
            {
                b.intakeMotorStart();
            }

            if (gamepad1.b)
            {
                b.intakeMotorStop();
            }

            if (gamepad1.right_bumper) {
                b.moveAimerUp(0.05);
            }

            if (gamepad1.left_bumper) {
                b.moveAimerDown(0.05);
            }

            if (gamepad1.x) {
                b.fullPushBlackWheel();
            }

            if (gamepad1.y) {
                b.fullIntakeCycle();
            }

            telemetry.update();
            sleep(100);
        }
    }
}

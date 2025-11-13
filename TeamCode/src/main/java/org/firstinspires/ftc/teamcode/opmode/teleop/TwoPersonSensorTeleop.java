package org.firstinspires.ftc.teamcode.opmode.teleop;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.common.Bot;
import org.firstinspires.ftc.teamcode.common.TeleopBotSimple;

@Config
@TeleOp(name = "TwoPersonSensorTeleop", group = "Linear OpMode")
public class TwoPersonSensorTeleop extends LinearOpMode {
    private TeleopBotSimple bot;

    private Bot b;

    double launcherTicksPerSecond;
    ElapsedTime aimerTimer = new ElapsedTime();
    private final static double ADJUSTMENT_DELAY = 50;
    boolean pressed;

    boolean ballIntaked = false;

    int totalBallsPossessed = 0;

    int ballOne = 0;
    int ballTwo = 0;
    int ballThree = 0;

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

            if (gamepad1.left_trigger >= .2) {
                b.startLaunchMotors(0.5);
                b.setAimerZero();
            } else if (gamepad1.right_trigger >= .2) {
                b.startLaunchMotors(0.55);
                b.setAimerZero();
            } else {
                b.stopLaunchMotors();
            }



            if (gamepad1.a) {
                b.intakeMotorStart();
            }

            if (gamepad1.b) {
                b.intakeMotorStop();
            }
//
//            if (gamepad1.right_bumper) {
//                b.moveAimerUp(0.05);
//            }
//
//            if (gamepad1.left_bumper) {
//                b.moveAimerDown(0.05);
//            }

            if (gamepad2.y) {
                if (totalBallsPossessed >= 1) {
                    totalBallsPossessed = totalBallsPossessed - 1;
                } else {
                    telemetry.addLine("Critical Failure Balls Possessed = 0!");
                }
            }

            if (gamepad2.x) {
                if (totalBallsPossessed < 3) {
                    totalBallsPossessed = totalBallsPossessed + 1;
                } else {
                    telemetry.addLine("Critical Failure Balls Possessed is more than 3!");
                }
            }

            if (gamepad2.a) {
                totalBallsPossessed = 0;
                telemetry.addLine("Balls Possessed is now 0");
            }

            if (gamepad1.y) {
                b.stickActivate();
            }


            if (gamepad1.x && !pressed) {
                b.toggleBlackWheel();
                pressed = true;
            }

            if (!gamepad1.x) {
                pressed = false;
            }

            b.updateStick();
            telemetry.addLine("Press A to Fully Reset Ball Counter!");
            telemetry.addLine("Press X to Say That Ball Was Intaked!");
            telemetry.addLine("Press Y to Say That a Ball Was Launched!");
            telemetry.addData("Balls Possessed: ", totalBallsPossessed);
            telemetry.update();
            sleep(100);
        }
    }
}

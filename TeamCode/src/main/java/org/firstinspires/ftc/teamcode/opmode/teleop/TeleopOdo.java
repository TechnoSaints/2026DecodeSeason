package org.firstinspires.ftc.teamcode.opmode.teleop;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.pedropathing.geometry.Pose;

@Config
@TeleOp(name = "TeleopGame", group = "Linear OpMode")
public class TeleopOdo extends LinearOpMode {
    private TeleopBot bot;
    protected Pose startPose;
    @Override
    public void runOpMode() {
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
        startPose = new Pose();
        bot = new TeleopBot(this, telemetry, startPose);
        telemetry.addData("Start Pose", startPose.toString());
        telemetry.update();

        waitForStart();
        bot.startOdoTeleop();
        while (opModeIsActive() && !isStopRequested()) {
            bot.processGamepadInput(gamepad1);
            telemetry.addData("Heading", startPose.getHeading());
            telemetry.addData("X", startPose.getX());
            telemetry.addData("Y", startPose.getY());
            telemetry.update();
            bot.update();
        }
    }
}

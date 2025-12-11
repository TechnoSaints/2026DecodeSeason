package org.firstinspires.ftc.teamcode.opmode.teleop;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.pedropathing.geometry.Pose;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.common.TeleopBot;
import org.firstinspires.ftc.teamcode.opmode.FieldLocations;

@Config
@TeleOp(name = "TeleopLongBlue", group = "Linear OpMode")
public class TeleopLongBlue extends LinearOpMode {
    private TeleopBot bot;

    @Override
    public void runOpMode() {
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
        bot = new TeleopBot(this, telemetry, FieldLocations.endPose, FieldLocations.goalLeftPose);

        waitForStart();
        while (opModeIsActive() && !isStopRequested()) {
            bot.processGamepadInput(gamepad1);
            bot.update();
        }}}

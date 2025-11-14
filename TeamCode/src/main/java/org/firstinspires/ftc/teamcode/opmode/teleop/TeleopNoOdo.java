package org.firstinspires.ftc.teamcode.opmode.teleop;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.common.Modes;
import org.firstinspires.ftc.teamcode.common.TeleopBot;

@Config
@TeleOp(name = "Teleop9800Decode", group = "Linear OpMode")
public class TeleopNoOdo extends LinearOpMode {
    private TeleopBot bot;

    @Override
    public void runOpMode() {
        double driveAxial = 0.0;
        double driveStrafe = 0.0;
        double driveYaw = 0.0;

        boolean intakePressed = false;

        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
        bot = new TeleopBot(this, telemetry);

        waitForStart();
        while (opModeIsActive() && !isStopRequested()) {
           bot.processDrivetrainInput(gamepad1);
    }}}

package org.firstinspires.ftc.teamcode.opmode.auto;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;

import org.firstinspires.ftc.teamcode.opmode.teleop.TeleopBot;

@Config
@TeleOp(name = "BasicAuto", group = "Linear OpMode")
public class BasicAuto extends LinearOpMode {
    private TeleopBot bot;

    private final DcMotorEx leftFrontDrive;
    private final DcMotorEx rightFrontDrive;
    private final DcMotorEx leftBackDrive;
    private final DcMotorEx rightBackDrive;

    public BasicAuto(DcMotorEx leftFrontDrive, DcMotorEx rightFrontDrive, DcMotorEx leftBackDrive, DcMotorEx rightBackDrive) {
        this.leftFrontDrive = leftFrontDrive;
        this.rightFrontDrive = rightFrontDrive;
        this.leftBackDrive = leftBackDrive;
        this.rightBackDrive = rightBackDrive;
    }


    @Override
    public void runOpMode() {
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
        bot = new TeleopBot(this, telemetry);


        waitForStart();
        while (opModeIsActive() && !isStopRequested()) {

            bot.update();
        }
    }
}

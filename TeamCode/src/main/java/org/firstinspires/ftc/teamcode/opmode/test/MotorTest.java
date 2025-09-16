package org.firstinspires.ftc.teamcode.opmode.test;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.common.Extendo;
import org.firstinspires.ftc.teamcode.common.TeleopBot;

@Config
@TeleOp(name = "MotorTest", group = "Test")
@Disabled
public class MotorTest extends LinearOpMode {

    private DcMotor motor;
  @Override
    public void runOpMode() {
        motor = hardwareMap.get(DcMotor.class, "intake");


      waitForStart();

        while (opModeIsActive() && !isStopRequested()) {
            motor.setPower(gamepad1.right_stick_y);
        }
    }
}

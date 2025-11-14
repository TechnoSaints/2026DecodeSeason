package org.firstinspires.ftc.teamcode.opmode.test;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@Disabled

@Config
@TeleOp(name = "MotorTest", group = "Test")
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

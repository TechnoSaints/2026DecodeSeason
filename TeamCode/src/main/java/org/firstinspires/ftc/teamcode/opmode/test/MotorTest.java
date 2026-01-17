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

    private DcMotor motor1, motor2;

    @Override
    public void runOpMode() {
        motor1 = hardwareMap.get(DcMotor.class, "motor1");
        motor2 = hardwareMap.get(DcMotor.class, "motor2");


        waitForStart();

        while (opModeIsActive() && !isStopRequested()) {
            motor1.setPower(1.0);
            motor2.setPower(1.0);
        }
    }
}

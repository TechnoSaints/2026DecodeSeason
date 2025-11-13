package org.firstinspires.ftc.teamcode.opmode.test;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name="Continuous Servo Test", group = "test")
public class ContinuousServoTest extends LinearOpMode {

    private CRServo servo;
    public void runOpMode() throws InterruptedException {
        servo = hardwareMap.get(CRServo.class, "test");
        waitForStart();
        while (opModeIsActive()) {
            servo.setPower(gamepad1.right_trigger - gamepad1.left_trigger);
        }
    }
}

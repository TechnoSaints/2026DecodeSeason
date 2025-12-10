package org.firstinspires.ftc.teamcode.opmode.test;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.TouchSensor;

@Config
@TeleOp(name="Switch test", group = "test")
public class SwitchTest extends LinearOpMode{
    private TouchSensor ball0, ball1, ball2;
    @Override
    public void runOpMode(){
        ball0 = hardwareMap.get(TouchSensor.class, "ball0");
        ball1 = hardwareMap.get(TouchSensor.class, "ball1");
        ball2 = hardwareMap.get(TouchSensor.class, "ball2");
        waitForStart();
        while (opModeIsActive() && !isStopRequested()){
            telemetry.addData("ball0", ball0.isPressed());
            telemetry.addData("ball1", ball1.isPressed());
            telemetry.addData("ball2", ball2.isPressed());
            telemetry.update();
        }
    }
}

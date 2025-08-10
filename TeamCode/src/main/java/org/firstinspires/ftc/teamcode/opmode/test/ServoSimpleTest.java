package org.firstinspires.ftc.teamcode.opmode.test;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.common.hardwareConfiguration.positions.HandlerGrabberPositions;
import org.firstinspires.ftc.teamcode.common.servos.ServoSimple;

@Config
@TeleOp(name = "ServoTest", group = "Test")
@Disabled
public class ServoSimpleTest extends LinearOpMode {

    private ServoSimple servo;

    @Override
    public void runOpMode() {
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());

        servo = new ServoSimple(hardwareMap, telemetry, "extendo");
//        servo = hardwareMap.get(Servo.class,"handlerGrabber");

        waitForStart();

        while (opModeIsActive() && !isStopRequested()) {
            if (gamepad1.right_bumper) {
                servo.setPositionTicks(HandlerGrabberPositions.OPEN.getValue(), 0);
            } else if (gamepad1.left_bumper) {
                servo.setPositionTicks(HandlerGrabberPositions.CLOSED_LOOSE.getValue(), 0);
            }
        }
    }
}

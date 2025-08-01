package org.firstinspires.ftc.teamcode.opmode.test;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.common.hardwareConfiguration.positions.IntakeSwivelPositions;
import org.firstinspires.ftc.teamcode.common.hardwareConfiguration.positions.IntakeWristPositions;
import org.firstinspires.ftc.teamcode.common.servos.ServoSpeedLimited;

@Config
@TeleOp(name = "ServoSpeedLimitedTest", group = "Test")
@Disabled

public class ServoSpeedLimitedTest extends LinearOpMode {

    private ServoSpeedLimited servo = new ServoSpeedLimited(hardwareMap, telemetry, "handlerArmServo");

    @Override
    public void runOpMode() {
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());

        waitForStart();

        while (opModeIsActive() && !isStopRequested()) {
            if (gamepad1.right_bumper) {
                servo.goToPositionTicks(1.0, 5);
            } else if (gamepad1.left_bumper) {
                servo.goToPositionTicks(-1.0, 50);
            }
        }
    }
}

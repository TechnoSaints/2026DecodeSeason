package org.firstinspires.ftc.teamcode.opmode.test;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.common.LiftSingle;
import org.firstinspires.ftc.teamcode.common.hardwareConfiguration.setPoints.LiftPositions;

@Config
@TeleOp(name = "LiftPresetTest", group = "Test")

public class LiftSinglePresetTest extends LinearOpMode {

    private LiftSingle lift;

    @Override
    public void runOpMode() {
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
        lift = new LiftSingle(hardwareMap, telemetry, "lift", false);
        waitForStart();

        while (opModeIsActive() && !isStopRequested()) {
            if (gamepad1.right_trigger > 0.2) {
                lift.setPositionPreset(LiftPositions.HIGH_BUCKET_TELEOP);
            } else if (gamepad1.left_trigger > 0.2) {
                lift.setPositionPreset(LiftPositions.MIN);
            }
            lift.log();
        }
    }
}

package org.firstinspires.ftc.teamcode.opmode.test;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.common.LiftDouble;
import org.firstinspires.ftc.teamcode.common.LiftSingle;
import org.firstinspires.ftc.teamcode.common.hardwareConfiguration.positions.LiftPositions;

@Config
@TeleOp(name = "LiftDoublePresetTest", group = "Test")

public class LiftDoublePresetTest extends LinearOpMode {

    private LiftDouble lift;

    @Override
    public void runOpMode() {
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
        lift = new LiftDouble(hardwareMap, telemetry, "liftL","liftR", false);
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

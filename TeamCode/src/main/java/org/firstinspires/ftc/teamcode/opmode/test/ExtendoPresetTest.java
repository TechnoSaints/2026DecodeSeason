package org.firstinspires.ftc.teamcode.opmode.test;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.common.Extendo;
import org.firstinspires.ftc.teamcode.common.hardwareConfiguration.setPoints.ExtendoPositions;

@Config
@TeleOp(name = "ExtendoPresetTest", group = "Test")
@Disabled
public class ExtendoPresetTest extends LinearOpMode {

    private Extendo extendo;

    @Override
    public void runOpMode() {
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());

        extendo = new Extendo(hardwareMap, telemetry, "extendo");

        waitForStart();

        while (opModeIsActive() && !isStopRequested()) {
            if (gamepad1.right_bumper) {
                extendo.setMedium();
                extendo.setPositionPreset(ExtendoPositions.EXTENDED);
            } else if (gamepad1.left_bumper) {
                extendo.setFast();
                extendo.setPositionPreset(ExtendoPositions.RETRACTED);
            }
       //     telemetry.addData("isbusy: ", extendo.isBusy());
         //   telemetry.update();
            extendo.update();
        }
    }
}

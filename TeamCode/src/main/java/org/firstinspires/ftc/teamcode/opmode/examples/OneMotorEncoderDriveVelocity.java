package org.firstinspires.ftc.teamcode.opmode.examples;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;

import org.firstinspires.ftc.teamcode.common.hardwareConfiguration.data.GoBilda312DcMotorData;
import org.firstinspires.ftc.teamcode.common.hardwareConfiguration.data.MotorData;

@Config
@TeleOp(name = "OneMotorEncoderDriveVelocity", group = "Examples")
@Disabled
public class OneMotorEncoderDriveVelocity extends LinearOpMode {

    private DcMotorEx motor;
    private final MotorData motorData = new GoBilda312DcMotorData();
    private final double maxMotorRPM = motorData.maxMotorRpm;
    private final double maxTicksPerSec = motorData.maxTicksPerSec;
    private double targetTicksPerSec, targetMotorRPM = 0.0;
    private double incrementRPM = 250.0;

    @Override
    public void runOpMode() {
        motor = hardwareMap.get(DcMotorEx.class, "motor");
        motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motor.setVelocity(targetTicksPerSec);
        waitForStart();

        while (opModeIsActive() && !isStopRequested()) {
            if (gamepad1.y) {
                targetMotorRPM += incrementRPM;
            } else if (gamepad1.a) {
                targetMotorRPM -= incrementRPM;
            }
            motor.setVelocity(targetMotorRPM/maxMotorRPM*maxTicksPerSec);
        }
    }
}

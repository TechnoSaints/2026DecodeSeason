package org.firstinspires.ftc.teamcode.opmode.examples;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.teamcode.common.hardwareConfiguration.data.GoBilda312DcMotorData;
import org.firstinspires.ftc.teamcode.common.hardwareConfiguration.data.MotorData;

@Config
@TeleOp(name = "OneMotorEncoderDriveVelocity", group = "Examples")

public class OneMotorEncoderDriveVelocity extends LinearOpMode {

    private DcMotorEx motor;

    // Data for GoBilda 312 RPM motor
    private final double maxMotorRPM = 6000;
    private final double gearRatio = 19.2;
    private final double ticksPerMotorRev = 28;
    private final double maxTicksPerSec = maxMotorRPM * ticksPerMotorRev * gearRatio;
    private double targetTicksPerSec = 0.0;
    private double ticksPerSecondIncrement = 100.0;

    @Override
    public void runOpMode() {
        motor = hardwareMap.get(DcMotorEx.class, "motor");
        motor.setDirection(DcMotorSimple.Direction.FORWARD);
        motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motor.setVelocity(targetTicksPerSec);
        telemetry.addData("maxTicksPerSec: ", maxTicksPerSec);
        telemetry.update();
        waitForStart();

        while (opModeIsActive() && !isStopRequested()) {
            if (gamepad1.y) {
                if (targetTicksPerSec >= maxTicksPerSec) {
                    targetTicksPerSec = maxTicksPerSec;
                } else
                {
                    targetTicksPerSec += ticksPerSecondIncrement;
                }
            } else if (gamepad1.a) {
                if (targetTicksPerSec <= maxTicksPerSec) {
                    targetTicksPerSec = -maxTicksPerSec;
                } else
                {
                    targetTicksPerSec -= ticksPerSecondIncrement;
                }
            }
            motor.setVelocity(targetTicksPerSec);
            telemetry.addData("Target Ticks Per Second: ",  targetTicksPerSec);
            telemetry.addData("Current Ticks Per Second: ", motor.getVelocity());
            telemetry.update();
            sleep(50);
        }
    }
}

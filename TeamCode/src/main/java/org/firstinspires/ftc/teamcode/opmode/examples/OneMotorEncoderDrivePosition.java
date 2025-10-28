package org.firstinspires.ftc.teamcode.opmode.examples;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@Config
@TeleOp(name = "OneMotorEncoderDriveVelocity", group = "Examples")

public class OneMotorEncoderDrivePosition extends LinearOpMode {

    private DcMotorEx motor;

    // Data for GoBilda 312 RPM motor
    private final double maxMotorRPM = 6000;
    private final double gearRatio = 19.2;
    private final double ticksPerMotorRev = 28;
    private final double ticksPerWheelRev = ticksPerMotorRev * gearRatio;

    // GoBilda mecanum wheels
    private final double wheelDiameterInches = 104 / 25.4;

    // Determine how many ticks required to move forward one inch.
    private final double wheelCircumferenceInches = wheelDiameterInches * Math.PI;
    private final double ticksPerInch = ticksPerWheelRev / wheelCircumferenceInches;

    private double targetInches = 0.0;
    private double targetInchesIncrement = 12.0;
    private double targetTicks;
    private double motorPower = 0.5;

    @Override
    public void runOpMode() {
        motor = hardwareMap.get(DcMotorEx.class, "motor");
        motor.setPower(motorPower);
        motor.setDirection(DcMotorSimple.Direction.FORWARD);
        motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        telemetry.addData("ticksPerInch: ", ticksPerInch);
        telemetry.update();
        waitForStart();

        while (opModeIsActive() && !isStopRequested()) {
            if (gamepad1.y) {
                targetInches += targetInchesIncrement;
            } else if (gamepad1.a) {
                targetInches -= targetInchesIncrement;
            }
            targetTicks = targetInches * ticksPerInch;
            motor.setTargetPosition((int)targetTicks);
            telemetry.addData("targetInches: ", targetInches);
            telemetry.addData("targetTicks: ", targetTicks);
            telemetry.addData("Current Position Inches: ", motor.getCurrentPosition()/ticksPerInch);
            telemetry.addData("Current Position Ticks: ", motor.getCurrentPosition());
            telemetry.update();
            sleep(50);
        }
    }
}

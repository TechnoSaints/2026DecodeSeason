package org.firstinspires.ftc.teamcode.opmode.tuning;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.CurrentUnit;
import org.firstinspires.ftc.teamcode.common.Modes;
import org.firstinspires.ftc.teamcode.common.TeleopBot;
import org.firstinspires.ftc.teamcode.common.hardwareConfiguration.data.GoBilda435DcMotorData;
import org.firstinspires.ftc.teamcode.common.hardwareConfiguration.positions.HandlerArmPositions;

@Config
@TeleOp(name = "LiftMotorCurrentTuner", group = "Tuning")
@Disabled
public class LiftMotorCurrentTuner extends LinearOpMode {

    private DcMotorEx motor;
    private ElapsedTime timer = new ElapsedTime(ElapsedTime.Resolution.MILLISECONDS);

    @Override
    public void runOpMode() {
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
        motor = hardwareMap.get(DcMotorEx.class, "lift");
        motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        int targetVelocity = 500;
        waitForStart();
        while (opModeIsActive() && !isStopRequested()) {
            if (gamepad1.right_bumper) {
                motor.setVelocity(targetVelocity);
            } else if (gamepad1.left_bumper) {
                motor.setVelocity(-targetVelocity);
            } else {
                motor.setVelocity(0.0);
            }
            telemetry.addData("current: ", motor.getCurrent(CurrentUnit.AMPS));
            telemetry.update();
        }
    }
}

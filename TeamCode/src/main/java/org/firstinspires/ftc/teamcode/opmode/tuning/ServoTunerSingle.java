package org.firstinspires.ftc.teamcode.opmode.tuning;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp(name = "Servo Tuner Single", group = "Tuning")

public class ServoTunerSingle extends LinearOpMode {

    static final double INCREMENT = 0.01;     // amount to slew servo each CYCLE_MS cycle
    static final int CYCLE_MS = 50;     // period of each cycle
    static final double MAX_POS = 1.0;     // Maximum rotational position
    static final double MIN_POS = 0.0;     // Minimum rotational position

    // Define class members
    Servo servo;
    CRServo continuous;
    double position = (MAX_POS - MIN_POS) / 2; // Start at halfway position

    @Override
    public void runOpMode() {

        // Connect to servo (Assume Robot Left Hand)
        // Change the text in quotes to match any servo name on your robot.
        servo = hardwareMap.get(Servo.class, "pusher");
        continuous = hardwareMap.get(CRServo.class, "test");

        // Wait for the start button
        telemetry.addData(">", "Press Start to tune servo.");
        telemetry.update();
        waitForStart();

        while (opModeIsActive() && !isStopRequested()) {
            if (gamepad1.right_bumper) {
                position += INCREMENT;
                if (position >= MAX_POS) {
                    position = MAX_POS;
                }
            } else if (gamepad1.left_bumper) {
                position -= INCREMENT;
                if (position <= MIN_POS) {
                    position = MIN_POS;
                }
            }
            continuous.setPower(gamepad1.right_trigger - gamepad1.left_trigger);
            // Display the current value
            telemetry.addData("Servo Position:", "%5.2f", position);
            telemetry.addLine("Right Bumper to Increase");
            telemetry.addLine("Left Bumper to Decrease");
            telemetry.update();
            // Set the servo to the new position and pause;
            servo.setPosition(position);
        }
    }
}


package org.firstinspires.ftc.teamcode.opmode.test;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.AnalogInput;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name = "Test Servo with Encoder", group = "Test")
@Disabled
public class EncoderTest extends LinearOpMode {

    static final double INCREMENT = 0.01;     // amount to slew servo each CYCLE_MS cycle
    static final int CYCLE_MS = 50;     // period of each cycle
    static final double MAX_POS = 1.0;     // Maximum rotational position
    static final double MIN_POS = 0.0;     // Minimum rotational position

    // Define class members
    Servo servo;
    AnalogInput encoder;
    double position = (MAX_POS - MIN_POS) / 2; // Start at halfway position

    @Override
    public void runOpMode() {

        // Connect to servo (Assume Robot Left Hand)
        // Change the text in quotes to match any servo name on your robot.
        servo = hardwareMap.get(Servo.class, "testServo");
        encoder = hardwareMap.get(AnalogInput.class, "testServoEncoder");
        // Wait for the start button
        telemetry.addData(">", "Press Start to move servo.");
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

            // Set the servo to the new position and pause;
            servo.setPosition(position);
            log();
            sleep(CYCLE_MS);
        }
    }

    private double getEncoderPosition() {
        double distanceFromMiddle = 0.5 - (encoder.getVoltage() / 3.3);
        double distanceScaled = distanceFromMiddle / 0.5203;
        return (0.5 + distanceScaled);
    }

    public void log()
    {
        telemetry.addData("Servo Setting: ", "%5.2f", position);
        telemetry.addData("Encoder Reading: ", getEncoderPosition());
        telemetry.addLine("Right Bumper to Increase");
        telemetry.addLine("Left Bumper to Decrease");
        telemetry.update();
    }
}


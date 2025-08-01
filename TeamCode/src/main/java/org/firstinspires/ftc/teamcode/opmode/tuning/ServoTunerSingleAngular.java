package org.firstinspires.ftc.teamcode.opmode.tuning;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.common.hardwareConfiguration.data.ExtendoData;
import org.firstinspires.ftc.teamcode.common.servos.ServoAngular;

@TeleOp(name = "Servo Tuner Angular", group = "Tuning")
@Disabled

public class ServoTunerSingleAngular extends LinearOpMode {
    static final double INCREMENT = 0.1;     // amount to slew servo each CYCLE_MS cycle
    static final int CYCLE_MS = 5;     // period of each cycle
    static final double MAX_POS_DEGREES = 180;     // Maximum rotational position
    static final double MAX_POS_TICKS = 0.22;     // Maximum rotational position
    static final double MIN_POS_DEGREES = 0.0;     // Minimum rotational position
    static final double MIN_POS_TICKS = 0.90;     // Maximum rotational position

    // Define class members
    ServoAngular servo;
    double positionDegrees = (MAX_POS_DEGREES - MIN_POS_DEGREES) / 2; // Start at halfway position

    @Override
    public void runOpMode() {

        servo = new ServoAngular(hardwareMap, telemetry, "testServo", MIN_POS_DEGREES, MIN_POS_TICKS, MAX_POS_DEGREES, MAX_POS_TICKS);

        // Wait for the start button
        telemetry.addData(">", "Press Start to tune servo.");
        telemetry.update();
        waitForStart();

        while (opModeIsActive() && !isStopRequested()) {
            if (gamepad1.right_bumper) {
                positionDegrees += INCREMENT;
                if (positionDegrees >= MAX_POS_DEGREES) {
                    positionDegrees = MAX_POS_DEGREES;
                }
            } else if (gamepad1.left_bumper) {
                positionDegrees -= INCREMENT;
                if (positionDegrees <= MIN_POS_DEGREES) {
                    positionDegrees = MIN_POS_DEGREES;
                }
            }

            // Display the current value
            telemetry.addData("Servo Degrees:", "%5.2f", positionDegrees);
            telemetry.addLine("Right Bumper to Increase");
            telemetry.addLine("Left Bumper to Decrease");
            telemetry.update();
            // Set the servo to the new position and pause;
            servo.setPositionDegrees(positionDegrees,0);
            sleep(CYCLE_MS);
        }
    }
}


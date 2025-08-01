package org.firstinspires.ftc.teamcode.opmode.test;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.common.hardwareConfiguration.positions.IntakeSwivelPositions;

@TeleOp(name = "Intake Swivel Test", group = "Test")
@Disabled
public class IntakeSwivelTest extends LinearOpMode {
    // Define class members
    Servo servo;
    double positionTicks = IntakeSwivelPositions.DEGREES0.getValue();
    double positionRangeTicks = IntakeSwivelPositions.DEGREES180.getValue() - IntakeSwivelPositions.DEGREES0.getValue();
    double positionTicksPerDegree = positionRangeTicks/180;
    double positionDegrees = 0;

    @Override
    public void runOpMode() {

        // Connect to servo (Assume Robot Left Hand)
        // Change the text in quotes to match any servo name on your robot.
        servo = hardwareMap.get(Servo.class, "extendo");

        // Wait for the start button
        telemetry.addData(">", "Press Start to tune servo.");
        telemetry.update();
        waitForStart();

        while (opModeIsActive() && !isStopRequested()) {
            if (gamepad1.right_bumper) {
                positionTicks += positionTicksPerDegree;
                positionDegrees++;
                if (positionTicks <= IntakeSwivelPositions.DEGREES180.getValue()) {
                    positionTicks = IntakeSwivelPositions.DEGREES180.getValue();
                    positionDegrees = 180;
                }
            } else if (gamepad1.left_bumper) {
                positionTicks -= positionTicksPerDegree;
                positionDegrees--;
                if (positionTicks >= IntakeSwivelPositions.DEGREES0.getValue()) {
                    positionTicks = IntakeSwivelPositions.DEGREES0.getValue();
                    positionDegrees = 0;
                }
            }

            // Display the current value
            telemetry.addData("Servo Position (degrees):", "%5.2f", positionDegrees);
            telemetry.addData("Servo Position (ticks):", "%5.2f", positionTicks);
            telemetry.addLine("Right Bumper to Increase");
            telemetry.addLine("Left Bumper to Decrease");
            telemetry.update();
            // Set the servo to the new position and pause;
            servo.setPosition(positionTicks);
            sleep(50);
        }
    }
}


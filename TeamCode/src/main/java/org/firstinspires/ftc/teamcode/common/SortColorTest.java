package org.firstinspires.ftc.teamcode.common;


import android.graphics.Color;
import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;


@Config
@TeleOp(name = "Color Sensor Test", group = "Linear OpMode")
public class SortColorTest extends LinearOpMode {

    // Define the sensor
    ColorSensor colorSensor;
    DistanceSensor distanceSensor;

    ColorSensor colorSensorTop;
    DistanceSensor distanceSensorTop;

    ColorSensor colorSensorShoot;
    DistanceSensor distanceSensorShoot;

    // HSV values are useful for color detection
    float hsvValues[] = {0F, 0F, 0F};

    // Define the range for purple and green hues (these are example values, calibrate them for your specific environment)
    private int purpleHueMin = 165;
    private int purpleHueMax = 240;
    private int greenHueMin = 150;
    private int greenHueMax = 163;
    private float saturationMin = 0.45f;

    @Override
    public void runOpMode() {
        colorSensor = hardwareMap.get(ColorSensor.class, "intakeSensor");
        distanceSensor = hardwareMap.get(DistanceSensor.class, "intakeSensor");



        // Optional: Turn the white LED on (consult the REV documentation for exact method if this one doesn't work)
        // colorSensor.enableLed(true);

        // Wait for the start button
        waitForStart();

        while (opModeIsActive()) {

            // Convert the RGB values to HSV values
            // Use the saturation and value for more robust detection
            Color.RGBToHSV((int) (colorSensor.red() * 255),
                    (int) (colorSensor.green() * 255),
                    (int) (colorSensor.blue() * 255),
                    hsvValues);

            // Check the color
            if (isPurple(hsvValues)) {
                telemetry.addData("Detected Color", "Purple Ball");
            } else if (isGreen(hsvValues)) {
                telemetry.addData("Detected Color", "Green Ball");
            } else {
                telemetry.addData("Detected Color", "Unknown");
            }

            // Display values for debugging and calibration
            telemetry.addData("Hue", "%.3f", hsvValues[0]);
            telemetry.addData("Saturation", "%.3f", hsvValues[1]);
            telemetry.addData("Value", "%.3f", hsvValues[2]);
            telemetry.addData("Distance (cm)", "%.3f", distanceSensor.getDistance(DistanceUnit.CM));
            telemetry.update();
        }
    }

    // Helper method to check for purple
    protected boolean isPurple(float[] hsv) {
        // Adjust these ranges after calibration
        return (hsv[0] >= purpleHueMin && hsv[0] <= purpleHueMax) && hsv[1] >= saturationMin;
    }

    // Helper method to check for green
    protected boolean isGreen(float[] hsv) {
        // Adjust these ranges after calibration
        return (hsv[0] >= greenHueMin && hsv[0] <= greenHueMax) && hsv[1] >= saturationMin;
    }
}

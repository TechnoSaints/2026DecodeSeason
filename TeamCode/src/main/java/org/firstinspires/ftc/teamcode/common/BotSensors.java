package org.firstinspires.ftc.teamcode.common;

import android.graphics.Color;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

@Config
public class BotSensors {
    private final ColorSensor intakeColor;
    private final ColorSensor topColor;
    private final ColorSensor shotColor;

    private final float[] hsvIntake = {0f, 0f, 0f};
    private final float[] hsvTop = {0f, 0f, 0f};
    private final float[] hsvShot = {0f, 0f, 0f};

    public static float minSaturation = 0.45f;
    private static float minValue = 0.18f;

    // purple values need tuning
    public static int minPurpleHue = 165;
    public static int maxPurpleHue = 240;

    public static int minGreenHue = 150;
    public static int maxGreenHue = 163;

    Telemetry telemetry;

    public BotSensors(HardwareMap hardwareMap, OpMode opMode, Telemetry telem) {
        telemetry = new MultipleTelemetry(telem, FtcDashboard.getInstance().getTelemetry());
        intakeColor = hardwareMap.get(ColorSensor.class, "intakeSensor");
        topColor = hardwareMap.get(ColorSensor.class, "topSensor");
        shotColor = hardwareMap.get(ColorSensor.class, "shotSensor");
    }

    public boolean ballInIntake() {
        updateHSV(intakeColor, hsvIntake);
        return isBall(hsvIntake);
    }

    public boolean ballInTop() {
        updateHSV(topColor, hsvTop);
        return isBall(hsvTop);
    }

    public boolean ballInShot() {
        updateHSV(shotColor, hsvShot);
        return isBall(hsvShot);
    }

    public boolean intakeIsPurple() {
        updateHSV(intakeColor, hsvIntake);
        return isPurple(hsvIntake);
    }
    public boolean intakeIsGreen() {
        updateHSV(intakeColor, hsvIntake);
        return isGreen(hsvIntake);
    }

    public boolean topIsPurple() {
        updateHSV(topColor, hsvTop);
        return isPurple(hsvTop);
    }

    public boolean topIsGreen() {
        updateHSV(topColor, hsvTop);
        return isGreen(hsvTop);
    }

    public boolean shotIsPurple() {
        updateHSV(shotColor, hsvShot);
        return isPurple(hsvShot);
    }

    public boolean shotIsGreen() {
        updateHSV(shotColor, hsvShot);
        return isGreen(hsvShot);
    }

    private void updateHSV(ColorSensor sensor, float[] hsv) {
        Color.RGBToHSV(
                (int) (sensor.red() * 255),
                (int) (sensor.green() * 255),
                (int) (sensor.blue() * 255),
                hsv
        );
    }

    private boolean isPurple(float[] hsv) {
        return hsv[0] >= minPurpleHue &&
                hsv[0] <= maxPurpleHue &&
                hsv[1] >= minSaturation &&
                hsv[2] >= minValue;
    }

    private boolean isGreen(float[] hsv) {
        return hsv[0] >= minGreenHue &&
                hsv[0] <= maxGreenHue &&
                hsv[1] >= minSaturation &&
                hsv[2] >= minValue;
    }

    private boolean isBall(float[] hsv) {
        return (isGreen(hsv) || isPurple(hsv));
    }


    public void log() {
        telemetry.addData("Intake", ballInIntake());
        if (intakeIsPurple()) {
            telemetry.addData("Intake is", "purple");
        } else if (intakeIsGreen()) {
            telemetry.addData("Intake is", "green");
        } else {
            telemetry.addData("Intake is", "empty");
        }

        telemetry.addData("Top", ballInTop());
        if (topIsPurple()) {
            telemetry.addData("Top is", "purple");
        } else if (topIsGreen()) {
            telemetry.addData("Top is", "green");
        } else {
            telemetry.addData("Top is", "empty");
        }

        telemetry.addData("Shot", ballInShot());
        if (shotIsPurple()) {
            telemetry.addData("Shot is", "purple");
        } else if (shotIsGreen()) {
            telemetry.addData("Shot is", "green");
        } else {
            telemetry.addData("Shot is", "empty");
        }
    }
}

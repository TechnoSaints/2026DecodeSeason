package org.firstinspires.ftc.teamcode.common;

import android.graphics.Color;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

@Config
public class BotSensors {
    private final ColorSensor intakeColor;
    private final ColorSensor topColor;
    private final ColorSensor shotColor;
    private final ColorSensor midwayColor;
    private final ColorSensor firstColor;

    DistanceSensor intakeSensor;
    DistanceSensor topSensor;
    DistanceSensor shotSensor;
    DistanceSensor midwaySensor;
    DistanceSensor firstSensor;

    private final float[] hsvIntake = {0f, 0f, 0f};
    private final float[] hsvTop = {0f, 0f, 0f};
    private final float[] hsvShot = {0f, 0f, 0f};
    private final float[] hsvMidway = {0f, 0f, 0f};
    private final float[] hsvFirst = {0f, 0f, 0f};

    public static float minSaturation = 0.35f;
    private static float minValue = 0.18f;

    // purple values need tuning
    public static int minPurpleHue = 200;
    public static int maxPurpleHue = 240;

    public static int minGreenHue = 150;
    public static int maxGreenHue = 163;

    Telemetry telemetry;

    public BotSensors(HardwareMap hardwareMap, OpMode opMode, Telemetry telem) {
        telemetry = new MultipleTelemetry(telem, FtcDashboard.getInstance().getTelemetry());
        intakeColor = hardwareMap.get(ColorSensor.class, "intakeSensor");
        topColor = hardwareMap.get(ColorSensor.class, "topSensor");
        shotColor = hardwareMap.get(ColorSensor.class, "shotSensor");
        midwayColor = hardwareMap.get(ColorSensor.class, "midwaySensor");
        firstColor = hardwareMap.get(ColorSensor.class, "firstSensor");

        intakeSensor = hardwareMap.get(DistanceSensor.class, "intakeSensor");
        topSensor = hardwareMap.get(DistanceSensor.class, "topSensor");
        shotSensor = hardwareMap.get(DistanceSensor.class, "shotSensor");
        midwaySensor = hardwareMap.get(DistanceSensor.class, "midwaySensor");
        firstSensor  = hardwareMap.get(DistanceSensor.class, "firstSensor");
    }

    public boolean ballInIntake() {
        updateHSV(intakeColor, hsvIntake);
        return isBall(hsvIntake, intakeSensor);
    }

    public boolean ballInTop() {
        updateHSV(topColor, hsvTop);
        return isBall(hsvTop, topSensor);
    }

    public boolean ballInShot() {
        updateHSV(shotColor, hsvShot);
        return isBall(hsvShot, shotSensor);
    }

    public boolean ballInMidwaySensor() {
        updateHSV(midwayColor, hsvMidway);
        return isBall(hsvMidway, midwaySensor);
    }

    public boolean ballInFirstSensor() {
        updateHSV(firstColor, hsvFirst);
        return isBall(hsvFirst, firstSensor);
    }

    public boolean intakeIsPurple() {
        updateHSV(intakeColor, hsvIntake);
        if(intakeSensor.getDistance(DistanceUnit.CM) <= 5) {
            return isPurple(hsvIntake);
        }
        return false;
    }
    public boolean intakeIsGreen() {
        updateHSV(intakeColor, hsvIntake);
        if(intakeSensor.getDistance(DistanceUnit.CM) <= 5) {
            return isGreen(hsvIntake);
        }
        return false;
    }

    public boolean topIsPurple() {
        updateHSV(topColor, hsvTop);
        if(topSensor.getDistance(DistanceUnit.CM) <= 5) {
            return isPurple(hsvTop);
        }
        return false;
    }

    public boolean topIsGreen() {
        updateHSV(topColor, hsvTop);
        if(topSensor.getDistance(DistanceUnit.CM) <= 5) {
            return isGreen(hsvTop);
        }
        return false;
    }

    public boolean shotIsPurple() {
        updateHSV(shotColor, hsvShot);
        if(shotSensor.getDistance(DistanceUnit.CM) <= 5) {
            return isPurple(hsvShot);
        }
        return false;
    }

    public boolean shotIsGreen() {
        updateHSV(shotColor, hsvShot);
        if(shotSensor.getDistance(DistanceUnit.CM) <= 5) {
            return isGreen(hsvShot);
        }
        return false;
    }


    public boolean midwayIsPurple() {
        updateHSV(midwayColor, hsvMidway);
        if(midwaySensor.getDistance(DistanceUnit.CM) <= 2) {
            return isPurple(hsvMidway);
        }
        return false;
    }

    public boolean midwayIsGreen() {
        updateHSV(midwayColor, hsvMidway);
        if(midwaySensor.getDistance(DistanceUnit.CM) <= 2) {
            return isGreen(hsvMidway);
        }
        return false;
    }

    public boolean firstIsPurple() {
        updateHSV(firstColor, hsvFirst);
        if(firstSensor.getDistance(DistanceUnit.CM) <= 5) {
            return isPurple(hsvFirst);
        }
        return false;
    }

    public boolean firstIsGreen() {
        updateHSV(firstColor, hsvFirst);
        if(firstSensor.getDistance(DistanceUnit.CM) <= 5) {
            return isGreen(hsvFirst);
        }
        return false;
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

    private boolean isBall(float[] hsv, DistanceSensor sensorName) {
        boolean distanceTrue = false;
        if (sensorName.getDistance(DistanceUnit.CM) <= 2) {
            distanceTrue = true;
        }
        return (isGreen(hsv) || isPurple(hsv)) && distanceTrue;
    }


    public void log() {
        telemetry.addData("First", ballInFirstSensor());
        if (firstIsPurple()) {
            telemetry.addData("First is", "purple");
        } else if (firstIsGreen()) {
            telemetry.addData("First is", "green");
        } else {
            telemetry.addData("First is", "empty");
        }

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

        telemetry.addData("Midway", ballInMidwaySensor());
        if (midwayIsPurple()) {
            telemetry.addData("Midway is", "purple");
        } else if (midwayIsGreen()) {
            telemetry.addData("Midway is", "green");
        } else {
            telemetry.addData("Midway is", "empty");
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

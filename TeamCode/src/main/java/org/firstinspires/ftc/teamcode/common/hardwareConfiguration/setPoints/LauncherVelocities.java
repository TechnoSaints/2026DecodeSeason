package org.firstinspires.ftc.teamcode.common.hardwareConfiguration.setPoints;

public enum LauncherVelocities {
    MAX(1.0),
    MEDIUM(0.5),
    MIN(0.0);

    private double value;

    LauncherVelocities(double value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }
}



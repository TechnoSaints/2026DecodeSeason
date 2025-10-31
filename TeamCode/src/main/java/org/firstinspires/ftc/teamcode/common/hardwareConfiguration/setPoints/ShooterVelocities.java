package org.firstinspires.ftc.teamcode.common.hardwareConfiguration.setPoints;

public enum ShooterVelocities {
    MAX(1.0),
    MEDIUM(0.5),
    MIN(0.0);

    private double value;

    ShooterVelocities(double value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }
}



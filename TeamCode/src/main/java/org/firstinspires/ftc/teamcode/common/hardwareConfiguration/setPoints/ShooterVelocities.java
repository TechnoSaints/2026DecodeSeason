package org.firstinspires.ftc.teamcode.common.hardwareConfiguration.setPoints;

public enum ShooterVelocities {
    MAX(1.0f),
    MEDIUM(0.5f),
    MIN(0.0f);

    private float value;

    ShooterVelocities(float value) {
        this.value = value;
    }

    public float getValue() {
        return value;
    }
}



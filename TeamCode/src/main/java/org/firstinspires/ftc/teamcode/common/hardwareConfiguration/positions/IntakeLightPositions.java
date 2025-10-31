package org.firstinspires.ftc.teamcode.common.hardwareConfiguration.positions;

public enum IntakeLightPositions {
    OFF(0.0),
    LOW(0.25),
    MEDIUM(0.50),
    HIGH(0.75),
    FULL(1.0);
    private double value;

    IntakeLightPositions(double value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }
}

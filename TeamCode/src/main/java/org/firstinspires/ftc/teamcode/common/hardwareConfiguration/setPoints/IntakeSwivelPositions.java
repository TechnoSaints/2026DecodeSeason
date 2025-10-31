package org.firstinspires.ftc.teamcode.common.hardwareConfiguration.setPoints;

public enum IntakeSwivelPositions {
    DEGREES0(0.86),
    DEGREES90(.58),
    DEGREES180(.30);
    private double value;

    IntakeSwivelPositions(double value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }
}

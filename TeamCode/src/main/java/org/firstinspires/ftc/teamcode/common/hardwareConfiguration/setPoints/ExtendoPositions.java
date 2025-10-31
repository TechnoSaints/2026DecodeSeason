package org.firstinspires.ftc.teamcode.common.hardwareConfiguration.setPoints;

public enum ExtendoPositions {
    RETRACTED(4.0),
    AUTO_INTAKING(14),
    EXTENDED(23.5);
    private double value;
    ExtendoPositions(double value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }
}

package org.firstinspires.ftc.teamcode.common.hardwareConfiguration.positions;

public enum HandlerGrabberPositions {
    OPEN(0.70),
    MIDDLE(0.65),
    CLOSED_LOOSE(0.59),
    CLOSED_TIGHT(0.57);
    private double value;
    HandlerGrabberPositions(double value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }
}

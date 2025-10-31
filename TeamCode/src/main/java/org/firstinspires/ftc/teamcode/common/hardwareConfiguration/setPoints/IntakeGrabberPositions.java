package org.firstinspires.ftc.teamcode.common.hardwareConfiguration.setPoints;

public enum IntakeGrabberPositions {
    //    OPEN(0.74),
    OPEN(0.66),
    CLOSED_LOOSE(0.44),
    //    CLOSED_TIGHT(0.52);
    CLOSED_TIGHT(0.42);

    private double value;

    IntakeGrabberPositions(double value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }
}

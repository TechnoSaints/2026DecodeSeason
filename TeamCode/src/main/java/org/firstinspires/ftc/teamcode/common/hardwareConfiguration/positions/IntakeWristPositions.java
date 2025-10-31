package org.firstinspires.ftc.teamcode.common.hardwareConfiguration.positions;

public enum IntakeWristPositions {
    UP(0.74),
    UPPISH(0.69),
    FRONT(0.47),
    LOOK(0.39),
    DOWN(.17);
    private double value;

    IntakeWristPositions(double value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }
}

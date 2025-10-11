package org.firstinspires.ftc.teamcode.common.hardwareConfiguration.positions;

public enum AimerPositions {
    CORNER_SHOT(0.6),
    FAR_SHOT(0.45);
    private double value;
    AimerPositions(double value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }
}

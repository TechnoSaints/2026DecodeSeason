package org.firstinspires.ftc.teamcode.common.hardwareConfiguration.setPoints;

public enum AimerPositions {
    INIT_POSITION(0),
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

package org.firstinspires.ftc.teamcode.common.hardwareConfiguration.setPoints;

public enum HandlerWristPositions {
    SPECIMEN_WALL(0.43),
    HIGH_BUCKET(.61),
    SUB_PARKING(0.53),
    HANDOFF(.45),
    UP_SPECIMEN(0.35),
    SPECIMEN_HANG(0.61);
    private double value;
    HandlerWristPositions(double value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }
}

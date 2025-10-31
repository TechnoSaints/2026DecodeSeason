package org.firstinspires.ftc.teamcode.common.hardwareConfiguration.setPoints;

public enum HandlerArmPositions {
    SPECIMEN_WALL(.04),
    HIGH_BUCKET(.30),
    TOP(.45),
    SUB_PARKING(.65),
    SPECIMEN_HANG(.65),
    AUTO_START(.86),
    HANDOFF(.90);
    private double value;

    HandlerArmPositions(double value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }
}

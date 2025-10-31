package org.firstinspires.ftc.teamcode.common.hardwareConfiguration.setPoints;

public enum LiftPositions {
    MAX(2250),
    HIGH_BUCKET_AUTO(2050),
    HIGH_BUCKET_TELEOP(2195),
    SPECIMEN_HANG_SETUP(120),
    SPECIMEN_HANG(925),
    HANDOFF_SETUP(300),
    HANDOFF(135),
    SPECIMEN_WALL(0),
    MIN(0);

    private int value;
    LiftPositions(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}

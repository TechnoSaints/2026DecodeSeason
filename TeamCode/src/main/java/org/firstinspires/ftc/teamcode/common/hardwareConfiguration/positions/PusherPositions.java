package org.firstinspires.ftc.teamcode.common.hardwareConfiguration.positions;

public enum PusherPositions {
    FIRE(.1),
    OPEN(.72),
    DELAY(500);
    private double value;

    PusherPositions(double value){
        this.value = value;
    }

    public double getValue() {
        return value;
    }
}

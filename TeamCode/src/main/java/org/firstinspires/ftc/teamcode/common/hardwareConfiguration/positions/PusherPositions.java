package org.firstinspires.ftc.teamcode.common.hardwareConfiguration.positions;

public enum PusherPositions {
    LOAD_POSITION(.1),
    FIRE_POSITION(.8);
    private double value;

    PusherPositions(double value){
        this.value = value;
    }

    public double getValue() {
        return value;
    }
}

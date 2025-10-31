package org.firstinspires.ftc.teamcode.common.hardwareConfiguration.data;

public abstract class MotorData {
    public double ticksPerMotorRev = 28.0;
    public double gearRatio;
    public double maxMotorRpm = 6000;
    public double maxMotorRps = maxMotorRpm/60.0;
    public int maxTicksPerSec = Math.toIntExact(Math.round(Math.floor(maxMotorRps*ticksPerMotorRev)));
}




package org.firstinspires.ftc.teamcode.common.hardwareConfiguration.data;

public abstract class MotorData {
    public static double ticksPerMotorRev = 28.0;
    public double gearRatio;
    public static double maxMotorRpm = 6000;
    public static double maxMotorRps = maxMotorRpm/60.0;
    public static int maxTicksPerSec = Math.toIntExact(Math.round(Math.floor(maxMotorRps*ticksPerMotorRev)));
}




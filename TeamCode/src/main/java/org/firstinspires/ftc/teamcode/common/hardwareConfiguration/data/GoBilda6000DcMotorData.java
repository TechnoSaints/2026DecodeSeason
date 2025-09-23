package org.firstinspires.ftc.teamcode.common.hardwareConfiguration.data;

public class GoBilda6000DcMotorData extends MotorData {
    public GoBilda6000DcMotorData() {
        ticksPerMotorRev = 28;
        gearRatio = 1;
        ticksPerGearboxRev = gearRatio * ticksPerMotorRev;
        maxMotorRpm = 5900;
        maxMotorRps = maxMotorRpm / 60.0;
        maxTicksPerSec = maxMotorRps * ticksPerMotorRev;
    }
}
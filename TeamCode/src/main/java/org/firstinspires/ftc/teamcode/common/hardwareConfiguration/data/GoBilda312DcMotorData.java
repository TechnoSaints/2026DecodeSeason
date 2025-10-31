package org.firstinspires.ftc.teamcode.common.hardwareConfiguration.data;

public class GoBilda312DcMotorData extends MotorData {
    public GoBilda312DcMotorData() {
        ticksPerMotorRev = 28;
        gearRatio = 19.2;
        ticksPerGearboxRev = gearRatio * ticksPerMotorRev;
        maxMotorRpm = 5900;
        maxMotorRps = maxMotorRpm / 60.0;
        maxTicksPerSec = maxMotorRps * ticksPerMotorRev;
    }
}




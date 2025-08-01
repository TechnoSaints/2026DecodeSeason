package org.firstinspires.ftc.teamcode.common.hardwareConfiguration.data;

public class GoBilda223DcMotorData extends MotorData {
    public GoBilda223DcMotorData() {
        ticksPerMotorRev = 28;
        gearRatio = 26.9;
        ticksPerGearboxRev = gearRatio * ticksPerMotorRev;
        maxMotorRpm = 5900;
        maxMotorRps = maxMotorRpm / 60.0;
        maxTicksPerSec = maxMotorRps * ticksPerMotorRev;
    }
}




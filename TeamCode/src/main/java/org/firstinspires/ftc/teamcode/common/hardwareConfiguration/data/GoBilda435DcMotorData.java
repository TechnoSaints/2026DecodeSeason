package org.firstinspires.ftc.teamcode.common.hardwareConfiguration.data;

public class GoBilda435DcMotorData extends MotorData {
    public GoBilda435DcMotorData() {
        ticksPerMotorRev = 28;
        gearRatio = 13.7;
        ticksPerGearboxRev = gearRatio * ticksPerMotorRev;
        maxMotorRpm = 5900;
        maxMotorRps = maxMotorRpm / 60.0;
        maxTicksPerSec = maxMotorRps * ticksPerMotorRev;
    }
}




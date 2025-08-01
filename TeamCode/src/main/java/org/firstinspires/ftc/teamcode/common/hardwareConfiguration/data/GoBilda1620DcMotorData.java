package org.firstinspires.ftc.teamcode.common.hardwareConfiguration.data;

public class GoBilda1620DcMotorData extends MotorData {
    public GoBilda1620DcMotorData() {
        ticksPerMotorRev = 28;
        gearRatio = 3.7;
        ticksPerGearboxRev = gearRatio * ticksPerMotorRev;
        maxMotorRpm = 5900;
        maxMotorRps = maxMotorRpm / 60.0;
        maxTicksPerSec = maxMotorRps * ticksPerMotorRev;
    }
}

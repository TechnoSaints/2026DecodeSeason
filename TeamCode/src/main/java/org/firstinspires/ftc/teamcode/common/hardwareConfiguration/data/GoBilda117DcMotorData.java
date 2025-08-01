package org.firstinspires.ftc.teamcode.common.hardwareConfiguration.data;

public class GoBilda117DcMotorData extends MotorData {

    public GoBilda117DcMotorData() {
        ticksPerMotorRev = 28;
        gearRatio = 50.9;
        ticksPerGearboxRev = gearRatio * ticksPerMotorRev;
        maxMotorRpm = 5900;
        maxMotorRps = maxMotorRpm / 60.0;
        maxTicksPerSec = maxMotorRps * ticksPerMotorRev;
    }
}

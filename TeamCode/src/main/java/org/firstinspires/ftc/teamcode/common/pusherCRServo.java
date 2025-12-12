package org.firstinspires.ftc.teamcode.common;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

@Config
public class pusherCRServo extends Component {
    private final CRServo servo;
    private double onPower = 1.0;
    public pusherCRServo(HardwareMap hardwareMap, Telemetry telemetry, String servoName) {
        super(telemetry);

        servo = hardwareMap.get(CRServo.class, servoName);
    }
    public void forward()
    {
        servo.setPower(onPower);
    }

    public void reverse()
    {
        servo.setPower(-onPower);
    }

    public void stop()
    {
        servo.setPower(0.0);

    }
}
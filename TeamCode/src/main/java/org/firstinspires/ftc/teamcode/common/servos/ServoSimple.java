package org.firstinspires.ftc.teamcode.common.servos;

import com.arcrobotics.ftclib.hardware.ServoEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.ServoImpl;
import com.qualcomm.robotcore.hardware.ServoImplEx;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.common.Component;

public class ServoSimple extends Component {

    protected final ServoImplEx servo;

    public ServoSimple(HardwareMap hardwareMap, Telemetry telemetry, String servoName) {
        super(telemetry);
        servo = hardwareMap.get(ServoImplEx.class, servoName);
    }

    public void setPositionTicks(double position) {
        servo.setPosition(position);
    }

    protected double getPositionTicks() {
        return (servo.getPosition());
    }

    public void disable() {
        servo.setPwmDisable();
    }

    public void enable() {
        servo.setPwmEnable();
    }
}

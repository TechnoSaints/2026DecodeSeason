package org.firstinspires.ftc.teamcode.common;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.common.hardwareConfiguration.data.GoBilda435DcMotorData;
import org.firstinspires.ftc.teamcode.common.hardwareConfiguration.data.MotorData;

@Config
public class intakeMotor extends Component {
    private final DcMotorEx motor;
    private MotorData motorData = new GoBilda435DcMotorData();
    private double onPower = 1.0;
    public intakeMotor(HardwareMap hardwareMap, Telemetry telemetry, String motorName) {
        super(telemetry);

        motor = hardwareMap.get(DcMotorEx.class, motorName);
    }

    public void forward()
    {
        motor.setPower(onPower);
    }

    public void reverse()
    {
        motor.setPower(-onPower);
    }

    public void stop()
    {
        motor.setPower(0.0);
    }
}
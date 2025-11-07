package org.firstinspires.ftc.teamcode.common;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.CurrentUnit;
import org.firstinspires.ftc.teamcode.common.hardwareConfiguration.data.GoBilda435DcMotorData;
import org.firstinspires.ftc.teamcode.common.hardwareConfiguration.data.LiftData;

public class IntakeMotors extends Component {
    private DcMotorEx motor;
    private double maxVelocity;

    private double maxMovePower;
    private double stopPower;
    private double maxTicks;
    private int currentSpeed;

    private final GoBilda435DcMotorData motorData = new GoBilda435DcMotorData();

    private final LiftData liftData = new LiftData();

    public IntakeMotors(Telemetry telemetry) {
        super(telemetry);
    }

    public void init(HardwareMap hardwareMap, String intakeName) {
        maxVelocity = motorData.maxTicksPerSec;
        maxMovePower = liftData.maxMovePower;
        stopPower = liftData.stopPower;
        maxTicks = 11.1428571;

        motor = hardwareMap.get(DcMotorEx.class, intakeName);
        resetEncoder();
    }

    public int currentPosition() {
        return (motor.getCurrentPosition());
    }

    public void setSpeed(double speedMultiplier) {
        motor.setPower(speedMultiplier);
    }

    public void stopMotor() {
        motor.setPower(0);
    }

    private void resetEncoder() {
        motor.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        motor.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        motor.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
    }


    public boolean isBusy() {
        return motor.isBusy();
    }

    public void log() {
        telemetry.addData("isBusy(): ", isBusy());
        telemetry.addData("Position:  ", motor.getCurrentPosition());
        telemetry.addData("current: ", motor.getCurrent(CurrentUnit.AMPS));
        telemetry.update();
    }
}
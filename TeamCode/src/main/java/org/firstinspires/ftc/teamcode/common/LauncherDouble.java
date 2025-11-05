package org.firstinspires.ftc.teamcode.common;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.VoltageSensor;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.common.hardwareConfiguration.data.MotorData;
import org.firstinspires.ftc.teamcode.common.hardwareConfiguration.data.LauncherData;

@Config
public class LauncherDouble extends Component {
    private final DcMotorEx launcherMotorL, launcherMotorR;
    private final LauncherData launcherData = new LauncherData();
    private final double maxVelocityFactor, targetOperatingVoltage;
    private double currentVoltage, voltageCompensationFactor;
    private final int maxTicksPerSecond;
    int targetVelocity = 0;
    private VoltageSensor myControlHubVoltageSensor;

    public LauncherDouble(HardwareMap hardwareMap, Telemetry telemetry, String motorNameL, String motorNameR, MotorData motorData) {
        super(telemetry);
        myControlHubVoltageSensor = hardwareMap.get(VoltageSensor.class, "Control Hub");
        targetOperatingVoltage = launcherData.targetOperatingVoltage;
        currentVoltage =  myControlHubVoltageSensor.getVoltage();
        voltageCompensationFactor = targetOperatingVoltage/currentVoltage;

        maxVelocityFactor = launcherData.maxVelocityFactor;
        maxTicksPerSecond = motorData.maxTicksPerSec;
        launcherMotorL = hardwareMap.get(DcMotorEx.class, motorNameL);
        launcherMotorR = hardwareMap.get(DcMotorEx.class, motorNameR);
        resetEncoders();
        setVelocityFactorVoltageCompensated(targetVelocity);
    }

    public void setVelocityFactorVoltageCompensated(double targetVelocityFactor) {

        if (Math.abs(targetVelocityFactor) < 0.05)
        {
            targetVelocityFactor = 0.0;
        } else if (targetVelocityFactor > 1.0)
        {
            targetVelocityFactor = 1.0;
        } else if (targetVelocityFactor < -1.0)
        {
            targetVelocityFactor = -1.0;
        }
        currentVoltage = myControlHubVoltageSensor.getVoltage();
        voltageCompensationFactor = targetOperatingVoltage/currentVoltage;
        targetVelocity = Math.toIntExact(Math.round(targetVelocityFactor * maxTicksPerSecond *voltageCompensationFactor));
        setMotorsTargetVelocity(targetVelocity);
    }

    private void setMotorsTargetVelocity(int targetVelocity)
    {
        launcherMotorL.setVelocity(targetVelocity);
        launcherMotorR.setVelocity(targetVelocity);
    }

    private void setMotorsPower(double power) {
        launcherMotorL.setPower(power);
        launcherMotorR.setPower(power);
    }

    private void resetEncoders() {
        launcherMotorL.setDirection(DcMotor.Direction.FORWARD);
        launcherMotorR.setDirection(DcMotor.Direction.REVERSE);

        launcherMotorL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        launcherMotorR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        launcherMotorL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        launcherMotorR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        launcherMotorL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        launcherMotorR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
    }

    public void log() {
        telemetry.addData("maxTicksPerSecond:  ", maxTicksPerSecond);
        telemetry.addData("targetOperatingVoltage:  ", targetOperatingVoltage);
        telemetry.addData("currentVoltage:  ", currentVoltage);
        telemetry.addData("voltageCompensationFactor: ", voltageCompensationFactor);
    //    telemetry.addData("targetVelocityFactor: ", targetVelocityFactor);
        telemetry.addData("targetVelocity:  ", targetVelocity);
        telemetry.addData("Actual Velocity L:  ", launcherMotorL.getVelocity());
        telemetry.addData("Actual Velocity R:  ", launcherMotorR.getVelocity());
        telemetry.addData("PowerL:  ", launcherMotorL.getPower());
        telemetry.addData("PowerR:  ", launcherMotorR.getPower());
        telemetry.update();
    }

}
package org.firstinspires.ftc.teamcode.common;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.common.hardwareConfiguration.data.GoBilda6000DcMotorData;
import org.firstinspires.ftc.teamcode.common.hardwareConfiguration.data.MotorData;
import org.firstinspires.ftc.teamcode.common.hardwareConfiguration.data.LauncherData;

@Config
public class LauncherDouble extends Component {
    private final DcMotorEx motorL, motorR;
    private final Servo servoL, servoR;
    private final LauncherData launcherData = new LauncherData();
    private final double maxVelocityFactor = launcherData.maxVelocityFactor;
    private MotorData motorData = new GoBilda6000DcMotorData();
    private final int maxTicksPerSecond = motorData.maxTicksPerSec;
    private int targetVelocity = 0;
    private double targetLaunchPosition = 0.5;
    private final double maxLaunchPosition = 1.0;
    private final double minLaunchPosition = 0.0;


    public LauncherDouble(HardwareMap hardwareMap, Telemetry telemetry) {
        super(telemetry);
        motorL = hardwareMap.get(DcMotorEx.class, "motorL");
        motorR = hardwareMap.get(DcMotorEx.class, "motorR");
        servoL = hardwareMap.get(Servo.class, "servoL");
        servoR = hardwareMap.get(Servo.class, "servoL");

        resetEncoders();
        setVelocityFactor(targetVelocity);
        setLaunchPosition(targetLaunchPosition);
    }

    public void setVelocityFactor(double targetVelocityFactor) {
        if (Math.abs(targetVelocityFactor) < 0.05)
        {
            targetVelocityFactor = 0.0;
        } else if (targetVelocityFactor > maxVelocityFactor)
        {
            targetVelocityFactor = maxVelocityFactor;
        } else if (targetVelocityFactor < -maxVelocityFactor)
        {
            targetVelocityFactor = -maxVelocityFactor;
        }
        targetVelocity = Math.toIntExact(Math.round(targetVelocityFactor * maxTicksPerSecond));
        setMotorsTargetVelocity(targetVelocity);
    }

    public void setLaunchPosition(double targetLaunchPosition) {
        if (targetLaunchPosition >= maxLaunchPosition)
        {
            targetLaunchPosition = maxLaunchPosition;
        } else if (targetLaunchPosition <= minLaunchPosition)
        {
            targetLaunchPosition = minLaunchPosition;
        }
        setServosTargetLaunchPosition(targetLaunchPosition);
    }
    private void setMotorsTargetVelocity(int targetVelocity)
    {
        motorL.setVelocity(targetVelocity);
        motorR.setVelocity(-targetVelocity);
    }

    private void setServosTargetLaunchPosition(double targetLaunchPosition)
    {
        servoL.setPosition(targetLaunchPosition);
        servoR.setPosition(1.0-targetLaunchPosition);
    }

    private void resetEncoders() {
        motorL.setDirection(DcMotor.Direction.FORWARD);
        motorR.setDirection(DcMotor.Direction.REVERSE);

        motorL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        motorL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        motorL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        motorR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
    }

    public void log() {
        telemetry.addData("maxTicksPerSecond:  ", maxTicksPerSecond);
        telemetry.addData("targetVelocity:  ", targetVelocity);
        telemetry.addData("Actual Velocity L:  ", motorL.getVelocity());
        telemetry.addData("Actual Velocity R:  ", motorR.getVelocity());
        telemetry.addData("PowerL:  ", motorL.getPower());
        telemetry.addData("PowerR:  ", motorR.getPower());
        telemetry.addData("targetLaunchPosition:  ", targetLaunchPosition);
        telemetry.addData("Actual Position L:  ", servoL.getPosition());
        telemetry.addData("Actual Position R:  ", servoR.getPosition());
        telemetry.update();
    }

}
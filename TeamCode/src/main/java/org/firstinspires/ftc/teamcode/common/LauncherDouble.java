package org.firstinspires.ftc.teamcode.common;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.common.hardwareConfiguration.data.GoBilda6000DcMotorData;
import org.firstinspires.ftc.teamcode.common.hardwareConfiguration.data.MotorData;
import org.firstinspires.ftc.teamcode.common.hardwareConfiguration.data.LauncherData;
import org.firstinspires.ftc.teamcode.common.hardwareConfiguration.setPoints.LauncherSettings;

@Config
public class LauncherDouble extends Component {
    private final DcMotorEx motorL, motorR;
    private final Servo launchServo;
    private final LauncherData launcherData = new LauncherData();
    private final double maxVelocityFactor = launcherData.maxVelocityFactor;
    private MotorData motorData = new GoBilda6000DcMotorData();
    private final int maxTicksPerSecond = motorData.maxTicksPerSec;

    private double targetVelocityFactor = 0.0;
    private int targetVelocity = 0;
    private double velocityFactorIncrement = 0.05;
    private double targetLaunchPosition = 0.5;
    private double launchPositionFactor = 0.05;
    private final double maxLaunchPosition = 1.0;
    private final double minLaunchPosition = 0.0;
    private double shortShotVelocityFactor, longShotVelocityFactor;
    private double shortShotPosition, longShotPosition;

    public LauncherDouble(HardwareMap hardwareMap, Telemetry telemetry) {
        super(telemetry);
        LauncherSettings.init();
        shortShotVelocityFactor = LauncherSettings.shortShotVelocityFactor;
        shortShotPosition = LauncherSettings.shortShotPosition;
        longShotVelocityFactor = LauncherSettings.longShotVelocityFactor;
        longShotPosition = LauncherSettings.longShotPosition;

        motorL = hardwareMap.get(DcMotorEx.class, "launcherMotorL");
        motorR = hardwareMap.get(DcMotorEx.class, "launcherMotorR");
        launchServo = hardwareMap.get(Servo.class, "launchServo");

        resetEncoders();
        setVelocityFactor(targetVelocity);
        setLaunchPosition(targetLaunchPosition);
    }

    public void setShortShot()
    {
        setVelocityFactor(shortShotVelocityFactor);
        setLaunchPosition(shortShotPosition);
    }

    public void setLongShot()
    {
        setVelocityFactor(longShotVelocityFactor);
        setLaunchPosition(longShotPosition);
    }

    public void stop()
    {
        targetVelocityFactor = 0.0;
        setVelocityFactor(targetVelocityFactor);
    }
    public void increaseVelocity()
    {
        targetVelocityFactor += velocityFactorIncrement;
        setVelocityFactor(targetVelocityFactor);
    }

    public void increaseLaunchPosition()
    {
        targetLaunchPosition += targetLaunchPosition;
        setLaunchPosition(targetLaunchPosition);
    }

    public void decreaseVelocity()
    {
        targetVelocityFactor -= velocityFactorIncrement;
        setVelocityFactor(targetVelocityFactor);
    }

    public void decreaseLaunchPosition()
    {
        targetLaunchPosition -= targetLaunchPosition;
        setLaunchPosition(targetLaunchPosition);
    }
    public void setVelocityFactor(double velocityFactor) {
        targetVelocityFactor = velocityFactor;
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


    public void setLaunchPosition(double launchPosition) {
        targetLaunchPosition = launchPosition;
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
        motorR.setVelocity(targetVelocity);
    }

    private void setServosTargetLaunchPosition(double targetLaunchPosition)
    {
        launchServo.setPosition(targetLaunchPosition);
        launchServo.setPosition(targetLaunchPosition);
    }

    private void resetEncoders() {
        motorL.setDirection(DcMotor.Direction.REVERSE);
        motorR.setDirection(DcMotor.Direction.FORWARD);

        motorL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        motorL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        motorL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        motorR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
    }

    public void log() {
        telemetry.addData("targetVelocityFactor:  ", targetVelocityFactor);
        telemetry.addData("maxTicksPerSecond:  ", maxTicksPerSecond);
        telemetry.addData("targetVelocity:  ", targetVelocity);
        telemetry.addData("Actual Velocity L:  ", motorL.getVelocity());
        telemetry.addData("Actual Velocity R:  ", motorR.getVelocity());
        //       telemetry.addData("PowerL:  ", motorL.getPower());
        //       telemetry.addData("PowerR:  ", motorR.getPower());
        telemetry.addData("targetLaunchPosition:  ", targetLaunchPosition);
        telemetry.addData("Actual Position L:  ", launchServo.getPosition());
        telemetry.addData("Actual Position R:  ", launchServo.getPosition());
        telemetry.update();
    }

}
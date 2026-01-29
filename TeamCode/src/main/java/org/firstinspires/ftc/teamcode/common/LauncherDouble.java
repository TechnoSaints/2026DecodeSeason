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
import org.firstinspires.ftc.teamcode.common.hardwareConfiguration.setPoints.LauncherSettings;

@Config
public class LauncherDouble extends Component {
    private final DcMotorEx motorL, motorR;
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
    private double shortShotVelocityFactor, longShotVelocityFactor, mediumShotVelocityFactor;

    public LauncherDouble(HardwareMap hardwareMap, Telemetry telemetry) {
        super(telemetry);
        shortShotVelocityFactor = LauncherSettings.shortShotVelocityFactor;
        longShotVelocityFactor = LauncherSettings.longShotVelocityFactor;
        mediumShotVelocityFactor = LauncherSettings.mediumShotVelocityFactor;

        motorL = hardwareMap.get(DcMotorEx.class, "leftLauncher");
        motorR = hardwareMap.get(DcMotorEx.class, "rightLauncher");

        resetEncoders();
        setVelocityFactor(targetVelocity);
    }

    public void setShortShot()
    {
        setVelocityFactor(shortShotVelocityFactor);
    }

    public void setMediumShot(){
        setVelocityFactor(mediumShotVelocityFactor);
    }

    public void setLongShot()
    {
        setVelocityFactor(longShotVelocityFactor);
    }

    public void setRPM(double d) {

        setVelocityFactor(0.45);

        if (d < 36) {
            setVelocityFactor(0.4);
        } else if ((d >= 36) && (d < 42)){
            setVelocityFactor(0.4);
        } else if ((d >= 42) && (d < 54)) {
            setVelocityFactor(0.415);
        } else if ((d >= 54) && (d < 66)) {
            setVelocityFactor(0.425);
        } else if ((d >= 66) && (d < 78)) {
            setVelocityFactor(0.44);
        } else if ((d >= 78) && (d < 90)) {
            setVelocityFactor(0.46);
        } else if ((d >= 90) && (d < 102)) {
            setVelocityFactor(0.49);
        } else if ((d >= 102) && (d < 114)) {
            setVelocityFactor(0.52);
        } else if ((d >= 114) && (d < 126)) {
            setVelocityFactor(0.55);
        } else if ((d >= 126) && (d < 138)) {
            setVelocityFactor(0.573);
        } else if ((d >= 138) && (d < 150)) {
            setVelocityFactor(0.585);
        } else if ((d >= 150) && (d < 162)) {
            setVelocityFactor(0.605);
        } else if (d >= 162) {
            setVelocityFactor(0.61);
        }

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


    public void decreaseVelocity()
    {
        targetVelocityFactor -= velocityFactorIncrement;
        setVelocityFactor(targetVelocityFactor);
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

    private void setMotorsTargetVelocity(int targetVelocity)
    {
        motorL.setVelocity(-targetVelocity);
        motorR.setVelocity(-targetVelocity);
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

    public boolean isBusy()
    {
        return (motorL.isBusy() || motorR.isBusy());
    }
    public void log() {
        telemetry.addData("targetVelocityFactor:  ", targetVelocityFactor);
        telemetry.addData("maxTicksPerSecond:  ", maxTicksPerSecond);
        telemetry.addData("targetVelocity:  ", targetVelocity);
        telemetry.addData("Actual Velocity L:  ", motorL.getVelocity());
        telemetry.addData("Actual Velocity R:  ", motorR.getVelocity());
        //       telemetry.addData("PowerL:  ", motorL.getPower());
        //       telemetry.addData("PowerR:  ", motorR.getPower())
        telemetry.update();
    }

}
package org.firstinspires.ftc.teamcode.common;

import com.acmerobotics.dashboard.config.Config;
import com.arcrobotics.ftclib.controller.PIDFController;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.CurrentUnit;
import org.firstinspires.ftc.teamcode.common.hardwareConfiguration.data.MotorData;
import org.firstinspires.ftc.teamcode.common.hardwareConfiguration.data.ShooterData;

@Config
public class ShooterDouble extends Component {
    private final DcMotorEx shooterMotorL, shooterMotorR;
    private final ShooterData shooterData = new ShooterData();
    private final double maxPower;
    private final int maxTicksPerSecond;

    int direction = 1;
    int targetVelocity = 0;

    public ShooterDouble(HardwareMap hardwareMap, Telemetry telemetry, String motorNameL, String motorNameR, MotorData motorData) {
        super(telemetry);
        maxPower = shooterData.maxPower;
        maxTicksPerSecond = motorData.maxTicksPerSec;

        shooterMotorL = hardwareMap.get(DcMotorEx.class, motorNameL);
        shooterMotorR = hardwareMap.get(DcMotorEx.class, motorNameR);
        resetEncoders();
        setMotorsPower(maxPower);
    }

    public void setPower(float targetPower)
    {
        targetVelocity = Math.toIntExact(Math.round(targetPower * maxTicksPerSecond));
    }
    public void stop() {
        setMotorsPower(0.0);
    }

    private void setMotorsPower(double power) {
        shooterMotorL.setPower(power);
        shooterMotorR.setPower(power);
    }

    private void resetEncoders() {
        shooterMotorL.setDirection(DcMotor.Direction.FORWARD);
        shooterMotorR.setDirection(DcMotor.Direction.REVERSE);

        shooterMotorL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        shooterMotorR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        shooterMotorL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        shooterMotorR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        shooterMotorL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        shooterMotorR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
    }


    public void log() {
        telemetry.addData("targetVelocity:  ", targetVelocity);
        telemetry.addData("Actual Velocity L:  ", shooterMotorL.getVelocity());
        telemetry.addData("Actual Velocity R:  ", shooterMotorR.getVelocity());
        telemetry.addData("PowerL:  ", shooterMotorL.getPower());
        telemetry.addData("PowerR:  ", shooterMotorR.getPower());
    }

}
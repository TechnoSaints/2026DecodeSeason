package org.firstinspires.ftc.teamcode.common;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.CurrentUnit;
import org.firstinspires.ftc.teamcode.common.hardwareConfiguration.data.GoBilda435DcMotorData;
import org.firstinspires.ftc.teamcode.common.hardwareConfiguration.data.LiftData;
import org.firstinspires.ftc.teamcode.common.hardwareConfiguration.data.MotorData;
import org.firstinspires.ftc.teamcode.common.hardwareConfiguration.positions.LiftPositions;

// Modified from LiftSingle to be used for continuous motor movement that doesn't require limits
public class ControlledMotor extends Component {
    private final DcMotorEx motor;
    private final double maxVelocity;
    private final double maxMovePower;
    private final double stopPower;
    private double targetVelocity;
    private int direction = 1;
    private final double lockPower;
    private final ElapsedTime delayTimer = new ElapsedTime(ElapsedTime.Resolution.MILLISECONDS);

    private final MotorData motorData = new GoBilda435DcMotorData();

    private final LiftData liftData = new LiftData();

    public ControlledMotor(HardwareMap hardwareMap, Telemetry telemetry, String motorName, boolean reverseMotor) {
        super(telemetry);
        maxVelocity = motorData.maxTicksPerSec;
        maxMovePower = liftData.maxMovePower;
        stopPower = liftData.stopPower;
        lockPower = liftData.lockPower;
        motor = hardwareMap.get(DcMotorEx.class, motorName);

        if (reverseMotor) {
            direction = -1;
        } else {
            direction = 1;
        }
        resetEncoder();
    }

    public int currentPosition() {
        return (motor.getCurrentPosition());
    }

    public void stop() {
        stopAtPosition(motor.getCurrentPosition());
        //       log();
    }

    public void up(double targetPower) {
        motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        targetVelocity = direction * targetPower * maxMovePower * maxVelocity;
        motor.setVelocity(targetVelocity);
    }

    public void down(double targetPower) {
        motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        targetVelocity = direction * -targetPower * maxMovePower * maxVelocity;
        motor.setVelocity(targetVelocity);

    }

    public void setPositionPreset(LiftPositions position) {
        setPositionTicks(position.getValue());
    }

    private void setPositionTicks(int ticks) {
        stopAtPosition(ticks);
    }

    private void stopAtPosition(int targetPosition) {
        motor.setTargetPosition(targetPosition);
        motor.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        motor.setPower(stopPower);
    }

    private void resetEncoder() {
        motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    public void lock() {
        motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motor.setPower(-lockPower);
    }

    public void unlock() {
        motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        stop();
    }

    public void setZero()
    {
        motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        targetVelocity = direction * -0.35 * maxMovePower * maxVelocity;
        motor.setVelocity(targetVelocity);
        while (motor.getCurrent(CurrentUnit.AMPS) < 7.5)
        {
//            log();
        }
        delayTimer.reset();
        motor.setVelocity(0);
        while (delayTimer.milliseconds() < 250)
        {}
        resetEncoder();
//        telemetry.addData("currentPos", motor.getCurrentPosition());
//        telemetry.update();
//        delayTimer.reset();
//        while (delayTimer.milliseconds() < 10000);
//        {}
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
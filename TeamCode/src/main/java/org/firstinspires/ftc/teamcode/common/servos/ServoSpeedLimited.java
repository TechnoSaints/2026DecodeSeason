package org.firstinspires.ftc.teamcode.common.servos;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class ServoSpeedLimited extends ServoSimple {
    private double tickIncrementSize = 0.01;
    private double workingTickIncrement;
    private double positionTolerance = 0.005;
    private double targetPosition, currentPosition;
    private double incrementDelayMS;
    private ElapsedTime controlTimer = new ElapsedTime(ElapsedTime.Resolution.MILLISECONDS);

    public ServoSpeedLimited(HardwareMap hardwareMap, Telemetry telemetry, String servoName) {
        super(hardwareMap, telemetry, servoName);
        controlTimer.reset();
    }

    public void goToPositionTicks(double position, double incrementDelayMS) {
        this.incrementDelayMS = incrementDelayMS;
        targetPosition = position;
        currentPosition = getPositionTicks();
        if (targetPosition >= currentPosition) {
            workingTickIncrement = tickIncrementSize;
        } else {
            workingTickIncrement = -tickIncrementSize;
        }
        controlTimer.reset();
    }

    private boolean atTargetPosition() {
        return (Math.abs(targetPosition - currentPosition) <= positionTolerance);
    }

    public boolean isBusy() {
        return (!atTargetPosition());
    }

    public void update() {
        if (isBusy()) {
            if (controlTimer.milliseconds() >= incrementDelayMS) {
                currentPosition = currentPosition + workingTickIncrement;
                setPositionTicks(currentPosition, 0);
                controlTimer.reset();
            }
        }
    }

    public void log()
    {
        telemetry.addData("currentPosition: ", currentPosition);
        telemetry.addData("getPositionTicks(): ", getPositionTicks());
        telemetry.addData("targetPosition: ", targetPosition);
    }
}

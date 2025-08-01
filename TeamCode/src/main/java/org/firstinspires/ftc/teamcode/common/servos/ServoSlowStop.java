package org.firstinspires.ftc.teamcode.common.servos;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class ServoSlowStop extends ServoSimple {
    private double targetPosition, controlPosition;
    private final double slowDownDistance = 0.15;
    private final double controlPositionIncrementDefault = 0.01;
    private double controlPositionIncrement = controlPositionIncrementDefault;
    private final double controlDelayDefaultMS;
    private double controlDelayMS = 0;
    private final double controlDelayMaxIncreaseMS = 25;
    private double controlDelayIncrementMS = controlDelayMaxIncreaseMS * controlPositionIncrementDefault / slowDownDistance;
    private final double tolerance = controlPositionIncrement / 1.75;
    private ElapsedTime timer = new ElapsedTime();
    private Servo servo;

    public ServoSlowStop(HardwareMap hardwareMap, Telemetry telemetry, String servoName, double delay) {
        super(hardwareMap, telemetry, servoName);
        servo = hardwareMap.get(Servo.class, servoName);
        controlDelayDefaultMS = delay;
        controlDelayMS = controlDelayDefaultMS;
        timer.reset();
    }

    public void setTargetPosition(double position)
    {
        targetPosition = position;
        slowStopMove();
    }

    private void slowStopMove() {
        controlDelayMS = controlDelayDefaultMS;
        controlPositionIncrement = controlPositionIncrementDefault;
        if (error(targetPosition, servo.getPosition()) < 0) {
            controlPositionIncrement *= -1.0;
        }
        timer.reset();
    }

    private double error(double target, double current) {
        return (target - current);
    }

    private boolean slowDown(double target, double current) {
        return (Math.abs(error(target, current)) < slowDownDistance);
    }

    public boolean isBusy() {
        return (Math.abs(error(targetPosition, controlPosition)) > tolerance);
    }

    public void update() {
        if (isBusy()) {
            if (timer.milliseconds() > controlDelayMS) {
                if (slowDown(targetPosition, controlPosition)) {
                    controlDelayMS += controlDelayIncrementMS;
                }
                controlPosition += controlPositionIncrement;
                setPositionTicks(controlPosition,0);
                timer.reset();
            }
        }
    }

    public void log() {
        telemetry.addData("targetPosition: ", targetPosition);
        telemetry.addData("slowDown: ", slowDown(targetPosition, servo.getPosition()));
        telemetry.addData("controlDelayMS: ", controlDelayMS);
        telemetry.addData("servo.getPosition(): ", servo.getPosition());
        telemetry.addData("controlPosition: ", controlPosition);
        telemetry.update();
    }
}
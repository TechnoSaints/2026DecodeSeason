package org.firstinspires.ftc.teamcode.common.servos;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class ServoAngular extends ServoSimple {
    private final double rangeTicks, rangeDegrees, ticksPerDegree, minPosDegrees, ticksAtMinPosDegrees, maxPosDegrees, ticksAtMaxPosDegrees;
    private double currentPosDegrees;

    public ServoAngular(HardwareMap hardwareMap, Telemetry telemetry, String servoName, double minPosDegrees, double ticksAtMinPosDegrees, double maxPosDegrees, double ticksAtMaxPosDegrees) {
        super(hardwareMap, telemetry, servoName);

        if (minPosDegrees >= maxPosDegrees) {
            throw new IllegalArgumentException();
        }

        this.minPosDegrees = minPosDegrees;
        this.ticksAtMinPosDegrees = ticksAtMinPosDegrees;
        this.maxPosDegrees = maxPosDegrees;
        this.ticksAtMaxPosDegrees = ticksAtMaxPosDegrees;

        rangeDegrees = this.maxPosDegrees - this.minPosDegrees;
        rangeTicks = this.ticksAtMaxPosDegrees - this.ticksAtMinPosDegrees;
        ticksPerDegree = rangeTicks / rangeDegrees;

//        setPositionDegrees(maxPosDegrees, 0);
    }

    public void setPositionDegrees(double posDegrees, double delay) {
        if (!stopAtLimit(posDegrees)) {
            setPositionTicks(degreesToTicks(posDegrees), 0);
            currentPosDegrees = posDegrees;
        }
        setTimer(delay);
    }

    public double getPositionDegrees() {
        return (currentPosDegrees);
    }

    private boolean stopAtLimit(double posDegrees) {
        boolean atLimit = false;

        if (posDegrees <= minPosDegrees) {
            setPositionTicks(ticksAtMinPosDegrees, 0);
            currentPosDegrees = minPosDegrees;
            atLimit = true;
        } else if (posDegrees >= maxPosDegrees) {
            setPositionTicks(ticksAtMaxPosDegrees, 0);
            currentPosDegrees = maxPosDegrees;
            atLimit = true;
        }

        return (atLimit);
    }

    private double degreesToTicks(double posDegrees) {
        return (ticksAtMinPosDegrees + ((posDegrees - minPosDegrees) * ticksPerDegree));
    }
}

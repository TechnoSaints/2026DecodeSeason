package org.firstinspires.ftc.teamcode.common;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.common.hardwareConfiguration.data.ExtendoData;
import org.firstinspires.ftc.teamcode.common.hardwareConfiguration.positions.ExtendoPositions;
import org.firstinspires.ftc.teamcode.common.servos.ServoAngular;

public class Extendo extends Component {
    private ServoAngular servo;
    private ElapsedTime controlTimer;

    private final double totalSlowExtensionTimeMS = 1750.0;
    private final double totalMediumExtensionTimeMS = 350.0;
    private final double moveIncrementInches = 0.2;
    private final ExtendoData extendoData = new ExtendoData();
    private final double slowMoveDelayMS = (totalSlowExtensionTimeMS * moveIncrementInches) / (extendoData.maxLengthInches - extendoData.minLengthInches);
    private final double mediumMoveDelayMS = (totalMediumExtensionTimeMS * moveIncrementInches) / (extendoData.maxLengthInches - extendoData.minLengthInches);
    private final double lengthToleranceInches = moveIncrementInches / 2.0;
    private double targetLengthInches, currentLengthInches, currentMoveDelayMS;
    private int direction = 1;

    private boolean fast = false;

    public Extendo(HardwareMap hardwareMap, Telemetry telemetry, String extendoName) {
        super(telemetry);
        double angleAtMinLength, angleAtMaxLength;
        angleAtMinLength = lengthToAngle(extendoData.minLengthInches);
        angleAtMaxLength = lengthToAngle(extendoData.maxLengthInches);

        controlTimer = new ElapsedTime(ElapsedTime.Resolution.MILLISECONDS);
        controlTimer.reset();

        servo = new ServoAngular(hardwareMap, telemetry, extendoName, angleAtMaxLength, extendoData.maxLengthTicks, angleAtMinLength, extendoData.minLengthTicks);

        currentLengthInches = angleToLength(servo.getPositionDegrees());
        targetLengthInches = currentLengthInches;
        setFast();
//        setPositionPreset(ExtendoPositions.RETRACTED);
    }

    public void setPositionPreset(ExtendoPositions position) {
        direction = -1;
        if (position.getValue() > currentLengthInches) {
            direction = 1;
        }
        goToLength(position.getValue());
    }

    public void extendSlowly(int direction) {
        setSlow();
        if (controlTimer.milliseconds() > currentMoveDelayMS) {
            this.direction = direction;
            goToLength(currentLengthInches + (direction * moveIncrementInches));
        }
    }

    public void setSlow() {
        fast = false;
        currentMoveDelayMS = slowMoveDelayMS;
    }

    public void setMedium() {
        fast = false;
        currentMoveDelayMS = mediumMoveDelayMS;
    }

    public void setFast() {
        fast = true;
    }

    private void goToLength(double targetLengthInches) {
        if (fast) {
            servo.setPositionDegrees(lengthToAngle(targetLengthInches), 500);
            currentLengthInches = targetLengthInches;
        }
        this.targetLengthInches = targetLengthInches;
        controlTimer.reset();
    }

    // my formula
    // d = l1cos(x)+sqrt(l2^2 - l1^2 + l2^2 * cos(x)^2)
    private double angleToLength(double angle) {
        double l1squared = Math.pow(extendoData.nearLinkageLengthInches, 2);
        double l2squared = Math.pow(extendoData.farLinkageLengthInches, 2);
        double cosx = Math.cos(Math.toRadians(angle));

        return ((extendoData.nearLinkageLengthInches * cosx) + Math.sqrt(l2squared - l1squared + l1squared * Math.pow(cosx, 2)));
    }

    // my formula
    // angle = arccos((d^2 + l1^2 - l2^2)/(2dl1))
    private double lengthToAngle(double length) {
        double temp = ((length * length) + (extendoData.nearLinkageLengthInches * extendoData.nearLinkageLengthInches) -
                (extendoData.farLinkageLengthInches * extendoData.farLinkageLengthInches));
        temp = temp / (2.0 * length * extendoData.nearLinkageLengthInches);

        return (Math.toDegrees(Math.acos(temp)));
    }

    public double getCurrentLength() {
        return (currentLengthInches);
    }
//
//    private double getCurrentAngle() {
//        return (servo.getPositionDegrees());
//    }

    private boolean atTarget() {
        return (Math.abs(targetLengthInches - currentLengthInches) <= lengthToleranceInches);
    }

    public boolean isBusy() {
        return (!atTarget() || servo.isBusy());
    }

    public void update() {
        if (controlTimer.milliseconds() >= currentMoveDelayMS) {
            if (!atTarget()) {
                currentLengthInches = currentLengthInches + direction * moveIncrementInches;
                servo.setPositionDegrees(lengthToAngle(currentLengthInches), 0);
                controlTimer.reset();
            }
        }
    }

    public void log() {
//        telemetry.addData("currentLength: ", getCurrentLength());
//        telemetry.addData("lengthToAngle(): ", getCurrentAngle());
//        telemetry.update();
    }
}
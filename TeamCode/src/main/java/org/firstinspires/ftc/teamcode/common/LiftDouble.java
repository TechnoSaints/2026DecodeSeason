package org.firstinspires.ftc.teamcode.common;

import com.acmerobotics.dashboard.config.Config;
import com.arcrobotics.ftclib.controller.PIDFController;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;


import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.CurrentUnit;
import org.firstinspires.ftc.teamcode.common.hardwareConfiguration.data.LiftData;
import org.firstinspires.ftc.teamcode.common.hardwareConfiguration.data.LiftData;
import org.firstinspires.ftc.teamcode.common.hardwareConfiguration.positions.LiftPositions;

@Config
public class LiftDouble extends Component {
    private final DcMotorEx liftMotorL;
    private final DcMotorEx liftMotorR;
    private final PIDFController pidfL;
    private final PIDFController pidfR;
    public static double kP = 0.02;
    //original kP = 0.025
    public static double kI = 0.0;
    public static double kD = 0.0002;
    //original kD = 0.0005
    public static double kF = 0.0001;

    private final LiftData liftData = new LiftData();
    private final double maxMovePower;
    private final int maxPosition, minPosition, manualTolerance, autoTolerance;
    int positionTolerancePID;
    int direction = 1;
    int targetPosition = 0;
    DcMotorEx motorL, motorR;
    private final ElapsedTime delayTimer = new ElapsedTime(ElapsedTime.Resolution.MILLISECONDS);

    public LiftDouble(HardwareMap hardwareMap, Telemetry telemetry, String motorNameL, String motorNameR, boolean reverseMotor) {
        super(telemetry);
        maxMovePower = liftData.maxMovePower;
        maxPosition = LiftPositions.MAX.getValue();
        manualTolerance = liftData.teleopTolerance;
        autoTolerance = liftData.autoTolerance;
        positionTolerancePID = autoTolerance;
        minPosition = LiftPositions.MIN.getValue();

        pidfL = new PIDFController(kP, kI, kD, kF);
        pidfL.setTolerance(positionTolerancePID);
        pidfR = new PIDFController(kP, kI, kD, kF);
        pidfR.setTolerance(positionTolerancePID);

        liftMotorL = hardwareMap.get(DcMotorEx.class, motorNameL);
        liftMotorR = hardwareMap.get(DcMotorEx.class, motorNameR);
        resetEncoders();

        if (reverseMotor) {
            direction = -1;
        } else {
            direction = 1;
        }

        targetPosition = 0;
    }

    public void up(double targetPower) {
        if (!atTop(manualTolerance)) {
            setMotorsPower(targetPower);
        } else {
            stop();
        }
    }

    public void down(double targetPower) {
        if (!atBottom(manualTolerance)) {
            setMotorsPower(-targetPower);
        } else {
            stop();
        }
    }

    public void stop() {
        setTargetPosition(liftMotorL.getCurrentPosition());
    }

    public void setPositionPreset(LiftPositions position) {
        setTargetPosition(position.getValue());
    }

    public void setTargetPosition(int position) {
        if (position < minPosition) {
            targetPosition = minPosition;
        } else if (position > maxPosition) {
            targetPosition = maxPosition;
        } else {
            targetPosition = position;
        }
    }

    private boolean atTop(int offset) {
        if ((liftMotorL.getCurrentPosition() - offset) >= maxPosition) {
            return true;
        } else {
            return false;
        }
    }

    private boolean atBottom(int offset) {
        if ((liftMotorL.getCurrentPosition() + offset) <= minPosition) {
            return true;
        } else {
            return false;
        }
    }

    public void setMotorsPower(double power) {
        liftMotorL.setPower(power * maxMovePower);
        liftMotorR.setPower(power * maxMovePower);
    }

    public void setLMotorPower(double power) {
        liftMotorL.setPower(power * maxMovePower);
    }

    public void setRMotorPower(double power) {
        liftMotorR.setPower(power * maxMovePower);
    }

    public boolean isBusy() {
        return (!pidfL.atSetPoint() || !pidfR.atSetPoint());
    }

    private void setPIDFMotorPower() {
        setLMotorPower(pidfL.calculate(liftMotorL.getCurrentPosition(), targetPosition));
        setRMotorPower(pidfR.calculate(liftMotorR.getCurrentPosition(), targetPosition));
    }

    public void update() {
//        pidfL.setPIDF(kP,kI,kD,kF);
//        pidfR.setPIDF(kP,kI,kD,kF);
        setPIDFMotorPower();
    }

    private void resetEncoders() {
//        liftMotorL.setTargetPositionTolerance(autoTolerance);
//        liftMotorR.setTargetPositionTolerance(autoTolerance);
        liftMotorL.setDirection(DcMotor.Direction.FORWARD);
        liftMotorR.setDirection(DcMotor.Direction.REVERSE);

        liftMotorL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        liftMotorR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        liftMotorL.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        liftMotorR.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        liftMotorL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        liftMotorR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

    }

    public void setZero() {
        double targetPower = direction * -0.35 * maxMovePower;
        liftMotorL.setPower(targetPower);
        liftMotorR.setPower(targetPower);

        while ((liftMotorL.getCurrent(CurrentUnit.AMPS) < 7.5) && (liftMotorR.getCurrent(CurrentUnit.AMPS) < 7.5))
        {
//            log();
        }
        delayTimer.reset();
        liftMotorL.setPower(0.0);
        liftMotorR.setPower(0.0);
        while (delayTimer.milliseconds() < 250) {
        }
        resetEncoders();
        targetPower = 0;
//        telemetry.addData("currentPos", motor.getCurrentPosition());
//        telemetry.update();
//        delayTimer.reset();
//        while (delayTimer.milliseconds() < 10000);
//        {}
    }

    public void log() {
        telemetry.addData("PositionL:  ", liftMotorL.getCurrentPosition());
        telemetry.addData("PositionR:  ", liftMotorR.getCurrentPosition());
        telemetry.addData("Target:  ", targetPosition);
        telemetry.addData("PowerL:  ", liftMotorL.getPower());
        telemetry.addData("PowerR:  ", liftMotorR.getPower());
        telemetry.addData("Busy:  ", isBusy());
        telemetry.update();
    }


}
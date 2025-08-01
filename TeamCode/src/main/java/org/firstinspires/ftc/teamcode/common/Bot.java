package org.firstinspires.ftc.teamcode.common;

import com.qualcomm.hardware.rev.RevTouchSensor;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.common.hardwareConfiguration.positions.ExtendoPositions;
import org.firstinspires.ftc.teamcode.common.hardwareConfiguration.positions.HandlerArmPositions;
import org.firstinspires.ftc.teamcode.common.hardwareConfiguration.positions.HandlerGrabberPositions;
import org.firstinspires.ftc.teamcode.common.hardwareConfiguration.positions.HandlerWristPositions;
import org.firstinspires.ftc.teamcode.common.hardwareConfiguration.positions.IntakeGrabberPositions;
import org.firstinspires.ftc.teamcode.common.hardwareConfiguration.positions.IntakeLightPositions;
import org.firstinspires.ftc.teamcode.common.hardwareConfiguration.positions.IntakeSwivelPositions;
import org.firstinspires.ftc.teamcode.common.hardwareConfiguration.positions.IntakeWristPositions;
import org.firstinspires.ftc.teamcode.common.hardwareConfiguration.positions.LiftPositions;
import org.firstinspires.ftc.teamcode.common.servos.ServoSimple;

public abstract class Bot extends Component {
    private final ServoSimple intakeWrist, intakeSwivel, intakeGrabber, intakeLight;
    private final ServoSimple handlerArm, handlerWrist, handlerGrabber;
    private final RevTouchSensor handlerSwitch, bumperSwitchL, bumperSwitchR;
    private final Extendo extendo;
    private final LiftSingle lift;
    private Modes currentMode;
    private int currentPhase;
    private boolean onHold = false;

    public Bot(OpMode opMode, Telemetry telemetry) {
        super(telemetry);
        extendo = new Extendo(opMode.hardwareMap, telemetry, "extendo");
        intakeWrist = new ServoSimple(opMode.hardwareMap, telemetry, "intakeWrist");
        intakeSwivel = new ServoSimple(opMode.hardwareMap, telemetry, "intakeSwivel");
        intakeGrabber = new ServoSimple(opMode.hardwareMap, telemetry, "intakeGrabber");
        handlerArm = new ServoSimple(opMode.hardwareMap, telemetry, "handlerArm");
        handlerWrist = new ServoSimple(opMode.hardwareMap, telemetry, "handlerWrist");
        handlerGrabber = new ServoSimple(opMode.hardwareMap, telemetry, "handlerGrabber");
        handlerSwitch = opMode.hardwareMap.get(RevTouchSensor.class, "handlerSwitch");
        lift = new LiftSingle(opMode.hardwareMap, telemetry, "lift", false);
        intakeLight = new ServoSimple(opMode.hardwareMap, telemetry, "intakeLight");
        intakeLight.setPositionTicks(IntakeLightPositions.OFF.getValue(), 0);
        bumperSwitchL = opMode.hardwareMap.get(RevTouchSensor.class, "bumperSwitchL");
        bumperSwitchR = opMode.hardwareMap.get(RevTouchSensor.class, "bumperSwitchR");
    }

    // Phases are used to divide mode actions into sequential section, with entry criteria
    private void setPhase(int phase) {
        currentPhase = phase;
    }

    private boolean isPhase(int phase) {
        return (currentPhase == phase);
    }

    // Modes used to manage all mechanisms except drivetrain
    public void setMode(Modes newMode) {
        currentMode = newMode;
        setPhase(1);
    }

    public Modes getMode() {
        return (currentMode);
    }

    public boolean isMode(Modes mode) {
        return (this.currentMode == mode);
    }

    public void setExtendoPositionPreset(ExtendoPositions position) {
        extendo.setPositionPreset(position);
    }

    public void extendoSetMedium() {
        extendo.setMedium();
    }

    public void extendoSetFast() {
        extendo.setFast();
    }

    protected boolean extendoIsBusy() {
        return extendo.isBusy();
    }

    public double extendoGetCurrentLength() {
        return (extendo.getCurrentLength());
    }

    public void setIntakeWristPositionPreset(IntakeWristPositions position) {
        intakeWrist.setPositionTicks(position.getValue(), 350);
    }

    public void setIntakeWristPositionPreset(IntakeWristPositions position, double delay) {
        intakeWrist.setPositionTicks(position.getValue(), delay);
    }

    protected boolean intakeWristIsBusy() {
        return intakeWrist.isBusy();
    }

    public void setIntakeSwivelPositionPreset(IntakeSwivelPositions position) {
        intakeSwivel.setPositionTicks(position.getValue(), 150);
    }

    public void setIntakeSwivelPositionPreset(IntakeSwivelPositions position, double delay) {
        intakeSwivel.setPositionTicks(position.getValue(), delay);
    }

    protected boolean intakeSwivelIsBusy() {
        return intakeSwivel.isBusy();
    }

    public void setIntakeGrabberPositionPreset(IntakeGrabberPositions position) {
        intakeGrabber.setPositionTicks(position.getValue(), 150);
    }

    public void setIntakeGrabberPositionPreset(IntakeGrabberPositions position, double delay) {
        intakeGrabber.setPositionTicks(position.getValue(), delay);
    }

    protected boolean intakeGrabberIsBusy() {
        return intakeGrabber.isBusy();
    }

    public void setIntakeLightPositionPreset(IntakeLightPositions position) {
        intakeLight.setPositionTicks(position.getValue(), 0);
    }

    public void setLiftPositionPreset(LiftPositions position) {
        lift.setPositionPreset(position);
    }

    public int liftCurrentPosition() {
        return (lift.currentPosition());
    }

    public void liftUp(double power) {
        lift.up(power);
    }

    public void liftDown(double power) {
        lift.down(power);
    }

    public void liftStop() {
        lift.stop();
    }

    public void liftLock()
    {
        lift.lock();
    }

    public void liftUnlock()
    {
        lift.unlock();
    }

    public void liftZero() {
        lift.setZero();
    }

    public boolean liftIsBusy() {
        return lift.isBusy();
    }

    public void setHandlerArmPositionPreset(HandlerArmPositions position) {
        handlerArm.setPositionTicks(position.getValue(), 350);
    }

    public void setHandlerArmPositionPreset(HandlerArmPositions position, int delay) {
        handlerArm.setPositionTicks(position.getValue(), delay);
    }

    protected boolean handlerArmIsBusy() {
        return handlerArm.isBusy();
    }

    public void setHandlerWristPositionPreset(HandlerWristPositions position) {
        handlerWrist.setPositionTicks(position.getValue(), 250);
    }

    public void setHandlerWristPositionPreset(HandlerWristPositions position, double delay) {
        handlerWrist.setPositionTicks(position.getValue(), delay);
    }

    protected boolean handlerWristIsBusy() {
        return handlerWrist.isBusy();
    }

    public void setHandlerGrabberPositionPreset(HandlerGrabberPositions position) {
        handlerGrabber.setPositionTicks(position.getValue(), 250);
    }

    public void setHandlerGrabberPositionPreset(HandlerGrabberPositions position, int delay) {
        handlerGrabber.setPositionTicks(position.getValue(), delay);
    }

    protected boolean handlerGrabberIsBusy() {
        return handlerGrabber.isBusy();
    }

    public boolean handlerIsBusy() {
        return (liftIsBusy() || handlerArmIsBusy() || handlerWristIsBusy() || handlerGrabberIsBusy());
    }

    public boolean intakeIsBusy() {
        return (extendoIsBusy() || intakeWristIsBusy() || intakeSwivelIsBusy() || intakeGrabberIsBusy());
    }

    public boolean isBusy() {
        return (handlerIsBusy() || intakeIsBusy());
    }

    public boolean onHold() {
        return (onHold);
    }

    public boolean bumperBumped() {
        return (bumperSwitchL.isPressed() || bumperSwitchR.isPressed());
    }

    protected void logIsBusyStatuses() {
    }

    public void update() {
        extendo.update();
        lift.log();
        switch (getMode()) {
            case AUTO_START_POS:
                if (isPhase(1)) {
                    onHold = true;
                    setIntakeGrabberPositionPreset(IntakeGrabberPositions.OPEN);
                    setIntakeWristPositionPreset(IntakeWristPositions.UP);
                    setIntakeSwivelPositionPreset(IntakeSwivelPositions.DEGREES0);
                    setIntakeLightPositionPreset(IntakeLightPositions.OFF);
                    setExtendoPositionPreset(ExtendoPositions.RETRACTED);
                    setHandlerGrabberPositionPreset(HandlerGrabberPositions.CLOSED_TIGHT);
                    setHandlerWristPositionPreset(HandlerWristPositions.HANDOFF);
                    setHandlerArmPositionPreset(HandlerArmPositions.AUTO_START);
                    setLiftPositionPreset(LiftPositions.MIN);
                    onHold = false;
                    setPhase(-1);
                }
                break;

            case AUTO_END_POS:
                if (isPhase(1)) {
                    onHold = true;
//                    setIntakeGrabberPositionPreset(IntakeGrabberPositions.OPEN);
//                    setIntakeWristPositionPreset(IntakeWristPositions.UP);
//                    setIntakeSwivelPositionPreset(IntakeSwivelPositions.DEGREES0);
//                    setIntakeLightPositionPreset(IntakeLightPositions.OFF);
//                    setExtendoPositionPreset(ExtendoPositions.RETRACTED);
//                    setHandlerGrabberPositionPreset(HandlerGrabberPositions.CLOSED_TIGHT);
//                    setHandlerWristPositionPreset(HandlerWristPositions.HANDOFF);
                    setHandlerArmPositionPreset(HandlerArmPositions.TOP);
                    setLiftPositionPreset(LiftPositions.MIN);
                    onHold = false;
                    setPhase(-1);
                }
                break;

            case TELEOP_START_POS:
                if (isPhase(1)) {
                    onHold = true;
                    setIntakeGrabberPositionPreset(IntakeGrabberPositions.OPEN);
                    setIntakeWristPositionPreset(IntakeWristPositions.UP);
                    setIntakeSwivelPositionPreset(IntakeSwivelPositions.DEGREES0);
                    setIntakeLightPositionPreset(IntakeLightPositions.OFF);
                    setExtendoPositionPreset(ExtendoPositions.RETRACTED);
                    setHandlerGrabberPositionPreset(HandlerGrabberPositions.OPEN);
                    setHandlerWristPositionPreset(HandlerWristPositions.SPECIMEN_HANG);
                    setHandlerArmPositionPreset(HandlerArmPositions.TOP);
                    setLiftPositionPreset(LiftPositions.MIN);
                    onHold = false;
                    setPhase(-1);
                }
                break;

            case CRUISING:
                if (isPhase(1)) {
                    onHold = true;
                    setHandlerGrabberPositionPreset(HandlerGrabberPositions.OPEN);
                    setHandlerWristPositionPreset(HandlerWristPositions.HIGH_BUCKET);
                    setHandlerArmPositionPreset(HandlerArmPositions.HIGH_BUCKET);
                    setLiftPositionPreset(LiftPositions.MIN);
                    setIntakeGrabberPositionPreset(IntakeGrabberPositions.OPEN);
                    setIntakeWristPositionPreset(IntakeWristPositions.UP);
                    setIntakeSwivelPositionPreset(IntakeSwivelPositions.DEGREES0);
                    setIntakeLightPositionPreset(IntakeLightPositions.OFF);
                    setExtendoPositionPreset(ExtendoPositions.RETRACTED);
                    onHold = false;
                    setPhase(-1);
                }
                break;

            case INTAKE_HOVER_POS:
                if (isPhase(1)) {
                    onHold = true;
                    extendo.setMedium();
                    setExtendoPositionPreset(ExtendoPositions.AUTO_INTAKING);
                    extendo.setFast();
//                    setExtendoPositionPreset(ExtendoPositions.AUTO_INTAKING);
                    setIntakeGrabberPositionPreset(IntakeGrabberPositions.OPEN);
                    setIntakeWristPositionPreset(IntakeWristPositions.LOOK);
                    setIntakeSwivelPositionPreset(IntakeSwivelPositions.DEGREES180);
                    onHold = false;
                    setPhase(-1);
                }
                break;

            // Assumes already directly above target brick
            case INTAKE_BRICK_FOR_HANDOFF:
                if (isPhase(1)) {
                    onHold = true;
                    setIntakeWristPositionPreset(IntakeWristPositions.DOWN);
                    setPhase(2);
                } else if (isPhase(2)) {
                    if (!intakeWristIsBusy()) {
                        setIntakeGrabberPositionPreset(IntakeGrabberPositions.CLOSED_LOOSE);
                        setPhase(3);
                    }
                } else if (isPhase(3)) {
                    if (!intakeGrabberIsBusy()) {
                        setIntakeSwivelPositionPreset(IntakeSwivelPositions.DEGREES180);
                        setIntakeWristPositionPreset(IntakeWristPositions.FRONT);
                        setPhase(4);
                    }
                } else if (isPhase(4)) {
                    if (!intakeIsBusy()) {
                        setIntakeGrabberPositionPreset(IntakeGrabberPositions.CLOSED_TIGHT);
                        setIntakeSwivelPositionPreset(IntakeSwivelPositions.DEGREES0);
                        setIntakeWristPositionPreset(IntakeWristPositions.UP);
                        onHold = false;
                        setPhase(-1);
                    }
                }
                break;

            case INTAKE_BRICK_SIMPLE:
                if (isPhase(1)) {
                    onHold = true;
                    setIntakeGrabberPositionPreset(IntakeGrabberPositions.OPEN);
                    setPhase(2);
                } else if (isPhase(2)) {
                    if (!intakeGrabberIsBusy()) {
                        setIntakeWristPositionPreset(IntakeWristPositions.DOWN);
                        setPhase(3);
                    }
                } else if (isPhase(3)) {
                    if (!intakeWristIsBusy()) {
                        setIntakeGrabberPositionPreset(IntakeGrabberPositions.CLOSED_LOOSE);
                        setPhase(4);
                    }
                } else if (isPhase(4)) {
                    if (!intakeGrabberIsBusy()) {
                        setIntakeWristPositionPreset(IntakeWristPositions.LOOK);
                        setIntakeSwivelPositionPreset(IntakeSwivelPositions.DEGREES0);
                        onHold = false;
                        setPhase(-1);
                    }
                }
                break;

            case HANDLER_HANDOFF_PREP_POS:
                if (isPhase(1)) {
                    onHold = true;
                    setHandlerWristPositionPreset(HandlerWristPositions.HANDOFF);
                    setLiftPositionPreset(LiftPositions.HANDOFF_SETUP);
                    setHandlerGrabberPositionPreset(HandlerGrabberPositions.OPEN);
                    onHold = false;
                    setPhase(-1);
                }
                break;

            case HANDLER_GRAB_SPECIMEN_POS:
                if (isPhase(1)) {
                    onHold = true;
//                    setHandlerArmPositionPreset(HandlerArmPositions.SPECIMEN_WALL);
                    setHandlerWristPositionPreset(HandlerWristPositions.SPECIMEN_WALL);
                    setLiftPositionPreset(LiftPositions.SPECIMEN_WALL);
                    setHandlerGrabberPositionPreset(HandlerGrabberPositions.OPEN);
                    onHold = false;
                    setPhase(-1);
                }
                break;

            case TELEOP_HANDOFF:
                if (isPhase(1)) {
                    onHold = true;
                    setIntakeGrabberPositionPreset(IntakeGrabberPositions.CLOSED_LOOSE);
                    setIntakeWristPositionPreset(IntakeWristPositions.FRONT);
                    setIntakeSwivelPositionPreset(IntakeSwivelPositions.DEGREES180,400);
                    setLiftPositionPreset(LiftPositions.HANDOFF_SETUP);
                    setHandlerArmPositionPreset(HandlerArmPositions.HANDOFF);
                    setHandlerGrabberPositionPreset(HandlerGrabberPositions.OPEN);
                    setHandlerWristPositionPreset(HandlerWristPositions.HANDOFF);
                    setPhase(2);
                } else if (isPhase(2)) {
                    if (!intakeIsBusy()) {
                        setIntakeGrabberPositionPreset(IntakeGrabberPositions.CLOSED_TIGHT);
                        setIntakeSwivelPositionPreset(IntakeSwivelPositions.DEGREES0);
                        setIntakeWristPositionPreset(IntakeWristPositions.UPPISH);
                        setPhase(3);
                    }
                } else if (isPhase(3)) {
                    if (!intakeIsBusy()) {
                        setIntakeWristPositionPreset(IntakeWristPositions.UP,625);
                        setExtendoPositionPreset(ExtendoPositions.RETRACTED);
                        setPhase(4);
                    }
                } else if (isPhase(4)) {
                    if (!intakeIsBusy()) {
                        setLiftPositionPreset(LiftPositions.HANDOFF);
                        setPhase(5);
                    }
                } else if (isPhase(5)) {
                    if (!liftIsBusy()) {
                        setHandlerGrabberPositionPreset(HandlerGrabberPositions.CLOSED_TIGHT, 350);
                        setPhase(6);
                    }
                } else if (isPhase(6)) {
                    if (!handlerGrabberIsBusy()) {
                        setIntakeGrabberPositionPreset(IntakeGrabberPositions.OPEN);
                        onHold = false;
                        setPhase(-1);
                    }
                }
                break;

            // Assumes already HANDLER_HANDOFF_PREP_POS and extendo retracted
            // Transfers brick from intake to handler
            case HAND_OFF_BRICK:
                if (isPhase(1)) {
                    onHold = true;
                    setLiftPositionPreset(LiftPositions.HANDOFF);
                    setPhase(2);
                } else if (isPhase(2)) {
                    if (!liftIsBusy()) {
                        setHandlerGrabberPositionPreset(HandlerGrabberPositions.CLOSED_TIGHT, 350);
//                        setPhase(3);
//                    }
//                } else if (isPhase(3)) {
//                    if (!handlerGrabberIsBusy()) {
                        setIntakeGrabberPositionPreset(IntakeGrabberPositions.OPEN);
                        onHold = false;
                        setPhase(-1);
                    }
                }
                break;

            case LOOKING_POS:
                if (isPhase(1)) {
                    onHold = true;
                    setExtendoPositionPreset(ExtendoPositions.EXTENDED);
                    setIntakeWristPositionPreset(IntakeWristPositions.LOOK);
                    setIntakeSwivelPositionPreset(IntakeSwivelPositions.DEGREES0);
                    setPhase(2);
                } else if (isPhase(2)) {
                    if (!intakeIsBusy()) {
                        setIntakeGrabberPositionPreset(IntakeGrabberPositions.OPEN);
                        onHold = false;
                        setPhase(-1);
                    }
                }
                break;

            case NOT_LOOKING_POS:
                if (isPhase(1)) {
                    onHold = true;
                    setIntakeWristPositionPreset(IntakeWristPositions.UP);
                    setIntakeSwivelPositionPreset(IntakeSwivelPositions.DEGREES0);
//                    setIntakeGrabberPositionPreset(IntakeGrabberPositions.OPEN);
                    setExtendoPositionPreset(ExtendoPositions.RETRACTED);
                    onHold = false;
                    setPhase(-1);
                }
                break;

            case GRAB_SPECIMEN:
                if (isPhase(1)) {
                    onHold = true;
                    setHandlerGrabberPositionPreset(HandlerGrabberPositions.CLOSED_LOOSE, 50);
                    setPhase(2);
                } else if (

                        isPhase(2)) {
                    if (!handlerGrabberIsBusy()) {
                        setHandlerWristPositionPreset(HandlerWristPositions.UP_SPECIMEN);
                        onHold = false;
                        setPhase(-1);
                    }
                }
                break;

            case TELEOP_GRAB_SPECIMEN:
                if (isPhase(1)) {
                    onHold = true;
                    setHandlerGrabberPositionPreset(HandlerGrabberPositions.CLOSED_LOOSE, 50);
                    setPhase(2);
                } else if (isPhase(2)) {
                    if (!handlerGrabberIsBusy()) {
                        setHandlerWristPositionPreset(HandlerWristPositions.UP_SPECIMEN);
                        setPhase(3);
                    }
                } else if (isPhase(3)) {
                    if (!handlerWristIsBusy()) {
                        setLiftPositionPreset(LiftPositions.SPECIMEN_HANG_SETUP);
                        setHandlerArmPositionPreset(HandlerArmPositions.SPECIMEN_HANG);
                        setHandlerWristPositionPreset(HandlerWristPositions.SPECIMEN_HANG);
                        onHold = false;
                        setPhase(-1);
                    }
                }
                break;

            // Assumes grasping specimen
            case HANDLER_HIGH_SPECIMEN_POS:
                if (isPhase(1)) {
                    onHold = true;
                    setLiftPositionPreset(LiftPositions.SPECIMEN_HANG_SETUP);
                    setHandlerGrabberPositionPreset(HandlerGrabberPositions.CLOSED_LOOSE);
//                    setHandlerArmPositionPreset(HandlerArmPositions.SPECIMEN_HANG);
                    setHandlerWristPositionPreset(HandlerWristPositions.SPECIMEN_HANG);
                    onHold = false;
                    setPhase(-1);
                }
                break;

            case HANG_SPECIMEN:
                if (isPhase(1)) {
                    onHold = true;
                    lift.setPositionPreset(LiftPositions.SPECIMEN_HANG);
                    setPhase(2);
                } else if (isPhase(2)) {
                    if (!liftIsBusy()) {
                        setHandlerGrabberPositionPreset(HandlerGrabberPositions.OPEN, 50);
                        onHold = false;
                        setPhase(-1);
                    }
                }
                break;

            // Assumes brick is already gripped tightly
            case HANDLER_HIGH_BUCKET_POS:
                if (isPhase(1)) {
                    onHold = true;
                    if (!handlerGrabberIsBusy()) {
                        setLiftPositionPreset(LiftPositions.HIGH_BUCKET_AUTO);
//                        setHandlerArmPositionPreset(HandlerArmPositions.HIGH_BUCKET);
                        setHandlerWristPositionPreset(HandlerWristPositions.HIGH_BUCKET);
                        onHold = false;
                        setPhase(-1);
                    }
                }
                break;

            case PARKING_AT_SUB_POS:
                if (isPhase(1)) {
                    onHold = true;
                    setLiftPositionPreset(LiftPositions.MIN);
                    setHandlerArmPositionPreset(HandlerArmPositions.SUB_PARKING);
                    setHandlerWristPositionPreset(HandlerWristPositions.SUB_PARKING);
                    setHandlerGrabberPositionPreset(HandlerGrabberPositions.CLOSED_LOOSE);
                    onHold = false;
                    setPhase(-1);
                }
                break;
        }
    }
}

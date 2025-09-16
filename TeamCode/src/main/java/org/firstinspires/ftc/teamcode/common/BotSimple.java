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

public abstract class BotSimple extends Component {
    private Modes currentMode;
    private int currentPhase;
    private boolean onHold = false;

    public BotSimple(OpMode opMode, Telemetry telemetry) {
        super(telemetry);
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

    public boolean onHold() {
        return (onHold);
    }

    protected void logIsBusyStatuses() {
    }
}

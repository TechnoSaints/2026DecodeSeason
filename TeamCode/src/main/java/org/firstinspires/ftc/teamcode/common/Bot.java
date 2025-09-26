package org.firstinspires.ftc.teamcode.common;

import com.qualcomm.hardware.rev.RevTouchSensor;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public abstract class Bot extends Component {
    private Modes currentMode;
    private int currentPhase;
    private boolean onHold = false;

    public Bot(OpMode opMode, Telemetry telemetry) {
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

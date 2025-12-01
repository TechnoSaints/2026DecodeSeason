package org.firstinspires.ftc.teamcode.common;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public abstract class BotSimple extends Component {
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

    public boolean onHold() {
        return (onHold);
    }

    protected void logIsBusyStatuses() {
    }
}

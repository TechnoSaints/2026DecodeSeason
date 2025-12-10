package org.firstinspires.ftc.teamcode.common;

import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public abstract class Component {

    protected Telemetry telemetry;

    public Component(Telemetry telemetry)
    {
        this.telemetry = telemetry;
    }
}

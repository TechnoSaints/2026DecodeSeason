package org.firstinspires.ftc.teamcode.common;

import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public abstract class Component {

    protected Telemetry telemetry;

    private ElapsedTime timer = new ElapsedTime(ElapsedTime.Resolution.MILLISECONDS);

    private double duration = 0;

    public Component(Telemetry telemetry)
    {
        this.telemetry = telemetry;
    }

    protected void setTimer(double duration)
    {
        this.duration = duration;
        timer.reset();
    }

    public boolean isBusy()
    {
        return (timer.milliseconds() < duration);
    }
}

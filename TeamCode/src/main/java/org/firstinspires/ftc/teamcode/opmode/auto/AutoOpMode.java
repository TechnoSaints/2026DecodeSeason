/*package org.firstinspires.ftc.teamcode.opmode.auto;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;

import com.pedropathing.geometry.Pose;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.common.AutoBot;

public abstract class AutoOpMode extends OpMode {
    protected AutoBot bot;
    protected int pathState;
    protected Pose startPose;
    protected ElapsedTime controlTimer = new ElapsedTime(ElapsedTime.Resolution.MILLISECONDS);


    /**
     * This method is called once at the init of the OpMode.
     **/
    /*
    @Override
     */
    /*public void init() {
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
        bot = new AutoBot(this, telemetry);
    }

    /**
     * This method is called continuously after Init while waiting for "play".
     **/
    /*
    public void init_loop() {
        bot.update();
    }

    @Override
    public void start()
    {
        setPathState(0);
    }
    /**
     * This is the main loop of the OpMode, it will run repeatedly after clicking "Play".
     **/
    /*
    @Override
    public void loop() {
        // These loop the movements of the robot
        bot.update();
        autonomousPathUpdate();
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    protected void setPathState(int pState)
    {
        pathState = pState;
    }

    protected abstract void autonomousPathUpdate();
}
*/
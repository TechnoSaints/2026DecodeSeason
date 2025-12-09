package org.firstinspires.ftc.teamcode.common;

import com.qualcomm.hardware.rev.RevTouchSensor;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.common.servos.ServoSimple;

public abstract class Bot extends Component {
    protected Launcher launcher;
    private Storage storage;

    public Bot(OpMode opMode, Telemetry telemetry) {
        super(telemetry);
        launcher = new Launcher(telemetry, opMode.hardwareMap);
        storage = new Storage(telemetry, opMode.hardwareMap);
    }

    public void stopLauncher(){
        launcher.stopLauncher();
    }

    public void shootBalls(){storage.shootBalls();}

    public void intakeBalls(){storage.intakeBalls();}

    public void stopStorage(){storage.stop();}

//    public void kickerLaunch()
//    {
//        kicker.setPositionTicks(kickerLaunchPosition);
//    }
//
//    public void kickerLoad()
//    {
//        kicker.setPositionTicks(kickerLoadPosition);
//    }


    public void update() {
        storage.updateStorage();
        launcher.log();
        storage.log();
    }
}

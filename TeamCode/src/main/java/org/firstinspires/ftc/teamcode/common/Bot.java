package org.firstinspires.ftc.teamcode.common;

import com.qualcomm.hardware.rev.RevTouchSensor;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.robotcore.external.Telemetry;

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

    public void setPosition(double position){
        launcher.setPosition(position);
    }

    public void setSpeed(double power){
        if (power < 0){
            power = 0;
        }
        else if (power > 1){
            power = 1;
        }
        launcher.setVelocity(power);
    }

    public void brakeLauncher(){launcher.brakeLauncher();}

    public int getState(){
        return storage.getState();
    }

    public void storageManualIntake(){
        storage.manualForward();
    }

    public void storageManualEject(){
        storage.manualBackward();
    }

    public char[] getBalls() { return storage.getBalls();}

//    public void kickerLaunch()
//    {
//        kicker.setPositionTicks(kickerLaunchPosition);
//    }
//
//    public void kickerLoad()
//    {
//        kicker.setPositionTicks(kickerLoadPosition);
//    }


    public boolean isBusy() {
        return launcher.motorBusy();
    }
    public boolean launcherIsReady(){return launcher.ready();}
    public void update() {
        storage.updateStorage();
        launcher.log();
        storage.log();
    }
}

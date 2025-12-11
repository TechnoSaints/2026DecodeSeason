package org.firstinspires.ftc.teamcode.common;

import com.qualcomm.hardware.gobilda.GoBildaPinpointDriver;
import com.qualcomm.hardware.rev.RevTouchSensor;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.common.servos.ServoSimple;

public abstract class Bot extends Component {
    protected Launcher launcher;
    private RollerMotor intake;

    private ServoSimple kicker;

    double kickerLoadPosition = 0.57;
    private double kickerLaunchPosition = 0.26;

    private double kickerGatePosition = 0.37;


    public Bot(OpMode opMode, Telemetry telemetry) {
        super(telemetry);
        launcher = new Launcher(telemetry, opMode.hardwareMap);
        intake = new RollerMotor(opMode.hardwareMap, telemetry,"intake");
        kicker = new ServoSimple(opMode.hardwareMap, telemetry,"kicker");

    }

    public void shoot(){
        // Get current position(Location AND Heading)
        // Find distance to goal
        // Find target heading
        // Set Launcher RPM
        // Rotate to heading
        // Kicker load position
    }

 /*   public void setLauncherShortShot() {
        launcher.setShortShot();
    }

    public void setLauncherLongShot() {
        launcher.setLongShot();
    }

    public void setLauncherMediumShot(){
        launcher.setMediumShot();
    }
    public void launcherStop()
    {

        launcher.stop();
    } */

    public void intakeForward() {
        intake.forward();
    }
    public void launcherStop()
    {

        launcher.stop();
    }

    public void intakeReverse() {
        intake.reverse();
    }

    public void intakeStop() {
        intake.stop();
    }


    public void kickerLaunch() {
        kicker.setPositionTicks(kickerLaunchPosition);
    }

    public void kickerLoad() {
        kicker.setPositionTicks(kickerLoadPosition);
    }
    
    public void kickerGate(){kicker.setPositionTicks(kickerGatePosition);}

    public boolean isBusy() {
        return (false);
    }
    public void update() {
    }
}

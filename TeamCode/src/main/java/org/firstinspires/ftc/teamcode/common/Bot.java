package org.firstinspires.ftc.teamcode.common;

import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public abstract class Bot extends Component {
    private LauncherDouble launcher;
    private RollerMotor intake, bottomRoller;
    private RollerCRServo topRoller;
    private ServoSimple kicker;
    private double kickerLoadPosition = 0.45;
    private double kickerLaunchPosition = 0.55;
    private Limelight3A limeLight;

    public Bot(OpMode opMode, Telemetry telemetry) {
        super(telemetry);
        launcher = new LauncherDouble(opMode.hardwareMap, telemetry);
        intake = new RollerMotor(opMode.hardwareMap, telemetry, "intake");
        topRoller = new RollerCRServo(opMode.hardwareMap, telemetry, "upperRoller");
        bottomRoller = new RollerMotor(opMode.hardwareMap, telemetry, "lowerRoller");
//        kicker = new ServoSimple(opMode.hardwareMap, telemetry, "kicker");
//        limeLight = opMode.hardwareMap.get(Limelight3A.class, "limelight");

        // Set pipeline
      //  limeLight.pipelineSwitch(0);

        /*
         * Starts polling for data.  If you neglect to call start(), getLatestResult() will return null.
         */
      //  limeLight.start();
    }

    public void setLauncherShortShot() {
        launcher.setShortShot();
    }

    public void setLauncherLongShot() {
        launcher.setLongShot();
    }

    public void launcherStop() {
        launcher.stop();
    }

    public void intakeForward() {
        intake.forward();
    }

    public void intakeReverse() {
        intake.reverse();
    }

    public void intakeStop() {
        intake.stop();
    }

    public void topRollerForward() {
        topRoller.forward();
    }

    public void topRollerReverse() {
        topRoller.reverse();
    }

    public void topRollerStop() {
        bottomRoller.stop();
    }

    public void bottomRollerForward() {
        bottomRoller.forward();
    }

    public void bottomRollerReverse() {
        bottomRoller.reverse();
    }

    public void bottomRollerStop() {
        bottomRoller.stop();
    }

    public void kickerLaunch() {
        kicker.setPositionTicks(kickerLaunchPosition);
    }

    public void kickerLoad() {
        kicker.setPositionTicks(kickerLoadPosition);
    }

    public boolean isBusy() {
        return (false);
    }
    public void update() {
    }
}

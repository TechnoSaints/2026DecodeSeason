package org.firstinspires.ftc.teamcode.common;

import com.qualcomm.hardware.rev.RevTouchSensor;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.common.servos.ServoSimple;

public abstract class Bot extends Component {
    private LauncherDouble launcher;
    private RollerMotor intake;
    private RollerCRServo topRoller, bottomRoller;
    private ServoSimple kicker;
    private double kickerLoadPosition = 0.45;
    private double kickerLaunchPosition = 0.55;

    public Bot(OpMode opMode, Telemetry telemetry) {
        super(telemetry);
        launcher = new LauncherDouble(opMode.hardwareMap, telemetry);
        intake = new RollerMotor(opMode.hardwareMap, telemetry, "intake");
        topRoller = new RollerCRServo(opMode.hardwareMap, telemetry, "topRoller");
        bottomRoller = new RollerCRServo(opMode.hardwareMap, telemetry, "bottomRoller");
        kicker = new ServoSimple(opMode.hardwareMap, telemetry, "kicker");
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

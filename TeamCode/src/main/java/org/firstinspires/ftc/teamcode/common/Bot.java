package org.firstinspires.ftc.teamcode.common;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.common.servos.ServoSimple;

public abstract class Bot extends Component {
    private LauncherDouble launcher;

    private intakeMotor intake;
    private pusherCRServo pusher;

    private ServoSimple stick;
    private double kickerLoadPosition = 0.45;
    private double kickerLaunchPosition = 0.55;

    public Bot(OpMode opMode, Telemetry telemetry) {
        super(telemetry);
        launcher = new LauncherDouble(opMode.hardwareMap,telemetry);
        intake = new intakeMotor(opMode.hardwareMap, telemetry,"intake");
        pusher = new pusherCRServo(opMode.hardwareMap, telemetry,"pusher");
        stick = new ServoSimple(opMode.hardwareMap, telemetry,"stick");
    }

    public void setLauncherShortShot()
    {
        launcher.setShortShot();
    }

    public void setLauncherLongShot()
    {
        launcher.setLongShot();
    }
    public void launcherStop()
    {
        launcher.stop();
    }

    public void intakeForward()
    {
        intake.forward();
    }

    public void intakeReverse()
    {
        intake.reverse();
    }

    public void intakeStop()
    {
        intake.stop();
    }

    public void pusherStart()
    {
        pusher.forward();
    }

    public void stopPusher()
    {
        pusher.stop();
    }


    public void stickLaunch()
    {
        stick.setPositionTicks(kickerLaunchPosition);
    }

    public void stickLoad()
    {
        stick.setPositionTicks(kickerLoadPosition);
    }


    public void update() {
    }
}

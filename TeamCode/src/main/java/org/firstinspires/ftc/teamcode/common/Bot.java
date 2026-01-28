package org.firstinspires.ftc.teamcode.common;

import com.pedropathing.geometry.Pose;
import com.pedropathing.follower.Follower;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.common.hardwareConfiguration.data.DrivetrainData;
import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import org.firstinspires.ftc.robotcore.external.navigation.Pose3D;
import org.firstinspires.ftc.teamcode.pedroPathing.Constants;

public abstract class Bot extends Component {
    private LauncherDouble launcher;

    private intakeMotor intake;

    private BotSensors colorSensor;

    private BlackWheel pusher;

    private ServoSimple stick;

    public boolean ballPass = false;

    public boolean check = true;


    private double kickerLoadPosition = 0.16;
    private double kickerLaunchPosition = .3;

    private ElapsedTime pauseTimer = new ElapsedTime();
    private ElapsedTime pusherTime = new ElapsedTime();

    // Limelight
    public double TURN_KP = 0.03;               // Adjustable turn gain
    public double MAX_TURN_POWER = 0.40;        // Safety cap
    public double TAG_DEADBAND_DEG = 1.8;       // Acceptable alignment
    public double YAW_SIGN = 1.0;               // <--- CHANGE THIS ONLY
    // Set +1.0 or -1.0 depending on turn direction

    // Limelight

    public Bot(OpMode opMode, Telemetry telemetry) {
        super(telemetry);
        launcher = new LauncherDouble(opMode.hardwareMap,telemetry);
        intake = new intakeMotor(opMode.hardwareMap, telemetry,"intake");
        pusher = new BlackWheel(opMode.hardwareMap, telemetry);
        stick = new ServoSimple(opMode.hardwareMap, telemetry,"stick");
        colorSensor = new BotSensors(opMode.hardwareMap, telemetry);

    }

    public void setLauncherShortShot()
    {
        launcher.setShortShot();
    }

    public void reverseLauncer() {launcher.reverse();}

    public void setLauncherLongShot() {
        launcher.setLongShot();
    }

    public void setLauncherMediumShot() {
        launcher.setMediumShot();
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

    public void pusherStart()
    {
        pusher.forward();
    }

    public void runUsingEncoder()
    {
        pusher.useEncoder();
    }

    public void noEncoder()
    {
        pusher.noEncoder();
    }

    public void setPusherSpeed(double pusherSpeed) {pusher.setSpeed(pusherSpeed);}
    public void pusherReverse()
    {
        pusher.reverse();
    }

    public void stopPusher()
    {
        pusher.stop();
    }

    public void tickBlackWheel(int ticks) {pusher.runForwardInTicks(ticks);}

    public void stickLaunch()
    {
        stick.setPositionTicks(kickerLaunchPosition);
    }

    public void stickLoad()
    {
        stick.setPositionTicks(kickerLoadPosition);
    }

    public void stickLaunchLoad()  {
        pauseTimer.reset();
        stick.setPositionTicks(kickerLaunchPosition);
        while(pauseTimer.milliseconds() < 350)
        {
        }
        stick.setPositionTicks(kickerLoadPosition);
    }

    public void pusherUpdate(){
        if (colorSensor.ballInTop()) {
            pusherTime.reset();
            check = false;
        }

        if (pusherTime.milliseconds() > 250 && check == false) {
            ballPass = true;
            check = true;
        }

        if(ballPass || colorSensor.ballInShot())
        {
            stopPusher();
        } else {
            pusherStart();
        }

        if((ballPass || colorSensor.ballInShot()) && (colorSensor.ballInFirstSensor()) && (!colorSensor.ballInTop()))
        {
            runUsingEncoder();
            tickBlackWheel(-500);
        } else {
            noEncoder();
        }
    }

    public boolean isBusy() {
        return (false);
    }

    public void update() {
        telemetry.addData("Ball Status", ballPass);
    }
}

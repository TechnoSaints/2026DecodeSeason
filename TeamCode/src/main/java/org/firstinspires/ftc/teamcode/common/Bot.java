package org.firstinspires.ftc.teamcode.common;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.common.hardwareConfiguration.data.DrivetrainData;
import org.firstinspires.ftc.teamcode.common.servos.ServoSimple;
import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import org.firstinspires.ftc.robotcore.external.navigation.Pose3D;

public abstract class Bot extends Component {
    private LauncherDouble launcher;

    private Limelight3A limelight;

    private intakeMotor intake;
    private pusherCRServo pusher;

    private ServoSimple stick;
    private double kickerLoadPosition = 0.45;
    private double kickerLaunchPosition = 0.55;

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
        pusher = new pusherCRServo(opMode.hardwareMap, telemetry,"pusher");
        stick = new ServoSimple(opMode.hardwareMap, telemetry,"stick");
        limelight = opMode.hardwareMap.get(Limelight3A.class, "limelight");
        telemetry.setMsTransmissionInterval(11);
        limelight.pipelineSwitch(8);
        limelight.start();
    }

    public void alignBot(Drivetrain drivetrain) {
        LLResult result = limelight.getLatestResult();

        double targetOffsetAngle_Vertical = result.getTy();
        double targetOffsetAngle_Horizontal = result.getTx();

        double perfectAlignmentOffset = 3;
        double movmentNeededX = 1000;
        if (Math.abs(targetOffsetAngle_Horizontal) > perfectAlignmentOffset) {
            if (targetOffsetAngle_Horizontal > 0 || targetOffsetAngle_Horizontal < 0) {
                while (Math.abs(targetOffsetAngle_Horizontal) > perfectAlignmentOffset) {
                    drivetrain.moveDirection(0, 0, targetOffsetAngle_Horizontal);
                }
            }
        } else {
            movmentNeededX = targetOffsetAngle_Horizontal;
        }
        movmentNeededX = targetOffsetAngle_Horizontal;
    }


    public void calcDist() {
        LLResult result = limelight.getLatestResult();

        double targetOffsetAngle_Vertical = result.getTy();

        // how many degrees back is your limelight rotated from perfectly vertical?
        double limelightMountAngleDegrees = 25.0;

        // distance from the center of the Limelight lens to the floor
        double limelightLensHeightInches = 20.0;

        // distance from the target to the floor
        double goalHeightInches = 60.0;

        double angleToGoalDegrees = limelightMountAngleDegrees + targetOffsetAngle_Vertical;
        double angleToGoalRadians = angleToGoalDegrees * (3.14159 / 180.0);

        double distanceFromLimelightToGoalInches = (goalHeightInches - limelightLensHeightInches) / Math.tan(angleToGoalRadians);
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

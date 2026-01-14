package org.firstinspires.ftc.teamcode.common;

import com.arcrobotics.ftclib.geometry.Pose2d;
import com.pedropathing.geometry.Pose;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;
import org.firstinspires.ftc.teamcode.common.hardwareConfiguration.data.GoBilda6000DcMotorData;
import org.firstinspires.ftc.teamcode.common.hardwareConfiguration.setPoints.LauncherSettings;
import org.firstinspires.ftc.teamcode.opmode.FieldLocations;

public class Launcher extends Component {
    private DcMotorEx leftLauncher, rightLauncher;
    private Servo leftAimer, rightAimer;
    private double maxVelocity, maxPosition, minPosition;
    private double targetPosition, targetVelocity;
    private boolean target;

    public Launcher(Telemetry telemetry, HardwareMap hardwareMap) {
        super(telemetry);
        maxVelocity = GoBilda6000DcMotorData.maxTicksPerSec;
        maxPosition = LauncherSettings.maxPosition;
        minPosition = LauncherSettings.minPosition;

        //InitAprilTag(hardwareMap);
        leftLauncher = hardwareMap.get(DcMotorEx.class, "leftLauncher");
        rightLauncher = hardwareMap.get(DcMotorEx.class, "rightLauncher");
        leftAimer = hardwareMap.get(Servo.class, "leftAimer");
        rightAimer = hardwareMap.get(Servo.class, "rightAimer");
        leftLauncher.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        rightLauncher.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        PIDFCoefficients pidfCoefficients = new PIDFCoefficients(515, 0, 0, 12.03);
        leftLauncher.setPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER, pidfCoefficients);
        rightLauncher.setPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER, pidfCoefficients);
        setVelocity(0);
        setPosition(0.25);
    }

    public void stopLauncher() {
        setVelocity(0);
    }

    public double distanceFromLauncher(Pose2D botPose, boolean red){
        Pose2D target;
        if (red){
            target = new Pose2D(DistanceUnit.INCH, 48,-52, AngleUnit.DEGREES, 0);
        }
        else {
            target = new Pose2D(DistanceUnit.INCH, 48,52, AngleUnit.DEGREES, 0);
        }
        return Math.sqrt(
                Math.pow(botPose.getX(DistanceUnit.INCH) - target.getX(DistanceUnit.INCH), 2) +
                        Math.pow(botPose.getY(DistanceUnit.INCH) - target.getY(DistanceUnit.INCH), 2)
        );
    }

    public double distanceFromLauncher(Pose botPose, boolean red){
        Pose target;
        if (red){
            target = new Pose(49,-52,0);
        }
        else {
            target = new Pose(49,52,0);
        }
        return Math.sqrt(
                Math.pow(botPose.getX() - target.getX(), 2) +
                        Math.pow(botPose.getY() - target.getY(), 2)
        );
    }

    public void preloadFromDistance(double distance) {
        setVelocity(LauncherSettings.getVelocityFactor(distance));
        setPosition(LauncherSettings.getLaunchPosition(distance));
    }

    public void update(Pose2D botPose, boolean red, boolean changeTarget){
        if (changeTarget){
            target = !target;
        }
        if (target){
            preloadFromDistance(distanceFromLauncher(botPose, red));
        }
        else {
            stopLauncher();
        }
    }

    public void update(Pose botPose, boolean red, boolean changeTarget){
        if (changeTarget){
            target = !target;
        }
        if (target){
            preloadFromDistance(distanceFromLauncher(botPose, red));
        }
        else {
            stopLauncher();
        }
    }

    public void autoPreload(Pose pose, boolean red){
        preloadFromDistance(distanceFromLauncher(pose, red));
    }

    public void setVelocity(double power) {
        targetVelocity = maxVelocity * power;
        leftLauncher.setVelocity(targetVelocity);
        rightLauncher.setVelocity(targetVelocity);
    }

    public void setPosition(double position) {
        telemetry.addData("Original position", position);
        if (position > maxPosition) {
            targetPosition = maxPosition;
        } else if (position < minPosition) {
            targetPosition = minPosition;
        } else {
            targetPosition = position;
        }
        leftAimer.setPosition(targetPosition);
        rightAimer.setPosition(targetPosition);
    }

    public double getVelocity() {
        return leftLauncher.getVelocity();
    }

    public double getPosition(){ return leftAimer.getPosition(); }

    public boolean ready(){
        return ((getVelocity()-targetVelocity) < 50 && Math.abs(getPosition()-targetPosition) <= 0.02);
    }

    public boolean motorBusy(){
        return (Math.abs(leftLauncher.getVelocity()-targetVelocity) > 50) || (Math.abs(-rightLauncher.getVelocity()-targetVelocity) > 50);
    }

    public void teleopDistanceLog(Pose2D pose, boolean red){
        telemetry.addData("Distance from launcher", distanceFromLauncher(pose, red));
    }

    public void log(){
        telemetry.addData("Target Velocity in Launcher:  ", targetVelocity);
        telemetry.addData("Actual Velocity L:  ", leftLauncher.getVelocity());
        telemetry.addData("Actual Velocity R:  ", rightLauncher.getVelocity());
        telemetry.addData("Target Launch Position in Launcher:  ", targetPosition);
        if (ready()){
            telemetry.addLine("Launcher is ready");
        }
        else {
            telemetry.addLine("Launcher is not ready");
        }
    }
}

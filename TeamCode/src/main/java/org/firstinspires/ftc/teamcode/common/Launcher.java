package org.firstinspires.ftc.teamcode.common;

import com.pedropathing.geometry.Pose;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.common.hardwareConfiguration.data.GoBilda6000DcMotorData;
import org.firstinspires.ftc.teamcode.common.hardwareConfiguration.setPoints.LauncherSettings;
import org.firstinspires.ftc.teamcode.opmode.FieldLocations;

public class Launcher extends Component{
    private DcMotorEx leftLauncher, rightLauncher;
    private Servo leftAimer, rightAimer;
    private double maxVelocity;
    private double targetAngle, targetVelocity;

    public Launcher(Telemetry telemetry, HardwareMap hardwareMap) {
        super(telemetry);
        maxVelocity = GoBilda6000DcMotorData.maxTicksPerSec;
        //InitAprilTag(hardwareMap);
        leftLauncher = hardwareMap.get(DcMotorEx.class, "leftLauncher");
        rightLauncher = hardwareMap.get(DcMotorEx.class, "rightLauncher");
        leftAimer = hardwareMap.get(Servo.class, "leftAimer");
        rightAimer = hardwareMap.get(Servo.class, "rightAimer");
        leftLauncher.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        rightLauncher.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);

    }

    public void stopLauncher() {
        setVelocity(0);
    }

    public double distanceFromLauncher(Pose botPose, boolean red){
        Pose target;
        if (red){
            target = FieldLocations.redTarget;
        }
        else {
            target = FieldLocations.blueTarget;
        }
        return Math.sqrt(
                Math.pow(botPose.getX() - target.getX(), 2) +
                        Math.pow(botPose.getY() - target.getY(), 2)
        );
    }

    public void preloadFromDistance(double distance) {
        setVelocity(LauncherSettings.getVelocityFactor(distance));
        setAngle(LauncherSettings.getLaunchAngle(distance));
    }

    private void setVelocity(double power){
        leftLauncher.setVelocity(maxVelocity * power);
        rightLauncher.setVelocity(maxVelocity * power);
    }

    private void setAngle(double angle){
        leftAimer.setPosition(angle);
        rightAimer.setPosition(angle);
    }

    public double getVelocity(){
        return leftLauncher.getVelocity();
    }

    public double getAngle(){ return leftAimer.getPosition(); }

    public boolean ready(){
        return (Math.abs(getVelocity()-targetVelocity) < 50 && Math.abs(getAngle()-targetAngle) < 0.05);
    }

    public void log(){
        telemetry.addData("targetVelocity:  ", targetVelocity);
        telemetry.addData("Actual Velocity L:  ", leftLauncher.getVelocity());
        telemetry.addData("Actual Velocity R:  ", rightLauncher.getVelocity());
        //       telemetry.addData("PowerL:  ", motorL.getPower());
        //       telemetry.addData("PowerR:  ", motorR.getPower());
        telemetry.addData("targetLaunchPosition:  ", targetAngle);
        telemetry.addData("Actual Position L:  ", leftAimer.getPosition());
        telemetry.addData("Actual Position R:  ", rightAimer.getPosition());
        if (ready()){
            telemetry.addLine("Launcher is ready");
        }
        else {
            telemetry.addLine("Launcher is not ready");
        }
    }
}

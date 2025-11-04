package org.firstinspires.ftc.teamcode.common;

import com.pedropathing.geometry.Pose;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.common.hardwareConfiguration.data.GoBilda6000DcMotorData;
import org.firstinspires.ftc.teamcode.common.hardwareConfiguration.setPoints.AimerPositions;

public class Launcher extends Component{
    private DcMotorEx leftLauncher, rightLauncher;
    private Servo leftAimer, rightAimer;
    private double maxVelocity, targetVelocity;


    public Launcher(Telemetry telemetry, HardwareMap hardwareMap) {
        super(telemetry);
        maxVelocity = GoBilda6000DcMotorData.maxTicksPerSec;
        //InitAprilTag(hardwareMap);
        leftLauncher = hardwareMap.get(DcMotorEx.class, "leftLauncher");
        rightLauncher = hardwareMap.get(DcMotorEx.class, "rightLauncher");
        leftAimer = hardwareMap.get(Servo.class, "leftAimer");
        rightAimer = hardwareMap.get(Servo.class, "rightAimer");

    }

    public void setPositionPreset(AimerPositions position) {
        leftAimer.setPosition(position.getValue());
        rightAimer.setPosition(position.getValue());
    }

    public void setTargetVelocity(int desiredVelocity){
        targetVelocity = desiredVelocity;
    }

    public void preloadLauncher(){
        leftLauncher.setVelocity(targetVelocity);
        rightLauncher.setVelocity(maxVelocity);
    }

    public void preloadLauncherPower(double desiredPower){
        leftLauncher.setVelocity(desiredPower * maxVelocity);
        rightLauncher.setVelocity(desiredPower * maxVelocity);
        telemetry.addLine("Launcher is ready");
    }

    public void stopLauncher() {
        leftLauncher.setVelocity(0);
        rightLauncher.setVelocity(0);
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

    public void preloadFromDistance(Pose botPose, boolean red) {
        double distance = distanceFromLauncher(botPose, red);
    }

    public double getVelocity(){
        return leftLauncher.getVelocity();
    }

    public void isReady(){
        if (getVelocity() == targetVelocity){
            telemetry.addLine("Launcher is ready");
        }
        else {
            telemetry.addLine("Launcher is not ready");
        }
    }
}

package org.firstinspires.ftc.teamcode.common;

import com.pedropathing.follower.Follower;

import com.pedropathing.geometry.BezierLine;
import com.pedropathing.geometry.Pose;
import com.pedropathing.paths.PathChain;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.common.hardwareConfiguration.data.DrivetrainData;
import org.firstinspires.ftc.teamcode.opmode.FieldLocations;
import org.firstinspires.ftc.teamcode.pedroPathing.Constants;

public class AutoBot extends Bot {
    private Follower follower;
    private DrivetrainData drivetrainData;

    public AutoBot(OpMode opMode, Telemetry telemetry) {
        super(opMode, telemetry);
        drivetrainData = new DrivetrainData();
        follower = Constants.createFollower(opMode.hardwareMap);
        follower.setStartingPose(FieldLocations.startPose);
    }

    public void followPath(PathChain path, double maxSpeed) {
        follower.followPath(path, maxSpeed, true);
    }

    public void followPath(PathChain path) {
        follower.followPath(path, true);
    }

    public Follower getFollower() {
        return (follower);
    }

    public void moveManualInches(double axial, double strafe, double heading) {
        Pose targetPose = new Pose(getFollower().getPose().getX() - axial, getFollower().getPose().getY() - strafe, getFollower().getPose().getHeading() + Math.toRadians(heading));

        PathChain targetPath = follower.pathBuilder()
                .addPath(new BezierLine(getFollower().getPose(), targetPose))
                .setLinearHeadingInterpolation(getFollower().getPose().getHeading(), targetPose.getHeading())
                .build();

        followPath(targetPath, 0.8);
    }
    public boolean followerIsBusy() {
        return (follower.isBusy());
    }

    public boolean isBusy() {
        return (super.isBusy() || followerIsBusy());
    }

    public void updateLauncher(Pose launchPose, boolean red, boolean changeTarget){
        launcher.update(launchPose, red, changeTarget);
    }

    public void setMaxPower(double maxPower){
        follower.setMaxPower(maxPower);
    }

    public void update() {
        super.update();
        //launcher.distanceFromLauncher(follower.getPose(), red);
        follower.update();
    }
}

package org.firstinspires.ftc.teamcode.common;

import com.pedropathing.follower.Follower;
import com.pedropathing.localization.Pose;
import com.pedropathing.pathgen.BezierLine;
import com.pedropathing.pathgen.PathChain;
import com.pedropathing.pathgen.Point;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.common.hardwareConfiguration.data.DrivetrainData;
import org.firstinspires.ftc.teamcode.opmode.FieldLocations;
import org.firstinspires.ftc.teamcode.pedroPathing.constants.FConstants;
import org.firstinspires.ftc.teamcode.pedroPathing.constants.LConstants;

public class AutoBot extends Bot {
    private final Follower follower;

    public AutoBot(OpMode opMode, Telemetry telemetry) {
        super(opMode, telemetry);
        follower = new Follower(opMode.hardwareMap, FConstants.class, LConstants.class);
        follower.setStartingPose(FieldLocations.startPose);
        setMode(Modes.AUTO_START_POS);
    }

    public void followPath(PathChain path, boolean holdEnd) {
        follower.followPath(path, holdEnd);
    }

    public Follower getFollower() {
        return (follower);
    }

    public boolean isBusy() {
        return (super.isBusy() || followerIsBusy());
    }

    public boolean followerIsBusy() {
        return (follower.isBusy());
    }

    public void moveManualInches(double axial, double strafe, double heading) {
        Pose targetPose = new Pose(getFollower().getPose().getX() - axial, getFollower().getPose().getY() - strafe, getFollower().getPose().getHeading() + Math.toRadians(heading));

        PathChain targetPath = follower.pathBuilder()
                .addPath(new BezierLine(new Point(getFollower().getPose()), new Point(targetPose)))
                .setLinearHeadingInterpolation(getFollower().getPose().getHeading(), targetPose.getHeading())
                .build();

        followPath(targetPath, true);
    }

    protected void logIsBusy() {
        telemetry.addData("lift: ", liftIsBusy());
        telemetry.addData("wrist: ", handlerWristIsBusy());
        telemetry.addData("arm: ", handlerArmIsBusy());
        telemetry.addData("grabber: ", handlerGrabberIsBusy());
        telemetry.update();
    }

    public void update() {
        super.update();
        follower.update();
//        logIsBusy();
    }
}

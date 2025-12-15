package org.firstinspires.ftc.teamcode.opmode;

import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.BezierLine;
import com.pedropathing.paths.PathChain;
public class PathsTest {

    public static PathChain startToShortShot, shortShotToStack1Setup, stack1SetupToStack1Finish, stack1FinishToShortShot;

    public static void buildPaths(Follower follower) {
        startToShortShot = follower.pathBuilder()
                .addPath(new BezierLine(FieldLocations.startPose, FieldLocations.shortShotPose))
                .setLinearHeadingInterpolation(FieldLocations.startPose.getHeading(), FieldLocations.shortShotPose.getHeading())
                .build();

        shortShotToStack1Setup = follower.pathBuilder()
                .addPath(new BezierLine(FieldLocations.shortShotPose, FieldLocations.stack1SetupPose))
                .setLinearHeadingInterpolation(FieldLocations.shortShotPose.getHeading(), FieldLocations.stack1SetupPose.getHeading())
                .build();

        stack1SetupToStack1Finish = follower.pathBuilder()
                .addPath(new BezierLine(FieldLocations.stack1SetupPose, FieldLocations.stack1FinishPose))
                .setLinearHeadingInterpolation(FieldLocations.stack1SetupPose.getHeading(), FieldLocations.stack1FinishPose.getHeading())
                .build();

        stack1FinishToShortShot = follower.pathBuilder()
                .addPath(new BezierLine(FieldLocations.stack1FinishPose, FieldLocations.shortShotPose))
                .setLinearHeadingInterpolation(FieldLocations.stack1FinishPose.getHeading(), FieldLocations.shortShotPose.getHeading())
                .build();

    }
}

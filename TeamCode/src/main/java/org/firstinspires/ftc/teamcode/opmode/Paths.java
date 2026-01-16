package org.firstinspires.ftc.teamcode.opmode;


import android.graphics.Point;

import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.BezierCurve;
import com.pedropathing.geometry.BezierLine;
import com.pedropathing.geometry.Pose;
import com.pedropathing.paths.PathChain;

import java.lang.reflect.Field;

public class Paths {

    public static PathChain longStartToLongShot, longShotToStack3Setup, stack3SetupToStack3Finish, stack3FinishToLongShot, longShotToStack2Setup, stack2SetupToStack2Finish, stack2FinishToLongShot, longShotToEndFar, goalStartToShortShot, shortShotToStack1Setup, stack1SetupToStack1Finish, stack1FinishToShortShot, shortShotToEndClose;

    public static void buildPaths(Follower follower) {
        longStartToLongShot = follower.pathBuilder()
                .addPath(new BezierLine(FieldLocations.longStartPose, FieldLocations.longShotPose))
                .setLinearHeadingInterpolation(FieldLocations.longStartPose.getHeading(), FieldLocations.longShotPose.getHeading())
                .build();

        longShotToStack3Setup = follower.pathBuilder()
                .addPath(new BezierLine(FieldLocations.longShotPose, FieldLocations.stack3SetupPose))
                .setLinearHeadingInterpolation(FieldLocations.longShotPose.getHeading(), FieldLocations.stack3SetupPose.getHeading())
                .build();

        stack3SetupToStack3Finish = follower.pathBuilder()
                .addPath(new BezierLine(FieldLocations.stack3SetupPose, FieldLocations.stack3FinishPose))
                .setLinearHeadingInterpolation(FieldLocations.stack3SetupPose.getHeading(), FieldLocations.stack3FinishPose.getHeading())
                .build();

        stack3FinishToLongShot = follower.pathBuilder()
                .addPath(new BezierCurve(FieldLocations.stack3FinishPose, FieldLocations.point, FieldLocations.longShotPose))
                .setLinearHeadingInterpolation(FieldLocations.stack3FinishPose.getHeading(), FieldLocations.longShotPose.getHeading())
                .build();

        longShotToStack2Setup = follower.pathBuilder()
                .addPath(new BezierLine(FieldLocations.longShotPose, FieldLocations.stack2SetupPose))
                .setLinearHeadingInterpolation(FieldLocations.longShotPose.getHeading(), FieldLocations.stack2SetupPose.getHeading())
                .build();

        stack2SetupToStack2Finish = follower.pathBuilder()
                .addPath(new BezierLine(FieldLocations.stack2SetupPose, FieldLocations.stack2FinishPose))
                .setLinearHeadingInterpolation(FieldLocations.stack2SetupPose.getHeading(), FieldLocations.stack2FinishPose.getHeading())
                .build();

        stack2FinishToLongShot = follower.pathBuilder()
                .addPath(new BezierLine(FieldLocations.stack2FinishPose, FieldLocations.longShotPose))
                .setLinearHeadingInterpolation(FieldLocations.stack2FinishPose.getHeading(), FieldLocations.longShotPose.getHeading())
                .build();

        longShotToEndFar = follower.pathBuilder()
                .addPath(new BezierLine(FieldLocations.longShotPose, FieldLocations.endFarPose))
                .setLinearHeadingInterpolation(FieldLocations.longShotPose.getHeading(), FieldLocations.endFarPose.getHeading())
                .build();
        goalStartToShortShot = follower.pathBuilder()
                .addPath(new BezierLine(FieldLocations.goalStartPose, FieldLocations.shortShotPose))
                .setLinearHeadingInterpolation(FieldLocations.goalStartPose.getHeading(), FieldLocations.shortShotPose.getHeading())
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
        shortShotToEndClose = follower.pathBuilder()
                .addPath(new BezierLine(FieldLocations.shortShotPose, FieldLocations.endClosePose))
                .setLinearHeadingInterpolation(FieldLocations.shortShotPose.getHeading(), FieldLocations.endClosePose.getHeading())
                .build();


    }
}

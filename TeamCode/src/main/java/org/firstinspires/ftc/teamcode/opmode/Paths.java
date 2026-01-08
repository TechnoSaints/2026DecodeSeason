package org.firstinspires.ftc.teamcode.opmode;


import android.graphics.Point;

import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.BezierLine;
import com.pedropathing.geometry.Pose;
import com.pedropathing.paths.PathChain;

import java.lang.reflect.Field;

public class Paths {

    public static PathChain startToShortShot, shortShotToStack1Setup, stack1SetupToStack1Finish, stack1FinishToShortShot, shortShotToStack2Setup, stack2SetupToStack2Finish, stack2FinishToShortShot, shortShotToStack3Setup, stack3SetupToStack3Finish, stack3FinishToShortShot, startToLongShot, longShotToStack3Setup, stack3FinishTolongShot, longShotToParking;

    public static void buildPaths(Follower follower) {

        //Short Shot Poses

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

        shortShotToStack2Setup = follower.pathBuilder()
                .addPath(new BezierLine(FieldLocations.shortShotPose, FieldLocations.stack2SetupPose))
                .setLinearHeadingInterpolation(FieldLocations.shortShotPose.getHeading(), FieldLocations.stack2SetupPose.getHeading())
                .build();

        stack2SetupToStack2Finish = follower.pathBuilder()
                .addPath(new BezierLine(FieldLocations.stack2SetupPose, FieldLocations.stack2FinishPose))
                .setLinearHeadingInterpolation(FieldLocations.stack2SetupPose.getHeading(), FieldLocations.stack2FinishPose.getHeading())
                .build();

        stack2FinishToShortShot = follower.pathBuilder()
                .addPath(new BezierLine(FieldLocations.stack2FinishPose, FieldLocations.shortShotPose))
                .setLinearHeadingInterpolation(FieldLocations.stack2FinishPose.getHeading(), FieldLocations.shortShotPose.getHeading())
                .build();

        shortShotToStack3Setup = follower.pathBuilder()
                .addPath(new BezierLine(FieldLocations.shortShotPose, FieldLocations.stack3SetupPose))
                .setLinearHeadingInterpolation(FieldLocations.shortShotPose.getHeading(), FieldLocations.stack3SetupPose.getHeading())
                .build();

        stack3SetupToStack3Finish = follower.pathBuilder()
                .addPath(new BezierLine(FieldLocations.stack3SetupPose, FieldLocations.stack3FinishPose))
                .setLinearHeadingInterpolation(FieldLocations.stack3SetupPose.getHeading(), FieldLocations.stack3FinishPose.getHeading())
                .build();

        stack3FinishToShortShot = follower.pathBuilder()
                .addPath(new BezierLine(FieldLocations.stack3FinishPose, FieldLocations.shortShotPose))
                .setLinearHeadingInterpolation(FieldLocations.stack3FinishPose.getHeading(), FieldLocations.shortShotPose.getHeading())
                .build();

        //Long Shot Poses

        startToLongShot = follower.pathBuilder()
                .addPath(new BezierLine(FieldLocations.startPose, FieldLocations.longShotPose))
                .setLinearHeadingInterpolation(FieldLocations.longStartPose.getHeading(), FieldLocations.longShotPose.getHeading())
                .build();

        longShotToStack3Setup = follower.pathBuilder()
                .addPath(new BezierLine(FieldLocations.longShotPose, FieldLocations.stack3SetupPose))
                .setLinearHeadingInterpolation(FieldLocations.longShotPose.getHeading(), FieldLocations.stack3SetupPose.getHeading())
                .build();

        stack3FinishTolongShot = follower.pathBuilder()
                .addPath(new BezierLine(FieldLocations.stack3FinishPose, FieldLocations.longShotPose))
                .setLinearHeadingInterpolation(FieldLocations.stack3FinishPose.getHeading(), FieldLocations.longShotPose.getHeading())
                .build();

        longShotToParking = follower.pathBuilder()
                .addPath(new BezierLine(FieldLocations.longShotPose, FieldLocations.basePose))
                .setLinearHeadingInterpolation(FieldLocations.longShotPose.getHeading(), FieldLocations.basePose.getHeading())
                .build();
    }
}

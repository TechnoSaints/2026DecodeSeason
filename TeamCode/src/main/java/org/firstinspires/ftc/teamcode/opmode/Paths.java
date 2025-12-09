package org.firstinspires.ftc.teamcode.opmode;


import android.graphics.Point;

import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.BezierLine;
import com.pedropathing.geometry.Pose;
import com.pedropathing.paths.PathChain;

import java.lang.reflect.Field;

public class Paths {

    // samples paths
    public static PathChain startToLaunchPosition, endToLaunchPosition;
    // specimen paths

    public static void buildSamplePaths(Follower follower) {
        startToLaunchPosition = follower.pathBuilder()
                .addPath(new BezierLine(FieldLocations.startPose, FieldLocations.bucketPose))
                .setLinearHeadingInterpolation(FieldLocations.startPose.getHeading(), FieldLocations.bucketPose.getHeading())
                .build();

        endToLaunchPosition = follower.pathBuilder()
                .addPath(new BezierLine(FieldLocations.sampleHumanPlayerPose, FieldLocations.bucketPose))
                .setLinearHeadingInterpolation(FieldLocations.sampleHumanPlayerPose.getHeading(), FieldLocations.bucketPose.getHeading())
                .build();
    }


    public static boolean currentLocWithinTolerance(
            Pose target, Pose current, double toleranceX, double toleranceY) {
        return ((Math.abs(target.getX() - current.getX()) < toleranceX) &&
                (Math.abs(target.getY() - current.getY()) < toleranceY));
    }
}

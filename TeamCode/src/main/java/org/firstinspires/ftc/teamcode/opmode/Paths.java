package org.firstinspires.ftc.teamcode.opmode;


import android.graphics.Point;

import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.BezierLine;
import com.pedropathing.geometry.Pose;
import com.pedropathing.paths.PathChain;

import java.lang.reflect.Field;

public class Paths {

    public static PathChain testX, testY, testRotation, doubleTest, tripleTest;
    public static PathChain returnX, returnY, returnRotation, returnDoubleTest;

    public static void buildSamplePaths(Follower follower) {
        testX = follower.pathBuilder()
                .addPath(new BezierLine(FieldLocations.testPose1,
                        FieldLocations.positiveXPose))
                .setConstantHeadingInterpolation(FieldLocations.startPose.getHeading())
                .build();
        testY = follower.pathBuilder()
                .addPath(new BezierLine(FieldLocations.startPose,
                        FieldLocations.postitiveYPose))
                .setConstantHeadingInterpolation(FieldLocations.startPose.getHeading())
                .build();
        testRotation = follower.pathBuilder()
                .addPath(new BezierLine(FieldLocations.startPose,
                        FieldLocations.positiveDegreePose))
                .setConstantHeadingInterpolation(FieldLocations.startPose.getHeading())
                .build();
        doubleTest = follower.pathBuilder()
                .addPath(new BezierLine(FieldLocations.startPose,
                        FieldLocations.testPose1))
                .setConstantHeadingInterpolation(FieldLocations.startPose.getHeading())
                .build();
        tripleTest = follower.pathBuilder()
                .addPath(new BezierLine(FieldLocations.startPose,
                        FieldLocations.testPose2))
                .setConstantHeadingInterpolation(FieldLocations.startPose.getHeading())
                .build();

        returnX = follower.pathBuilder()
                .addPath(new BezierLine(FieldLocations.positiveXPose,
                        FieldLocations.startPose))
                .setConstantHeadingInterpolation(FieldLocations.positiveXPose.getHeading())
                .build();
        returnY = follower.pathBuilder()
                .addPath(new BezierLine(FieldLocations.postitiveYPose,
                        FieldLocations.startPose))
                .setConstantHeadingInterpolation(FieldLocations.postitiveYPose.getHeading())
                .build();
        returnRotation = follower.pathBuilder()
                .addPath(new BezierLine(FieldLocations.positiveDegreePose,
                        FieldLocations.startPose))
                .setConstantHeadingInterpolation(FieldLocations.positiveDegreePose.getHeading())
                .build();
        returnDoubleTest = follower.pathBuilder()
                .addPath(new BezierLine(FieldLocations.testPose1,
                        FieldLocations.startPose))
                .setConstantHeadingInterpolation(FieldLocations.testPose1.getHeading())
                .build();
    }

    public static boolean currentLocWithinTolerance(
            Pose target, Pose current, double toleranceX, double toleranceY) {
        return ((Math.abs(target.getX() - current.getX()) < toleranceX) &&
                (Math.abs(target.getY() - current.getY()) < toleranceY));
    }
}

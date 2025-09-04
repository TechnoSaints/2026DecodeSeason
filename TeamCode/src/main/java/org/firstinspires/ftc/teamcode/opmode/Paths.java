package org.firstinspires.ftc.teamcode.opmode;


import android.graphics.Point;

import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.BezierLine;
import com.pedropathing.geometry.Pose;
import com.pedropathing.paths.PathChain;

import java.lang.reflect.Field;

public class Paths {

    // samples paths
    public static PathChain startToBucket, bucketToSampleSpike1, sampleSpike1ToBucket, bucketToSampleSpike2, sampleSpike2ToBucket, bucketToSampleSpike3Setup,
            sampleSpike3SetupToSampleSpike3, sampleSpike3ToBucket, bucketToSampleParkSetup, sampleParkSetupToSamplePark,
            bucketToSampleHumanPlayer, sampleHumanPlayerToBucket;
    // specimen paths
    public static PathChain startToSubShortSideSetup, subShortSideSetupToSubShortSide, subShortSideToHumanPlayerPark,
            pushSpike1, pushSpike2, pushSpike3, specimenSpike3DropToSpecimenSpike3Grab, specimenSpike3GrabToSubShortSideSetup,
            specimenGrabToSubShortSideSetup, subShortSideToSpecimenGrabSetup, specimenGrabSetupToSpecimenGrab,
            specimenSpike3GrabToSpecimenGrabSetup, subShortSideSetupToSubShortSideStart;

    public static void buildSamplePaths(Follower follower) {
        startToBucket = follower.pathBuilder()
                .addPath(new BezierLine(new Point(FieldLocations.startPose), new Point(FieldLocations.bucketPose)))
                .setLinearHeadingInterpolation(FieldLocations.startPose.getHeading(), FieldLocations.bucketPose.getHeading())
                .build();

        bucketToSampleSpike1 = follower.pathBuilder()
                .addPath(new BezierLine(new Point(FieldLocations.bucketPose), new Point(FieldLocations.sampleSpike1Pose)))
                .setLinearHeadingInterpolation(FieldLocations.bucketPose.getHeading(), FieldLocations.sampleSpike1Pose.getHeading())
                .build();

        sampleSpike1ToBucket = follower.pathBuilder()
                .addPath(new BezierLine(new Point(FieldLocations.sampleSpike1Pose), new Point(FieldLocations.bucketPose)))
                .setLinearHeadingInterpolation(FieldLocations.sampleSpike1Pose.getHeading(), FieldLocations.bucketPose.getHeading())
                .build();

        bucketToSampleSpike2 = follower.pathBuilder()
                .addPath(new BezierLine(new Point(FieldLocations.bucketPose), new Point(FieldLocations.sampleSpike2Pose)))
                .setLinearHeadingInterpolation(FieldLocations.bucketPose.getHeading(), FieldLocations.sampleSpike2Pose.getHeading())
                .build();

        sampleSpike2ToBucket = follower.pathBuilder()
                .addPath(new BezierLine(new Point(FieldLocations.sampleSpike2Pose), new Point(FieldLocations.bucketPose)))
                .setLinearHeadingInterpolation(FieldLocations.sampleSpike2Pose.getHeading(), FieldLocations.bucketPose.getHeading())
                .build();

        bucketToSampleSpike3Setup = follower.pathBuilder()
                .addPath(new BezierLine(new Point(FieldLocations.bucketPose), new Point(FieldLocations.sampleSpike3SetupPose)))
                .setLinearHeadingInterpolation(FieldLocations.bucketPose.getHeading(), FieldLocations.sampleSpike3SetupPose.getHeading())
                .build();

        sampleSpike3SetupToSampleSpike3 = follower.pathBuilder()
                .addPath(new BezierLine(new Point(FieldLocations.sampleSpike3SetupPose), new Point(FieldLocations.sampleSpike3Pose)))
                .setLinearHeadingInterpolation(FieldLocations.sampleSpike3SetupPose.getHeading(), FieldLocations.sampleSpike3Pose.getHeading())
                .build();

        sampleSpike3ToBucket = follower.pathBuilder()
                .addPath(new BezierLine(new Point(FieldLocations.sampleSpike3Pose), new Point(FieldLocations.bucketPose)))
                .setLinearHeadingInterpolation(FieldLocations.sampleSpike3Pose.getHeading(), FieldLocations.bucketPose.getHeading())
                .build();

        bucketToSampleHumanPlayer = follower.pathBuilder()
                .addPath(new BezierLine(new Point(FieldLocations.bucketPose), new Point(FieldLocations.sampleHumanPlayerPose)))
                .setLinearHeadingInterpolation(FieldLocations.bucketPose.getHeading(), FieldLocations.sampleHumanPlayerPose.getHeading())
                .build();

        sampleHumanPlayerToBucket = follower.pathBuilder()
                .addPath(new BezierLine(new Point(FieldLocations.sampleHumanPlayerPose), new Point(FieldLocations.bucketPose)))
                .setLinearHeadingInterpolation(FieldLocations.sampleHumanPlayerPose.getHeading(), FieldLocations.bucketPose.getHeading())
                .build();

        bucketToSampleParkSetup = follower.pathBuilder()
                .addPath(new BezierLine(new Point(FieldLocations.bucketPose), new Point(FieldLocations.sampleParkSetupPose)))
                .setLinearHeadingInterpolation(FieldLocations.bucketPose.getHeading(), FieldLocations.sampleParkSetupPose.getHeading())
                .build();

        sampleParkSetupToSamplePark = follower.pathBuilder()
                .addPath(new BezierLine(new Point(FieldLocations.sampleParkSetupPose), new Point(FieldLocations.sampleParkPose)))
                .setConstantHeadingInterpolation(FieldLocations.sampleParkSetupPose.getHeading())
                .build();
    }

    public static void buildSpecimenPaths(Follower follower) {
        buildSpecimenHangPaths(follower);

        pushSpike1 = follower.pathBuilder()
                .addPath(new BezierLine(
                        new Point(FieldLocations.subShortSidePose),
                        new Point(FieldLocations.subShortClearPose)))
                .setConstantHeadingInterpolation(FieldLocations.subShortClearPose.getHeading())
                .addPath(new BezierCurve(
                        new Point(FieldLocations.subShortClearPose),
                        new Point(FieldLocations.specimenSpike1SetupPose),
                        new Point(FieldLocations.specimenSpike1Pose),
                        new Point(FieldLocations.specimenSpike1DropPose)))
                .setConstantHeadingInterpolation(FieldLocations.specimenSpike1DropPose.getHeading())
                .build();

        pushSpike2 = follower.pathBuilder()
                .addPath(new BezierCurve(
                        new Point(FieldLocations.specimenSpike1DropPose),
                        new Point(FieldLocations.specimenSpike2SetupPose),
                        new Point(FieldLocations.specimenSpike2Pose),
                        new Point(FieldLocations.specimenSpike2DropPose)))
                .setConstantHeadingInterpolation(FieldLocations.specimenSpike2DropPose.getHeading())
                .build();

        pushSpike3 = follower.pathBuilder()
                .addPath(new BezierCurve(
                        new Point(FieldLocations.specimenSpike2DropPose),
                        new Point(FieldLocations.specimenSpike3SetupPose),
                        new Point(FieldLocations.specimenSpike3Pose),
//                        new Point(FieldLocations.specimenSpike3DropPose),
                        new Point(FieldLocations.specimenSpike3GrabPose)))
                .setConstantHeadingInterpolation(FieldLocations.specimenSpike3SetupPose.getHeading())
                .build();

//        specimenSpike3DropToSpecimenSpike3Grab = follower.pathBuilder()
//                .addPath(new BezierLine(new Point(FieldLocations.specimenSpike3DropPose), new Point(FieldLocations.specimenSpike3GrabPose)))
//                .setConstantHeadingInterpolation(FieldLocations.specimenSpike3DropPose.getHeading())
//                .build();

        specimenSpike3GrabToSpecimenGrabSetup = follower.pathBuilder()
                .addPath(new BezierLine(new Point(FieldLocations.specimenSpike3GrabPose), new Point(FieldLocations.specimenGrabSetupPose)))
                .setConstantHeadingInterpolation(FieldLocations.specimenGrabSetupPose.getHeading())
                .build();

        subShortSideToSpecimenGrabSetup = follower.pathBuilder()
                .addPath(new BezierLine(new Point(FieldLocations.subShortSidePose), new Point(FieldLocations.specimenGrabSetupPose)))
                .setConstantHeadingInterpolation(FieldLocations.subShortSidePose.getHeading())
                .build();

        specimenGrabSetupToSpecimenGrab = follower.pathBuilder()
                .addPath(new BezierLine(new Point(FieldLocations.specimenGrabSetupPose), new Point(FieldLocations.specimenGrabPose)))
                .setConstantHeadingInterpolation(FieldLocations.specimenGrabSetupPose.getHeading())
                .build();

        subShortSideToHumanPlayerPark = follower.pathBuilder()
                .addPath(new BezierLine(new Point(FieldLocations.subShortSidePose), new Point(FieldLocations.humanPlayerParkPose)))
                .setConstantHeadingInterpolation(FieldLocations.subShortSidePose.getHeading())
                .build();
    }

    public static void buildSpecimenHangPaths(Follower follower) {

        startToSubShortSideSetup = follower.pathBuilder()
                .addPath(new BezierLine(new Point(FieldLocations.startPose),
                        new Point(FieldLocations.subShortSidePose)))
                .setConstantHeadingInterpolation(FieldLocations.startPose.getHeading())
                .build();

        subShortSideSetupToSubShortSideStart = follower.pathBuilder()
                .addPath(new BezierLine(new Point(FieldLocations.subShortSideSetupPose),
                        new Point(FieldLocations.startSubShortSidePose)))
                .setConstantHeadingInterpolation(FieldLocations.startSubShortSidePose.getHeading())
                .build();

        subShortSideSetupToSubShortSide = follower.pathBuilder()
                .addPath(new BezierLine(new Point(FieldLocations.subShortSideSetupPose),
                        new Point(FieldLocations.subShortSidePose)))
                .setConstantHeadingInterpolation(FieldLocations.subShortSideSetupPose.getHeading())
                .build();

        specimenGrabToSubShortSideSetup = follower.pathBuilder()
                .addPath(new BezierLine(new Point(FieldLocations.specimenGrabPose),
                        new Point(FieldLocations.subShortSideSetupPose)))
                .setConstantHeadingInterpolation(FieldLocations.specimenGrabPose.getHeading())
                .build();

        specimenSpike3GrabToSubShortSideSetup = follower.pathBuilder()
                .addPath(new BezierLine(new Point(FieldLocations.specimenSpike3GrabPose),
                        new Point(FieldLocations.subShortSideSetupPose)))
                .setConstantHeadingInterpolation(FieldLocations.specimenGrabPose.getHeading())
                .build();
    }

    public static boolean currentLocWithinTolerance(
            Pose target, Pose current, double toleranceX, double toleranceY) {
        return ((Math.abs(target.getX() - current.getX()) < toleranceX) &&
                (Math.abs(target.getY() - current.getY()) < toleranceY));
    }
}

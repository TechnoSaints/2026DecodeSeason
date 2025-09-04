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
                .addPath(new BezierLine(FieldLocations.startPose, FieldLocations.bucketPose))
                .setLinearHeadingInterpolation(FieldLocations.startPose.getHeading(), FieldLocations.bucketPose.getHeading())
                .build();

        bucketToSampleSpike1 = follower.pathBuilder()
                .addPath(new BezierLine(FieldLocations.bucketPose, FieldLocations.sampleSpike1Pose))
                .setLinearHeadingInterpolation(FieldLocations.bucketPose.getHeading(), FieldLocations.sampleSpike1Pose.getHeading())
                .build();

        sampleSpike1ToBucket = follower.pathBuilder()
                .addPath(new BezierLine(FieldLocations.sampleSpike1Pose, FieldLocations.bucketPose))
                .setLinearHeadingInterpolation(FieldLocations.sampleSpike1Pose.getHeading(), FieldLocations.bucketPose.getHeading())
                .build();

        bucketToSampleSpike2 = follower.pathBuilder()
                .addPath(new BezierLine(FieldLocations.bucketPose, FieldLocations.sampleSpike2Pose))
                .setLinearHeadingInterpolation(FieldLocations.bucketPose.getHeading(), FieldLocations.sampleSpike2Pose.getHeading())
                .build();

        sampleSpike2ToBucket = follower.pathBuilder()
                .addPath(new BezierLine(FieldLocations.sampleSpike2Pose, FieldLocations.bucketPose))
                .setLinearHeadingInterpolation(FieldLocations.sampleSpike2Pose.getHeading(), FieldLocations.bucketPose.getHeading())
                .build();

        bucketToSampleSpike3Setup = follower.pathBuilder()
                .addPath(new BezierLine(FieldLocations.bucketPose, FieldLocations.sampleSpike3SetupPose))
                .setLinearHeadingInterpolation(FieldLocations.bucketPose.getHeading(), FieldLocations.sampleSpike3SetupPose.getHeading())
                .build();

        sampleSpike3SetupToSampleSpike3 = follower.pathBuilder()
                .addPath(new BezierLine(FieldLocations.sampleSpike3SetupPose, FieldLocations.sampleSpike3Pose))
                .setLinearHeadingInterpolation(FieldLocations.sampleSpike3SetupPose.getHeading(), FieldLocations.sampleSpike3Pose.getHeading())
                .build();

        sampleSpike3ToBucket = follower.pathBuilder()
                .addPath(new BezierLine(FieldLocations.sampleSpike3Pose, FieldLocations.bucketPose))
                .setLinearHeadingInterpolation(FieldLocations.sampleSpike3Pose.getHeading(), FieldLocations.bucketPose.getHeading())
                .build();

        bucketToSampleHumanPlayer = follower.pathBuilder()
                .addPath(new BezierLine(FieldLocations.bucketPose, FieldLocations.sampleHumanPlayerPose))
                .setLinearHeadingInterpolation(FieldLocations.bucketPose.getHeading(), FieldLocations.sampleHumanPlayerPose.getHeading())
                .build();

        sampleHumanPlayerToBucket = follower.pathBuilder()
                .addPath(new BezierLine(FieldLocations.sampleHumanPlayerPose, FieldLocations.bucketPose))
                .setLinearHeadingInterpolation(FieldLocations.sampleHumanPlayerPose.getHeading(), FieldLocations.bucketPose.getHeading())
                .build();

        bucketToSampleParkSetup = follower.pathBuilder()
                .addPath(new BezierLine(FieldLocations.bucketPose, FieldLocations.sampleParkSetupPose))
                .setLinearHeadingInterpolation(FieldLocations.bucketPose.getHeading(), FieldLocations.sampleParkSetupPose.getHeading())
                .build();

        sampleParkSetupToSamplePark = follower.pathBuilder()
                .addPath(new BezierLine(FieldLocations.sampleParkSetupPose, FieldLocations.sampleParkPose))
                .setConstantHeadingInterpolation(FieldLocations.sampleParkSetupPose.getHeading())
                .build();
    }

    public static void buildSpecimenPaths(Follower follower) {
        buildSpecimenHangPaths(follower);
/*
        pushSpike1 = follower.pathBuilder()
                .addPath(new BezierLine(
                        FieldLocations.subShortSidePose,
                        FieldLocations.subShortClearPose))
                .setConstantHeadingInterpolation(FieldLocations.subShortClearPose.getHeading())
                .addPath(new BezierCurve(
                        FieldLocations.subShortClearPose,
                        FieldLocations.specimenSpike1SetupPose,
                        FieldLocations.specimenSpike1Pose,
                        FieldLocations.specimenSpike1DropPose))
                .setConstantHeadingInterpolation(FieldLocations.specimenSpike1DropPose.getHeading())

                .build();

        pushSpike2 = follower.pathBuilder()
                .addPath(new BezierCurve(
                        FieldLocations.specimenSpike1DropPose,
                        FieldLocations.specimenSpike2SetupPose,
                        FieldLocations.specimenSpike2Pose,
                        FieldLocations.specimenSpike2DropPose))
                .setConstantHeadingInterpolation(FieldLocations.specimenSpike2DropPose.getHeading())
                .build();

        pushSpike3 = follower.pathBuilder()
                .addPath(new BezierCurve(
                        FieldLocations.specimenSpike2DropPose,
                        FieldLocations.specimenSpike3SetupPose,
                        FieldLocations.specimenSpike3Pose,
//                        FieldLocations.specimenSpike3DropPose,
                        FieldLocations.specimenSpike3GrabPose))
                .setConstantHeadingInterpolation(FieldLocations.specimenSpike3SetupPose.getHeading())
                .build();

//        specimenSpike3DropToSpecimenSpike3Grab = follower.pathBuilder()
//                .addPath(new BezierLine(FieldLocations.specimenSpike3DropPose, FieldLocations.specimenSpike3GrabPose))
//                .setConstantHeadingInterpolation(FieldLocations.specimenSpike3DropPose.getHeading())
//                .build();

        specimenSpike3GrabToSpecimenGrabSetup = follower.pathBuilder()
                .addPath(new BezierLine(FieldLocations.specimenSpike3GrabPose, FieldLocations.specimenGrabSetupPose))
                .setConstantHeadingInterpolation(FieldLocations.specimenGrabSetupPose.getHeading())
                .build();

        subShortSideToSpecimenGrabSetup = follower.pathBuilder()
                .addPath(new BezierLine(FieldLocations.subShortSidePose, FieldLocations.specimenGrabSetupPose))
                .setConstantHeadingInterpolation(FieldLocations.subShortSidePose.getHeading())
                .build();

        specimenGrabSetupToSpecimenGrab = follower.pathBuilder()
                .addPath(new BezierLine(FieldLocations.specimenGrabSetupPose, FieldLocations.specimenGrabPose))
                .setConstantHeadingInterpolation(FieldLocations.specimenGrabSetupPose.getHeading())
                .build();

        subShortSideToHumanPlayerPark = follower.pathBuilder()
                .addPath(new BezierLine(FieldLocations.subShortSidePose, FieldLocations.humanPlayerParkPose))
                .setConstantHeadingInterpolation(FieldLocations.subShortSidePose.getHeading())
                .build();
*/
    }

    public static void buildSpecimenHangPaths(Follower follower) {

        startToSubShortSideSetup = follower.pathBuilder()
                .addPath(new BezierLine(FieldLocations.startPose,
                        FieldLocations.subShortSidePose))
                .setConstantHeadingInterpolation(FieldLocations.startPose.getHeading())
                .build();

        subShortSideSetupToSubShortSideStart = follower.pathBuilder()
                .addPath(new BezierLine(FieldLocations.subShortSideSetupPose,
                        FieldLocations.startSubShortSidePose))
                .setConstantHeadingInterpolation(FieldLocations.startSubShortSidePose.getHeading())
                .build();

        subShortSideSetupToSubShortSide = follower.pathBuilder()
                .addPath(new BezierLine(FieldLocations.subShortSideSetupPose,
                        FieldLocations.subShortSidePose))
                .setConstantHeadingInterpolation(FieldLocations.subShortSideSetupPose.getHeading())
                .build();

        specimenGrabToSubShortSideSetup = follower.pathBuilder()
                .addPath(new BezierLine(FieldLocations.specimenGrabPose,
                        FieldLocations.subShortSideSetupPose))
                .setConstantHeadingInterpolation(FieldLocations.specimenGrabPose.getHeading())
                .build();

        specimenSpike3GrabToSubShortSideSetup = follower.pathBuilder()
                .addPath(new BezierLine(FieldLocations.specimenSpike3GrabPose,
                        FieldLocations.subShortSideSetupPose))
                .setConstantHeadingInterpolation(FieldLocations.specimenGrabPose.getHeading())
                .build();
    }

    public static boolean currentLocWithinTolerance(
            Pose target, Pose current, double toleranceX, double toleranceY) {
        return ((Math.abs(target.getX() - current.getX()) < toleranceX) &&
                (Math.abs(target.getY() - current.getY()) < toleranceY));
    }
}

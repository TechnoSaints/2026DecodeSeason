package org.firstinspires.ftc.teamcode.opmode;

import com.pedropathing.localization.Pose;

public class FieldLocations {
    public static Pose startPose;
    public final static Pose sampleStartPose = new Pose(0, 2.0, Math.toRadians(90));
    public final static Pose specimenStartPose = new Pose(47.25, 3.0, Math.toRadians(90));

    // position to drop the brick in the top bucket
    public final static Pose bucketPose = new Pose(-17.25, 7.5, Math.toRadians(45));

    // position to grab the brick at the innermost spike mark
    public final static Pose sampleSpike1Pose = new Pose(-8.75, 23.5, Math.toRadians(90));

    // position to grab the brick at the middle spike mark
    public final static Pose sampleSpike2Pose = new Pose(-18.24, 23.5, Math.toRadians(90));

    public final static Pose sampleSpike3SetupPose = new Pose(-14.5, 28, Math.toRadians(180));

    // position to grab the brick at the spike mark closest to the wall
    public final static Pose sampleSpike3Pose = new Pose(-14.5, 37.5, Math.toRadians(180));

    public final static Pose sampleHumanPlayerPose = new Pose(75, 4.5, Math.toRadians(0));

    public final static Pose sampleHumanPlayerReturnSetupPose = new Pose(55, 7.5, Math.toRadians(90));

    public final static Pose sampleParkSetupPose = new Pose(14, 54, Math.toRadians(0));
    // park position
    public final static Pose sampleParkPose = new Pose(20.5, 54, Math.toRadians(0));

    public final static Pose humanPlayerParkPose = new Pose(85, 3, Math.toRadians(90));

    // Specimen hanging poses
    public static Pose subShortSideSetupPose = new Pose(35.5, 32, Math.toRadians(90));
    public static Pose subShortSidePose = new Pose(35.5, 36.5, Math.toRadians(90));
    public static Pose startSubShortSidePose = new Pose(34, 35, Math.toRadians(90));

    // Push poses
    public final static Pose subShortClearPose = new Pose(72, 20, Math.toRadians(90));
    public final static Pose specimenSpike1SetupPose = new Pose(74, 58, Math.toRadians(90));
    public final static Pose specimenSpike1Pose = new Pose(90, 57, Math.toRadians(90));
    public final static Pose specimenSpike1DropPose = new Pose(90, 7, Math.toRadians(90));
    public final static Pose specimenSpike2SetupPose = new Pose(80, 57, Math.toRadians(90));
    public final static Pose specimenSpike2Pose = new Pose(100, 57, Math.toRadians(90));
    public final static Pose specimenSpike2DropPose = new Pose(95, 7, Math.toRadians(90));
    public final static Pose specimenSpike3SetupPose = new Pose(95, 70, Math.toRadians(90));
    public final static Pose specimenSpike3Pose = new Pose(103, 60, Math.toRadians(90));
    public final static Pose specimenSpike3DropPose = new Pose(103, 7, Math.toRadians(90));
    public final static Pose specimenSpike3GrabPose = new Pose(103, 2.25, Math.toRadians(90));

    //
    public final static Pose humanPlayerDropPose = new Pose(98, 7, Math.toRadians(90));

    public final static Pose specimenGrabSetupPose = new Pose(73.5, 7.5, Math.toRadians(90));

    public final static Pose specimenGrabPose = new Pose(73.5, 2, Math.toRadians(90));
}
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

    public static PathChain redS1B1Pickup, redS1B1Shoot, redS1B2Pickup, redS1B2Shoot, redS1B3Pickup, redS1B3Shoot, redS2B1Pickup, redS2B1Shoot, redS2B2Pickup, redS2B2Shoot, redS2B3Pickup, redS2B3Shoot, redS3B1Pickup, redS3B1Shoot, redS3B2Pickup, redS3B2Shoot, redS3B3Pickup, redS3B3Shoot;
    public static PathChain redPark;

    public static PathChain blueS1B1Pickup, blueS1B1Shoot, blueS1B2Pickup, blueS1B2Shoot, blueS1B3Pickup, blueS1B3Shoot, blueS2B1Pickup, blueS2B1Shoot, blueS2B2Pickup, blueS2B2Shoot, blueS2B3Pickup, blueS2B3Shoot, blueS3B1Pickup, blueS3B1Shoot, blueS3B2Pickup, blueS3B2Shoot, blueS3B3Pickup, blueS3B3Shoot;
    public static PathChain bluePark;


    public static void buildRedFarStartPaths(Follower follower){
        redS3B1Pickup = follower.pathBuilder()
                .addPath(new BezierLine(FieldLocations.redBaseStartPose, FieldLocations.redS3B1))
                .setLinearHeadingInterpolation(FieldLocations.redBaseStartPose.getHeading(), FieldLocations.redS3B1.getHeading())
                .build();

        redS3B1Shoot = follower.pathBuilder()
                .addPath(new BezierLine(FieldLocations.redS3B1, FieldLocations.redNearShoot))
                .setLinearHeadingInterpolation(FieldLocations.redS3B1.getHeading(), FieldLocations.redNearShoot.getHeading())
                .build();

        redS3B2Pickup = follower.pathBuilder()
                .addPath(new BezierLine(FieldLocations.redNearShoot, FieldLocations.redS3B2))
                .setLinearHeadingInterpolation(FieldLocations.redNearShoot.getHeading(), FieldLocations.redS3B2.getHeading())
                .build();

        redS3B2Shoot = follower.pathBuilder()
                .addPath(new BezierLine(FieldLocations.redS3B2, FieldLocations.redNearShoot))
                .setLinearHeadingInterpolation(FieldLocations.redS3B2.getHeading(), FieldLocations.redNearShoot.getHeading())
                .build();


        redS3B3Pickup = follower.pathBuilder()
                .addPath(new BezierLine(FieldLocations.redNearShoot, FieldLocations.redS3B3))
                .setLinearHeadingInterpolation(FieldLocations.redNearShoot.getHeading(), FieldLocations.redS3B3.getHeading())
                .build();

        redS3B3Shoot = follower.pathBuilder()
                .addPath(new BezierLine(FieldLocations.redS3B3, FieldLocations.redNearShoot))
                .setLinearHeadingInterpolation(FieldLocations.redS3B3.getHeading(), FieldLocations.redNearShoot.getHeading())
                .build();

        redS2B1Pickup = follower.pathBuilder()
                .addPath(new BezierLine(FieldLocations.redNearShoot, FieldLocations.redS2B1))
                .setLinearHeadingInterpolation(FieldLocations.redNearShoot.getHeading(), FieldLocations.redS2B1.getHeading())
                .build();

        redS2B1Shoot = follower.pathBuilder()
                .addPath(new BezierLine(FieldLocations.redS1B1, FieldLocations.redNearShoot))
                .setLinearHeadingInterpolation(FieldLocations.redS1B1.getHeading(), FieldLocations.redNearShoot.getHeading())
                .build();

        redS2B2Pickup = follower.pathBuilder()
                .addPath(new BezierLine(FieldLocations.redNearShoot, FieldLocations.redS2B2))
                .setLinearHeadingInterpolation(FieldLocations.redNearShoot.getHeading(), FieldLocations.redS2B2.getHeading())
                .build();

        redS2B2Shoot = follower.pathBuilder()
                .addPath(new BezierLine(FieldLocations.redS2B2, FieldLocations.redNearShoot))
                .setLinearHeadingInterpolation(FieldLocations.redS2B2.getHeading(), FieldLocations.redNearShoot.getHeading())
                .build();

        redS2B3Pickup = follower.pathBuilder()
                .addPath(new BezierLine(FieldLocations.redNearShoot, FieldLocations.redS2B3))
                .setLinearHeadingInterpolation(FieldLocations.redNearShoot.getHeading(), FieldLocations.redS2B3.getHeading())
                .build();

        redS2B3Shoot = follower.pathBuilder()
                .addPath(new BezierLine(FieldLocations.redS2B3, FieldLocations.redNearShoot))
                .setLinearHeadingInterpolation(FieldLocations.redS2B3.getHeading(), FieldLocations.redNearShoot.getHeading())
                .build();

        redS1B1Pickup = follower.pathBuilder()
                .addPath(new BezierLine(FieldLocations.redNearShoot, FieldLocations.redS1B1))
                .setLinearHeadingInterpolation(FieldLocations.redNearShoot.getHeading(), FieldLocations.redS1B1.getHeading())
                .build();

        redS1B1Shoot = follower.pathBuilder()
                .addPath(new BezierLine(FieldLocations.redS1B1, FieldLocations.redFarShoot))
                .setLinearHeadingInterpolation(FieldLocations.redS1B1.getHeading(), FieldLocations.redFarShoot.getHeading())
                .build();

        redS1B2Pickup = follower.pathBuilder()
                .addPath(new BezierLine(FieldLocations.redFarShoot, FieldLocations.redS1B2))
                .setLinearHeadingInterpolation(FieldLocations.redFarShoot.getHeading(), FieldLocations.redS1B2.getHeading())
                .build();

        redS1B2Shoot = follower.pathBuilder()
                .addPath(new BezierLine(FieldLocations.redS1B2, FieldLocations.redFarShoot))
                .setLinearHeadingInterpolation(FieldLocations.redS1B2.getHeading(), FieldLocations.redFarShoot.getHeading())
                .build();

        redS1B3Pickup = follower.pathBuilder()
                .addPath(new BezierLine(FieldLocations.redFarShoot, FieldLocations.redS1B3))
                .setLinearHeadingInterpolation(FieldLocations.redFarShoot.getHeading(), FieldLocations.redS1B3.getHeading())
                .build();

        redS1B3Shoot = follower.pathBuilder()
                .addPath(new BezierLine(FieldLocations.redS1B3, FieldLocations.redFarShoot))
                .setLinearHeadingInterpolation(FieldLocations.redS1B3.getHeading(), FieldLocations.redFarShoot.getHeading())
                .build();

    }

    public static void buildBlueFarStartPaths(Follower follower){
        blueS3B1Pickup = follower.pathBuilder()
                .addPath(new BezierLine(FieldLocations.blueBaseStartPose, FieldLocations.blueS3B1))
                .setLinearHeadingInterpolation(FieldLocations.blueBaseStartPose.getHeading(), FieldLocations.blueS3B1.getHeading())
                .build();

        blueS3B1Shoot = follower.pathBuilder()
                .addPath(new BezierLine(FieldLocations.blueS3B1, FieldLocations.blueNearShoot))
                .setLinearHeadingInterpolation(FieldLocations.blueS3B1.getHeading(), FieldLocations.blueNearShoot.getHeading())
                .build();

        blueS3B2Pickup = follower.pathBuilder()
                .addPath(new BezierLine(FieldLocations.blueNearShoot, FieldLocations.blueS3B2))
                .setLinearHeadingInterpolation(FieldLocations.blueNearShoot.getHeading(), FieldLocations.blueS3B2.getHeading())
                .build();

        blueS3B2Shoot = follower.pathBuilder()
                .addPath(new BezierLine(FieldLocations.blueS3B2, FieldLocations.blueNearShoot))
                .setLinearHeadingInterpolation(FieldLocations.blueS3B2.getHeading(), FieldLocations.blueNearShoot.getHeading())
                .build();


        blueS3B3Pickup = follower.pathBuilder()
                .addPath(new BezierLine(FieldLocations.blueNearShoot, FieldLocations.blueS3B3))
                .setLinearHeadingInterpolation(FieldLocations.blueNearShoot.getHeading(), FieldLocations.blueS3B3.getHeading())
                .build();

        blueS3B3Shoot = follower.pathBuilder()
                .addPath(new BezierLine(FieldLocations.blueS3B3, FieldLocations.blueNearShoot))
                .setLinearHeadingInterpolation(FieldLocations.blueS3B3.getHeading(), FieldLocations.blueNearShoot.getHeading())
                .build();

        blueS2B1Pickup = follower.pathBuilder()
                .addPath(new BezierLine(FieldLocations.blueNearShoot, FieldLocations.blueS2B1))
                .setLinearHeadingInterpolation(FieldLocations.blueNearShoot.getHeading(), FieldLocations.blueS2B1.getHeading())
                .build();

        blueS2B1Shoot = follower.pathBuilder()
                .addPath(new BezierLine(FieldLocations.blueS1B1, FieldLocations.blueNearShoot))
                .setLinearHeadingInterpolation(FieldLocations.blueS1B1.getHeading(), FieldLocations.blueNearShoot.getHeading())
                .build();

        blueS2B2Pickup = follower.pathBuilder()
                .addPath(new BezierLine(FieldLocations.blueNearShoot, FieldLocations.blueS2B2))
                .setLinearHeadingInterpolation(FieldLocations.blueNearShoot.getHeading(), FieldLocations.blueS2B2.getHeading())
                .build();

        blueS2B2Shoot = follower.pathBuilder()
                .addPath(new BezierLine(FieldLocations.blueS2B2, FieldLocations.blueNearShoot))
                .setLinearHeadingInterpolation(FieldLocations.blueS2B2.getHeading(), FieldLocations.blueNearShoot.getHeading())
                .build();

        blueS2B3Pickup = follower.pathBuilder()
                .addPath(new BezierLine(FieldLocations.blueNearShoot, FieldLocations.blueS2B3))
                .setLinearHeadingInterpolation(FieldLocations.blueNearShoot.getHeading(), FieldLocations.blueS2B3.getHeading())
                .build();

        blueS2B3Shoot = follower.pathBuilder()
                .addPath(new BezierLine(FieldLocations.blueS2B3, FieldLocations.blueNearShoot))
                .setLinearHeadingInterpolation(FieldLocations.blueS2B3.getHeading(), FieldLocations.blueNearShoot.getHeading())
                .build();

        blueS1B1Pickup = follower.pathBuilder()
                .addPath(new BezierLine(FieldLocations.blueNearShoot, FieldLocations.blueS1B1))
                .setLinearHeadingInterpolation(FieldLocations.blueNearShoot.getHeading(), FieldLocations.blueS1B1.getHeading())
                .build();

        blueS1B1Shoot = follower.pathBuilder()
                .addPath(new BezierLine(FieldLocations.blueS1B1, FieldLocations.blueFarShoot))
                .setLinearHeadingInterpolation(FieldLocations.blueS1B1.getHeading(), FieldLocations.blueFarShoot.getHeading())
                .build();

        blueS1B2Pickup = follower.pathBuilder()
                .addPath(new BezierLine(FieldLocations.blueFarShoot, FieldLocations.blueS1B2))
                .setLinearHeadingInterpolation(FieldLocations.blueFarShoot.getHeading(), FieldLocations.blueS1B2.getHeading())
                .build();

        blueS1B2Shoot = follower.pathBuilder()
                .addPath(new BezierLine(FieldLocations.blueS1B2, FieldLocations.blueFarShoot))
                .setLinearHeadingInterpolation(FieldLocations.blueS1B2.getHeading(), FieldLocations.blueFarShoot.getHeading())
                .build();

        blueS1B3Pickup = follower.pathBuilder()
                .addPath(new BezierLine(FieldLocations.blueFarShoot, FieldLocations.blueS1B3))
                .setLinearHeadingInterpolation(FieldLocations.blueFarShoot.getHeading(), FieldLocations.blueS1B3.getHeading())
                .build();

        blueS1B3Shoot = follower.pathBuilder()
                .addPath(new BezierLine(FieldLocations.blueS1B3, FieldLocations.blueFarShoot))
                .setLinearHeadingInterpolation(FieldLocations.blueS1B3.getHeading(), FieldLocations.blueFarShoot.getHeading())

    public static PathChain startToShortShot, shortShotToStack1Setup, stack1SetupToStack1Finish,
                stack1FinishToShortShot;

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

package org.firstinspires.ftc.teamcode.opmode;


import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.BezierLine;
import com.pedropathing.paths.PathChain;

public class Paths {
    public static PathChain testX, testY, testRotation, doubleTest, tripleTest;
    public static PathChain returnX, returnY, returnRotation, returnDoubleTest;

    public static PathChain startToShortShot, shortShotToStack1Setup, stack1FinishToShortShot;
    public static PathChain startToLongShot, longShotToStack3Setup, stack3FinishToLongShot, longShotToStack2Setup, stack2FinishToLongShot, longShotToStack1Setup, stack1FinishToLongShot, longShotToBase;

    public static PathChain stack1SetupToStack1Finish, stack2SetupToStack2Finish, stack3SetupToStack3Finish;

    public static void buildPaths(Follower follower) {
        // Short
        startToShortShot = follower.pathBuilder()
                .addPath(new BezierLine(FieldLocations.startPose, FieldLocations.shortShotPose))
                .setLinearHeadingInterpolation(FieldLocations.startPose.getHeading(), FieldLocations.shortShotPose.getHeading())
                .build();

        shortShotToStack1Setup = follower.pathBuilder()
                .addPath(new BezierLine(FieldLocations.shortShotPose, FieldLocations.stack1SetupPose))
                .setLinearHeadingInterpolation(FieldLocations.shortShotPose.getHeading(), FieldLocations.stack1SetupPose.getHeading())
                .build();

        stack1FinishToShortShot = follower.pathBuilder()
                .addPath(new BezierLine(FieldLocations.stack1FinishPose, FieldLocations.shortShotPose))
                .setLinearHeadingInterpolation(FieldLocations.stack1FinishPose.getHeading(), FieldLocations.shortShotPose.getHeading())
                .build();

        // Long (stack order = 3,2,1
        startToLongShot = follower.pathBuilder()
                .addPath(new BezierLine(FieldLocations.startPose, FieldLocations.longShotPose))
                .setLinearHeadingInterpolation(FieldLocations.startPose.getHeading(), FieldLocations.longShotPose.getHeading())
                .build();

        longShotToStack3Setup = follower.pathBuilder()
                .addPath(new BezierLine(FieldLocations.shortShotPose, FieldLocations.stack1SetupPose))
                .setLinearHeadingInterpolation(FieldLocations.shortShotPose.getHeading(), FieldLocations.stack1SetupPose.getHeading())
                .build();

        stack3FinishToLongShot = follower.pathBuilder()
                .addPath(new BezierLine(FieldLocations.stack3FinishPose, FieldLocations.longShotPose))
                .setLinearHeadingInterpolation(FieldLocations.stack3FinishPose.getHeading(), FieldLocations.longShotPose.getHeading())
                .build();

        longShotToStack2Setup = follower.pathBuilder()
                .addPath(new BezierLine(FieldLocations.longShotPose, FieldLocations.stack2SetupPose))
                .setLinearHeadingInterpolation(FieldLocations.longShotPose.getHeading(), FieldLocations.stack2SetupPose.getHeading())
                .build();

        stack2FinishToLongShot = follower.pathBuilder()
                .addPath(new BezierLine(FieldLocations.stack2FinishPose, FieldLocations.longShotPose))
                .setLinearHeadingInterpolation(FieldLocations.stack2FinishPose.getHeading(), FieldLocations.longShotPose.getHeading())
                .build();

        longShotToStack1Setup = follower.pathBuilder()
                .addPath(new BezierLine(FieldLocations.longShotPose, FieldLocations.stack1SetupPose))
                .setLinearHeadingInterpolation(FieldLocations.longShotPose.getHeading(), FieldLocations.stack1SetupPose.getHeading())
                .build();

        stack1FinishToLongShot = follower.pathBuilder()
                .addPath(new BezierLine(FieldLocations.stack1FinishPose, FieldLocations.longShotPose))
                .setLinearHeadingInterpolation(FieldLocations.stack1FinishPose.getHeading(), FieldLocations.longShotPose.getHeading())
                .build();

        longShotToBase = follower.pathBuilder()
                .addPath(new BezierLine(FieldLocations.longShotPose, FieldLocations.basePose))
                .setLinearHeadingInterpolation(FieldLocations.longShotPose.getHeading(), FieldLocations.basePose.getHeading())
                .build();


        // Common (setup to finish)
        stack1SetupToStack1Finish = follower.pathBuilder()
                .addPath(new BezierLine(FieldLocations.stack1SetupPose, FieldLocations.stack1FinishPose))
                .setLinearHeadingInterpolation(FieldLocations.stack1SetupPose.getHeading(), FieldLocations.stack1FinishPose.getHeading())
                .build();

        stack2SetupToStack2Finish = follower.pathBuilder()
                .addPath(new BezierLine(FieldLocations.stack2SetupPose, FieldLocations.stack2FinishPose))
                .setLinearHeadingInterpolation(FieldLocations.stack2SetupPose.getHeading(), FieldLocations.stack2FinishPose.getHeading())
                .build();

        stack3SetupToStack3Finish = follower.pathBuilder()
                .addPath(new BezierLine(FieldLocations.stack3SetupPose, FieldLocations.stack3FinishPose))
                .setLinearHeadingInterpolation(FieldLocations.stack3SetupPose.getHeading(), FieldLocations.stack3FinishPose.getHeading())
                .build();

    }
}

package org.firstinspires.ftc.teamcode.opmode;


import com.pedropathing.geometry.Pose;

public class FieldLocations {

    public static Pose startPose, goalPose, goalStartPose, shortShotPose, stack1SetupPose, stack1FinishPose,
            stack2SetupPose, stack2FinishPose, stack3SetupPose, stack3FinishPose, longShotPose, longStartPose, basePose;
    // Right side poses
    public final static Pose goalRightPose = new Pose(132.0, 132.0, Math.toRadians(45));
    public final static Pose goalStartRightPose = new Pose(124, 124.0, Math.toRadians(40));
    public final static Pose shortShotRightPose = new Pose(85, 74, Math.toRadians(49.5));
    public final static Pose stack1SetupRightPose = new Pose(98, 84, Math.toRadians(180));
    public final static Pose stack1FinishRightPose = new Pose(130, 84, Math.toRadians(180));
    public final static Pose stack2SetupRightPose = new Pose(98, 60.0, Math.toRadians(180));
    public final static Pose stack2FinishRightPose = new Pose(140, 60.0, Math.toRadians(180));
    public final static Pose stack3SetupRightPose = new Pose(98, 36.0, Math.toRadians(180));
    public final static Pose stack3FinishRightPose = new Pose(125, 36.0, Math.toRadians(180));
    public final static Pose longShotRightPose = new Pose(88.0, 21.0, Math.toRadians(67.5));
    public final static Pose longStartRightPose = new Pose(90.0, 8.0, Math.toRadians(90));
    public final static Pose baseRightPose = new Pose(36, 36, Math.toRadians(90));

    // Left side poses
    public final static Pose goalLeftPose = new Pose(60, goalRightPose.getY(), Math.toRadians(139.5));
    public final static Pose goalStartLeftPose = new Pose(144 - goalStartRightPose.getX(), goalStartRightPose.getY(), Math.toRadians(140));
    public final static Pose shortShotLeftPose = new Pose(60, 76, Math.toRadians(130.5));
    public final static Pose stack1SetupLeftPose = new Pose(144 - stack1SetupRightPose.getX(), stack1SetupRightPose.getY(), Math.toRadians(0));
    public final static Pose stack1FinishLeftPose = new Pose(144 - stack1FinishRightPose.getX(), stack1FinishRightPose.getY(), Math.toRadians(0));
    public final static Pose stack2SetupLeftPose = new Pose(144 - stack2SetupRightPose.getX(), stack2SetupRightPose.getY(), Math.toRadians(0));
    public final static Pose stack2FinishLeftPose = new Pose(144 - stack2FinishRightPose.getX(), stack2FinishRightPose.getY(), Math.toRadians(0));
    public final static Pose stack3SetupLeftPose = new Pose(144 - stack3SetupRightPose.getX(), stack3SetupRightPose.getY(), Math.toRadians(0));
    public final static Pose stack3FinishLeftPose = new Pose(144 - stack3FinishRightPose.getX(), stack3FinishRightPose.getY(), Math.toRadians(0));
    public final static Pose longShotLeftPose = new Pose(144 - longShotRightPose.getX(), longShotRightPose.getY(), Math.toRadians(112.5));
    public final static Pose longStartLeftPose = new Pose(144 - longStartRightPose.getX(), longStartRightPose.getY(), Math.toRadians(90));
    public final static Pose baseLeftPose = new Pose(144 - baseRightPose.getX(), baseRightPose.getY(), Math.toRadians(90));


    public static void buildPoses(String side, String shortLong) {
        if (side.equals("red")) {
            goalPose = goalRightPose;
            goalStartPose = goalStartRightPose;
            shortShotPose = shortShotRightPose;
            stack1SetupPose = stack1SetupRightPose;
            stack1FinishPose = stack1FinishRightPose;
            stack2SetupPose = stack2SetupRightPose;
            stack2FinishPose = stack2FinishRightPose;
            stack3SetupPose = stack3SetupRightPose;
            stack3FinishPose = stack3FinishRightPose;
            longShotPose = longShotRightPose;
            longStartPose = longStartRightPose;
            basePose = baseLeftPose;
        } else if (side.equals("blue")) {
            goalPose = goalLeftPose;
            goalStartPose = goalStartLeftPose;
            shortShotPose = shortShotLeftPose;
            stack1SetupPose = stack1SetupLeftPose;
            stack1FinishPose = stack1FinishLeftPose;
            stack2SetupPose = stack2SetupLeftPose;
            stack2FinishPose = stack2FinishLeftPose;
            stack3SetupPose = stack3SetupLeftPose;
            stack3FinishPose = stack3FinishLeftPose;
            longShotPose = longShotLeftPose;
            longStartPose = longStartLeftPose;
            basePose = baseRightPose;
        } else {
            throw new RuntimeException("side = " + side);
        }

        if (shortLong.equals("short")) {
            startPose = goalStartPose;
        } else if (shortLong.equals("long")) {
            startPose = longStartPose;
        } else {
            throw new RuntimeException("shortLong = " + shortLong);
        }
    }
}

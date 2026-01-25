package org.firstinspires.ftc.teamcode.opmode;


import com.pedropathing.geometry.BezierPoint;
import com.pedropathing.geometry.Pose;

public class FieldLocations {
    public static Pose startPose, goalPose, goalStartPose, shortShotPose, stack1SetupPose, stack1FinishPose,
            stack2SetupPose, stack2FinishPose, stack3SetupPose, stack3FinishPose, longShotPose, longStartPose, basePose, endFarPose, point, endClosePose;
    // Right side poses
    public final static Pose goalRightPose = new Pose(132.0, 132.0, Math.toRadians(45));
    public final static Pose goalStartRightPose = new Pose(124.0, 124.0, Math.toRadians(217));
    public final static Pose shortShotRightPose = new Pose(100.0, 100.0, Math.toRadians(227.5));
    public final static Pose stack1SetupRightPose = new Pose(90.0, 93.0, Math.toRadians(0));
    public final static Pose stack1FinishRightPose = new Pose(121.0, 93.0, Math.toRadians(0));
    public final static Pose stack2SetupRightPose = new Pose(90.0, 63.0, Math.toRadians(0));
    public final static Pose stack2FinishRightPose = new Pose(121.0, 63.0, Math.toRadians(0));
    public final static Pose stack3SetupRightPose = new Pose(90.0, 40.0, Math.toRadians(0));
    public final static Pose stack3FinishRightPose = new Pose(121.0, 40.0, Math.toRadians(0));
    public final static Pose longShotRightPose = new Pose(80.5, 72.5, Math.toRadians(230.5));
    public final static Pose longStartRightPose = new Pose(90.0, 9.0, Math.toRadians(270));
    public final static Pose baseRightPose = new Pose(36.0, 36.0, Math.toRadians(90));
    public final static Pose endFarRightPose = new Pose(110.0, 72.0, Math.toRadians(330));
    public final static Pose endCloseRightPose = new Pose(90,135,0);
    public final static Pose pointRight = new Pose(80, 53, 150);

    // Left side poses
    public final static Pose goalLeftPose = new Pose(144 - goalRightPose.getX(), goalRightPose.getY(), Math.toRadians(135));
    public final static Pose goalStartLeftPose = new Pose(144 - goalStartRightPose.getX(), goalStartRightPose.getY(), goalStartRightPose.getHeading() + Math.toRadians(90));
    public final static Pose shortShotLeftPose = new Pose(144 - shortShotRightPose.getX(), shortShotRightPose.getY(), shortShotRightPose.getHeading() + Math.toRadians(90));
    public final static Pose stack1SetupLeftPose = new Pose(144 - stack1SetupRightPose.getX(), stack1SetupRightPose.getY() - 10, Math.toRadians(180));
    public final static Pose stack1FinishLeftPose = new Pose(144 - stack1FinishRightPose.getX() - 12, stack1FinishRightPose.getY() - 10, Math.toRadians(180));
    public final static Pose stack2SetupLeftPose = new Pose(144 - stack2SetupRightPose.getX(), stack2SetupRightPose.getY() - 8, Math.toRadians(180));
    public final static Pose stack2FinishLeftPose = new Pose(144 - stack2FinishRightPose.getX() - 12, stack2FinishRightPose.getY() - 8, Math.toRadians(180));
    public final static Pose stack3SetupLeftPose = new Pose(144 - stack3SetupRightPose.getX(), stack3SetupRightPose.getY() - 10, Math.toRadians(180));
    public final static Pose stack3FinishLeftPose = new Pose(144 - stack3FinishRightPose.getX() - 12, stack3FinishRightPose.getY() - 10, Math.toRadians(180));
    public final static Pose longShotLeftPose = new Pose(144 - longShotRightPose.getX() - 1, longShotRightPose.getY() + 7, longShotRightPose.getHeading() + Math.toRadians(81.5));
    public final static Pose longStartLeftPose = new Pose(144 - longStartRightPose.getX(), longStartRightPose.getY(), Math.toRadians(270));
    public final static Pose endFarLeftPose = new Pose(144 - endFarRightPose.getX(), endFarRightPose.getY() - 12, Math.toRadians(230));
    public final static Pose endCloseLeftPose = new Pose(144 - endCloseRightPose.getX(), endCloseRightPose.getY(), Math.toRadians(180));
    public final static Pose baseLeftPose = new Pose(144 - baseRightPose.getX(), baseRightPose.getY(), Math.toRadians(90));
    public final static Pose pointLeft = new Pose(144 - pointRight.getX(), pointRight.getY());


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
            endFarPose = endFarRightPose;
            endClosePose = endCloseRightPose;
            basePose = baseLeftPose;
            point = pointRight;
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
            endFarPose = endFarLeftPose;
            endClosePose = endCloseLeftPose;
            basePose = baseRightPose;
            point = pointLeft;
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

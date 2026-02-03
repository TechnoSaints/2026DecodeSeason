package org.firstinspires.ftc.teamcode.opmode;


import com.pedropathing.geometry.Pose;

import javax.annotation.Nullable;

public class FieldLocations {
    public static Pose startPose, goalPose, goalStartPose, shortShotPose, stack1SetupPose, stack1FinishPose,
            stack2SetupPose, stack2FinishPose, stack3SetupPose, stack3FinishPose, longShotPose, longStartPose, basePose,
            humanStackSetupPose, humanStackFinishPose, gatePose, endPose;
    // Right side
    public final static Pose goalRightPose = new Pose(132.0, 132.0, Math.toRadians(45));
    public final static Pose goalStartRightPose = new Pose(124.0, 124.0, Math.toRadians(45));
    public final static Pose shortShotRightPose = new Pose(84.0, 84.0, Math.toRadians(45));
    public final static Pose stack1SetupRightPose = new Pose(100.0, 83.0, Math.toRadians(0));
    public final static Pose stack1FinishRightPose = new Pose(133.0, 83.0, Math.toRadians(0));
    public final static Pose stack2SetupRightPose = new Pose(100.0, 60.0, Math.toRadians(0));
    public final static Pose stack2FinishRightPose = new Pose(135.0, 60.0, Math.toRadians(0));
    public final static Pose stack3SetupRightPose = new Pose(100.0, 36.0, Math.toRadians(0));
    public final static Pose stack3FinishRightPose = new Pose(135.0, 36.0, Math.toRadians(0));
    public final static Pose longShotRightPose = new Pose(84.0, 19, Math.toRadians(65));
    public final static Pose longStartRightPose = new Pose(88.0, 8.0, Math.toRadians(90));
    public final static Pose baseRightPose = new Pose(36.0, 36.0, Math.toRadians(90));
    public final static Pose humanStackSetupRightPose = new Pose(134, 29, Math.toRadians(-60));
    public final static Pose humanStackFinishRightPose = new Pose(134, 9, Math.toRadians(-60));
    public final static Pose gateRightPose = new Pose(136, 70.5, Math.toRadians(0));

    // Left side
    public final static Pose goalLeftPose =
            new Pose(144 - goalRightPose.getX(), goalRightPose.getY(), Math.toRadians(135));
    public final static Pose goalStartLeftPose =
            new Pose(144 - goalStartRightPose.getX(), goalStartRightPose.getY(), Math.toRadians(135));
    public final static Pose shortShotLeftPose =
            new Pose(144 - shortShotRightPose.getX(), shortShotRightPose.getY(), Math.toRadians(135));
    public final static Pose stack1SetupLeftPose =
            new Pose(144 - stack1SetupRightPose.getX() + 6, stack1SetupRightPose.getY(), Math.toRadians(180));

    public final static Pose stack1FinishLeftPose =
            new Pose(144 - stack1FinishRightPose.getX() + 7.25, stack1FinishRightPose.getY(), Math.toRadians(180));

    public final static Pose stack2SetupLeftPose =
            new Pose(55, stack2SetupRightPose.getY() - 1, Math.toRadians(180));

    public final static Pose stack2FinishLeftPose =
            new Pose(12, stack2FinishRightPose.getY() - 1, Math.toRadians(180));
    public final static Pose stack3SetupLeftPose =
            new Pose(50, 35, Math.toRadians(180));
    public final static Pose stack3FinishLeftPose =
            new Pose(12, 35, Math.toRadians(180));

    public final static Pose longShotLeftPose =
            new Pose(60, 19, Math.toRadians(114));

    public final static Pose longStartLeftPose =
            new Pose(144 - longStartRightPose.getX(), longStartRightPose.getY(), Math.toRadians(90));

    public final static Pose baseLeftPose =
            new Pose(144 - baseRightPose.getX(), baseRightPose.getY(), Math.toRadians(90));

    public final static Pose humanStackSetupLeftPose = new Pose(15, 29, Math.toRadians(-120));
    public final static Pose humanStackFinishLeftPose = new Pose(15, 9, Math.toRadians(-120));
    public final static Pose gateLeftPose = new Pose(136, 70.5, Math.toRadians(180));
    public static Pose shortEndPose, longEndPose;
    public final static Pose nearLeftEndPose = new Pose(38, 132, Math.toRadians(90));
    public final static Pose nearRightEndPose = new Pose(106, 132, Math.toRadians(90));
    public final static Pose farLeftEndPose = new Pose(longStartLeftPose.getX() - 12, longStartLeftPose.getY(), Math.toRadians(90));
    public final static Pose resetLeftPose = new Pose(18, 121.5,Math.toRadians(124));
    public final static Pose resetRightPose = new Pose(126, 121.5, Math.toRadians(34));

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
            basePose = baseRightPose;
            gatePose = gateRightPose;
            humanStackSetupPose = humanStackSetupRightPose;
            humanStackFinishPose = humanStackFinishRightPose;
            longEndPose = baseLeftPose;
            shortEndPose = nearRightEndPose;
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
            basePose = baseLeftPose;
            gatePose = gateLeftPose;
            humanStackSetupPose = humanStackSetupLeftPose;
            humanStackFinishPose = humanStackFinishLeftPose;
            longEndPose = baseRightPose;
            shortEndPose = nearLeftEndPose;
        } else {
            throw new RuntimeException("side = " + side);
        }

        if (shortLong.equals("short")) {
            startPose = goalStartPose;
            endPose = shortEndPose;
        } else if (shortLong.equals("long")) {
            startPose = longStartPose;
            endPose = longEndPose;
        } else {
            throw new RuntimeException("shortLong = " + shortLong);
        }
    }

    public static void buildPoses(String side, String shortLong, boolean teleop) {
        buildPoses(side, shortLong);
        if (teleop){
            startPose = endPose;
        }
    }
}


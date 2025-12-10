package org.firstinspires.ftc.teamcode.opmode;


import com.pedropathing.geometry.Pose;

public class FieldLocations {
    public static Pose startPose, goalPose, goalStartPose, shortShootPose, stack1Pose, stack2Pose, stack3Pose,
            longShotPose, longStartPose, basePose;
    public final static Pose goalRightPose = new Pose(132.0, 132.0, Math.toRadians(45));
    public final static Pose goalStartRightPose = new Pose(124.0, 124.0, Math.toRadians(45));
    public final static Pose shortShootRightPose = new Pose(84.0, 84.0, Math.toRadians(45));
    public final static Pose stack1RightPose = new Pose(108.0, 84.0, Math.toRadians(0));
    public final static Pose stack2RightPose = new Pose(108.0, 60.0, Math.toRadians(0));
    public final static Pose stack3RightPose = new Pose(108.0, 36.0, Math.toRadians(0));
    public final static Pose longShotRightPose = new Pose(86.0, 21.0, Math.toRadians(67.5));
    public final static Pose longStartRightPose = new Pose(90.0, 9.0, Math.toRadians(90));
    public final static Pose baseRightPose = new Pose(36.0, 36.0, Math.toRadians(90));

    public final static Pose goalLeftPose = rightToLeftPose(goalRightPose);
    public final static Pose goalStartLeftPose = rightToLeftPose(goalStartRightPose);
    public final static Pose shortShootLeftPose = rightToLeftPose(shortShootRightPose);
    public final static Pose stack1LeftPose = rightToLeftPose(stack1RightPose);
    public final static Pose stack2LeftPose = rightToLeftPose(stack2RightPose);
    public final static Pose stack3LeftPose = rightToLeftPose(stack3RightPose);
    public final static Pose longShotLeftPose = rightToLeftPose(longShotRightPose);
    public final static Pose longStartLeftPose =
            new Pose(144 - longStartRightPose.getX(), longStartRightPose.getY(), longStartRightPose.getHeading());
    public final static Pose baseLeftPose = rightToLeftPose(baseRightPose);

    public static void buildPoses(String side, String shortLong) {

        if (side.equals("red")) {
            goalPose = goalRightPose;
            goalStartPose = goalStartRightPose;
            shortShootPose = shortShootRightPose;
            stack1Pose = stack1RightPose;
            stack2Pose = stack2RightPose;
            stack3Pose = stack3RightPose;
            longShotPose = longShotRightPose;
            longStartPose = longStartRightPose;
            basePose = baseLeftPose;
        } else if (side.equals("blue")) {
            goalPose = goalLeftPose;
            goalStartPose = goalStartLeftPose;
            shortShootPose = shortShootLeftPose;
            stack1Pose = stack1LeftPose;
            stack2Pose = stack2LeftPose;
            stack3Pose = stack3LeftPose;
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

    private static Pose rightToLeftPose(Pose rightPose) {
        Pose leftPose = new Pose(144 - rightPose.getX(), rightPose.getY(), rightPose.getHeading() + Math.PI / 2.0);
        return (leftPose);
    }
}

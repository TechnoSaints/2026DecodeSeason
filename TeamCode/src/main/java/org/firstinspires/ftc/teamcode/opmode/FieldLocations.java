package org.firstinspires.ftc.teamcode.opmode;


import com.pedropathing.geometry.Pose;

public class FieldLocations {
    public static Pose startPose;
    private static final int inches = 2;
    private static final int feet = 24;
    // x+ = forward
    // y+ = left
    public final static Pose redBaseStartPose = new Pose(55.2,8,90);
    // Spike 1 Pickup (counted from furthest from goal)
    public final static Pose redS1B1 = new Pose(40, 36, 180);
    public final static Pose redS1B2 = new Pose(36, 36, 180);
    public final static Pose redS1B3 = new Pose(30, 36, 180);

    // Spike 2 Pickup
    public final static Pose redS2B1 = new Pose(40, 60, 180);
    public final static Pose redS2B2 = new Pose(36, 60, 180);
    public final static Pose redS2B3 = new Pose(30, 60, 180);

    // Spike 3 Pickup
    public final static Pose redS3B1 = new Pose(40, 84, 180);
    public final static Pose redS3B2 = new Pose(36, 84, 180);
    public final static Pose redS3B3 = new Pose(30, 84, 180);


    // Shoot Positions
    public final static Pose redFarShoot = new Pose(72, 24,60);
    public final static Pose redNearShoot = new Pose(72, 72, 45);

    // Park
    public final static Pose redPark = new Pose(38.7, 33.2, 90);


    // Blue Positions:
    public final static Pose blueBaseStartPose = new Pose(55.2,8,90);
    // Spike 1 Pickup (counted from furthest from goal)
    public final static Pose blueS1B1 = new Pose(104, 36, 0);
    public final static Pose blueS1B2 = new Pose(144-36, 36, 0);
    public final static Pose blueS1B3 = new Pose(144-30, 36, 0);

    // Spike 2 Pickup
    public final static Pose blueS2B1 = new Pose(144-40, 60, 0);
    public final static Pose blueS2B2 = new Pose(144-36, 60, 0);
    public final static Pose blueS2B3 = new Pose(144-30, 60, 0);

    // Spike 3 Pickup
    public final static Pose blueS3B1 = new Pose(144-40, 84, 0);
    public final static Pose blueS3B2 = new Pose(144-36, 84, 0);
    public final static Pose blueS3B3 = new Pose(144-30, 84, 0);


    // Shoot Positions
    public final static Pose blueFarShoot = new Pose(72, 24,120);
    public final static Pose blueNearShoot = new Pose(72, 72, 135);

    // Park
    public final static Pose bluePark = new Pose(105.4, 33.2, 90);


    public final static Pose redGoalStartPose = new Pose(5.5*feet,0.5*feet,0);
    public final static Pose blueGoalStartPose = new Pose(5.5*feet,5.5*feet,0);

    //start far = 48 horizontal, 0 vertical
    // start far right = 96h, 0v
    // start goal left = 12h, 132v
    // start goal right = 132h, 132v

    // Targets (temp.)
    public final static Pose redTarget = new Pose(58, 55, 0);
    public final static Pose blueTarget = new Pose(58, -55, 0);

    //test
    public final static Pose testStartPose = new Pose(0,0,0);
    public final static Pose positiveXPose = new Pose(4*inches, 0, 0);
    public final static Pose postitiveYPose = new Pose(0, 4*inches, 0);
    public final static Pose positiveDegreePose = new Pose(0,0,Math.toRadians(90));
    public final static Pose testPose1 = new Pose(4*inches, 0*inches, 0);
    public final static Pose testPose2 = new Pose(4*inches, 4*inches, Math.toRadians(90));
}
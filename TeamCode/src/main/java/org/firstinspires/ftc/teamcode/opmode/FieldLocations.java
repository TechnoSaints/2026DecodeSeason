package org.firstinspires.ftc.teamcode.opmode;


import com.pedropathing.geometry.Pose;

public class FieldLocations {
    public static Pose startPose;
    private static final int inches = 2;
    private static final int feet = 24;
    // x+ = forward
    // y+ = left
    public final static Pose redBaseStartPose = new Pose(4*feet,0, 0);
    public final static Pose blueBaseStartPose = new Pose(36*feet,0,0);

    public final static Pose redGoalStartPose = new Pose(5.5*feet,0.5*feet,0);
    public final static Pose blueGoalStartPose = new Pose(5.5*feet,5.5*feet,0);

    //test
    public final static Pose testStartPose = new Pose(0,0,0);
    public final static Pose positiveXPose = new Pose(4*inches, 0, 0);
    public final static Pose postitiveYPose = new Pose(0, 4*inches, 0);
    public final static Pose positiveDegreePose = new Pose(0,0,Math.toRadians(90));
    public final static Pose testPose1 = new Pose(4*inches, 0*inches, 0);
    public final static Pose testPose2 = new Pose(4*inches, 4*inches, Math.toRadians(90));
}
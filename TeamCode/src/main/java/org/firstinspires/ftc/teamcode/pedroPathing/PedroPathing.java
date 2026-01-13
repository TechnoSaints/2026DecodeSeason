package org.firstinspires.ftc.teamcode.pedroPathing;

import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.BezierLine;
import com.pedropathing.geometry.Pose;
import com.pedropathing.paths.PathChain;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.pedropathing.util.Timer;

@Autonomous
public class PedroPathing extends OpMode
{
    private Follower follower;

    private Timer pathTimer, opModeTimer;

    public enum Pathstate
    {
        // START POSITION_END POSITION
        // DRIVE = MOVEMENT STATE
        // SHOOT = ATTEMPT TO SCORE ARTIFACTS

        START_POS_TO_SHOOT_POS,
        SHOOT
    }

    Pathstate pathstate;

    private final Pose startPose = new Pose(56, 8, Math.toRadians(90));

    private final Pose shootPose = new Pose(56, 16, Math.toRadians(120));

    private final Pose emptySecretTunnel = new Pose();

    private final Pose startPickupArtifactsFront = new Pose(41.143, 83.527, 180);

    private final Pose endPickupArtifactsFront = new Pose(14.719, 83.527, 180);

    private PathChain pathStartPosShootPos;
    private PathChain ShootPosEmptySecretTunnel;
    private PathChain path;

    public void buildPaths()
    {
        // put in coordinates for start pose and end pose
        pathStartPosShootPos = follower.pathBuilder()
                .addPath(new BezierLine(startPose, shootPose))
                .setLinearHeadingInterpolation(startPose.getHeading(), shootPose.getHeading())
                .build();
        //drive
        //ShootPosEmptySecretTunnel = follower.pathBuilder()
          //      .addPath(new BezierLine(startPose, shootPose))
            //    .setLinearHeadingInterpolation(startPose.getHeading(), shootPose.getHeading())
              //  .build();
        //drive

    }

    public void statePathUpdate()
    {
        switch (pathstate) {
            case START_POS_TO_SHOOT_POS:
                follower.followPath(pathStartPosShootPos, true);
                pathstate = Pathstate.SHOOT;
                break;
        }
    }

    public void setPathState(Pathstate newState)
    {
        pathstate = newState;
        pathTimer.resetTimer();

    }


    @Override
    public void init()
    {
        pathstate = Pathstate.START_POS_TO_SHOOT_POS;
        pathTimer = new Timer();
        opModeTimer = new Timer();
        follower = Constants.createFollower(hardwareMap);

        buildPaths();
        follower.setPose(startPose);

    }

    public void start()
    {
        opModeTimer.resetTimer();
        setPathState(pathstate);
    }

    @Override
    public void loop()
    {
        follower.update();
        statePathUpdate();
    }
}

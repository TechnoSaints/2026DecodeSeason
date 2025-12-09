package org.firstinspires.ftc.teamcode.opmode.auto;

import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.BezierLine;
import com.pedropathing.geometry.Pose;
import com.pedropathing.paths.PathBuilder;
import com.pedropathing.paths.PathChain;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.pedropathing.util.Timer;

import org.firstinspires.ftc.teamcode.pedroPathing.Constants;

@Autonomous
public class PedroSampleAuto extends OpMode{
    private Follower follower;
    private Timer pathTimer, opModeTimer;

    public enum PathState{
        // START Position -> END Position
        //DRIVE > MOVEMENT State
        //SHOOT > SCORE The Artifact
        DRIVE_STARTPOS_SHOOTPOS,
        SHOOT_PRELOAD,

        DRIVE_SHOOTPOS_PREPPICKUP1POS
    }

    PathState pathState;

    private final Pose startPose = new Pose(60, 8, Math.toRadians(90));
    private final Pose shootPose = new Pose(60, 83.445, Math.toRadians(135));

    private final Pose prepPickup1Pose = new Pose(43.17102615694165, 84, Math.toRadians(180));

    private PathChain driveStartPosShootPos, driveShootPosPrepPickup1Pos;

    public void buildPaths() {
        //put in coordinates for starting pose then put in coordinates for ending pose
        driveStartPosShootPos = follower.pathBuilder()
                .addPath(new BezierLine(startPose, shootPose))
                .setLinearHeadingInterpolation(startPose.getHeading(), shootPose.getHeading())
                .build();
        driveShootPosPrepPickup1Pos = follower.pathBuilder()
                .addPath(new BezierLine(shootPose, prepPickup1Pose))
                .setLinearHeadingInterpolation(shootPose.getHeading(), prepPickup1Pose.getHeading())
                .build();

    }

    public void statePathUpdate() {
        switch (pathState) {
            case DRIVE_STARTPOS_SHOOTPOS:
                follower.followPath(driveStartPosShootPos, true);
                setPathState(PathState.SHOOT_PRELOAD); //reset timer and make new state
            case SHOOT_PRELOAD:
                //check is follower done its path
                //check that 5 second is elapsed
                if (!follower.isBusy() && pathTimer.getElapsedTimeSeconds() > 5){
                    //TODO add logic for shooter
                    telemetry.addLine("Done Path 1");
                    follower.followPath(driveShootPosPrepPickup1Pos, true);
                    setPathState(PathState.DRIVE_SHOOTPOS_PREPPICKUP1POS);
                }
                break;
            case DRIVE_SHOOTPOS_PREPPICKUP1POS:
                if (!follower.isBusy()) {
                    telemetry.addLine("Done Path 2");
                }
            default:
                telemetry.addLine("No State Command");
                break;
        }

    }

    public void setPathState(PathState newState) {
        pathState = newState;
        pathTimer.resetTimer();
    }

    @Override
    public void init(){
        pathState = PathState.DRIVE_STARTPOS_SHOOTPOS;
        pathTimer = new Timer();
        opModeTimer = new Timer();
        follower = Constants.createFollower(hardwareMap);
        //TODO add in any other init mechanisms

        buildPaths();
        follower.setPose(startPose);
    }

    public void start(){
        opModeTimer.resetTimer();
        setPathState(pathState);
    }
    @Override
    public void loop(){
        follower.update();
        statePathUpdate();

        telemetry.addData("path state", pathState.toString());
        telemetry.addData("x", follower.getPose().getX());
        telemetry.addData("y", follower.getPose().getY());
        telemetry.addData("heading", follower.getPose().getHeading());
        telemetry.addData("Path time", pathTimer.getElapsedTimeSeconds());
    }
}

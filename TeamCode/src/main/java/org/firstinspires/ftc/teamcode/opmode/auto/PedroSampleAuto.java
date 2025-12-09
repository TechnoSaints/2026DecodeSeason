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

        DRIVE_SHOOTPOS_PREPINTAKE1POS,
        INTAKE_ONE,

        DRIVE_INTAKE1POS_SHOOTPOS,

        SHOOT_INTAKEONE,

        DRIVE_SHOOTPOS_PREPINTAKE2POS,

        INTAKE_TWO

    }

    PathState pathState;

    private final Pose startPose = new Pose(60, 8, Math.toRadians(90));
    private final Pose shootPose = new Pose(60, 83.445, Math.toRadians(135));

    private final Pose prepIntake1Pose = new Pose(43.17102615694165, 84, Math.toRadians(180));
    private final Pose intake1Pose = new Pose(17.38430583501006, 84, Math.toRadians(180));
    private final Pose prepIntake2Pose = new Pose(43.17102615694165, 60, Math.toRadians(180));
    private final Pose intake2Pose = new Pose(17.38430583501006, 60, Math.toRadians(180));

    private PathChain driveStartPosShootPos, driveShootPosPrepIntake1Pos, drivePrepIntake1PosIntake1Pos, driveIntake1PosShootPos, driveShootPosPrepIntake2Pos, drivePrepIntake2PosIntake2Pos;

    public void buildPaths() {
        //put in coordinates for starting pose then put in coordinates for ending pose
        driveStartPosShootPos = follower.pathBuilder()
                .addPath(new BezierLine(startPose, shootPose))
                .setLinearHeadingInterpolation(startPose.getHeading(), shootPose.getHeading())
                .build();
        driveShootPosPrepIntake1Pos = follower.pathBuilder()
                .addPath(new BezierLine(shootPose, prepIntake1Pose))
                .setLinearHeadingInterpolation(shootPose.getHeading(), prepIntake1Pose.getHeading())
                .build();
        drivePrepIntake1PosIntake1Pos = follower.pathBuilder()
                .addPath(new BezierLine(prepIntake1Pose, intake1Pose))
                .setLinearHeadingInterpolation(prepIntake1Pose.getHeading(), intake1Pose.getHeading())
                .build();
        driveIntake1PosShootPos = follower.pathBuilder()
                .addPath(new BezierLine(intake1Pose, shootPose))
                .setLinearHeadingInterpolation(intake1Pose.getHeading(), shootPose.getHeading())
                .build();
        driveShootPosPrepIntake2Pos = follower.pathBuilder()
                .addPath(new BezierLine(shootPose, prepIntake2Pose))
                .setLinearHeadingInterpolation(shootPose.getHeading(), prepIntake2Pose.getHeading())
                .build();
        drivePrepIntake2PosIntake2Pos = follower.pathBuilder()
                .addPath(new BezierLine(prepIntake2Pose, intake2Pose))
                .setLinearHeadingInterpolation(prepIntake2Pose.getHeading(), intake2Pose.getHeading())
                .build();

    }

    public void statePathUpdate() {
        switch (pathState) {
            case DRIVE_STARTPOS_SHOOTPOS:
                follower.followPath(driveStartPosShootPos, true);
                telemetry.addLine("Done Path 1");
                setPathState(PathState.SHOOT_PRELOAD); //reset timer and make new state
            case SHOOT_PRELOAD:
                //check is follower done its path
                //check that 5 second is elapsed
                if (!follower.isBusy() && pathTimer.getElapsedTimeSeconds() > 5){
                    //TODO add logic for shooter
                    setPathState(PathState.DRIVE_SHOOTPOS_PREPINTAKE1POS);
                }
            case DRIVE_SHOOTPOS_PREPINTAKE1POS:
                if (!follower.isBusy()) {
                    follower.followPath(driveShootPosPrepIntake1Pos, true);
                    telemetry.addLine("Done Path 2");
                    setPathState(PathState.INTAKE_ONE);
                }
            case INTAKE_ONE:
                if(!follower.isBusy()) {
                    //TODO Turn on intake
                    follower.followPath(drivePrepIntake1PosIntake1Pos, true);
                    telemetry.addLine("Done Path 3");
                    setPathState(PathState.DRIVE_INTAKE1POS_SHOOTPOS);
                }
            case DRIVE_INTAKE1POS_SHOOTPOS:
                if(!follower.isBusy()) {
                    follower.followPath(driveIntake1PosShootPos, true);
                    telemetry.addLine("Done path 4");
                    setPathState(PathState.SHOOT_INTAKEONE);
                }
            case SHOOT_INTAKEONE:
                if (!follower.isBusy() && pathTimer.getElapsedTimeSeconds() > 5) {
                    //TODO add logic for shooter
                    //TODO Turn off intake
                    setPathState(PathState.DRIVE_SHOOTPOS_PREPINTAKE2POS);
                }
            case DRIVE_SHOOTPOS_PREPINTAKE2POS:
                if(!follower.isBusy()) {
                    follower.followPath(driveShootPosPrepIntake2Pos, true);
                    telemetry.addLine("Done path 5");
                    setPathState(PathState.INTAKE_TWO);
                }
            case INTAKE_TWO:
                if(!follower.isBusy()) {
                    //TODO Turn on intake
                    follower.followPath(drivePrepIntake2PosIntake2Pos, true);
                    telemetry.addLine("Done Path 6");
                    setPathState(PathState.DRIVE_INTAKE1POS_SHOOTPOS);
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

package org.firstinspires.ftc.teamcode.opmode.test;

import com.acmerobotics.dashboard.config.Config;
import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.BezierLine;
import com.pedropathing.geometry.Pose;
import com.pedropathing.paths.Path;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.opmode.FieldLocations;
import org.firstinspires.ftc.teamcode.pedroPathing.Constants;

@Config
@Autonomous(name="Test Follower")
public class FollowerTest extends LinearOpMode {
    Follower follower;
    public void runOpMode(){
        follower = Constants.createFollower(hardwareMap);
        waitForStart();
        follower.followPath(follower.pathBuilder()
                .addPath(new BezierLine(new Pose(0,0,0),
                        new Pose(0,-12,0))
                        )
                .setLinearHeadingInterpolation(0,0)
                .build());
    }
}

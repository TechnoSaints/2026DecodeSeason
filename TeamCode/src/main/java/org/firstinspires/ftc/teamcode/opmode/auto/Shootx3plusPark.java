package org.firstinspires.ftc.teamcode.opmode.auto;

import com.pedropathing.geometry.BezierLine;
import com.pedropathing.geometry.Pose;
import com.pedropathing.paths.PathChain;

import org.firstinspires.ftc.teamcode.opmode.FieldLocations;

public abstract class Shootx3plusPark extends AutoOpMode{
    protected Pose shootPose;

    public Shootx3plusPark() {

    }
    protected void autonomousPathUpdate(){
        /*
        PathChain path = bot.getFollower().pathBuilder()
                .addPath(new BezierLine(FieldLocations.startPose, FieldLocations.shootPose))
                .setLinearHeadingInterpolation(startPose.getHeading(), shootPose.getHeading())
                .build();
        bot.followPath(path, true);
*/
    }
}

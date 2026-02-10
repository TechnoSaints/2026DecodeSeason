package org.firstinspires.ftc.teamcode.opmode.test;

import com.acmerobotics.dashboard.config.Config;
import com.pedropathing.follower.Follower;
import com.pedropathing.ftc.InvertedFTCCoordinates;
import com.pedropathing.ftc.PoseConverter;
import com.pedropathing.geometry.PedroCoordinates;
import com.pedropathing.geometry.Pose;
import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;


import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;
import org.firstinspires.ftc.robotcore.external.navigation.Pose3D;
import org.firstinspires.ftc.robotcore.external.navigation.Position;
import org.firstinspires.ftc.teamcode.pedroPathing.Constants;

@Config
@TeleOp(name = "Limelight Test", group = "Linear OpMode")
public class limeLightTest extends LinearOpMode {

    private Limelight3A limelight;
    private Follower follower;
    private ElapsedTime timer;
    boolean limelightActive = false;

    double limelightMountAngleDegrees = 25.0;

    double limelightLensHeightInches = 16.0;

    double angleToGoalDegrees;

    double angleToGoalRadians;

    double distanceFromLimelightToGoalInches;



    @Override
    public void runOpMode() throws InterruptedException
    {
        limelight = hardwareMap.get(Limelight3A.class, "limelight");
        follower = Constants.createFollower(hardwareMap);
        timer = new ElapsedTime(30);

        telemetry.setMsTransmissionInterval(11);

        limelight.pipelineSwitch(0);
        limelight.start();

        /*
         * Starts polling for data.
         */
        waitForStart();

        while (opModeIsActive() && !isStopRequested()) {
            if (true) {
                LLResult result = limelight.getLatestResult();
                if (result != null) {
                    if (result.isValid()) {
                        Pose3D pose3D = result.getBotpose();
                        Position botPose = pose3D.getPosition().toUnit(DistanceUnit.INCH);
                      //  angleToGoalDegrees = limelightMountAngleDegrees + result.getTy();

                        telemetry.addData("tx", result.getTx());
                        telemetry.addData("ty", result.getTy());
                        telemetry.addData("Bot Pose", botPose.toString());
                        telemetry.addData("Heading", pose3D.getOrientation().getYaw(AngleUnit.DEGREES));

                        Pose2D pose2D = new Pose2D(DistanceUnit.INCH, botPose.x, botPose.y, AngleUnit.DEGREES, pose3D.getOrientation().getYaw(AngleUnit.DEGREES));
                        Pose followerPose = PoseConverter.pose2DToPose(pose2D, InvertedFTCCoordinates.INSTANCE);
                        followerPose.getAsCoordinateSystem(PedroCoordinates.INSTANCE);
                        follower.setPose(followerPose);
                        timer.reset();
                    }
                }
            }
            follower.update();
            telemetry.addData("Follower Pose", follower.getPose().toString());
            telemetry.update();
        }
    }
}
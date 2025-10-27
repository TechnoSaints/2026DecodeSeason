// Limelight Targeting & Estimating Distance Documentation: https://docs.limelightvision.io/docs/docs-limelight/tutorials/tutorial-estimating-distance
package org.firstinspires.ftc.teamcode.opmode.test;

import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.navigation.Pose3D;
import org.firstinspires.ftc.robotcore.external.navigation.YawPitchRollAngles;

@Autonomous(name="AprilTagLimelightTEST")
public class AprilTagLimeLightTEST extends OpMode {
    private Limelight3A limelight;
    private IMU imu;

    public void init() {
        limelight = hardwareMap.get(Limelight3A.class, "limelight"); // Change "limelight" to what it was in config
        limelight.pipelineSwitch(8); // Change to our pipeline, setup tutorial -> https://www.youtube.com/watch?v=slt0fIq-a2E
        imu = hardwareMap.get(IMU.class, "imu"); // If error make sure our CHUB has the value set to "imu"
        RevHubOrientationOnRobot revHubOrientationOnRobot = new RevHubOrientationOnRobot(
                RevHubOrientationOnRobot.LogoFacingDirection.LEFT, // Change this value to match the logo facing way on our robot
                RevHubOrientationOnRobot.UsbFacingDirection.FORWARD // Change this value to match the usb facing way on our robot
        );
        imu.initialize(new IMU.Parameters(revHubOrientationOnRobot)); // inits the imu
    }

    // Limelight eats energy so should start manually, if there is a delay on start remove this and move limelight.start() to init
    public void start() {
        limelight.start();
    }

    // Constantly get data from limelight
    public void loop() {
        YawPitchRollAngles orientation = imu.getRobotYawPitchRollAngles();
        limelight.updateRobotOrientation(orientation.getYaw()); // Tells limelight what yaw is currently
        LLResult llResult = limelight.getLatestResult(); // pulls data from limelight
        if (llResult != null && llResult.isValid()) { // FYI: First time we pull the limelight it will likely return a null
            Pose3D botPose = llResult.getBotpose_MT2();
            telemetry.addData("Target X (mm)", llResult.getTx());
            telemetry.addData("Target Y (mm)", llResult.getTy());
            telemetry.addData("Target Area (% of cam)", llResult.getTa());
            telemetry.addData("Bot Pose", botPose.toString());
            telemetry.addData("Yaw", botPose.getOrientation().getYaw());

        }
    }
}

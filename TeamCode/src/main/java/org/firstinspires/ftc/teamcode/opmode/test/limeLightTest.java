package org.firstinspires.ftc.teamcode.opmode.test;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;


import org.firstinspires.ftc.robotcore.external.navigation.Pose3D;

@Config
@TeleOp(name = "Limelight Test", group = "Linear OpMode")
public class limeLightTest extends LinearOpMode {

    private Limelight3A limelight;

    @Override
    public void runOpMode() throws InterruptedException
    {
        limelight = hardwareMap.get(Limelight3A.class, "limelight");

        telemetry.setMsTransmissionInterval(11);

        limelight.pipelineSwitch(8);

        /*
         * Starts polling for data.
         */
        limelight.start();


        waitForStart();

        while (opModeIsActive()) {
            LLResult result = limelight.getLatestResult();
//            NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
//            NetworkTableEntry ty = table.getEntry("ty");
//            double targetOffsetAngle_Vertical = ty.getDouble(0.0);

            // ^ No need for networktable

            double targetOffsetAngle_Vertical = result.getTy();
            double targetOffsetAngle_Horizontal = result.getTx();

            double perfectAlignmentOffset = 3;
            double movmentNeededX = 1000;
            if (Math.abs(targetOffsetAngle_Horizontal) > perfectAlignmentOffset) {
                if (targetOffsetAngle_Horizontal > 0) { // limelight is to the right of bot
                    // Move the bot right
                    while (Math.abs(targetOffsetAngle_Horizontal) > perfectAlignmentOffset) {

                    }
                } else if (targetOffsetAngle_Horizontal < 0) { // limelight is to the left of bot
                    // Move the bot left
                }
            } else {
                movmentNeededX = targetOffsetAngle_Horizontal;
            }
            movmentNeededX = targetOffsetAngle_Horizontal;

            // how many degrees back is your limelight rotated from perfectly vertical?
            double limelightMountAngleDegrees = 25.0;

            // distance from the center of the Limelight lens to the floor
            double limelightLensHeightInches = 20.0;

            // distance from the target to the floor
            double goalHeightInches = 60.0;

            double angleToGoalDegrees = limelightMountAngleDegrees + targetOffsetAngle_Vertical;
            double angleToGoalRadians = angleToGoalDegrees * (3.14159 / 180.0);

            //calculate distance
            double distanceFromLimelightToGoalInches = (goalHeightInches - limelightLensHeightInches) / Math.tan(angleToGoalRadians);
            if (result != null) {
                if (result.isValid()) {
                    Pose3D botpose = result.getBotpose();
                    telemetry.addData("tx", result.getTx());
                    telemetry.addData("ty", result.getTy());
                    telemetry.addData("Botpose", botpose.toString());
                    telemetry.addData("Distance", distanceFromLimelightToGoalInches);
                    telemetry.addData("Angle Off", movmentNeededX);
                    telemetry.update();
                }
            }
        }
    }
}
package org.firstinspires.ftc.teamcode.opmode.test;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.common.Drivetrain;
import org.firstinspires.ftc.teamcode.common.hardwareConfiguration.data.DrivetrainData;
import org.firstinspires.ftc.teamcode.common.hardwareConfiguration.data.GoBilda435DcMotorData;

public class EncoderTest extends LinearOpMode {
    private Drivetrain drivetrain;
    @Override
    public void runOpMode() {
        drivetrain = new Drivetrain(this, hardwareMap, telemetry, new DrivetrainData(), new GoBilda435DcMotorData());
        waitForStart();
        if (opModeIsActive() && !isStopRequested()){
            drivetrain.moveForwardForDistance(24);
        }
    }

}

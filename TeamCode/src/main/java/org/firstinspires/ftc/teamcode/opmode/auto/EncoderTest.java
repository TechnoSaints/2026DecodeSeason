package org.firstinspires.ftc.teamcode.opmode.auto;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.common.Drivetrain;
import org.firstinspires.ftc.teamcode.common.hardwareConfiguration.data.DrivetrainData;
import org.firstinspires.ftc.teamcode.common.hardwareConfiguration.data.GoBilda312DcMotorData;

@Config
@Autonomous(name="Encoder Test")
public class EncoderTest extends LinearOpMode {
    private Drivetrain drivetrain;
    private GoBilda312DcMotorData motorData;
    private DrivetrainData drivetrainData;
    public void runOpMode() throws InterruptedException{
        drivetrainData = new DrivetrainData();
        motorData = new GoBilda312DcMotorData();
        drivetrain = new Drivetrain(hardwareMap, telemetry, drivetrainData, motorData);
        waitForStart();
        drivetrain.moveForwardForDistance(12);
        drivetrain.turnToHeading(40);
        drivetrain.strafeRightForDistance(5);
        sleep(2000);
        drivetrain.stop();
    }

}

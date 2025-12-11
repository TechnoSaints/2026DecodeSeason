package org.firstinspires.ftc.teamcode.opmode.auto;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.common.Drivetrain;
import org.firstinspires.ftc.teamcode.common.hardwareConfiguration.data.DrivetrainData;
import org.firstinspires.ftc.teamcode.common.hardwareConfiguration.data.GoBilda435DcMotorData;

@Config
@Autonomous(name="Far Red Leave")
public class FarRedLeave extends LinearOpMode {
    private Drivetrain drivetrain;
    private GoBilda435DcMotorData motorData;
    private DrivetrainData drivetrainData;
    public void runOpMode() throws InterruptedException{
        drivetrainData = new DrivetrainData();
        motorData = new GoBilda435DcMotorData();
        drivetrain = new Drivetrain(this, hardwareMap, telemetry, drivetrainData, motorData);
        waitForStart();
        drivetrain.moveDirection(-1, -1.0, 0.0);
        sleep(2000);
        drivetrain.stop();
    }

}

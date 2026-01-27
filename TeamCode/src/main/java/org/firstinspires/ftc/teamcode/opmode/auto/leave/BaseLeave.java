package org.firstinspires.ftc.teamcode.opmode.auto.leave;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.common.Drivetrain;
import org.firstinspires.ftc.teamcode.common.hardwareConfiguration.data.DrivetrainData;
import org.firstinspires.ftc.teamcode.common.hardwareConfiguration.data.GoBilda435DcMotorData;

@Config
@Autonomous(name="\uD83D\uDFE1Base Leave (neutral)", group = "leave")
public class BaseLeave extends LinearOpMode {
    private Drivetrain drivetrain;
    private GoBilda435DcMotorData motorData;
    private DrivetrainData drivetrainData;
    public void runOpMode() throws InterruptedException{
        drivetrainData = new DrivetrainData();
        motorData = new GoBilda435DcMotorData();
        drivetrain = new Drivetrain(this, hardwareMap, telemetry, drivetrainData, motorData);
        waitForStart();
        drivetrain.moveDirection(1.0, 0.0, 0.0);
        sleep(1000);
        drivetrain.stop();
    }

}

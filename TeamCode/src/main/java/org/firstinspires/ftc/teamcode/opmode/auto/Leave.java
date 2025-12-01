package org.firstinspires.ftc.teamcode.opmode.auto;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.common.AutoBot;
import org.firstinspires.ftc.teamcode.common.Bot;
import org.firstinspires.ftc.teamcode.common.Drivetrain;
import org.firstinspires.ftc.teamcode.common.TeleopBotSimple;
import org.firstinspires.ftc.teamcode.common.hardwareConfiguration.data.DrivetrainData;
import org.firstinspires.ftc.teamcode.common.hardwareConfiguration.data.GoBilda435DcMotorData;
import org.firstinspires.ftc.teamcode.common.hardwareConfiguration.data.GoBilda6000DcMotorData;

@Config
@Autonomous(name="Base Leave")
public class Leave extends LinearOpMode {
    private Drivetrain drivetrain;
    private GoBilda435DcMotorData motorData;
    private DrivetrainData drivetrainData;
    public void runOpMode() throws InterruptedException{
        drivetrainData = new DrivetrainData();
        motorData = new GoBilda435DcMotorData();
        drivetrain = new Drivetrain(hardwareMap, telemetry, drivetrainData, motorData);
        waitForStart();
        drivetrain.moveDirection(1.0, 0.0, 0.0);
        sleep(1000);
        drivetrain.stop();
    }

}

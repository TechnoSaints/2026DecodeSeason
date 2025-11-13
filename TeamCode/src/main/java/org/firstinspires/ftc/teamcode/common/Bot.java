package org.firstinspires.ftc.teamcode.common;


import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.common.hardwareConfiguration.data.GoBilda435DcMotorData;
import org.firstinspires.ftc.teamcode.common.hardwareConfiguration.data.DrivetrainData;

public abstract class Bot extends Component {
    private LinearOpMode opMode;
    private Drivetrain drivetrain;
    DcMotorEx intake, leftLauncher, rightLauncher;
    Servo kicker, spinner;


    public Bot(LinearOpMode opMode, Telemetry telemetry) {

        super(telemetry);
        drivetrain = new Drivetrain(opMode, telemetry, new DrivetrainData(), new GoBilda435DcMotorData());
        intake = hardwareMap.get(DcMotorEx.class, "intake");
        leftLauncher = hardwareMap.get(DcMotorEx.class, "leftLauncher");
        rightLauncher = hardwareMap.get(DcMotorEx.class, "rightLauncher");
        kicker = hardwareMap.get(Servo.class, "kicker");
        spinner = hardwareMap.get(Servo.class, "spinner");
    }
    public void creepDirection(double axial, double strafe, double yaw) {
        drivetrain.creepDirection(axial, strafe, yaw);
    }

    public void moveDirection(double axial, double strafe, double yaw) {
        drivetrain.moveDirection(axial, strafe, yaw);
    }
    public void setToFastPower() {
        drivetrain.setToFastTeleopPower();
    }

    public void setToMediumPower(){drivetrain.setToMediumTeleopPower();}

    public void setToSlowPower() {
        drivetrain.setToSlowTeleopPower();
    }
    public void stopDrive() {
        drivetrain.moveDirection(0, 0, 0);
    }
        }

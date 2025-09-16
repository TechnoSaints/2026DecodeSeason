package org.firstinspires.ftc.teamcode.opmode.test;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@Config
@TeleOp(name = "WheelDirectionTest")
public class WheelDirectionTest extends LinearOpMode {
    DcMotor frontleft, frontright, rearleft, rearright;
    @Override

    public void runOpMode() throws InterruptedException {
        frontleft = hardwareMap.get(DcMotor.class, "leftFrontDrive");
        frontright = hardwareMap.get(DcMotor.class, "rightFrontDrive");
        rearleft = hardwareMap.get(DcMotor.class, "leftRearDrive");
        rearright = hardwareMap.get(DcMotor.class, "rightRearDrive");
        waitForStart();
        while (opModeIsActive()){
            if (gamepad1.a){
                frontleft.setPower(1);
            }
            else {
                frontleft.setPower(0);
            }
            if (gamepad1.b){
                frontright.setPower(1);
            }
            else {
                frontright.setPower(0);
            }
            if (gamepad1.x){
                rearleft.setPower(1);
            }
            else {
                rearleft.setPower(0);
            }
            if (gamepad1.y){
                rearright.setPower(1);
            }
            else {
                rearright.setPower(0);
            }
        }
    }
}

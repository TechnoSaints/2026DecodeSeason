package org.firstinspires.ftc.teamcode.opmode.auto;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@Autonomous(name="Decode Auto", group="Autonomous")
public class DecodeAuto extends LinearOpMode
{
    private DcMotor frontLeftMotor, frontRightMotor, backLeftMotor, backRightMotor, wheelMotor;
    private Servo leftServo, rightServo;


    @Override
    public void runOpMode() throws InterruptedException {
        // Init hardware
        frontLeftMotor = hardwareMap.get(DcMotor.class, "frontLeftMotor");
        frontRightMotor = hardwareMap.get(DcMotor.class, "frontRightMotor");
        backLeftMotor = hardwareMap.get(DcMotor.class, "backLeftMotor");
        backRightMotor = hardwareMap.get(DcMotor.class, "backRightMotor");
        wheelMotor = hardwareMap.get(DcMotor.class, "wheelMotor");
        leftServo = hardwareMap.get(Servo.class, "leftServo");
        rightServo = hardwareMap.get(Servo.class, "rightServo");

        backLeftMotor.setDirection(DcMotor.Direction.REVERSE);

        waitForStart();
        //Movement
        frontLeftMotor.setPower(0.3);
        frontRightMotor.setPower(0.3);
        backRightMotor.setPower(0.3);
        backLeftMotor.setPower(0.3);
        sleep(2000);
        frontRightMotor.setPower(0);
        frontLeftMotor.setPower(0);
        backRightMotor.setPower(0);
        backLeftMotor.setPower(0);



    }


}

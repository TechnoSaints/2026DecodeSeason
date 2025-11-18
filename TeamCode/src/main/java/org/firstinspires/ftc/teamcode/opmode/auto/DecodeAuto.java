package org.firstinspires.ftc.teamcode.opmode.auto;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@Autonomous(name="Decode Auto", group="Autonomous")
public class DecodeAuto extends LinearOpMode
{

    @Override
    public void runOpMode() throws InterruptedException {

        // Init hardware
        DcMotor frontLeftMotor = hardwareMap.get(DcMotor.class, "frontLeftMotor");
        DcMotor frontRightMotor = hardwareMap.get(DcMotor.class, "frontRightMotor");
        DcMotor backLeftMotor = hardwareMap.get(DcMotor.class, "backLeftMotor");
        DcMotor backRightMotor = hardwareMap.get(DcMotor.class, "backRightMotor");
        DcMotor wheelMotor = hardwareMap.get(DcMotor.class, "wheelMotor");
        Servo leftServo = hardwareMap.get(Servo.class, "leftServo");
        Servo rightServo = hardwareMap.get(Servo.class, "rightServo");

        frontLeftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        // Setting encoders
        frontLeftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontRightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backLeftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backRightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        wheelMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        backLeftMotor.setDirection(DcMotor.Direction.REVERSE);

        waitForStart();
        //Movement forward
        frontLeftMotor.setPower(0.5);
        frontRightMotor.setPower(0.5);
        backRightMotor.setPower(0.5);
        backLeftMotor.setPower(0.5);
        sleep(1200);
        frontRightMotor.setPower(0);
        frontLeftMotor.setPower(0);
        backRightMotor.setPower(0);
        backLeftMotor.setPower(0);

        sleep(1000);
        //Turning to shoot
        frontLeftMotor.setPower(-0.3);
        frontRightMotor.setPower(0.3);
        backRightMotor.setPower(0.3);
        backLeftMotor.setPower(-0.3);
        sleep(350);
        frontLeftMotor.setPower(0);
        frontRightMotor.setPower(0);
        backRightMotor.setPower(0);
        backLeftMotor.setPower(0);

        for (int i = 0; i < 3; i++)
        {
            leftServo.setPosition(1.0);
            rightServo.setPosition(0.0);
            wheelMotor.setPower(0.60);
            sleep(3000);
            wheelMotor.setPower(0.0);
            leftServo.setPosition(0.0);
            rightServo.setPosition(1.0);
            sleep(1000);
        }

        frontLeftMotor.setPower(-0.3);
        frontRightMotor.setPower(0.3);
        backRightMotor.setPower(0.3);
        backLeftMotor.setPower(-0.3);
        sleep(1000);
        frontLeftMotor.setPower(0);
        frontRightMotor.setPower(0);
        backRightMotor.setPower(0);
        backLeftMotor.setPower(0);

        frontLeftMotor.setPower(0.3);
        frontRightMotor.setPower(0.3);
        backLeftMotor.setPower(0.3);
        backRightMotor.setPower(0.3);
        sleep(900);
    }

}




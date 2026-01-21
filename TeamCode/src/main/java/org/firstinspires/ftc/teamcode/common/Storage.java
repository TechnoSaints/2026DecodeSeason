package org.firstinspires.ftc.teamcode.common;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.NormalizedColorSensor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.TouchSensor;
import com.qualcomm.robotcore.util.ElapsedTime;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

import java.util.Arrays;

public class Storage extends Component {
    private CRServo upperRoller;
    private DcMotorEx intake, lowerRoller;
    private NormalizedColorSensor ball0, ball1, ball2;
    private Servo gate;
    public int state = 0;
    private ElapsedTime timer;
    private static final int INTAKE_DELAY_MS = 250;
    private static final int SHOOT_DELAY_MS = 100;
    private char[] balls = new char[3]; // these balls are massive - Nathaniel Hoaglen aden's king

    public Storage(Telemetry telemetry, HardwareMap hardwareMap) {
        super(telemetry);
        balls[0] = 'X';
        balls[1] = 'X';
        balls[2] = 'X';
        lowerRoller = hardwareMap.get(DcMotorEx.class, "lowerRoller");
        upperRoller = hardwareMap.get(CRServo.class, "upperRoller");
        lowerRoller.setDirection(DcMotorSimple.Direction.REVERSE);
        lowerRoller.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        intake = hardwareMap.get(DcMotorEx.class, "intake");
        ball0 = hardwareMap.get(NormalizedColorSensor.class, "ball0");
        ball1 = hardwareMap.get(NormalizedColorSensor.class, "colorBall1");
        ball2 = hardwareMap.get(NormalizedColorSensor.class, "colorBall2");
        //gate = hardwareMap.get(Servo.class, "gate");
        ball1.setGain(2);
        ball2.setGain(2);
        timer = new ElapsedTime();
    }


    public void intakeBalls() {
        if (state <= 0) {
            if (balls[0] != 'X' && balls[1] == 'X' && balls[2] == 'X') {
                state = 4;
            }
            else if (balls[0] != 'X' && balls[1] != 'X' && balls[2] == 'X') {
                state = 6;
            }
            else {
                state = 1;
            }
        }
    }

    public void updateStorage() {
        switch (state) {
            case 1:
                if (!ballFind0(ball0)){
                    intake.setPower(0.7); //garrett was here
                    lowerRoller.setPower(0.6);
                    upperRoller.setPower(0.1);
                    state = 2;
                }
                else {
                    state = 3;
                }
                break;
            case 2:
                if (ballFind0(ball0)){
                    state = 3;
                }
                break;
            case 3:
                balls[0] = 'O';
                state = 4;
                break;
            case 4:
                intake.setPower(0.7);
                lowerRoller.setPower(0.4);
                upperRoller.setPower(0);
                state = 5;
                break;
            case 5:
                if (ballFind(ball1)) {
                    balls[1] = 'O';
                    state = 6;
                }
                break;
            case 6:
                intake.setPower(0.6);
                lowerRoller.setPower(0);
                upperRoller.setPower(0);
                state = 7;
                break;
            case 7:
                if (ballFind(ball2)){
                    balls[2] = 'O';
                    state = 0;
                }
                break;
            case -1:
                /*intake.setPower(0);
                lowerRoller.setPower(0);
                upperRoller.setPower(1);
                state = -2;*/
                intake.setPower(0.7);
                lowerRoller.setPower(0.7);
                upperRoller.setPower(0.7);
                state = -2;
                break;
            case -2:
                findBalls();
                if (balls[0] == 'O' || balls[1] == 'O' || balls[2] == 'O'){
                   timer.reset();
                }
                else if (balls[0] == 'X' && balls[1] == 'X' && balls[2] == 'X' && timer.milliseconds() > 1000){
                    state = 0;
                }
                    break;
            case 0:
                gate.;
                intake.setPower(0);
                lowerRoller.setPower(0);
                upperRoller.setPower(0);
                break;
            // Manual eject
            case -3:
                intake.setPower(-1);
                lowerRoller.setPower(-1);
                upperRoller.setPower(-1);
                break;
            // Manual intake
            case -4:
                intake.setPower(1);
                lowerRoller.setPower(1);
                upperRoller.setPower(1);
                break;
        }
    }

    public boolean ballFind0(NormalizedColorSensor sensor){
        if (((DistanceSensor) sensor).getDistance(DistanceUnit.CM) < 1){
            return true;
        }
        else if (sensor.getNormalizedColors().alpha < 0.1){
            return (((DistanceSensor) sensor).getDistance(DistanceUnit.CM) <= 8);
        }
        else {
            return false;
        }
    }
    
    public boolean ballFind(NormalizedColorSensor sensor){
        return (sensor.getNormalizedColors().red > 0.01 || sensor.getNormalizedColors().green > 0.01 || sensor.getNormalizedColors().blue > 0.01);
    }

    public void shootBalls() {
        state = -1;
    }
    
    public void stop(){
        state = 0;
    }

    public int getState() {
        return state;
    }

    public char[] getBalls(){ return balls; }

    public void findBalls(){
        if (ballFind0(ball0)){
            balls[0] = 'O';
        } else {
            balls[0] = 'X';
        }
        if (ballFind(ball1)){
            balls[1] = 'O';
        } else {
            balls[1] = 'X';
        }
        if (ballFind(ball2)){
            balls[2] = 'O';
        } else {
            balls[2] = 'X';
        }
    }

    public void manualForward(){
        state = -4;
    }

    public void manualBackward(){
        state = -3;
    }

    public void log() {
        telemetry.addData("State", state);
        telemetry.addData("Balls", Arrays.toString(balls));
    }
}
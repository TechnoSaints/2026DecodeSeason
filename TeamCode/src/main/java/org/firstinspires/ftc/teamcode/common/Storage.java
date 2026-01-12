package org.firstinspires.ftc.teamcode.common;

import android.graphics.Color;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.NormalizedColorSensor;
import com.qualcomm.robotcore.hardware.NormalizedRGBA;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.TouchSensor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;

import java.util.Arrays;

public class Storage extends Component {
    private CRServo upperRoller;
    private DcMotorEx intake, lowerRoller;
    private TouchSensor ball0;
    private NormalizedColorSensor ball1, ball2;
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
        ball0 = hardwareMap.get(TouchSensor.class, "ball0");
        ball1 = hardwareMap.get(NormalizedColorSensor.class, "colorBall1");
        ball2 = hardwareMap.get(NormalizedColorSensor.class, "colorBall2");
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
                intake.setPower(1); //garrett was here
                lowerRoller.setPower(1);
                upperRoller.setPower(1);
                state = 2;
                break;
            case 2:
                if (ball0.isPressed()){
                    state = 3;
                }
                break;
            case 3:
                balls[0] = 'O';
                state = 4;
                break;
            case 4:
                intake.setPower(1);
                lowerRoller.setPower(0.5);
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
                intake.setPower(1);
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
                intake.setPower(1);
                lowerRoller.setPower(1);
                upperRoller.setPower(1);
                state = -2;
                break;
            case -2:
                if (!ball0.isPressed() && !ballFind(ball1) && !ballFind(ball2)) {
                    balls[0] = 'X';
                    balls[1] = 'X';
                    balls[2] = 'X';
                    state = 0;
                }
                    break;
            case -3:
                if (ball0.isPressed()){
                    state = -4;
                }
                break;
            case -4:
                state = -5;
                break;
            case -5:
                intake.setPower(1);
                lowerRoller.setPower(0.5);
                upperRoller.setPower(0);
                state = -6;
                break;
            case -6:
                if (true) {
                    state = 0;
                    balls[2] = 'X';
                }
                break;
            case -7:
                intake.setPower(0);
                lowerRoller.setPower(0);
                upperRoller.setPower(1);
                state = -8;
                break;
            case -8:
                if (!ball0.isPressed()) {
                    intake.setPower(0); //garrett was here
                    lowerRoller.setPower(1);
                    upperRoller.setPower(1);
                    state = -9;
                }
                break;
            case -9:
                if (ball0.isPressed()){
                    state = -10;
                }
                break;
            case -10:
                balls[1] = 'X';
                state = 0;
                break;
            case -11:
                intake.setPower(0);
                lowerRoller.setPower(0);
                upperRoller.setPower(1);
                state = -12;
                break;
            case -12:
                if (!ball0.isPressed()) {
                    upperRoller.setPower(0);
                    balls[0] = 'X';
                    state = 0;
                }
                break;

            case 0:
                intake.setPower(0);
                lowerRoller.setPower(0);
                upperRoller.setPower(0);
                break;
        }
    }

    public boolean ballFind(NormalizedColorSensor sensor){
        float r, g, b;
        r = sensor.getNormalizedColors().red;
        g = sensor.getNormalizedColors().green;
        b = sensor.getNormalizedColors().blue;
        return (r >= 0.01 || b >= 0.01 || g >= 0.01);
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
        if (ball0.isPressed()){
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
        intake.setPower(1);
        lowerRoller.setPower(1);
        upperRoller.setPower(1);
    }

    public void manualBackward(){
        intake.setPower(-1);
        lowerRoller.setPower(-1);
        upperRoller.setPower(-1);
    }

    public void log() {
        telemetry.addData("State", state);
        telemetry.addData("Balls", Arrays.toString(balls));
    }
}
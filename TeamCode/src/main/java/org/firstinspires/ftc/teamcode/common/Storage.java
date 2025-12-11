package org.firstinspires.ftc.teamcode.common;

import android.graphics.Color;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.TouchSensor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.common.servos.ServoSimple;

import java.util.Arrays;

public class Storage extends Component {
    private CRServo upperRoller;
    private DcMotorEx intake, lowerRoller;
    private TouchSensor ball0,  ball1, ball2;
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
        ball1 = hardwareMap.get(TouchSensor.class, "ball1");
        ball2 = hardwareMap.get(TouchSensor.class, "ball2");
        timer = new ElapsedTime();
    }


    public void intakeBalls() {
        if (state <= 0) {
            if (balls[0] == 'X' && balls[1] == 'X' && balls[2] == 'X') {
                state = 1;
            }
            else if (balls[0] != 'X' && balls[1] == 'X' && balls[2] == 'X') {
                state = 4;
            }
            else if (balls[0] != 'X' && balls[1] != 'X' && balls[2] == 'X') {
                state = 6;
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
                    timer.reset();
                    state = 3;
                }
                break;
            case 3:
                if (timer.milliseconds() > INTAKE_DELAY_MS){
                    balls[0] = 'O';
                    state = 4;
                }
                break;
            case 4:
                intake.setPower(1);
                lowerRoller.setPower(0.5);
                upperRoller.setPower(0);
                state = 5;
                break;
            case 5:
                if (ball1.isPressed()) {
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
                if (ball2.isPressed()){
                    balls[2] = 'O';
                    state = 0;
                }
                break;
            case -1:
            intake.setPower(0);
                lowerRoller.setPower(0);
                upperRoller.setPower(1);
                state = -2;
                break;
            case -2:
                if (!ball0.isPressed()) {
                    intake.setPower(0); //garrett was here
                    lowerRoller.setPower(1);
                    upperRoller.setPower(1);
                    state = -3;
                }
                    break;
            case -3:
                if (ball0.isPressed()){
                    timer.reset();
                    state = -4;
                }
                break;
            case -4:
                if (timer.milliseconds() > SHOOT_DELAY_MS){
                    state = -5;
                }
                break;
            case -5:
                intake.setPower(1);
                lowerRoller.setPower(0.5);
                upperRoller.setPower(0);
                state = -6;
                        break;
            case -6:
                if (ball1.isPressed()) {
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
                    timer.reset();
                    state = -10;
                }
                break;
            case -10:
                if (timer.milliseconds() > SHOOT_DELAY_MS){
                    balls[1] = 'X';
                    state = 0;
                }
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

    public void shootBalls() {
        if (state >= 0) {
            if (balls[0] != 'X' && balls[1] != 'X' && balls[2] != 'X') {
                state = -1;
            } else if (balls[0] != 'X' && balls[1] != 'X' && balls[2] == 'X') {
                state = -7;
            } else if (balls[0] != 'X' && balls[1] == 'X' && balls[2] == 'X') {
                state = -11;
            }
        }
    }
    
    public void stop(){
        state = 0;
    }

    public int getState() {
        return state;
    }

    public void log() {
        telemetry.addData("State", state);
        telemetry.addData("Balls", Arrays.toString(balls));
    }
}
package org.firstinspires.ftc.teamcode.common;

import android.graphics.Color;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.TouchSensor;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.common.servos.ServoSimple;

import java.util.Arrays;

public class Storage extends Component {
    private CRServo upperRoller;
    private DcMotorEx intake, lowerRoller;
    private TouchSensor ball0,  ball1, ball2;
    public int state = 0;
    private char[] balls = new char[3]; // these balls are massive - Nathaniel Hoaglen aden's king

    public Storage(Telemetry telemetry, HardwareMap hardwareMap) {
        super(telemetry);
        balls[0] = 'X';
        balls[1] = 'X';
        balls[2] = 'X';
        lowerRoller = hardwareMap.get(DcMotorEx.class, "lowerRoller");
        upperRoller = hardwareMap.get(CRServo.class, "upperRoller");
        lowerRoller.setDirection(DcMotorSimple.Direction.REVERSE);
        intake = hardwareMap.get(DcMotorEx.class, "intake");
        ball0 = hardwareMap.get(TouchSensor.class, "ball0");
        ball1 = hardwareMap.get(TouchSensor.class, "ball1");
        ball2 = hardwareMap.get(TouchSensor.class, "ball2");
    }


    public void intakeBalls() {
        if (state <= 0) {
            if (balls[0] == 'X' && balls[1] == 'X' && balls[2] == 'X') {
                state = 1;
            }
            else if (balls[0] != 'X' && balls[1] == 'X' && balls[2] == 'X') {
                state = 3;
            }
            else if (balls[0] != 'X' && balls[1] != 'X' && balls[2] == 'X') {
                state = 5;
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
                    balls[0] = 'O';
                    state = 3;
                }
                break;
            case 3:
                intake.setPower(1);
                lowerRoller.setPower(1);
                upperRoller.setPower(0);
                state = 4;
                break;
            case 4:
                if (ball1.isPressed()) {
                    balls[1] = 'O';
                    state = 5;
                }
                break;
            case 5:
                intake.setPower(1);
                lowerRoller.setPower(0);
                upperRoller.setPower(0);
                state = 6;
                break;
            case 6:
                balls[2] = 'O';
                if (ball2.isPressed()){
                    state = 0;
                    break;
                }
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
                if (ball0.isPressed()) {
                    state = -4;
                }
                break;
            case -4:
                intake.setPower(1);
                lowerRoller.setPower(1);
                upperRoller.setPower(0);
                state = -5;
                        break;
            case -5:
                if (ball1.isPressed()) {
                    state = 0;
                    balls[2] = 'X';
                }
                break;
            case -6:
                intake.setPower(0);
                lowerRoller.setPower(0);
                upperRoller.setPower(1);
                state = -7;
                break;
            case -7:
                if (!ball0.isPressed()) {
                    intake.setPower(0); //garrett was here
                    lowerRoller.setPower(1);
                    upperRoller.setPower(1);
                    state = -8;
                }
                break;
            case -8:
                if (ball0.isPressed()) {
                    state = 0;
                    balls[1] = 'X';
                }
                break;
            case -9:
                intake.setPower(0);
                lowerRoller.setPower(0);
                upperRoller.setPower(1);
                state = -10;
                break;
            case -10:
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
                state = -6;
            } else if (balls[0] != 'X' && balls[1] == 'X' && balls[2] == 'X') {
                state = -8;
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
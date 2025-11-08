package org.firstinspires.ftc.teamcode.common;

import android.graphics.Color;

import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.TouchSensor;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.common.servos.ServoSimple;

import java.util.Arrays;

public class Storage extends Component {
    private Servo lowerRoller, upperRoller;
    private DcMotorEx intake;
    private ColorSensor color, ball0;
    private TouchSensor touch;
    public int state = 0;
    private char[] balls = new char[3]; // these balls are massive - Nathaniel Hoaglen aden's king

    public Storage(Telemetry telemetry, HardwareMap hardwareMap) {
        super(telemetry);
        balls[0] = 'X';
        balls[1] = 'X';
        balls[2] = 'X';
        lowerRoller = hardwareMap.get(Servo.class, "lowerRoller");
        upperRoller = hardwareMap.get(Servo.class, "upperRoller");
        intake = hardwareMap.get(DcMotorEx.class, "intake");
        color = hardwareMap.get(ColorSensor.class, "color");
        ball0 = hardwareMap.get(ColorSensor.class, "ball0");
        touch = hardwareMap.get(TouchSensor.class, "touch");
    }


    public void intakeBalls() {
        if (state != 0) {
            state = 0;
        } else {
            if (balls[0] == 'X' && balls[1] == 'X' && balls[2] == 'X') {
                state = 1;
            }
            else if (balls[0] != 'X' && balls[1] != 'X' && balls[2] != 'X') {
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
                lowerRoller.setPosition(0);
                upperRoller.setPosition(0);
                balls[0] = getColor(color);
                while (!detectBall(ball0)){

                }
                state = 2;
                break;
            case 2:
                intake.setPower(1);
                lowerRoller.setPosition(0);
                upperRoller.setPosition(0);
                balls[1] = getColor(color);
                while (!touch.isPressed()) {

                }
                state = 3;
                break;
            case 3:
                intake.setPower(1);
                lowerRoller.setPosition(0);
                upperRoller.setPosition(0);
                balls[2] = getColor(color);
                state = 0;
                break;
            case -1:
                intake.setPower(0);
                lowerRoller.setPosition(0);
                upperRoller.setPosition(1);
                balls[1] = 'X';
                state = -2;
                break;
            case -2:
                intake.setPower(-1);
                lowerRoller.setPosition(0);
                upperRoller.setPosition(1);
                balls[0] = 'X';
                state = -3;
                break;
            case -3:
                intake.setPower(1);
                lowerRoller.setPosition(0);
                upperRoller.setPosition(1);
                balls[2] = 'X';
                state = 0;
                break;
        }
    }

    public void shootBalls() {
        if (state == 0) {
            if (balls[0] != 'X' && balls[1] != 'X' && balls[2] != 'X') {
                state = -1;
            } else if (balls[0] != 'X' && balls[1] == 'X' && balls[2] != 'X') {
                state = -3;
            } else if (balls[0] == 'X' && balls[1] == 'X' && balls[2] != 'X') {
                state = -5;
            }
        } else {
            state = 0;
        }
    }

    public int getState() {
        return state;
    }

    public void updateState(boolean disable) {
        if (disable) {
            state = 0;
        } else if (state > 0) {
            state++;
        } else {
            state--;
        }
    }

    private char getColor(ColorSensor sensor) {
        int red, blue, green;
        boolean greenBall = false;
        boolean purpleBall = false;
        while (!greenBall && !purpleBall) {
            red = sensor.red();
            blue = sensor.blue();
            green = sensor.green();
            greenBall = red < 25 && blue < 25 && green > 250;
            purpleBall = red > 130 && blue > 230 && green < 25;
        }
        if (greenBall) {
            return 'G';
        } else {
            return 'P';
        }
    }

    private boolean detectBall(ColorSensor sensor){
        int red = sensor.red();
        int blue = sensor.blue();
        int green = sensor.green();
        return red < 25 && blue < 25 && green < 25;
    }

    public void log(){
        telemetry.addData("State", state);
        telemetry.addData("Balls", Arrays.toString(balls));
    }
}
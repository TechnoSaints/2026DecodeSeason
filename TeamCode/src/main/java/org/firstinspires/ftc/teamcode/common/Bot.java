package org.firstinspires.ftc.teamcode.common;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Bot extends Component {
//    private final ServoSimple intakeWrist, intakeSwivel, intakeGrabber, intakeLight;
//    private final ServoSimple handlerArm, handlerWrist, handlerGrabber;
//    private final RevTouchSensor handlerSwitch, bumperSwitchL, bumperSwitchR;
//    private final Extendo extendo;
    private LaunchMotor rightLaunchMotor, leftLaunchMotor;

    private IntakeMotors intake;

    Servo launchServo, pusher;
    private Modes currentMode;
    private int currentPhase;

    private boolean blackWheelRunning = false;
    private boolean blackWheelForward = false; // true = moving forward, false = stopped
    private boolean blackWheelBackward = false; // true = moving backward, false = stopped

    public double aimerPosition = 0.0;
    private boolean onHold = false;

    public Bot(Telemetry telemetry) {
        super(telemetry);
    }

    public void init(OpMode opMode) {
        rightLaunchMotor = new LaunchMotor(telemetry);
        rightLaunchMotor.init(opMode.hardwareMap, "rightLaunchMotor");

        leftLaunchMotor = new LaunchMotor(telemetry);
        leftLaunchMotor.init(opMode.hardwareMap, "leftLaunchMotor");
        intake = new IntakeMotors(telemetry);
        intake.init(opMode.hardwareMap, "intake");

        // Servos
        launchServo = opMode.hardwareMap.get(Servo.class, "launchServo");
        launchServo.setPosition(0);
        pusher = opMode.hardwareMap.get(Servo.class, "pusher");
        pusher.setPosition(0);
    }

    // Phases are used to divide mode actions into sequential section, with entry criteria
    private void setPhase(int phase) {
        currentPhase = phase;
    }

    private boolean isPhase(int phase) {
        return (currentPhase == phase);
    }

    public void fullIntakeCycle() {
        intakeMotorStart();
        // Some code to sleep onl this portion for 0-1 seconds
        intakeMotorStop();
        fullPushBlackWheel();
    }
    public void moveAimerUp(double amount) {
        aimerPosition+=amount;
        launchServo.setPosition(aimerPosition);
    }

    public void moveAimerDown(double amount) {
        aimerPosition-=amount;
        launchServo.setPosition(aimerPosition);
    }

    public void zeroAimer() {
        aimerPosition = 0;
        launchServo.setPosition(aimerPosition);
    }

    public void fullPushBlackWheel() {
        pusher.setPosition(1);
        pusher.setPosition(0);
    }

    public void toggleBlackWheel() {
        if (!blackWheelRunning) {
            // Turn on
            pusher.setPosition(1.0);
            blackWheelRunning = true;
        } else {
            // Turn off
            pusher.setPosition(0.0);
            blackWheelRunning = false;
        }
    }


    public void startLaunchMotors(double multiple) {
        rightLaunchMotor.setSpeed(-multiple);
        leftLaunchMotor.setSpeed(multiple);
    }

    public void stopLaunchMotors() {
        rightLaunchMotor.stopMotor();
        leftLaunchMotor.stopMotor();
    }

    public void intakeMotorStop() {
        intake.stopMotor();
    }

    public void intakeMotorStart() {
        intake.setSpeed(-1);
    }

    public void log() {
        leftLaunchMotor.log();
    }

}

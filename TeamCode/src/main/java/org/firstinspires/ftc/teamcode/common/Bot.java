package org.firstinspires.ftc.teamcode.common;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Servo;
import org.firstinspires.ftc.teamcode.common.hardwareConfiguration.data.GoBilda6000DcMotorData;
import org.firstinspires.ftc.teamcode.common.LauncherDouble;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Bot extends Component {
//    private final ServoSimple intakeWrist, intakeSwivel, intakeGrabber, intakeLight;
//    private final ServoSimple handlerArm, handlerWrist, handlerGrabber;
//    private final RevTouchSensor handlerSwitch, bumperSwitchL, bumperSwitchR;
//    private final Extendo extendo;
//    private LaunchMotor rightLaunchMotor, leftLaunchMotor;
    private LauncherDouble launchMotors;

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
        launchMotors = new LauncherDouble(opMode.hardwareMap, telemetry, "leftLaunchMotor", "rightLaunchMotor", new GoBilda6000DcMotorData());
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

    public void moveAimerUp(double amount) {
        aimerPosition+=amount;
        launchServo.setPosition(aimerPosition);
    }

    public void moveAimerDown(double amount) {
        aimerPosition-=amount;
        launchServo.setPosition(aimerPosition);
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
        launchMotors.setVelocityFactor(multiple);
    }

    public void stopLaunchMotors() {
        launchMotors.setVelocityFactor(0.0);
    }

    public void intakeMotorStop() {
        intake.stopMotor();
    }

    public void intakeMotorStart() {
        intake.setSpeed(-1);
    }

    public void log() {
        launchMotors.log();
    }

}

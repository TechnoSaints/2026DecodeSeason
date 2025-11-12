package org.firstinspires.ftc.teamcode.common;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;

//import com.arcrobotics.ftclib.hardware.motors.CRServo;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

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

    public ElapsedTime runtime = new ElapsedTime();

    Servo launchServo, stick;
    CRServo pusher;
    private Modes currentMode;
    private int currentPhase;

    private long stickTimer = 0;
    private boolean stickActiveRunning = false;

    private boolean blackWheelRunning = false;
    private boolean blackWheelForward = false; // true = moving forward, false = stopped
    private boolean blackWheelBackward = false; // true = moving backward, false = stopped

    public double aimerPosition = 0.0;
    private boolean onHold = false;

    public Bot(Telemetry telemetry) {
        super(telemetry);
    }

    public void init(OpMode opMode) {
        double miliseconds = runtime.milliseconds();
        launchMotors = new LauncherDouble(opMode.hardwareMap, telemetry);

        intake = new IntakeMotors(telemetry);
        intake.init(opMode.hardwareMap, "intake");

        // Servos
        launchServo = opMode.hardwareMap.get(Servo.class, "launchServo");
        launchServo.setPosition(aimerPosition);
        pusher = opMode.hardwareMap.get(CRServo.class, "pusher");
        stick = opMode.hardwareMap.get(Servo.class, "stick");
        stick.setPosition(0.43);
    }

    // Phases are used to divide mode actions into sequential section, with entry criteria
    private void setPhase(int phase) {
        currentPhase = phase;
    }

    private boolean isPhase(int phase) {
        return (currentPhase == phase);
    }

    public void updateStick() {
        if (stickActiveRunning) {
            if (System.currentTimeMillis() - stickTimer >= 500) {
                stick.setPosition(0.85);
                stickActiveRunning = false;  // done
            }
        }
    }

    public void stickActivate() {
        if (!stickActiveRunning) {
            stickActiveRunning = true;
            stickTimer = System.currentTimeMillis();

            stick.setPosition(0.54);
        }
    }

    public void moveAimerUp(double amount) {
        aimerPosition+=amount;
        if (aimerPosition >= 0.3) {
            aimerPosition=0.3;
        }
        launchServo.setPosition(aimerPosition);
    }

    public void moveAimerDown(double amount) {
        aimerPosition-=amount;
        launchServo.setPosition(aimerPosition);
    }


    public void toggleBlackWheel() {
        if (!blackWheelRunning) {
            double currentServoTime = runtime.milliseconds() + 5000;
            while (currentServoTime > runtime.milliseconds()) {
                pusher.setPower(1.0);
            }
            blackWheelRunning = true;
        } else {
            // Turn off
            pusher.setPower(0.0);
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

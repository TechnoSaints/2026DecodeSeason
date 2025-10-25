package org.firstinspires.ftc.teamcode.common;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Bot extends Component {
//    private final ServoSimple intakeWrist, intakeSwivel, intakeGrabber, intakeLight;
//    private final ServoSimple handlerArm, handlerWrist, handlerGrabber;
//    private final RevTouchSensor handlerSwitch, bumperSwitchL, bumperSwitchR;
//    private final Extendo extendo;
    private LaunchMotor launchTest1, launchTest2;

    private IntakeMotors intake;

    private Modes currentMode;
    private int currentPhase;
    private boolean onHold = false;

    public Bot(Telemetry telemetry) {
        super(telemetry);
    }

    public void init(OpMode opMode) {
        launchTest1 = new LaunchMotor(telemetry);
        launchTest1.init(opMode.hardwareMap, "launchtest1");

        launchTest2 = new LaunchMotor(telemetry);
        launchTest2.init(opMode.hardwareMap, "launchtest2");
        intake = new IntakeMotors(telemetry);
        intake.init(opMode.hardwareMap, "intake");
    }

    // Phases are used to divide mode actions into sequential section, with entry criteria
    private void setPhase(int phase) {
        currentPhase = phase;
    }

    private boolean isPhase(int phase) {
        return (currentPhase == phase);
    }


    public void launchMotor1Stop() {
        launchTest1.stopMotor();
    }

    public void launchMotor1Start() {
        launchTest1.setSpeed(-0.5);
    }

    public void launchMotor2Stop() {
        launchTest2.stopMotor();
    }

    public void launchMotor2Start() {
        launchTest2.setSpeed(-0.5);
    }

    public void intakeMotorStop() {
        intake.stopMotor();
    }

    public void intakeMotorStart() {
        intake.setSpeed(1);
    }

}

package org.firstinspires.ftc.teamcode.common;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.common.hardwareConfiguration.data.DrivetrainData;
import org.firstinspires.ftc.teamcode.common.hardwareConfiguration.data.ExtendoData;
import org.firstinspires.ftc.teamcode.common.hardwareConfiguration.data.GoBilda435DcMotorData;
import org.firstinspires.ftc.teamcode.common.hardwareConfiguration.positions.ExtendoPositions;
import org.firstinspires.ftc.teamcode.common.hardwareConfiguration.positions.HandlerArmPositions;
import org.firstinspires.ftc.teamcode.common.hardwareConfiguration.positions.HandlerGrabberPositions;
import org.firstinspires.ftc.teamcode.common.hardwareConfiguration.positions.HandlerWristPositions;
import org.firstinspires.ftc.teamcode.common.hardwareConfiguration.positions.IntakeGrabberPositions;
import org.firstinspires.ftc.teamcode.common.hardwareConfiguration.positions.IntakeSwivelPositions;
import org.firstinspires.ftc.teamcode.common.hardwareConfiguration.positions.IntakeWristPositions;
import org.firstinspires.ftc.teamcode.common.hardwareConfiguration.positions.LiftPositions;

public class TeleopBot extends Bot {
    private final Drivetrain drivetrain;
    private double driveAxial = 0.0;
    private double driveStrafe = 0.0;
    private double driveYaw = 0.0;
    private ElapsedTime buttonTimer = new ElapsedTime(ElapsedTime.Resolution.MILLISECONDS);
    private ElapsedTime controlTimer = new ElapsedTime(ElapsedTime.Resolution.MILLISECONDS);

    private int buttonDelay = 350;
    private boolean looking = false;
    private boolean liftManualControl = false;
    private boolean holdingSpecimen = false;
    private boolean grabPosition = false;

    public TeleopBot(OpMode opMode, Telemetry telemetry) {
        super(opMode, telemetry);
        drivetrain = new Drivetrain(opMode.hardwareMap, telemetry, new DrivetrainData(), new GoBilda435DcMotorData());
        buttonTimer.reset();
    }

    private boolean buttonPushable() {
        return (buttonTimer.milliseconds() > buttonDelay);
    }

    private void processDrivetrainInput(Gamepad gamepad) {
        if (gamepad.dpad_up) {
            drivetrain.creepDirection(1.0, 0.0, 0.0);
        } else if (gamepad.dpad_down) {
            drivetrain.creepDirection(-1.0, 0.0, 0.0);
        } else if (gamepad.dpad_left) {
            drivetrain.creepDirection(0.0, -1.0, 0.0);
        } else if (gamepad.dpad_right) {
            drivetrain.creepDirection(0.0, 1.0, 0.0);
        } else {
            driveAxial = -gamepad.left_stick_y;
            driveStrafe = gamepad.left_stick_x;
            driveYaw = gamepad.right_stick_x;
            if ((Math.abs(driveAxial) < 0.2) && (Math.abs(driveStrafe) < 0.2) && (Math.abs(driveYaw) < 0.2)) {
                drivetrain.stop();
            } else
                drivetrain.moveDirection(driveAxial, driveStrafe, driveYaw);
        }
    }

    public void processSpecimenInput(Gamepad gamepad) {
        processDrivetrainInput(gamepad);

        if (gamepad.right_trigger > 0.2) {
            liftManualControl = true;
            liftUp(gamepad.right_trigger);
        } else if (gamepad.left_trigger > 0.2) {
            liftManualControl = true;
            liftDown(gamepad.left_trigger);
        }
        if (liftManualControl && (gamepad.right_trigger <= 0.2) && (gamepad.left_trigger <= 0.2)) {
            liftStop();
        }

        if (holdingSpecimen && bumperBumped()) {
            holdingSpecimen = false;
            liftManualControl = false;
            setMode(Modes.HANG_SPECIMEN);
        }
        if (buttonPushable()) {
            //Controls while looking for brick
            if (gamepad.a) {
                liftManualControl = false;
                if (extendoGetCurrentLength() > (ExtendoPositions.RETRACTED.getValue() + 1.5)) {
                    looking = false;
                    setMode(Modes.NOT_LOOKING_POS);
                } else {
                    setMode(Modes.LOOKING_POS);
                    setLiftPositionPreset(LiftPositions.SPECIMEN_WALL);
                    setHandlerArmPositionPreset(HandlerArmPositions.SPECIMEN_WALL);
                    setHandlerWristPositionPreset(HandlerWristPositions.SPECIMEN_WALL);
                    setHandlerGrabberPositionPreset(HandlerGrabberPositions.OPEN);
                    looking = true;
                }
                buttonTimer.reset();
            } else if (gamepad.x) {
                liftManualControl = false;
                if (looking) {
                    setMode(Modes.INTAKE_BRICK_SIMPLE);
                }
                buttonTimer.reset();
            } else if (gamepad.y) {
                liftManualControl = false;
                setMode(Modes.TELEOP_HANDOFF);
                buttonTimer.reset();
            } else if (gamepad.right_bumper) {
                liftManualControl = false;
                if (liftCurrentPosition() < 1900) {
                    setLiftPositionPreset(LiftPositions.HIGH_BUCKET_TELEOP);
                    setHandlerArmPositionPreset(HandlerArmPositions.HIGH_BUCKET);
                    setHandlerWristPositionPreset(HandlerWristPositions.HIGH_BUCKET);
                } else {
                    setHandlerGrabberPositionPreset(HandlerGrabberPositions.OPEN);
                }
                buttonTimer.reset();
            } else if (gamepad.b) {
                liftManualControl = false;
                if (!grabPosition) {
                    grabPosition = true;
                    holdingSpecimen = false;
                    setHandlerArmPositionPreset(HandlerArmPositions.SPECIMEN_WALL);
                    setMode(Modes.HANDLER_GRAB_SPECIMEN_POS);
                } else {
                    if (!holdingSpecimen) {
                        holdingSpecimen = true;
                        grabPosition = false;
                        setMode(Modes.TELEOP_GRAB_SPECIMEN);
                    }
                }
                buttonTimer.reset();
            } else if (gamepad.left_bumper) {
                liftManualControl = false;
                if (looking) {
                    setIntakeGrabberPositionPreset(IntakeGrabberPositions.OPEN);
                    setIntakeWristPositionPreset(IntakeWristPositions.DOWN);
                    setIntakeSwivelPositionPreset(IntakeSwivelPositions.DEGREES90);
                }
                buttonTimer.reset();
            } else if ((gamepad.start) && (gamepad.share)) {
                liftManualControl = false;
                liftZero();
                buttonTimer.reset();
            } else if (gamepad.ps)
            {
                liftManualControl = false;
                liftLock();
                buttonTimer.reset();
            } else if (gamepad.touchpad)
            {
                liftManualControl = false;
                liftUnlock();
                buttonTimer.reset();
            }
        }
    }
}

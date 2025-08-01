package org.firstinspires.ftc.teamcode.opmode.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

import org.firstinspires.ftc.teamcode.common.Modes;
import org.firstinspires.ftc.teamcode.common.hardwareConfiguration.positions.ExtendoPositions;
import org.firstinspires.ftc.teamcode.common.hardwareConfiguration.positions.HandlerArmPositions;
import org.firstinspires.ftc.teamcode.common.hardwareConfiguration.positions.HandlerGrabberPositions;
import org.firstinspires.ftc.teamcode.common.hardwareConfiguration.positions.HandlerWristPositions;
import org.firstinspires.ftc.teamcode.common.hardwareConfiguration.positions.IntakeGrabberPositions;
import org.firstinspires.ftc.teamcode.common.hardwareConfiguration.positions.IntakeSwivelPositions;
import org.firstinspires.ftc.teamcode.common.hardwareConfiguration.positions.IntakeWristPositions;
import org.firstinspires.ftc.teamcode.common.hardwareConfiguration.positions.LiftPositions;
import org.firstinspires.ftc.teamcode.opmode.FieldLocations;
import org.firstinspires.ftc.teamcode.opmode.Paths;

@Autonomous(name = "Bucket x 4 + 1", group = "Bucket")
@Disabled
public class Bucketx4plus1 extends BucketAutoOpMode {

    protected void autonomousPathUpdate() {
        switch (pathState) {
            // Go to high bucket scoring config and pose
            case 0:
                bot.setLiftPositionPreset(LiftPositions.HIGH_BUCKET_AUTO);
                bot.setHandlerArmPositionPreset(HandlerArmPositions.TOP, 0);
                bot.followPath(Paths.startToBucket, false);
                setPathState(1);
                break;

            case 1:
                if (!bot.handlerIsBusy() && !bot.onHold()) {
                    bot.setHandlerArmPositionPreset(HandlerArmPositions.HIGH_BUCKET, 0);
                    bot.setHandlerWristPositionPreset(HandlerWristPositions.HIGH_BUCKET, 0);
                    setPathState(2);
                }
                break;

            // Drop brick into high bucket
            case 2:
//                if (!bot.handlerIsBusy()) {
                controlTimer.reset();
                while (controlTimer.milliseconds() < 100) {
                }
                bot.setHandlerGrabberPositionPreset(HandlerGrabberPositions.OPEN);
                setPathState(10);
                //           }
                break;

            // Go to spike 1
            case 10:
                if (!bot.handlerIsBusy() && !bot.onHold()) {
                    bot.followPath(Paths.bucketToSampleSpike1, false);
                    bot.setHandlerArmPositionPreset(HandlerArmPositions.HANDOFF, 0);
                    bot.setMode(Modes.INTAKE_HOVER_POS);
                    setPathState(11);
                }
                break;

            case 11:
                if (!bot.handlerIsBusy()) {
                    bot.setLiftPositionPreset(LiftPositions.HANDOFF_SETUP);
                    bot.setHandlerWristPositionPreset(HandlerWristPositions.HANDOFF);
                    setPathState(12);
                }
                break;

            // Grab brick from spike1
            case 12:
                if (!bot.followerIsBusy()) {
                    bot.setMode(Modes.INTAKE_BRICK_FOR_HANDOFF);
                    setPathState(13);
                }
                break;

            case 13:
                if (!bot.intakeIsBusy() && !bot.onHold()) {
                    bot.setExtendoPositionPreset(ExtendoPositions.RETRACTED);
                    bot.followPath(Paths.sampleSpike1ToBucket, false);
                    setPathState(14);
                }
                break;

            case 14:
                if (!bot.handlerIsBusy() && !bot.intakeIsBusy() && !bot.onHold()) {
                    bot.setMode(Modes.HAND_OFF_BRICK);
                    setPathState(15);
                }
                break;

            case 15:
                if (!bot.handlerIsBusy() && !bot.onHold()) {
                    bot.setLiftPositionPreset(LiftPositions.HIGH_BUCKET_AUTO);
                    bot.setHandlerArmPositionPreset(HandlerArmPositions.TOP, 0);
                    setPathState(16);
                }
                break;

            case 16:
                if (!bot.liftIsBusy()) {
                    bot.setHandlerArmPositionPreset(HandlerArmPositions.HIGH_BUCKET, 50);
                    bot.setHandlerWristPositionPreset(HandlerWristPositions.HIGH_BUCKET, 0);
                    setPathState(17);
                }
                break;

            case 17:
//                if (!bot.handlerIsBusy()) {
                controlTimer.reset();
                while (controlTimer.milliseconds() < 100) {
                }
                bot.setHandlerGrabberPositionPreset(HandlerGrabberPositions.OPEN);
                setPathState(20);
//                }
                break;

            // Go to spike 2
            case 20:
                if (!bot.handlerIsBusy() && !bot.onHold()) {
                    bot.followPath(Paths.bucketToSampleSpike2, false);
                    bot.setHandlerArmPositionPreset(HandlerArmPositions.HANDOFF, 0);
                    bot.setMode(Modes.INTAKE_HOVER_POS);
                    setPathState(21);
                }
                break;

            case 21:
                if (!bot.handlerIsBusy()) {
                    bot.setLiftPositionPreset(LiftPositions.HANDOFF_SETUP);
                    bot.setHandlerWristPositionPreset(HandlerWristPositions.HANDOFF);
                    setPathState(22);
                }
                break;

            // Grab brick from spike2
            case 22:
                if (!bot.followerIsBusy()) {
                    bot.setMode(Modes.INTAKE_BRICK_FOR_HANDOFF);
                    setPathState(23);
                }
                break;

            case 23:
                if (!bot.intakeIsBusy() && !bot.onHold()) {
                    bot.setExtendoPositionPreset(ExtendoPositions.RETRACTED);
                    bot.followPath(Paths.sampleSpike2ToBucket, false);
                    setPathState(24);
                }
                break;

            case 24:
                if (!bot.handlerIsBusy() && !bot.intakeIsBusy() && !bot.onHold()) {
                    bot.setMode(Modes.HAND_OFF_BRICK);
                    setPathState(25);
                }
                break;

            case 25:
                if (!bot.handlerIsBusy() && !bot.onHold()) {
                    bot.setLiftPositionPreset(LiftPositions.HIGH_BUCKET_AUTO);
                    bot.setHandlerArmPositionPreset(HandlerArmPositions.TOP, 0);
                    setPathState(26);
                }
                break;

            case 26:
                if (!bot.liftIsBusy()) {
                    bot.setHandlerArmPositionPreset(HandlerArmPositions.HIGH_BUCKET, 50);
                    bot.setHandlerWristPositionPreset(HandlerWristPositions.HIGH_BUCKET, 0);
                    setPathState(27);
                }
                break;

            case 27:
//                if (!bot.handlerIsBusy()) {
                controlTimer.reset();
                while (controlTimer.milliseconds() < 100) {
                }
                bot.setHandlerGrabberPositionPreset(HandlerGrabberPositions.OPEN);
                setPathState(30);
                //              }
                break;

            // Go to spike 3 setup
            case 30:
                if (!bot.handlerIsBusy() && !bot.onHold()) {
                    bot.followPath(Paths.bucketToSampleSpike3Setup, false);
                    bot.setHandlerArmPositionPreset(HandlerArmPositions.HANDOFF, 0);
                    bot.setMode(Modes.INTAKE_HOVER_POS);
                    setPathState(31);
                }
                break;

            case 31:
                if (!bot.handlerIsBusy()) {
                    bot.setLiftPositionPreset(LiftPositions.HANDOFF_SETUP);
                    bot.setHandlerWristPositionPreset(HandlerWristPositions.HANDOFF);
                    setPathState(32);
                }
                break;

            // Rotate swivel, lower grabber, then strafe to spike 3
            case 32:
                if (!bot.followerIsBusy()) {
                    bot.setIntakeSwivelPositionPreset(IntakeSwivelPositions.DEGREES90);
                    bot.setIntakeWristPositionPreset(IntakeWristPositions.DOWN);
                    bot.followPath(Paths.sampleSpike3SetupToSampleSpike3, false);
                    setPathState(33);
                }
                break;

            case 33:
                if (!bot.followerIsBusy()) {
                    bot.setIntakeGrabberPositionPreset(IntakeGrabberPositions.CLOSED_LOOSE);
//                    bot.setExtendoPositionPreset(ExtendoPositions.AUTO_SHORT);
                    bot.moveManualInches(-4, 0, 0);
                    setPathState(34);
                }
                break;

            // Grab brick from spike3
            case 34:
                if (!bot.intakeIsBusy()) {
                    bot.setMode(Modes.INTAKE_BRICK_FOR_HANDOFF);
                    setPathState(35);
                }
                break;

            case 35:
                if (!bot.intakeIsBusy() && !bot.onHold()) {
                    bot.setExtendoPositionPreset(ExtendoPositions.RETRACTED);
                    bot.followPath(Paths.sampleSpike3ToBucket, false);
                    setPathState(36);
                }
                break;

            case 36:
                if (!bot.handlerIsBusy() && !bot.intakeIsBusy() && !bot.onHold()) {
                    bot.setMode(Modes.HAND_OFF_BRICK);
                    setPathState(37);
                }
                break;

            case 37:
                if (!bot.handlerIsBusy() && !bot.onHold()) {
                    bot.setLiftPositionPreset(LiftPositions.HIGH_BUCKET_AUTO);
                    bot.setHandlerArmPositionPreset(HandlerArmPositions.TOP, 0);
                    setPathState(38);
                }
                break;

            case 38:
                if (!bot.liftIsBusy()) {
                    bot.setHandlerArmPositionPreset(HandlerArmPositions.HIGH_BUCKET, 50);
                    bot.setHandlerWristPositionPreset(HandlerWristPositions.HIGH_BUCKET, 0);
                    setPathState(39);
                }
                break;

            case 39:
//                if (!bot.handlerIsBusy()) {
                controlTimer.reset();
                while (controlTimer.milliseconds() < 100) {
                }
                bot.setHandlerGrabberPositionPreset(HandlerGrabberPositions.OPEN);
                setPathState(40);
//                }
                break;

            case 40:
                if (!bot.handlerIsBusy() && !bot.onHold()) {
                    bot.followPath(Paths.bucketToSampleHumanPlayer, false);
                    bot.setHandlerArmPositionPreset(HandlerArmPositions.HANDOFF, 0);
                    bot.setMode(Modes.INTAKE_HOVER_POS);
                    setPathState(41);
                }
                break;

            case 41:
                if (!bot.handlerIsBusy()) {
                    bot.setLiftPositionPreset(LiftPositions.HANDOFF_SETUP);
                    bot.setHandlerWristPositionPreset(HandlerWristPositions.HANDOFF);
                    setPathState(42);
                }
                break;

            // Grab brick from spike2
            case 42:
                if (!bot.followerIsBusy()) {
                    bot.setMode(Modes.INTAKE_BRICK_FOR_HANDOFF);
                    setPathState(43);
                }
                break;

            case 43:
                if (!bot.intakeIsBusy() && !bot.onHold()) {
                    bot.setExtendoPositionPreset(ExtendoPositions.RETRACTED);
                    bot.followPath(Paths.sampleHumanPlayerToBucket, false);
                    setPathState(44);
                }
                break;

            case 44:
                if (!bot.handlerIsBusy() && !bot.intakeIsBusy() && !bot.onHold()) {
                    bot.setMode(Modes.HAND_OFF_BRICK);
                    setPathState(45);
                }
                break;

            case 45:
                if (!bot.handlerIsBusy() && !bot.onHold()) {
                    bot.setHandlerArmPositionPreset(HandlerArmPositions.TOP, 0);
                    bot.setLiftPositionPreset(LiftPositions.HIGH_BUCKET_AUTO);
                    setPathState(46);
                }
                break;

            case 46:
                if (!bot.liftIsBusy()) {
                    bot.setHandlerArmPositionPreset(HandlerArmPositions.HIGH_BUCKET, 0);
                    bot.setHandlerWristPositionPreset(HandlerWristPositions.HIGH_BUCKET);
                    setPathState(47);
                }
                break;

            case 47:
                bot.setHandlerGrabberPositionPreset(HandlerGrabberPositions.OPEN);
                setPathState(50);
                break;

            case 50:
                if (!bot.handlerIsBusy()) {
                    bot.setHandlerArmPositionPreset(HandlerArmPositions.TOP);
                    setPathState(51);
                }
                break;

            case 51:
                if (!bot.handlerIsBusy()) {
                    bot.setMode(Modes.AUTO_END_POS);
                    setPathState(52);
                }
                break;

            // deactivate and rest up for teleop
            case 52:
                if (!bot.isBusy()) {
                    setPathState(-1);
                    requestOpModeStop();
                }
                break;
        }
    }
}

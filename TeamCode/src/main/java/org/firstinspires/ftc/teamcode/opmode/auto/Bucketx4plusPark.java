package org.firstinspires.ftc.teamcode.opmode.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.common.Modes;
import org.firstinspires.ftc.teamcode.common.hardwareConfiguration.setPoints.ExtendoPositions;
import org.firstinspires.ftc.teamcode.common.hardwareConfiguration.setPoints.HandlerArmPositions;
import org.firstinspires.ftc.teamcode.common.hardwareConfiguration.setPoints.HandlerGrabberPositions;
import org.firstinspires.ftc.teamcode.common.hardwareConfiguration.setPoints.HandlerWristPositions;
import org.firstinspires.ftc.teamcode.common.hardwareConfiguration.setPoints.IntakeGrabberPositions;
import org.firstinspires.ftc.teamcode.common.hardwareConfiguration.setPoints.IntakeSwivelPositions;
import org.firstinspires.ftc.teamcode.common.hardwareConfiguration.setPoints.IntakeWristPositions;
import org.firstinspires.ftc.teamcode.common.hardwareConfiguration.setPoints.LiftPositions;
import org.firstinspires.ftc.teamcode.opmode.FieldLocations;
import org.firstinspires.ftc.teamcode.opmode.Paths;

@Autonomous(name = "\uD83E\uDEA3 Bucket x 4 + Park", group = "Bucket")
public class Bucketx4plusPark extends BucketAutoOpMode {

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
                //               }
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
                if (!bot.handlerIsBusy()) {
                    bot.setHandlerArmPositionPreset(HandlerArmPositions.SUB_PARKING, 0);
                    bot.followPath(Paths.bucketToSampleParkSetup, false);
                    setPathState(41);
                }
                break;

            case 41:
                if (Paths.currentLocWithinTolerance(bot.getFollower().getPose(), FieldLocations.sampleParkSetupPose, 2, 2)) {
                    bot.followPath(Paths.sampleParkSetupToSamplePark, true);
                    setPathState(42);
                }
                break;

            case 42:
                if (!bot.handlerIsBusy()) {
                    bot.setMode(Modes.PARKING_AT_SUB_POS);
                    setPathState(43);
                }
                break;

            // deactivate and rest up for teleop
            case 43:
                if (!bot.isBusy()) {
                    setPathState(-1);
                    requestOpModeStop();
                }
                break;
        }
    }
}

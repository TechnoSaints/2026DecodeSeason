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

@Autonomous(name = "\uD83E\uDEA3 Bucket x 4 + Park", group = "Bucket")
@Disabled
public abstract class Bucketx4plusPark extends BucketAutoOpMode {

    /*protected void autonomousPathUpdate() {
        switch (pathState) {
            // Go to high bucket scoring config and pose
            case 0:
                bot.followPath(Paths.startToBucket, false);
                setPathState(1);
                break;

            case 1:
                    setPathState(2);

                break;

            // Drop brick into high bucket
            case 2:
//                if (!bot.handlerIsBusy()) {
                controlTimer.reset();
                while (controlTimer.milliseconds() < 100) {
                }
                setPathState(10);
                //               }
                break;

            // Go to spike 1
            case 10:
                    bot.followPath(Paths.bucketToSampleSpike1, false);
                    setPathState(11);

                break;

            case 11:
                    setPathState(12);

                break;

            // Grab brick from spike1
            case 12:
                if (!bot.followerIsBusy()) {
                    bot.setMode(Modes.INTAKE_BRICK_FOR_HANDOFF);
                    setPathState(13);
                }
                break;

            case 13:
                    bot.followPath(Paths.sampleSpike1ToBucket, false);
                    setPathState(14);

                break;

            case 14:
                    setPathState(15);

                break;

            case 15:
                    setPathState(16);

                break;

            case 16:
                    setPathState(17);

                break;

            case 17:
//                if (!bot.handlerIsBusy()) {
                controlTimer.reset();
                while (controlTimer.milliseconds() < 100) {
                }
                setPathState(20);
//                }
                break;

            // Go to spike 2
            case 20:
                    bot.followPath(Paths.bucketToSampleSpike2, false);
                    bot.setMode(Modes.INTAKE_HOVER_POS);
                    setPathState(21);

                break;

            case 21:
                    setPathState(22);

                break;

            // Grab brick from spike2
            case 22:
                if (!bot.followerIsBusy()) {
                    bot.setMode(Modes.INTAKE_BRICK_FOR_HANDOFF);
                    setPathState(23);
                }
                break;

            case 23:
                    bot.followPath(Paths.sampleSpike2ToBucket, false);
                    setPathState(24);

                break;

            case 24:
                    bot.setMode(Modes.HAND_OFF_BRICK);
                    setPathState(25);

                break;

            case 25:
                    setPathState(26);

                break;

            case 26:
                    setPathState(27);

                break;

            case 27:
//                if (!bot.handlerIsBusy()) {
                controlTimer.reset();
                while (controlTimer.milliseconds() < 100) {
                }
                setPathState(30);
                //              }
                break;

            // Go to spike 3 setup
            case 30:
                    bot.followPath(Paths.bucketToSampleSpike3Setup, false);
                    bot.setMode(Modes.INTAKE_HOVER_POS);
                    setPathState(31);

                break;

            case 31:
                    setPathState(32);

                break;

            // Rotate swivel, lower grabber, then strafe to spike 3
            case 32:
                    bot.followPath(Paths.sampleSpike3SetupToSampleSpike3, false);
                    setPathState(33);

                break;

            case 33:
                if (!bot.followerIsBusy()) {
//                    bot.setExtendoPositionPreset(ExtendoPositions.AUTO_SHORT);
                    bot.moveManualInches(-4, 0, 0);
                    setPathState(34);
                }
                break;

            // Grab brick from spike3
            case 34:
                    bot.setMode(Modes.INTAKE_BRICK_FOR_HANDOFF);
                    setPathState(35);

                break;

            case 35:
                    bot.followPath(Paths.sampleSpike3ToBucket, false);
                    setPathState(36);

                break;

            case 36:
                    bot.setMode(Modes.HAND_OFF_BRICK);
                    setPathState(37);

                break;

            case 37:
                    setPathState(38);

                break;

            case 38:
                    setPathState(39);

                break;

            case 39:
//                if (!bot.handlerIsBusy()) {
                controlTimer.reset();
                while (controlTimer.milliseconds() < 100) {
                }
                setPathState(40);
//                }
                break;

            case 40:
                    bot.followPath(Paths.bucketToSampleParkSetup, false);
                    setPathState(41);

                break;

            case 41:
                if (Paths.currentLocWithinTolerance(bot.getFollower().getPose(), FieldLocations.sampleParkSetupPose, 2, 2)) {
                    bot.followPath(Paths.sampleParkSetupToSamplePark, true);
                    setPathState(42);
                }
                break;

            case 42:
                    bot.setMode(Modes.PARKING_AT_SUB_POS);
                    setPathState(43);

                break;

            // deactivate and rest up for teleop
            case 43:
                if (!bot.isBusy()) {
                    setPathState(-1);
                    requestOpModeStop();
                }
                break;
        }
    }*/
}

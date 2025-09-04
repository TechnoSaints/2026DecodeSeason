package org.firstinspires.ftc.teamcode.opmode.auto;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.common.Modes;
import org.firstinspires.ftc.teamcode.common.hardwareConfiguration.positions.HandlerArmPositions;
import org.firstinspires.ftc.teamcode.opmode.FieldLocations;
import org.firstinspires.ftc.teamcode.opmode.Paths;

@Autonomous(name = "\uD83E\uDE9D Specimen x 5", group = "Specimen")
public class Specimenx5 extends SpecimenAutoOpMode {
    protected void autonomousPathUpdate() {

        switch (pathState) {
            case 0:
                bot.setHandlerArmPositionPreset(HandlerArmPositions.SPECIMEN_HANG, 0);
                bot.setMode(Modes.HANDLER_HIGH_SPECIMEN_POS);
                bot.followPath(Paths.startToSubShortSideSetup, false);
                setPathState(1);
                break;

            case 1:
                if (Paths.currentLocWithinTolerance(bot.getFollower().getPose(), FieldLocations.subShortSideSetupPose, 3.0, 3.0)) {
                    bot.followPath(Paths.subShortSideSetupToSubShortSide, true);
                    setPathState(2);
                }
                break;

            case 2:
                if (bot.bumperBumped()) {
                    setPathState(3);
                }
                break;

            case 3:
                if (!bot.handlerIsBusy() && !bot.onHold()) {
                    bot.setMode(Modes.HANG_SPECIMEN);
                    setPathState(10);
                }
                break;

            case 10:
                if (!bot.handlerIsBusy() && !bot.onHold()) {
                    bot.setMode(Modes.CRUISING);
                    bot.followPath(Paths.pushSpike1, false);
                    setPathState(11);
                }
                break;

            case 11:
                if (Paths.currentLocWithinTolerance(bot.getFollower().getPose(), FieldLocations.specimenSpike1DropPose, 4, 4)) {
                    bot.followPath(Paths.pushSpike2, false);
                    setPathState(12);
                }
                break;

            case 12:
                if (Paths.currentLocWithinTolerance(bot.getFollower().getPose(), FieldLocations.specimenSpike2DropPose, 3, 3)) {
                    bot.followPath(Paths.pushSpike3, true);
                    bot.setHandlerArmPositionPreset(HandlerArmPositions.SPECIMEN_WALL, 0);
                    bot.setMode(Modes.HANDLER_GRAB_SPECIMEN_POS);
                    controlTimer.reset();
                    setPathState(14);
                }
                break;

//            case 13:
//                if (Paths.currentLocWithinTolerance(bot.getFollower().getPose(), FieldLocations.specimenSpike3DropPose, 3, 3)) {
//                    bot.followPath(Paths.specimenSpike3DropToSpecimenSpike3Grab, false);
//
//                    setPathState(14);
//                }
//                break;

            case 14:
//                if (!bot.followerIsBusy()){
                if (Paths.currentLocWithinTolerance(bot.getFollower().getPose(),
                        FieldLocations.specimenSpike3GrabPose, 2, 2)) {
                    bot.setMode(Modes.GRAB_SPECIMEN);
                    FieldLocations.subShortSideSetupPose =
                            new Pose(FieldLocations.subShortSideSetupPose.getX() + gapBetweenHangingSpecimensIN,
                                    FieldLocations.subShortSideSetupPose.getY(),
                                    FieldLocations.subShortSideSetupPose.getHeading());

                    FieldLocations.subShortSidePose =
                            new Pose(FieldLocations.subShortSidePose.getX() + gapBetweenHangingSpecimensIN,
                                    FieldLocations.subShortSidePose.getY(),
                                    FieldLocations.subShortSidePose.getHeading());

                    Paths.buildSpecimenHangPaths(bot.getFollower());

                    setPathState(15);
                } else if (controlTimer.milliseconds() > 6250) {
                    bot.setHandlerArmPositionPreset(HandlerArmPositions.SPECIMEN_WALL, 0);
                    bot.setMode(Modes.HANDLER_GRAB_SPECIMEN_POS);
                    bot.followPath(Paths.specimenSpike3GrabToSpecimenGrabSetup, false);

                    setPathState(21);
                }
                break;

            case 15:
                if (!bot.handlerIsBusy() && !bot.onHold()) {
                    bot.setHandlerArmPositionPreset(HandlerArmPositions.SPECIMEN_HANG, 0);
                    bot.setMode(Modes.HANDLER_HIGH_SPECIMEN_POS);
                    bot.followPath(Paths.specimenSpike3GrabToSubShortSideSetup, false);
                    setPathState(16);
                }
                break;

            case 16:
                if (Paths.currentLocWithinTolerance(bot.getFollower().getPose(), FieldLocations.subShortSideSetupPose, 2.5, 2.5)) {
                    bot.followPath(Paths.subShortSideSetupToSubShortSide, true);
                    setPathState(17);
                }
                break;

            case 17:
                if (bot.bumperBumped()) {
                    setPathState(18);
                }
                break;

            case 18:
                if (!bot.handlerIsBusy() && !bot.onHold()) {
                    bot.setMode(Modes.HANG_SPECIMEN);
                    setPathState(20);
                }
                break;

            case 20:
                if (!bot.handlerIsBusy() && !bot.onHold()) {
                    bot.setHandlerArmPositionPreset(HandlerArmPositions.SPECIMEN_WALL, 0);
                    bot.setMode(Modes.HANDLER_GRAB_SPECIMEN_POS);
                    bot.followPath(Paths.subShortSideToSpecimenGrabSetup, false);

                    setPathState(21);
                }
                break;

            case 21:
                if (Paths.currentLocWithinTolerance(bot.getFollower().getPose(), FieldLocations.specimenGrabSetupPose, 2, 2)) {
                    bot.followPath(Paths.specimenGrabSetupToSpecimenGrab, false);

                    FieldLocations.subShortSideSetupPose =
                            new Pose(FieldLocations.subShortSideSetupPose.getX() + gapBetweenHangingSpecimensIN,
                                    FieldLocations.subShortSideSetupPose.getY(),
                                    FieldLocations.subShortSideSetupPose.getHeading());

                    FieldLocations.subShortSidePose =
                            new Pose(FieldLocations.subShortSidePose.getX() + gapBetweenHangingSpecimensIN,
                                    FieldLocations.subShortSidePose.getY(),
                                    FieldLocations.subShortSidePose.getHeading());

                    Paths.buildSpecimenHangPaths(bot.getFollower());

                    setPathState(22);
                }
                break;

            case 22:
                if (Paths.currentLocWithinTolerance(bot.getFollower().getPose(), FieldLocations.specimenGrabPose, 2, 2)) {
                    bot.setMode(Modes.GRAB_SPECIMEN);
                    setPathState(23);
                }
                break;

            case 23:
                if (!bot.handlerIsBusy() && !bot.onHold()) {
                    bot.setHandlerArmPositionPreset(HandlerArmPositions.SPECIMEN_HANG, 0);
                    bot.setMode(Modes.HANDLER_HIGH_SPECIMEN_POS);
                    bot.followPath(Paths.specimenGrabToSubShortSideSetup, false);
                    setPathState(24);
                }
                break;

            case 24:
                if (Paths.currentLocWithinTolerance(bot.getFollower().getPose(), FieldLocations.subShortSideSetupPose, 2.5, 2.5)) {
                    bot.followPath(Paths.subShortSideSetupToSubShortSide, true);
                    setPathState(25);
                }
                break;

            case 25:
                if (bot.bumperBumped()) {
                    setPathState(26);
                }
                break;

            case 26:
                if (!bot.handlerIsBusy() && !bot.onHold()) {
                    bot.setMode(Modes.HANG_SPECIMEN);
                    setPathState(30);
                }
                break;

            case 30:
                if (!bot.handlerIsBusy() && !bot.onHold()) {
                    bot.followPath(Paths.subShortSideToSpecimenGrabSetup, false);
                    bot.setHandlerArmPositionPreset(HandlerArmPositions.SPECIMEN_WALL, 0);
                    bot.setMode(Modes.HANDLER_GRAB_SPECIMEN_POS);

                    setPathState(31);
                }
                break;

            case 31:
                if (Paths.currentLocWithinTolerance(bot.getFollower().getPose(), FieldLocations.specimenGrabSetupPose, 2, 2)) {
                    bot.followPath(Paths.specimenGrabSetupToSpecimenGrab, false);

                    FieldLocations.subShortSideSetupPose =
                            new Pose(FieldLocations.subShortSideSetupPose.getX() + gapBetweenHangingSpecimensIN,
                                    FieldLocations.subShortSideSetupPose.getY(),
                                    FieldLocations.subShortSideSetupPose.getHeading());

                    FieldLocations.subShortSidePose =
                            new Pose(FieldLocations.subShortSidePose.getX() + gapBetweenHangingSpecimensIN,
                                    FieldLocations.subShortSidePose.getY(),
                                    FieldLocations.subShortSidePose.getHeading());

                    Paths.buildSpecimenHangPaths(bot.getFollower());
                    setPathState(32);
                }
                break;

            case 32:
                if (Paths.currentLocWithinTolerance(bot.getFollower().getPose(), FieldLocations.specimenGrabPose, 2, 2)) {
                    bot.setMode(Modes.GRAB_SPECIMEN);
                    setPathState(33);
                }
                break;

            case 33:
                if (!bot.handlerIsBusy() && !bot.onHold()) {
                    bot.setHandlerArmPositionPreset(HandlerArmPositions.SPECIMEN_HANG, 0);
                    bot.setMode(Modes.HANDLER_HIGH_SPECIMEN_POS);
                    bot.followPath(Paths.specimenGrabToSubShortSideSetup, false);
                    setPathState(34);
                }
                break;

            case 34:
                if (Paths.currentLocWithinTolerance(bot.getFollower().getPose(), FieldLocations.subShortSideSetupPose, 2.5, 2.5)) {
                    bot.followPath(Paths.subShortSideSetupToSubShortSide, true);
                    setPathState(35);
                }
                break;

            case 35:
                if (bot.bumperBumped()) {
                    setPathState(36);
                }
                break;

            case 36:
                if (!bot.handlerIsBusy() && !bot.onHold()) {
                    bot.setMode(Modes.HANG_SPECIMEN);
                    setPathState(40);
                }
                break;

            case 40:
                if (!bot.handlerIsBusy() && !bot.onHold()) {
                    bot.followPath(Paths.subShortSideToSpecimenGrabSetup, false);
                    bot.setHandlerArmPositionPreset(HandlerArmPositions.SPECIMEN_WALL, 0);
                    bot.setMode(Modes.HANDLER_GRAB_SPECIMEN_POS);

                    setPathState(41);
                }
                break;

            case 41:
                if (Paths.currentLocWithinTolerance(bot.getFollower().getPose(), FieldLocations.specimenGrabSetupPose, 2, 2)) {
                    bot.followPath(Paths.specimenGrabSetupToSpecimenGrab, false);

                    FieldLocations.subShortSideSetupPose =
                            new Pose(FieldLocations.subShortSideSetupPose.getX() + gapBetweenHangingSpecimensIN,
                                    FieldLocations.subShortSideSetupPose.getY(),
                                    FieldLocations.subShortSideSetupPose.getHeading());

                    FieldLocations.subShortSidePose =
                            new Pose(FieldLocations.subShortSidePose.getX() + gapBetweenHangingSpecimensIN,
                                    FieldLocations.subShortSidePose.getY(),
                                    FieldLocations.subShortSidePose.getHeading());

                    Paths.buildSpecimenHangPaths(bot.getFollower());
                    setPathState(42);
                }
                break;

            case 42:
                if (Paths.currentLocWithinTolerance(bot.getFollower().getPose(), FieldLocations.specimenGrabPose, 2, 2)) {
                    bot.setMode(Modes.GRAB_SPECIMEN);
                    setPathState(43);
                }
                break;

            case 43:
                if (!bot.handlerIsBusy() && !bot.onHold()) {
                    bot.setHandlerArmPositionPreset(HandlerArmPositions.SPECIMEN_HANG, 0);
                    bot.setMode(Modes.HANDLER_HIGH_SPECIMEN_POS);
                    bot.followPath(Paths.specimenGrabToSubShortSideSetup, false);
                    setPathState(44);
                }
                break;

            case 44:
                if (Paths.currentLocWithinTolerance(bot.getFollower().getPose(), FieldLocations.subShortSideSetupPose, 2.5, 2.5)) {
                    bot.followPath(Paths.subShortSideSetupToSubShortSide, true);
                    setPathState(45);
                }
                break;

            case 45:
                if (bot.bumperBumped()) {
                    setPathState(46);
                }
                break;

            case 46:
                if (!bot.handlerIsBusy() && !bot.onHold()) {
                    bot.setMode(Modes.HANG_SPECIMEN);
                    setPathState(47);
                }
                break;

            case 47:
                if (!bot.handlerIsBusy() && !bot.onHold()) {
                    bot.setMode(Modes.AUTO_END_POS);
                    setPathState(99);
                }
                break;

            case 99:
                if (!bot.isBusy() && !bot.onHold()) {
                    setPathState(-1);
                    requestOpModeStop();
                }
                break;
        }
    }
}


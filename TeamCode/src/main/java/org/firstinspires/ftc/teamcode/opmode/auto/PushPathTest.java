package org.firstinspires.ftc.teamcode.opmode.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

import org.firstinspires.ftc.teamcode.common.Modes;
import org.firstinspires.ftc.teamcode.common.hardwareConfiguration.positions.HandlerArmPositions;
import org.firstinspires.ftc.teamcode.common.hardwareConfiguration.positions.IntakeSwivelPositions;
import org.firstinspires.ftc.teamcode.opmode.FieldLocations;
import org.firstinspires.ftc.teamcode.opmode.Paths;

@Autonomous(name = "PushPathTest", group = "Specimen")
@Disabled
public class PushPathTest extends SpecimenAutoOpMode {

    protected void autonomousPathUpdate() {
        switch (pathState) {
            case 0:
                bot.followPath(Paths.startToSubShortSideSetup, false);
                setPathState(1);
                break;

            case 1:
                if (Paths.currentLocWithinTolerance(bot.getFollower().getPose(), FieldLocations.subShortSideSetupPose, 2, 2)) {
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
                bot.followPath(Paths.pushSpike1, false);
                setPathState(4);
                break;

            case 4:
                if (Paths.currentLocWithinTolerance(bot.getFollower().getPose(), FieldLocations.specimenSpike1DropPose, 2, 2)) {
                    bot.followPath(Paths.pushSpike2, false);
                    setPathState(5);
                }
                break;

            case 5:
                if (Paths.currentLocWithinTolerance(bot.getFollower().getPose(), FieldLocations.specimenSpike2DropPose, 2, 2)) {
                    bot.followPath(Paths.pushSpike3, false);
                    setPathState(6);
                }
                break;

//            case 6:
//                if (Paths.currentLocWithinTolerance(bot.getFollower().getPose(), FieldLocations.specimenSpike3DropPose, 2, 2)) {
//                    bot.followPath(Paths.pushFinish, false);
//                    setPathState(99);
//                }
//                break;

            case 99:
                if (!bot.followerIsBusy() && !bot.isBusy() && !bot.onHold()) {
                    setPathState(-1);
                    requestOpModeStop();
                }
                break;
        }
    }
}


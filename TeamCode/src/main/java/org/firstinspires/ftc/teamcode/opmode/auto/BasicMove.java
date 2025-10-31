package org.firstinspires.ftc.teamcode.opmode.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

import org.firstinspires.ftc.teamcode.common.Modes;
import org.firstinspires.ftc.teamcode.common.hardwareConfiguration.setPoints.ExtendoPositions;
import org.firstinspires.ftc.teamcode.common.hardwareConfiguration.setPoints.HandlerArmPositions;
import org.firstinspires.ftc.teamcode.common.hardwareConfiguration.setPoints.HandlerGrabberPositions;
import org.firstinspires.ftc.teamcode.common.hardwareConfiguration.setPoints.LiftPositions;
import org.firstinspires.ftc.teamcode.opmode.Paths;

@Autonomous(name = "BasicMove", group = "Bucket")
@Disabled
public class BasicMove extends BucketAutoOpMode {

    protected void autonomousPathUpdate() {
        switch (pathState) {
            case 0:
                bot.setLiftPositionPreset(LiftPositions.HIGH_BUCKET_AUTO);
                bot.setHandlerArmPositionPreset(HandlerArmPositions.TOP,0);
                bot.followPath(Paths.startToBucket, false);
                setPathState(1);
                break;

            case 1:
                if (!bot.handlerIsBusy() && !bot.onHold()) {
                    bot.setHandlerArmPositionPreset(HandlerArmPositions.HIGH_BUCKET,0);
                    bot.setMode(Modes.HANDLER_HIGH_BUCKET_POS);
                    setPathState(2);
                }
                break;

            // Drop brick into high bucket
            case 2:
                if (!bot.handlerIsBusy() && !bot.onHold()) {
                    bot.setHandlerGrabberPositionPreset(HandlerGrabberPositions.OPEN);
                    setPathState(3);
                }
                break;

            // Go to spike 1
            case 3:
                if (!bot.handlerIsBusy() && !bot.onHold()) {
                    bot.followPath(Paths.bucketToSampleHumanPlayer, false);
                    bot.setHandlerArmPositionPreset(HandlerArmPositions.HANDOFF,0);
                    bot.setMode(Modes.INTAKE_HOVER_POS);
                    setPathState(4);
                }
                break;

            case 4:
                if (!bot.intakeIsBusy() && !bot.onHold()) {
                    bot.setMode(Modes.HANDLER_HANDOFF_PREP_POS);
                    setPathState(5);
                }
                break;

            // Grab brick from spike1
            case 5:
                if (!bot.followerIsBusy() && !bot.onHold()) {
                    bot.setMode(Modes.INTAKE_BRICK_FOR_HANDOFF);
                    setPathState(6);
                }
                break;

            case 6:
                if (!bot.intakeIsBusy() && !bot.onHold()) {
                    bot.setExtendoPositionPreset(ExtendoPositions.RETRACTED);
                    setPathState(7);
                }
                break;

            case 7:
                if (!bot.intakeIsBusy() && !bot.handlerIsBusy() && !bot.onHold()) {
                    bot.setMode(Modes.HAND_OFF_BRICK);
                    bot.followPath(Paths.sampleHumanPlayerToBucket, false);
                    setPathState(8);
                }
                break;

            case 8:
                if (!bot.isBusy()) {
                    setPathState(-1);
                    requestOpModeStop();
                }
                break;
        }
    }
}


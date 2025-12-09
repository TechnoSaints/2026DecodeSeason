package org.firstinspires.ftc.teamcode.opmode.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

import org.firstinspires.ftc.teamcode.common.Modes;
import org.firstinspires.ftc.teamcode.opmode.Paths;


@Autonomous(name = "BasicMove", group = "Bucket")
@Disabled
public class BasicMove extends AutoOpMode {

    protected void autonomousPathUpdate() {
        switch (pathState) {
            case 0:
                //bot.(LiftPositions.HIGH_BUCKET_AUTO);
                //bot.setHandlerArmPositionPreset(HandlerArmPositions.TOP,0);
                bot.followPath(Paths.startToLaunchPosition, false);
                setPathState(1);
                break;

//            case 1:
//                if (!bot.handlerIsBusy() && !bot.onHold()) {
//                    bot.setHandlerArmPositionPreset(HandlerArmPositions.HIGH_BUCKET,0);
//                    bot.setMode(Modes.HANDLER_HIGH_BUCKET_POS);
//                    setPathState(2);
//                }
//                break;


            case 2:
                if (!bot.isBusy()) {
                    setPathState(-1);
                    requestOpModeStop();
                }
                break;
        }
    }
}


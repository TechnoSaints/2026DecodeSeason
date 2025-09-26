package org.firstinspires.ftc.teamcode.opmode.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

import org.firstinspires.ftc.teamcode.common.Modes;
import org.firstinspires.ftc.teamcode.common.hardwareConfiguration.positions.ExtendoPositions;
import org.firstinspires.ftc.teamcode.common.hardwareConfiguration.positions.HandlerArmPositions;
import org.firstinspires.ftc.teamcode.common.hardwareConfiguration.positions.HandlerGrabberPositions;
import org.firstinspires.ftc.teamcode.common.hardwareConfiguration.positions.IntakeSwivelPositions;
import org.firstinspires.ftc.teamcode.common.hardwareConfiguration.positions.LiftPositions;
import org.firstinspires.ftc.teamcode.opmode.Paths;

@Autonomous(name = "BasicMove", group = "Bucket")
@Disabled
public class BasicMove extends BucketAutoOpMode {

    protected void autonomousPathUpdate() {
        switch (pathState) {
            case 0:
                bot.followPath(Paths.startToBucket, false);
                setPathState(1);
                break;

            case 1:
                break;

            // Drop brick into high bucket
            case 2:
                break;

            // Go to spike 1
            case 3:
                setPathState(4);
                break;

            case 4:
                    setPathState(5);
                break;

            // Grab brick from spike1
            case 5:
                    setPathState(6);

                break;

            case 6:
                    setPathState(7);

                break;

            case 7:
                    bot.followPath(Paths.sampleHumanPlayerToBucket, false);
                    setPathState(8);

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


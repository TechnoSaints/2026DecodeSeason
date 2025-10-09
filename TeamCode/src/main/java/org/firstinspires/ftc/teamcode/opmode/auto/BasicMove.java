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
public class BasicMove extends BucketAutoOpMode {

    protected void autonomousPathUpdate() {
        switch (pathState) {
            case 0:
                bot.followPath(Paths.testX, false);
                setPathState(1);
                break;/*

            case 1:
                bot.followPath(Paths.returnX, false);
                setPathState(2);
                break;

            // Drop brick into high bucket
            case 2:
                bot.followPath(Paths.testY, false);
                setPathState(3);
                break;

            // Go to spike 1
            case 3:
                bot.followPath(Paths.returnY, false);
                setPathState(4);
                break;

            case 4:
                bot.followPath(Paths.testRotation, false);
                setPathState(5);
                break;

            // Grab brick from spike1
            case 5:
                bot.followPath(Paths.returnRotation, false);
                    setPathState(6);

                break;

            case 6:
                bot.followPath(Paths.doubleTest, false);
                    setPathState(7);

                break;

            case 7:
                    bot.followPath(Paths.returnDoubleTest, false);
                    setPathState(8);
                break;

            case 8:
                bot.followPath(Paths.tripleTest, false);
                setPathState(9);
                break;

            case 9:
                bot.followPath(Paths.tripleTest, false);
                setPathState(10);
                break;

            case 10:
                if (!bot.isBusy()) {
                    setPathState(-1);
                    requestOpModeStop();
                }
                break; */
        }
    }
}


package org.firstinspires.ftc.teamcode.opmode.auto.RedAuto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

import org.firstinspires.ftc.teamcode.opmode.FieldLocations;
import org.firstinspires.ftc.teamcode.opmode.Paths;
import org.firstinspires.ftc.teamcode.opmode.auto.AutoOpMode;

@Disabled
@Autonomous(name = "\uD83D\uDD34longRedAuto3Ball", group = "Red")
public class LongRedAuto3Ball extends AutoOpMode {

    @Override
    public void init() {
        FieldLocations.buildPoses("red", "long");
        super.init();
    }

    protected void autonomousPathUpdate() {
        switch (pathState) {
            // Move start to short shot
            case 0:
                bot.followPath(Paths.startToLongShot, 0.6f, false);
                bot.setLauncherLongShot();
                bot.stickLoad();
                setPathState(1);
                break;

            // Shoot three balls after move is finished
            case 1:
                if (!bot.followerIsBusy()) {
                    bot.stickLaunch();
                    setPathState(2);
                }
                break;

            // Move to stack1 setup
            case 2:
                    bot.followPath(Paths.longShotToParking, 0.8f, false);
                    setPathState(3);
                break;

            // Stop opmode
            case 3:
                if (!bot.followerIsBusy()) {
                    setPathState(-1);
                    requestOpModeStop();
                    break;
                }
        }
    }
}


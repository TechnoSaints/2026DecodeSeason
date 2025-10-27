package org.firstinspires.ftc.teamcode.opmode.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.opmode.Paths;

@Autonomous(name="BlueFarAuto", group = "Blue")
public class BlueFarAuto extends BlueAutoOpMode{

    protected void autonomousPathUpdate(){
        switch (pathState){
            case 0:
                bot.followPath(Paths.blueS3B3Pickup, false);
                setPathState(1);
                break;

            case 1:

        }
    }
}

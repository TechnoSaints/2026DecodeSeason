package org.firstinspires.ftc.teamcode.common;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.opmode.Paths;

public class sortingMachine extends Component{

    protected int pathState;

    private BotSensors sense;
    private Bot bot;

    public sortingMachine(HardwareMap hardwareMap, OpMode opmode) {
        sense =  new BotSensors(hardwareMap, opmode, telemetry);
        // init bot here
    }

    protected void setPathState(int pState)
    {
        pathState = pState;
    }
    protected void autonomousPathUpdate() {
        switch (pathState) {

            //Move From Start To Short Shot

            case 0:
                setPathState(1);
                break;

            //Shooting The First Shot
            case 1:

                //please improt these 2
                
                if (!bot.followerIsBusy()) {
                    controlTimer.reset();
                    setPathState(2);
                }
                break;
        }
    }
}

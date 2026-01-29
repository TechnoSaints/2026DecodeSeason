package org.firstinspires.ftc.teamcode.opmode.auto.standard;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.opmode.FieldLocations;
import org.firstinspires.ftc.teamcode.opmode.auto.StandardBase;

@Autonomous(name = "\uD83D\uDD34Standard Red Auto (12)", group = "0Red", preselectTeleOp = "Red Far Teleop - After Auto")
public class RedAuto extends StandardBase {

    @Override
    public void init() {
        FieldLocations.buildPoses("red", "long");
        super.init();
    }
}


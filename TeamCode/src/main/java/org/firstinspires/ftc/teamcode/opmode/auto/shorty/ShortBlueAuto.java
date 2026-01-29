package org.firstinspires.ftc.teamcode.opmode.auto.shorty;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.opmode.FieldLocations;
import org.firstinspires.ftc.teamcode.opmode.auto.ShortBase;

@Autonomous(name = "\uD83D\uDD34Short Blue Auto (12)", group = "1Blue", preselectTeleOp = "Blue Short Teleop - After Auto")
public class ShortBlueAuto extends ShortBase {

    @Override
    public void init() {
        FieldLocations.buildPoses("blue", "short");
        super.init();
    }
}


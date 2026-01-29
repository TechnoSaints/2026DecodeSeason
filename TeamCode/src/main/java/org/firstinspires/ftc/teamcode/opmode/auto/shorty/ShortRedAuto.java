package org.firstinspires.ftc.teamcode.opmode.auto.shorty;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.opmode.FieldLocations;
import org.firstinspires.ftc.teamcode.opmode.auto.ShortBase;

@Autonomous(name = "\uD83D\uDD34Short Red Auto (12)", group = "1Red", preselectTeleOp = "Red Short Teleop - After Auto")
public class ShortRedAuto extends ShortBase {

    @Override
    public void init() {
        FieldLocations.buildPoses("red", "short");
        super.init();
    }
}


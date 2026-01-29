package org.firstinspires.ftc.teamcode.opmode.auto.far;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.opmode.FieldLocations;
import org.firstinspires.ftc.teamcode.opmode.auto.FarBase;
import org.firstinspires.ftc.teamcode.opmode.auto.StandardBase;

@Autonomous(name = "\uD83D\uDD34Far Red Auto (12)", group = "2Red", preselectTeleOp = "Red Far Teleop - After Auto")
public class FarRedAuto extends FarBase {

    @Override
    public void init() {
        FieldLocations.buildPoses("red", "long");
        super.init();
    }
}


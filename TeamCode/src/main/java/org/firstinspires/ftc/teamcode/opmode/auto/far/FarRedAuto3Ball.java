package org.firstinspires.ftc.teamcode.opmode.auto.far;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.opmode.FieldLocations;
import org.firstinspires.ftc.teamcode.opmode.auto.FarBase;

@Autonomous(name = "\uD83D\uDD34Far Red Auto - 3 Balls", group = "2Red", preselectTeleOp = "Red Far Teleop - After Auto")
public class FarRedAuto3Ball extends FarBase {

    @Override
    public void init() {
        FieldLocations.buildPoses("red", "long");
        skipFromState = 1;
        super.init();
    }
}


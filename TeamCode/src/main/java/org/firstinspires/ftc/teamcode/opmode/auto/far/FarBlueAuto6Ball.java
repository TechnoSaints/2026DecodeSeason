package org.firstinspires.ftc.teamcode.opmode.auto.far;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.opmode.FieldLocations;
import org.firstinspires.ftc.teamcode.opmode.auto.FarBase;

@Autonomous(name = "\uD83D\uDD35Far Blue Auto - 6 Balls)", group = "2Blue", preselectTeleOp = "Blue Far Teleop - After Auto")
public class FarBlueAuto6Ball extends FarBase {

    @Override
    public void init() {
        FieldLocations.buildPoses("blue", "long");
        skipFromState = 6;
        super.init();
    }
}


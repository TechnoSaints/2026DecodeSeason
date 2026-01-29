package org.firstinspires.ftc.teamcode.opmode.auto.far;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.opmode.FieldLocations;
import org.firstinspires.ftc.teamcode.opmode.auto.FarBase;
import org.firstinspires.ftc.teamcode.opmode.auto.StandardBase;

@Autonomous(name = "\uD83D\uDD35Far Blue Auto (12)", group = "2Blue", preselectTeleOp = "Blue Far Teleop - After Auto")
public class FarBlueAuto extends FarBase {

    @Override
    public void init() {
        FieldLocations.buildPoses("blue", "long");
        super.init();
    }
}


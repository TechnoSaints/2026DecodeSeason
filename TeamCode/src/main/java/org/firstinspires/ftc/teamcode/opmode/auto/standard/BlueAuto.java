package org.firstinspires.ftc.teamcode.opmode.auto.standard;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.opmode.FieldLocations;
import org.firstinspires.ftc.teamcode.opmode.auto.StandardBase;

@Autonomous(name = "\uD83D\uDD35Standard Blue Auto (12)", group = "0Blue", preselectTeleOp = "Blue Far Teleop - After Auto")
public class BlueAuto extends StandardBase {

    @Override
    public void init() {
        FieldLocations.buildPoses("blue", "long");
        super.init();
    }
}


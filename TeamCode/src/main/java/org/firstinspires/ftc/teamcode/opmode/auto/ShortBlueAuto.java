package org.firstinspires.ftc.teamcode.opmode.auto;

import com.pedropathing.geometry.Pose;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.opmode.FieldLocations;
import org.firstinspires.ftc.teamcode.opmode.Paths;

@Autonomous(name = "\uD83D\uDD34shortBlueAuto", group = "Blue")
public class ShortBlueAuto extends ShortStandardBase {

    @Override
    public void init() {
        FieldLocations.buildPoses("blue", "short");
        super.init();
    }
}


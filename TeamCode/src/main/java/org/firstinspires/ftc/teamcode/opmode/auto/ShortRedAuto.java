package org.firstinspires.ftc.teamcode.opmode.auto;

import com.pedropathing.geometry.Pose;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

import org.firstinspires.ftc.teamcode.opmode.FieldLocations;
import org.firstinspires.ftc.teamcode.opmode.Paths;

@Autonomous(name = "\uD83D\uDD34shortRedAuto", group = "Red")
public class ShortRedAuto extends ShortStandardBase {

    @Override
    public void init() {
        FieldLocations.buildPoses("red", "short");
        super.init();
    }
}


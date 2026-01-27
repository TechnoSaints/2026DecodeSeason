package org.firstinspires.ftc.teamcode.opmode.auto.farStandard;

import com.pedropathing.geometry.Pose;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.opmode.FieldLocations;
import org.firstinspires.ftc.teamcode.opmode.Paths;
import org.firstinspires.ftc.teamcode.opmode.auto.AutoOpMode;
import org.firstinspires.ftc.teamcode.opmode.auto.FarStandardBase;

@Autonomous(name = "\uD83D\uDD35Far Standard Blue Auto", group = "Blue", preselectTeleOp = "Blue Far Teleop - After Auto")
public class FarBlueAuto extends FarStandardBase {

    @Override
    public void init() {
        FieldLocations.buildPoses("blue", "long");
        super.init();
    }
}


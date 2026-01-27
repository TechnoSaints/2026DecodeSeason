package org.firstinspires.ftc.teamcode.opmode.auto.farStandard;

import com.pedropathing.geometry.Pose;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.common.hardwareConfiguration.setPoints.LauncherSettings;
import org.firstinspires.ftc.teamcode.opmode.FieldLocations;
import org.firstinspires.ftc.teamcode.opmode.Paths;
import org.firstinspires.ftc.teamcode.opmode.auto.AutoOpMode;
import org.firstinspires.ftc.teamcode.opmode.auto.FarStandardBase;

@Autonomous(name = "\uD83D\uDD34Far Red Auto", group = "Red", preselectTeleOp = "Red Far Teleop - After Auto")
public class FarRedAuto extends FarStandardBase {

    @Override
    public void init() {
        FieldLocations.buildPoses("red", "long");
        super.init();
    }
}


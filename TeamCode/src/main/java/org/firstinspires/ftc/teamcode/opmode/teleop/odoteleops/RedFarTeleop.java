package org.firstinspires.ftc.teamcode.opmode.teleop.odoteleops;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.opmode.FieldLocations;
import org.firstinspires.ftc.teamcode.opmode.teleop.TeleopOdoBase;

@TeleOp(name = "Red Far Teleop - After Auto", group = "2 Preset")
public class RedFarTeleop extends TeleopOdoBase {
    @Override
    public void init() {
        FieldLocations.buildPoses("red", "long", true);
        super.init();
    }
}
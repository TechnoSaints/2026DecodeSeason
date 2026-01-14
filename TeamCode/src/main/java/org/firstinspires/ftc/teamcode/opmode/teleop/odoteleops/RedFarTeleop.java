package org.firstinspires.ftc.teamcode.opmode.teleop.odoteleops;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.opmode.FieldLocations;
import org.firstinspires.ftc.teamcode.opmode.teleop.TeleopOdoBase;
import org.firstinspires.ftc.teamcode.opmode.teleop.TeleopWithOdoSimple;

@TeleOp(name = "Red Far Teleop - After Auto", group = "2 Preset")
public class RedFarTeleop extends TeleopOdoBase {
    public RedFarTeleop() {
        FieldLocations.buildPoses("red", "long", true);
    }
}
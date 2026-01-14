package org.firstinspires.ftc.teamcode.opmode.teleop.odoteleops;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.opmode.FieldLocations;
import org.firstinspires.ftc.teamcode.opmode.teleop.TeleopOdoBase;

@TeleOp(name = "Unknown Start Position Teleop (emergency)", group = "1 Game")
public class UnknownTeleop extends TeleopOdoBase {

    public UnknownTeleop() {
        // Arbitrary start until we can build actual poses
        FieldLocations.buildPoses("blue", "long", true);
        super.noStart = true;
    }
}
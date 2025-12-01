package org.firstinspires.ftc.teamcode.opmode.teleop.odoteleops;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.opmode.FieldLocations;
import org.firstinspires.ftc.teamcode.opmode.teleop.TeleopOdoBase;
import org.firstinspires.ftc.teamcode.opmode.teleop.TeleopWithOdoSimple;

@TeleOp(name = "Blue Base Teleop - Odo Simple", group = "1Odo")
public class BlueNearTeleop extends TeleopOdoBase {

    public BlueNearTeleop() {
        super.red = false;
        super.startPose = FieldLocations.blueBaseStartPose;
    }
}
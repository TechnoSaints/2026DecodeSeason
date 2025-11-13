package org.firstinspires.ftc.teamcode.opmode.teleop.odoteleops;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.opmode.FieldLocations;
import org.firstinspires.ftc.teamcode.opmode.teleop.TeleopWithOdoSimple;

@TeleOp(name = "Red Base Teleop - Odo Simple", group = "1Odo")
public class RedNearTeleop extends TeleopWithOdoSimple {

    public RedNearTeleop() {
        super.red = true;
        super.startPose = FieldLocations.redBaseStartPose;
    }
}
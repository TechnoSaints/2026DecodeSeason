package org.firstinspires.ftc.teamcode.opmode.teleop.odoteleops;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.opmode.FieldLocations;
import org.firstinspires.ftc.teamcode.opmode.teleop.TeleopOdoBase;
import org.firstinspires.ftc.teamcode.opmode.teleop.TeleopWithOdoSimple;

@TeleOp(name = "Red Goal Teleop - Odo Simple", group = "1Odo")
public class RedFarTeleop extends TeleopOdoBase {
    public RedFarTeleop() {
        super.red = true;
        super.startPose = FieldLocations.redGoalStartPose;
    }
}
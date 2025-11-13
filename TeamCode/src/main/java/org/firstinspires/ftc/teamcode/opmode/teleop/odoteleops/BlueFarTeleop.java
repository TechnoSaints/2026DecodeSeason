package org.firstinspires.ftc.teamcode.opmode.teleop.odoteleops;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.opmode.FieldLocations;
import org.firstinspires.ftc.teamcode.opmode.teleop.TeleopWithOdoSimple;

@TeleOp(name = "Blue Goal Teleop - Odo Simple", group = "1Odo")
public class BlueFarTeleop extends TeleopWithOdoSimple {

    public BlueFarTeleop() {
        super.red = false;
        super.startPose = FieldLocations.blueGoalStartPose;
    }
    }
package org.firstinspires.ftc.teamcode.opmode.teleop;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.common.TeleopBotSimple;
import org.firstinspires.ftc.teamcode.opmode.FieldLocations;

import java.util.ArrayList;
import java.util.List;

public class RedFarTeleop extends TeleopWithOdoSimple {

    public RedFarTeleop() {
        super.red = true;
        super.startPose = FieldLocations.redGoalStartPose;
    }
    }
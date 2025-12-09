package org.firstinspires.ftc.teamcode.common.hardwareConfiguration.data;

import com.qualcomm.robotcore.hardware.DcMotorSimple;

public class DrivetrainData {
    public double maxFastTeleopPower = 0.80;
    public double maxMediumTeleopPower = 0.45;
    public double maxSlowTeleopPower = 0.45;
    public double wheelDiameterMM = 104.0;
    public double wheelDiameterInches = wheelDiameterMM / 25.4;
    public double wheelCircumferenceInches = Math.PI * wheelDiameterInches;
    public String leftFrontMotorName = "leftFrontDrive";
    public String leftRearMotorName = "leftRearDrive";
    public String rightFrontMotorName = "rightFrontDrive";
    public String rightRearMotorName = "rightRearDrive";
    public DcMotorSimple.Direction leftFrontMotorDirection = DcMotorSimple.Direction.FORWARD;
    public DcMotorSimple.Direction leftRearMotorDirection = DcMotorSimple.Direction.FORWARD;
    public DcMotorSimple.Direction rightFrontMotorDirection = DcMotorSimple.Direction.REVERSE;
    public DcMotorSimple.Direction rightRearMotorDirection = DcMotorSimple.Direction.REVERSE;
}

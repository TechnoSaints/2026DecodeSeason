package org.firstinspires.ftc.teamcode.opmode.tuning;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

public class IMULimelight {
    private IMU imu;

    // Initialize the IMU with orientation
    public void init(HardwareMap hwMap) {
        imu = hwMap.get(IMU.class, "imu");

        RevHubOrientationOnRobot RevOrientation = new RevHubOrientationOnRobot(
                RevHubOrientationOnRobot.LogoFacingDirection.RIGHT, // Ivan change the "FORWARD" to the actual direction the logo is facing on the device
                RevHubOrientationOnRobot.UsbFacingDirection.FORWARD // Ivan change the "FORWARD" to the actual direction the USB is facing on the device
        );

        imu.initialize(new IMU.Parameters(RevOrientation));
    }

    // Getting the IMU info
    public double getHeading() {
        return imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.DEGREES); // If Radians change degrees to radians
    }

}

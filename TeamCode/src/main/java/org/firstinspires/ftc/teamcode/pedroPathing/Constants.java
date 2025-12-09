package org.firstinspires.ftc.teamcode.pedroPathing;

import com.pedropathing.control.FilteredPIDFCoefficients;
import com.pedropathing.control.PIDFCoefficients;
import com.pedropathing.follower.Follower;
import com.pedropathing.follower.FollowerConstants;
import com.pedropathing.ftc.FollowerBuilder;
import com.pedropathing.ftc.drivetrains.MecanumConstants;
import com.pedropathing.ftc.localization.constants.PinpointConstants;
import com.pedropathing.paths.PathConstraints;
import com.qualcomm.hardware.gobilda.GoBildaPinpointDriver;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.common.hardwareConfiguration.data.DrivetrainData;

public class Constants {
    private static DrivetrainData drivetrainData = new DrivetrainData();
    public static FollowerConstants followerConstants = new FollowerConstants()
            .mass(11.35)
            .forwardZeroPowerAcceleration(-17.11541848)
            .lateralZeroPowerAcceleration(-46.95679073)
            .useSecondaryTranslationalPIDF(false)
            .useSecondaryHeadingPIDF(false)
            .useSecondaryDrivePIDF(false)
            .centripetalScaling(0.0001)
            .translationalPIDFCoefficients(new PIDFCoefficients(0.15, 0, 0.02, 0))
            .headingPIDFCoefficients(new PIDFCoefficients(2, 0, 0.1, 0))
            .drivePIDFCoefficients(
                    new FilteredPIDFCoefficients(0.007, 0, 0.000001, 0.6, 0)
            );

    public static MecanumConstants driveConstants = new MecanumConstants()
            .leftFrontMotorName(drivetrainData.leftFrontMotorName)
            .leftRearMotorName(drivetrainData.leftRearMotorName)
            .rightFrontMotorName(drivetrainData.rightFrontMotorName)
            .rightRearMotorName(drivetrainData.rightRearMotorName)
            .leftFrontMotorDirection(drivetrainData.leftFrontMotorDirection)
            .leftRearMotorDirection(drivetrainData.leftRearMotorDirection)
            .rightFrontMotorDirection(drivetrainData.rightFrontMotorDirection)
            .rightRearMotorDirection(drivetrainData.rightRearMotorDirection)
            .xVelocity(66.9132988397)
            .yVelocity(44)
            .useBrakeModeInTeleOp(true)
            .nominalVoltage(12.5)
            .useVoltageCompensation(true);

    public static PinpointConstants localizerConstants = new PinpointConstants()
            .forwardPodY(-5.768)
            .strafePodX(-4.838)
            .distanceUnit(DistanceUnit.INCH)
            .hardwareMapName("pinpoint")
            .encoderResolution(
                    GoBildaPinpointDriver.GoBildaOdometryPods.goBILDA_SWINGARM_POD
            )
            .forwardEncoderDirection(GoBildaPinpointDriver.EncoderDirection.FORWARD)
            .strafeEncoderDirection(GoBildaPinpointDriver.EncoderDirection.FORWARD);

    public static PathConstraints pathConstraints = new PathConstraints(
            0.995,
            500,
            0.75,
            1
    );

    public static Follower createFollower(HardwareMap hardwareMap) {
        return new FollowerBuilder(followerConstants, hardwareMap)
                .mecanumDrivetrain(driveConstants)
                .pinpointLocalizer(localizerConstants)
                .pathConstraints(pathConstraints)
                .build();
    }
}


package org.firstinspires.ftc.teamcode.common;

import com.pedropathing.geometry.Pose;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.common.hardwareConfiguration.data.DrivetrainData;
import org.firstinspires.ftc.teamcode.common.hardwareConfiguration.data.GoBilda435DcMotorData;

import javax.annotation.Nullable;

public class TeleopBotSimple extends Component {
    private final Drivetrain drivetrain;
    private final Launcher launcher;
    private final Storage storage;
    private double driveAxial = 0.0;
    private double driveStrafe = 0.0;
    private double driveYaw = 0.0;

    // new constructor for odo in teleop
    public TeleopBotSimple(OpMode opMode, Telemetry telemetry, Pose pose) {
        super(telemetry);
        drivetrain = new Drivetrain(opMode.hardwareMap, telemetry, new DrivetrainData(), new GoBilda435DcMotorData());
        launcher = new Launcher(telemetry, opMode.hardwareMap);
        storage = new Storage(telemetry, opMode.hardwareMap);
        drivetrain.setOdoStartingPose(pose);
    }

    // legacy constructor for TeleopNoOdo
    public TeleopBotSimple(OpMode opMode, Telemetry telemetry) {
        super(telemetry);
        drivetrain = new Drivetrain(opMode.hardwareMap, telemetry, new DrivetrainData(), new GoBilda435DcMotorData());
        launcher = new Launcher(telemetry, opMode.hardwareMap);
        storage = new Storage(telemetry, opMode.hardwareMap);
    }

    public void processDrivetrainInput(Gamepad gamepad) {
        if (gamepad.dpad_up) {
            drivetrain.creepDirection(1.0, 0.0, 0.0);
        } else if (gamepad.dpad_down) {
            drivetrain.creepDirection(-1.0, 0.0, 0.0);
        } else if (gamepad.dpad_left) {
            drivetrain.creepDirection(0.0, -1.0, 0.0);
        } else if (gamepad.dpad_right) {
            drivetrain.creepDirection(0.0, 1.0, 0.0);
        } else {
            driveAxial = -gamepad.left_stick_y;
            driveStrafe = gamepad.left_stick_x;
            driveYaw = gamepad.right_stick_x;
            if ((Math.abs(driveAxial) < 0.2) && (Math.abs(driveStrafe) < 0.2) && (Math.abs(driveYaw) < 0.2)) {
                drivetrain.stop();
            } else
                drivetrain.moveDirection(driveAxial, driveStrafe, driveYaw);
        }
    }

    public void odoDrivetrainInput(Gamepad gamepad){
        drivetrain.processTeleopDrive(gamepad);
    }

    public void startOdoTeleop(){
        drivetrain.startTeleopDrive();
    }

    public void resetOdo(){
        drivetrain.resetOdo();
    }

    public void preloadLauncher(boolean red){
        launcher.preloadFromDistance(launcher.distanceFromLauncher(drivetrain.getPose(), red));
    }

    public void stopLauncher(){
        launcher.stopLauncher();
    }

    public void intake(){
        storage.intakeBalls();
    }

    public void shoot(){
        storage.shootBalls();
    }

    public void stopStorage() {storage.stop();}

    public boolean launcherIsReady(){
        return launcher.ready();
    }

    public void updateStorage(){
        storage.updateStorage();
    }

    public void log(){
        launcher.log();
        storage.log();
        telemetry.addData("Current Pose", drivetrain.getPose().toString());
    }
}

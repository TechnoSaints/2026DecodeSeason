package org.firstinspires.ftc.teamcode.opmode.teleop;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.Pose;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.opmode.teleop.AutoAim.AutoAimHelper;
import org.firstinspires.ftc.teamcode.opmode.teleop.AutoAim.AutoAimValues;
import org.firstinspires.ftc.teamcode.opmode.teleop.TeleopBots.TeleopBotWithOdo;
import org.firstinspires.ftc.teamcode.pedroPathing.Constants;

@Config
@TeleOp(name = "TeleopOdo", group = "OpMode")
public class TeleopOdo extends OpMode {

    private TeleopBotWithOdo bot;
    private Follower follower;

    private AutoAimHelper aim;
    private Pose startingPose = new Pose(124, 124, Math.toRadians(45));
    private boolean isRedAlliance = true;

    @Override
    public void init() {
        follower = Constants.createFollower(hardwareMap);
        follower.setStartingPose(startingPose);
        follower.update();
        bot = new TeleopBotWithOdo(this, telemetry);

        AutoAimValues values = new AutoAimValues();
        aim = new AutoAimHelper(values, isRedAlliance);
    }

    @Override
    public void start() {
        follower.startTeleopDrive();
    }

    @Override
    public void loop() {
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());

        bot.processGamepadInput(gamepad1);
        follower.update();


        Pose pose = follower.getPose();
        double turn = aim.computeTurn(pose);

        if (gamepad1.aWasPressed()) {
            bot.autoAim(turn);
        }

        aim.log(telemetry, pose);
        telemetry.update();
        bot.update();

    }
}

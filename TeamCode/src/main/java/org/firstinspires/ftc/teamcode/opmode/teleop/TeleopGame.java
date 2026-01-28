package org.firstinspires.ftc.teamcode.opmode.teleop;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.pedropathing.follower.Follower;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.common.BotSensors;
import org.firstinspires.ftc.teamcode.opmode.teleop.TeleopBots.TeleopBotBasic;
import org.firstinspires.ftc.teamcode.pedroPathing.Constants;
import org.firstinspires.ftc.teamcode.common.SortingSystem;

@Config
@TeleOp(name = "TeleopGame", group = "Linear OpMode")
public class TeleopGame extends LinearOpMode {

    private TeleopBotBasic bot;

    //private SortingSystem feeder;


    private Follower follower;


    private ElapsedTime pusherTime = new ElapsedTime();

    private double lastHeading = 0;
    private long lastTime = 0;
    private boolean ballInSensor = false;
    private boolean check = true;

    @Override
    public void runOpMode() {
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
        bot = new TeleopBotBasic(this, telemetry);
        follower = Constants.createFollower(hardwareMap);
        //feeder = new SortingSystem(this, telemetry);


        waitForStart();


        lastHeading = follower.getPose().getHeading();
        lastTime = System.nanoTime();

        bot.setBlackWheelSpeed(-0.3);

        while (opModeIsActive() && !isStopRequested()) {

            bot.processGamepadInput(gamepad1);
            follower.update();

            // --- Pose Data ---
            double x = follower.getPose().getX();
            double y = follower.getPose().getY();
            double headingRad = follower.getPose().getHeading();
            double headingDeg = Math.toDegrees(headingRad);

            // --- Translational Velocity ---
            double vx = follower.getVelocity().getXComponent();
            double vy = follower.getVelocity().getYComponent();
            double speed = follower.getVelocity().getMagnitude();

            // Direction robot is moving (not facing)
            double motionDirectionDeg = Math.toDegrees(follower.getVelocity().getTheta());

            // --- Angular Velocity Calculation ---
            long currentTime = System.nanoTime();
            double deltaT = (currentTime - lastTime) / 1e9; // seconds
            double omega = (headingRad - lastHeading) / deltaT; // rad/s

            lastHeading = headingRad;
            lastTime = currentTime;

          /*  telemetry.addLine("=== Pinpoint Odometry (Pedro) ===");
            telemetry.addData("X", "%.2f in", x);
            telemetry.addData("Y", "%.2f in", y);
            telemetry.addData("Heading", "%.2f°", headingDeg);

            telemetry.addLine("--- Velocity ---");
            telemetry.addData("VX", "%.2f in/s", vx);
            telemetry.addData("VY", "%.2f in/s", vy);
            telemetry.addData("Speed", "%.2f in/s", speed);
            telemetry.addData("Velocity Direction", "%.2f°", motionDirectionDeg);
            telemetry.addData("Angular Vel", "%.3f rad/s", omega); */
        //    feeder.log();
         //   feeder.update();


            bot.pusherUpdate();

            telemetry.addData("Check (True Means Ball Has NOT Passed)", bot.check);
            telemetry.update();
            bot.update();
        }
    }
}

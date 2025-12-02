package org.firstinspires.ftc.teamcode.opmode.auto;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.opmode.teleop.TeleopBot;

@Config
@Autonomous(name = "AUTOblue", group = "Linear OpMode")
public class basicAutoBlue extends LinearOpMode {
    private TeleopBot teleopBot;

    @Override
    public void runOpMode() {
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
        teleopBot = new TeleopBot(this, telemetry);

        waitForStart();
        if (opModeIsActive() && !isStopRequested()) {
            teleopBot.moveBot(0.4, 0, 0);
            sleep(2000);
            teleopBot.fullStop();
            sleep(200);
            teleopBot.moveBot(0,0,0.5);
            sleep(250);
            teleopBot.fullStop();

            sleep(100);
            teleopBot.launcherTurnOn();
            sleep(1200);
            teleopBot.stickL();
            sleep(500);
            teleopBot.stickReset();
            sleep(500);
            telemetry.addLine("Ball #1 Has Been Shot");
            telemetry.update();


            teleopBot.turnOnBlackWheel();
            sleep(2000);
            teleopBot.turnOffBlackWheel();
            sleep(3000);
            telemetry.addLine("Black Wheel for Shot #2 is Done");
            telemetry.update();
            sleep(1000);
            teleopBot.stickL();
            sleep(1000);
            teleopBot.stickReset();
            sleep(1000);
            telemetry.addLine("Ball #2 Has Been Shot");
            telemetry.update();


            teleopBot.turnOnBlackWheel();
            sleep(2000);
            teleopBot.turnOffBlackWheel();
            sleep(3000);
            telemetry.addLine("Black Wheel for Shot #3 is Done");
            telemetry.update();
            teleopBot.stickL();
            sleep(1000);
            teleopBot.stickReset();
            sleep(1000);
            /*
            telemetry.addLine("Ball #3 Has Been Shot");
            telemetry.update();
            sleep(1000); */
            teleopBot.update();
        }
    }
}

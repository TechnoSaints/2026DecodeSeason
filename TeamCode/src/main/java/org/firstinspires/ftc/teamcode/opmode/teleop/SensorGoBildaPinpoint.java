package org.firstinspires.ftc.teamcode.opmode.teleop;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.hardware.gobilda.GoBildaPinpointDriver;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import com.qualcomm.hardware.gobilda.GoBildaPinpointDriver;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;

import org.firstinspires.ftc.teamcode.pedroPathing.Constants;

@Config
@TeleOp(name = "TeleopPin", group = "Linear OpMode")
public class SensorGoBildaPinpoint extends LinearOpMode {

    private TeleopBot bot;

    GoBildaPinpointDriver pinpoint;

    @Override
    public void runOpMode() {
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
        bot = new TeleopBot(this, telemetry);
        pinpoint = hardwareMap.get(GoBildaPinpointDriver.class, "pinpoint");
        configurePinpoint();
        pinpoint.setPosition(new Pose2D(DistanceUnit.INCH, 0, 0, AngleUnit.DEGREES, 0));

        int xPosRedGoal = 0;
        int yPosRedGoal = 0;
        int xPosBlueGoal = 0;
        int yPosBlueGoal = 0;
        double rotation = 0;

        waitForStart();
        while (opModeIsActive() && !isStopRequested()) {
            bot.processGamepadInput(gamepad1);

            Pose2D pose2D = pinpoint.getPosition();
            pinpoint.update();
            if (gamepad1.right_trigger > .2) {
                rotation = Math.atan(pose2D.getY(DistanceUnit.INCH)/pose2D.getX(DistanceUnit.INCH));

            }

            telemetry.addData("X", pose2D.getX(DistanceUnit.INCH));
            telemetry.addData("Y", pose2D.getY(DistanceUnit.INCH));
            telemetry.addData("Heading", pose2D.getHeading(AngleUnit.DEGREES));
            telemetry.addData("Rotation", rotation);

            telemetry.update();
            bot.update();
        }
    }

    public void configurePinpoint() {
        pinpoint.setOffsets(-84, -168.0, DistanceUnit.MM);
        pinpoint.setEncoderResolution(GoBildaPinpointDriver.GoBildaOdometryPods.goBILDA_SWINGARM_POD);
        pinpoint.setEncoderDirections(GoBildaPinpointDriver.EncoderDirection.FORWARD,
                GoBildaPinpointDriver.EncoderDirection.FORWARD
                );
        pinpoint.resetPosAndIMU();
    }
}

package org.firstinspires.ftc.teamcode.opmode.auto;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotorEx;

import org.firstinspires.ftc.teamcode.common.Bot;

@Autonomous(name = "AIAuto", group = "Autonomous")
public class chatGPTAuto extends LinearOpMode {

    // Drive motors (raw because Bot does not handle drivetrain)
    private DcMotorEx lf, rf, lb, rb;

    private Bot bot;  // Use all launcher/pusher/stick functions

    // Drivetrain constants
    private final double wheelDiameterInches = 4.09449;
    private final double ticksPerRev = 537.6;
    private final double wheelCircumference = Math.PI * wheelDiameterInches;

    @Override
    public void runOpMode() {

        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());

        // Construct Bot (launcher, intake, pusher, stick)
        bot = new Bot(this, telemetry) {};

        // Drive motors
        lf = hardwareMap.get(DcMotorEx.class, "leftFrontDrive");
        lb = hardwareMap.get(DcMotorEx.class, "leftRearDrive");
        rf = hardwareMap.get(DcMotorEx.class, "rightFrontDrive");
        rb = hardwareMap.get(DcMotorEx.class, "rightRearDrive");

        lf.setDirection(DcMotorEx.Direction.REVERSE);
        lb.setDirection(DcMotorEx.Direction.REVERSE);

        resetEncoders();

        waitForStart();

        if (!opModeIsActive()) return;

        // ---------------------------------
        // 1. Move backward to shooting line
        // ---------------------------------
        moveInches(-10, 0.5);   // Example 10 in backward

        // ---------------------------------
        // 2. Start launcher (short shot)
        // ---------------------------------
        bot.setLauncherShortShot();
        sleep(1200); // allow spin-up

        // ---------------------------------
        // 3. Shoot ONE ball using stick + pusher
        // ---------------------------------
        bot.stickLaunch();
        sleep(250);

        bot.pusherStart();
        sleep(450);
        bot.stopPusher();

        bot.stickLoad();
        sleep(200);

        // ---------------------------------
        // 4. Stop launcher
        // ---------------------------------
        bot.launcherStop();

        telemetry.addLine("Auto Done");
        telemetry.update();
    }


    // ==============================
    // DRIVETRAIN HELPERS
    // ==============================

    private void resetEncoders() {
        lf.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        rf.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        lb.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        rb.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);

        lf.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        rf.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        lb.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        rb.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
    }

    private int inchesToTicks(double inches) {
        return (int) ((inches / wheelCircumference) * ticksPerRev);
    }

    private void moveInches(double inches, double power) {
        int ticks = inchesToTicks(inches);

        lf.setTargetPosition(lf.getCurrentPosition() + ticks);
        lb.setTargetPosition(lb.getCurrentPosition() + ticks);
        rf.setTargetPosition(rf.getCurrentPosition() + ticks);
        rb.setTargetPosition(rb.getCurrentPosition() + ticks);

        lf.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        lb.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        rf.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        rb.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);

        lf.setPower(power);
        lb.setPower(power);
        rf.setPower(power);
        rb.setPower(power);

        while (opModeIsActive() &&
                (lf.isBusy() || lb.isBusy() || rf.isBusy() || rb.isBusy())) {
            telemetry.addData("LF", lf.getCurrentPosition());
            telemetry.addData("RF", rf.getCurrentPosition());
            telemetry.update();
        }

        lf.setPower(0);
        lb.setPower(0);
        rf.setPower(0);
        rb.setPower(0);

        sleep(150);
    }
}

package org.firstinspires.ftc.teamcode.opmode.teleop.AutoAim;

import com.pedropathing.geometry.Pose;
import org.firstinspires.ftc.robotcore.external.Telemetry;

/**
 * Pure math helper for auto-aiming at a goal.
 * Constructed with starting pose + alliance.
 */
public class AutoAimHelper {

    private AutoAimValues values;
    private boolean isRedAlliance;

    boolean aiming;

    private double dx, dy, targetHeading, headingError, turnOutput;

    /**
     * Create the helper with the alliance and tuning values.
     * @param values the auto-aim constants and goal positions
     * @param isRedAlliance true = red, false = blue
     */
    public AutoAimHelper(AutoAimValues values, boolean isRedAlliance) {
        this.values = values;
        this.isRedAlliance = isRedAlliance;
    }

    /**
     * Computes the turn output to aim at the alliance goal.
     * @param pose current robot pose
     * @return turn output [-MAX_TURN, MAX_TURN]
     */
    public void aimingAngleCalculation(Pose pose) {
        double goalX = values.getGoalX(isRedAlliance);
        double goalY = values.getGoalY(isRedAlliance);

        dx = goalX - pose.getX();
        dy = goalY - pose.getY();

        if (Math.hypot(dx, dy) < 1e-6) {
            aiming = false;
            return;
        }

        //Try to add Math.Pi/2
        targetHeading = (Math.PI / 2 - Math.atan2(dy, dx));

        //Make sure to test
        aiming = true;
    }

    public double computeTurn(Pose pose) {
        if (!aiming) return 0;

        headingError = angleWrap(targetHeading - pose.getHeading());

        if (Math.abs(headingError) < values.AIM_DEADBAND) {
            aiming = false;
            return 0;
        }

        turnOutput = headingError * values.AIM_KP;
        return clamp(turnOutput, -values.MAX_TURN, values.MAX_TURN);
    }

    /**
     * Log telemetry for tuning
     * @param telemetry Telemetry object from OpMode
     * @param pose current robot pose
     */
    public void log(Telemetry telemetry, Pose pose) {
        double goalX = values.getGoalX(isRedAlliance);
        double goalY = values.getGoalY(isRedAlliance);

        telemetry.addLine("=== AUTO AIM DEBUG ===");
        telemetry.addData("Robot X", "%.2f", pose.getX());
        telemetry.addData("Robot Y", "%.2f", pose.getY());
        telemetry.addData("Heading (deg)", "%.2f", Math.toDegrees(pose.getHeading()));

        telemetry.addData("Goal X", "%.2f", goalX);
        telemetry.addData("Goal Y", "%.2f", goalY);

        telemetry.addData("dx", "%.2f", dx);
        telemetry.addData("dy", "%.2f", dy);

        telemetry.addData("Target Heading (deg)", "%.2f", Math.toDegrees(targetHeading));
        telemetry.addData("Heading Error (deg)", "%.2f", Math.toDegrees(headingError));
        telemetry.addData("Turn Output", "%.3f", turnOutput);
    }

    private double angleWrap(double radians) {
        while (radians > Math.PI) radians -= 2 * Math.PI;
        while (radians < -Math.PI) radians += 2 * Math.PI;
        return radians;
    }

    private double clamp(double v, double min, double max) {
        return Math.max(min, Math.min(max, v));
    }
}


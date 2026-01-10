package org.firstinspires.ftc.teamcode.opmode.teleop.AutoAim;

/**
 * Stores all auto-aim constants and field goals.
 * Can be used for red/blue alliance.
 */
public class AutoAimValues {

    // ===== TUNING =====
    public double AIM_KP = 2.5;
    public double MAX_TURN = 0.6;
    public double AIM_DEADBAND = Math.toRadians(1.5); //Degrees

    // ===== FIELD GOALS =====
    public double RED_GOAL_X = 144;
    public double RED_GOAL_Y = 144;

    public double BLUE_GOAL_X = 0;
    public double BLUE_GOAL_Y = 144;

    /**
     * Returns the X coordinate of the goal based on alliance
     * @param isRedAlliance true = red alliance, false = blue
     */
    public double getGoalX(boolean isRedAlliance) {
        return isRedAlliance ? RED_GOAL_X : BLUE_GOAL_X;
    }

    /**
     * Returns the Y coordinate of the goal based on alliance
     * @param isRedAlliance true = red alliance, false = blue
     */
    public double getGoalY(boolean isRedAlliance) {
        return isRedAlliance ? RED_GOAL_Y : BLUE_GOAL_Y;
    }
}

package org.firstinspires.ftc.teamcode.common.hardwareConfiguration.setPoints;

import org.apache.commons.math3.fitting.PolynomialCurveFitter;
import org.apache.commons.math3.fitting.WeightedObservedPoints;

import java.util.Arrays;

public class LauncherSettings {
    public static double shortShotVelocityFactor = 0.4;
    public static double shortShotPosition = 0.8;
    public static double mediumShotVelocityFactor = 0.5;
    public static double mediumShotPosition = 0.8;
    public static double longShotVelocityFactor = 0.55;
    public static double longShotPosition = 0.8;

    private static final WeightedObservedPoints velocityFactors = new WeightedObservedPoints();
    private static final WeightedObservedPoints launchPositions = new WeightedObservedPoints();
    private static final PolynomialCurveFitter velocityFactorFitter = PolynomialCurveFitter.create(2);
    private static final PolynomialCurveFitter launchPositionFitter = PolynomialCurveFitter.create(2);
    private static double[] velocityFactorCoefficients, launchPositionCoefficients;
    private static boolean initialized = false;


    private static void init() {

        // Set your empirical data here
        // For each distance to the target (in inches), you need to determine what
        //      velocity and position to use.
        // Both velocity and position should be represented as decimal numbers.
        // These numbers are the ones you provide to the launcher and servo when setting velocity
        //  and position, respectively.
        // For wheel velocities, put each the distance and velocity in velocityFactors
        // For launch position, put each the distance and position in launchPositions
        // For example, from 35 inches, you may need velocity 0.5 and position 0.25.
        // This data would be entered as:
        //      velocityFactors.add(35.0, 0.5);
        //      launchPositions.add(25.0, 0.25);

        velocityFactors.add(1.0, 0.95);
        velocityFactors.add(144.0, 0.1);

        launchPositions.add(1.0, 0.25);
        launchPositions.add(144.0, 1.0);

        velocityFactorCoefficients = velocityFactorFitter.fit(velocityFactors.toList());
        launchPositionCoefficients = launchPositionFitter.fit(launchPositions.toList());
/*
        System.out.println("Fitted coefficients (a, b, c) for velocity: " + Arrays.toString(velocityFactorCoefficients));
        System.out.println("Fitted coefficients (a, b, c) for Position: " + Arrays.toString(launchAngleCoefficients));

        // The resulting equation is y = coefficients[0] + coefficients[1]*x + coefficients[2]*x^2
        // You can now use this equation to predict y values for new x values.

        double predictedVelocityFactor = 0.0, predictedLaunchAngle = 0.0;
        for (double x = 1.0; x <= 144.0; x += 1.0) {
            predictedVelocityFactor = velocityFactorCoefficients[0] + velocityFactorCoefficients[1] * x
                    + velocityFactorCoefficients[2] * x * x;
            predictedLaunchAngle = launchAngleCoefficients[0] + launchAngleCoefficients[1] * x
                    + launchAngleCoefficients[2] * x * x;

            System.out.println("Predicted velocityFactor for x = " + x + ": " + predictedVelocityFactor);
            System.out.println("Predicted launchAngle for x = " + x + ": " + predictedLaunchAngle);
*/
    }

    private static double calculateY(double[] coefficients, double x) {
        return (coefficients[0] + coefficients[1] * x + coefficients[2] * x * x);
    }

    public static double getVelocityFactor(double distance) {
        return (calculateY(velocityFactorCoefficients, distance));
    }

    public static double getLaunchAngle(double distance) {
        return (calculateY(launchPositionCoefficients, distance));
    }
}



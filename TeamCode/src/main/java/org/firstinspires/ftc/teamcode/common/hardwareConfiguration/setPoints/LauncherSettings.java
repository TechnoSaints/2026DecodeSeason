package org.firstinspires.ftc.teamcode.common.hardwareConfiguration.setPoints;

import org.apache.commons.math3.fitting.PolynomialCurveFitter;
import org.apache.commons.math3.fitting.WeightedObservedPoints;

import java.util.Arrays;

public class LauncherSettings {
    public static double shortShotVelocityFactor = 0.40;
    public static double mediumShotVelocityFactor = 0.41;
    public static double longShotVelocityFactor = 0.51;
    public static double defenseShotVelocityFactor = 0.46;


    private static final WeightedObservedPoints velocityFactors = new WeightedObservedPoints();
    private static final WeightedObservedPoints launchAngles = new WeightedObservedPoints();
    private static final PolynomialCurveFitter velocityFactorFitter = PolynomialCurveFitter.create(2);
    private static final PolynomialCurveFitter launchAngleFitter = PolynomialCurveFitter.create(2);
    private static double[] velocityFactorCoefficients, launchAngleCoefficients;
    private static boolean initialized = false;


    private static void init() {

        // Set your empirical data here
        // For each distance to the target (in inches), you need to determine what
        //      velocity and angle to use.
        // Both velocity and angle should be represented as decimal numbers.
        // These numbers are the ones you provide to the launcher and servo when setting velocity
        //  and position, respectively.
        // For wheel velocities, put each the distance and velocity in velocityFactors
        // For launch angle, put each the distance and ange in launchAngles
        // For example, from 35 inches, you may need velocity 0.5 and angle 0.25.
        // This data would be entered as:
        //      velocityFactors.add(35.0, 0.5);
        //      launchAngles.add(25.0, 0.25);


        velocityFactors.add(1.0, 0.95);
        velocityFactors.add(144.0, 0.1);

        launchAngles.add(1.0, 0.25);
        launchAngles.add(144.0, 1.0);

        velocityFactorCoefficients = velocityFactorFitter.fit(velocityFactors.toList());
        launchAngleCoefficients = launchAngleFitter.fit(launchAngles.toList());
/*
        System.out.println("Fitted coefficients (a, b, c) for velocity: " + Arrays.toString(velocityFactorCoefficients));
        System.out.println("Fitted coefficients (a, b, c) for angle: " + Arrays.toString(launchAngleCoefficients));

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
        return (calculateY(velocityFactorCoefficients, distance));
    }
}



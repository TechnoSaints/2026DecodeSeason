package org.firstinspires.ftc.teamcode.common.hardwareConfiguration.setPoints;

import org.apache.commons.math3.fitting.PolynomialCurveFitter;
import org.apache.commons.math3.fitting.WeightedObservedPoints;

import java.util.Arrays;

public class LauncherSettings {
    private static WeightedObservedPoints velocityFactors = new WeightedObservedPoints();
    private static WeightedObservedPoints launchAngles = new WeightedObservedPoints();

    private static PolynomialCurveFitter velocityFactorFitter = PolynomialCurveFitter.create(2);
    private static PolynomialCurveFitter launchAngleFitter = PolynomialCurveFitter.create(2);
    private static double[] velocityFactorCoefficients, launchAngleCoefficients;
    private static boolean initialized = false;

    private static void init() {
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



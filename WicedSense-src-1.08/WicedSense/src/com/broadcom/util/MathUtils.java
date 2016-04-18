package com.broadcom.util;

/**
 * Helper math class
 *
 *
 */
public class MathUtils {

    public static double degreesToRadians(double value) {
        return 0.0174532925 * value;
    }

    public static double radiansToDegrees(double value) {
        return 57.295779578 * value;
    }

    public static double getMagnitude(double x, double y) {
        return Math.sqrt(x * x + y * y);
    }

    public static double getDegrees(double y, double x) {

        double mag = getMagnitude(x, y);
        double nY = y / mag;
        double nX = x / mag;
        return radiansToDegrees(Math.atan2(nY, nX));
    }
}

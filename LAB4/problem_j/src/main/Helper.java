package main;

import java.util.List;

import geometry.Point;
import geometry.shapes.Circle;

public abstract class Helper {
    public static double tolerance = 1e-9;
    public static Point origin = new Point(0, 0);

    /**
     * Prints and error message on the output and exits the program. <br>
     * <hr>
     * 
     * @param ERR_MSG : a {@link String} representing the error message.
     */
    public static void ivExit(String ERR_MSG) {
        throw new IllegalArgumentException(ERR_MSG);
        // System.out.println(ERR_MSG);
        // System.exit(0);
    }

    /**
     * Creates a generic point.
     * <hr>
     * 
     * @implNote Used for testings only.
     * @param s : a {@code double} representing both the point's x and y axis
     *          values.
     * @return a {@link Point} with values (x: s, y: s).
     * @see Point#Point(double, double)
     */
    public static Point genericPoint(double s) {
        return new Point(s, s);
    }

    /**
     * Creates a generic circle.
     * <hr>
     * 
     * @implNote Used for testings only.
     * @param s : a {@code double} representing both the x and y values for the
     *          center of the {@link Circle} as well as it's radius. If
     *          {@code s < 1}, the value for the radius will be 1 instead.
     * @return a {@link Circle} with center at (x: s, y: s) and radius = s.
     * @see Circle#Circle(Point, double)
     */
    public static Circle genericCircle(double s) {
        return new Circle(new Point(s, s), s < 1e-9 ? 1 : s);
    }

    /**
     * Checks if a number is zero within a default tolerance.
     * <hr>
     * 
     * @param n : a {@code double} representing the number to be verified.
     * @return
     *         <ul>
     *         <li>{@code true} if the number's absolute value is less than the
     *         tolerance.
     *         <li>{@code false} if it's greater than the tolerance.
     *         </ul>
     * 
     * @see Math#abs(double)
     */
    public static boolean isZero(double n) {
        return Math.abs(n) < tolerance;
    }

    /**
     * Verifies whether two doubles are equal to each other, within a default
     * tolerance.
     * <hr>
     * 
     * @param a : a {@code double}.
     * @param b : a {@code double}.
     * @return
     *         <ul>
     *         <li>{@code true} if both numbers are equal to each other.
     *         <li>{@code false} if they are different.
     *         </ul>
     * @see #isZero(double)
     */
    public static boolean tolerantEquals(double a, double b) {
        return isZero(a - b);
    }

    /**
     * Converts a list of points into a string.
     * <hr>
     * 
     * @param points : a {@link List} of {@link Point}, which will be converted to
     *               String.
     * @return a {@link String} with the converted list in the format "(x, y) (x',
     *         y') (x", y") ...".
     */
    public static String pointsToString(List<Point> points) {
        if (points == null)
            return null;

        String str = "";
        for (Point point : points) {
            if (point == points.getLast())
                str += point;
            else
                str += point + " ";
        }

        return str;
    }
}

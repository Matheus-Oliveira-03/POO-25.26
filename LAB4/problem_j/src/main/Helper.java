package main;

import java.util.List;

import geometry.Point;
import geometry.shapes.Circle;

public abstract class Helper {
    public static double tolerance = 1e-9;
    public static Point origin = new Point(0, 0);

    public static void ivExit(String ERR_MSG) {
        throw new IllegalArgumentException(ERR_MSG);
        // System.out.println(ERR_MSG);
        // System.exit(0);
    }

    public static Point genericPoint(double s) {
        return new Point(s, s);
    }

    public static Circle genericCircle(double s) {
        return new Circle(new Point(s, s), s < 1e-9 ? 1 : s);
    }

    public static boolean isZero(double n) {
        return Math.abs(n) < tolerance;
    }

    public static boolean tolerantEquals(double a, double b) {
        return isZero(a - b);
    }

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

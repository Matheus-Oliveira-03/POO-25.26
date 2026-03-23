import java.util.List;

public class Helper {
    public static double zero = 1e-9;
    public static Point origin = new Point(0, 0);

    public static void ivExit(String ERR_MSG) {
        System.out.println(ERR_MSG);
        System.exit(0);
    }

    public static boolean isZero(double n) {
        return Math.abs(n) < zero;
    }

    public static boolean equals(double a, double b) {
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

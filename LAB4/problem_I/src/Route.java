import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * @author Matheus Martins Oliveira | a85794
 * @version 1.0 | 12/03/2026
 * @inv
 */
public class Route {
    private final String ERR_MSG = "Route.iv";
    private List<Point> points;

    public Route(ArrayList<Point> points) {
        this.points = points;

        checkInvariant();
    }

    public List<Point> points() {
        return this.points;
    }

    public double length() {
        double length = 0;
        int i = 0;
        while (i < points.size() - 1) {
            length += points.get(i).distanceTo(points.get(++i));
        }

        return length;
    }

    public List<Point> intersect(SegmentoReta sr) {
        BiFunction<Point, Point, Double> det = (a, b) -> (a.x() * b.y()) - (a.y() * b.x());
        List<Point> intersections = new ArrayList<Point>();
        int i = 0;
        while (i < points.size() - 1) {
            Point p1 = sr.a();
            Vector v1 = new Vector(sr);

            Point p2 = points.get(i);
            Vector v2 = new Vector(points.get(i), points.get(++i));

            double d = det.apply(v1, v2);
            if (Math.abs(d) < 1e-9)
                return null;
            Function<Point, Double> scalar = (p) -> det.apply(p2.calculator(p1, (m, n) -> m - n), p) / d;

            double t = scalar.apply(v2);
            double u = scalar.apply(v1);
            if ((0 <= t && t <= 1) && (0 <= u && u <= 1))
                intersections.add(p1.calculator(v1, (m, n) -> m + t * n));
        }

        return intersections;
    }

    public String toString() {
        return toString(this.points);
    }

    public static String toString(Route r) {
        return toString(r.points());
    }

    public static String toString(List<Point> points) {
        if (points == null)
            return null;

        String str = "";
        for (Point point : points) {
            str += point;
        }

        return str;
    }

    private void checkInvariant() {
        if (points.size() < 2)
            invariantExit();

        int i = 0;
        while (i < points.size() - 1) {
            if (points.get(i).equals(points.get(++i)))
                invariantExit();
        }
    }

    private void invariantExit() {
        System.out.println(ERR_MSG);
        System.exit(0);
    }
}

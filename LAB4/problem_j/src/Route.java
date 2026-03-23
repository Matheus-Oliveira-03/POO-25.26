import java.util.ArrayList;
import java.util.List;

/**
 * @author Matheus Martins Oliveira | a85794
 * @version 1.0 | 12/03/2026
 * @inv
 */
public class Route {
    private final String ERR_MSG = "Route.iv";
    private List<Point> points;

    public Route() {
        this.points = new ArrayList<Point>();
    }

    public Route(ArrayList<Point> points) {
        this.points = points;
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
        List<Point> intersections = new ArrayList<Point>();
        int i = 0;
        while (i < points.size() - 1) {
            try {
                Point p = new SegmentoReta(points.get(i), points.get(++i)).intersect(sr.a(), sr.b());
                if (p != null)
                    intersections.add(p);

            } catch (Exception e) {
                System.out.println(e.getMessage());
                System.exit(0);
            }
        }

        return intersections.isEmpty() ? null : intersections;
    }

    public String toString() {
        return toString(this.points);
    }

    public static String toString(Route r) {
        return toString(r.points);
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

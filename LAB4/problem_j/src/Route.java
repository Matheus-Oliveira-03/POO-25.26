import java.util.ArrayList;
import java.util.List;

/**
 * @author Matheus Martins Oliveira | a85794
 * @version 1.0 | 12/03/2026
 * @inv
 */
public class Route {
    private List<Point> points;
    private List<SegmentoReta> segments;

    public Route(ArrayList<Point> points) {
        this.points = points;
        this.segments = segments();
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

    private List<SegmentoReta> segments() {
        if (this.points.size() < 2)
            Helper.ivExit("Rota:iv");
        List<SegmentoReta> segments = new ArrayList<SegmentoReta>();
        for (int i = 0; i < this.points.size() - 1; i++) {
            try {
                segments.add(new SegmentoReta(this.points.get(i), this.points.get(i + 1)));
            } catch (Exception e) {
                Helper.ivExit(e.getMessage());
            }
        }

        return segments;
    }

    public List<Point> intersect(SegmentoReta sr) {
        List<Point> intersections = new ArrayList<Point>();

        for (SegmentoReta segment : segments) {
            Point intersection = segment.intersect(sr);
            if (intersection != null && !intersections.contains(intersection))
                intersections.add(intersection);
        }

        return intersections.isEmpty() ? null : intersections;
    }

    public List<Point> intersect(Shape shape) {
        List<Point> intersections = new ArrayList<Point>();
        for (SegmentoReta segment : segments) {
            List<Point> intersectionsWithP = shape.intersect(segment);
            if (intersectionsWithP == null)
                continue;

            intersectionsWithP.sort(Point.compareToRef(segment.a()));
            for (Point intersection : intersectionsWithP) {
                if (!intersections.contains(intersection))
                    intersections.add(intersection);
            }
        }

        return intersections.isEmpty() ? null : intersections;
    }

    public String toString() {
        return Helper.pointsToString(this.points);
    }
}

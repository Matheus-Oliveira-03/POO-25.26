package geometry;

import java.util.ArrayList;
import java.util.List;

import geometry.shapes.Shape;
import main.Helper;

/**
 * @author Matheus Martins Oliveira | a85794
 * @version 1.0 | 12/03/2026
 * @inv
 */
public class Route {
    private final String ERR_MSG = "Rota:iv";
    private List<Point> points;
    private double length = 0;

    public Route(List<Point> points) {
        if (points.size() < 2)
            Helper.ivExit(ERR_MSG);

        this.points = points;
        this.length = 0;

        for (int i = 0; i < points.size() - 1; i++) {
            length += points.get(i).distanceTo(points.get(i + 1));
        }
    }

    public List<Point> points() {
        return this.points;
    }

    public double length() {
        return length;
    }

    public List<SegmentoReta> segments() {
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

        for (SegmentoReta segment : segments()) {
            Point intersection = segment.intersect(sr);
            if (intersection != null && !intersections.contains(intersection))
                intersections.add(intersection);
        }

        return intersections.isEmpty() ? null : intersections;
    }

    public List<Point> intersect(Shape shape) {
        List<Point> intersections = new ArrayList<Point>();
        for (SegmentoReta segment : segments()) {
            List<Point> intersectionsWithP = shape.intersect(segment);
            if (intersectionsWithP == null)
                continue;

            intersectionsWithP.sort(Point.compareToRef(segment.a()));
            for (Point intersection : intersectionsWithP) {
                intersections.add(intersection);
            }
        }

        return intersections.isEmpty() ? null : intersections;
    }

    @Override
    public String toString() {
        return Helper.pointsToString(this.points);
    }
}

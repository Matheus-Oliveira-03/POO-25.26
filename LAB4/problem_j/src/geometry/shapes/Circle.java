package geometry.shapes;

import java.util.ArrayList;
import java.util.List;
import geometry.*;
import main.Helper;

public class Circle implements Shape {
    private final String ERR_MSG = "Circulo:iv";
    private Point center;
    private double radius;

    public Circle(Point center, double radius) {
        this.center = center;
        this.radius = radius;

        if (radius < 1e-9)
            Helper.ivExit(ERR_MSG);
    }

    public Point center() {
        return this.center;
    }

    public double radius() {
        return this.radius;
    }

    public List<Point> intersect(SegmentoReta sr) {
        List<Point> intersections = new ArrayList<Point>();
        Point dif = sr.a().calculator(center, (m, n) -> m - n);
        Vector v = sr.directionVector();

        double A = Math.pow(v.x(), 2) + Math.pow(v.y(), 2);
        double B = 2 * (v.x() * dif.x() + v.y() * dif.y());
        double C = Math.pow(dif.x(), 2) + Math.pow(dif.y(), 2) - Math.pow(radius, 2);

        double delta = (B * B) - (4 * A * C);
        if (Math.abs(delta) < 1e-9) {
            // delta == 0, one intersections
            double t = (-B) / (2 * A);

            if (0 <= t && t <= 1)
                intersections.add(sr.getPoint(t));
        } else if (delta > 1e-9) {
            // delta > 0, two intersecions
            double deltaSQRT = Math.sqrt(delta);

            double t1 = (-B + deltaSQRT) / (2 * A);
            double t2 = (-B - deltaSQRT) / (2 * A);

            if (0 <= t1 && t1 <= 1)
                intersections.add(sr.getPoint(t1));
            if (0 <= t2 && t2 <= 1)
                intersections.add(sr.getPoint(t2));
        } else {
            // delta < 0, no intersections
            return null;
        }

        return intersections.isEmpty() ? null : intersections;
    }
}

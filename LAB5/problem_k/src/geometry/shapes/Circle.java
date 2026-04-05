package geometry.shapes;

import java.util.ArrayList;
import java.util.List;
import geometry.*;
import main.Helper;

/**
 * Represents a circle whithin a cartesian space
 * 
 * @author Matheus Martins Oliveira | a85794
 * @version 2.0 | 02/04/2026
 * @inv radius > 0
 */
public class Circle implements Shape {
    private Point center;
    private double radius;

    /**
     * Constructor from a point and a radius;
     * 
     * @param center a {@link Point} representing the center of the Circle
     * @param radius a {@code double} representing the radius of the Circle. Must be
     *               greater than 0.
     * @see Helper#ivExit(String)
     */
    public Circle(Point center, double radius) {
        this.center = center;
        this.radius = radius;

        if (radius < 1e-9)
            Helper.ivExit(ERR_MSG());
    }

    public Point center() {
        return this.center;
    }

    public double radius() {
        return this.radius;
    }

    /**
     * Calculates the intersection of the Circle with a given line segment
     * 
     * <p>
     * The method utilizes the line-segment's parametric equation
     * {@code P(t) = A + t(B - A)}
     * in the circle equation
     * {@code r^2 = (x - center.x())^2 + (y - center.y())^2}
     * to find a quadratic formula, which can be solved for {@code t}.
     * </p>
     * 
     * <hr>
     * 
     * <p>
     * Rules for intersection between line-segment's support rect and the circle
     * based on {@code delta = b^2 - 4*a*c}
     * </p>
     * <ul>
     * <li>{@code delta < 0}, means there is no points of intersection between the
     * rect and the circle;
     * <li>{@code delta = 0}, means there is 1 point of intersection between the
     * rect and the circle;
     * <li>{@code delta > 0}, means there is 2 points of intersection between the
     * rect and the circle;
     * </ul>
     * <p>
     * Based on this information, the method calculates {@code t} through the
     * quadratic formula and returns the point/s of intersection based on whether
     * {@code 0 <= t <= 1}
     * </p>
     * 
     * <hr>
     * 
     * @param sr a {@link SegmentoReta} with which the intersection will be
     *           verified. Must be {@code != null}.
     * @return
     *         <ul>
     *         <li>A {@code List<Point>} containing one or two {@link Point};
     *         <li>{@code null} if the Circle does not intersect the line-segment;
     *         </ul>
     */
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

    @Override
    public String ERR_MSG() {
        return "Circulo:iv";
    }
}

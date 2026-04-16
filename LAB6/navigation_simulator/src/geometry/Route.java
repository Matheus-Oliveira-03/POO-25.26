package geometry;

import java.util.ArrayList;
import java.util.List;

import geometry.shapes.Shape;
import main.Helper;

/**
 * Represents a route, determined by a list of cartesian points
 * <hr>
 * 
 * @author Matheus Martins Oliveira | a85794
 * @version 1.0 | 12/03/2026
 * @inv
 *      <ul>
 *      <li>The route must contain at least two points
 *      <li>Two subsequent points must be different from each other
 *      </ul>
 */
public class Route implements Shape {
    protected List<Point> points;
    protected List<SegmentoReta> segments = null;
    protected double length = 0;

    /**
     * Constructor from a list of points
     * <hr>
     * 
     * @param points a {@link List} of {@link Point} representing the points of the
     *               route
     */
    public Route(List<Point> points) {
        if (points.size() < 2)
            Helper.ivExit(ERR_MSG());

        this.points = points;
        this.length = genLength();
    }

    /**
     * Constructor from a array of strings
     * <hr>
     * 
     * @param data a array of {@link String} where every pair of subsequent strings
     *             contains the coordiantes of a point of the route
     * @pre-conditions The array must contain an even number of elements and each
     *                 element inside it must be a parsable double
     */
    public Route(String[] data) {
        if (data.length % 2 != 0)
            Helper.ivExit(ERR_MSG());

        this.points = Helper.pointReader(data);
        this.length = genLength();
    }

    /**
     * Calculates the total length of the route by calculating the distances from
     * each point to the next and adding those distances together
     * <hr>
     * 
     * @return a {@code double} representing this route's total length
     */
    private double genLength() {
        double length = 0;
        for (int i = 0; i < this.points.size() - 1; i++) {
            length += points.get(i).distanceTo(points.get(i + 1));
        }

        return length;
    }

    public List<Point> points() {
        return this.points;
    }

    public List<SegmentoReta> segments() {
        if (this.segments == null)
            segments = genSegments();

        return this.segments;
    }

    public double length() {
        return length;
    }

    /**
     * Generates a list of line-segments containing each segment formed by a point
     * and its' next one within the points list
     * <hr>
     * 
     * @return a {@link List} of {@link SegmentoReta} representing this route's
     *         segments
     * @see SegmentoReta#checkInvariants()
     * @see Helper#ivExit(String)
     */
    protected List<SegmentoReta> genSegments() {
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

    /**
     * @return
     *         <ul>
     *         <li>a {@code List<Point>}, containing the {@link Point}(s) of
     *         intersection found,
     *         <li>{@code null}, if the shape does not intersect the
     *         {@link SegmentoReta}
     *         </ul>
     * 
     * @see SegmentoReta#intersect(SegmentoReta)
     */
    @Override
    public List<Point> intersect(SegmentoReta sr) {
        List<Point> intersections = new ArrayList<Point>();

        for (SegmentoReta segment : genSegments()) {
            Point intersection = segment.intersect(sr);
            if (intersection != null && !intersections.contains(intersection))
                intersections.add(intersection);
        }

        return intersections.isEmpty() ? null : intersections;
    }

    /**
     * Calculates a list of points where this route intersects the shape
     * <hr>
     * 
     * @param shape a {@link Shape} with which the intersections will be calculed
     * @return a {@link List} of {@link Point} representing the points of
     *         intersection between this and the shape
     * 
     * @see Shape#intersect(SegmentoReta)
     */
    public List<Point> intersect(Shape shape) {
        List<Point> intersections = new ArrayList<Point>();
        for (SegmentoReta segment : genSegments()) {
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

    /**
     * @return a {@link String} representing this route's list of points in the
     *         format "(x,y) (x,y)' (x,y)'' ..."
     */
    @Override
    public String toString() {
        return Helper.listToString(this.points);
    }

    /**
     * The method returns the error message, represented by a {@link String},
     * associated with the
     * shape's invariants violations. <br>
     * Each shape has its own error message.
     * 
     * @return {@code "Rota:iv"}
     */
    @Override
    public String ERR_MSG() {
        return "Rota:iv";
    }
}

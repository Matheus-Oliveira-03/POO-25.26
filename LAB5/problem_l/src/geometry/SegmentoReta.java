package geometry;

import java.util.Objects;
import main.Helper;

/**
 * Represents a line-segment defined by two extrems
 * 
 * @author Matheus Martins Oliveira | a85794
 * @version 3.0 | 06/03/2026
 * @inv {@code this.a} must be different from {@code this.b}
 * @see https://youtu.be/5FkOO1Wwb8w
 */
public class SegmentoReta {
    private final String ERR_MSG = "SegmentoReta:iv";
    private Point a;
    private Point b;

    // #region Constructors

    /**
     * Constructor from a point and a vector
     * <hr>
     * 
     * @param p an {@code Point} representing the orgin point of the line-segment
     * @param v an {@code Vetor} representing the position vector of the
     *          line-segment
     */
    public SegmentoReta(Point p, Vector v) {
        this(p, v.transform(p));
    }

    /**
     * Constructor from two points
     * <hr>
     * 
     * @param a an {@code Point} representing the first of the line-segment's
     *          extrems
     * @param b an {@code Point} representing the second of the line-segment's
     *          extrems
     */
    public SegmentoReta(Point a, Point b) {
        this.a = a;
        this.b = b;

        checkInvariants();
    }

    // #endregions

    // #region Getters

    public Point a() {
        return this.a;
    }

    public Point b() {
        return this.b;
    }

    public double length() {
        return a.distanceTo(b);
    }

    /**
     * @return a {@link String} representing this line-segment in the format
     *         "sr(this.a.toString();this.b.toString())"
     */
    @Override
    public String toString() {
        return "sr(" + this.a.toString() + ";" + this.b.toString() + ")";
    }

    /**
     * Calculates a direction vector from two points
     * <hr>
     * 
     * @return a {@link Vector} representing the vector from A to B
     * 
     * @see Vector
     */
    public static Vector directionVector(Point A, Point B) {
        Vector d = null;
        try {
            d = new Vector(B.x() - A.x(), B.y() - A.y());
        } catch (Exception e) {
            Helper.ivExit(e.getMessage());
        }
        return d;
    }

    /**
     * Calculates this line-segment's direction vector, from this.a to this.b
     * <hr>
     * 
     * @return a {@link Vector} representing the direction vector
     * 
     * @see #directionVector(Point, Point)
     */
    public Vector directionVector() {
        return SegmentoReta.directionVector(a, b);
    }

    /**
     * Calculates the coordinates of a point within this line-segment, based on its'
     * parametric equation: <br>
     * Point p = A + t * (B-A), where 0 <= t and t <= 1
     * <hr>
     * 
     * @param t a {@code double} representing the desired point's position in a
     *          ration from 0 to 1, where 0 is the same position as this.a and 1 is
     *          the same position as this.b
     * @return a {@link Point} representing the desired point's coordinates
     * 
     * @see #directionVector()
     * @see Vector#transform(Point, double)
     */
    public Point getPoint(double t) {
        return directionVector().transform(a, t);
    }

    // #endregion

    // #region Calculations

    /**
     * Given two line-segments, this and another one, each defined by two points,
     * calculates two 0 to 1 ratios from each segment's first extrem to the point of
     * intersection with the other segment, if there is one, using the cross product
     * between each segment's direction vector
     * <hr>
     * 
     * @param C a {@link Point} representing the second line-segment's first extrem
     * @param D a {@link Point} representing the second line-segment's second extrem
     * @return
     *         <ul>
     *         <li>a {@link Point} containg this segment's ration in its' x value
     *         and the other segment's ratio in its' y value
     *         <li>{@code null} if the segments are colinear or paralel to each
     *         other, meaning there is not a single point of intersection between
     *         them
     *         </ul>
     * 
     * @see #directionVector(Point, Point)
     * @see Vector#crossProduct(Vector)
     * @see Math#abs(double)
     */
    private Point scalars(Point C, Point D) {
        Vector AC = directionVector(this.a, C);
        Vector AB = directionVector(this.a, this.b);
        Vector CD = directionVector(C, D);

        double ABxCD = Vector.crossProduct(AB, CD);
        if (Math.abs(ABxCD) < 1e-9)
            return null;

        double t = (Vector.crossProduct(AC, CD) / ABxCD);
        double u = (Vector.crossProduct(AC, AB) / ABxCD);

        return new Point(t, u);
    }

    /**
     * Calculates the point of intersection betwween this segment and a vector
     * <hr>
     * 
     * @param p a {@link Point} representing the vector's origin point
     * @param v a {@link Vector} representing the vector to be calculated with
     * @return a {@link Point} representing the point of intersection between this
     *         segment and the vector
     */
    public Point intersect(Point p, Vector v) {
        Point i = scalars(p, v.transform(p));
        if (i == null)
            return null;

        double t = i.x();
        double u = i.y();

        if (0 <= t && t <= 1 && 0 <= u)
            return v.transform(p, u);

        return null;
    }

    /**
     * Calculates the point of intersection betwween this segment and another one,
     * defined by two points
     * <hr>
     * 
     * @param C a {@link Point} representing the second segment's first extrem
     * @param D a {@link Point} representing the second segment's second extrem
     * @return a {@link Point} representing the point of intersection between the
     *         two segments
     */
    public Point intersect(Point C, Point D) {
        Point i = scalars(C, D);
        if (i == null)
            return null;

        double t = i.x();
        double u = i.y();

        if ((0 <= t && t <= 1) && (0 <= u && u <= 1))
            return getPoint(t);

        return null;
    }

    /**
     * Calculates the point of intersection betwween this segment and another one
     * <hr>
     * 
     * @param sr a {@link SegmentoReta} representing the line-segment to be
     *           calculated with
     * @return a {@link Point} representing the point of intersection between the
     *         two segments
     */
    public Point intersect(SegmentoReta sr) {
        return intersect(sr.a(), sr.b());
    }

    // #endregion

    // #region Checks

    /**
     * Determines whether a object is equal to this.
     * <hr>
     * 
     * @param o an {@link Object} representing the object which will be compared to
     *          this
     * @return
     *         <ul>
     *         <li>{@code true} if the object is a {@link SegmentoReta} and (this.a
     *         == o.a, this.b
     *         == o.b)
     *         <li>{@code false} otherwise
     *         </ul>
     */
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof SegmentoReta))
            return false;

        SegmentoReta sr = (SegmentoReta) o;
        return this.a().equals(sr.a()) && this.b().equals(sr.b());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.a, this.b);
    }

    /**
     * Checks the class' invariants validity
     * <hr>
     * 
     * Exits the program with the mensage {@code "SegmentoReta:iv"} if one of them
     * is
     * violated
     */
    private void checkInvariants() {
        if (!a.equals(b))
            return;

        Helper.ivExit(ERR_MSG);
    }

    // #endregion
}

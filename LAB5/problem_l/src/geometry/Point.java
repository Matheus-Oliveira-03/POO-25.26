package geometry;

import java.util.Comparator;
import java.util.Objects;
import java.util.function.BinaryOperator;

import main.Helper;

/**
 * Represents a point with (x,y) coordinates in a cartesian space
 * 
 * @author Matheus Martins Oliveira | a85794
 * @version 3.0 | 27/02/2026
 */
public class Point implements Comparable<Point> {
    public static Point ORIGIN = new Point(0, 0);
    private double x;
    private double y;

    /**
     * Constructor from two doubles
     * 
     * @param x (double) x axis value
     * @param y (double) y axis value
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double x() {
        return this.x;
    }

    public double y() {
        return this.y;
    }

    /**
     * Calculates a operation between two points, where one of those points is
     * obtained by multiplying this point by an scalar: <br>
     * (this.x * scalar [operation] point.x, this.y * scalar [operation] point.y)
     * <hr>
     * 
     * @param scalar   a {@code double} representing a scalar
     * @param p        a {@link Point} representing the other point for the
     *                 operation
     * @param operator a {@link BinaryOperator} representing the operation between
     *                 both points
     * @return a {@link Point} representing the result of the operation
     */
    public Point calculator(double scalar, Point p, BinaryOperator<Double> operator) {
        /*
         * Personal Note:
         * 
         * This method is allmost never used with proper points, it is more often used
         * with vectors instead, so I could move it to the Vecotr class once and for
         * all.
         * It Does work the way it is, but in the next implementation I should make this
         * one the "main" method(with the real logic) and the other two would be the
         * overcharged methods. In other words, merge all the calculator methods into
         * one calculator(double, Point, BinaryOperator<Double>)
         * or something like that.
         * Probably there should be a static implementation of this method, it feels
         * more... elegant.
         * 
         */
        Point scaled = this.calculator(scalar, (m, n) -> m * n);
        return scaled.calculator(p, operator);
    }

    /**
     * Calculates a operation between a scalar and this point: <br>
     * (this.x [operation] scalar, this.y [operation] scalar)
     * <hr>
     * 
     * @param scalar   an {@code double} representing a scalar
     * @param operator an {@link BinaryOperator} representing the operation
     *                 between the scalar and this
     * 
     * @return a {@link Point} resulting of the scalar being aplied by the operation
     *         to each of this point values
     */
    public Point calculator(double scalar, BinaryOperator<Double> operator) {
        return calculator(new Point(scalar, scalar), operator);
    }

    /**
     * Calculates a operation between a given point p and this point: <br>
     * (this.x [operation] p.x, this.y [operation] p.y)
     * <hr>
     * 
     * @param p        an {@link Point} representing the second point for the
     *                 operation
     * @param operator an {@link BinaryOperator} representing the operation
     *                 between the two points
     * @return a {@link Point} representing the result of the operation between the
     *         two points.
     */
    public Point calculator(Point p, BinaryOperator<Double> operator) {
        if (operator == null)
            return null;

        double xx = operator.apply(this.x(), p.x());
        double yy = operator.apply(this.y(), p.y());
        return new Point(xx, yy);
    }

    /**
     * Calculates the distance from this point to another.
     * <hr>
     * 
     * @param p an {@link Point} representing the point to which the distance will
     *          be measured.
     * @return a {@code double} representing the distance between this and p.
     */
    public double distanceTo(Point p) {
        return Math.sqrt(Math.pow(this.x - p.x(), 2) + Math.pow(this.y - p.y(), 2));
    }

    /**
     * Calculates the distance from this to the ORIGIN.
     * <hr>
     * 
     * @return a {@code double} representing the distance between this and (0, 0).
     */
    public double norm() {
        return distanceTo(ORIGIN);
    }

    /**
     * Determines whether a object is equal to this.
     * <hr>
     * 
     * @param o an {@link Object} representing the object which will be compared to
     *          this
     * @return
     *         <ul>
     *         <li>{@code true} if the object is a point and (this.x == o.x, this.y
     *         == o.y)
     *         <li>{@code false} otherwise
     *         </ul>
     * 
     */
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Point))
            return false;

        Point p = (Point) o;
        return Helper.tolerantEquals(this.x(), p.x()) && Helper.tolerantEquals(this.y(), p.y());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.x(), this.y());
    }

    /**
     * Determines which point, whether this or a given point p, is closer to the
     * origin (0, 0)
     * <hr>
     * 
     * @param p an {@link Point} representing the point which will be compared to
     *          this one regarding the distance to the origin (0, 0)
     * @return {@code -1} If this is closer to the origin<br>
     *         {@code 0} If both are at the same distance to the origin<br>
     *         {@code 1} If the p is closer to the origin
     * @see #norm()
     */
    @Override
    public int compareTo(Point p) {
        double disT = this.norm();
        double disP = p.norm();
        return Double.compare(disT, disP);
    }

    /**
     * Creates a comparator which determines which of two points is closer to a
     * reference point
     * <hr>
     * 
     * @param ref a {@link Point} representing the reference point
     * @return a {@link Comparator} which compares which of two points is closer to
     *         ref
     */
    public static Comparator<Point> compareToRef(Point ref) {
        return (p1, p2) -> Double.compare(p1.distanceTo(ref), p2.distanceTo(ref));
    }

    /**
     * @return a {@link String} representing this point in the format "(X.XX, Y.YY)"
     */
    @Override
    public String toString() {
        return String.format("(%.2f,%.2f)", this.x(), this.y());
    }
}

package geometry;

import java.util.Comparator;
import java.util.Objects;
import java.util.function.BinaryOperator;

import main.Helper;

/**
 * Represents a point with (x,y) coordinates
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

    /**
     * @return a {@code double} representing this point's x axis value
     */
    public double x() {
        return this.x;
    }

    /**
     * @return a {@code double} representing this point's y axis value
     */
    public double y() {
        return this.y;
    }

    public Point calculator(double scalar, Point p, BinaryOperator<Double> operator) {
        Point scaled = this.calculator(scalar, (m, n) -> m * n);
        return scaled.calculator(p, operator);
    }

    /**
     * @param scalar   an {@code double} representing a scalar
     * @param operator an {@code BinaryOperator<Double>} representing the operation
     *                 between the scalar and this
     * @return a {@code Point} resulting of the scalar being aplied by the operation
     *         to each of this point values
     */
    public Point calculator(double scalar, BinaryOperator<Double> operator) {
        return calculator(new Point(scalar, scalar), operator);
    }

    /**
     * @param p        an {@code Point} representing the second point for the
     *                 operation
     * @param operator an {@code BinaryOperator<Double>} representing the operation
     *                 between the two points
     * @return a {@code Point} in which each axis contain the result of the first
     *         point({@code this}) being aplied by the operation to the second point
     *         in the same
     *         axis:{@code (this.x() _operator_ p.x(), this.() _operator_ p.y())}
     */
    public Point calculator(Point p, BinaryOperator<Double> operator) {
        if (operator == null)
            return null;

        double xx = operator.apply(this.x(), p.x());
        double yy = operator.apply(this.y(), p.y());
        return new Point(xx, yy);
    }

    /**
     * @param p an {@code Point} representing the point which will be measured the
     *          distance to
     * @return a {@code double} representing the distance between this and the point
     *         p
     */
    public double distanceTo(Point p) {
        return Math.sqrt(Math.pow(this.x - p.x(), 2) + Math.pow(this.y - p.y(), 2));
    }

    /**
     * @return a {@code double} representing the distance between this and the
     *         origin (0, 0)
     */
    public double norm() {
        return distanceTo(ORIGIN);
    }

    /**
     * @param p an {@code Point} representing the point which will be compared to
     *          this
     * @return {@code true} if the points are equal to each other (this.x() ==
     *         p-x(), this.y() == p.y())
     */
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Point))
            return false;

        Point p = (Point) o;
        return Helper.equals(this.x(), p.x()) && Helper.equals(this.y(), p.y());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.x(), this.y());
    }

    /**
     * @param p an {@code Point} representing the point which will be compared to
     *          this one regarding the distance to the origin (0, 0)
     * @return {@code -1} If this is closer to the origin<br>
     *         {@code 0} If both are at the same distance to the origin<br>
     *         {@code 1} If the given point p is closer to the origin
     */
    @Override
    public int compareTo(Point p) {
        double disT = this.norm();
        double disP = p.norm();
        return Double.compare(disT, disP);
    }

    public static Comparator<Point> compareToRef(Point ref) {
        return (p1, p2) -> Double.compare(p1.distanceTo(ref), p2.distanceTo(ref));
    }

    public String toString() {
        return String.format("(%.2f,%.2f)", this.x(), this.y());
    }
}

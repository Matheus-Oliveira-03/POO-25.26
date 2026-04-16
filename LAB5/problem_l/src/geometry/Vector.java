package geometry;

import java.util.Objects;
import java.util.function.BiFunction;

import main.Helper;

/**
 * Represents a direction vector <br>
 * This class extends from the Ponto class for the sake of simplicity
 * 
 * @author Matheus Martins Oliveira | a85794
 * @version 3.0 | 07/03/2026
 * @inv Vector's norm can't be equal 0
 */
public class Vector extends Point {
    private final String ERR_MSG = "Vetor:iv";

    /**
     * Constructor from two doubles;
     * <hr>
     * 
     * @param x an {@code double} representing the coordinate in x axis
     * @param y an {@code double} representing the coordinate in y axis
     */
    public Vector(double x, double y) {
        super(x, y);
        checkInvariants();
    }

    /**
     * Constructor from a point
     * <hr>
     * 
     * @param p an {@link Point} representing the point obtained when the vector is
     *          aplied to the origin
     */
    public Vector(Point p) {
        super(p.x(), p.y());
        checkInvariants();
    }

    /**
     * Constructor from two points
     * <hr>
     * 
     * @param a an {@link Point} representing the first extrem
     * @param b an {@link Point} representing the second extrem
     */
    public Vector(Point a, Point b) {
        super(b.x() - a.x(), b.y() - a.y());
        checkInvariants();
    }

    /**
     * Constructor from a line-segment
     * <hr>
     * 
     * @param sr an {@link SegmentoReta} to which this vector is a direction vector
     */
    public Vector(SegmentoReta sr) {
        super(sr.b().x() - sr.a().x(), sr.b().y() - sr.a().y());
        checkInvariants();
    }

    /**
     * Calculates the multiplication between a scalar and this
     * <hr>
     * 
     * @param d an {@code double} representing a scalar
     * @return a {@link Vector} representing the multiplication result: ( this.x() *
     *         d , this.y() * d )
     */
    public Vector mult(double d) {
        return new Vector(this.calculator(d, (m, n) -> m * n));
    }

    /**
     * Calculates the sum between this and another vector
     * <hr>
     * 
     * @param v an {@link Vector} representing the vector to be calculated with
     * @return a {@link Vector} representing the sum's result: (this.x() + v.x(),
     *         this.y() + v.y())
     */
    public Vector add(Vector v) {
        return new Vector(this.calculator(v, (m, n) -> m + n));
    }

    /**
     * Calculates the subtraction between this and another vector
     * <hr>
     * 
     * @param v an {@link Vector} representing the vector to be calculated with
     * @return a {@link Vector} representing the subtraction's result: (this.x() -
     *         v.x(), this.y() - v.y())
     */
    public Vector sub(Vector v) {
        return new Vector(this.calculator(v, (m, n) -> m - n));
    }

    /**
     * Calculates the transformation of a point by this vector multiplied by an
     * scalar
     * <hr>
     * 
     * @param p an {@link Point} representing the point to be transformed
     * @param s an {@code double} representing a scalar
     * @return a {@link Point} representing the point obtained by the transformation
     *         of p
     */
    public Point transform(Point p, double s) {
        return calculator(p, (m, n) -> n + m * s);
    }

    /**
     * Calculates the transformation of a point by this vector
     * <hr>
     * 
     * @param p an {@link Point} representing the point to be transformed
     * @return a {@link Point} representing the point obtained by the transformation
     *         of p
     */
    public Point transform(Point p) {
        return transform(p, 1);
    }

    /**
     * Calculates the intern product with another vector
     * <hr>
     * 
     * @param v an {@link Vector} representing the vector to be calculated with
     * @return a {@code double} representing the intern product between the two
     *         vectors
     */
    public double internProduct(Vector v) {
        return v.x() * this.x() + v.y() * this.y();
    }

    /**
     * Calculates the cross product with another vector
     * <hr>
     * 
     * @param v an {@link Vector} representing the vector to be calculated with
     * @return a {@code double} representing the cross product between the two
     *         vectors
     */
    public double crossProduct(Vector v) {
        return this.x() * v.y() - this.y() * v.x();
    }

    /**
     * Calculates the cross product between two vectors
     * <hr>
     * 
     * @param v an {@link Vector} representing the first vector
     * @param w an {@link Vector} representing the second vector
     * @return a {@code double} representing the cross product between the two
     *         vectors
     */
    public static double crossProduct(Vector v, Vector w) {
        return v.x() * w.y() - v.y() * w.x();
    }

    /**
     * Calculates the cossine similarity with another vector
     * 
     * @param v an {@link Vector} representing the vector to be calculated with
     * @return a {@code double} representing the cosine of the angle between the two
     *         vectors
     */
    public double cosineSimilarity(Vector v) {
        return this.internProduct(v) / (v.norm() * this.norm());
    }

    /**
     * Calculates the point of intersection with a line-segment
     * <hr>
     * 
     * @param sr an {@link SegmentoReta} representing the line-segment to be
     *           calculated with
     * @return
     *         <ul>
     *         <li>a {@link Point} representing the point of intersection between
     *         this
     *         and the line-segment
     *         <li>or {@code null} If there is no point where this and the
     *         line-segment
     *         intersect each other
     *         </ul>
     */
    public Point intersect(SegmentoReta sr) {
        BiFunction<Point, Point, Double> det = (a, b) -> (a.x() * b.y()) - (a.y() * b.x());

        Point A = sr.a(), B = sr.b();
        Vector u = new Vector(A, B);

        double d = det.apply(this, u);
        if (Math.abs(d) < 1e-9)
            return null;

        double s = det.apply(A, this) / d;
        double t = (A.x() + s * u.x()) / this.x();
        if ((s < 0 || 1 < s) || t < 0)
            return null;

        return this.calculator(t, (m, n) -> m * n);
    }

    /**
     * Determines whether a object is equal to this.
     * <hr>
     * 
     * @param o an {@link Object} representing the object which will be compared to
     *          this
     * @return
     *         <ul>
     *         <li>{@code true} if the object is a {@link Vector} and (this.x
     *         == o.x, this.y
     *         == o.y), within a default tolerance
     *         <li>{@code false} otherwise
     *         </ul>
     */
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Vector))
            return false;

        Vector p = (Vector) o;
        return Helper.tolerantEquals(this.x(), p.x()) && Helper.tolerantEquals(this.y(), p.y());
    }

    @Override
    public int hashCode() {
        long xFixed = Math.round(this.x() * 1e9);
        long yFixed = Math.round(this.y() * 1e9);
        return Objects.hash(xFixed, yFixed);
    }

    /**
     * @return a {@link String} representing this vector in the format "[X.XX,
     *         Y.YY]"
     */
    @Override
    public String toString() {
        return String.format("[%.2f,%.2f]", this.x(), this.y());
    }

    /**
     * Checks the class' invariants validity
     * <hr>
     * 
     * Exits the program with the mensage {@code "Vetor:iv"} if one of them is
     * violated
     */
    private void checkInvariants() {
        if (Math.abs(this.norm()) > 1e-9)
            return;

        Helper.ivExit(ERR_MSG);
    }
}
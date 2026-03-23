import java.util.function.BiFunction;

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
     * 
     * @param p an {@code Point} representing the point obtained when the vector is
     *          aplied to the origin
     */
    public Vector(Point p) {
        super(p.x(), p.y());
        checkInvariants();
    }

    public Vector(Point a, Point b) {
        super(b.x() - a.x(), b.y() - a.y());
        checkInvariants();
    }

    public Vector(SegmentoReta sr) {
        super(sr.b().x() - sr.a().x(), sr.b().y() - sr.a().y());
        checkInvariants();
    }

    /**
     * @param d an {@code double} representing a scalar
     * @return a {@code vetor} which equals ( this.x() * d , this.y() * d )
     */
    public Vector mult(double d) {
        return new Vector(this.calculator(d, (m, n) -> m * n));
    }

    /**
     * @param v an {@code Vetor} representing the vector which will be added to this
     *          vector
     * @return a {@code Vetor} which equals (this.x() + v.x(), this.y() + v.y())
     */
    public Vector add(Vector v) {
        return new Vector(this.calculator(v, (m, n) -> m + n));
    }

    /**
     * @param v an {@code Vetor} representing the vector which will be subtracted
     *          from this vector
     * @return a {@code Vetor} which equals (this.x() - v.x(), this.y() - v.y())
     */
    public Vector sub(Vector v) {
        return new Vector(this.calculator(v, (m, n) -> m - n));
    }

    /**
     * @param p an {@code Point} representing the point which will be transformed by
     *          this vector
     * @return a {@code Point} representing the point obtained by the transformation
     *         of p
     */
    public Point transform(Point p, double s) {
        return calculator(p, (m, n) -> n + m * s);
    }

    public Point transform(Point p) {
        return transform(p, 1);
    }

    /**
     * @param v an {@code Vetor} representing a vector
     * @return a {@code double} representing the intern product between the two
     *         vectors
     */
    public double internProduct(Vector v) {
        return v.x() * this.x() + v.y() * this.y();
    }

    public double crossProduct(Vector v) {
        return this.x() * v.y() - this.y() * v.x();
    }

    public static double crossProduct(Vector v, Vector w) {
        return v.x() * w.y() - v.y() * w.x();
    }

    /**
     * @param v v an {@code Vetor} representing a vector
     * @return a {@code double} representing the cosine of the angle between the two
     *         vectors
     */
    public double cosineSimilarity(Vector v) {
        return this.internProduct(v) / (v.norm() * this.norm());
    }

    /**
     * @param sr an {@code SegmentoReta} representing a line-segment
     * @return a {@code Point} representing the point of intersection between this
     *         and the line-segment
     *         <br>
     *         or {@code null} If there is no point where this and the line-segment
     *         intersect each other
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
     * Checks the class' invariants validity <br>
     * 
     * Exits the program with the mensage "Vetor:iv" if one of them is violated
     */
    public void checkInvariants() {
        if (Math.abs(this.norm()) > 1e-9)
            return;

        throw new IllegalArgumentException(ERR_MSG);
    }

    public String toString() {
        return String.format("[%.2f,%.2f]", this.x(), this.y());
    }
}
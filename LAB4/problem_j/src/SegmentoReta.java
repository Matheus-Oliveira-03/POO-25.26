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

    /**
     * @return {@code this.a}
     */
    public Point a() {
        return this.a;
    }

    /**
     * @return {@code this.b}
     */
    public Point b() {
        return this.b;
    }

    public double length() {
        return a.distanceTo(b);
    }

    public String toString() {
        return "sr(" + this.a.toString() + ";" + this.b.toString() + ")";
    }

    /**
     * @return a {@code Vector} representing the vector from A to B
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
     * @return a {@code Vector} representing this line-segment's direction vector
     */
    public Vector directionVector() {
        return SegmentoReta.directionVector(a, b);
    }

    public Point getPoint(double t) {
        return directionVector().transform(a, t);
    }

    // #endregion

    // #region Calculations

    /**
     * 
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

    public Point intersect(SegmentoReta sr) {
        return intersect(sr.a(), sr.b());
    }

    // #endregion

    // #region Checks

    /**
     * Checks the class' invariants validity <br>
     * 
     * Exits the program with the mensage "SegmentoReta:iv" if one of them is
     * violated
     */
    private void checkInvariants() {
        if (!a.equals(b))
            return;

        throw new IllegalArgumentException(ERR_MSG);
    }

    // #endregion
}

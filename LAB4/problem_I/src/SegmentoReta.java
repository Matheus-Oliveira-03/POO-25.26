/**
 * Represents a line-segment defined by two extrems
 * 
 * @author Matheus Martins Oliveira | a85794
 * @version 3.0 | 06/03/2026
 * @inv {@code this.a} must be different from {@code this.b}
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

    /**
     * @return a {@code Double} representing this line-segment's slope<br>
     *         or {@code null} in case the slope could not be found
     */
    private Double slope() {
        Point xyDif = this.a.calculator(this.b, (m, n) -> m - n);
        return xyDif.x() == 0 ? null : xyDif.y() / xyDif.x();
    }

    /**
     * @return a {@code double} representing the y-intercept of this line-segment's
     *         supporting line
     */
    private double y0() {
        Double slope = this.slope();
        return slope == null ? 0 : this.b.y() - this.b.x() * slope;
    }

    public String toString() {
        return "sr(" + this.a.toString() + ";" + this.b.toString() + ")";
    }

    /**
     * @return a {@code Vetor} representing This line-segment's position vector
     */
    public Vector directionVector() {
        return new Vector(b.x() - a.x(), b.y() - a.y());
    }

    // #endregion

    // #region Calculations

    /**
     * @param x an {@code double} representing the x axis value to be used
     * @return a {@code double} representing the y value found using the supporting
     *         line <br>
     *         or {@code null} in case the supporting line's slope is {@code null}
     */
    private Double y(double x) {
        if (this.slope() == null)
            return null;
        return this.slope() * x + this.y0();
    }

    /**
     * @param v an {@code Vetor} representing the vector this line-segment will be
     *          intersected with
     * @return an {@code Point} representing the point of intersection between this
     *         and v <br>
     *         or {@code null} in case there is no point where this and v intersect
     *         each other
     */
    public Point intersect(Vector v) {
        SegmentoReta sr = new SegmentoReta(Point.ORIGIN, v);
        Double sl1 = this.slope(), sl2 = sr.slope();

        if (supportRectEquals(sr) != false)
            return null;
        if (Math.abs(sl1 - sl2) < 1e-9)
            return null;

        double x = (sr.y0() - this.y0()) / (sl1 - sl2);
        double y = y(x);
        if (checkBounds(x, y))
            return new Point(x, y);

        return null;
    }

    // #endregion

    // #region Checks

    /**
     * @param x an {@code double} representing the x axis value to be checked
     * @return {@code true} if x is within this line-segments x-axis bounds <br>
     *         {@code false} otherwise
     */
    private boolean checkX(double x) {
        double x1, x2;
        if (this.a.x() < this.b.x()) {
            x1 = this.a.x();
            x2 = this.b.x();
        } else {
            x1 = this.b.x();
            x2 = this.a.x();
        }
        return x1 <= x && x <= x2;
    }

    /**
     * @param y an {@code double} representing the y axis value to be checked
     * @return {@code true} if y is within this line-segments y-axis bounds <br>
     *         {@code false} otherwise
     */
    private boolean checkY(double y) {
        double y1, y2;
        if (this.a.y() < this.b.y()) {
            y1 = this.a.y();
            y2 = this.b.y();
        } else {
            y1 = this.b.y();
            y2 = this.a.y();
        }
        return y1 <= y && y <= y2;
    }

    private boolean checkBounds(double x, double y) {
        return checkX(x) && checkY(y);
    }

    public Boolean supportRectEquals(SegmentoReta sr) {
        Double sl1 = this.slope();
        Double sl2 = sr.slope();

        if (sl1 != sl2)
            return false;
        else if (sl1 != null)
            return this.y0() == sr.y0() ? true : null;
        else
            return this.a().x() == sr.a().x() ? true : null;
    }

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
        // System.out.println(ERR_MSG);
        // System.exit(0);
    }

    // #endregion
}

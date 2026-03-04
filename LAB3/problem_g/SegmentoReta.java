/**
 * Represents a line-segment defined by two extrems
 * 
 * @author Matheus Martins Oliveira | a85794
 * @version 3.0 | 27/02/2026
 * @inv The extrems can not be equal
 */
public class SegmentoReta {
    private final String ERR_MSG = "SegmentoReta:iv";
    private Ponto a;
    private Ponto b;

    /**
     * Constructor from a point and a vector
     * 
     * @param p (Ponto)
     * @param v (Vetor)
     */
    public SegmentoReta(Ponto p, Vetor v) {
        this(p, v.transform(p));
    }

    /**
     * Constructor from two points
     * 
     * @param a (Ponto)
     * @param b (Ponto)
     */
    public SegmentoReta(Ponto a, Ponto b) {
        if (a.x() < b.x()) {
            this.a = a;
            this.b = b;
        } else {
            this.a = b;
            this.b = a;
        }

        checkInvariants();
    }

    /**
     * Getter for point A
     * 
     * @return (Ponto) This instance's point A
     */
    public Ponto a() {
        return this.a;
    }

    /**
     * Getter for point B
     * 
     * @return (Ponto) This instance's point B
     */
    public Ponto b() {
        return this.b;
    }

    /**
     * Calculates this line-segment's supporting line's slope
     * 
     * @return (Double) A number, if the slope was successfully calculed <br>
     *         (null) Otherwise
     */
    private Double slope() {
        Ponto xyDif = this.a.calculator(this.b, (m, n) -> m - n);
        return xyDif.x() == 0 ? null : xyDif.y() / xyDif.x();
    }

    /**
     * Calculates this line-segment's supporting line's y-intercept
     * 
     * @return (double) The y axis value when x=0
     */
    private double y0() {
        Double slope = this.slope();
        return slope == null ? 0 : this.b.y() - this.b.x() * slope;
    }

    /**
     * Calculates the y axis value for a given x using this line-segment's
     * supporting line equation
     * 
     * @param x (double)
     * @return (Double) The y value for the given x <br>
     *         (null) If this segment's slope is null
     */
    private Double y(double x) {
        if (this.slope() == null)
            return null;
        return this.slope() * x + this.y0();
    }

    /**
     * Calculates the intersection between this and a vector
     * 
     * @param v (Vetor)
     * @return (Ponto) The point of intersection between this and the vector <br>
     *         (null) If there is no point where this and the vector intersect each
     *         other
     */
    public Ponto intersect(Vetor v) {
        SegmentoReta sr = new SegmentoReta(Ponto.ORIGIN, v);
        Double sl1 = this.slope(), sl2 = sr.slope();

        if (sl1 == null || sl2 == null)
            return null;
        if (Math.abs(sl1 - sl2) < 1e-9)
            return null;

        double x = (sr.y0() - this.y0()) / (sl1 - sl2);
        double y = y(x);
        if (checkX(x) && checkY(y))
            return new Ponto(x, y);

        return null;
    }

    /**
     * Checks whether the given x value is within this line-segment's x axis bounds
     * 
     * @param x (double)
     * @return (true) If the given value is within bounds <br>
     *         (false) Otherwise
     */
    private boolean checkX(double x) {
        return this.a.x() <= x && x <= this.b.x();
    }

    /**
     * Checks whether the given y value is within this line-segment's y axis bounds
     * 
     * @param y (double)
     * @return (true) If the given value is within bounds <br>
     *         (false) Otherwise
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

    public String toString() {
        return "sr(" + this.a.toString() + ";" + this.b.toString() + ")";
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

        System.out.println(ERR_MSG);
        System.exit(0);
    }
}

import java.util.function.BinaryOperator;

/**
 * Represents a point with (x,y) coordinates
 * 
 * @author Matheus Martins Oliveira | a85794
 * @version 2.0 | 27/02/2026
 */
public class Ponto {
    public static Ponto ORIGIN = new Ponto(0, 0);
    private double x;
    private double y;

    /**
     * Constructor from two doubles
     * 
     * @param x (double) x axis value
     * @param y (double) y axis value
     */
    public Ponto(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Getter for x axis value
     * 
     * @return (double) x value
     */
    public double x() {
        return this.x;
    }

    /**
     * Getter for y axis value
     * 
     * @return (double) y value
     */
    public double y() {
        return this.y;
    }

    public Ponto calculator(double x, BinaryOperator<Double> operator) {
        return calculator(new Ponto(x, x), operator);
    }

    /**
     * Calculates a given operation between this point and a given one <br>
     * The operation is applied to each axis separately, not between axis, <br>
     * e.g. (this.x + p.x, this.y + p.y) -> Valid, (this.x + p.y, this.y + p.x) ->
     * Invalid
     * 
     * @param p        (Ponto)
     * @param operator (BinaryOperator<Double>)
     * @return (Ponto) A point containing the results of the operation on each axis
     */
    public Ponto calculator(Ponto p, BinaryOperator<Double> operator) {
        if (operator == null)
            return null;

        double xx = operator.apply(this.x(), p.x());
        double yy = operator.apply(this.y(), p.y());
        return new Ponto(xx, yy);
    }

    /**
     * Calculates the distance between this point and a given one
     * 
     * @param p (Ponto)
     * @return (double) The distance between the two points
     */
    public double distanceTo(Ponto p) {
        return Math.sqrt(Math.pow(this.x - p.x(), 2) + Math.pow(this.y - p.y(), 2));
    }

    /**
     * Calculates the distance between this point and the origin(0, 0)
     * 
     * @return (double) The distance between this and the origin
     */
    public double norm() {
        return distanceTo(ORIGIN);
    }

    /**
     * Calculates whether this point and a given one are equal by comparing their
     * values
     * 
     * @param p (Ponto)
     * @return (true) If the points are equal <br>
     *         (false) Otherwise
     */
    public boolean equals(Ponto p) {
        double xx = Math.abs(this.x() - p.x());
        double yy = Math.abs(this.y() - p.y());

        return xx < 1e-9 && yy < 1e-9;
    }

    /**
     * Compare which is closer to the origin between this and a given point
     * 
     * @param p (Ponto)
     * @return (-1) If the given point is closer to the origin<br>
     *         (0) If both are at the same distance to the origin<br>
     *         (1) If this is closer to the origin
     */
    public int compareTo(Ponto p) {
        double disA = this.norm();
        double disB = p.norm();
        return Math.abs(disA - disB) < 1e-9 ? 0 : disA < disB ? 1 : -1;
    }

    public String toString() {
        return "(" + String.format("%.2f", this.x) + "," + String.format("%.2f", this.x) + ")";
    }
}

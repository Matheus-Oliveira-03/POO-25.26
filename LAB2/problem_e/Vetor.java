import java.util.function.BiFunction;

/**
 * Represents a direction vector <br>
 * This class extends from the Ponto class for the sake of simplicity
 * 
 * @author Matheus Martins Oliveira | a85794
 * @version 2.0 | 27/02/2026
 * @inv Vector's norm can't be equal 0
 */
public class Vetor extends Ponto {
    private final String ERR_MSG = "Vetor:iv";

    /**
     * Constructor from two doubles;
     * 
     * @param x (double) coordinate in x axis
     * @param y (double) coordinate in y axis
     */
    public Vetor(double x, double y) {
        super(x, y);
        checkInvariants();
    }

    /**
     * Constructor from a point
     * 
     * @param p (Ponto) point in (x,y) format
     */
    public Vetor(Ponto p) {
        super(p.x(), p.y());
        checkInvariants();
    }

    /**
     * Transform a given point by using this vector unmodiffied
     * 
     * @param p (Ponto)
     * @return (Ponto) The transformed point
     */
    public Ponto transform(Ponto p) {
        return calculator(p, (m, n) -> m + n);
    }

    /**
     * Calculates the intern product of this vector by a vector v
     * 
     * @param v (Vetor)
     * @return (double) The calculated value for the intern product
     */
    public double internProduct(Vetor v) {
        return v.x() * this.x() + v.y() * this.y();
    }

    /**
     * Calculates the cosine similarity of this vector and a vector v
     * 
     * @param v (Vetor)
     * @return (double) The cosine of the angle between the two vectors
     */
    public double cosineSimilarity(Vetor v) {
        return this.internProduct(v) / (v.norm() * this.norm());
    }

    /**
     * Calculates the point of intersection between this vector and a line-segment
     * 
     * @param sr (SegmentoReta)
     * @return (Ponto) The point of intersection between this and the line-segment
     *         <br>
     *         (null) If there is no point where this and the line-segment intersect
     *         each other
     */
    public Ponto intersect(SegmentoReta sr) {
        BiFunction<Ponto, Ponto, Double> det = (a, b) -> (a.x() * b.y()) - (a.y() * b.x());

        Ponto A = sr.a(), B = sr.b();
        Vetor u = new Vetor(B.calculator(A, (b, a) -> b - a));

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

        System.out.println(ERR_MSG);
        System.exit(0);
    }
}
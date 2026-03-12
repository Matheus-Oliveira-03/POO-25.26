/**
 * Represents the moviment of a plane within a certain trajectory. <br>
 * Since the tranjectory is defined by a line-segment, this class extends from
 * the class SegmentoReta.
 * 
 * @extends {@link SegmentoReta}
 * @author Matheus Martins Oliveira | a85794
 * @version 1.0 | 06/03/2026
 * @inv {@code this.a} must be different from {@code this.b}
 */
public class AutoPilot extends SegmentoReta {
    /**
     * Constructor from two points
     * 
     * @param start  an {@code Point} from which the plane will depart
     * @param finish The {@code Point} where the plane will arrive
     */
    public AutoPilot(Point start, Point finish) {
        super(start, finish);
    }

    /**
     * @param windSpeed   an {@code Vetor} representig the wind speed
     * @param linearSpeed an {@code double} representing the linear speed of the
     *                    plane
     * @return a double representing the time needed to fly through the trajectory,
     *         in seconds
     */
    public double time(Vetor windSpeed, double linearSpeed) {
        Vetor d = this.toVetor().sub(windSpeed);
        return d.norm() / linearSpeed;
    }

    /**
     * @param windSpeed an {@code Vetor} representing the wind speed
     * @param time      an {@code double} representing the time in which the
     *                  trajectory will be covered
     * @return a {@code Vetor} representing the plane's speed
     */
    public Vetor speed(Vetor windSpeed, double time) {
        return (this.toVetor().sub(windSpeed)).mult(1 / time);
    }
}

package main;

import geometry.Point;
import geometry.SegmentoReta;
import geometry.Vector;

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
     * @param linearSpeed an {@code double} representing the linear speed of the
     *                    plane
     * @return a double representing the time needed to fly through the trajectory,
     *         in seconds
     */
    public double time(double linearSpeed) {
        if (linearSpeed == 0)
            throw new IllegalArgumentException("linearSpeed must be != 0");

        double r = this.a().distanceTo(this.b());
        return r / linearSpeed;
    }

    /**
     * @param windSpeed an {@code Vetor} representing the wind speed
     * @param time      an {@code double} representing the time in which the
     *                  trajectory will be covered
     * @return a {@code Vetor} representing the plane's speed
     */
    public Vector speed(Vector windSpeed, double time) {
        if (windSpeed == null)
            throw new IllegalArgumentException("windSpeed cannot be null");
        else if (time == 0)
            throw new IllegalArgumentException("time must be != 0");
        else
            return (this.directionVector().mult(1 / time)).sub(windSpeed);
    }
}

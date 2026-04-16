package main;

import java.util.ArrayList;
import java.util.List;

import geometry.Point;
import geometry.Route;
import geometry.SegmentoReta;
import geometry.Vector;

/**
 * Represents the moviment of a plane within a certain trajectory.
 * <hr>
 * 
 * @see Route
 * @author a85794 | Matheus Martins Oliveira
 * @version 3.0 | 06/04/2026
 * @inv
 *      <ul>
 *      <li>Two consecutive points within the trajectory cannot be equal
 *      <li>The trajectory must contain at least two points
 *      <li>The wind speed cannot be null and its' norm cannot be {@code == 0}
 *      <li>The plane's linear speed cannot be {@code == 0}
 *      </ul>
 */
public class AutoPilot extends Route {
    private Vector windSpeed;
    private double linearSpeed;

    /**
     * Constructor from two points
     * 
     * @param start  : a {@link Point} representing the place from where the plane
     *               will depart
     * @param finish : a {@link Point} representing the place where the plane will
     *               arrive
     * @param w      : a {@link Vector} representing the wind speed
     * @param ls     : a {@code double} representing the plane's linear speed
     */
    public AutoPilot(List<Point> points, Vector w, double ls) {
        super(points);
        assignParameters(w, ls);
    }

    /**
     * Constructor from an array
     * 
     * @param data : a array of {@link String} containg the values for each point
     *             within the trajectory. Must have a even quantity of elements and
     *             all must be parsable doubles.
     * @param w    : a {@link Vector} representing the wind speed
     * @param ls   : a {@code double} representing the plane's linear speed
     */
    public AutoPilot(String[] data, Vector w, double ls) {
        super(data);
        assignParameters(w, ls);
    }

    /**
     * Verifies whether some parameters are apropriate and assings its values to the
     * respective class parameters
     * 
     * @param v : a {@link Vector} which corresponds to the wind speed
     * @param d : a {@code double} which corresponds to the linear speed
     */
    private void assignParameters(Vector v, double d) {
        if (v == null || v.norm() == 0 || d == 0)
            Helper.ivExit(ERR_MSG());

        this.windSpeed = v;
        this.linearSpeed = d;
    }

    /**
     * Calculates the timen needed to fly through a part of the trajectory
     * <hr>
     * 
     * @param sr : a {@link SegmentoReta} representing a segment of the trajectory
     * @return a {@code double} representing the time needed to fly through this
     *         segment, in seconds
     */
    public double time(SegmentoReta sr) {
        if (sr == null)
            Helper.ivExit(ERR_MSG());

        double r = sr.a().distanceTo(sr.b());
        return r / linearSpeed;
    }

    /**
     * Calculates the time needed to fly trhough the whole trajectory
     * <hr>
     * 
     * @return a {@code double} representing the time needed to fly through the
     *         trajectory, in seconds
     */
    public double time() {
        List<SegmentoReta> _segments = this.segments();
        double totalTime = 0;

        for (SegmentoReta sr : _segments) {
            totalTime += this.time(sr);
        }

        return totalTime;
    }

    /**
     * Calculates the plane's speed while flying through a part of the trajectory
     * <hr>
     * 
     * @param sr : a {@link SegmentoReta} representing a segment of the trajectory
     * @return a {@link Vetor} representing the plane's speed through this segment
     */
    public Vector speed(SegmentoReta sr) {
        if (sr == null)
            Helper.ivExit(ERR_MSG());

        return (sr.directionVector().mult(1 / this.time(sr))).sub(this.windSpeed);
    }

    /**
     * Calculates the plane's speeds while flying through the whole trajectory
     * <hr>
     * 
     * @return a {@link List} of {@link Vetor}, where each vector represents the
     *         plane's speed while flying through the respective segment of the
     *         trajectory
     */
    public List<Vector> speed() {
        List<SegmentoReta> _segments = this.segments();
        List<Vector> speeds = new ArrayList<>();

        for (SegmentoReta sr : _segments) {
            speeds.add(this.speed(sr));
        }

        return speeds;
    }

    /**
     * Calculates the plane's position at a given time
     * <hr>
     * 
     * @param time : a {@code double} representing the time past since the flight
     *             start
     * @return a {@link Point} representing the plane's current position
     */
    public Point position(double time) {
        List<SegmentoReta> _segments = this.segments();
        double currentTime = 0;
        double oldTime = 0;

        for (SegmentoReta sr : _segments) {
            oldTime = currentTime;
            double segmentTime = this.time(sr);
            currentTime += segmentTime;

            if (time <= currentTime) {
                double t = (time - oldTime) / segmentTime;
                return sr.getPoint(t);
            }
        }

        return null;
    }
}

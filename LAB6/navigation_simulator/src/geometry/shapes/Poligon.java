package geometry.shapes;

import java.util.ArrayList;
import java.util.List;

import geometry.Point;
import geometry.SegmentoReta;
import main.Helper;

public class Poligon implements Shape {
    protected List<Point> vertices;
    protected List<SegmentoReta> edges;

    /**
     * Creates a Poligon from a list of {@link Point}.
     * <hr>
     * 
     * @param pts : a {@code List<Point>} representing the vertices of the
     *            poligon.
     * 
     * @see SegmentoReta#checkInvariants()
     * @see #sizeCheck()
     * @see Helper#ivExit(String)
     */
    public Poligon(List<Point> pts) {
        this.vertices = pts;
        this.edges = new ArrayList<SegmentoReta>();
        sizeCheck();

        try {
            for (int i = 0; i < vertices.size(); i++) {
                if (i == vertices.size() - 1)
                    edges.add(new SegmentoReta(vertices.get(i++), vertices.get(0)));
                else
                    edges.add(new SegmentoReta(vertices.get(i), vertices.get(i + 1)));
            }
        } catch (Exception e) {
            Helper.ivExit(e.getMessage());
        }
    }

    public List<SegmentoReta> edges() {
        return this.edges;
    }

    /**
     * @return The list of vertices in the format "(x, y) (x', y') (x", y") ..."
     */
    @Override
    public String toString() {
        return Helper.listToString(this.vertices);
    }

    /**
     * Checks the vertices list's size. Exists the program with a error message in
     * case the list has less than 3 vertices.
     * 
     * @see Helper#ivExit(String)
     */
    protected void sizeCheck() {
        if (this.vertices.size() < 3)
            Helper.ivExit(this.ERR_MSG());
    }

    /**
     * @return
     *         <ul>
     *         <li>a {@code List<Point>}, containing the {@link Point}(s) of
     *         intersection found,
     *         <li>{@code null}, if the shape does not intersect the
     *         {@link SegmentoReta}
     *         </ul>
     */
    @Override
    public List<Point> intersect(SegmentoReta sr) {
        List<Point> intersections = new ArrayList<Point>();
        for (SegmentoReta edge : edges) {
            Point intersection = edge.intersect(sr);
            if (intersection != null && !intersections.contains(intersection))
                intersections.add(intersection);
        }

        return intersections.isEmpty() ? null : intersections;
    }

    /**
     * The method returns the error message, represented by a {@link String},
     * associated with the
     * shape's invariants violations. <br>
     * Each shape has its own error message.
     * 
     * @return {@code "Poligono:iv"}
     */
    @Override
    public String ERR_MSG() {
        return "Poligono:iv";
    }
}
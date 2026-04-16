package geometry.shapes;

import java.util.List;

import geometry.Point;
import main.Helper;

public class Triangle extends Poligon {

    /**
     * Creates a Triangle from a list of {@link Point} and with "Triangle:iv" for
     * error message.
     * <hr>
     * 
     * @param pts : a {@code List<Point>} representing the vertices of the
     *            triangle.
     * 
     * @see #colinearityCheck()
     * @see #sizeCheck()
     * @see Helper#ivExit(String)
     */
    public Triangle(List<Point> pts) {
        super(pts);
        colinearityCheck();
    }

    /**
     * Checks the vertices list's size. Exists the program with a error message in
     * case the list doesn't have exactly 3 vertices.
     * <hr>
     * 
     * @see Helper#ivExit(String)
     */
    @Override
    protected void sizeCheck() {
        if (this.vertices.size() != 3)
            Helper.ivExit(ERR_MSG());
    }

    /**
     * Checks, using the determinant, if all vertices are contained within the same
     * line. If so, exits the program with the error message.
     * 
     * @see Helper#ivExit(String)
     */
    private void colinearityCheck() {
        Point p1 = vertices.get(0);
        Point p2 = vertices.get(1);
        Point p3 = vertices.get(2);

        double mainDiagonals = p1.x() * p2.y() + p1.y() * p3.x() + p2.x() * p3.y();
        double secondaryDiagonals = p2.y() * p3.x() + p1.x() * p3.y() + p1.y() * p2.x();
        double D = mainDiagonals - secondaryDiagonals;

        if (Helper.isZero(D))
            Helper.ivExit(ERR_MSG());
    }

    /**
     * The method returns the error message, represented by a {@link String},
     * associated with the
     * shape's invariants violations. <br>
     * Each shape has its own error message.
     * 
     * @return {@code "Triangulo:iv"}
     */
    @Override
    public String ERR_MSG() {
        return "Triangulo:iv";
    }
}

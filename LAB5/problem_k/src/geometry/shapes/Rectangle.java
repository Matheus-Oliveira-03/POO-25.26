package geometry.shapes;

import java.util.List;

import geometry.Point;
import main.Helper;

public class Rectangle extends Poligon {

    /**
     * Creates a Rectangle from a list of {@link Point}. <br>
     * <hr>
     * 
     * @param pts     : a {@code List<Point>} representing the vertices of the
     *                rectangle.
     * @param ERR_MSG : a {@link String} representing the error message that is
     *                shown when the
     *                invariates are violated.
     * 
     * @see #diagonalCheck()
     * @see #sizeCheck()
     * @see #edgesCheck()
     * @see Helper#ivExit(String)
     */
    public Rectangle(List<Point> pts) {
        super(pts);

        diagonalCheck();
        edgesCheck();
    }

    /**
     * Checks the vertices list's size. Exists the program with a error message in
     * case the list doesn't have exactly 4 vertices.
     * 
     * @see Helper#ivExit(String)
     */
    @Override
    protected void sizeCheck() {
        if (vertices.size() != 4)
            Helper.ivExit(ERR_MSG());
    }

    /**
     * Checks whether the diagonals ahave the same length.
     * Exits the program if the diagonals are different.
     * 
     * @see Helper#ivExit(String)
     */
    protected void diagonalCheck() {
        double AC = this.vertices.get(0).distanceTo(this.vertices.get(2));
        double BD = this.vertices.get(1).distanceTo(this.vertices.get(3));

        if (!Helper.tolerantEquals(AC, BD))
            Helper.ivExit(ERR_MSG());
    }

    /**
     * Checks whether the paralel edges have the same length.
     * Exits the program if two paralel edges are different.
     * 
     * @see Helper#ivExit(String)
     */
    protected void edgesCheck() {
        double AB = this.vertices.get(0).distanceTo(this.vertices.get(1));
        double BC = this.vertices.get(1).distanceTo(this.vertices.get(2));
        double CD = this.vertices.get(2).distanceTo(this.vertices.get(3));
        double DA = this.vertices.get(3).distanceTo(this.vertices.get(0));

        if (!Helper.tolerantEquals(AB, CD) || !Helper.tolerantEquals(BC, DA))
            Helper.ivExit(ERR_MSG());
    }

    @Override
    public String ERR_MSG() {
        return "Retangulo:iv";
    }
}

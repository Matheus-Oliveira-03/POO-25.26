package geometry.shapes;

import java.util.List;

import geometry.Point;
import geometry.SegmentoReta;
import main.Helper;

public class Square extends Rectangle {

    /**
     * Creates a Square from a list of {@link Point} and with "Quadrado:iv" for
     * error message.
     * <hr>
     * 
     * @param pts : a {@code List<Point>} representing the vertices of the
     *            square.
     * 
     * @see Rectangle#diagonalCheck()
     * @see Rectangle#sizeCheck()
     * @see #edgesCheck()
     * @see Helper#ivExit(String)
     */
    public Square(List<Point> pts) {
        super(pts);
    }

    /**
     * Checks whether the edges have the same length.
     * Exits the program if even one of the edges are different.
     * 
     * @see Helper#ivExit(String)
     */
    @Override
    protected void edgesCheck() {
        for (SegmentoReta edge : edges) {
            if (!Helper.tolerantEquals(edge.length(), edges.getFirst().length()))
                Helper.ivExit(ERR_MSG());
        }
    }

    /**
     * The method returns the error message, represented by a {@link String},
     * associated with the
     * shape's invariants violations. <br>
     * Each shape has its own error message.
     * 
     * @return {@code "Quadrado:iv"}
     */
    @Override
    public String ERR_MSG() {
        return "Quadrado:iv";
    }
}

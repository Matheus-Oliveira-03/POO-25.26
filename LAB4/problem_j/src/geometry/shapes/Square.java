package geometry.shapes;

import java.util.List;

import geometry.Point;
import geometry.SegmentoReta;
import main.Helper;

public class Square extends Rectangle {
    public Square(List<Point> pts) {
        super(pts, "Quadrado:iv");
    }

    @Override
    protected void edgesCheck() {
        for (SegmentoReta edge : edges) {
            if (!Helper.equals(edge.length(), edges.getFirst().length()))
                Helper.ivExit(ERR_MSG);
        }
    }
}

import java.util.List;

public class Triangle extends Poligon {
    public Triangle(List<Point> pts) {
        super(pts, "Triangulo:iv");
        colinearityCheck();
    }

    @Override
    protected void sizeCheck() {
        if (this.vertices.size() != 3)
            Helper.ivExit(ERR_MSG);
    }

    private void colinearityCheck() {
        Point p1 = vertices.get(0);
        Point p2 = vertices.get(1);
        Point p3 = vertices.get(2);

        double mainDiagonals = p1.x() * p2.y() + p1.y() * p3.x() + p2.x() * p3.y();
        double secondaryDiagonals = p2.y() * p3.x() + p1.x() * p3.y() + p1.y() * p2.x();
        double D = mainDiagonals - secondaryDiagonals;

        if (Helper.isZero(D))
            Helper.ivExit(ERR_MSG);
    }
}

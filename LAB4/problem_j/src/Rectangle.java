import java.util.List;

public class Rectangle extends Poligon {
    public Rectangle(List<Point> pts, String ERR_MSG) {
        super(pts, ERR_MSG);

        diogonalCheck();
        edgesCheck();
    }

    public Rectangle(List<Point> pts) {
        this(pts, "Retangulo:iv");
    }

    @Override
    protected void sizeCheck() {
        if (vertices.size() != 4)
            Helper.ivExit(ERR_MSG);
    }

    protected void diogonalCheck() {
        double AC = this.vertices.get(0).distanceTo(this.vertices.get(2));
        double BD = this.vertices.get(1).distanceTo(this.vertices.get(3));

        if (!Helper.isZero(AC - BD))
            Helper.ivExit(ERR_MSG);
    }

    protected void edgesCheck() {
        double AB = this.vertices.get(0).distanceTo(this.vertices.get(1));
        double BC = this.vertices.get(1).distanceTo(this.vertices.get(2));
        double CD = this.vertices.get(2).distanceTo(this.vertices.get(3));
        double DA = this.vertices.get(3).distanceTo(this.vertices.get(0));

        if (!Helper.equals(AB, CD) || !Helper.equals(BC, DA))
            Helper.ivExit(ERR_MSG);
    }
}

import java.util.ArrayList;
import java.util.List;

public class Poligon implements Shape {
    protected String ERR_MSG;
    protected List<Point> vertices;
    protected List<SegmentoReta> edges;

    public Poligon(List<Point> pts, String ERR_MSG) {
        this.ERR_MSG = ERR_MSG;
        this.vertices = pts;
        this.edges = new ArrayList<SegmentoReta>();
        sizeCheck();

        for (int i = 0; i < vertices.size(); i++) {
            if (i == vertices.size() - 1)
                edges.add(new SegmentoReta(vertices.get(i++), vertices.get(0)));
            else
                edges.add(new SegmentoReta(vertices.get(i), vertices.get(i + 1)));
        }
    }

    public Poligon(List<Point> pts) {
        this(pts, "Poligono:iv");
    }

    public List<SegmentoReta> edges() {
        return this.edges;
    }

    public String toString(List<Point> pts) {
        if (pts == null)
            return null;

        String str = "";
        for (Point point : pts) {
            str += point;
        }

        return str;
    }

    protected void sizeCheck() {
        if (this.vertices.size() < 3)
            Helper.ivExit(ERR_MSG);
    }

    @Override
    public List<Point> intersect(SegmentoReta sr) {
        List<Point> intersections = new ArrayList<Point>();
        for (SegmentoReta edge : edges) {
            Point intersection = edge.intersect(sr);
            if (intersection != null)
                intersections.add(intersection);
        }

        return intersections.isEmpty() ? null : intersections;
    }
}
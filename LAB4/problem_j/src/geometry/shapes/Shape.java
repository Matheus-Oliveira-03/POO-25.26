package geometry.shapes;

import java.util.List;

import geometry.Point;
import geometry.SegmentoReta;

public interface Shape {
    public List<Point> intersect(SegmentoReta sr);
}

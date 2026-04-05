package geometry.shapes;

import java.util.List;

import geometry.Point;
import geometry.SegmentoReta;

/**
 * 
 */
public interface Shape {

    /**
     * The method calculates the points of intersection between each of the shape's
     * edges and the given {@link SegmentoReta}.
     * <hr>
     * 
     * @param sr a {@link SegmentoReta} with which the intersection will be
     *           calculated
     * @return
     *         <ul>
     *         <li>a {@code List<Point>}, containing the {@link Point}(s) of
     *         intersection found,
     *         <li>{@code null}, if the shape does not intersect the
     *         {@link SegmentoReta}
     *         </ul>
     * 
     */
    public List<Point> intersect(SegmentoReta sr);

    public String ERR_MSG();
}

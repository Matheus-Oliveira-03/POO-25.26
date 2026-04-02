package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

import geometry.Point;
import geometry.SegmentoReta;
import geometry.shapes.Circle;
import main.Helper;

import java.util.List;

public class CircleTest {
    @Test
    void testConstructor() {
        // Illegal Argument Tests
        assertThrows(IllegalArgumentException.class, () -> new Circle(new Point(1, 1), 0));
        assertThrows(IllegalArgumentException.class, () -> new Circle(new Point(1, 1), 1e-10));
        assertThrows(IllegalArgumentException.class, () -> new Circle(new Point(1, 1), -1));
    }

    @Test
    void testGetters() {
        // Center Getter
        assertEquals(new Point(2, 2), Helper.genericCircle(2).center());
        assertEquals(new Point(3, 3), Helper.genericCircle(3).center());
        assertEquals(new Point(2, 1), (new Circle(new Point(2, 1), 3)).center());

        // Radius Getter
        assertEquals(2, Helper.genericCircle(2).radius());
        assertEquals(3, Helper.genericCircle(3).radius());
        assertEquals(5, (new Circle(new Point(2, 1), 5)).radius());
    }

    @Test
    void testIntersect() {
        SegmentoReta horizontal = new SegmentoReta(new Point(0, 1), new Point(4, 1));
        SegmentoReta diagonal = new SegmentoReta(new Point(-4, -4), new Point(4, 4));

        // Intersection Fails
        assertEquals(null, new Circle(new Point(-1, -1), 1).intersect(horizontal));

        // One Point Intersection
        assertEquals(new Point(1, 1), new Circle(Point.ORIGIN, Math.sqrt(2)).intersect(horizontal).get(0));
        assertEquals(1, new Circle(Point.ORIGIN, Math.sqrt(2)).intersect(horizontal).size());

        assertEquals(Point.ORIGIN, new Circle(new Point(-1, -1), Math.sqrt(2)).intersect(diagonal).get(0));
        assertEquals(1, new Circle(new Point(1, -1), Math.sqrt(2)).intersect(diagonal).size());

        // Two Points Intersection
        assertEquals(List.of(new Point(1, 1), new Point(-1, -1)),
                new Circle(Point.ORIGIN, Math.sqrt(2)).intersect(diagonal));
        assertEquals(2, new Circle(Point.ORIGIN, Math.sqrt(2)).intersect(diagonal).size());

        assertEquals(List.of(new Point(1, 1), new Point(-1, -1)), new Circle(new Point(1, -1), 2).intersect(diagonal));
        assertEquals(2, new Circle(new Point(1, -1), 2).intersect(diagonal).size());

        assertEquals(List.of(new Point(3, 1), new Point(1, 1)),
                new Circle(new Point(2, 0), Math.sqrt(2)).intersect(horizontal));
        assertEquals(2, new Circle(new Point(2, 0), Math.sqrt(2)).intersect(horizontal).size());
    }
}

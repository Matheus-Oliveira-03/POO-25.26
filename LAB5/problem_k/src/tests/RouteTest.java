package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import java.util.List;
import java.util.Locale;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import geometry.Point;
import geometry.Route;
import geometry.SegmentoReta;
import geometry.shapes.Circle;
import geometry.shapes.Poligon;
import geometry.shapes.Square;
import geometry.shapes.Triangle;

public class RouteTest {
    // Routes
    List<Point> disfunctional = List.of(Point.ORIGIN, new Point(1, 1), new Point(1, 1), new Point(1, 5));
    List<Point> functional = List.of(Point.ORIGIN, new Point(4, 2), new Point(4, -4), new Point(6, -4),
            new Point(10, 2));

    String functional_toString = "(0.00,0.00) (4.00,2.00) (4.00,-4.00) (6.00,-4.00) (10.00,2.00)";

    // Shapes
    Circle c = new Circle(new Point(-2, 0), 1);
    Square s = new Square(List.of(new Point(0, 1), new Point(0, -1), new Point(-2, -1), new Point(-2, 1)));
    Triangle t = new Triangle(List.of(new Point(4, -1), new Point(5, -4), new Point(3, -5)));
    Poligon p = new Poligon(List.of(new Point(1, 1), new Point(-1, -1), new Point(1, -1), new Point(-1, 1)));

    @BeforeAll
    static void setup() {
        Locale.setDefault(Locale.US);
    }

    @Test
    void testConstructor() {
        // Succeeds
        assertDoesNotThrow(() -> new Route(functional));
        assertDoesNotThrow(() -> new Route(disfunctional));

        // Fails
        assertThrows(IllegalArgumentException.class, () -> new Route(List.of(Point.ORIGIN)));
    }

    @Test
    void testIntersect() {
        // Succeeds
        assertEquals(null, new Route(functional).intersect(c));
        assertEquals(List.of(Point.ORIGIN), new Route(functional).intersect(s));
        assertEquals(List.of(new Point(4, -1), new Point(5, -4)), new Route(functional).intersect(t));
        assertEquals(List.of(Point.ORIGIN), new Route(functional).intersect(p));

        // Fails
        assertThrows(IllegalArgumentException.class, () -> new Route(disfunctional).intersect(p));
    }

    @Test
    void testSegments() {
        // Succeeds
        assertDoesNotThrow(() -> new Route(functional).segments());
        assertEquals(List.of(new SegmentoReta(Point.ORIGIN, new Point(4, 2)),
                new SegmentoReta(new Point(4, 2), new Point(4, -4)),
                new SegmentoReta(new Point(4, -4), new Point(6, -4)),
                new SegmentoReta(new Point(6, -4), new Point(10, 2))), new Route(functional).segments());

        // Fails
        assertThrows(IllegalArgumentException.class, () -> new Route(disfunctional).segments());
    }

    @Test
    void testLength() {
        assertEquals(19.68, new Route(functional).length(), 0.01);
        assertEquals(5.414, new Route(disfunctional).length(), 0.001);
    }

    @Test
    void testPoints() {
        assertEquals(functional, new Route(functional).points());
        assertEquals(disfunctional, new Route(disfunctional).points());
    }

    @Test
    void testToString() {
        assertEquals(functional_toString, new Route(functional).toString());
    }
}

package tests;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;
import java.util.Locale;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import geometry.Point;
import geometry.SegmentoReta;
import geometry.shapes.Poligon;

public class PoligonTest {
    // Poligons
    List<Point> square = List.of(new Point(2, 2), new Point(2, -2), new Point(-2, -2), new Point(-2, 2));
    List<Point> rectangle = List.of(new Point(2, 4), new Point(2, -4), new Point(-2, -4), new Point(-2, 4));
    List<Point> trapezoid = List.of(new Point(2, 4), new Point(2, -4), new Point(-2, 0), new Point(-2, 4));
    List<Point> triangle = List.of(new Point(2, 4), new Point(2, -4), new Point(-6, 0));
    List<Point> pentagon = List.of(new Point(2, 5), new Point(4, 0), new Point(2, -5), new Point(-2, -4),
            new Point(-2, 4));

    List<Point> disfunctional = List.of(Point.ORIGIN, new Point(1, 1), new Point(1, 1), new Point(1, 5));
    List<Point> functional = List.of(new Point(1, 1), new Point(-1, -1), new Point(1, -1), new Point(-1, 1));
    SegmentoReta AB = new SegmentoReta(new Point(1, 1), new Point(-1, -1));
    SegmentoReta BC = new SegmentoReta(new Point(-1, -1), new Point(1, -1));
    SegmentoReta CD = new SegmentoReta(new Point(1, -1), new Point(-1, 1));
    SegmentoReta DA = new SegmentoReta(new Point(-1, 1), new Point(1, 1));

    String functional_toString = "(1.00,1.00) (-1.00,-1.00) (1.00,-1.00) (-1.00,1.00)";

    // Shapes
    SegmentoReta singleIntersect = new SegmentoReta(new Point(-1, 0), new Point(1, 0));
    SegmentoReta doesntIntersect = new SegmentoReta(new Point(-2, -1), new Point(-2, 1));
    SegmentoReta multiIntersects = new SegmentoReta(new Point(0, -2), new Point(0, 2));

    @BeforeAll
    static void setup() {
        Locale.setDefault(Locale.US);
    }

    @Test
    void testConstructor() {
        // Succeeds
        assertDoesNotThrow(() -> new Poligon(functional));
        assertDoesNotThrow(() -> new Poligon(square));
        assertDoesNotThrow(() -> new Poligon(rectangle));
        assertDoesNotThrow(() -> new Poligon(trapezoid));
        assertDoesNotThrow(() -> new Poligon(triangle));
        assertDoesNotThrow(() -> new Poligon(pentagon));

        // Fails
        assertThrows(IllegalArgumentException.class, () -> new Poligon(disfunctional));
        assertThrows(IllegalArgumentException.class, () -> new Poligon(List.of(new Point(1, 0))));
        assertThrows(IllegalArgumentException.class, () -> new Poligon(List.of(new Point(-1, -1), new Point(1, 1))));
    }

    @Test
    void testEdges() {
        assertEquals(List.of(AB, BC, CD, DA), new Poligon(functional).edges());

        // this is a getter test, I don't really know what else to test
    }

    @Test
    void testIntersect() {
        assertEquals(null, new Poligon(functional).intersect(doesntIntersect));
        assertEquals(List.of(Point.ORIGIN), new Poligon(functional).intersect(singleIntersect));
        assertEquals(List.of(Point.ORIGIN, new Point(0, -1), new Point(0, 1)),
                new Poligon(functional).intersect(multiIntersects));
    }

    @Test
    void testToString() {
        assertEquals(functional_toString, new Poligon(functional).toString());
    }
}

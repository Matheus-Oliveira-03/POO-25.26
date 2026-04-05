package tests;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Locale;

import geometry.Point;
import geometry.shapes.Triangle;

public class TriangleTest {
    List<Point> functional_triangle = List.of(new Point(2, 4), new Point(2, -4), new Point(-6, 0));
    List<Point> functional_righTriangle = List.of(new Point(2, 4), new Point(2, -4), new Point(-2, -4));

    List<Point> line = List.of(new Point(-1, -1), new Point(1, 1), new Point(3, 3));
    List<Point> square = List.of(new Point(2, 2), new Point(2, -2), new Point(-2, -2), new Point(-2, 2));
    List<Point> rectangle = List.of(new Point(2, 4), new Point(2, -4), new Point(-2, -4), new Point(-2, 4));
    List<Point> trapezoid = List.of(new Point(2, 4), new Point(2, -4), new Point(-2, 0), new Point(-2, 4));
    List<Point> pentagon = List.of(new Point(2, 5), new Point(4, 0), new Point(2, -5), new Point(-2, -4),
            new Point(-2, 4));

    @BeforeAll
    static void setup() {
        Locale.setDefault(Locale.US);
    }

    @Test
    void testSizeCheck() {
        // Succeeds
        assertDoesNotThrow(() -> new Triangle(functional_triangle));
        assertDoesNotThrow(() -> new Triangle(functional_righTriangle));

        // Not Three Sides Fail
        assertThrows(IllegalArgumentException.class, () -> new Triangle(square));
        assertThrows(IllegalArgumentException.class, () -> new Triangle(rectangle));
        assertThrows(IllegalArgumentException.class, () -> new Triangle(trapezoid));
        assertThrows(IllegalArgumentException.class, () -> new Triangle(pentagon));

        // Colinear Points Fail
        assertThrows(IllegalArgumentException.class, () -> new Triangle(line));
    }

    @Test
    void testErrorMessage() {
        Exception e = assertThrows(IllegalArgumentException.class,
                () -> new Triangle(List.of(new Point(1, 1), new Point(2, 2))));

        assertEquals("Triangulo:iv", e.getMessage());
    }
}

package tests;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;
import java.util.Locale;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import geometry.Point;
import geometry.shapes.Rectangle;

public class RectangleTest {
    List<Point> functional_rectangle = List.of(new Point(2, 4), new Point(2, -4), new Point(-2, -4), new Point(-2, 4));
    List<Point> functional_square = List.of(new Point(2, 2), new Point(2, -2), new Point(-2, -2), new Point(-2, 2));
    List<Point> unalinned = List.of(new Point(2, 4), new Point(2, 0), new Point(-2, -4), new Point(-2, 0));
    List<Point> trapezoid = List.of(new Point(2, 4), new Point(2, -4), new Point(-2, 0), new Point(-2, 4));
    List<Point> triangle = List.of(new Point(2, 4), new Point(2, -4), new Point(-6, 0));
    List<Point> pentagon = List.of(new Point(2, 5), new Point(3.5, 0), new Point(2, -5), new Point(-2, -4),
            new Point(-2, 4));

    @BeforeAll
    static void setup() {
        Locale.setDefault(Locale.US);
    }

    @Test
    void testContructor() {
        // Succeeds
        assertDoesNotThrow(() -> new Rectangle(functional_rectangle));
        assertDoesNotThrow(() -> new Rectangle(functional_square));

        // Different Diagonals Fail
        assertThrows(IllegalArgumentException.class, () -> new Rectangle(unalinned));

        // Different Paralel Edges Fail
        assertThrows(IllegalArgumentException.class, () -> new Rectangle(trapezoid));

        // Not Four Sides Fail
        assertThrows(IllegalArgumentException.class, () -> new Rectangle(List.of()));
        assertThrows(IllegalArgumentException.class, () -> new Rectangle(triangle));
        assertThrows(IllegalArgumentException.class, () -> new Rectangle(pentagon));
    }
}

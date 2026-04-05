package tests;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;
import java.util.Locale;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import geometry.Point;
import geometry.shapes.Square;

public class SquareTest {
    List<Point> functional_square = List.of(new Point(2, 2), new Point(2, -2), new Point(-2, -2), new Point(-2, 2));
    List<Point> unalinned = List.of(new Point(2, 3), new Point(2, -2), new Point(-2, -5), new Point(-2, 0));
    List<Point> rectangle = List.of(new Point(2, 4), new Point(2, -4), new Point(-2, -4), new Point(-2, 4));
    List<Point> trapezoid = List.of(new Point(2, 4), new Point(2, -4), new Point(-2, 0), new Point(-2, 4));
    List<Point> triangle = List.of(new Point(2, 4), new Point(2, -4), new Point(-6, 0));
    List<Point> pentagon = List.of(new Point(2, 5), new Point(3.5, 0), new Point(2, -5), new Point(-2, -4),
            new Point(-2, 4));

    @BeforeAll
    static void setup() {
        Locale.setDefault(Locale.US);
    }

    @Test
    void testConstructor() {
        // Succeeds
        assertDoesNotThrow(() -> new Square(functional_square));

        // Different Diagonals Fail
        assertThrows(IllegalArgumentException.class, () -> new Square(unalinned));

        // Different Edges Fail
        assertThrows(IllegalArgumentException.class, () -> new Square(rectangle));
        assertThrows(IllegalArgumentException.class, () -> new Square(trapezoid));

        // Not Four Sides Fail
        assertThrows(IllegalArgumentException.class, () -> new Square(List.of()));
        assertThrows(IllegalArgumentException.class, () -> new Square(triangle));
        assertThrows(IllegalArgumentException.class, () -> new Square(pentagon));
    }

    @Test
    void testErrorMessage() {
        Exception e = assertThrows(IllegalArgumentException.class,
                () -> new Square(List.of(new Point(1, 1), new Point(2, 2))));

        assertEquals("Quadrado:iv", e.getMessage());
    }
}

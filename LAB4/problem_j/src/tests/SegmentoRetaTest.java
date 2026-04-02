package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Locale;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import geometry.Point;
import geometry.SegmentoReta;
import geometry.Vector;

public class SegmentoRetaTest {
    @BeforeAll
    static void setup() {
        Locale.setDefault(Locale.US);
    }

    @Test
    void testCheckInvariants() {
        assertThrows(IllegalArgumentException.class, () -> new SegmentoReta(new Point(2, 3), new Vector(0, 0)));
        assertThrows(IllegalArgumentException.class, () -> new SegmentoReta(new Point(2, 2), new Point(2, 2)));
    }

    @Test
    void testIntersect() {
        assertTrue(
                new Point(1, 1).equals(
                        new SegmentoReta(new Point(0, 1), new Point(4, 1)).intersect(Point.ORIGIN, new Vector(2, 2))));
        assertEquals(null,
                new SegmentoReta(new Point(4, 4), new Point(6, 6)).intersect(Point.ORIGIN, new Vector(2, 2)));
    }

    @Test
    void testToString() {
        assertEquals("sr((2.00,2.00);(3.00,3.00))", new SegmentoReta(new Point(2, 2), new Point(3, 3)).toString());
    }

    @Test
    void testToVetor() {
        assertTrue(new Vector(1, 1).equals(new SegmentoReta(new Point(1, 1), new Point(2, 2)).directionVector()));
        assertTrue(new Vector(-1, -1).equals(new SegmentoReta(new Point(2, 2), new Point(1, 1)).directionVector()));
    }
}

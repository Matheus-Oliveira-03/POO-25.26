package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import geometry.Point;
import geometry.SegmentoReta;
import geometry.Vector;

public class VectorTest {
    @Test
    void testAdd() {
        assertTrue(new Vector(2, 2).equals(new Vector(1, 1).add(new Vector(1, 1))));
        assertTrue(new Vector(4, 4).equals(new Vector(1, 1).add(new Vector(3, 3))));
        assertTrue(new Vector(2, 2).equals(new Vector(3, 0).add(new Vector(-1, 2))));
    }

    @Test
    void testCosineSimilarity() {
        assertEquals("0,95", String.format("%.2f", new Vector(1, 3).cosineSimilarity(new Vector(4, 5.5))));
    }

    @Test
    void testInternProduct() {
        assertEquals(15.90, new Vector(2, 1).internProduct(new Vector(6.2, 3.5)));
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
    void testMult() {
        assertTrue(new Vector(5, 5).equals(new Vector(1, 1).mult(5)));
        assertTrue(new Vector(0.5, 0.5).equals(new Vector(2, 2).mult(0.25)));
        assertTrue(new Vector(-1, -1).equals(new Vector(1, 1).mult(-1)));
    }

    @Test
    void testSub() {
        assertTrue(new Vector(2, 2).equals(new Vector(3, 3).sub(new Vector(1, 1))));
        assertTrue(new Vector(4, 4).equals(new Vector(3, 3).sub(new Vector(-1, -1))));
    }

    @Test
    void testCheckInvariant() {
        assertThrows(IllegalArgumentException.class, () -> new Vector(0, 0));
    }

    @Test
    void testToString() {
        assertEquals("[0,50,0,50]", new Vector(0.5, 0.5).toString());
        assertEquals("[1,00,2,00]", new Vector(1, 2).toString());
    }

    @Test
    void testTransform() {
        assertTrue(new Point(2, 2).equals(new Vector(1, 1).transform(new Point(1, 1))));
        assertTrue(new Point(1.5, 0).equals(new Vector(0.5, -1).transform(new Point(1, 1))));
    }
}

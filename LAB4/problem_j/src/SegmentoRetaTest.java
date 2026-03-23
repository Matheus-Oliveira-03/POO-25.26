import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public class SegmentoRetaTest {
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
        // I don't Know what is going on here, why this isn´t working
        assertEquals("sr((2,2);(3,3))", new SegmentoReta(new Point(2, 2), new Point(3, 3)));
    }

    @Test
    void testToVetor() {
        assertTrue(new Vector(1, 1).equals(new SegmentoReta(new Point(1, 1), new Point(2, 2)).directionVector()));
        assertTrue(new Vector(-1, -1).equals(new SegmentoReta(new Point(2, 2), new Point(1, 1)).directionVector()));
    }
}

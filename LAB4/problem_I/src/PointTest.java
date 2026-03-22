import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.jupiter.api.Test;

public class PointTest {
    @Test
    void testCalculator() {
        assertTrue(new Point(5, 5).equals(new Point(1, 1).calculator(5, (m, n) -> m * n)));
        assertTrue(new Point(1, 1).equals(new Point(5, 5).calculator(5, (m, n) -> m / n)));
        assertTrue(new Point(6, 6).equals(new Point(1, 1).calculator(5, (m, n) -> m + n)));
        assertTrue(new Point(-4, -4).equals(new Point(1, 1).calculator(5, (m, n) -> m - n)));
    }

    @Test
    void testCalculator2() {
        assertTrue(new Point(5, 2).equals(new Point(1, 1).calculator(new Point(5, 2), (m, n) -> m * n)));
        assertTrue(new Point(1, 2).equals(new Point(5, 4).calculator(new Point(5, 2), (m, n) -> m / n)));
        assertTrue(new Point(6, 3).equals(new Point(1, 1).calculator(new Point(5, 2), (m, n) -> m + n)));
        assertTrue(new Point(-4, -1).equals(new Point(1, 1).calculator(new Point(5, 2), (m, n) -> m - n)));
    }

    @Test
    void testCompareTo() {
        assertEquals(-1, new Point(1, 1).compareTo(new Point(0.5, 0.5)));
        assertEquals(0, new Point(1, 1).compareTo(new Point(-1, -1)));
        assertEquals(1, new Point(1, 1).compareTo(new Point(2, 2)));
    }

    @Test
    void testDistanceTo() {
        assertTrue(new Point(1, 1).distanceTo(new Point(1, 2)) == 1);
    }

    @Test
    void testEquals() {
        assertTrue(new Point(1, 1).equals(new Point(1, 1)));
        assertFalse(new Point(1, 1).equals(new Point(0, 0)));
    }

    @Test
    void testNorm() {
        assertTrue(new Point(1, 0).norm() == 1);
        assertTrue(new Point(4, 3).norm() == 5);
    }

    @Test
    void testToString() {
        assertEquals("(0,00,0,00)", new Point(0, 0).toString());
        assertEquals("(1,00,2,00)", new Point(1, 2).toString());
    }
}

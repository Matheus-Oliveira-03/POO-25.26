import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public class VetorTest {
    @Test
    void testAdd() {
        assertTrue(new Vetor(2, 2).equals(new Vetor(1, 1).add(new Vetor(1, 1))));
        assertTrue(new Vetor(4, 4).equals(new Vetor(1, 1).add(new Vetor(3, 3))));
        assertTrue(new Vetor(2, 2).equals(new Vetor(3, 0).add(new Vetor(-1, 2))));
    }

    @Test
    void testCosineSimilarity() {
        assertEquals("0,95", String.format("%.2f", new Vetor(1, 3).cosineSimilarity(new Vetor(4, 5.5))));
    }

    @Test
    void testInternProduct() {
        assertEquals(15.90, new Vetor(2, 1).internProduct(new Vetor(6.2, 3.5)));
    }

    @Test
    void testIntersect() {
        assertTrue(
                new Point(1, 1).equals(new SegmentoReta(new Point(0, 1), new Point(4, 1)).intersect(new Vetor(2, 2))));
        assertEquals(null, new SegmentoReta(new Point(4, 4), new Point(6, 6)).intersect(new Vetor(2, 2)));
    }

    @Test
    void testMult() {
        assertTrue(new Vetor(5, 5).equals(new Vetor(1, 1).mult(5)));
        assertTrue(new Vetor(0.5, 0.5).equals(new Vetor(2, 2).mult(0.25)));
        assertTrue(new Vetor(-1, -1).equals(new Vetor(1, 1).mult(-1)));
    }

    @Test
    void testSub() {
        assertTrue(new Vetor(2, 2).equals(new Vetor(3, 3).sub(new Vetor(1, 1))));
        assertTrue(new Vetor(4, 4).equals(new Vetor(3, 3).sub(new Vetor(-1, -1))));
    }

    @Test
    void testCheckInvariant() {
        assertThrows(IllegalArgumentException.class, () -> new Vetor(0, 0));
    }

    @Test
    void testToString() {
        // I don't know what is happening here
        assertEquals("[0,00,0,00]", new Vetor(0, 0).toString());
        assertEquals("[1,00,2,00]", new Vetor(1, 2).toString());
    }

    @Test
    void testTransform() {
        // PS. Right now, Vetor.add() and Vetor.transfrom() are almost identical. For
        // the next interation of the class I could either remove one of them or change
        // the Vetor.transform() method to receive a scalar (as a double s), so it
        // becomes a more complete vector transformation
        assertTrue(new Point(2, 2).equals(new Vetor(1, 1).transform(new Point(1, 1))));
        assertTrue(new Point(1.5, 0).equals(new Vetor(0.5, -1).transform(new Point(1, 1))));
    }
}

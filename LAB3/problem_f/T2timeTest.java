import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class T2timeTest {

    @Test
    public void testConstructorInbounds() {
        // from seconds
        assertEquals(0, new T2time(0).asSeconds());
        assertEquals(3661, new T2time(3661).asSeconds());
        assertEquals(48435, new T2time(48435).asSeconds());

        // from time
        assertEquals(0, new T2time(0, 0, 0).asSeconds());
        assertEquals(3661, new T2time(1, 1, 1).asSeconds());
        assertEquals(48435, new T2time(13, 27, 15).asSeconds());

        // from string(seconds)
        assertEquals(0, new T2time("0").asSeconds());
        assertEquals(3661, new T2time("3661").asSeconds());
        assertEquals(48435, new T2time("48435").asSeconds());

        // from string(time)
        assertEquals(0, new T2time("00:00:00").asSeconds());
        assertEquals(3661, new T2time("01:01:01").asSeconds());
        assertEquals(48435, new T2time("13:27:15").asSeconds());
    }

    @Test
    public void testConstructorOutbounds() {
        // from seconds
        assertThrows(IllegalArgumentException.class, () -> new T2time(-1));
        assertThrows(IllegalArgumentException.class, () -> new T2time(48436));

        // from time
        assertThrows(IllegalArgumentException.class, () -> new T2time(0, 0, -1));
        assertThrows(IllegalArgumentException.class, () -> new T2time(13, 27, 16));

        // from string(seconds)
        assertThrows(IllegalArgumentException.class, () -> new T2time("-1"));
        assertThrows(IllegalArgumentException.class, () -> new T2time("48436"));

        // from string(time)
        assertThrows(IllegalArgumentException.class, () -> new T2time("00:00:0-1"));
        assertThrows(IllegalArgumentException.class, () -> new T2time("-00:00:01"));
        assertThrows(IllegalArgumentException.class, () -> new T2time("13:27:16"));
    }

    @Test
    public void testToString() {
        assertEquals("00:00:00", new T2time(0).toString());
        assertEquals("00:00:01", new T2time(1).toString());
        assertEquals("01:01:01", new T2time(3661).toString());
        assertEquals("13:27:15", new T2time(T2time.T2DAYSECONDS - 1).toString());
    }

    @Test
    public void testAdd() {
        assertEquals(0, (new T2time(0).add(new T2time(0))).asSeconds());
        assertEquals(1, (new T2time(0).add(new T2time(1))).asSeconds());
        assertEquals(0, (new T2time(48435).add(new T2time(1))).asSeconds());
        assertEquals(1, (new T2time(48435).add(new T2time(2))).asSeconds());
    }

    @Test
    public void testIsTime() {
        // positive tests
        assertTrue(T2time.isTime("1"));
        assertTrue(T2time.isTime("00:00:01"));

        // negative tests
        assertFalse(T2time.isTime(null));
        assertFalse(T2time.isTime("-1"));
        assertFalse(T2time.isTime("13:27:16"));
        assertFalse(T2time.isTime("13-27-15"));
        assertFalse(T2time.isTime("1:1:1"));
    }
}
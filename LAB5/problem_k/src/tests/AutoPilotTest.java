package tests;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Locale;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import geometry.Point;
import geometry.Vector;
import main.AutoPilot;

public class AutoPilotTest {
    Point a = new Point(3, 2);
    Point b = new Point(3, 4);
    Vector w = new Vector(0.2, 0.2);

    @BeforeAll
    static void setup() {
        Locale.setDefault(Locale.US);
    }

    @Test
    void testConstructor() {
        // Succeeds
        assertDoesNotThrow(() -> new AutoPilot(a, b));

        // Fails
        assertThrows(IllegalArgumentException.class, () -> new AutoPilot(a, a));
    }

    @Test
    void testSpeed() {
        // Succeeds
        assertDoesNotThrow(() -> new AutoPilot(a, b).speed(w, 5.00));
        assertEquals(new Point(-0.2, 0.2), new AutoPilot(a, b).speed(w, 5));
        assertEquals(new Point(-0.2, -0.6), new AutoPilot(b, a).speed(w, 5));

        // Fails
        assertThrows(IllegalArgumentException.class, () -> new AutoPilot(a, b).speed(w, 0));
        assertThrows(IllegalArgumentException.class, () -> new AutoPilot(a, b).speed(null, 5));
    }

    @Test
    void testTime() {
        // Succeeds
        assertDoesNotThrow(() -> new AutoPilot(a, b).time(0.4));
        assertEquals(5.00, new AutoPilot(a, b).time(0.4), 1e-9);

        // Fails
        assertThrows(IllegalArgumentException.class, () -> new AutoPilot(a, b).time(0));
    }
}

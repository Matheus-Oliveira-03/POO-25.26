package tests;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;
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
        assertDoesNotThrow(() -> new AutoPilot(List.of(a, b), w, 1));

        // Fails
        assertThrows(IllegalArgumentException.class, () -> new AutoPilot(List.of(a, a), w, 1));
    }

    @Test
    void testSpeed() {
        // Succeeds
        assertDoesNotThrow(() -> new AutoPilot(List.of(a, b), w, 0.4).speed());
        assertEquals(new Point(-0.2, 0.2), new AutoPilot(List.of(a, b), w, 0.4).speed());
        assertEquals(new Point(-0.2, -0.6), new AutoPilot(List.of(b, a), w, 0.4).speed());

        // Fails
        assertThrows(IllegalArgumentException.class, () -> new AutoPilot(List.of(a, b), w, 0.4).speed());
        assertThrows(IllegalArgumentException.class, () -> new AutoPilot(List.of(a, b), null, 0.4).speed());
    }

    @Test
    void testTime() {
        // Succeeds
        assertDoesNotThrow(() -> new AutoPilot(List.of(a, b), w, 0.4).time());
        assertEquals(5.00, new AutoPilot(List.of(a, b), w, 0.4).time(), 1e-9);

        // Fails
        assertThrows(IllegalArgumentException.class, () -> new AutoPilot(List.of(a, b), w, 0).time());
    }
}

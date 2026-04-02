package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Locale;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import geometry.Point;
import geometry.Vector;
import main.AutoPilot;

public class AutoPilotTest {
    AutoPilot ap1 = new AutoPilot(new Point(2, 2), new Point(2, 4));
    AutoPilot ap2 = new AutoPilot(new Point(2, 4), new Point(2, 2));
    Vector w = new Vector(1, 1);
    double t1 = ap1.time(w, 0.283);
    double t2 = ap2.time(w, 0.632);

    @BeforeAll
    static void setup() {
        Locale.setDefault(Locale.US);
    }

    @Test
    void testSpeed() {
        assertEquals(-0.2, ap1.speed(w, t1).x(), 0.01);
        assertEquals(0.2, ap1.speed(w, t1).y(), 0.01);
    }

    @Test
    void testTime() {
        assertEquals("5,00", String.format("%.2f", ap1.time(w, 0.283)));
        assertEquals("5,00", String.format("%.2f", ap2.time(w, 0.632)));
    }
}

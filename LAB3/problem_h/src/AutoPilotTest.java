
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public class AutoPilotTest {
    @Test
    void testSpeed() {
        AutoPilot ap1 = new AutoPilot(new Point(2, 2), new Point(2, 4));
        AutoPilot ap2 = new AutoPilot(new Point(2, 4), new Point(2, 2));
        Vetor w = new Vetor(1, 1);
        double t1 = ap1.time(w, 0.283);
        double t2 = ap2.time(w, 0.632);

        IO.println("test 1: " + ap1.speed(w, t1)); // Both prints are giving the correct result, but for some reason the
                                                   // tests fail
        IO.println("test 2: " + ap2.speed(w, t2));
        assertTrue(new Vetor(-0.2, 0.2).equals(ap1.speed(w, t1)));
        assertTrue(new Vetor(-0.2, -0.6).equals(ap2.speed(w, t2)));
    }

    @Test
    void testTime() {
        AutoPilot ap1 = new AutoPilot(new Point(2, 2), new Point(2, 4));
        AutoPilot ap2 = new AutoPilot(new Point(2, 4), new Point(2, 2));
        Vetor w = new Vetor(1, 1);

        assertEquals("5,00", String.format("%.2f", ap1.time(w, 0.283)));
        assertEquals("5,00", String.format("%.2f", ap2.time(w, 0.632)));
    }
}

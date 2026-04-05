package main;

import java.util.Locale;
import java.util.Scanner;
import geometry.*;

/**
 * Represents the interface between the user and the program
 * 
 * @author Matheus Martins Oliveira | a85794
 * @version 3.0 | 27/02/2026
 */
public abstract class Client {
    public static void main() {
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);

        // Get start and finish points
        Point start = new Point(sc.nextDouble(), sc.nextDouble());
        Point finish = new Point(sc.nextDouble(), sc.nextDouble());

        // Get wind speed and direction
        Vector w = new Vector(sc.nextDouble(), sc.nextDouble());

        // Get linear speed
        double s = sc.nextDouble();
        sc.close();

        // Setup auto pilot and compute:
        // i) desired time to reach the finish point
        // ii) vectorial speed required
        AutoPilot ap = new AutoPilot(start, finish);
        double t = ap.time(s);
        System.out.println(String.format("%.2f", t));
        System.out.println(ap.speed(w, t));
    }
}

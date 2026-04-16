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

        String[] data = new String(sc.nextLine()).split("\s");
        Vector windSpeed = new Vector(sc.nextDouble(), sc.nextDouble());
        double linearSpeed = sc.nextDouble();
        double currentTime = sc.nextDouble();

        AutoPilot ap = new AutoPilot(data, windSpeed, linearSpeed);
        double totalTime = ap.time();

        System.out.println(String.format("%.2f", ap.length()));
        System.out.println(String.format("%.2f", totalTime));
        System.out.println(ap.position(currentTime));
        System.out.println(Helper.listToString(ap.speed()));

        sc.close();
    }
}

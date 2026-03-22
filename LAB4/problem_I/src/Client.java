import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

/**
 * Represents the interface between the user and the program
 * 
 * @author Matheus Martins Oliveira | a85794
 * @version 3.0 | 27/02/2026
 */
public class Client {
    public static void main() {
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);

        ArrayList<Point> routePoints = pointReader(sc.nextLine().split("\\s"));
        ArrayList<Point> segmentPoints = pointReader(sc.nextLine().split("\\s"));

        Route r = new Route(routePoints);
        SegmentoReta sr = new SegmentoReta(segmentPoints.get(0), segmentPoints.get(1));

        System.out.println(String.format("%.2f", r.length()));
        System.out.println(Route.toString(r.intersect(sr)));

        sc.close();
    }

    private static ArrayList<Point> pointReader(String[] doubles) {
        ArrayList<Point> points = new ArrayList<Point>();

        int i = 0;
        while (i < doubles.length - 1) {
            points.add(new Point(Double.parseDouble(doubles[i]), Double.parseDouble(doubles[++i])));
            i++;
        }

        return points;
    }
}

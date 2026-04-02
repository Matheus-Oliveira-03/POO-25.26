package main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;
import java.util.Scanner;

import geometry.shapes.*;
import geometry.*;

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
        String[] shapeData = sc.nextLine().split("\\s");

        Route r = new Route(routePoints);
        Shape s = shapeReader(shapeData);

        System.out.println(Helper.pointsToString(r.intersect(s)));
        sc.close();
    }

    private static ArrayList<Point> pointReader(String[] doubles) {
        ArrayList<Point> points = new ArrayList<Point>();
        if (doubles.length % 2 != 0)
            throw new IllegalArgumentException();

        int i = 0;
        while (i < doubles.length - 1) {
            points.add(new Point(Double.parseDouble(doubles[i]), Double.parseDouble(doubles[++i])));
            i++;
        }

        return points;
    }

    private static Shape shapeReader(String[] data) {
        String type = data[0].toUpperCase();
        String[] doubles = Arrays.copyOfRange(data, 1, data.length);
        Shape shape;

        switch (type) {
            case "P":
                shape = new Poligon(pointReader(doubles));
                break;
            case "S":
                shape = new Square(pointReader(doubles));
                break;
            case "R":
                shape = new Rectangle(pointReader(doubles));
                break;
            case "T":
                shape = new Triangle(pointReader(doubles));
                break;
            case "C":
                Point center = new Point(Double.parseDouble(doubles[0]), Double.parseDouble(doubles[1]));
                double radius = Double.parseDouble(doubles[2]);
                shape = new Circle(center, radius);
                break;
            default:
                throw new IllegalArgumentException("type: " + type);
        }

        return shape;
    }
}

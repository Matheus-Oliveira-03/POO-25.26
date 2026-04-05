package main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import geometry.Point;
import geometry.shapes.Circle;
import geometry.shapes.Poligon;
import geometry.shapes.Rectangle;
import geometry.shapes.Shape;
import geometry.shapes.Square;
import geometry.shapes.Triangle;

public abstract class Helper {
    public static double tolerance = 1e-9;
    public static Point origin = new Point(0, 0);

    /**
     * Prints and error message on the output and exits the program. <br>
     * <hr>
     * 
     * @param ERR_MSG : a {@link String} representing the error message.
     */
    public static void ivExit(String ERR_MSG) {
        throw new IllegalArgumentException(ERR_MSG);
        // System.out.println(ERR_MSG);
        // System.exit(0);
    }

    /**
     * Creates a generic point.
     * <hr>
     * 
     * @implNote Used for testings only.
     * @param s : a {@code double} representing both the point's x and y axis
     *          values.
     * @return a {@link Point} with values (x: s, y: s).
     * @see Point#Point(double, double)
     */
    public static Point genericPoint(double s) {
        return new Point(s, s);
    }

    /**
     * Creates a generic circle.
     * <hr>
     * 
     * @implNote Used for testings only.
     * @param s : a {@code double} representing both the x and y values for the
     *          center of the {@link Circle} as well as it's radius. If
     *          {@code s < 1}, the value for the radius will be 1 instead.
     * @return a {@link Circle} with center at (x: s, y: s) and radius = s.
     * @see Circle#Circle(Point, double)
     */
    public static Circle genericCircle(double s) {
        return new Circle(new Point(s, s), s < 1e-9 ? 1 : s);
    }

    /**
     * Checks if a number is zero within a default tolerance.
     * <hr>
     * 
     * @param n : a {@code double} representing the number to be verified.
     * @return
     *         <ul>
     *         <li>{@code true} if the number's absolute value is less than the
     *         tolerance.
     *         <li>{@code false} if it's greater than the tolerance.
     *         </ul>
     * 
     * @see Math#abs(double)
     */
    public static boolean isZero(double n) {
        return Math.abs(n) < tolerance;
    }

    /**
     * Verifies whether two doubles are equal to each other, within a default
     * tolerance.
     * <hr>
     * 
     * @param a : a {@code double}.
     * @param b : a {@code double}.
     * @return
     *         <ul>
     *         <li>{@code true} if both numbers are equal to each other.
     *         <li>{@code false} if they are different.
     *         </ul>
     * @see #isZero(double)
     */
    public static boolean tolerantEquals(double a, double b) {
        return isZero(a - b);
    }

    /**
     * Converts a list of points into a string.
     * <hr>
     * 
     * @param points : a {@link List} of {@link Point}, which will be converted to
     *               String.
     * @return a {@link String} with the converted list in the format "(x, y) (x',
     *         y') (x", y") ...".
     */
    public static String pointsToString(List<Point> points) {
        if (points == null)
            return null;

        String str = "";
        for (Point point : points) {
            if (point == points.getLast())
                str += point;
            else
                str += point + " ";
        }

        return str;
    }

    /**
     * Reads a array of Strings, representing doubles, and converts it into a list
     * of points.
     * <hr>
     * 
     * @param doubles : a array of {@link String} where each pair of Strings
     *                represents a point's axis values.
     * @return a {@link List} of {@link Point} created from the doubles list.
     * 
     * @throws IllegalArgumentException in case the list does not have a even amount
     *                                  of elements.
     * @throws NumberFormatException    in case any of the elements is not a
     *                                  parsable double.
     * 
     * @see Double#parseDouble(String)
     * 
     * @pre-conditions The array must have a even number of elements.
     * @pre-conditions Each element in the array must be a parsable double.
     */
    public static ArrayList<Point> pointReader(String[] doubles) {
        if (doubles.length % 2 != 0)
            throw new IllegalArgumentException();

        ArrayList<Point> points = new ArrayList<Point>();

        int i = 0;
        while (i < doubles.length - 1) {
            points.add(new Point(Double.parseDouble(doubles[i]), Double.parseDouble(doubles[++i])));
            i++;
        }

        return points;
    }

    /**
     * Reads a array of Strings and converts it into a shape.
     * <hr>
     * 
     * @param data : a array of {@link String} representig both the type of shape to
     *             be created and the values and points related to that shape. <br>
     *             The first character must be one of the following:
     *             <ul>
     *             <li>{@code P}, is used to create a {@link Poligon}
     *             <li>{@code S}, is used to create a {@link Square}
     *             <li>{@code R}, is used to create a {@link Rectangle}
     *             <li>{@code T}, is used to create a {@link Triangle}
     *             <li>{@code C}, is used to create a {@link Circle}
     *             </ul>
     *             In case the desired shape is a Circle, the rest of the array must
     *             contain only two {@code double}, representing the circle's center
     *             {@link Point} values, and one {@code double} representing the
     *             circle's radius. The array can't contain more than those three
     *             {@code double}. <br>
     *             In any other case, the array must contain an even number of
     *             parsable {@code double}, each pair of those represents a
     *             {@link Point}'s axis value.
     * 
     * @return a {@link Shape} created from the given data.
     * 
     * @throws IllegalArgumentException In case data == null, or data.length < 4 or
     *                                  the given data violates any of the
     *                                  previously mentioned requirements.
     * @throws NumberFormatException    In case any element of the array, other than
     *                                  the first, is not a parsable double.
     * 
     * @pre-conditions Each element in the array, except for the first, must be a
     *                 parsable double.
     */
    public static Shape shapeReader(String[] data) {
        if (data == null || data.length < 4)
            throw new IllegalArgumentException();

        String type = data[0].toUpperCase();
        String[] doubles = Arrays.copyOfRange(data, 1, data.length);
        Shape shape;

        switch (type) {
            case "P":
                if (doubles.length % 2 != 0)
                    throw new IllegalArgumentException();

                shape = new Poligon(pointReader(doubles));
                break;
            case "S":
                if (doubles.length % 2 != 0)
                    throw new IllegalArgumentException();

                shape = new Square(pointReader(doubles));
                break;
            case "R":
                if (doubles.length % 2 != 0)
                    throw new IllegalArgumentException();

                shape = new Rectangle(pointReader(doubles));
                break;
            case "T":
                if (doubles.length % 2 != 0)
                    throw new IllegalArgumentException();

                shape = new Triangle(pointReader(doubles));
                break;
            case "C":
                if (doubles.length != 3)
                    throw new IllegalArgumentException();

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

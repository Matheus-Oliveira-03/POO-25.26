package problem_d;

public class Ponto {
    public static Ponto ORIGIN = new Ponto(0, 0);
    private double x;
    private double y;

    public Ponto(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double x() {
        return this.x;
    }

    public double y() {
        return this.y;
    }

    public String toString() {
        return this.toString("%.2f");
    }

    public String toString(String format) {
        format = format == null ? "%.2f" : format; // default value
        return "(" + String.format(format, this.x) + "," + String.format(format, this.y) + ")";
    }

    public double distanceToOrigin() {
        return distance(this, ORIGIN);
    }

    public boolean equals(Ponto b) {
        return equals(this, b);
    }

    public int compareTo(Ponto a) {
        return compare(this, a);
    }

    public static double distance(Ponto a, Ponto b) {
        return Math.sqrt(Math.pow(a.x() - b.x(), 2) + Math.pow(a.y() - b.y(), 2));
    }

    public static int compare(Ponto a, Ponto b) {
        double disA = a.distanceToOrigin();
        double disB = b.distanceToOrigin();
        return Math.abs(disA - disB) < 1e-9 ? 0 : disB < disA ? 1 : -1;
    }

    public static boolean equals(Ponto a, Ponto b) {
        double xx = Math.abs(a.x() - b.x());
        double yy = Math.abs(a.y() - b.y());

        return xx < 1e-9 && yy < 1e-9;
    }
}

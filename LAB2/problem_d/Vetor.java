package problem_d;

import java.lang.Math;

public class Vetor {
    private double x;
    private double y;

    Vetor(double x, double y) {
        set(x, y);
    }

    Vetor(Ponto p) {
        set(p.x(), p.y());
    }

    private void set(Double x, Double y) {
        this.x = x;
        this.y = y;
        checkInvariable();
    }

    public double x() {
        return this.x;
    }

    public double y() {
        return this.y;
    }

    public String toString() {
        return "[" + String.format("%.2f", this.x) + ", " + String.format("%.2f", this.x) + "]";
    }

    public double norm() {
        return Math.sqrt(this.x * this.x + this.y * this.y);
    }

    public Ponto transform(Ponto p) {
        return new Ponto(p.x() + this.x, p.y() + this.y);
    }

    public void checkInvariable() {
        assert this.norm() != 0 : "Vector:iv";
    }

    public static double internProduct(Vetor v, Vetor u) {
        return v.x() * u.x() + v.y() * u.y();
    }

    public static double cossineSimilarity(Vetor v, Vetor u) {
        return Vetor.internProduct(v, u) / (v.norm() * u.norm());
    }
}
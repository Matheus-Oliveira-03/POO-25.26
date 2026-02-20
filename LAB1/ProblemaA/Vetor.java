import java.lang.Math;

public class Vetor {
    private float x;
    private float y;

    Vetor(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public void set(Float x, Float y) {
        this.x = x == null ? this.x : x;
        this.y = y == null ? this.y : y;
    }

    public void x(Float x) {
        set(x, null);
    }

    public void y(Float y) {
        set(null, y);
    }

    public float x() {
        return this.x;
    }

    public float y() {
        return this.y;
    }

    public String toString() {
        return "(" + x + ", " + y + ")";
    }

    public double norm() {
        return Math.sqrt(x * x + y * y);
    }

    public void checkNorm() {
        if (this.norm() == 0) {
            System.out.println("iv");
            System.exit(0);
        }
    }

    public static double internProduct(Vetor v, Vetor u) {
        return v.x() * u.x() + v.y() * u.y();
    }

    public static double cossineSimilarity(Vetor v, Vetor u) {
        return Vetor.internProduct(v, u) / (v.norm() * u.norm());
    }
}
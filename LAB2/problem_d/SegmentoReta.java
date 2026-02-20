package problem_d;

public class SegmentoReta {
    private Ponto a;
    private Ponto b;

    public SegmentoReta(Ponto p, Vetor v) {
        Ponto q = v.transform(p);
        this.a = p.compareTo(q) == 1 ? q : p;
        this.b = p.compareTo(q) == 1 ? p : q;

        checkInvariable();
    }

    public String toString() {
        return "sr(" + this.a.toString() + ";" + this.b.toString() + ")";
    }

    private void checkInvariable() {
        assert !Ponto.equals(a, b) : "SegmentoReta:iv";
    }
}

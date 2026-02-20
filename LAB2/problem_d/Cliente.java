package problem_d;

import java.util.Locale;
import java.util.Scanner;

public class Cliente {
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);

        Scanner scanner = new Scanner(System.in);
        Ponto p = new Ponto(scanner.nextFloat(), scanner.nextFloat());
        Vetor v = new Vetor(scanner.nextFloat(), scanner.nextFloat());
        SegmentoReta sr = new SegmentoReta(p, v);
        System.out.println(sr.toString());
        scanner.close();
    }
}

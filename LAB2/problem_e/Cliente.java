import java.util.Locale;
import java.util.Scanner;

/**
 * Represents the interface between the user and the program
 * 
 * @author Matheus Martins Oliveira | a85794
 * @version 1.0 | 27/02/2026
 */
public class ClienteE {
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);

        Scanner scanner = new Scanner(System.in);
        Ponto p = new Ponto(scanner.nextDouble(), scanner.nextDouble());
        Ponto q = new Ponto(scanner.nextDouble(), scanner.nextDouble());
        Vetor v = new Vetor(scanner.nextDouble(), scanner.nextDouble());

        SegmentoReta sr = new SegmentoReta(p, q);
        System.out.println(sr.intersect(v));

        scanner.close();
    }
}

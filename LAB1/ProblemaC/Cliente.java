import java.util.Locale;
import java.util.Scanner;

public class Cliente {
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        Scanner scanner = new Scanner(System.in);
        Vetor v = new Vetor(scanner.nextFloat(), scanner.nextFloat());
        Vetor u = new Vetor(scanner.nextFloat(), scanner.nextFloat());
        System.out.println(String.format("%.2f", Vetor.cossineSimilarity(v, u)));
        scanner.close();
    }
}

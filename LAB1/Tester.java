import java.util.Scanner;

public class Tester {
    public static void main(String[] args) {
        System.out.println("===| Program Started |===");
        Scanner scanner = new Scanner(System.in);
        Vetor v, u;

        while (true) {
            System.out.println("Choose mode:\nA-Vector Norm\nB-Intern Product\nC-Cossine Similarity\nE-Exit");
            String option = scanner.nextLine().toLowerCase();

            if (option.compareTo("a") == 0) {
                System.out.println("Insert v coordinates:");
                v = new Vetor(scanner.nextFloat(), scanner.nextFloat());

                System.out.println("v = " + v.toString() + ", ||v|| = " + String.format("%.2f", v.norm()));
            } else if (option.compareTo("b") == 0) {
                System.out.println("Insert v coordinates:");
                v = new Vetor(scanner.nextFloat(), scanner.nextFloat());
                v.checkNorm();

                System.out.println("Insert u coordinates:");
                u = new Vetor(scanner.nextFloat(), scanner.nextFloat());
                u.checkNorm();

                System.out.println("v = " + v.toString() + "\nu = " + u.toString() + "\nv*u = "
                        + String.format("%.2f", Vetor.internProduct(v, u)));
            } else if (option.compareTo("c") == 0) {
                System.out.println("Insert v coordinates:");
                v = new Vetor(scanner.nextFloat(), scanner.nextFloat());
                v.checkNorm();

                System.out.println("Insert u coordinates:");
                u = new Vetor(scanner.nextFloat(), scanner.nextFloat());
                u.checkNorm();

                System.out.println("v = " + v.toString() + "\nu = " + u.toString() + "\nv*u = "
                        + String.format("%.2f", Vetor.cossineSimilarity(v, u)));
            } else if (option.compareTo("e") == 0) {
                System.out.println("===| Ending Program |===");
                break;
            } else {
                System.out.println(option + ", does this looks valid to you?");
                continue;
            }
        }
        scanner.close();
    }
}

import java.util.Scanner;

public class Client {
    static final String TOTIME = "asTime";
    static final String TOSECS = "asSeconds";
    static final String ADDTIME = "add";

    public static void main() {
        Scanner scanner = new Scanner(System.in);
        String aTime = scanner.nextLine();
        String operator = scanner.nextLine();
        String output = "";
        T2time receiver = new T2time(aTime);

        switch (operator) {
            case TOTIME:
                output = receiver.toString();
                break;
            case TOSECS:
                output = "" + Integer.toString(receiver.asSeconds());
                break;
            case ADDTIME:
                String bTime = scanner.nextLine();
                output = receiver.add(new T2time(bTime)).toString();
                break;
            default:
                // seems right to have a default statement...
        }

        IO.println(output);
        scanner.close();
    }
}

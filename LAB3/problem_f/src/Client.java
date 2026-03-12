package src;

import java.util.Scanner;

public class Client {
    static final String TOTIME = "asTime";
    static final String TOSECS = "asSeconds";
    static final String ADDTIME = "add";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String aString = sc.nextLine();
        String operator = sc.nextLine();
        String output = "";
        T2time receiver;
        switch (operator) {
            case TOTIME:
                receiver = new T2time(Integer.parseInt(aString));
                output = receiver.toString();
                System.out.println(output);
                break;
            case TOSECS:
                receiver = new T2time(aString);
                output = "" + receiver.asSeconds();
                System.out.println(output);
                break;
            case ADDTIME:
                T2time argument;
                receiver = new T2time(aString);
                String bString = sc.nextLine();
                if (T2time.isTime(bString))
                    argument = new T2time(bString);
                else
                    argument = new T2time(Integer.parseInt(bString));
                output = receiver.add(argument).toString();
                System.out.println(output);
                break;
            default:
                System.out.println(output);
        }

        sc.close();
        System.out.println("error");
    }
}

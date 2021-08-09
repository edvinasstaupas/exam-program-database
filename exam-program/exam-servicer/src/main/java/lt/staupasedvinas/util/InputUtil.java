package lt.staupasedvinas.util;

import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;

import static lt.staupasedvinas.util.PrintUtil.printBadInput;

public class InputUtil {
    private static final Scanner scanner = new Scanner(System.in);

    public static char getAnswerInput() {
        Scanner scanner = new Scanner(System.in);
        String answerInput = "";
        while (true) {
            answerInput = scanner.next();
            if (answerInput.equals("a") || answerInput.equals("b") || answerInput.equals("c"))
                return answerInput.toCharArray()[0];
            else
                printBadInput();
        }
    }

    public static Long getLongInput() {
        Long longInput = null;
        boolean badOrNoInput = true;
        while (badOrNoInput) {
            try {
                longInput = scanner.nextLong();
                badOrNoInput = false;
            } catch (InputMismatchException e) {
                printBadInput();
            }
        }
        return longInput;
    }

    public static boolean getBooleanInput() {
        Boolean b = null;
        while (b == null) {
            String answer = scanner.next();
            if (answer.toLowerCase(Locale.ROOT).equals("yes"))
                b = true;
            else if (answer.toLowerCase(Locale.ROOT).equals("no"))
                b = false;
            else
                printBadInput();
        }
        return b;
    }

    public static String getStringInput() {
        return scanner.next();
    }

    public static char getCharInput() {
        return InputUtil.getAnswerInput();
    }
}

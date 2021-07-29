package lt.staupasedvinas.util;

public class PrintUtil {
    public static void print(String string) {
        System.out.println(string);
    }

    public static void printBadInput() {
        printErr("Bad input!");
    }

    public static void printErr(String s) {
        print(s);
    }
}

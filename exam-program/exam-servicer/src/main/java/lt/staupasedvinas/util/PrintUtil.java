package lt.staupasedvinas.util;

public class PrintUtil {
    public static void print(String string) {
        System.out.println(string);
    }

    private void printLong(Long l) {
        print(String.valueOf(l));
    }

    public static void printBadInput() {
        printErr("Bad input!");
    }

    public static void printErr(String s) {
        print(s);
    }
}

package ua.khpi.azarenko.task01;


import java.util.Map;
import java.util.TreeMap;


public class ExcelStyleColumns {
    private static Map<Character, Integer> alphabet = new TreeMap<>();

    static {
        char symbol = 'A';
        for (int i = 0; i < 26; i++) {
            alphabet.put(symbol, i + 1);
            symbol++;
        }
    }

    private static int chars2digits(String number) {
        char[] chars = number.toCharArray();
        int result = 0;
        for (int i = chars.length - 1, exp = 0; i >= 0; i--, exp++ ) {
            result += alphabet.get(chars[i]) * Math.pow(26, exp);
        }
        return result;
    }

    private static String digits2chars(int number) {
        StringBuilder sb = new StringBuilder();
        int quotient = number / 26;
        int modulo = number % 26;
        if (modulo == 0) {
            sb.append('Z');
            quotient = (number - 1) / 26;
        } else {
            sb.append(findSymbol(modulo));
        }
        if (quotient <= 26) {
            sb.append(findSymbol(quotient));
        } else {
            quotient = quotient / 26;
            modulo = quotient % 26;
            sb.append(findSymbol(modulo));
            sb.append(findSymbol(quotient));
        }
        char[] obtainedChars = sb.toString().toCharArray();
        StringBuilder reverseSb = new StringBuilder();
        for (int i = obtainedChars.length - 1; i >= 0; i--) {
            reverseSb.append(obtainedChars[i]);
        }
        return reverseSb.toString();
    }

    private static char findSymbol(int value) {
        char symbol = '\u0000';
        for (Map.Entry<Character, Integer> pair: alphabet.entrySet()) {
            if (pair.getValue().equals(value)) {
                symbol = pair.getKey();
            }
        }
        return symbol;
    }

    private static String rightColumn(String number) {
        int digitView = chars2digits(number);
        digitView += 1;
        return digits2chars(digitView);
    }

    public static void main(String[] args) {
        if (args.length != 3) {
            System.out.println("You should specify 3 program parameters");
        } else {
            System.out.println(args[0] + " ==> " + chars2digits(args[0]));
            System.out.println(args[1] + " ==> " + digits2chars(Integer.parseInt(args[1])));
            System.out.println("Next column of " + args[2] + " ==> " + rightColumn(args[2]));
        }
    }
}

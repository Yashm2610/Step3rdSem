package Week2.PraticeProblems;
import java.util.*;

public class AsciiOps {
    static void classify(char c) {
        if (Character.isUpperCase(c)) System.out.println(c+" -> Uppercase");
        else if (Character.isLowerCase(c)) System.out.println(c+" -> Lowercase");
        else if (Character.isDigit(c)) System.out.println(c+" -> Digit");
        else System.out.println(c+" -> Special");
    }

    static String caesarCipher(String s, int shift) {
        StringBuilder sb = new StringBuilder();
        for (char c: s.toCharArray()) {
            if (Character.isLetter(c)) {
                char base = Character.isUpperCase(c)? 'A':'a';
                sb.append((char)( (c-base+shift)%26 + base ));
            } else sb.append(c);
        }
        return sb.toString();
    }

    static int[] toAsciiArray(String s) {
        int[] arr = new int[s.length()];
        for (int i=0;i<s.length();i++) arr[i] = (int)s.charAt(i);
        return arr;
    }

    static String fromAsciiArray(int[] arr) {
        StringBuilder sb = new StringBuilder();
        for (int x: arr) sb.append((char)x);
        return sb.toString();
    }

    public static void main(String[] args) {
        String text="Java123!";
        for (char c: text.toCharArray()) {
            System.out.println(c+" -> ASCII "+(int)c);
            classify(c);
        }
        System.out.println("Caesar +3: " + caesarCipher(text,3));
        int[] arr = toAsciiArray(text);
        System.out.println("Back from ASCII: " + fromAsciiArray(arr));
    }
}

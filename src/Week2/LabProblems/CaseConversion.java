package Week2.LabProblems;
import java.util.*;

public class CaseConversion {
    static char toUpper(char c) {
        if (c >= 'a' && c <= 'z') return (char)(c-32);
        return c;
    }
    static char toLower(char c) {
        if (c >= 'A' && c <= 'Z') return (char)(c+32);
        return c;
    }
    static String toTitleCase(String text) {
        StringBuilder sb = new StringBuilder();
        boolean newWord = true;
        for (char c : text.toCharArray()) {
            if (Character.isWhitespace(c)) {
                newWord = true;
                sb.append(c);
            } else {
                if (newWord) {
                    sb.append(toUpper(c));
                    newWord = false;
                } else sb.append(toLower(c));
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.print("Enter text: ");
        String text=sc.nextLine();

        String upper="", lower="";
        for(char c:text.toCharArray()){ upper+=toUpper(c); lower+=toLower(c); }

        System.out.println("Original: " + text);
        System.out.println("Uppercase (manual): " + upper);
        System.out.println("Lowercase (manual): " + lower);
        System.out.println("Title case (manual): " + toTitleCase(text));
        System.out.println("Built-in Upper: " + text.toUpperCase());
        System.out.println("Built-in Lower: " + text.toLowerCase());
    }
}

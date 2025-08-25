package Week2.LabProblems;
import java.util.*;

public class FindReplace {
    static List<Integer> findOccurrences(String text, String pattern) {
        List<Integer> list = new ArrayList<>();
        int index = text.indexOf(pattern);
        while (index != -1) {
            list.add(index);
            index = text.indexOf(pattern, index + 1);
        }
        return list;
    }

    static String manualReplace(String text, String pattern, String replacement) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < text.length(); ) {
            if (i <= text.length()-pattern.length() && text.substring(i, i+pattern.length()).equals(pattern)) {
                sb.append(replacement);
                i += pattern.length();
            } else {
                sb.append(text.charAt(i));
                i++;
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter text: ");
        String text = sc.nextLine();
        System.out.print("Enter substring to replace: ");
        String pattern = sc.nextLine();
        System.out.print("Enter replacement: ");
        String replacement = sc.nextLine();

        String manual = manualReplace(text, pattern, replacement);
        String builtin = text.replace(pattern, replacement);

        System.out.println("Manual Replace: " + manual);
        System.out.println("Built-in Replace: " + builtin);
        System.out.println("Match? " + manual.equals(builtin));
    }
}

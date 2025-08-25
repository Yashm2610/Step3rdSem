package Week1.LabProblems;
import java.util.Scanner;

class CustomTrim {
    public static String trimSpaces(String text) {
        int start = 0, end = text.length() - 1;
        while (start <= end && text.charAt(start) == ' ') start++;
        while (end >= start && text.charAt(end) == ' ') end--;
        StringBuilder sb = new StringBuilder();
        for (int i = start; i <= end; i++) sb.append(text.charAt(i));
        return sb.toString();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter text with spaces: ");
        String text = sc.nextLine();

        String manual = trimSpaces(text);
        String builtin = text.trim();

        System.out.println("Manual Trim: [" + manual + "]");
        System.out.println("Built-in Trim: [" + builtin + "]");
    }
}
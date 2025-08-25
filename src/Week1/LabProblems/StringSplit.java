package Week1.LabProblems;
import java.util.Scanner;

class StringSplit {
    public static String[] customSplit(String text) {
        int spaceCount = 1;
        for (int i = 0; i < text.length(); i++)
            if (text.charAt(i) == ' ') spaceCount++;

        String[] words = new String[spaceCount];
        int wordIndex = 0;
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) == ' ') {
                words[wordIndex++] = sb.toString();
                sb.setLength(0);
            } else {
                sb.append(text.charAt(i));
            }
        }
        words[wordIndex] = sb.toString();
        return words;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter text: ");
        String text = sc.nextLine();

        String[] customWords = customSplit(text);
        String[] builtInWords = text.split(" ");

        System.out.println("Custom Split:");
        for (String w : customWords) System.out.println(w);

        System.out.println("Built-in Split:");
        for (String w : builtInWords) System.out.println(w);
    }
}
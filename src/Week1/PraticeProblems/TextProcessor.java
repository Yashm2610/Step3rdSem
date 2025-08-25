package Week1.PraticeProblems;
import java.util.*;

public class TextProcessor {

    public static String cleanInput(String input) {
        input = input.trim().replaceAll("\\s+", " ");
        String[] words = input.split(" ");
        StringBuilder sb = new StringBuilder();
        for (String w : words) {
            if (!w.isEmpty()) {
                sb.append(Character.toUpperCase(w.charAt(0)))
                        .append(w.substring(1).toLowerCase())
                        .append(" ");
            }
        }
        return sb.toString().trim();
    }

    public static void analyzeText(String text) {
        String[] words = text.split("\\s+");
        int wordCount = words.length;
        int charCount = text.replace(" ", "").length();
        int sentenceCount = text.split("[.!?]").length;

        String longestWord = "";
        for (String w : words) {
            if (w.length() > longestWord.length()) longestWord = w;
        }

        Map<Character, Integer> freq = new HashMap<>();
        for (char c : text.toCharArray()) {
            if (Character.isLetter(c)) {
                freq.put(c, freq.getOrDefault(c, 0) + 1);
            }
        }
        char mostCommon = ' ';
        int max = 0;
        for (Map.Entry<Character, Integer> e : freq.entrySet()) {
            if (e.getValue() > max) {
                max = e.getValue();
                mostCommon = e.getKey();
            }
        }

        System.out.println("\n=== TEXT ANALYSIS ===");
        System.out.println("Words: " + wordCount);
        System.out.println("Characters (no spaces): " + charCount);
        System.out.println("Sentences: " + sentenceCount);
        System.out.println("Longest Word: " + longestWord);
        System.out.println("Most Common Character: " + mostCommon);
    }

    public static String[] getWordsSorted(String text) {
        String cleaned = text.replaceAll("[^a-zA-Z ]", "").toLowerCase();
        String[] words = cleaned.split("\\s+");
        Arrays.sort(words);
        return words;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("=== TEXT PROCESSOR ===");
        System.out.print("Enter a paragraph: ");
        String input = scanner.nextLine();

        String cleaned = cleanInput(input);
        analyzeText(cleaned);

        String[] sorted = getWordsSorted(cleaned);
        System.out.println("\nWords in Alphabetical Order:");
        for (String w : sorted) {
            System.out.print(w + " ");
        }

        System.out.print("\n\nSearch a word: ");
        String search = scanner.nextLine().toLowerCase();
        boolean found = Arrays.asList(sorted).contains(search);
        System.out.println("Word Found: " + found);

        scanner.close();
    }
}
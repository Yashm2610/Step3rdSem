package Week2.PraticeProblems;
import java.util.*;

public class StringManipulation {
    static String removePunctuation(String s) {
        return s.replaceAll("[^a-zA-Z0-9 ]", "");
    }

    static String capitalizeWords(String s) {
        String[] words = s.split("\\s+");
        StringBuilder sb = new StringBuilder();
        for (String w: words) {
            if (w.length() > 0) sb.append(Character.toUpperCase(w.charAt(0))).append(w.substring(1)).append(" ");
        }
        return sb.toString().trim();
    }

    static String reverseWords(String s) {
        String[] words = s.split("\\s+");
        StringBuilder sb = new StringBuilder();
        for (int i = words.length-1; i >= 0; i--) sb.append(words[i]).append(" ");
        return sb.toString().trim();
    }

    static void wordFrequency(String s) {
        Map<String,Integer> freq = new HashMap<>();
        for (String w: s.toLowerCase().split("\\s+")) {
            freq.put(w, freq.getOrDefault(w,0)+1);
        }
        System.out.println("Word frequencies: " + freq);
    }

    public static void main(String[] args) {
        String input = "Hello, world! Java is fun. Java is powerful.";
        System.out.println("Trimmed: " + input.trim());
        System.out.println("Replace 'Java' -> 'Python': " + input.replace("Java","Python"));
        System.out.println("Remove punctuation: " + removePunctuation(input));
        System.out.println("Capitalize: " + capitalizeWords(input));
        System.out.println("Reversed words: " + reverseWords(input));
        wordFrequency(removePunctuation(input));
    }
}
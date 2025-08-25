package Week2.PraticeProblems;
import java.util.*;

public class StringBasics {
    static int countVowels(String s) {
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = Character.toLowerCase(s.charAt(i));
            if ("aeiou".indexOf(c) >= 0) count++;
        }
        return count;
    }

    static List<Integer> findAllOccurrences(String s, char target) {
        List<Integer> positions = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == target) positions.add(i);
        }
        return positions;
    }

    public static void main(String[] args) {
        String str = "   Java String Practice   ";
        System.out.println("Original: '" + str + "'");
        System.out.println("Length: " + str.length());
        System.out.println("Trimmed: '" + str.trim() + "'");
        System.out.println("charAt(2): " + str.charAt(2));
        System.out.println("Substring(3,8): " + str.substring(3,8));
        System.out.println("IndexOf('a'): " + str.indexOf('a'));
        System.out.println("Contains 'String'? " + str.contains("String"));
        System.out.println("StartsWith 'Ja'? " + str.startsWith("Ja"));
        System.out.println("EndsWith 'ice'? " + str.endsWith("ice"));
        System.out.println("Upper: " + str.toUpperCase());
        System.out.println("Lower: " + str.toLowerCase());

        System.out.println("Vowels count: " + countVowels(str));
        System.out.println("All positions of 'a': " + findAllOccurrences(str,'a'));
    }
}

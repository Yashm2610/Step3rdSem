package Week1.AssigmentProblem;

import java.util.*;

class AnagramCheck {
    public static boolean isAnagram(String text1, String text2) {
        if (text1.length() != text2.length()) return false;

        int[] freq1 = new int[256];
        int[] freq2 = new int[256];

        for (char c : text1.toCharArray()) freq1[c]++;
        for (char c : text2.toCharArray()) freq2[c]++;

        return Arrays.equals(freq1, freq2);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter first text: ");
        String text1 = sc.nextLine();
        System.out.print("Enter second text: ");
        String text2 = sc.nextLine();

        System.out.println("Are Anagrams? " + isAnagram(text1, text2));
    }
}

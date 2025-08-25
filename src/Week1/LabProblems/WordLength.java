package Week1.LabProblems;

import java.util.Scanner;

class WordLength {
    public static String[] customSplit(String text) {
        return text.split(" "); // reusing logic for simplicity
    }

    public static int getLength(String word) {
        int count = 0;
        try {
            while (true) {
                word.charAt(count);
                count++;
            }
        } catch (StringIndexOutOfBoundsException e) {
            return count;
        }
    }

    public static String[][] wordsWithLength(String[] words) {
        String[][] result = new String[words.length][2];
        for (int i = 0; i < words.length; i++) {
            result[i][0] = words[i];
            result[i][1] = String.valueOf(getLength(words[i]));
        }
        return result;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter text: ");
        String text = sc.nextLine();

        String[] words = customSplit(text);
        String[][] result = wordsWithLength(words);

        System.out.println("Word\tLength");
        for (String[] row : result)
            System.out.println(row[0] + "\t" + row[1]);
    }
}
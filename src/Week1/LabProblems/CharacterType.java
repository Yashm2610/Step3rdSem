package Week1.LabProblems;

import java.util.Scanner;

class CharacterType {
    public static String getType(char c) {
        if (!Character.isLetter(c)) return "Not a Letter";
        c = Character.toLowerCase(c);
        return "aeiou".indexOf(c) != -1 ? "Vowel" : "Consonant";
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter text: ");
        String text = sc.nextLine();

        System.out.println("Character\tType");
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            System.out.println(c + "\t\t" + getType(c));
        }
    }
}
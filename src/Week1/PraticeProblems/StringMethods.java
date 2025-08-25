package Week1.PraticeProblems;
import java.util.Scanner;

public class StringMethods {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // User inputs
        System.out.print("Enter your full name (First Last): ");
        String fullName = scanner.nextLine();

        System.out.print("Enter your favorite programming language: ");
        String language = scanner.nextLine();

        System.out.print("Write a sentence about your programming experience: ");
        String sentence = scanner.nextLine();

        // Processing
        String[] parts = fullName.split(" ");
        String firstName = parts[0];
        String lastName = parts.length > 1 ? parts[1] : "";

        int charCount = sentence.replace(" ", "").length();
        String languageUpper = language.toUpperCase();

        // Output
        System.out.println("\n=== SUMMARY ===");
        System.out.println("First Name: " + firstName);
        System.out.println("Last Name: " + lastName);
        System.out.println("Favorite Language: " + languageUpper);
        System.out.println("Sentence Character Count (no spaces): " + charCount);

        scanner.close();
    }
}
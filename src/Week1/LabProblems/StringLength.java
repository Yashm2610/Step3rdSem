package Week1.LabProblems;

import java.util.Scanner;

class StringLength {
    public static int getLength(String text) {
        int count = 0;
        try {
            while (true) {
                text.charAt(count);
                count++;
            }
        } catch (StringIndexOutOfBoundsException e) {
            return count;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter text: ");
        String text = sc.next();

        int len = getLength(text);
        System.out.println("Manual Length: " + len);
        System.out.println("Built-in Length: " + text.length());
    }
}
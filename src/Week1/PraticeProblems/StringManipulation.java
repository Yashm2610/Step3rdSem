package Week1.PraticeProblems;
public class StringManipulation {
    public static void main(String[] args) {
        // 1. String literal
        String str1 = "Java Programming";

        // 2. new String() constructor
        String str2 = new String("Java Programming");

        // 3. Character array
        char[] chars = {'J','a','v','a',' ','P','r','o','g','r','a','m','m','i','n','g'};
        String str3 = new String(chars);

        // Comparisons
        System.out.println("str1 == str2: " + (str1 == str2)); // false - different memory
        System.out.println("str1.equals(str2): " + str1.equals(str2)); // true - same content
        System.out.println("str1 == str3: " + (str1 == str3)); // false
        System.out.println("str1.equals(str3): " + str1.equals(str3)); // true

        // Escape sequence string
        String quote = "Programming Quote:\n\t\"Code is poetry\" - Unknown\n\tPath: C:\\Java\\Projects";
        System.out.println(quote);
    }
}
package Week2.PraticeProblems;
import java.util.*;

public class StringUtilityApp {
    static void wordCount(String s){
        String[] w=s.trim().split("\\s+");
        System.out.println("Word count: "+w.length);
    }
    static void transform(String s){
        System.out.println("Upper: "+s.toUpperCase());
        System.out.println("Lower: "+s.toLowerCase());
        System.out.println("Trim: "+s.trim());
        System.out.println("Replace spaces->_: "+s.replace(" ","_"));
    }
    static void asciiOps(String s){
        for(char c:s.toCharArray()) System.out.println(c+" -> "+(int)c);
    }
    static void compare(String a,String b){
        System.out.println("equals: "+a.equals(b));
        System.out.println("ignoreCase: "+a.equalsIgnoreCase(b));
    }
    static void palindrome(String s){
        String rev=new StringBuilder(s).reverse().toString();
        System.out.println(s.equalsIgnoreCase(rev)? "Palindrome":"Not Palindrome");
    }

    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        while(true){
            System.out.println("\n--- String Utility Menu ---");
            System.out.println("1. Word Count\n2. Transform\n3. ASCII Codes\n4. Compare\n5. Palindrome\n0. Exit");
            int ch=sc.nextInt(); sc.nextLine();
            if(ch==0) break;
            System.out.print("Enter text1: ");
            String t1=sc.nextLine();
            switch(ch){
                case 1: wordCount(t1); break;
                case 2: transform(t1); break;
                case 3: asciiOps(t1); break;
                case 4:
                    System.out.print("Enter text2: ");
                    String t2=sc.nextLine();
                    compare(t1,t2); break;
                case 5: palindrome(t1); break;
            }
        }
    }
}

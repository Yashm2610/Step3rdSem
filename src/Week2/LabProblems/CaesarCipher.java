package Week2.LabProblems;
import java.util.*;

public class CaesarCipher {
    static String encrypt(String text,int shift){
        StringBuilder sb=new StringBuilder();
        for(char c:text.toCharArray()){
            if(Character.isUpperCase(c))
                sb.append((char)('A'+(c-'A'+shift+26)%26));
            else if(Character.isLowerCase(c))
                sb.append((char)('a'+(c-'a'+shift+26)%26));
            else sb.append(c);
        }
        return sb.toString();
    }
    static String decrypt(String text,int shift){
        return encrypt(text,-shift);
    }
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        System.out.print("Enter text: ");
        String text=sc.nextLine();
        System.out.print("Enter shift: ");
        int shift=sc.nextInt();

        String enc=encrypt(text,shift);
        String dec=decrypt(enc,shift);

        System.out.println("Original: "+text);
        System.out.println("Encrypted: "+enc);
        System.out.println("Decrypted: "+dec);
        System.out.println("Valid? "+text.equals(dec));
    }
}

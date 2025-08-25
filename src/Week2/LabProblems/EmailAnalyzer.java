package Week2.LabProblems;
import java.util.*;

public class EmailAnalyzer {
    static boolean validate(String email){
        int at=email.indexOf('@');
        int lastAt=email.lastIndexOf('@');
        if(at==-1 || at!=lastAt) return false;
        int dot=email.indexOf('.',at);
        if(dot==-1) return false;
        if(at==0 || dot-at<=1 || dot==email.length()-1) return false;
        return true;
    }

    static void analyzeEmails(String[] emails){
        int valid=0, invalid=0;
        Map<String,Integer> domainCount=new HashMap<>();
        int totalUserLen=0;
        for(String e:emails){
            boolean ok=validate(e);
            if(ok){
                valid++;
                int at=e.indexOf('@');
                int dot=e.indexOf('.',at);
                String user=e.substring(0,at);
                String domain=e.substring(at+1);
                String dname=e.substring(at+1,dot);
                String ext=e.substring(dot+1);
                totalUserLen+=user.length();
                domainCount.put(domain,domainCount.getOrDefault(domain,0)+1);
                System.out.println(e+" | "+user+" | "+domain+" | "+dname+" | "+ext+" | Valid");
            } else {
                invalid++;
                System.out.println(e+" | Invalid");
            }
        }
        System.out.println("Valid: "+valid+", Invalid: "+invalid);
        if(!domainCount.isEmpty()){
            String common=Collections.max(domainCount.entrySet(), Map.Entry.comparingByValue()).getKey();
            System.out.println("Most common domain: "+common);
            System.out.println("Avg username length: "+(totalUserLen/valid));
        }
    }

    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter number of emails: ");
        int n=sc.nextInt(); sc.nextLine();
        String[] emails=new String[n];
        for(int i=0;i<n;i++){ emails[i]=sc.nextLine(); }
        analyzeEmails(emails);
    }
}

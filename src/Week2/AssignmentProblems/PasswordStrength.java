package Week2.AssignmentProblems;
import java.util.*;

class PasswordStrength {
    static boolean isUpper(char c){ int a=c; return a>=65&&a<=90; }
    static boolean isLower(char c){ int a=c; return a>=97&&a<=122; }
    static boolean isDigit(char c){ int a=c; return a>=48&&a<=57; }
    static boolean isPrintableNonAlnum(char c){
        int a=c; return (a>=33&&a<=47)||(a>=58&&a<=64)||(a>=91&&a<=96)||(a>=123&&a<=126);
    }

    static int[] analyzeAsciiCounts(String p){
        int up=0, lo=0, di=0, sp=0;
        for (int i=0;i<p.length();i++){
            char c=p.charAt(i);
            if(isUpper(c)) up++;
            else if(isLower(c)) lo++;
            else if(isDigit(c)) di++;
            else if(isPrintableNonAlnum(c)) sp++;
        }
        return new int[]{up,lo,di,sp};
    }

    static boolean hasCommonPattern(String p){
        String q = p.toLowerCase();
        return q.contains("123") || q.contains("abc") || q.contains("qwerty") || q.contains("password");
    }

    static int score(String p){
        int s=0;
        int len=p.length();
        if(len>8) s += (len-8)*2;
        int[] c = analyzeAsciiCounts(p);
        if(c[0]>0) s+=10; if(c[1]>0) s+=10; if(c[2]>0) s+=10; if(c[3]>0) s+=10;
        if(hasCommonPattern(p)) s -= 15;
        if(len>=16 && c[3]>0) s+=10; // bonus for long+specials
        return Math.max(s, 0);
    }

    static String level(int s){
        if(s<=20) return "Weak";
        if(s<=50) return "Medium";
        return "Strong";
    }

    static String generateStrong(int length){
        if(length<8) length=12;
        String upp="ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String low="abcdefghijklmnopqrstuvwxyz";
        String dig="0123456789";
        String sp="!@#$%^&*()-_=+[]{};:,.<>?/|";
        Random r = new Random();

        List<Character> out = new ArrayList<>();
        out.add(upp.charAt(r.nextInt(upp.length())));
        out.add(low.charAt(r.nextInt(low.length())));
        out.add(dig.charAt(r.nextInt(dig.length())));
        out.add(sp.charAt(r.nextInt(sp.length())));

        String all = upp+low+dig+sp;
        while(out.size()<length){
            out.add(all.charAt(r.nextInt(all.length())));
        }
        // shuffle
        for(int i=0;i<out.size();i++){
            int j=i+r.nextInt(out.size()-i);
            char tmp=out.get(i); out.set(i,out.get(j)); out.set(j,tmp);
        }
        StringBuilder sb=new StringBuilder();
        for(char c:out) sb.append(c);
        return sb.toString();
    }

    static void printTable(String[] pwds){
        String fmt="%-24s %5s %6s %6s %6s %8s %6s %8s%n";
        System.out.printf(fmt,"Password","Len","Upper","Lower","Digits","Special","Score","Strength");
        System.out.printf(fmt,"--------","---","-----","-----","------","-------","-----","--------");
        for(String p: pwds){
            int[] c=analyzeAsciiCounts(p);
            int s=score(p);
            System.out.printf(fmt,p,p.length(),c[0],c[1],c[2],c[3],s,level(s));
        }
    }

    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        System.out.print("How many passwords to analyze? ");
        int n=sc.nextInt(); sc.nextLine();
        String[] pwds=new String[n];
        for(int i=0;i<n;i++){ pwds[i]=sc.nextLine(); }
        printTable(pwds);

        System.out.print("Generate how many strong passwords? ");
        int g=sc.nextInt();
        System.out.print("Desired length? ");
        int len=sc.nextInt();
        for(int i=1;i<=g;i++){
            String p=generateStrong(len);
            System.out.println("Generated["+i+"]: "+p+"  (score="+score(p)+", "+level(score(p))+")");
        }
    }
}

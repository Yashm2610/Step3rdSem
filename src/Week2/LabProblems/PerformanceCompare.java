package Week2.LabProblems;
import java.util.*;

public class PerformanceCompare {
    static long concatString(int n) {
        long t1=System.currentTimeMillis();
        String s="";
        for(int i=0;i<n;i++) s+="a";
        return System.currentTimeMillis()-t1;
    }
    static long concatBuilder(int n) {
        long t1=System.currentTimeMillis();
        StringBuilder sb=new StringBuilder();
        for(int i=0;i<n;i++) sb.append("a");
        return System.currentTimeMillis()-t1;
    }
    static long concatBuffer(int n) {
        long t1=System.currentTimeMillis();
        StringBuffer sb=new StringBuffer();
        for(int i=0;i<n;i++) sb.append("a");
        return System.currentTimeMillis()-t1;
    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.print("Enter iterations: ");
        int n=sc.nextInt();

        System.out.println("String: "+concatString(n)+" ms");
        System.out.println("StringBuilder: "+concatBuilder(n)+" ms");
        System.out.println("StringBuffer: "+concatBuffer(n)+" ms");
    }
}

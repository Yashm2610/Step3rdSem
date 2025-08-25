package Week2.PraticeProblems;
public class StringPerformance {
    static void testConcat(int n) {
        long t1 = System.nanoTime();
        String s="";
        for(int i=0;i<n;i++) s += "a";
        long t2 = System.nanoTime();
        System.out.println("String concat: "+(t2-t1)/1e6+" ms");

        t1=System.nanoTime();
        StringBuilder sb=new StringBuilder();
        for(int i=0;i<n;i++) sb.append("a");
        t2=System.nanoTime();
        System.out.println("StringBuilder: "+(t2-t1)/1e6+" ms");

        t1=System.nanoTime();
        StringBuffer sbuf=new StringBuffer();
        for(int i=0;i<n;i++) sbuf.append("a");
        t2=System.nanoTime();
        System.out.println("StringBuffer: "+(t2-t1)/1e6+" ms");
    }

    public static void main(String[] args) {
        testConcat(50000);

        StringBuilder sb=new StringBuilder("Java");
        sb.append(" World").insert(5,"Hello ").delete(0,4).reverse();
        System.out.println("StringBuilder ops: "+sb);

        String s1="Test", s2="Test";
        System.out.println("== : "+(s1==s2));
        System.out.println("equals : "+s1.equals(s2));
        System.out.println("compareTo : "+s1.compareTo(s2));
    }
}

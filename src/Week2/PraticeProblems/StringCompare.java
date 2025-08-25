package Week2.PraticeProblems;
public class StringCompare {
    static int levenshtein(String a, String b) {
        int[][] dp=new int[a.length()+1][b.length()+1];
        for(int i=0;i<=a.length();i++) dp[i][0]=i;
        for(int j=0;j<=b.length();j++) dp[0][j]=j;
        for(int i=1;i<=a.length();i++) {
            for(int j=1;j<=b.length();j++) {
                int cost = a.charAt(i-1)==b.charAt(j-1)?0:1;
                dp[i][j] = Math.min(Math.min(dp[i-1][j]+1, dp[i][j-1]+1), dp[i-1][j-1]+cost);
            }
        }
        return dp[a.length()][b.length()];
    }

    public static void main(String[] args) {
        String s1="Java", s2="java";
        System.out.println("== : "+(s1==s2));
        System.out.println("equals : "+s1.equals(s2));
        System.out.println("equalsIgnoreCase : "+s1.equalsIgnoreCase(s2));
        System.out.println("compareTo : "+s1.compareTo(s2));

        int dist=levenshtein(s1,s2);
        double sim=100.0*(1.0-dist/(double)Math.max(s1.length(),s2.length()));
        System.out.println("Similarity %: "+sim);

        String pooled="Pool".intern();
        String pooled2=new String("Pool").intern();
        System.out.println("Intern check: "+(pooled==pooled2));
    }
}

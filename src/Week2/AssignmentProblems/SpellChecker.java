package Week2.AssignmentProblems;
import java.util.*;

class SpellChecker {
    // ---- Utilities ----
    static boolean isWordChar(char c) {
        // letters or digits only (ASCII)
        int a = (int)c;
        return (a>=48 && a<=57) || (a>=65 && a<=90) || (a>=97 && a<=122);
    }

    // Manual split into words, keep punctuation as separators
    static String[] manualSplitWords(String sentence) {
        List<String> words = new ArrayList<>();
        int n = sentence.length(), i = 0;
        while (i < n) {
            while (i < n && !isWordChar(sentence.charAt(i))) i++;
            int start = i;
            while (i < n && isWordChar(sentence.charAt(i))) i++;
            if (start < i) words.add(sentence.substring(start, i));
        }
        return words.toArray(new String[0]);
    }

    // Levenshtein edit distance (insert/delete/substitute)
    static int distance(String a, String b) {
        int n = a.length(), m = b.length();
        int[][] dp = new int[n+1][m+1];
        for (int x=0; x<=n; x++) dp[x][0] = x;
        for (int y=0; y<=m; y++) dp[0][y] = y;
        for (int x=1; x<=n; x++) {
            for (int y=1; y<=m; y++) {
                int cost = a.charAt(x-1)==b.charAt(y-1)?0:1;
                dp[x][y] = Math.min(
                        Math.min(dp[x-1][y]+1, dp[x][y-1]+1),
                        dp[x-1][y-1]+cost
                );
            }
        }
        return dp[n][m];
    }

    static String suggest(String word, String[] dict, int threshold) {
        String best = word;
        int bestD = Integer.MAX_VALUE;
        for (String d : dict) {
            int dist = distance(word.toLowerCase(), d.toLowerCase());
            if (dist < bestD) { bestD = dist; best = d; }
        }
        return bestD <= threshold ? best : "(no suggestion)";
    }

    // Tabular print
    static void printReport(String[] words, String[] dict) {
        String fmt = "%-16s %-16s %-8s %-12s%n";
        System.out.printf(fmt, "Word", "Suggestion", "Dist", "Status");
        System.out.printf(fmt, "----", "----------", "----", "------");
        for (String w: words) {
            String s = suggest(w, dict, 2);
            int d = s.equals("(no suggestion)") ? - : distance(w.toLowerCase(), s.toLowerCase());
            String status = (d==0) ? "Correct" : (s.equals("(no suggestion)") ? "Misspelled" : "Misspelled");
            System.out.printf(fmt, w, s, (d<0?"-":String.valueOf(d)), status);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter sentence:");
        String sentence = sc.nextLine();
        System.out.println("Enter dictionary size:");
        int n = sc.nextInt(); sc.nextLine();
        String[] dict = new String[n];
        for (int i=0;i<n;i++) { dict[i] = sc.nextLine().trim(); }

        String[] words = manualSplitWords(sentence);
        printReport(words, dict);
    }
}

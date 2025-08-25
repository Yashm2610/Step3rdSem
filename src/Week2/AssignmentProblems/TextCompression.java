package Week2.AssignmentProblems;
import java.util.*;

class TextCompression {
    static class Pair { char ch; int freq; Pair(char c,int f){ch=c;freq=f;} }

    // Count unique chars and frequencies without HashMap
    static Pair[] frequencies(String text){
        // collect unique in arrays
        char[] chars = new char[text.length()];
        int[] freq = new int[text.length()];
        int unique=0;
        for(int i=0;i<text.length();i++){
            char c=text.charAt(i);
            int idx=-1;
            for(int k=0;k<unique;k++){ if(chars[k]==c){ idx=k; break; } }
            if(idx==-1){ chars[unique]=c; freq[unique]=1; unique++; }
            else freq[idx]++;
        }
        Pair[] res = new Pair[unique];
        for(int i=0;i<unique;i++) res[i]=new Pair(chars[i],freq[i]);
        return res;
    }

    // Build code mapping: frequent => 1-char code from codebook; others => "~"+index (2 chars)
    static String[][] buildCodes(Pair[] freqTable){
        Arrays.sort(freqTable, (a,b)->Integer.compare(b.freq,a.freq)); // desc by freq
        String codebook = "!@#$%^&*+=?/ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        int k1 = Math.min(codebook.length(), freqTable.length);
        String[][] map = new String[freqTable.length][2]; // [char, code]
        // top frequent single-char codes
        for(int i=0;i<k1;i++){
            map[i][0]=String.valueOf(freqTable[i].ch);
            map[i][1]=String.valueOf(codebook.charAt(i));
        }
        // remaining two-char codes with '~' prefix and (i-k1) base36
        for(int i=k1;i<freqTable.length;i++){
            int idx=i-k1;
            String base = Integer.toString(idx, 36);
            map[i][0]=String.valueOf(freqTable[i].ch);
            map[i][1]="~"+base;
        }
        return map;
    }

    static String codeFor(char c, String[][] map){
        for(String[] row: map) if(row[0].charAt(0)==c) return row[1];
        return "?"; // should not happen
    }

    static char charFor(String code, String[][] map){
        for(String[] row: map) if(row[1].equals(code)) return row[0].charAt(0);
        return '\u0000';
    }

    static String compress(String text, String[][] map){
        StringBuilder sb=new StringBuilder();
        for(int i=0;i<text.length();i++){
            if(i>0) sb.append(' ');
            sb.append(codeFor(text.charAt(i), map));
        }
        return sb.toString();
    }

    static String decompress(String compressed, String[][] map){
        // split by spaces manually
        List<String> tokens = new ArrayList<>();
        int i=0, n=compressed.length();
        while(i<n){
            while(i<n && compressed.charAt(i)==' ') i++;
            int start=i;
            while(i<n && compressed.charAt(i)!=' ') i++;
            if(start<i) tokens.add(compressed.substring(start,i));
        }
        StringBuilder out=new StringBuilder();
        for(String t: tokens) out.append(charFor(t,map));
        return out.toString();
    }

    static void printFreq(Pair[] freq){
        String fmt="%-8s %-8s%n";
        System.out.printf(fmt,"Char","Freq");
        System.out.printf(fmt,"----","----");
        for(Pair p: freq){
            String show = (p.ch==' ')? "[space]" : String.valueOf(p.ch);
            System.out.printf("%-8s %-8d%n",show,p.freq);
        }
    }

    static void printMap(String[][] map){
        String fmt="%-10s %-10s%n";
        System.out.printf(fmt,"Char","Code");
        System.out.printf(fmt,"----","----");
        for(String[] r: map){
            String show = r[0].equals(" ")? "[space]" : r[0];
            System.out.printf(fmt,show,r[1]);
        }
    }

    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter text to compress:");
        String text=sc.nextLine();

        Pair[] freq=frequencies(text);
        String[][] map=buildCodes(freq);
        String compressed=compress(text,map);
        String decompressed=decompress(compressed,map);

        System.out.println("\nCharacter Frequency:");
        printFreq(freq);
        System.out.println("\nCompression Mapping:");
        printMap(map);

        System.out.println("\nOriginal:   "+text);
        System.out.println("Compressed: "+compressed);
        System.out.println("Decompressed equals original? "+text.equals(decompressed));

        int origBits = text.length()*8;
        int compBits = compressed.length()*8; // simplistic, shows relative size of encoded string representation
        double efficiency = 100.0*(1.0 - ((double)compBits/Math.max(1,origBits)));
        System.out.printf("Compression Efficiency: %.2f%%%n", efficiency);
    }
}

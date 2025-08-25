package Week2.AssignmentProblems;
import java.util.*;

class CSVAnalyzer {
    // Parse CSV without split(); support quoted fields with commas
    static String[][] parseCSV(String input){
        List<List<String>> rows=new ArrayList<>();
        int i=0, n=input.length();
        List<String> currentRow=new ArrayList<>();
        StringBuilder cell=new StringBuilder();
        boolean inQuotes=false;

        while(i<n){
            char c=input.charAt(i);
            if(inQuotes){
                if(c=='"'){
                    if(i+1<n && input.charAt(i+1)=='"'){ cell.append('"'); i++; } // escaped quote
                    else inQuotes=false;
                } else cell.append(c);
            } else {
                if(c=='"'){ inQuotes=true; }
                else if(c==','){ currentRow.add(cell.toString()); cell.setLength(0); }
                else if(c=='\n' || c=='\r'){
                    // end row (handle \r\n)
                    if(c=='\r' && i+1<n && input.charAt(i+1)=='\n') i++;
                    currentRow.add(cell.toString()); cell.setLength(0);
                    rows.add(new ArrayList<>(currentRow));
                    currentRow.clear();
                } else cell.append(c);
            }
            i++;
        }
        // last cell/row
        currentRow.add(cell.toString());
        rows.add(new ArrayList<>(currentRow));
        // normalize to rectangular
        int cols=0; for(List<String> r: rows) cols=Math.max(cols, r.size());
        String[][] out=new String[rows.size()][cols];
        for(int r=0;r<rows.size();r++){
            List<String> rr=rows.get(r);
            for(int c=0;c<cols;c++) out[r][c]= c<rr.size()? rr.get(c).trim(): "";
        }
        return out;
    }

    static boolean isNumeric(String s){
        if(s==null||s.isEmpty()) return false;
        int i=0, n=s.length(), dots=0, sign=0;
        if(s.charAt(0)=='+'||s.charAt(0)=='-'){ sign=1; }
        for(i=sign;i<n;i++){
            char c=s.charAt(i);
            if(c=='.'){ dots++; if(dots>1) return false; }
            else if(c<'0'||c>'9') return false;
        }
        return true;
    }

    static class ColStats {
        boolean numeric;
        double min=Double.POSITIVE_INFINITY, max=Double.NEGATIVE_INFINITY, sum=0;
        int count=0, missing=0, invalid=0;
        Set<String> unique = new HashSet<>();
        void add(String v){
            if(v==null || v.isEmpty()){ missing++; return; }
            if(isNumeric(v)){
                numeric=true;
                double d=Double.parseDouble(v);
                min=Math.min(min,d); max=Math.max(max,d); sum+=d; count++;
            } else {
                unique.add(v);
                invalid++;
            }
        }
        double avg(){ return count==0? Double.NaN : (sum/count); }
    }

    static void analyze(String[][] table){
        if(table.length==0){ System.out.println("No data."); return; }
        int rows = table.length, cols = table[0].length;
        ColStats[] stats=new ColStats[cols];
        for(int c=0;c<cols;c++) stats[c]=new ColStats();

        for(int r=0;r<rows;r++){
            for(int c=0;c<cols;c++){
                stats[c].add(table[r][c]);
            }
        }

        // Print table
        System.out.println("Parsed Table:");
        for(int r=0;r<rows;r++){
            StringBuilder sb=new StringBuilder();
            for(int c=0;c<cols;c++){
                String v=table[r][c];
                sb.append(String.format("%-15s", v));
            }
            System.out.println(sb);
        }

        // Column stats
        System.out.println("\nColumn-wise Statistics:");
        String hdr="Col  Type      Min        Max        Avg        Missing Invalid Unique";
        System.out.println(hdr);
        for(int c=0;c<cols;c++){
            ColStats st=stats[c];
            String type = st.numeric? "Numeric  " : "Categoric";
            String min = st.numeric? String.format("%-10.3f", st.min) : " -        ";
            String max = st.numeric? String.format("%-10.3f", st.max) : " -        ";
            String avg = st.numeric? String.format("%-10.3f", st.avg()) : " -        ";
            System.out.printf("%-4d %-9s %s %s %s %-8d %-7d %-6d%n",
                    c, type, min, max, avg, st.missing, st.invalid, (st.numeric?0:st.unique.size()));
        }

        int totalCells = rows*cols;
        int totalMissing=0, totalInvalid=0;
        for(ColStats st: stats){ totalMissing+=st.missing; totalInvalid+=st.invalid; }
        double completeness = 100.0*(1.0 - ((double)(totalMissing+totalInvalid))/Math.max(1,totalCells));
        System.out.printf("%nRecords: %d, Columns: %d, Completeness: %.2f%%%n", rows, cols, completeness);
    }

    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        System.out.println("Paste CSV-like data (end with a single line containing __END__):");
        StringBuilder input=new StringBuilder();
        while(true){
            String line=sc.nextLine();
            if(line.equals("__END__")) break;
            input.append(line).append('\n');
        }
        String[][] table=parseCSV(input.toString());
        analyze(table);
    }
}

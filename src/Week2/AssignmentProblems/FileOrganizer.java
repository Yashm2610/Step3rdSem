package Week2.AssignmentProblems;
import java.util.*;

class FileOrganizer {
    static class FileInfo {
        String original;
        String name;
        String ext;
        String category;
        String suggestion;
        boolean valid;
        FileInfo(String o){ original=o; }
    }

    static boolean isValidNameChar(char c){
        // Disallow: /\:*?"<>| and control chars
        return c>31 && "\\/:*?\"<>|".indexOf(c)==-1;
    }

    static FileInfo parse(String filename){
        FileInfo fi=new FileInfo(filename);
        fi.valid=true;
        for(int i=0;i<filename.length();i++){
            if(!isValidNameChar(filename.charAt(i))){ fi.valid=false; break; }
        }
        int dot=filename.lastIndexOf('.');
        if(dot==-1 || dot==filename.length()-1){ fi.name=filename; fi.ext=""; }
        else { fi.name=filename.substring(0,dot); fi.ext=filename.substring(dot+1).toLowerCase(); }
        return fi;
    }

    static String categorize(String ext){
        if(ext==null || ext.isEmpty()) return "Unknown";
        if(ext.equals("txt")||ext.equals("doc")||ext.equals("docx")||ext.equals("pdf")) return "Documents";
        if(ext.equals("jpg")||ext.equals("jpeg")||ext.equals("png")||ext.equals("gif")) return "Images";
        if(ext.equals("mp4")||ext.equals("mkv")||ext.equals("mov")) return "Videos";
        if(ext.equals("mp3")||ext.equals("wav")||ext.equals("flac")) return "Audio";
        if(ext.equals("zip")||ext.equals("rar")||ext.equals("7z")) return "Archives";
        if(ext.equals("java")||ext.equals("py")||ext.equals("c")||ext.equals("cpp")||ext.equals("js")) return "Code";
        return "Others";
    }

    static String dateStamp(){
        Calendar cal=Calendar.getInstance();
        int y=cal.get(Calendar.YEAR);
        int m=cal.get(Calendar.MONTH)+1;
        int d=cal.get(Calendar.DAY_OF_MONTH);
        return String.format("%04d%02d%02d", y,m,d);
    }
    static String sanitize(String s){
        StringBuilder sb=new StringBuilder();
        for(int i=0;i<s.length();i++){
            char c=s.charAt(i);
            sb.append(isValidNameChar(c)? c : '_');
        }
        return sb.toString().trim();
    }

    static String suggestName(FileInfo f, Map<String,Integer> existing){
        String base = f.category + "_" + dateStamp() + "_" + sanitize(f.name);
        String ext = f.ext.isEmpty()? "" : "."+f.ext;
        String key=base.toLowerCase();
        int count = existing.getOrDefault(key, 0);
        existing.put(key, count+1);
        if(count==0) return base + ext;
        return base + "(" + count + ")" + ext;
    }

    // Simple content-based hints (simulated via filename keywords)
    static String subCategoryHint(FileInfo f){
        String n = f.name.toLowerCase();
        if(f.category.equals("Documents")){
            if(n.contains("resume")||n.contains("cv")) return "Resume";
            if(n.contains("report")) return "Report";
        }
        if(f.category.equals("Code")){
            if(n.contains("test")) return "Test";
            if(n.contains("util")) return "Utility";
        }
        return "-";
    }

    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        System.out.println("How many files?");
        int n=Integer.parseInt(sc.nextLine());
        List<FileInfo> list=new ArrayList<>();
        for(int i=0;i<n;i++){
            String fn=sc.nextLine();
            list.add(parse(fn));
        }
        Map<String,Integer> existing=new HashMap<>();
        Map<String,Integer> counts=new HashMap<>();
        int invalid=0, unknown=0;

        for(FileInfo f: list){
            f.category = categorize(f.ext);
            if(f.category.equals("Unknown")) unknown++;
            if(!f.valid) invalid++;
            f.suggestion = suggestName(f, existing);
            counts.put(f.category, counts.getOrDefault(f.category,0)+1);
        }

        String fmt="%-28s %-12s %-18s %-10s %-10s%n";
        System.out.printf(fmt,"Original","Category","Suggested Name","Valid","Hint");
        System.out.printf(fmt,"--------","--------","--------------","-----","----");
        for(FileInfo f : list){
            System.out.printf(fmt, f.original, f.category, f.suggestion, f.valid?"Yes":"No", subCategoryHint(f));
        }

        System.out.println("\nCategory Counts:");
        for(Map.Entry<String,Integer> e: counts.entrySet()){
            System.out.printf("%-12s : %d%n", e.getKey(), e.getValue());
        }
        System.out.println("\nInvalid names: "+invalid+", Unknown types: "+unknown);

        // Batch rename simulation (shell-neutral)
        System.out.println("\nBatch Rename Commands:");
        for(FileInfo f: list){
            System.out.println("RENAME \""+f.original+"\" -> \""+f.suggestion+"\"");
        }
    }
}

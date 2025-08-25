package Week2.LabProblems;
import java.util.*;

public class TextFormatter {
    static List<String> splitWords(String text){
        List<String> words=new ArrayList<>();
        int start=0;
        for(int i=0;i<text.length();i++){
            if(text.charAt(i)==' '){
                if(start<i) words.add(text.substring(start,i));
                start=i+1;
            }
        }
        if(start<text.length()) words.add(text.substring(start));
        return words;
    }

    static List<String> justify(List<String> words,int width){
        List<String> lines=new ArrayList<>();
        for(int i=0;i<words.size();){
            int j=i, len=words.get(i).length();
            while(j+1<words.size() && len+1+words.get(j+1).length()<=width){
                len+=1+words.get(j+1).length();
                j++;
            }
            StringBuilder sb=new StringBuilder();
            int gaps=j-i;
            if(j==words.size()-1 || gaps==0){
                for(int k=i;k<=j;k++){
                    sb.append(words.get(k));
                    if(k<j) sb.append(" ");
                }
                while(sb.length()<width) sb.append(" ");
            } else {
                int spaces=(width-len+gaps)/(gaps);
                int extra=(width-len+gaps)%gaps;
                for(int k=i;k<=j;k++){
                    sb.append(words.get(k));
                    if(k<j){
                        for(int s=0;s<spaces+(k-i<extra?1:0)+1;s++) sb.append(" ");
                    }
                }
            }
            lines.add(sb.toString());
            i=j+1;
        }
        return lines;
    }

    static void display(List<String> lines){
        int n=1;
        for(String l:lines){
            System.out.println(n++ + ": ["+l+"] ("+l.length()+")");
        }
    }

    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        System.out.print("Enter text: ");
        String text=sc.nextLine();
        System.out.print("Enter width: ");
        int width=sc.nextInt();

        List<String> words=splitWords(text);
        List<String> lines=justify(words,width);
        display(lines);
    }
}

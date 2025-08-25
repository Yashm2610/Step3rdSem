package Week2.AssignmentProblems;
import java.util.*;

class StringCalculator {
    // ASCII checks
    static boolean isDigit(char c){ int a=c; return a>=48 && a<=57; }
    static boolean isSpace(char c){ return c==' '; }

    // Validate allowed chars and parentheses
    static boolean validate(String expr){
        int bal=0;
        for(int i=0;i<expr.length();i++){
            char c=expr.charAt(i);
            boolean ok = isDigit(c) || isSpace(c) || c=='+'||c=='-'||c=='*'||c=='/'||c=='('||c==')';
            if(!ok) return false;
            if(c=='(') bal++;
            if(c==')'){ bal--; if(bal<0) return false; }
        }
        return bal==0;
    }

    // Trim spaces
    static String compact(String s){
        StringBuilder sb=new StringBuilder();
        for(int i=0;i<s.length();i++) if(!isSpace(s.charAt(i))) sb.append(s.charAt(i));
        return sb.toString();
    }

    // Evaluate no-parentheses expression with precedence
    static long evalFlat(String s, StringBuilder steps){
        // tokenize numbers and ops manually
        List<Long> nums=new ArrayList<>();
        List<Character> ops=new ArrayList<>();
        int i=0; int n=s.length();
        while(i<n){
            // parse (possibly signed) number
            int sign=1;
            if((i==0 || (i>0 && (s.charAt(i-1)=='('||s.charAt(i-1)=='+'||s.charAt(i-1)=='-'||s.charAt(i-1)=='*'||s.charAt(i-1)=='/'))) && (i<n && (s.charAt(i)=='+'||s.charAt(i)=='-'))){
                if(s.charAt(i)=='-') sign=-1;
                i++;
            }
            int start=i;
            while(i<n && isDigit(s.charAt(i))) i++;
            long val=Long.parseLong(s.substring(start,i))*sign;
            nums.add(val);
            if(i<n){
                char op=s.charAt(i++);
                ops.add(op);
            }
        }
        // first pass: * and /
        for(int k=0;k<ops.size();){
            char op=ops.get(k);
            if(op=='*' || op=='/'){
                long a=nums.get(k), b=nums.get(k+1);
                long r = (op=='*')? (a*b) : (a/b);
                steps.append(a).append(' ').append(op).append(' ').append(b).append(" = ").append(r).append('\n');
                nums.set(k, r);
                nums.remove(k+1);
                ops.remove(k);
            } else k++;
        }
        // second pass: + and -
        long res = nums.get(0);
        for(int k=0;k<ops.size();k++){
            char op=ops.get(k);
            long b=nums.get(k+1);
            long prev=res;
            res = (op=='+')? (res+b) : (res-b);
            steps.append(prev).append(' ').append(op).append(' ').append(b).append(" = ").append(res).append('\n');
        }
        return res;
    }

    // Resolve parentheses recursively
    static long eval(String s, StringBuilder steps){
        // find innermost parentheses
        int start=-1;
        for(int i=0;i<s.length();i++){
            if(s.charAt(i)=='(') start=i;
            else if(s.charAt(i)==')'){
                String inside=s.substring(start+1,i);
                long val=eval(inside, steps);
                String before=s.substring(0,start);
                String after=s.substring(i+1);
                String replaced = before + val + after;
                steps.append("Replace (").append(inside).append(") -> ").append(val).append('\n');
                return eval(replaced, steps);
            }
        }
        // no parentheses
        return evalFlat(s, steps);
    }

    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter expression (e.g., 15 + 23 * 4 - 10 / (2 + 3)):");
        String in=sc.nextLine();
        if(!validate(in)){ System.out.println("Invalid expression."); return; }
        String expr=compact(in);
        StringBuilder steps=new StringBuilder("Steps:\n");
        long result=eval(expr, steps);
        System.out.println("Original: "+in);
        System.out.println(steps);
        System.out.println("Result: "+result);
    }
}
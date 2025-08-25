package Week1.LabProblems;
import java.util.*;

class VotingEligibility {
    public static boolean canVote(int age) {
        return age >= 18;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] ages = new int[10];
        System.out.println("Enter ages of 10 students:");
        for (int i = 0; i < 10; i++) ages[i] = sc.nextInt();

        System.out.println("Age\tEligible");
        for (int age : ages)
            System.out.println(age + "\t" + (canVote(age) ? "Yes" : "No"));
    }
}
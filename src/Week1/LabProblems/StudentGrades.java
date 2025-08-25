package Week1.LabProblems;
import java.util.*;

class StudentGrades {
    public static String getGrade(double percent) {
        if (percent >= 90) return "A";
        if (percent >= 75) return "B";
        if (percent >= 60) return "C";
        if (percent >= 40) return "D";
        return "F";
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n;
        System.out.print("Enter number of students: ");
        n = sc.nextInt();

        String[] names = new String[n];
        int[][] marks = new int[n][3];
        double[] percent = new double[n];
        String[] grades = new String[n];

        for (int i = 0; i < n; i++) {
            System.out.print("Enter name: ");
            names[i] = sc.next();
            System.out.print("Enter marks in Physics, Chemistry, Maths: ");
            marks[i][0] = sc.nextInt();
            marks[i][1] = sc.nextInt();
            marks[i][2] = sc.nextInt();

            int total = marks[i][0] + marks[i][1] + marks[i][2];
            percent[i] = Math.round((total / 3.0) * 100.0) / 100.0;
            grades[i] = getGrade(percent[i]);
        }

        System.out.println("\nName\tPCM Marks\tPercentage\tGrade");
        for (int i = 0; i < n; i++) {
            System.out.println(names[i] + "\t" + marks[i][0] + "," + marks[i][1] + "," + marks[i][2] +
                    "\t" + percent[i] + "\t\t" + grades[i]);
        }
    }
}
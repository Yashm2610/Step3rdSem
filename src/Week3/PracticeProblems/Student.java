package Week3.PracticeProblems;

public class Student {
        // Private attributes (Encapsulation)
        private String studentId;
        private String name;
        private double grade;
        private String course;

        // Default constructor
        public Student() {}

        // Parameterized constructor
        public Student(String studentId, String name, double grade, String course) {
            this.studentId = studentId;
            this.name = name;
            this.grade = grade;
            this.course = course;
        }

        // Getters & Setters
        public String getStudentId() { return studentId; }
        public void setStudentId(String studentId) { this.studentId = studentId; }

        public String getName() { return name; }
        public void setName(String name) { this.name = name; }

        public double getGrade() { return grade; }
        public void setGrade(double grade) { this.grade = grade; }

        public String getCourse() { return course; }
        public void setCourse(String course) { this.course = course; }

        public String calculateLetterGrade() {
            if (grade >= 90) return "A";
            else if (grade >= 80) return "B";
            else if (grade >= 70) return "C";
            else if (grade >= 60) return "D";
            else return "F";
        }

        public void displayStudent() {
            System.out.println("ID: " + studentId + ", Name: " + name + ", Course: " + course + ", Grade: " + grade + " (" + calculateLetterGrade() + ")");
        }

        public static void main(String[] args) {
            Student s1 = new Student();
            s1.setStudentId("S101");
            s1.setName("Alice");
            s1.setGrade(88);
            s1.setCourse("Java Programming");

            Student s2 = new Student("S102", "Bob", 95, "Data Structures");

            s1.displayStudent();
            s2.displayStudent();
        }
    }



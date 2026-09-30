import java.util.ArrayList;
import java.util.Scanner;

class Student {
    private int rollNo;
    private String name;
    private int age;
    private double marks;

    public Student(int rollNo, String name, int age, double marks) {
        this.rollNo = rollNo;
        this.name = name;
        this.age = age;
        this.marks = marks;
    }

    public int getRollNo() { return rollNo; }
    public String getName() { return name; }
    public int getAge() { return age; }
    public double getMarks() { return marks; }

    public void setName(String name) { this.name = name; }
    public void setAge(int age) { this.age = age; }
    public void setMarks(double marks) { this.marks = marks; }

    public String getGrade() {
        if (marks >= 90) return "A+";
        else if (marks >= 80) return "A";
        else if (marks >= 70) return "B";
        else if (marks >= 60) return "C";
        else if (marks >= 50) return "D";
        else if (marks >= 40) return "E";
        else return "F";
    }

    public String getResult() {
        return marks >= 40 ? "PASS" : "FAIL";
    }

    public void display() {
        System.out.println("--------------------------------");
        System.out.println("Roll No : " + rollNo);
        System.out.println("Name    : " + name);
        System.out.println("Age     : " + age);
        System.out.println("Marks   : " + marks);
        System.out.println("Grade   : " + getGrade());
        System.out.println("Result  : " + getResult());
    }
}

public class Main {
    static Scanner sc = new Scanner(System.in);
    static ArrayList<Student> students = new ArrayList<>();

    public static void main(String[] args) {
        int choice;
        do {
            showMenu();
            choice = readInt("Enter your choice: ");

            switch (choice) {
                case 1: addStudent(); break;
                case 2: viewStudents(); break;
                case 3: searchStudent(); break;
                case 4: updateStudent(); break;
                case 5: deleteStudent(); break;
                case 6: showStatistics(); break;
                case 7:
                    System.out.println("\nThank you for using Student Management System!");
                    break;
                default:
                    System.out.println("\nInvalid choice! Please choose 1 to 7.");
            }
        } while (choice != 7);

        sc.close();
    }

    static void showMenu() {
        System.out.println("\n================================");
        System.out.println("     STUDENT MANAGEMENT SYSTEM");
        System.out.println("================================");
        System.out.println("1. Add Student");
        System.out.println("2. View All Students");
        System.out.println("3. Search Student");
        System.out.println("4. Update Student");
        System.out.println("5. Delete Student");
        System.out.println("6. Statistics");
        System.out.println("7. Exit");
        System.out.println("================================");
    }

    static void addStudent() {
        System.out.println("\n--- Add Student ---");
        int rollNo = readInt("Enter Roll No: ");

        if (findStudent(rollNo) != null) {
            System.out.println("Student with this Roll No already exists!");
            return;
        }

        System.out.print("Enter Name: ");
        String name = sc.nextLine();
        int age = readInt("Enter Age: ");
        double marks = readMarks();

        Student student = new Student(rollNo, name, age, marks);
        students.add(student);
        System.out.println("\nStudent added successfully!");
    }

    static void viewStudents() {
        System.out.println("\n--- All Students ---");

        if (students.isEmpty()) {
            System.out.println("No students available.");
            return;
        }

        for (Student student : students) {
            student.display();
        }

        System.out.println("--------------------------------");
        System.out.println("Total Students: " + students.size());
    }

    static void searchStudent() {
        System.out.println("\n--- Search Student ---");
        int rollNo = readInt("Enter Roll No: ");
        Student student = findStudent(rollNo);

        if (student != null) {
            System.out.println("\nStudent Found!");
            student.display();
        } else {
            System.out.println("Student not found.");
        }
    }

    static void updateStudent() {
        System.out.println("\n--- Update Student ---");
        int rollNo = readInt("Enter Roll No: ");
        Student student = findStudent(rollNo);

        if (student == null) {
            System.out.println("Student not found.");
            return;
        }

        System.out.print("Enter New Name: ");
        String name = sc.nextLine();
        int age = readInt("Enter New Age: ");
        double marks = readMarks();

        student.setName(name);
        student.setAge(age);
        student.setMarks(marks);

        System.out.println("\nStudent updated successfully!");
    }

    static void deleteStudent() {
        System.out.println("\n--- Delete Student ---");
        int rollNo = readInt("Enter Roll No: ");
        Student student = findStudent(rollNo);

        if (student != null) {
            students.remove(student);
            System.out.println("Student deleted successfully!");
        } else {
            System.out.println("Student not found.");
        }
    }

    static void showStatistics() {
        System.out.println("\n--- Student Statistics ---");

        if (students.isEmpty()) {
            System.out.println("No student data available.");
            return;
        }

        double totalMarks = 0;
        double highest = students.get(0).getMarks();
        double lowest = students.get(0).getMarks();
        int pass = 0;
        int fail = 0;

        for (Student student : students) {
            double marks = student.getMarks();
            totalMarks += marks;

            if (marks > highest) highest = marks;
            if (marks < lowest) lowest = marks;

            if (marks >= 40) pass++;
            else fail++;
        }

        double average = totalMarks / students.size();

        System.out.println("Total Students : " + students.size());
        System.out.println("Average Marks  : " + average);
        System.out.println("Highest Marks  : " + highest);
        System.out.println("Lowest Marks   : " + lowest);
        System.out.println("Passed         : " + pass);
        System.out.println("Failed         : " + fail);
    }

    static Student findStudent(int rollNo) {
        for (Student student : students) {
            if (student.getRollNo() == rollNo) return student;
        }
        return null;
    }

    static int readInt(String message) {
        while (true) {
            try {
                System.out.print(message);
                return Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }

    static double readMarks() {
        while (true) {
            try {
                System.out.print("Enter Marks (0-100): ");
                double marks = Double.parseDouble(sc.nextLine());

                if (marks >= 0 && marks <= 100) return marks;

                System.out.println("Marks must be between 0 and 100.");
            } catch (NumberFormatException e) {
                System.out.println("Please enter valid marks.");
            }
        }
    }
}

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Represents a person with a name.
 * Used as a base class for common attributes.
 */
abstract class Person {  // Abstract class (Abstraction)
    private String name;  // Encapsulation: private attribute

    public Person(String name) {
        this.name = name;
    }

    // Getter and setter for name (Encapsulation)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Abstract method (Abstraction)
    public abstract String getDetails();
}

/**
 * Represents a student with an ID, name, and list of enrolled courses.
 * Inherits from Person class (Inheritance).
 */
class Student extends Person {
    private String studentId;   // Encapsulation: private attribute
    private List<String> enrolledCourses;  // Encapsulation

    // Constructor (Inheritance + Encapsulation)
    public Student(String studentId, String name) {
        super(name);  // Call to superclass constructor
        this.studentId = studentId;
        this.enrolledCourses = new ArrayList<>();
    }

    // Getter for studentId (Encapsulation)
    public String getStudentId() {
        return studentId;
    }

    // Getter for enrolledCourses (Encapsulation)
    public List<String> getEnrolledCourses() {
        return enrolledCourses;
    }

    // Enroll in a course (Encapsulation)
    public void enrollInCourse(String courseName) {
        enrolledCourses.add(courseName);
    }

    // Overridden method from Person (Polymorphism)
    @Override
    public String getDetails() {
        return "Student ID: " + studentId + ", Name: " + getName();
    }
}

/**
 * Represents a university with a list of enrolled students and offered courses.
 */
class GoaUniversity {
    private String universityName;  // Encapsulation
    private List<Student> enrolledStudents;  // Encapsulation
    private List<String> offeredCourses;  // Encapsulation

    // Constructor
    public GoaUniversity(String universityName) {
        this.universityName = universityName;
        this.enrolledStudents = new ArrayList<>();
        this.offeredCourses = new ArrayList<>();
    }

    // Getter for universityName (Encapsulation)
    public String getUniversityName() {
        return universityName;
    }

    // Method to add a student
    public void addStudent(String studentId, String name) {
        Student student = new Student(studentId, name);  // Encapsulation + Inheritance
        enrolledStudents.add(student);
    }

    // Method to add a course
    public void addCourse(String courseName) {
        offeredCourses.add(courseName);
    }

    // Enroll a student in a course
    public void enrollStudentInCourse(String studentId, String courseName) {
        for (Student student : enrolledStudents) {
            if (student.getStudentId().equals(studentId)) {
                student.enrollInCourse(courseName);
                return;
            }
        }
        System.out.println("Student with ID " + studentId + " not found.");
    }

    // Display information about all students
    public void displayAllInformation() {
        System.out.println(universityName);
        System.out.println("----------------");
        for (Student student : enrolledStudents) {
            System.out.println(student.getDetails());  // Polymorphism
            System.out.println("Enrolled Courses:");
            if (student.getEnrolledCourses().isEmpty()) {
                System.out.println("  - None");
            } else {
                for (String course : student.getEnrolledCourses()) {
                    System.out.println("---> " + course);
                }
            }
            System.out.println();
        }
    }
}

/**
 * Main class for running the GoaUniversity application.
 */
public class GoaUniversityMain {
    /**
     * Entry point of the application.
     * 
     * @param args Command-line arguments.
     */
    public static void main(String[] args) {
        GoaUniversity goaUniversity = new GoaUniversity("Goa University");
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Add Student");
            System.out.println("2. Enroll Student in Course");
            System.out.println("3. Display Information");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter student ID: ");
                    String studentId = scanner.nextLine();
                    System.out.print("Enter student name: ");
                    String name = scanner.nextLine();
                    goaUniversity.addStudent(studentId, name);
                    break;
                case 2:
                    System.out.print("Enter student ID: ");
                    studentId = scanner.nextLine();
                    System.out.print("Enter course name: ");
                    String courseName = scanner.nextLine();
                    goaUniversity.enrollStudentInCourse(studentId, courseName);
                    break;
                case 3:
                    goaUniversity.displayAllInformation();
                    break;
                case 4:
                    System.out.println("Exiting...");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}

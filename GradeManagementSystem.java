/*
 * This program reads course data from a file and calculates final grades for students based on given categories.
 * Each category has a specific weight.
 * It assigns letter grades, and calculates the class statistics (average, minimum, maximum).
 * It outputs the summary and results to a file.
 * Grading Scale: A: 90-100, B: 80-89, C: 70-79, D: 60-69, F: <60.
 */

import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class GradeManagementSystem {

    public static void main(String[] args) {

        // Array categories and percentage weights for CS1050
        final int TOTAL_CATEGORIES_1050 = 5;
        String[] categories1050 = {"Class Participation", "Guided Exploration", "Quizzes", "Project Percent", "Final Exam"};
        double[] percentWeights1050 = {.12, .22, .22, .22, .22};
        int MAX_STUDENTS_1050 = 10;

        Course course1050 = new Course("CS1050", categories1050, percentWeights1050, MAX_STUDENTS_1050);

        try {
            System.out.println("\nSetting up course " + course1050.getCourseName());
            courseSetUp(course1050, TOTAL_CATEGORIES_1050, "course1050.txt");
            System.out.println("\nTotal Students in " + course1050.getCourseName() + ": " + course1050.getNumberStudents());
            course1050.displayCourseGrading();
            course1050.postFinalGrades();
            course1050.displayCourseSummary();
            course1050.writeSummaryToFile();

        } catch (FileNotFoundException e) {
            System.out.println("Error: Can't Upload course information" + e.getMessage());
        }

        // Array categories and percentage weights for CS2040
        final int TOTAL_CATEGORIES_2040 = 4;
        String[] categories2040 = {"Class Participation", "Homework", "Midterm", "Final Exam"};
        double[] percentWeights2040 = {.15, .25, .3, .3};
        int MAX_STUDENTS_2040 = 4;

        Course course2040 = new Course("CS2040", categories2040, percentWeights2040, MAX_STUDENTS_2040);

        try {
            System.out.println("\nSetting up course " + course2040.getCourseName());
            courseSetUp(course2040, TOTAL_CATEGORIES_2040, "course2040.txt");
            System.out.println("\nTotal Students in " + course2040.getCourseName() + ": " + course2040.getNumberStudents());
            course2040.displayCourseGrading();
            course2040.postFinalGrades();
            course2040.displayCourseSummary();
            course2040.writeSummaryToFile();

        } catch (FileNotFoundException e) {
            System.out.println("Error: Can't Upload course information" + e.getMessage());
        }

    } // end of main

    // Method to set up the course by reading course information from a file and assigning it
    public static void courseSetUp(Course course, int totalCategories, String fileName) throws FileNotFoundException {
        Scanner fileScanner = new Scanner(new File(fileName));

        // Set instructor
        String instructorLine = fileScanner.nextLine().trim();
        Scanner instructorScanner = new Scanner(instructorLine);
        String firstName = instructorScanner.next().trim();
        String lastName = instructorScanner.next().trim();
        String email = instructorScanner.next().trim();
        course.setInstructor(new Instructor(firstName, lastName, email));
        instructorScanner.close();

        // Add students
        while (fileScanner.hasNextLine()) {
            String studentLine = fileScanner.nextLine().trim();
            Scanner studentScanner = new Scanner(studentLine);
            String studentFirstName = studentScanner.next().trim();
            String studentLastName = studentScanner.next().trim();
            String studentEmail = studentScanner.next().trim();

            // Get grades using a loop
            double[] grades = new double[totalCategories];
            for (int i = 0; i < totalCategories; i++) {
                grades[i] = Double.parseDouble(studentScanner.next().trim());
            }

            // Add student to course
            course.addStudent(new Student(studentFirstName, studentLastName, studentEmail, grades));
            studentScanner.close();
        }

        fileScanner.close();
    }//end of courseSetUp
}// end of FinalClassProject

// Base class for Instructor and Student
class Person {
    private final String firstName;
    private final String lastName;
    private final String email;

    public Person(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    // Getter for firstName
    public String getFirstName() {
        return firstName;
    }

    // Getter for lastName
    public String getLastName() {
        return lastName;
    }

    // Getter for email
    public String getEmail() {
        return email;
    }

    public String toString() {
        return firstName + " " + lastName + " " + email;
    }
}//end Person Class

// Instructor class inherits from Person
class Instructor extends Person {
    public Instructor(String firstName, String lastName, String email) {
        super(firstName, lastName, email);
    }
}// end Instructor Class

// Student class inherits from Person
class Student extends Person {
    private final double[] grades;
    private double finalGrade;

    public Student(String firstName, String lastName, String email, double[] grades) {
        super(firstName, lastName, email);
        this.grades = grades;
    }

    // Getter for grades
    public double[] getGrades() {
        return grades;
    }

    // Setter for final grade
    public void setFinalGrade(double finalGrade) {
        this.finalGrade = finalGrade;
    }

    // Getter for final grade
    public double getFinalGrade() {
        return finalGrade;
    }

    public String toString() {
        return getFirstName() + " " + getLastName() + ": " + String.format("%.2f", finalGrade) + "  " + Course.determineLetterGrade(finalGrade);
    }
}//end Student Class

// Course class represents a course with instructor, students, and categories with weights
class Course {
    private final String courseName;
    private final String[] categories;
    private final double[] weights;
    private final Student[] students;
    private Instructor instructor;
    private int numberStudents = 0;

    public Course(String courseName, String[] categories, double[] weights, int maxStudents) {
        this.courseName = courseName;
        this.categories = categories;
        this.weights = weights;
        this.students = new Student[maxStudents];
    }

    // Method to add a student to the course, only if there is space available
    public void addStudent(Student student) {
        if (numberStudents < students.length) {
            students[numberStudents++] = student;
        } else {
            System.out.println("Course is full. Couldn't add student: " +
                    student.getFirstName() + " " + student.getLastName() + " " + student.getEmail());
        }
    }//end addStudent

    // Setter for instructor for the course
    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }

    // Getter for instructor for the course
    public Instructor getInstructor() {
        return instructor;
    }

    // Getter for the course name
    public String getCourseName() {
        return courseName;
    }

    // Getter for the number of students enrolled in the course
    public double getNumberStudents() {
        return numberStudents;
    }

    // Method to calculate the final grade based on category weights for each student
    public double calculateFinalGrade(Student student) {
        double[] grades = student.getGrades(); // Get the student's grades
        double total = 0;
        for (int i = 0; i < grades.length; i++) {
            total += grades[i] * weights[i];
        }
        return total;
    }

    // Method to post final grades for each student
    public void postFinalGrades() {
        for (int index = 0; index < numberStudents; index++) {
            double finalGrade = calculateFinalGrade(students[index]);
            students[index].setFinalGrade(finalGrade);
        }
    }//end postFinalGrades

    // Method to determine the letter grade based on the final grade
    public static char determineLetterGrade(double finalGrade) {
        char letterGrade;
        if (finalGrade >= 90) {
            letterGrade = 'A';
        } else if (finalGrade < 90 && finalGrade >= 80) {
            letterGrade = 'B';
        } else if (finalGrade < 80 && finalGrade >= 70) {
            letterGrade = 'C';
        } else if (finalGrade < 70 && finalGrade >= 60) {
            letterGrade = 'D';
        } else {
            letterGrade = 'F';
        }
        return letterGrade;
    }

    // Method to display the course grading information
    public void displayCourseGrading() {
        System.out.println("\n**********************************");
        System.out.println(getCourseName() + " Final Grade Calculator");
        System.out.println("Instructor Name:" + getInstructor());
        System.out.println("**********************************");
        System.out.println("------------------------------");
        System.out.println("Category:Percent");
        System.out.println("------------------------------");
        for (int i = 0; i < categories.length; i++) {
            System.out.println(categories[i] + " Grade: " + weights[i]);
        }
        System.out.println("\n-------------------------------");
        System.out.println("Letter Grade Range");
        System.out.println("-------------------------------");
        System.out.println("A: 90 to 100");
        System.out.println("B: 80 to < 90");
        System.out.println("C: 70 to < 80");
        System.out.println("D: 60 to < 70");
        System.out.println("F: < 60");
    }//end displayCourseGrading

    // Method to calculate the average final grade for all students in the course
    public static double calculateClassAverage(double[] finalGrades) {
        double sum = 0;
        for (int i = 0; i < finalGrades.length; i++) {
            sum += finalGrades[i];
        }
        return sum / finalGrades.length;
    }// end calculateClassAverage

    // Method to calculate the class minimum final grade among all students in the course
    public static double calculateClassMin(double[] finalGrades) {
        double min = finalGrades[0];
        for (int i = 1; i < finalGrades.length; i++) {
            if (finalGrades[i] < min) {
                min = finalGrades[i];
            }
        }
        return min;
    } // end calculateClassMin

    // // Method to calculate the class maximum final grade among all students in the course
    public static double calculateClassMax(double[] finalGrades) {
        double max = finalGrades[0];
        for (int i = 1; i < finalGrades.length; i++) {
            if (finalGrades[i] > max) {
                max = finalGrades[i];
            }
        }
        return max;
    }// end calculateClassMax

    // Method to displays the course summary including instructor's name, student grades, and class statistics
    public void displayCourseSummary() {
        System.out.println("\n***** " + getCourseName() + " Final Grades *******");
        System.out.println("Instructor Name:" + getInstructor() + "\n");

        // Array to hold final grades of students
        double[] finalGrades = new double[numberStudents];

        // Loop through students, print their details, and store their final grades
        for (int i = 0; i < numberStudents; i++) {
            if (students[i] != null) {
                System.out.println(students[i]);
                finalGrades[i] = students[i].getFinalGrade();
            }
        }

        // Calling methods for class statistics
        double average = calculateClassAverage(finalGrades);
        double min = calculateClassMin(finalGrades);
        double max = calculateClassMax(finalGrades);

        System.out.printf("\nClass average: %.2f\n", average);
        System.out.printf("Class min: %.2f\n", min);
        System.out.printf("Class max: %.2f\n", max);

    }//end displayCourseSummary

    // Method to write the course summary to a file
    public void writeSummaryToFile() throws FileNotFoundException {
        PrintWriter writer = new PrintWriter(getCourseName() + "_summary.txt");
        writer.println(getCourseName() + " Final Grades");
        writer.println("Instructor Name:" + getInstructor() + "\n");

        // Same code from displayCourseSummary but writing to file instead of system
        double[] finalGrades = new double[numberStudents];

        for (int i = 0; i < numberStudents; i++) {
            if (students[i] != null) {
                writer.println(students[i]);
                finalGrades[i] = students[i].getFinalGrade();
            }
        }

        double average = calculateClassAverage(finalGrades);
        double min = calculateClassMin(finalGrades);
        double max = calculateClassMax(finalGrades);

        writer.printf("\nClass average: %.2f\n", average);
        writer.printf("Class min: %.2f\n", min);
        writer.printf("Class max: %.2f\n", max);

        File courseFile = new File(getCourseName() + "_summary.txt");
        System.out.println("\nFile located at " + courseFile.getAbsolutePath());

        writer.close();
    }//end writeSummaryToFile

}// end Course class

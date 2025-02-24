package org.hina.buoi5.Student;

import java.io.File;
import java.util.Scanner;

public class StudentSolve {
    private StudentManaget studentManaget;

    public StudentSolve() {
        this.studentManaget = new StudentManaget();

        try (Scanner scanner = new Scanner(new File("src/main/java/org/hina/buoi5/Student/sv.csv"))) {
            while (scanner.hasNextLine()) {
                studentManaget.add(new Student(scanner.nextLine()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Student getStudent(String string) {
        return studentManaget.getStudent(string);
    }

    public static void main(String[] args) {
        StudentSolve solve = new StudentSolve();
        System.out.println(solve.getStudent("123"));
    }
}

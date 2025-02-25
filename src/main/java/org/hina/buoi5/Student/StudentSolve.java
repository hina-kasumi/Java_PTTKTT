package org.hina.buoi5.Student;

import java.io.File;
import java.util.Scanner;

public class StudentSolve {
    private StudentManager studentManaget;

    public StudentSolve() {
        this.studentManaget = new StudentManager();

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
        solve.getStudent("123").addDiem(new Mon("toan", 1), 3);
        solve.getStudent("123").addDiem(new Mon("hoa", 3), 6);
        solve.getStudent("123").addDiem(new Mon("van", 2), 4);
        System.out.println(solve.getStudent("123").diemTBC());
    }
}

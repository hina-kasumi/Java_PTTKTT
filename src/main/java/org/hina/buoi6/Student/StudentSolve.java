package org.hina.buoi6.Student;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StudentSolve {
    private StudentManager studentManaget;
    private List<BangDiem> list;

    public StudentSolve() {
        this.studentManaget = new StudentManager();
        list = new ArrayList<>();

        try (Scanner scanner = new Scanner(new File("src/main/java/org/hina/buoi5/Student/sv.csv"))) {
            while (scanner.hasNextLine()) {
                studentManaget.add(new Student(scanner.nextLine()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        list.add(new BangDiem("src/main/java/org/hina/buoi6/Student/toan34.csv"));
        list.add(new BangDiem("src/main/java/org/hina/buoi6/Student/hoa21.csv"));

        for (BangDiem bangDiem : list) {
            bangDiem.addDiem(studentManaget);
        }
    }

    public Student getStudent(String string) {
        return studentManaget.getStudent(string);
    }

    public static void main(String[] args) {
        StudentSolve solve = new StudentSolve();
        
        System.out.println(solve.getStudent("123456").diemTBC());
        System.out.println(solve.studentManaget.getTbLop());
    }
}

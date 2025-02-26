package org.hina.buoi6.Student;

import org.hina.buoi5.RedBlackBST;

public class StudentManager {
    private final RedBlackBST<String, Student> studentManager;

    public StudentManager() {
        this.studentManager = new RedBlackBST<>();
    }

    public void add(Student student) {
        studentManager.put(student.getMsv(), student);
    }

    public void del(String msv) {
        studentManager.delete(msv);
    }

    public Student getStudent(String msv) {
        return studentManager.get(msv);
    }

    public RedBlackBST<String, Student> getStudentManager() {
        return studentManager;
    }

    public float getTBCHk(int hocKy) {
        float res = 0;
        for (var x : studentManager.keys()) {
            res += studentManager.get(x).tbHK(hocKy);
        }

        return res / studentManager.size();
    }

    public float getTbLop() {
        float res = 0;
        for (var x : studentManager.keys()) {
            Student student = studentManager.get(x);
            res += student.diemTBC();
        }

        return res / studentManager.size();
    }
}

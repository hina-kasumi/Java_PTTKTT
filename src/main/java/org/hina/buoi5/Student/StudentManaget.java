package org.hina.buoi5.Student;

import org.hina.buoi5.RedBlackBST;

public class StudentManaget {
    private RedBlackBST<String, Student> studentManager;

    public StudentManaget() {
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
}

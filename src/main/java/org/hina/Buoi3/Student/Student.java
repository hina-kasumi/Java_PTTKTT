package org.hina.Buoi3.Student;

import java.util.PriorityQueue;
import java.util.Stack;

public class Student implements Comparable<Student> {
    private final int msv;
    private final String name;
    private final float diem;

    public Student(int msv, String name, float diem) {
        this.msv = msv;
        this.name = name;
        this.diem = diem;
    }

    @Override
    public String toString() {
        return msv + " " + name + " " + diem;
    }

    @Override
    public int compareTo(Student o) {
        return this.msv - o.msv;
    }

    public static void main(String[] args) {
        Student[] a = new Student[]{
                new Student(1, "hieu", 10),
                new Student(7, "hai", 1),
                new Student(2, "phong", 2.7f),
                new Student(9, "tung", 5.5f),
                new Student(11, "ho", 9),
                new Student(5, "duong", 8),
        };

        PriorityQueue<Student> pq = new PriorityQueue<>();
        int m = 5;
        for (var x : a) {
            pq.add(x);
            if (pq.size() > m)
                pq.poll();
        }

        Stack<Student> stack = new Stack<>();
        while (!pq.isEmpty())
            stack.add(pq.poll());
        while (!stack.isEmpty())
            System.out.println(stack.pop());
    }
}

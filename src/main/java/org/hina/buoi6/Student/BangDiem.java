package org.hina.buoi6.Student;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.hina.buoi5.RedBlackBST;

public class BangDiem {
    private RedBlackBST<String, Float> diem;
    private Mon mon;

    public BangDiem(String path) {
        diem = new RedBlackBST<>();

        File file = new File(path);

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String[] s = scanner.nextLine().split(",");
                for (int i = 0; i < s.length; i++) {
                    s[i] = s[i].trim();
                }
                diem.put(s[0], Float.parseFloat(s[1]));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        String fileName = file.getName().replace(".csv", "");

        Pattern pattern = Pattern.compile("([a-zA-Z]+)(\\d+)"); // Tạo pattern
        Matcher matcher = pattern.matcher(fileName); // Tạo matcher để kiểm tra

        if (matcher.matches()) {
            int num = Integer.parseInt(matcher.group(2));
            mon = new Mon(matcher.group(1), num / 10, num % 10);
        } else {
            System.out.println("Không khớp!");
        }
    }

    public void addDiem(StudentManager studentManager) {
        RedBlackBST<String, Student> stStudent = studentManager.getStudentManager();
        for (String msv : stStudent.keys()) {
            Student bangDiemStudent = stStudent.get(msv);

            if (diem.contains(msv)) {
                bangDiemStudent.addDiem(mon, diem.get(msv));
            }
        }
    }

    public void getBangDiem() {
        for (var key : diem.keys()) {
            System.out.println(key + " " + diem.get(key));
        }
    }
}

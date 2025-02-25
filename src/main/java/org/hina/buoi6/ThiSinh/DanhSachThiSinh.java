package org.hina.buoi6.ThiSinh;

import java.io.File;
import java.util.HashMap;
import java.util.Scanner;

import org.hina.buoi5.RedBlackBST;

public class DanhSachThiSinh {
    private final HashMap<ThiSinh, RedBlackBST<String, Float>> hashMap;

    public DanhSachThiSinh() {
        hashMap = new HashMap<>();
    }

    public void addThiSinh(ThiSinh thiSinh) {
        hashMap.put(thiSinh, null);
    }

    public void add(ThiSinh thiSinh, String mon, float diem) {
        var bst = hashMap.get(thiSinh);
        if (bst == null) {
            bst = new RedBlackBST<>();
            bst.put(mon, diem);
            hashMap.put(thiSinh, bst);
        } else {
            bst.put(mon, diem);
        }
    }

    public void getDiemThiSinh(ThiSinh thiSinh) {
        var st = hashMap.get(thiSinh);
        if (!hashMap.containsKey(thiSinh)) {
            System.out.println("không có sinh viên");
            return;
        }
        System.out.println(thiSinh);
        for (var x : st.keys())
            System.out.println(x + " " + st.get(x));
    }

    public static void main(String[] args) {
        DanhSachThiSinh ds = new DanhSachThiSinh();
        try (Scanner scanner = new Scanner(new File("src/main/java/org/hina/buoi6/ThiSinh/thisinh.csv"))) {
            while (scanner.hasNextLine()) {
                String[] s = scanner.nextLine().split(",");
                ThiSinh thiSinh = new ThiSinh(s[0] + "," + s[1] + "," + s[2] + "," + s[3]);
                ds.add(thiSinh, "toan", Float.parseFloat(s[4]));
                ds.add(thiSinh, "ly", Float.parseFloat(s[5]));
                ds.add(thiSinh, "hoa", Float.parseFloat(s[6]));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        ds.getDiemThiSinh(new ThiSinh("Phạm Thị,D,12/12/2000,23"));
    }
}

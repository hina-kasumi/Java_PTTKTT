package org.hina.buoi5.Student;

import org.hina.Buoi3.Transaction.Date;
import org.hina.buoi5.RedBlackBST;

public class Student {
    private String msv;
    private String hoDem;
    private String ten;
    private Date ngaySinh;
    private String que;
    private RedBlackBST<Mon, Float> bangDiem;

    public Student(String msv, String hoDem, String ten, Date ngaySinh, String que) {
        this.msv = msv;
        this.hoDem = hoDem;
        this.ten = ten;
        this.ngaySinh = ngaySinh;
        this.que = que;
        bangDiem = new RedBlackBST<>();
    }

    public Student(String string) {
        String[] strings = string.split(",");
        for (int i = 0; i < strings.length; i++) {
            strings[i] = strings[i].trim();
        }
        this.msv = strings[0];
        this.hoDem = strings[1];
        this.ten = strings[2];
        this.ngaySinh = new Date(strings[3]);
        this.que = strings[4];
        bangDiem = new RedBlackBST<>();
    }

    public void addDiem(Mon mon, float diem) {
        bangDiem.put(mon, diem);
    }

    public float diemTBC() {
        float res = 0;
        int soMon = 0;
        for (var x : bangDiem.keys()) {
            res += bangDiem.get(x) * x.getSoTin();
            soMon += x.getSoTin();
        }
        return res / soMon;
    }

    public RedBlackBST<Mon, Float> getDiem() {
        return bangDiem;
    }

    public String getMsv() {
        return msv;
    }

    @Override
    public String toString() {
        return "msv=" + msv + ", hoDem=" + hoDem + ", ten=" + ten + ", ngaySinh=" + ngaySinh + ", que=" + que;
    }
}

package org.hina.buoi5.Student;

import org.hina.Buoi3.Transaction.Date;

public class Student {
    private String msv;
    private String hoDem;
    private String ten;
    private Date ngaySinh;
    private String que;

    public Student(String msv, String hoDem, String ten, Date ngaySinh, String que) {
        this.msv = msv;
        this.hoDem = hoDem;
        this.ten = ten;
        this.ngaySinh = ngaySinh;
        this.que = que;
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
    }

    public String getMsv() {
        return msv;
    }

    @Override
    public String toString() {
        return "msv=" + msv + ", hoDem=" + hoDem + ", ten=" + ten + ", ngaySinh=" + ngaySinh + ", que=" + que;
    }

}

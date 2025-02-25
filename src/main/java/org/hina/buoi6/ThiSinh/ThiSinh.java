package org.hina.buoi6.ThiSinh;

import org.hina.Buoi3.Transaction.Date;

public class ThiSinh {
    private String hoDem;
    private String ten;
    private Date ngaySinh;
    private float dtbcpt;

    public ThiSinh(String hoDem, String ten, String ngaySinh, float dtbcpt) {
        this.hoDem = hoDem;
        this.ten = ten;
        this.ngaySinh = new Date(ngaySinh);
        this.dtbcpt = dtbcpt;
    }

    public ThiSinh(String string) {
        String[] data = string.split(",");
        for (int i = 0; i < data.length; i++) {
            data[i] = data[i].trim();
        }

        this.hoDem = data[0];
        this.ten = data[1];
        this.ngaySinh = new Date(data[2]);
        this.dtbcpt = Float.parseFloat(data[3]);
    }

    public String getHoDem() {
        return hoDem;
    }

    public String getTen() {
        return ten;
    }

    public Date getNgaySinh() {
        return ngaySinh;
    }

    public float getDtbcpt() {
        return dtbcpt;
    }

    @Override
    public int hashCode() {
        int hash = 7; // Số nguyên khởi tạo
        hash = 31 * hash + hoDem.hashCode();
        hash = 31 * hash + ten.hashCode();
        hash = 31 * hash + ngaySinh.toString().hashCode();
        hash = 31 * hash + Float.floatToIntBits(dtbcpt);
        return hash;
    }

    @Override
    public String toString() {
        return "ThiSinh [hoDem=" + hoDem + ", ten=" + ten + ", ngaySinh=" + ngaySinh + ", dtbcpt=" + dtbcpt + "]";
    }

    @Override
    public boolean equals(Object obj) {
        return this.hashCode() == obj.hashCode();
    }
}

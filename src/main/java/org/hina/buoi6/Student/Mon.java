package org.hina.buoi6.Student;

public class Mon implements Comparable<Mon> {
    private final String tenMon;
    private final int soTin;
    private final int kyThu;

    public Mon(String tenMon, int soTin, int kyThu) {
        this.tenMon = tenMon;
        this.soTin = soTin;
        this.kyThu = kyThu;
    }

    public String getTenMon() {
        return tenMon;
    }

    public int getSoTin() {
        return soTin;
    }

    public int getKyThu() {
        return kyThu;
    }

    @Override
    public int compareTo(Mon o) {
        return tenMon.compareTo(o.tenMon);
    }
}

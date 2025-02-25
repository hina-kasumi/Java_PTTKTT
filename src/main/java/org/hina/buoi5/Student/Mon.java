package org.hina.buoi5.Student;

public class Mon implements Comparable<Mon> {
    private final String tenMon;
    private final int soTin;

    public Mon(String tenMon, int soTin) {
        this.tenMon = tenMon;
        this.soTin = soTin;
    }

    public String getTenMon() {
        return tenMon;
    }

    public int getSoTin() {
        return soTin;
    }

    @Override
    public int compareTo(Mon o) {
        return tenMon.compareTo(o.tenMon);
    }
}

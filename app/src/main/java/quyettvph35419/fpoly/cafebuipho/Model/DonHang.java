package quyettvph35419.fpoly.cafebuipho.Model;

import java.util.Date;

public class DonHang {
    private int maDH;
    private String maKH;
    private int gia;
    private int soLuong;
    private int trangThai;



    public DonHang() {
    }

    public DonHang(int maDH, String maKH, int gia, int soLuong, int trangThai) {
        this.maDH = maDH;
        this.maKH = maKH;
        this.gia = gia;
        this.soLuong = soLuong;
        this.trangThai = trangThai;
    }

    public int getMaDH() {
        return maDH;
    }

    public void setMaDH(int maDH) {
        this.maDH = maDH;
    }

    public String getMaKH() {
        return maKH;
    }

    public void setMaKH(String maKH) {
        this.maKH = maKH;
    }

    public int getGia() {
        return gia;
    }

    public void setGia(int gia) {
        this.gia = gia;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }
}

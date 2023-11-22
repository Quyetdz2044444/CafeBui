package quyettvph35419.fpoly.cafebuipho.Model;

import java.util.Date;

public class DonHang {
    private int maDH;
    private String maKH;
    private String ngay;
    private int gia;
    private String thanhToan;
    private int trangThai;

    public DonHang() {
    }

    public DonHang(int maDH, String maKH, String ngay, int gia, String thanhToan, int trangThai) {
        this.maDH = maDH;
        this.maKH = maKH;
        this.ngay = ngay;
        this.gia = gia;
        this.thanhToan = thanhToan;
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

    public String getNgay() {
        return ngay;
    }

    public void setNgay(String ngay) {
        this.ngay = ngay;
    }

    public int getGia() {
        return gia;
    }

    public void setGia(int gia) {
        this.gia = gia;
    }

    public String getThanhToan() {
        return thanhToan;
    }

    public void setThanhToan(String thanhToan) {
        this.thanhToan = thanhToan;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }
}

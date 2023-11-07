package quyettvph35419.fpoly.cafebuipho.Model;

import java.util.Date;

public class HoaDon {
    private int maHoaDon;
    private String maQuanLy;
    private int maNV;
    private int maDoUong;
    private int maBan;
    private int gia;
    private int soLuong;
    private Date ngay;
    private int trangThai;

    public HoaDon() {
    }

    public HoaDon(int maHoaDon, String maQuanLy, int maNV, int maDoUong,
                  int maBan, int gia, int soLuong, Date ngay, int trangThai) {
        this.maHoaDon = maHoaDon;
        this.maQuanLy = maQuanLy;
        this.maNV = maNV;
        this.maDoUong = maDoUong;
        this.maBan = maBan;
        this.gia = gia;
        this.soLuong = soLuong;
        this.ngay = ngay;
        this.trangThai = trangThai;
    }

    public int getMaHoaDon() {
        return maHoaDon;
    }

    public void setMaHoaDon(int maHoaDon) {
        this.maHoaDon = maHoaDon;
    }

    public String getMaQuanLy() {
        return maQuanLy;
    }

    public void setMaQuanLy(String maQuanLy) {
        this.maQuanLy = maQuanLy;
    }

    public int getMaNV() {
        return maNV;
    }

    public void setMaNV(int maNV) {
        this.maNV = maNV;
    }

    public int getMaDoUong() {
        return maDoUong;
    }

    public void setMaDoUong(int maDoUong) {
        this.maDoUong = maDoUong;
    }

    public int getMaBan() {
        return maBan;
    }

    public void setMaBan(int maBan) {
        this.maBan = maBan;
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

    public Date getNgay() {
        return ngay;
    }

    public void setNgay(Date ngay) {
        this.ngay = ngay;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }
}

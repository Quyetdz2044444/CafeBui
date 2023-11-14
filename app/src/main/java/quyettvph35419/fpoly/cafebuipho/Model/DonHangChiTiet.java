package quyettvph35419.fpoly.cafebuipho.Model;

import java.util.Date;

public class DonHangChiTiet {
    private int maDHCT;
    private int maDoUong;
    private int maDH;
    private int maSize;
    private int soLuong;
    private Date ngay;
    private int trangThai;
    private int tongTien;

    public DonHangChiTiet() {
    }

    public DonHangChiTiet(int maDHCT, int maDoUong, int maDH, int maSize, int soLuong, Date ngay, int trangThai, int tongTien) {
        this.maDHCT = maDHCT;
        this.maDoUong = maDoUong;
        this.maDH = maDH;
        this.maSize = maSize;
        this.soLuong = soLuong;
        this.ngay = ngay;
        this.trangThai = trangThai;
        this.tongTien = tongTien;
    }

    public int getMaDHCT() {
        return maDHCT;
    }

    public void setMaDHCT(int maDHCT) {
        this.maDHCT = maDHCT;
    }

    public int getMaDoUong() {
        return maDoUong;
    }

    public void setMaDoUong(int maDoUong) {
        this.maDoUong = maDoUong;
    }

    public int getMaDH() {
        return maDH;
    }

    public void setMaDH(int maDH) {
        this.maDH = maDH;
    }

    public int getMaSize() {
        return maSize;
    }

    public void setMaSize(int maSize) {
        this.maSize = maSize;
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

    public int getTongTien() {
        return tongTien;
    }

    public void setTongTien(int tongTien) {
        this.tongTien = tongTien;
    }
}

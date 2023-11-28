package quyettvph35419.fpoly.cafebuipho.Model;

import java.util.Date;

public class DonHangChiTiet {
    private int maDHCT;
    private int maDoUong;
    private int maDH;
    private int maSize;
    private int soLuong;
    private String ngay;
    private String thanhToan;

    private int tongTien;

    public DonHangChiTiet() {
    }

    public DonHangChiTiet(int maDHCT, int maDoUong, int maDH, int maSize, int soLuong,
                          String ngay, String thanhToan, int tongTien) {
        this.maDHCT = maDHCT;
        this.maDoUong = maDoUong;
        this.maDH = maDH;
        this.maSize = maSize;
        this.soLuong = soLuong;
        this.ngay = ngay;
        this.thanhToan = thanhToan;
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

    public String getNgay() {
        return ngay;
    }

    public void setNgay(String ngay) {
        this.ngay = ngay;
    }

    public String getThanhToan() {
        return thanhToan;
    }

    public void setThanhToan(String thanhToan) {
        this.thanhToan = thanhToan;
    }



    public int getTongTien() {
        return tongTien;
    }

    public void setTongTien(int tongTien) {
        this.tongTien = tongTien;
    }
}

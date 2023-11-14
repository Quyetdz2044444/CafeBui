package quyettvph35419.fpoly.cafebuipho.Model;

public class GioHang {
    private int maGH;
    private int maDoUong;
    private int maSize;
    private int soLuong;
    private int tongTien;

    public GioHang() {
    }

    public GioHang(int maGH, int maDoUong, int maSize, int soLuong, int tongTien) {
        this.maGH = maGH;
        this.maDoUong = maDoUong;
        this.maSize = maSize;
        this.soLuong = soLuong;
        this.tongTien = tongTien;
    }

    public int getMaGH() {
        return maGH;
    }

    public void setMaGH(int maGH) {
        this.maGH = maGH;
    }

    public int getMaDoUong() {
        return maDoUong;
    }

    public void setMaDoUong(int maDoUong) {
        this.maDoUong = maDoUong;
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

    public int getTongTien() {
        return tongTien;
    }

    public void setTongTien(int tongTien) {
        this.tongTien = tongTien;
    }
}

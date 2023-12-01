package quyettvph35419.fpoly.cafebuipho.Model;

public class DanhGia {
    private int maDanhGia;
    private String tenKhachHang;
    private int maDoUong;
    private float soSao;
    private String noiDung;

    public DanhGia() {
    }

    public DanhGia(int maDanhGia, String tenKhachHang, int maDoUong, float soSao, String noiDung) {
        this.maDanhGia = maDanhGia;
        this.tenKhachHang = tenKhachHang;
        this.maDoUong = maDoUong;
        this.soSao = soSao;
        this.noiDung = noiDung;
    }

    public int getMaDanhGia() {
        return maDanhGia;
    }

    public void setMaDanhGia(int maDanhGia) {
        this.maDanhGia = maDanhGia;
    }

    public String getTenKhachHang() {
        return tenKhachHang;
    }

    public void setTenKhachHang(String tenKhachHang) {
        this.tenKhachHang = tenKhachHang;
    }

    public int getMaDoUong() {
        return maDoUong;
    }

    public void setMaDoUong(int maDoUong) {
        this.maDoUong = maDoUong;
    }

    public float getSoSao() {
        return soSao;
    }

    public void setSoSao(float soSao) {
        this.soSao = soSao;
    }

    public String getNoiDung() {
        return noiDung;
    }

    public void setNoiDung(String noiDung) {
        this.noiDung = noiDung;
    }
}

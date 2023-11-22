package quyettvph35419.fpoly.cafebuipho.Model;

public class LoaiDoUong {
    private int maLoai;
    private String tenLoai;
    private int imgloai;

    public LoaiDoUong() {
    }

    public LoaiDoUong(int maLoai, String tenLoai, int imgloai) {
        this.maLoai = maLoai;
        this.tenLoai = tenLoai;
        this.imgloai = imgloai;
    }

    public int getMaLoai() {
        return maLoai;
    }

    public void setMaLoai(int maLoai) {
        this.maLoai = maLoai;
    }

    public String getTenLoai() {
        return tenLoai;
    }

    public void setTenLoai(String tenLoai) {
        this.tenLoai = tenLoai;
    }

    public int getImgloai() {
        return imgloai;
    }

    public void setImgloai(int imgloai) {
        this.imgloai = imgloai;
    }
}

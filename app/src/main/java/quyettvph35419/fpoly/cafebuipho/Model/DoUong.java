package quyettvph35419.fpoly.cafebuipho.Model;

public class DoUong {
    private int maDoUong;
    private String tenDoUong;
    private int gia;
    private int maSize;
    private int imageId;
    private int maLoai;


    public DoUong() {
    }

    public DoUong(int maDoUong, String tenDoUong, int gia, int maSize, int imageId, int maLoai) {
        this.maDoUong = maDoUong;
        this.tenDoUong = tenDoUong;
        this.gia = gia;
        this.maSize = maSize;
        this.imageId = imageId;
    }


    public DoUong(String tenDoUong, int gia, int imageId) {
        this.maDoUong = maDoUong;
        this.tenDoUong = tenDoUong;
        this.gia = gia;
        this.imageId = imageId;
    }

    public int getMaDoUong() {
        return maDoUong;
    }

    public void setMaDoUong(int maDoUong) {
        this.maDoUong = maDoUong;
    }

    public String getTenDoUong() {
        return tenDoUong;
    }

    public void setTenDoUong(String tenDoUong) {
        this.tenDoUong = tenDoUong;
    }

    public int getGia() {
        return gia;
    }

    public void setGia(int gia) {
        this.gia = gia;
    }

    public int getMaSize() {
        return maSize;
    }

    public void setMaSize(int maSize) {
        this.maSize = maSize;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public int getMaLoai() {
        return maLoai;
    }

    public void setMaLoai(int maLoai) {
        this.maLoai = maLoai;
    }
}

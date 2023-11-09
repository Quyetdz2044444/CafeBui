package quyettvph35419.fpoly.cafebuipho.Model;

public class Ban {
    private int maBan;
    private int viTriBan;
    private int trangThai;

    public Ban() {
    }

    public Ban(int maBan, int viTriBan, int trangThai) {
        this.maBan = maBan;
        this.viTriBan = viTriBan;
        this.trangThai = trangThai;
    }

    public int getMaBan() {
        return maBan;
    }

    public void setMaBan(int maBan) {
        this.maBan = maBan;
    }

    public int getViTriBan() {
        return viTriBan;
    }

    public void setViTriBan(int viTriBan) {
        this.viTriBan = viTriBan;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }
}

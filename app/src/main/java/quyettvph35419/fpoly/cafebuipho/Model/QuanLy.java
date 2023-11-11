package quyettvph35419.fpoly.cafebuipho.Model;

public class QuanLy {
    private String maQL;
    private String hoTen;
    private String matKhau;
    private String sdt;
    private String email;


    public QuanLy() {
    }

    public QuanLy(String maQL, String hoTen, String matKhau, String sdt, String email) {
        this.maQL = maQL;
        this.hoTen = hoTen;
        this.matKhau = matKhau;
        this.sdt = sdt;
        this.email = email;
    }

    public String getMaQL() {
        return maQL;
    }

    public void setMaQL(String maQL) {
        this.maQL = maQL;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

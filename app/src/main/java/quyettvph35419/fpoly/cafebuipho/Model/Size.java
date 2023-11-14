package quyettvph35419.fpoly.cafebuipho.Model;

public class Size {
    private int maSize;
    private int size;
    private int giaSize;

    public Size() {
    }

    public Size(int maSize, int size, int giaSize) {
        this.maSize = maSize;
        this.size = size;
        this.giaSize = giaSize;
    }

    public int getMaSize() {
        return maSize;
    }

    public void setMaSize(int maSize) {
        this.maSize = maSize;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getGiaSize() {
        return giaSize;
    }

    public void setGiaSize(int giaSize) {
        this.giaSize = giaSize;
    }
}

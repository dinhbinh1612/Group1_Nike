package binhpdph44989.group1.group1.model;

public class Top {
    private int maGiay;
    private String tenGiay;
    private String loaiGiay;

    public Top(int maGiay, String tenGiay, String loaiGiay) {
        this.maGiay = maGiay;
        this.tenGiay = tenGiay;
        this.loaiGiay = loaiGiay;
    }

    public int getMaGiay() {
        return maGiay;
    }

    public void setMaGiay(int maGiay) {
        this.maGiay = maGiay;
    }

    public String getTenGiay() {
        return tenGiay;
    }

    public void setTenGiay(String tenGiay) {
        this.tenGiay = tenGiay;
    }

    public String getLoaiGiay() {
        return loaiGiay;
    }

    public void setLoaiGiay(String loaiGiay) {
        this.loaiGiay = loaiGiay;
    }
}

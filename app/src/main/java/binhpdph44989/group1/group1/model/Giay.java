package binhpdph44989.group1.group1.model;

public class Giay {
    private int maGiay;
    private String tenGiay;
    private String hinhAnh;

    private int size;
    private int giaBan;
    private int soLuong;

    public Giay(int maGiay, String tenGiay, String hinhAnh, int size, int giaBan, int soLuong) {
        this.maGiay = maGiay;
        this.tenGiay = tenGiay;
        this.hinhAnh = hinhAnh;
        this.size = size;
        this.giaBan = giaBan;
        this.soLuong = soLuong;
    }

    public Giay(String tenGiay, String hinhAnh, int size, int giaBan, int soLuong) {
        this.tenGiay = tenGiay;
        this.hinhAnh = hinhAnh;
        this.size = size;
        this.giaBan = giaBan;
        this.soLuong = soLuong;
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

    public String getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        this.hinhAnh = hinhAnh;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getGiaBan() {
        return giaBan;
    }

    public void setGiaBan(int giaBan) {
        this.giaBan = giaBan;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }
}

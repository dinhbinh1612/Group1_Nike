package binhpdph44989.group1.group1.model;

public class Giay {

    private int maGiay;
    private String hinhAnh;
    private String tenGiay;


    private int size;
    private int giaBan;
    private int soLuong;

    public Giay() {
    }

    public Giay(String hinhAnh, String tenGiay, int size, int giaBan, int soLuong) {
        this.hinhAnh = hinhAnh;
        this.tenGiay = tenGiay;
        this.size = size;
        this.giaBan = giaBan;
        this.soLuong = soLuong;
    }

    public Giay(int maGiay, String hinhAnh, String tenGiay, int size, int giaBan, int soLuong) {
        this.maGiay = maGiay;
        this.hinhAnh = hinhAnh;
        this.tenGiay = tenGiay;
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

    public String getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        this.hinhAnh = hinhAnh;
    }

    public String getTenGiay() {
        return tenGiay;
    }

    public void setTenGiay(String tenGiay) {
        this.tenGiay = tenGiay;
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

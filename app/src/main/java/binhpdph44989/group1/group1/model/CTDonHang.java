package binhpdph44989.group1.group1.model;

public class CTDonHang {
    private int madh;
    private int makh;
    private int magiay;
    private String ngaydat;
    private int trangthaidonhang;
    private int tongtien;
    private int soluong;

    public CTDonHang(int madh, int makh, int magiay, String ngaydat, int trangthaidonhang, int tongtien, int soluong) {
        this.madh = madh;
        this.makh = makh;
        this.magiay = magiay;
        this.ngaydat = ngaydat;
        this.trangthaidonhang = trangthaidonhang;
        this.tongtien = tongtien;
        this.soluong = soluong;
    }

    public int getMadh() {
        return madh;
    }

    public void setMadh(int madh) {
        this.madh = madh;
    }

    public int getMakh() {
        return makh;
    }

    public void setMakh(int makh) {
        this.makh = makh;
    }

    public int getMagiay() {
        return magiay;
    }

    public void setMagiay(int magiay) {
        this.magiay = magiay;
    }

    public String getNgaydat() {
        return ngaydat;
    }

    public void setNgaydat(String ngaydat) {
        this.ngaydat = ngaydat;
    }

    public int getTrangthaidonhang() {
        return trangthaidonhang;
    }

    public void setTrangthaidonhang(int trangthaidonhang) {
        this.trangthaidonhang = trangthaidonhang;
    }

    public int getTongtien() {
        return tongtien;
    }

    public void setTongtien(int tongtien) {
        this.tongtien = tongtien;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }
}

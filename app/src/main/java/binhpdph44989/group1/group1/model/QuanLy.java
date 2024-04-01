package binhpdph44989.group1.group1.model;

public class QuanLy {
    private int maql;
    private String hoten;
    private String taikhoan;
    private String matkhau;
    private String loaitaikhoan;
    private int trangthaitk;

    public QuanLy(int maql, String hoten, String taikhoan, String matkhau, int trangthaitk) {
        this.maql = maql;
        this.hoten = hoten;
        this.taikhoan = taikhoan;
        this.matkhau = matkhau;
        this.trangthaitk = trangthaitk;
    }

    public int getMaql() {
        return maql;
    }

    public void setMaql(int maql) {
        this.maql = maql;
    }

    public String getHoten() {
        return hoten;
    }

    public void setHoten(String hoten) {
        this.hoten = hoten;
    }

    public String getTaikhoan() {
        return taikhoan;
    }

    public void setTaikhoan(String taikhoan) {
        this.taikhoan = taikhoan;
    }

    public String getMatkhau() {
        return matkhau;
    }

    public void setMatkhau(String matkhau) {
        this.matkhau = matkhau;
    }

    public String getLoaitaikhoan() {
        return loaitaikhoan;
    }

    public void setLoaitaikhoan(String loaitaikhoan) {
        this.loaitaikhoan = loaitaikhoan;
    }

    public int getTrangthaitk() {
        return trangthaitk;
    }

    public void setTrangthaitk(int trangthaitk) {
        this.trangthaitk = trangthaitk;
    }
}

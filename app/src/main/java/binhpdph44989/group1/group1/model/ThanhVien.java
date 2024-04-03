package binhpdph44989.group1.group1.model;

public class ThanhVien {
    private int maql;
    private int makh;
    private String hoten;
    private String taikhoan;
    private String matkhau;
    private String sdt;
    private String diachi;
    private String loaitaikhoan;
    private int trangthaitk;

    public ThanhVien(int maql, int makh, String hoten, String sdt, String diachi, String loaitaikhoan, int trangthaitk) {
        this.maql = maql;
        this.makh = makh;
        this.hoten = hoten;
        this.sdt = sdt;
        this.diachi = diachi;
        this.loaitaikhoan = loaitaikhoan;
        this.trangthaitk = trangthaitk;
    }

    public int getMaql() {
        return maql;
    }

    public void setMaql(int maql) {
        this.maql = maql;
    }

    public int getMakh() {
        return makh;
    }

    public void setMakh(int makh) {
        this.makh = makh;
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

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
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

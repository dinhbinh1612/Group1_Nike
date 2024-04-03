package binhpdph44989.group1.group1.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper {
    public DbHelper(Context context){
        super(context,"NIKE",null,3);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String dbQuanLy = "CREATE TABLE QUANLY(maql INTEGER PRIMARY KEY AUTOINCREMENT,hoten TEXT,taikhoan text,matkhau TEXT,loaitaikhoan TEXT, trangthaitk integer)";
        db.execSQL(dbQuanLy);
        String dbKhachHang = "CREATE TABLE KHACHHANG(makh integer PRIMARY KEY AUTOINCREMENT, hoten text,taikhoan text,matkhau text, sdt text, diachi text, loaitaikhoan text,trangthaitk integer)";
        db.execSQL(dbKhachHang);
        String dbLoaiGiay = "CREATE TABLE LOAIGIAY(maloai integer PRIMARY KEY AUTOINCREMENT,  tenloai text, trangthailoai integer)";
        db.execSQL(dbLoaiGiay);
        String dbGiay = "CREATE TABLE GIAY(magiay integer PRIMARY KEY AUTOINCREMENT,tengiay text,hinhanh text,size integer,giaban integer,soluong integer,maloai integer references LOAIGIAY(maloai))";
        db.execSQL(dbGiay);
        String dbCTDonHang = "CREATE TABLE CTDONHANG(madh integer PRIMARY KEY AUTOINCREMENT,makh integer references KHACHHANG(makh),magiay integer references GIAY(magiay),ngaydat text,trangthaidonhang integer,tongtien integer,soluong integer)";
        db.execSQL(dbCTDonHang);
        String dbDonHang = "CREATE TABLE DONHANG(madh integer PRIMARY KEY AUTOINCREMENT,makh integer references KHACHHANG(makh),magiay integer references GIAY(magiay))";
        db.execSQL(dbDonHang);
        String dbCTSanPham = "CREATE TABLE CTSANPHAM(mactsp integer PRIMARY KEY AUTOINCREMENT,magiay integer references GIAY(magiay),tengiay references GIAY(tengiay),hinhanh references GIAY(hinhanh), size integer,giaban integer,motasp text)";
        db.execSQL(dbCTSanPham);

        db.execSQL("INSERT INTO QUANLY VALUES (1,'Phạm Đình Bính','admin1','123','Admin',1),(2,'Lê Nam Sơn','admin2','123','Admin',1),(3,'Vũ Văn Đức','admin3','123','Admin',1),(4,'Nguyễn Quang Thắng','admin4','123','Admin',1)");
        db.execSQL("INSERT INTO KHACHHANG VALUES (1,'test1','test1','123','0432987678','nhà 3 khu 2','user',1),(2,'test2','test2','123','0477894222','nhà 4 khu 2','user',0),(3,'test3','test1','123','0987092345','nhà 6 khu 2','user',1)");
        db.execSQL("INSERT INTO LOAIGIAY VALUES (1,'JORDAN',0),(2,'AF1',0),(3,'NEW',1)");
        db.execSQL("INSERT INTO GIAY VALUES (1,'AF1','avatar1',42,200,10,1),(2,'AIRMAX','avatar2',37,100,10,2),(3,'JORDAN1','avatar3',40,250,10,1),(4,'JORDAN','avatar4',40,300,10,1),(5,'AF2','avatar5',39,200,10,2),(6,'AF1','avatar6',44,200,10,1),(7,'AF12','avatar7',43,200,10,1),(8,'NIKE1','avatar8',42,200,10,2),(9,'AIRMIN','avatar9',38,900,17,2),(10,'AF123','avatar10',41,200,10,1),(11,'TRUNK','avatar11',40,400,10,1),(12,'JORDAN!','avatar12',39,200,10,2)");
        db.execSQL("INSERT INTO CTDONHANG VALUES (1,1,1,'11/03/2023',0,2000000,1),(2,2,2,'14/03/2023',1,3000000,1),(3,1,1,'11/03/2023',0,2000000,1)");
        db.execSQL("INSERT INTO DONHANG VALUES (1,1,1),(2,2,2),(3,3,3)");
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion != newVersion){
            db.execSQL("DROP TABLE IF EXISTS QUANLY");
            db.execSQL("DROP TABLE IF EXISTS KHACHHANG");
            db.execSQL("DROP TABLE IF EXISTS GIAY");
            db.execSQL("DROP TABLE IF EXISTS CTSANPHAM");
            db.execSQL("DROP TABLE IF EXISTS LOAIGIAY");
            db.execSQL("DROP TABLE IF EXISTS DONHANG");
            db.execSQL("DROP TABLE IF EXISTS CTDONHANG");
            onCreate(db);
        }
    }
    public boolean kiemTraDangNhap(String taiKhoan, String matKhau) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursorQuanLy = db.rawQuery("SELECT * FROM QUANLY WHERE taikhoan=? AND matkhau=?", new String[]{taiKhoan, matKhau});
        Cursor cursorKhachHang = db.rawQuery("SELECT * FROM KHACHHANG WHERE taikhoan=? AND matkhau=?", new String[]{taiKhoan, matKhau});

        if (cursorQuanLy.getCount() > 0 || cursorKhachHang.getCount() > 0) {
            cursorQuanLy.close();
            cursorKhachHang.close();
            return true;
        } else {
            cursorQuanLy.close();
            cursorKhachHang.close();
            return false;
        }
    }
    public void addUser(String hoTen, String sdt, String user, String diaChi, String matKhau) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("hoten", hoTen);
        values.put("sdt", sdt);
        values.put("taikhoan", user);
        values.put("diachi", diaChi);
        values.put("matkhau", matKhau);
        values.put("loaitaikhoan", "user");
        // Chèn dữ liệu vào bảng KHACHHANG
        db.insert("KHACHHANG", null, values);
        db.close();
    }
    public void capNhatMatKhau(String taiKhoan, String matKhauMoi) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("matkhau", matKhauMoi);
        db.update("QUANLY", values, "taikhoan=?", new String[]{taiKhoan});
        db.close();
    }
    public String getLoaiTaiKhoan(String taiKhoan) {
        SQLiteDatabase db = this.getReadableDatabase();
        String loaiTaiKhoan = "";
        Cursor cursorQuanLy = db.rawQuery("SELECT loaitaikhoan FROM QUANLY WHERE taikhoan=?", new String[]{taiKhoan});
        Cursor cursorKhachHang = db.rawQuery("SELECT loaitaikhoan FROM KHACHHANG WHERE taikhoan=?", new String[]{taiKhoan});

        if (cursorQuanLy.getCount() > 0) {
            cursorQuanLy.moveToFirst();
            loaiTaiKhoan = cursorQuanLy.getString(0); // Get loaiTaiKhoan from QUANLY table
        } else if (cursorKhachHang.getCount() > 0) {
            cursorKhachHang.moveToFirst();
            loaiTaiKhoan = cursorKhachHang.getString(0); // Get loaiTaiKhoan from KHACHHANG table
        }

        cursorQuanLy.close();
        cursorKhachHang.close();
        return loaiTaiKhoan;
    }
    public boolean TrungTK(String taiKhoan) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT COUNT(*) FROM KHACHHANG WHERE taikhoan=?", new String[]{taiKhoan});
        cursor.moveToFirst();
        int count = cursor.getInt(0);
        cursor.close();
        return count > 0;
    }
}

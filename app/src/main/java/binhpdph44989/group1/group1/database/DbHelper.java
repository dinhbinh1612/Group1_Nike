package binhpdph44989.group1.group1.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper {
    public DbHelper(Context context){
        super(context,"NIKE",null,1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String dbQuanLy = "CREATE TABLE QUANLY(maql text primary key,hoten text,matkhau text,loaitaikhoan text)";
        db.execSQL(dbQuanLy);
        String dbKhachHang = "CREATE TABLE KHACHHANG(makh integer primary key autoincrement, hoten text,taikhoan text,matkhau text, sdt text, diachi text )";
        db.execSQL(dbKhachHang);
        String dbLoai = "CREATE TABLE LOAIGIAY(maloai integer primary key autoincrement,  tenloai text, trangthailoai integer)";
        db.execSQL(dbLoai);
        String dbGiay = "CREATE TABLE GIAY(magiay integer primary key autoincrement,tengiay text,hinhanh text,size integer,giaban integer,soluong integer,maloai integer references LOAIGIAY(maloai))";
        db.execSQL(dbGiay);
        String dbCTDonHang = "CREATE TABLE CTDONHANG(madh integer primary key autoincrement,makh integer references KHACHHANG(makh),magiay integer references GIAY(magiay),ngaydat text,trangthaidonhang integer,tongtien interger)";
        db.execSQL(dbCTDonHang);
        String dbDonHang = "CREATE TABLE DONHANG(madh integer primary key autoincrement,makh integer references KHACHHANG(makh),magiay integer references GIAY(magiay))";
        db.execSQL(dbDonHang);
        String dbCTSanPham = "CREATE TABLE CTSANPHAM(mactsp integer primary key autoincrement,magiay integer references GIAY(magiay),tengiay references GIAY(tengiay),hinhanh references GIAY(hinhanh), size integer,giaban integer,motasp text)";
        db.execSQL(dbCTSanPham);

        db.execSQL("INSERT INTO QUANLY VALUES (1,'Phạm Đình Bính','123','Admin'),(2,'Lê Nam Sơn','123','Admin'),(3,'Vũ Văn Đức','123','Admin'),(4,'Nguyễn Quang Thắng','123','Admin'),(4,'Test','123','User')");
        db.execSQL("INSERT INTO KHACHHANG VALUES (1,'test1','test1','123','0432987678','nhà 3 khu 2'),(2,'test2','test2','123','0477894222','nhà 4 khu 2'),(3,'test3','test1','123','0987092345','nhà 6 khu 2')");
        db.execSQL("INSERT INTO LOAIGIAY VALUES (1,'JORDAN',0),(2,'AF1',0),(3,'NEW',1)");
        db.execSQL("INSERT INTO GIAY VALUES (1,'AF1','avatar1',42,200,10,1),(2,'AIRMAX','avatar2',37,100,10,2),(3,'JORDAN1','avatar3',40,250,10,1),(4,'JORDAN','avatar4',40,300,10,1),(5,'AF2','avatar5',39,200,10,2),(6,'AF1','avatar6',44,200,10,1),(7,'AF12','avatar7',43,200,10,1),(8,'NIKE1','avatar8',42,200,10,2),(9,'AIRMIN','avatar9',38,900,17,2),(10,'AF123','avatar14',41,200,10,1),(11,'TRUNK','avatar13',40,400,10,1),(12,'JORDAN!','avatar12',39,200,10,2)");
        db.execSQL("INSERT INTO CTDONHANG VALUES (1,1,1,'11/03/2023',0,2000000),(2,2,2,'14/03/2023',1,3000000),(1,1,1,'11/03/2023',0,2000000)");
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
        }
    }
}

package binhpdph44989.group1.group1.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import binhpdph44989.group1.group1.database.DbHelper;
import binhpdph44989.group1.group1.model.KhachHang;
import binhpdph44989.group1.group1.model.QuanLy;

public class ThanhVienDAO {
    DbHelper dbHelper;

    public ThanhVienDAO(Context context){
        dbHelper = new DbHelper(context);
    }
    public ArrayList<Object> getDSTaiKhoan() {
        ArrayList<Object> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();

        // Truy vấn dữ liệu từ cả bảng QUANLY và KHACHHANG
        Cursor quanLyCursor = sqLiteDatabase.rawQuery("SELECT maql, hoten, loaitaikhoan, trangthaitk FROM QUANLY", null);
        Cursor khachHangCursor = sqLiteDatabase.rawQuery("SELECT makh, hoten, sdt, diachi, loaitaikhoan, trangthaitk FROM KHACHHANG", null);

        // Thêm dữ liệu của QUANLY vào danh sách
        if (quanLyCursor.moveToFirst()) {
            do {
                list.add(new QuanLy(quanLyCursor.getInt(0), quanLyCursor.getString(1), quanLyCursor.getString(2), quanLyCursor.getInt(3)));
            } while (quanLyCursor.moveToNext());
        }

        // Thêm dữ liệu của KHACHHANG vào danh sách
        if (khachHangCursor.moveToFirst()) {
            do {
                list.add(new KhachHang(khachHangCursor.getInt(0), khachHangCursor.getString(1), khachHangCursor.getString(2), khachHangCursor.getString(3), khachHangCursor.getString(4), khachHangCursor.getInt(5)));
            } while (khachHangCursor.moveToNext());
        }

        // Đóng các con trỏ
        quanLyCursor.close();
        khachHangCursor.close();

        // Trả về danh sách đã hợp nhất
        return list;
    }
}


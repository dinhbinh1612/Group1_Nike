package binhpdph44989.group1.group1.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import binhpdph44989.group1.group1.database.DbHelper;
import binhpdph44989.group1.group1.model.KhachHang;

public class KhachHangDAO {
    DbHelper dbHelper;

    public KhachHangDAO(Context context) {
        dbHelper = new DbHelper(context);
    }

    public ArrayList<KhachHang> getDSKhachHang(){
        ArrayList<KhachHang> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT kh.makh,kh.hoten,kh.sdt,kh.diachi,kh.loaitaikhoan,kh.trangthaitk FROM KHACHHANG kh",null);
        if (cursor.getCount() != 0){
            cursor.moveToFirst();
            do {
                list.add(new KhachHang(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getInt(5)));
            } while (cursor.moveToNext());
        }
        return list;
    }
}
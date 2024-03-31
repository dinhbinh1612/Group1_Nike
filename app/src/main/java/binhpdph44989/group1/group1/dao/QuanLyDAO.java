package binhpdph44989.group1.group1.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import binhpdph44989.group1.group1.database.DbHelper;
import binhpdph44989.group1.group1.model.QuanLy;

public class QuanLyDAO {
    private DbHelper dbHelper;

    public QuanLyDAO(Context context) {
        dbHelper = new DbHelper(context);
    }

    public ArrayList<QuanLy> getDSQuanLy(){
        ArrayList<QuanLy> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT ql.maql, ql.hoten, ql.loaitaikhoan, ql.trangthaitk FROM QUANLY ql", null);
        if (cursor.getCount() != 0){
            cursor.moveToFirst();
            do {
                list.add(new QuanLy(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getInt(3)));
            } while (cursor.moveToNext());
        }
        cursor.close(); // Đóng con trỏ sau khi sử dụng
        sqLiteDatabase.close(); // Đóng cơ sở dữ liệu sau khi sử dụng
        return list;
    }
}
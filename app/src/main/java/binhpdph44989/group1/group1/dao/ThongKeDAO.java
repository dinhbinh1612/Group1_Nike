package binhpdph44989.group1.group1.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import binhpdph44989.group1.group1.database.DbHelper;

public class ThongKeDAO {
    DbHelper dbHelper;

    public ThongKeDAO(Context context) {
        dbHelper = new DbHelper(context);
    }

    public int getDoanhThu(String ngaybatdau, String ngayketthuc) {
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT SUM(tongtien) FROM CTDONHANG WHERE ngaydat BETWEEN ? AND ?", new String[]{ngaybatdau, ngayketthuc});
        if (cursor != null && cursor.moveToFirst()) {
            return cursor.getInt(0);
        }
        return 0;
    }
}
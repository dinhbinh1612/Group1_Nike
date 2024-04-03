package binhpdph44989.group1.group1.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import binhpdph44989.group1.group1.database.DbHelper;
import binhpdph44989.group1.group1.model.Top;

public class TopDAO {
    DbHelper dbHelper;

    public TopDAO(Context context) {
        dbHelper = new DbHelper(context);
    }

    public ArrayList<Top> getDSTop(){
        ArrayList<Top> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT g.magiay, g.tengiay, lg.tenloai " +
                "FROM Giay g " +
                "INNER JOIN LOAIGIAY lg ON g.maloai = lg.maloai", null);
        if (cursor.getCount() != 0){
            cursor.moveToFirst();
            do {
                list.add(new Top(cursor.getInt(0), cursor.getString(1), cursor.getString(2)));
            } while (cursor.moveToNext());
        }
        return list;
    }
}

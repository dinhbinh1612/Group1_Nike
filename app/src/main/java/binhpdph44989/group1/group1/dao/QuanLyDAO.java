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

    public ArrayList<QuanLy> getDSQuanLy() {
        ArrayList<QuanLy> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = null;

        try {
            cursor = sqLiteDatabase.query("QUANLY", null, null, null, null, null, null);
            if (cursor.moveToFirst()) {
                do {
                    list.add(new QuanLy(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getInt(4)));
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            sqLiteDatabase.close();
        }

        return list;
    }
}
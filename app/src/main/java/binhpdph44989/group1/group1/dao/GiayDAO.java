package binhpdph44989.group1.group1.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import binhpdph44989.group1.group1.database.DbHelper;
import binhpdph44989.group1.group1.model.Giay;
import binhpdph44989.group1.group1.model.LoaiGiay;

public class GiayDAO {
    private DbHelper dbHelper;

    public GiayDAO(Context context) {
        dbHelper = new DbHelper(context);
    }
    public ArrayList<Giay> getDSGiay() {
        ArrayList<Giay> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM GIAY", null);
        if (cursor.getCount() != 0) {
            cursor.moveToFirst();
            do {
                list.add(new Giay(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getInt(3), cursor.getInt(4), cursor.getInt(5)));
            } while (cursor.moveToNext());
        }
        return list;
    }


    public boolean themGiay(Giay giay) {
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("hinhAnh", giay.getHinhAnh());
        values.put("tenGiay", giay.getTenGiay());
        values.put("size", giay.getSize());
        values.put("giaBan", giay.getGiaBan());
        values.put("soLuong", giay.getSoLuong());

        long result = sqLiteDatabase.insert("GIAY", null, values);
        sqLiteDatabase.close();
        return result != -1;
    }
    public boolean capNhatGiay(Giay giay) {
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("hinhAnh", giay.getHinhAnh());
        values.put("tenGiay", giay.getTenGiay());
        values.put("size", giay.getSize());
        values.put("giaBan", giay.getGiaBan());
        values.put("soLuong", giay.getSoLuong());

        int result = sqLiteDatabase.update("GIAY", values, "maGiay = ?", new String[]{String.valueOf(giay.getMaGiay())});
        sqLiteDatabase.close();
        return result > 0;
    }

}

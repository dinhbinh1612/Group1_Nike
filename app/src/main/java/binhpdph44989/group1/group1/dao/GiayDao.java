package binhpdph44989.group1.group1.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;



import java.util.ArrayList;

import binhpdph44989.group1.group1.database.DbHelper;
import binhpdph44989.group1.group1.model.Giay;

public class GiayDAO {
    DbHelper dbhelper;
    public GiayDAO(Context context){dbhelper = new DbHelper(context);}
    public ArrayList<Giay>getDSGiay(){
        ArrayList<Giay>list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = dbhelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT g.magiay,g.tengiay,g.hinhanh,g.giaban,g.soluong,g.size,lo.tenloai FROM GIAY g,LOAIGIAY lo WHERE g.maloai = lo.maloai ",null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String imgName = cursor.getString(2);
            int gia = cursor.getInt(3);
            int soluong = cursor.getInt(4);
            int maloai = cursor.getInt(5);
            int size = cursor.getInt(6);
            Giay g = new Giay(id,name,imgName,gia,soluong,size);
            list.add(g);
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }
    public boolean themGiay(Giay giay) {
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("tengiay", giay.getTenGiay());
        values.put("hinhanh", giay.getHinhAnh());
        values.put("giaban", giay.getGiaBan());
        values.put("soluong", giay.getSoLuong());
        values.put("size", giay.getSize());

        // Thực hiện việc chèn dữ liệu vào bảng GIAY
        long result = db.insert("GIAY", null, values);
        db.close();

        // Kiểm tra kết quả và trả về true nếu thêm thành công, ngược lại trả về false
        return result != -1;
    }
}

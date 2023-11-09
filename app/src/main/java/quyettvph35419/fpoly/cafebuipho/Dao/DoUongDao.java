package quyettvph35419.fpoly.cafebuipho.Dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import quyettvph35419.fpoly.cafebuipho.Database.DbHelper;
import quyettvph35419.fpoly.cafebuipho.Model.DoUong;

public class DoUongDao {
    private SQLiteDatabase db;

    public DoUongDao(Context context) {
        DbHelper dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }


    public long delete(String id) {
        return db.delete("DOUONG", "MADOUONG = ?", new String[]{String.valueOf(id)});
    }

    public List<DoUong> getAll() {
        String sql = "SELECT * FROM DOUONG";
        return getData(sql);
    }

    public DoUong getID(String id) {
        String sql = "SELECT * FROM DOUONG WHERE MADOUONG=?";
        List<DoUong> list = getData(sql, id);
        return list.get(0);
    }

    @SuppressLint("Range")
    private List<DoUong> getData(String sql, String... selectionArgs) {
        List<DoUong> list = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql, selectionArgs);
        while (cursor.moveToNext()) {
            DoUong obj = new DoUong();
            obj.setMaDoUong(Integer.parseInt(cursor.getString(cursor.getColumnIndex("MADOUONG"))));
            obj.setMaLoai(Integer.parseInt(cursor.getString(cursor.getColumnIndex("MALOAI"))));
            obj.setGia(Integer.parseInt(cursor.getString(cursor.getColumnIndex("GIA"))));
            obj.setMaBan(Integer.parseInt(cursor.getString(cursor.getColumnIndex("MABAN"))));
            obj.setTonKho(Integer.parseInt(cursor.getString(cursor.getColumnIndex("TONKHO"))));
            list.add(obj);
        }
        return list;
    }

}

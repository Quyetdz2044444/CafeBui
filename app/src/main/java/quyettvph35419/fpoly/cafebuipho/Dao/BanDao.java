package quyettvph35419.fpoly.cafebuipho.Dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import quyettvph35419.fpoly.cafebuipho.Database.DbHelper;
import quyettvph35419.fpoly.cafebuipho.Model.Ban;

public class BanDao {
    private SQLiteDatabase db;

    public BanDao(Context context) {
        DbHelper dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }





    public long delete(String id) {
        return db.delete("BAN", "MABAN = ?", new String[]{String.valueOf(id)});
    }

    public List<Ban> getAll() {
        String sql = "SELECT * FROM BAN";
        return getData(sql);
    }

    public Ban getID(String id) {
        String sql = "SELECT * FROM BAN WHERE MABAN=?";
        List<Ban> list = getData(sql, id);
        return list.get(0);
    }

    @SuppressLint("Range")
    private List<Ban> getData(String sql, String... selectionArgs) {
        List<Ban> list = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql, selectionArgs);
        while (cursor.moveToNext()) {
            Ban obj = new Ban();
            obj.setMaBan(Integer.parseInt(cursor.getString(cursor.getColumnIndex("MABAN"))));
            obj.setViTriBan(Integer.parseInt(cursor.getString(cursor.getColumnIndex("VITRIBAN"))));
            obj.setTrangThai(Integer.parseInt(cursor.getString(cursor.getColumnIndex("TRANGTHAI"))));

            list.add(obj);
        }
        return list;
    }

}

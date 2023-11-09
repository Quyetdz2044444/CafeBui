package quyettvph35419.fpoly.cafebuipho.Dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import quyettvph35419.fpoly.cafebuipho.Database.DbHelper;
import quyettvph35419.fpoly.cafebuipho.Model.LoaiDoUong;

public class LoaiDoUongDao {
    private SQLiteDatabase db;

    public LoaiDoUongDao(Context context) {
        DbHelper dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public long insert(LoaiDoUong obj) {
        ContentValues values = new ContentValues();
        values.put("TENLOAI", obj.getTenLoai());
        return db.insert("LOAIDOUONG", null, values);
    }

    public long update(LoaiDoUong obj) {
        ContentValues values = new ContentValues();
        values.put("TENLOAI", obj.getTenLoai());
        return db.update("LOAIDOUONG", values, "MADOUONG = ?", new String[]{String.valueOf(obj.getMaLoai())});
    }

    public long delete(String id) {
        return db.delete("LOAIDOUONG", "MADOUONG = ?", new String[]{String.valueOf(id)});
    }

    public List<LoaiDoUong> getAll() {
        String sql = "SELECT * FROM LOAIDOUONG";
        return getData(sql);
    }

    public LoaiDoUong getID(String id) {
        String sql = "SELECT * FROM LOAIDOUONG WHERE MADOUONG=?";
        List<LoaiDoUong> list = getData(sql, id);
        return list.get(0);
    }

    @SuppressLint("Range")
    private List<LoaiDoUong> getData(String sql, String... selectionArgs) {
        List<LoaiDoUong> list = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql, selectionArgs);
        while (cursor.moveToNext()) {
            LoaiDoUong obj = new LoaiDoUong();
            obj.setMaLoai(Integer.parseInt(cursor.getString(cursor.getColumnIndex("MADOUONG"))));
            obj.setTenLoai(cursor.getString(cursor.getColumnIndex("TENLOAI")));
            list.add(obj);
        }
        return list;
    }

}

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

    public long insert(DoUong obj) {

        ContentValues values = new ContentValues();
        values.put("TenDO", obj.getTenDoUong());
        values.put("GiaDO", obj.getGia());
        values.put("MaSize", obj.getMaSize());
        values.put("Anh", obj.getImageId());
        values.put("MaLoai", obj.getMaLoai());
        return db.insert("DOUONG", null, values);

    }

    public long update(DoUong obj) {
        ContentValues values = new ContentValues();
        values.put("TenDO", obj.getTenDoUong());
        values.put("GiaDO", obj.getGia());
        values.put("MaSize", obj.getMaSize());
        values.put("Anh", obj.getImageId());
        values.put("MaLoai", obj.getMaLoai());
        return db.update("DOUONG", values, "MaDO = ?", new String[]{String.valueOf(obj.getMaDoUong())});
    }

    public long delete(String id) {
        return db.delete("DOUONG", "MaDO = ?", new String[]{String.valueOf(id)});
    }

    public List<DoUong> getAll() {
        String sql = "SELECT * FROM DOUONG";
        return getData(sql);
    }

    public DoUong getID(String id) {
        String sql = "SELECT * FROM DOUONG WHERE MaDO=?";
        List<DoUong> list = getData(sql, id);
        return list.get(0);
    }

    @SuppressLint("Range")
    private List<DoUong> getData(String sql, String... selectionArgs) {
        List<DoUong> list = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql, selectionArgs);

        while (cursor.moveToNext()) {
            DoUong obj = new DoUong();


            obj.setMaDoUong(Integer.parseInt(cursor.getString(cursor.getColumnIndex("MaDO"))));
            obj.setTenDoUong(cursor.getString(cursor.getColumnIndex("TenDO")));
            obj.setGia(Integer.parseInt(cursor.getString(cursor.getColumnIndex("GiaDO"))));
            obj.setMaSize(Integer.parseInt(cursor.getString(cursor.getColumnIndex("MaSize"))));
            obj.setImageId(Integer.parseInt(cursor.getString(cursor.getColumnIndex("Anh"))));
            obj.setMaLoai(Integer.parseInt(cursor.getString(cursor.getColumnIndex("MaLoai"))));
            list.add(obj);
        }
        return list;

    }
}

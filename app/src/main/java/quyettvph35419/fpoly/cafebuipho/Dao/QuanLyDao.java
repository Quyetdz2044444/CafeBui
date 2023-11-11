package quyettvph35419.fpoly.cafebuipho.Dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import quyettvph35419.fpoly.cafebuipho.Database.DbHelper;
import quyettvph35419.fpoly.cafebuipho.Model.QuanLy;

public class QuanLyDao {
    DbHelper dbHelper;
    private SQLiteDatabase db;

    public QuanLyDao(Context context) {
        dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public long insert(QuanLy obj) {
        ContentValues values = new ContentValues();
        values.put("MAQUANLY", obj.getMaQL());
        values.put("HOTEN", obj.getHoTen());
        values.put("MATKHAU", obj.getMatKhau());
        values.put("SDT", obj.getSdt());
        values.put("EMAIL", obj.getEmail());
        return db.insert("QUANLY", null, values);
    }

    public long updatePass(QuanLy obj) {
        ContentValues values = new ContentValues();
        values.put("HOTEN", obj.getHoTen());
        values.put("MATKHAU", obj.getMatKhau());
        return db.update("QUANLY", values, "MAQUANLY = ?", new String[]{String.valueOf(obj.getMaQL())});
    }

    public long delete(String id) {
        return db.delete("QUANLY", "MAQUANLY = ?", new String[]{String.valueOf(id)});
    }

    public List<QuanLy> getAll() {
        String sql = "SELECT * FROM QUANLY";
        return getData(sql);
    }

    public QuanLy getID(String id) {
        String sql = "SELECT * FROM QUANLY WHERE MAQUANLY=?";
        List<QuanLy> list = getData(sql, id);
        return list.get(0);
    }

    // check login
    public int checkLogin(String id, String password) {
        String sql = "SELECT * FROM QUANLY WHERE MAQUANLY=? AND MATKHAU=?";
        List<QuanLy> list = getData(sql, id, password);
        if (list.size() == 0) {
            return -1;
        }
        return 1;
    }

    public boolean register(QuanLy user) {

        db = dbHelper.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put("MAQUANLY", user.getMaQL());
        values.put("HOTEN", user.getHoTen());
        values.put("MATKHAU", user.getMatKhau());
        values.put("SDT", user.getSdt());
        values.put("EMAIL", user.getEmail());
        long rowId = db.insert("QUANLY", null, values);

        return (rowId != -1);
    }


    @SuppressLint("Range")
    private List<QuanLy> getData(String sql, String... selectionArgs) {
        List<QuanLy> list = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql, selectionArgs);
        while (cursor.moveToNext()) {
            QuanLy obj = new QuanLy();
            obj.setMaQL(cursor.getString(cursor.getColumnIndex("MAQUANLY")));
            obj.setHoTen(cursor.getString(cursor.getColumnIndex("HOTEN")));
            obj.setMatKhau(cursor.getString(cursor.getColumnIndex("MATKHAU")));
            obj.setSdt(cursor.getString(cursor.getColumnIndex("SDT")));
            obj.setEmail(cursor.getString(cursor.getColumnIndex("EMAIL")));
            list.add(obj);
        }
        return list;
    }

}

package quyettvph35419.fpoly.cafebuipho.Dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import quyettvph35419.fpoly.cafebuipho.Database.DbHelper;
import quyettvph35419.fpoly.cafebuipho.Model.KhachHang;

public class KhachHangDao {
    DbHelper dbHelper;
    private SQLiteDatabase db;

    public KhachHangDao(Context context) {
        dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public long insert(KhachHang obj) {
        ContentValues values = new ContentValues();
        values.put("MAKH", obj.getmaKH());
        values.put("HOTEN", obj.getHoTen());
        values.put("MATKHAU", obj.getMatKhau());
        values.put("SDT", obj.getSdt());
        values.put("DIACHI", obj.getDiaChi());
        values.put("EMAIL", obj.getEmail());
        return db.insert("KHACHHANG", null, values);
    }

    public long updatePass(KhachHang obj) {
        ContentValues values = new ContentValues();
        values.put("HOTEN", obj.getHoTen());
        values.put("MATKHAU", obj.getMatKhau());
        values.put("SDT", obj.getSdt());
        values.put("DIACHI", obj.getDiaChi());
        values.put("EMAIL", obj.getEmail());
        return db.update("KHACHHANG", values, "MAKH = ?", new String[]{String.valueOf(obj.getmaKH())});
    }

    public long delete(String id) {
        return db.delete("KHACHHANG", "MAKH = ?", new String[]{String.valueOf(id)});
    }

    public List<KhachHang> getAll() {
        String sql = "SELECT * FROM KHACHHANG";
        return getData(sql);
    }

    public KhachHang getID(String id) {
        String sql = "SELECT * FROM KHACHHANG WHERE MAKH=?";
        List<KhachHang> list = getData(sql, id);
        return list.get(0);
    }

    // check login
    public int checkLogin(String id, String password) {
        String sql = "SELECT * FROM KHACHHANG WHERE MAKH=? AND MATKHAU=?";
        List<KhachHang> list = getData(sql, id, password);
        if (list.size() == 0) {
            return -1;
        }
        return 1;
    }

    public boolean register(KhachHang user) {

        db = dbHelper.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put("MAKH", user.getmaKH());
        values.put("HOTEN", user.getHoTen());
        values.put("MATKHAU", user.getMatKhau());
        values.put("SDT", user.getSdt());
        values.put("DIACHI", user.getDiaChi());
        values.put("EMAIL", user.getEmail());
        long rowId = db.insert("KHACHHANG", null, values);

        return (rowId != -1);
    }



    @SuppressLint("Range")
    private List<KhachHang> getData(String sql, String... selectionArgs) {
        List<KhachHang> list = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql, selectionArgs);
        while (cursor.moveToNext()) {
            KhachHang obj = new KhachHang();
            obj.setmaKH(cursor.getString(cursor.getColumnIndex("MAKH")));
            obj.setHoTen(cursor.getString(cursor.getColumnIndex("HOTEN")));
            obj.setMatKhau(cursor.getString(cursor.getColumnIndex("MATKHAU")));
            obj.setSdt(cursor.getString(cursor.getColumnIndex("SDT")));
            obj.setDiaChi(cursor.getString(cursor.getColumnIndex("DIACHI")));
            obj.setEmail(cursor.getString(cursor.getColumnIndex("EMAIL")));
            list.add(obj);
        }
        return list;
    }

}

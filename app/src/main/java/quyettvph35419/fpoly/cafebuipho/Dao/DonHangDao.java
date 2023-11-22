package quyettvph35419.fpoly.cafebuipho.Dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import quyettvph35419.fpoly.cafebuipho.Database.DbHelper;
import quyettvph35419.fpoly.cafebuipho.Model.DonHang;

public class DonHangDao {

    DbHelper dbHelper;
    private SQLiteDatabase db;

    public DonHangDao(Context context) {
        dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public int getCount() {
        String sql = "SELECT COUNT(*) FROM DONHANG";
        Cursor cursor = db.rawQuery(sql, null);

        int count = 0;
        if (cursor.moveToFirst()) {
            count = cursor.getInt(0);
        }

        cursor.close();
        return count;
    }

    public long insert(DonHang obj) {
        ContentValues values = new ContentValues();

        values.put("MAKH", obj.getMaKH());
        values.put("Ngay", String.valueOf(obj.getNgay()));
        values.put("Gia", obj.getGia());
        values.put("ThanhToan", obj.getThanhToan());
        values.put("TrangThai", obj.getTrangThai());

        return db.insert("DONHANG", null, values);
    }


    public List<DonHang> getAll() {
        String sql = "SELECT * FROM DONHANG";
        return getData(sql);
    }

    public DonHang getID(String id) {
        String sql = "SELECT * FROM DONHANG WHERE MaDH=?";
        List<DonHang> list = getData(sql, id);
        return list.get(0);
    }


    @SuppressLint("Range")
    private List<DonHang> getData(String sql, String... selectionArgs) {
        List<DonHang> list = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql, selectionArgs);
        while (cursor.moveToNext()) {
            DonHang obj = new DonHang();

            obj.setMaDH(Integer.parseInt(cursor.getString(cursor.getColumnIndex("MaDH"))));
            obj.setMaKH(cursor.getString(cursor.getColumnIndex("MAKH")));
            obj.setNgay(cursor.getString(cursor.getColumnIndex("Ngay")));
            obj.setGia(Integer.parseInt(cursor.getString(cursor.getColumnIndex("Gia"))));
            obj.setThanhToan(cursor.getString(cursor.getColumnIndex("ThanhToan")));
            obj.setTrangThai(Integer.parseInt(cursor.getString(cursor.getColumnIndex("TrangThai"))));

            list.add(obj);
        }
        return list;
    }

}

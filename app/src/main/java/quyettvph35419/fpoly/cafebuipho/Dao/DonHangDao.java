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
import quyettvph35419.fpoly.cafebuipho.Model.DonHang;
import quyettvph35419.fpoly.cafebuipho.Model.DonHangChiTiet;

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
        values.put("Gia", obj.getGia());
        values.put("SoLuong", obj.getSoLuong());
        values.put("TrangThai", obj.getTrangThai());
        values.put("Ngay", obj.getNgay());

        return db.insert("DONHANG", null, values);
    }

    public long update(DonHang obj) {
        ContentValues values = new ContentValues();
        values.put("TrangThai", obj.getTrangThai());
        return db.update("DONHANG", values, "MaDH = ?", new String[]{String.valueOf(obj.getMaDH())});
    }

    public long updateTrangThai(int maDH, int trangThai) {
        ContentValues values = new ContentValues();
        values.put("TrangThai", trangThai);
        String whereClause = "MaDH = ?";
        String[] whereArgs = {String.valueOf(maDH)};
        return db.update("DONHANG", values, whereClause, whereArgs);
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
            obj.setGia(Integer.parseInt(cursor.getString(cursor.getColumnIndex("Gia"))));
            obj.setSoLuong(Integer.parseInt(cursor.getString(cursor.getColumnIndex("SoLuong"))));
            obj.setTrangThai(Integer.parseInt(cursor.getString(cursor.getColumnIndex("TrangThai"))));
            obj.setNgay(cursor.getString(cursor.getColumnIndex("Ngay")));

            list.add(obj);
            
        }
        return list;
    }


}

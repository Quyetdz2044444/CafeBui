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
import quyettvph35419.fpoly.cafebuipho.Model.DonHangChiTiet;

public class DonHangChiTietDao {
    DbHelper dbHelper;
    private SQLiteDatabase db;

    public DonHangChiTietDao(Context context) {
        dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public int getCount() {
        String sql = "SELECT COUNT(*) FROM DONHANGCHITIET";
        Cursor cursor = db.rawQuery(sql, null);

        int count = 0;
        if (cursor.moveToFirst()) {
            count = cursor.getInt(0);
        }

        cursor.close();
        return count;
    }

    public long update(DonHangChiTiet obj) {
        ContentValues values = new ContentValues();
        values.put("TrangThai", obj.getTrangThai());
        return db.update("DONHANGCHITIET", values, "MaDHCT = ?", new String[]{String.valueOf(obj.getMaDHCT())});
    }

    @SuppressLint("Range")
    public List<DonHangChiTiet> getAllByMaDonHang(int maDonHang) {
        List<DonHangChiTiet> list = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String selection = "MaDH = ?";
        String[] selectionArgs = {String.valueOf(maDonHang)};

        Cursor cursor = db.query("DONHANGCHITIET", null, selection, selectionArgs, null, null, null);
        while (cursor.moveToNext()) {
            DonHangChiTiet obj = new DonHangChiTiet();

            obj.setMaDHCT(cursor.getInt(cursor.getColumnIndex("MaDHCT")));
            obj.setMaDH(cursor.getInt(cursor.getColumnIndex("MaDH")));
            obj.setNgay(cursor.getString(cursor.getColumnIndex("Ngay")));
            obj.setThanhToan(cursor.getString(cursor.getColumnIndex("ThanhToan")));
            obj.setSoLuong(cursor.getInt(cursor.getColumnIndex("SoLuong")));
            obj.setTongTien(cursor.getInt(cursor.getColumnIndex("TongTien")));
            obj.setMaSize(cursor.getInt(cursor.getColumnIndex("MaSize")));
            obj.setTrangThai(cursor.getInt(cursor.getColumnIndex("TrangThai")));

            list.add(obj);
        }
        cursor.close();
        db.close();
        return list;
    }


    public long insert(DonHangChiTiet obj) {

        ContentValues values = new ContentValues();
        values.put("MaDH", obj.getMaDH());
        values.put("MaDO", obj.getMaDoUong());
        values.put("TongTien", obj.getTongTien());
        values.put("SoLuong", obj.getSoLuong());
        values.put("ThanhToan", obj.getThanhToan());
        values.put("Ngay", String.valueOf(obj.getNgay()));
        values.put("TrangThai", obj.getTrangThai());
        values.put("MaSize", obj.getMaSize());

        return db.insert("DONHANGCHITIET", null, values);
    }


    public List<DonHangChiTiet> getAll() {
        String sql = "SELECT * FROM DONHANGCHITIET";
        return getData(sql);
    }

    public DonHangChiTiet getID(String id) {
        String sql = "SELECT * FROM DONHANGCHITIET WHERE MaDHCT=?";
        List<DonHangChiTiet> list = getData(sql, id);
        return list.get(0);
    }


    @SuppressLint("Range")
    private List<DonHangChiTiet> getData(String sql, String... selectionArgs) {
        List<DonHangChiTiet> list = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql, selectionArgs);
        while (cursor.moveToNext()) {
            DonHangChiTiet obj = new DonHangChiTiet();

            obj.setMaDHCT(Integer.parseInt(cursor.getString(cursor.getColumnIndex("MaDHCT"))));
            obj.setMaDH(Integer.parseInt(cursor.getString(cursor.getColumnIndex("MaDH"))));
            obj.setNgay(cursor.getString(cursor.getColumnIndex("Ngay")));
            obj.setThanhToan(cursor.getString(cursor.getColumnIndex("ThanhToan")));
            obj.setSoLuong(Integer.parseInt(cursor.getString(cursor.getColumnIndex("SoLuong"))));
            obj.setTongTien(Integer.parseInt(cursor.getString(cursor.getColumnIndex("TongTien"))));
            obj.setMaSize(Integer.parseInt(cursor.getString(cursor.getColumnIndex("MaSize"))));
            obj.setTrangThai(Integer.parseInt(cursor.getString(cursor.getColumnIndex("TrangThai"))));

            list.add(obj);
        }
        return list;
    }

}

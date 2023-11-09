package quyettvph35419.fpoly.cafebuipho.Dao;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import quyettvph35419.fpoly.cafebuipho.Database.DbHelper;
import quyettvph35419.fpoly.cafebuipho.Model.NhanVien;

public class NhanVienDao {
    private SQLiteDatabase db;

    public NhanVienDao(Context context) {
        DbHelper dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }


    public long delete(String id) {
        return db.delete("NHANVIEN", "MANV = ?", new String[]{String.valueOf(id)});
    }

    public List<NhanVien> getAll() {
        String sql = "SELECT * FROM NHANVIEN";
        return getData(sql);
    }

    public NhanVien getID(String id) {
        String sql = "SELECT * FROM NHANVIEN WHERE MANV=?";
        List<NhanVien> list = getData(sql, id);
        return list.get(0);
    }

    @SuppressLint("Range")
    private List<NhanVien> getData(String sql, String... selectionArgs) {
        List<NhanVien> list = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql, selectionArgs);
        while (cursor.moveToNext()) {
            NhanVien obj = new NhanVien();
            obj.setMaNV(Integer.parseInt(cursor.getString(cursor.getColumnIndex("MANV"))));
            obj.setGioiTinh(Integer.parseInt(cursor.getString(cursor.getColumnIndex("GIOITINH"))));
            obj.setTenNV(cursor.getString(cursor.getColumnIndex("TENNV")));
            obj.setSoDienThoai(cursor.getString(cursor.getColumnIndex("SDT")));
            obj.setDiaChi(cursor.getString(cursor.getColumnIndex("DIACHI")));
            obj.setCccd(cursor.getString(cursor.getColumnIndex("CCCD")));
            obj.setNgaySinh(cursor.getString(cursor.getColumnIndex("NGAYSINH")));

            list.add(obj);
        }
        return list;
    }

}

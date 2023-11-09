package quyettvph35419.fpoly.cafebuipho.Dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import quyettvph35419.fpoly.cafebuipho.Database.DbHelper;
import quyettvph35419.fpoly.cafebuipho.Model.HoaDon;

public class HoaDonDao {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");

    private SQLiteDatabase db;

    public HoaDonDao(Context context) {
        DbHelper dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }


    public long delete(String id) {
        return db.delete("HOADON", "MAHOADON = ?", new String[]{String.valueOf(id)});
    }

    public List<HoaDon> getAll() {
        String sql = "SELECT * FROM HOADON";
        return getData(sql);
    }

    public HoaDon getID(String id) {
        String sql = "SELECT * FROM HOADON WHERE MAHOADON=?";
        List<HoaDon> list = getData(sql, id);
        return list.get(0);
    }

    @SuppressLint("Range")
    private List<HoaDon> getData(String sql, String... selectionArgs) {
        List<HoaDon> list = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql, selectionArgs);
        while (cursor.moveToNext()) {
            HoaDon obj = new HoaDon();
            obj.setMaHoaDon(Integer.parseInt(cursor.getString(cursor.getColumnIndex("MAHOADON"))));
            obj.setMaDoUong(Integer.parseInt(cursor.getString(cursor.getColumnIndex("MADOUONG"))));
            obj.setSoLuong(Integer.parseInt(cursor.getString(cursor.getColumnIndex("SOLUONG"))));
            obj.setMaBan(Integer.parseInt(cursor.getString(cursor.getColumnIndex("MABAN"))));
            obj.setMaNV(Integer.parseInt(cursor.getString(cursor.getColumnIndex("MANV"))));
            obj.setGia(Integer.parseInt(cursor.getString(cursor.getColumnIndex("GIA"))));
            obj.setTrangThai(Integer.parseInt(cursor.getString(cursor.getColumnIndex("TRANGTHAI"))));
            obj.setMaQuanLy(cursor.getString(cursor.getColumnIndex("MAQUANLY")));
            try {
                obj.setNgay(sdf.parse(cursor.getString(cursor.getColumnIndex("NGAY"))));
            } catch (ParseException e) {
                e.printStackTrace();
            }

            list.add(obj);
        }
        return list;
    }

}

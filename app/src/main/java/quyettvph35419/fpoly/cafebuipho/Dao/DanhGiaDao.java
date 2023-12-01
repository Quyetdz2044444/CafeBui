package quyettvph35419.fpoly.cafebuipho.Dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import quyettvph35419.fpoly.cafebuipho.Database.DbHelper;
import quyettvph35419.fpoly.cafebuipho.Model.DanhGia;
import quyettvph35419.fpoly.cafebuipho.Model.DoUong;

public class DanhGiaDao {
    private SQLiteDatabase db;
    private DbHelper dbHelper;

    public DanhGiaDao(Context context) {
        dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public long insert(DanhGia obj) {

        ContentValues values = new ContentValues();

        values.put("TENKH", obj.getTenKhachHang());
        values.put("MADOUONG", obj.getMaDoUong());
        values.put("SOSAO", obj.getSoSao());
        values.put("NOIDUNG", obj.getNoiDung());

        return db.insert("DANHGIA", null, values);

    }

    public List<DanhGia> getAll() {
        String sql = "SELECT * FROM DANHGIA";
        return getData(sql);
    }

    public DanhGia getID(String id) {
        String sql = "SELECT * FROM DANHGIA WHERE MADANHGIA=?";
        List<DanhGia> list = getData(sql, id);
        return list.get(0);
    }

    @SuppressLint("Range")
    private List<DanhGia> getData(String sql, String... selectionArgs) {
        List<DanhGia> list = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql, selectionArgs);

        while (cursor.moveToNext()) {
            DanhGia obj = new DanhGia();

            obj.setMaDanhGia(Integer.parseInt(cursor.getString(cursor.getColumnIndex("MADANHGIA"))));
            obj.setTenKhachHang(cursor.getString(cursor.getColumnIndex("TENKH")));
            obj.setMaDoUong(Integer.parseInt(cursor.getString(cursor.getColumnIndex("MADOUONG"))));
            obj.setSoSao(Float.parseFloat(cursor.getString(cursor.getColumnIndex("SOSAO"))));
            obj.setNoiDung(String.valueOf(cursor.getString(cursor.getColumnIndex("NOIDUNG"))));

            list.add(obj);
        }
        return list;

    }

}

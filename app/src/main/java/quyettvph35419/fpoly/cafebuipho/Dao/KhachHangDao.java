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

    public boolean update(String maKH,String hoten, String sdt, String diachi, String email){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("HOTEN",hoten);
        values.put("SDT",sdt);
        values.put("DIACHI",diachi);
        values.put("EMAIL",email);
        long check = db.update("KHACHHANG",values,"MAKH = ?",new String[]{String.valueOf(maKH)});
        if(check == -1){
            return false;
        }else{
            return true;
        }
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
    public List<KhachHang> getData(String sql, String... selectionArgs) {
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

    public ArrayList<KhachHang> getKH(){
        ArrayList<KhachHang> list = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from KHACHHANG",null);
        if(cursor.getCount() != 0){
            cursor.moveToFirst();
            do {
                list.add(new KhachHang(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4)));
            }while (cursor.moveToNext());
        }
        return list;
    }
}

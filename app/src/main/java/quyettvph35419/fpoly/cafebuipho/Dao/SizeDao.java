package quyettvph35419.fpoly.cafebuipho.Dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import quyettvph35419.fpoly.cafebuipho.Database.DbHelper;
import quyettvph35419.fpoly.cafebuipho.Model.Size;

public class SizeDao {
    DbHelper dbHelper;
    private SQLiteDatabase db;

    public SizeDao(Context context) {
        dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public long insert(Size obj) {
        ContentValues values = new ContentValues();
        values.put("Size", obj.getSize());
        values.put("GiaSize", obj.getGiaSize());
        return db.insert("SIZE", null, values);
    }

    public long updata(Size obj) {
        ContentValues values = new ContentValues();
        values.put("Size", obj.getSize());
        values.put("GiaSize", obj.getGiaSize());
        return db.update("SIZE", values, "MaSize = ?",
                new String[]{String.valueOf(obj.getMaSize())});
    }

    public long delete(String id) {
        return db.delete("SIZE", "MaSize = ?", new String[]{String.valueOf(id)});
    }

    public List<Size> getAll() {
        String sql = "SELECT * FROM SIZE";
        return getData(sql);
    }

    public Size getID(String id) {
        String sql = "SELECT * FROM SIZE WHERE MaSize=?";
        List<Size> list = getData(sql, id);
        return list.get(0);
    }

    @SuppressLint("Range")
    private List<Size> getData(String sql, String... selectionArgs) {
        List<Size> list = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql, selectionArgs);
        while (cursor.moveToNext()) {
            Size obj = new Size();
            obj.setMaSize(Integer.parseInt(cursor.getString(cursor.getColumnIndex("MaSize"))));
            obj.setSize(cursor.getString(cursor.getColumnIndex("Size")));
            obj.setGiaSize(cursor.getInt(cursor.getColumnIndex("GiaSize")));
            list.add(obj);
        }
        return list;
    }
}

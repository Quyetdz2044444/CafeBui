package quyettvph35419.fpoly.cafebuipho.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {
    public static final String DB_NAME = "CafeBui";
    public static final int DB_VERSION = 6;

    public DbHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

//        Bảng tài khoản đăng nhập
        String createTableKhachHang = "create table KHACHHANG(" +
                "MAKH TEXT PRIMARY KEY, " +
                "HOTEN TEXT NOT NULL, " +
                "MATKHAU TEXT NOT NULL," +
                "SDT TEXT NOT NULL," +
                "DIACHI TEXT NOT NULL," +
                "EMAIL TEXT NOT NULL)";
        db.execSQL(createTableKhachHang);
//--------------------------------------------------------------------------


//        ----------------------------------------------------------
//        INSERT DỮ LIỆU

        db.execSQL("INSERT INTO KHACHHANG VALUES('admin','Admin','admin','0983917432','Hà Nam','admin@gmail.com')," +
                "('trinhpk','Phạm Trưởng','123','098458902','Ninh Bình','trinhpk3@gmail.com')");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion != newVersion) {
            db.execSQL("drop table if exists KHACHHANG");
            onCreate(db);
        }
    }
}

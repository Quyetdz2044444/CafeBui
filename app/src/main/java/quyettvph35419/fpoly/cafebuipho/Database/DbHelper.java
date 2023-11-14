package quyettvph35419.fpoly.cafebuipho.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {
    public static final String DB_NAME = "CafeBui";
    public static final int DB_VERSION = 7;

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
//        Bảng đồ uống
        String createTableLoaiDoUong = "create table LOAIDOUONG(" +
                "MaLoai integer primary key autoincrement, " +
                "TenLoai TEXT NOT NULL)";
        db.execSQL(createTableLoaiDoUong);

        String tb_size = "create table SIZE(" +
                "MaSize integer primary key autoincrement," +
                "Size text not null," +
                "GiaSize integer not null)";
        db.execSQL(tb_size);


        String tb_DoUong= "create table DOUONG(" +
                "MaDO integer primary key autoincrement," +
                "TenDO text not null," +
                "GiaDO integer not null," +
                "MaSize integer references SIZE(MaSize)," +
                "Anh integer not null,"
                +"MaLoai integer references LOAIDOUONG(MaLoai))";
        db.execSQL(tb_DoUong);


        String tb_GioHang= "create table GIOHANG(" +
                "MaGH integer primary key autoincrement," +
                "MaDO integer references DOUONG(MaDO)," +
                "MaSize integer references SIZE(MaSize)," +
                "SoLuong integer not null,"
                +"TongTien integer not null)";
        db.execSQL(tb_GioHang);


        String tb_DonHang= "create table DONHANG(" +
                "MaDH integer primary key autoincrement," +
                "MAKH Text references KHACHHANG(MAKH)," +
                "MaDO integer references DOUONG(MaDO)," +
                "Ngay text not null,"
                +"Gia integer not null,"
                +"TrangThai integer not null)";
        db.execSQL(tb_DonHang);

        String tb_DonHangChiTiet= "create table DONHANGCHITIET(" +
                "MaDHCT integer primary key autoincrement," +
                "MaDO integer references DOUONG(MaDO)," +
                "MaDH integer references DONHANG(MaDH)," +
                "TongTien integer not null,"
                +"SoLuong integer not null,"
                +"Ngay text not null,"
                +"TrangThai integer not null,"
                +"MaSize integer references SIZE(MaSize))";
        db.execSQL(tb_DonHangChiTiet);



//        INSERT DỮ LIỆU

        db.execSQL("INSERT INTO KHACHHANG VALUES('admin','Admin','admin','0983917432','Hà Nam','admin@gmail.com')," +
                "('trinhpk','Phạm Trưởng','123','098458902','Ninh Bình','trinhpk3@gmail.com')");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion != newVersion) {
            db.execSQL("drop table if exists KHACHHANG");
            db.execSQL("drop table if exists LOAIDOUONG");
            db.execSQL("drop table if exists SIZE");
            db.execSQL("drop table if exists DOUONG");
            db.execSQL("drop table if exists GIOHANG");
            db.execSQL("drop table if exists DONHANG");
            db.execSQL("drop table if exists DONHANGCHITIET");
            onCreate(db);
        }
    }
}

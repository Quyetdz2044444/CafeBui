package quyettvph35419.fpoly.cafebuipho.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {
    public static final String DB_NAME = "CafeBui";
    public static final int DB_VERSION = 19;

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
        String createTableLoaiDoUong = "create table LOAIDOUONG(" +
                "MaLoai integer primary key autoincrement, " +
                "TenLoai TEXT NOT NULL, " +
                "ImgLoai INTEGER NOT NULL)";
        db.execSQL(createTableLoaiDoUong);

        String tb_size = "create table SIZE(" +
                "MaSize integer primary key," +
                "Size text not null," +
                "GiaSize integer not null)";
        db.execSQL(tb_size);

        String tb_DoUong = "create table DOUONG(" +
                "MaDO integer primary key autoincrement," +
                "TenDO text not null," +
                "GiaDO integer not null," +
                "Anh integer not null,"
                + "MaLoai integer references LOAIDOUONG(MaLoai))";
        db.execSQL(tb_DoUong);


        String tb_GioHang = "create table GIOHANG(" +
                "MaGH integer primary key autoincrement," +
                "MaDO integer references DOUONG(MaDO)," +
                "MaSize integer references SIZE(MaSize)," +
                "SoLuong integer not null,"
                + "TongTien integer not null)";
        db.execSQL(tb_GioHang);


        String tb_DonHang = "create table DONHANG(" +
                "MaDH integer primary key autoincrement," +
                "MaDO integer references DOUONG(MaDO)," +
                "MAKH Text references KHACHHANG(MAKH)," +
                "Gia integer not null,"
                + "SoLuong integer not null,"
                + "TrangThai integer not null)";
        db.execSQL(tb_DonHang);

        String tb_DonHangChiTiet = "create table DONHANGCHITIET(" +
                "MaDHCT integer primary key autoincrement," +
                "MaDO integer references DOUONG(MaDO)," +
                "MaDH integer references DONHANG(MaDH)," +
                "TongTien integer not null,"
                + "SoLuong integer not null,"
                + "Ngay text not null," +
                "ThanhToan Text not null,"
                + "TrangThai integer not null,"
                + "MaSize integer references SIZE(MaSize))";
        db.execSQL(tb_DonHangChiTiet);

//        INSERT DỮ LIỆU
        db.execSQL("INSERT INTO KHACHHANG VALUES('admin','Admin','admin','0983917432','Hà Nam','admin@gmail.com')," +
                "('trinhpk','Phạm Trưởng','123','098458902','Ninh Bình','trinhpk3@gmail.com')");

        db.execSQL("INSERT INTO LOAIDOUONG (MaLoai,TenLoai,ImgLoai) VALUES (1, 'Cafe truyền thống',2),(2,'Cafe sữa',3),(3,'Cafe pha máy',4)," +
                "(4,'Cafe đặc biệt',5)");

        db.execSQL("INSERT INTO SIZE VALUES (1, 'M',0 ),(2,'L',10000),(3,'XL',15000)");

        db.execSQL("INSERT INTO DOUONG VALUES (1, 'Latte', 30000,1,1)," +
                "(2, 'Machiato', 35000,2,1),(3, 'Cafe sữa đá', 25000,3,2),(4, 'Cafe sữa nóng', 25000,4,2)," +
                "(5, 'Mocha', 45000,5,3),(6, 'Affogato', 50000,6,3),(7, 'Irish cofee', 45000,7,4)," +
                "(8, 'Flat white', 65000,8,4),(9, 'Lungo coffee', 60000,9,1),(10, 'Ristresto', 55000,10,3)," +
                "(11, 'Red eyes', 50000,11,4),(12, 'Picolo coffee', 65000,12,2)," +
                "(13, 'Cafe muối', 60000,13,3)," +
                "(14, 'Cafe trứng', 50000,14,2),(15, 'Long black ', 45000,15,1),(16, 'Cafe cốt dừa', 55000,16,4)");


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

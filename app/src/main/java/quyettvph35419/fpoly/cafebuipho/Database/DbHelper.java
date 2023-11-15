package quyettvph35419.fpoly.cafebuipho.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {
    public static final String DB_NAME = "CafeBui";
    public static final int DB_VERSION = 8;

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
                "TenLoai TEXT NOT NULL)";
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
                "MaSize integer references SIZE(MaSize)," +
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
                "MAKH Text references KHACHHANG(MAKH)," +
                "MaDO integer references DOUONG(MaDO)," +
                "Ngay date not null,"
                + "Gia integer not null,"
                + "TrangThai integer not null)";
        db.execSQL(tb_DonHang);

        String tb_DonHangChiTiet = "create table DONHANGCHITIET(" +
                "MaDHCT integer primary key autoincrement," +
                "MaDO integer references DOUONG(MaDO)," +
                "MaDH integer references DONHANG(MaDH)," +
                "TongTien integer not null,"
                + "SoLuong integer not null,"
                + "Ngay date not null,"
                + "TrangThai integer not null,"
                + "MaSize integer references SIZE(MaSize))";
        db.execSQL(tb_DonHangChiTiet);

//        INSERT DỮ LIỆU
        db.execSQL("INSERT INTO KHACHHANG VALUES('admin','Admin','admin','0983917432','Hà Nam','admin@gmail.com')," +
                "('trinhpk','Phạm Trưởng','123','098458902','Ninh Bình','trinhpk3@gmail.com')");

        db.execSQL("INSERT INTO LOAIDOUONG VALUES (1, 'Cafe truyền thống'),(2,'Cafe sữa'),(3,'Cafe pha máy')," +
                "(4,'Cafe đặc biệt')");

        db.execSQL("INSERT INTO SIZE VALUES (1, 'M',0 ),(2,'L',10000),(3,'XL',15000)");

        db.execSQL("INSERT INTO DOUONG VALUES (1, 'Latte', 30000,1,1,1)," +
                "(2, 'Machiato', 35000,2,2,1),(3, 'Cafe sữa đá', 25000,3,3,2),(4, 'Cafe sữa nóng', 25000,1,4,2)," +
                "(5, 'Mocha', 45000,2,5,3),(6, 'Affogato', 50000,1,6,3),(7, 'Irish cofee', 45000,2,7,4)," +
                "(8, 'Flat white', 65000,3,8,4)");

        db.execSQL("INSERT INTO GIOHANG VALUES (1, 1, 1, 2, 20000),(2, 2, 2, 3, 30000)," +
                "(3, 3, 3, 4, 40000)");

        db.execSQL("INSERT INTO DONHANG VALUES (1, 'Customer1', 1, '2022-11-14', 20000, 1)," +
                "(2, 'Customer2', 2, '2023-03-10', 30000, 2),(3, 'Customer3', 3, '2023-05-16', 25000, 2)," +
                "(4, 'Customer3', 3, '2023-07-20', 40000, 3),(5, 'Customer4', 4, '2023-05-04', 35000, 4)");

//        db.execSQL("INSERT INTO DONHANGCHITIET VALUES (1, 1, 1, 50000, 2, '2023-02-14', 1,1)," +
//                "(2, 2, 2, 60000, 3, '2023-03-15', 2,2),(4, 4, 4, 50000, 4, '2023-05-12', 3,3)," +
//                "(1, 1, 1, 50000, 2, '2023-02-14', 1,1)");

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

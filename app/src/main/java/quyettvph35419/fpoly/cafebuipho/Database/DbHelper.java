package quyettvph35419.fpoly.cafebuipho.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {
    public static final String DB_NAME = "CafeBui";
    public static final int DB_VERSION = 41;

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
                + "MaLoai integer references LOAIDOUONG(MaLoai)," +
                "TonKho integer not null)";
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
                "Gia integer not null,"
                + "SoLuong integer not null,"
                + "TrangThai integer not null," +
                "Ngay text not null)";
        db.execSQL(tb_DonHang);

        String tb_DonHangChiTiet = "create table DONHANGCHITIET(" +
                "MaDHCT integer primary key autoincrement," +
                "MaDO integer references DOUONG(MaDO)," +
                "MaDH integer references DONHANG(MaDH)," +
                "TongTien integer not null,"
                + "SoLuong integer not null,"
                + "Ngay text not null," +
                "ThanhToan Text not null,"
                + "MaSize integer references SIZE(MaSize))";
        db.execSQL(tb_DonHangChiTiet);

        String createTableDanhGia = "CREATE TABLE DANHGIA (" +
                "MADANHGIA INTEGER PRIMARY KEY AUTOINCREMENT," +
                "TENKH TEXT NOT NULL ," +
                "MADOUONG INTEGER REFERENCES DOUONG(MaDO)," +
                "SOSAO REAL," +
                "NOIDUNG TEXT)";
        db.execSQL(createTableDanhGia);


//        INSERT DỮ LIỆU
        db.execSQL("INSERT INTO KHACHHANG VALUES('admin','Admin','admin','0983917432','Hà Nam','admin@gmail.com')," +
                "('trinhpk','Phạm Trưởng','123','098458902','Ninh Bình','trinhpk3@gmail.com')");

        db.execSQL("INSERT INTO LOAIDOUONG (MaLoai,TenLoai,ImgLoai) VALUES (1, 'Cafe truyền thống',2),(2,'Cafe sữa',3),(3,'Cafe pha máy',4)," +
                "(4,'Cafe đặc biệt',5)");

        db.execSQL("INSERT INTO SIZE VALUES (1, 'M',0 ),(2,'L',10000),(3,'XL',15000)");

        db.execSQL("INSERT INTO DOUONG VALUES (1, 'Latte', 30000,1,1,66)," +
                "(2, 'Machiato', 35000,2,1,77),(3, 'Cafe sữa đá', 25000,3,2,88),(4, 'Cafe sữa nóng', 25000,4,2,99)," +
                "(5, 'Mocha', 45000,5,3,76),(6, 'Affogato', 50000,6,3,78),(7, 'Irish cofee', 45000,7,4,87)," +
                "(8, 'Flat white', 65000,8,4,74),(9, 'Lungo coffee', 60000,9,1,63),(10, 'Ristresto', 55000,10,3,69)," +
                "(11, 'Red eyes', 50000,11,4,82),(12, 'Picolo coffee', 65000,12,2,81)," +
                "(13, 'Cafe muối', 60000,13,3,90)," +
                "(14, 'Cafe trứng', 50000,14,2,60),(15, 'Long black ', 45000,15,1,65),(16, 'Cafe cốt dừa', 55000,16,4,73)");

        db.execSQL("INSERT INTO DANHGIA VALUES" +
                "(1,'Hải Huy',1, 4.0, 'Rất ngon!'),(2,'Mạnh Dũng',1, 3.0, 'độc lạ như cái tên')," +
                "(3,'Công Phượng',2, 5.0, 'Quá tuyệt vời.Very very ngon!!'),(4,'Quang Hải',2, 4.0, 'Khá là ngon theo cảm nhận của tôi')," +
                "(5,'Ngọc Trinh',2, 5.0, 'Đỉnh cao của hương vị'), (6,'Văn Toàn',3, 4.0, 'Thơm ngon hấp dẫn')," +
                "(7,'Văn Quyết',3, 3.0, 'Không có gì nổi bật'),(8,'Hồng Hào',4, 4.0, 'Đặc trưng và thơm phức')," +
                "(9,'Hồng Xiêm',4, 4.0, 'Mùi vị đăc biệt ')," +
                "(10,'Hồng Lác',5, 4.0, 'Đặc trưng , tuyệt'),(11,'Tiến Linh',5, 4.0, 'Đáng thử nghiệm')," +
                "(12,'Hải Linh',6, 3.0, 'Khá là tốt'),(13,'Khánh ',6, 3.0, 'Khá, thức uống độc lạ')," +
                "(14,'Hoàng',7, 3.0, 'Ngon vậy shaooo'),(15,'Bảo',7, 3.0, 'hahaha săn được voucher mlem wa')," +
                "(16,'Duy Nhất',8, 3.0, 'Khá là tốt'),(17,'Giang',8, 3.0, 'Khá là tốt'),(18,'Mạnh Dũng',9, 3.0, 'Bình thường')," +
                "(19,'Mạnh Dũng',9, 3.0, 'Bình thường như cân đường kim chỉ'),(20,'Mạnh Dũng',10, 3.0, 'Maxxxx ngonnn')," +
                "(21,'Mạnh Dũng',10, 3.0, 'Món này hơi lạ'),(22,'Mạnh Dũng',11, 3.0, 'Bình thường nhưng k bth')," +
                "(23,'Công Công',11, 5.0, 'Quá tuyệt vời.Very very ngon!!'),(24,'Quang Hải',11, 4.0, 'Khá là ngon theo cảm nhận của tôi')," +
                "(25,'Ngọc Ngọc',12, 5.0, 'Đỉnh cao của hương vị'),(26,'Văn Toàn',12, 4.0, 'Thơm ngon hấp dẫn')," +
                "(27,'Văn Nam',13, 3.0, 'Không có gì nổi bật'),(28,'Hồng Hài Nhi',13, 4.0, 'Vô địch thế gian')," +
                "(29,'Dương',14, 4.0, 'Đặc trưng và thơm phức'),(30,'Quang',14, 4.0, 'Đỉnh của làng')," +
                "(31,'Tuấn Anh',15, 4.0, 'Khá hài lòng với giá'),(32,'Tuấn',15, 4.0, 'Quá đỉnh')," +
                "(33,'Tiến',16, 4.0, 'Ngon'),(34,'Thành',16, 4.0, 'Đúng tầm giá')");



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
            db.execSQL("drop table if exists DANHGIA");
            onCreate(db);
        }
    }
}

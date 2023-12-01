package quyettvph35419.fpoly.cafebuipho;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import quyettvph35419.fpoly.cafebuipho.Adapter.ChiTietDonHangAdapter_User;
import quyettvph35419.fpoly.cafebuipho.Dao.DonHangChiTietDao;
import quyettvph35419.fpoly.cafebuipho.Dao.DonHangDao;
import quyettvph35419.fpoly.cafebuipho.Dao.KhachHangDao;
import quyettvph35419.fpoly.cafebuipho.Model.DonHang;
import quyettvph35419.fpoly.cafebuipho.Model.DonHangChiTiet;
import quyettvph35419.fpoly.cafebuipho.Model.KhachHang;


public class ChiTietDonHang extends AppCompatActivity {
    private Toolbar tlToolbar;
    private TextView tvHoten, tvSdt, tvDiaChi;
    private KhachHang khachHang;
    private KhachHangDao khachHangDao;
    private ChiTietDonHangAdapter_User adapterCTDH;
    private RecyclerView rclCTDH;
    private DonHangChiTiet donHangChiTiet;
    private DonHangChiTietDao donHangChiTietDao;

    private List<DonHangChiTiet> chiTietList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_don_hang);
        tvHoten = findViewById(R.id.tvhoten_chitietdonhang);
        tvDiaChi = findViewById(R.id.tvdiachi_chitietdonhang);
        tvSdt = findViewById(R.id.tvsdt_chitietdonhang);

        Intent intent = getIntent();
        int madh = intent.getIntExtra("madonhang", -1);
        String makh = intent.getStringExtra("makh");
        khachHangDao = new KhachHangDao(this);
        khachHang = khachHangDao.getID(makh);

        tvHoten.setText("Họ tên : " + khachHang.getHoTen());
        tvDiaChi.setText("Địa chỉ : " + khachHang.getDiaChi());
        tvSdt.setText("Sđt : " + khachHang.getSdt());

        rclCTDH = findViewById(R.id.rclChitietdonhang);
        tlToolbar = findViewById(R.id.toolbarchitiet_donhang);
        setSupportActionBar(tlToolbar);
        // Hiển thị nút Back
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        tlToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        donHangChiTietDao = new DonHangChiTietDao(this);

        // Lấy danh sách chi tiết đơn hàng từ DonHangChiTietDao
        chiTietList = donHangChiTietDao.getAllByMaDonHang(madh);

        // Thiết lập RecyclerView
        adapterCTDH = new ChiTietDonHangAdapter_User(chiTietList, this, makh);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rclCTDH.setLayoutManager(layoutManager);
        rclCTDH.setAdapter(adapterCTDH);
    }
}
package quyettvph35419.fpoly.cafebuipho;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import quyettvph35419.fpoly.cafebuipho.Adapter.ChiTietDonHangAdapter_User;
import quyettvph35419.fpoly.cafebuipho.Dao.DonHangChiTietDao;
import quyettvph35419.fpoly.cafebuipho.Model.DonHangChiTiet;


public class ChiTietDonHang extends AppCompatActivity {
    private Toolbar tlToolbar;
    private ChiTietDonHangAdapter_User adapterCTDH;
    private RecyclerView rclCTDH;
    private DonHangChiTiet donHangChiTiet;
    private DonHangChiTietDao donHangChiTietDao;
    private List<DonHangChiTiet> chiTietList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_don_hang);

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
        chiTietList = donHangChiTietDao.getAll();

        // Thiết lập RecyclerView
        adapterCTDH = new ChiTietDonHangAdapter_User(chiTietList, this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rclCTDH.setLayoutManager(layoutManager);
        rclCTDH.setAdapter(adapterCTDH);
    }
}
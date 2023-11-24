package quyettvph35419.fpoly.cafebuipho;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import quyettvph35419.fpoly.cafebuipho.Adapter.DoUongAdapter_Xndathang_GioHang;
import quyettvph35419.fpoly.cafebuipho.Adapter.GioHangAdapter;
import quyettvph35419.fpoly.cafebuipho.Dao.GioHangDao;
import quyettvph35419.fpoly.cafebuipho.Dao.KhachHangDao;
import quyettvph35419.fpoly.cafebuipho.Model.GioHang;
import quyettvph35419.fpoly.cafebuipho.Model.KhachHang;

public class XacNhanDatHang_GioHang extends AppCompatActivity {
    private Toolbar tlbarxndathang;
    private TextView tvHoten, tvSdt, tvDiaChi, tvTongTien;
    private Button btndathangGH;
    private RecyclerView rclDouongGH;
    private GioHangDao gioHangDao;
    private List<GioHang> gioHangList;
    private DoUongAdapter_Xndathang_GioHang gioHangAdapter;
    private KhachHang khachHang;
    private KhachHangDao khachHangDao;
    private int tongTien = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xac_nhan_dat_hang_gio_hang);

        anhXa();

        Intent intent = getIntent();
        String makh = intent.getStringExtra("makh");

        setSupportActionBar(tlbarxndathang);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        tlbarxndathang.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        rclDouongGH = findViewById(R.id.rclViewDoUong_Xndathang_giohang);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rclDouongGH.setLayoutManager(layoutManager);

        gioHangDao = new GioHangDao(this);
        gioHangList = gioHangDao.getAll();
        gioHangAdapter = new DoUongAdapter_Xndathang_GioHang(gioHangList, this);
        rclDouongGH.setAdapter(gioHangAdapter);

        for (GioHang gioHang : gioHangList) {
            int gia = gioHang.getTongTien(); // Lấy giá từ sản phẩm
            tongTien += gia;// Tính tổng giá tiền
        }

        tvTongTien.setText(String.valueOf(tongTien) + " vnđ");

        khachHang = khachHangDao.getID(makh);
        tvHoten.setText("Họ tên : " + khachHang.getHoTen());
        tvSdt.setText("Số điện thoại : " + khachHang.getSdt());
        tvDiaChi.setText("Địa chỉ : " + khachHang.getDiaChi());

        btndathangGH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }

    private void anhXa() {
        tlbarxndathang = findViewById(R.id.toolbarxndathang);

        tvTongTien = findViewById(R.id.tvTongTien_xndathang_gh);
        tvHoten = findViewById(R.id.tvhoten_xndathang_gh);
        tvSdt = findViewById(R.id.tvsdt_xndathang_gh);
        tvDiaChi = findViewById(R.id.tvdiachi_xndathang_gh);

        khachHang = new KhachHang();
        khachHangDao = new KhachHangDao(this);

        btndathangGH = findViewById(R.id.btndathang_xndathang_gh);
    }
}
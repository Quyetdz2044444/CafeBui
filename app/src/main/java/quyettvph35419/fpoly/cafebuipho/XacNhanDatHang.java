package quyettvph35419.fpoly.cafebuipho;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import quyettvph35419.fpoly.cafebuipho.Dao.KhachHangDao;
import quyettvph35419.fpoly.cafebuipho.Model.KhachHang;

public class XacNhanDatHang extends AppCompatActivity {
    private TextView tvHoten, tvSdt, tvDiaChi, tvTenDoUong, tvSizeDoUong, tvSoLuong, tvGiaDoUong, tvTongTien;
    private RadioGroup radioGrThanhToan;
    private RadioButton rdoBanking, rdoCard;
    private Button btnDatHang;
    private Toolbar tlbarxndathang;
    private KhachHang khachHang;
    private KhachHangDao khachHangDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xac_nhan_dat_hang);

        anhxa();
        setSupportActionBar(tlbarxndathang);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        tlbarxndathang.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        Intent intent = getIntent();
        String tendouong = intent.getStringExtra("tendouong");
        String size = intent.getStringExtra("size");
        String giadouong = intent.getStringExtra("giadouong");
        String soluong = intent.getStringExtra("soluong");
        String tongtien = intent.getStringExtra("tongtien");

        tvTenDoUong.setText(tendouong);
        tvSizeDoUong.setText("Size : " + size);
        tvSoLuong.setText("Số lượng : " + soluong);
        tvGiaDoUong.setText(giadouong);

        tvTongTien.setText(tongtien);


    }

    private void anhxa() {
        tlbarxndathang = findViewById(R.id.toolbarxndathang);

        tvHoten = findViewById(R.id.tvhoten_xndathang);
        tvSdt = findViewById(R.id.tvsdt_xndathang);
        tvDiaChi = findViewById(R.id.tvdiachi_xndathang);

        tvTenDoUong = findViewById(R.id.tvtendouong_xndathang);
        tvSizeDoUong = findViewById(R.id.tvsizedouong_xndathang);
        tvSoLuong = findViewById(R.id.tvsoluong_xndathang);
        tvGiaDoUong = findViewById(R.id.tvgiadouong_xndathang);
        tvTongTien = findViewById(R.id.tvTongTien_xndathang);

        radioGrThanhToan = findViewById(R.id.radioGrthanhtoan);
        rdoBanking = findViewById(R.id.rdo_banking);
        rdoCard = findViewById(R.id.rdo_card);

        btnDatHang = findViewById(R.id.btndathang_xndathang);
    }
}
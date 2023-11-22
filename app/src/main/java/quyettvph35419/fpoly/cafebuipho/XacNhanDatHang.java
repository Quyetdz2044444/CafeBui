package quyettvph35419.fpoly.cafebuipho;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import quyettvph35419.fpoly.cafebuipho.Dao.KhachHangDao;
import quyettvph35419.fpoly.cafebuipho.Model.KhachHang;

public class XacNhanDatHang extends AppCompatActivity {
    private Toolbar tlbarxndathang;
    private KhachHang khachHang;
    private KhachHangDao khachHangDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xac_nhan_dat_hang);
        tlbarxndathang = findViewById(R.id.toolbarxndathang);
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


    }
}
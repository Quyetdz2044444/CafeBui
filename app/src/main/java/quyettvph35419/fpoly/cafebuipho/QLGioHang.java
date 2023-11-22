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
import android.widget.Toast;

import java.util.List;

import quyettvph35419.fpoly.cafebuipho.Account.Login;
import quyettvph35419.fpoly.cafebuipho.Adapter.GioHangAdapter;
import quyettvph35419.fpoly.cafebuipho.Dao.GioHangDao;
import quyettvph35419.fpoly.cafebuipho.Model.GioHang;


public class QLGioHang extends AppCompatActivity {
    private Toolbar tlbargiohang;
    private RecyclerView rclgiohang;
    private Login login;
    private Button btndathangGH;
    private GioHangDao gioHangDao;
    private List<GioHang> gioHangList;
    private GioHangAdapter gioHangAdapter;
    private int tongTien = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qlgio_hang);
        login = new Login();
        tlbargiohang = findViewById(R.id.toolbargiohang);
        setSupportActionBar(tlbargiohang);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        tlbargiohang.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        rclgiohang = findViewById(R.id.rclViewGioHang);
        gioHangDao = new GioHangDao(this);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rclgiohang.setLayoutManager(layoutManager);

        gioHangList = gioHangDao.getAll();
        gioHangAdapter = new GioHangAdapter(gioHangList, this);
        rclgiohang.setAdapter(gioHangAdapter);


        for (GioHang gioHang : gioHangList) {
            int gia = gioHang.getTongTien(); // Lấy giá từ sản phẩm
            tongTien += gia;// Tính tổng giá tiền
        }


        // Hiển thị tổng giá tiền trong TextView
        TextView tvTongTien = findViewById(R.id.tvtongtienGH);
        tvTongTien.setText(String.valueOf(tongTien) + " vnđ");

        btndathangGH = findViewById(R.id.btndathangGH);
        btndathangGH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }
}
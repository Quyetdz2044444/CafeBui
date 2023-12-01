package quyettvph35419.fpoly.cafebuipho;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import quyettvph35419.fpoly.cafebuipho.Account.Login;
import quyettvph35419.fpoly.cafebuipho.Adapter.GioHangAdapter;
import quyettvph35419.fpoly.cafebuipho.Adapter.OnDataChangeListener;
import quyettvph35419.fpoly.cafebuipho.Dao.DonHangDao;
import quyettvph35419.fpoly.cafebuipho.Dao.GioHangDao;
import quyettvph35419.fpoly.cafebuipho.DatHang.XacNhanDatHang_GioHang;
import quyettvph35419.fpoly.cafebuipho.Model.DonHang;
import quyettvph35419.fpoly.cafebuipho.Model.DonHangChiTiet;
import quyettvph35419.fpoly.cafebuipho.Model.GioHang;


public class QLGioHang extends AppCompatActivity implements OnDataChangeListener {
    private Toolbar tlbargiohang;
    private RecyclerView rclgiohang;
    private Login login;
    private Button btndathangGH;
    private GioHangDao gioHangDao;
    private List<GioHang> gioHangList;
    private GioHangAdapter gioHangAdapter;
    private DonHang donHang;
    private DonHangDao donHangDao;
    private DonHangChiTiet donHangChiTiet;
    int sluong_incart;
    private int tongTien = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qlgio_hang);
        Intent intent = getIntent();
        String makh = intent.getStringExtra("makh");

        login = new Login();
        donHang = new DonHang();
        donHangDao = new DonHangDao(this);
        donHangChiTiet = new DonHangChiTiet();

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
        sluong_incart = gioHangDao.getCount();

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rclgiohang.setLayoutManager(layoutManager);

        gioHangList = gioHangDao.getAll();

        gioHangAdapter = new GioHangAdapter(gioHangList, this);
        gioHangAdapter.setOnDataChangeListener(this);

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
                if (sluong_incart == 0) {
                    showAlertDialog("Thông báo !", "Giỏ hàng hiện không có đồ uống nào");
                } else {
                    Intent intent = new Intent(QLGioHang.this, XacNhanDatHang_GioHang.class);
                    intent.putExtra("makh", makh);
                    startActivity(intent);
                }

            }
        });

    }


    private void showAlertDialog(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(QLGioHang.this);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    @Override
    public void onDataChanged() {
        int tongTien = 0;
        for (GioHang gioHang : gioHangList) {
            tongTien += gioHang.getTongTien();
        }
        TextView tvTongTien = findViewById(R.id.tvtongtienGH);
        tvTongTien.setText(String.valueOf(tongTien) + " vnđ");

    }

}
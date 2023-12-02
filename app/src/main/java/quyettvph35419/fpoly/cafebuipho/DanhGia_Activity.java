package quyettvph35419.fpoly.cafebuipho;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import quyettvph35419.fpoly.cafebuipho.Dao.DanhGiaDao;
import quyettvph35419.fpoly.cafebuipho.Dao.KhachHangDao;
import quyettvph35419.fpoly.cafebuipho.Model.DanhGia;
import quyettvph35419.fpoly.cafebuipho.Model.KhachHang;

public class DanhGia_Activity extends AppCompatActivity {
    private RatingBar ratingBar;
    private EditText edDanhGia;
    private TextView tvtendouong;
    private Button btnDanhGia;
    private ImageView imgdouong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_gia);
        Intent intent = getIntent();
        String tendouong = intent.getStringExtra("tendouong");
        String makh = intent.getStringExtra("makh");
        int maanh = intent.getIntExtra("maanh", -1);
        int madouong = intent.getIntExtra("madouong", -1);


        ratingBar = findViewById(R.id.ratingBar);
        edDanhGia = findViewById(R.id.edDanhGia);
        btnDanhGia = findViewById(R.id.btnDanhGia);
        tvtendouong = findViewById(R.id.tvtendouong_danhgia);
        imgdouong = findViewById(R.id.imageViewProduct);

        tvtendouong.setText(tendouong);
        int resourceId;
        switch (maanh) {
            case 1:
                resourceId = R.drawable.americano;
                break;
            case 2:
                resourceId = R.drawable.cafebacxiu;
                break;
            case 3:
                resourceId = R.drawable.capuchino;
                break;
            case 4:
                resourceId = R.drawable.cafetruyenthong;
                break;
            case 5:
                resourceId = R.drawable.macchiato;
                break;
            case 6:
                resourceId = R.drawable.irishcafe;
                break;
            case 7:
                resourceId = R.drawable.mochacafe;
                break;

            case 8:
                resourceId = R.drawable.cafelungo;
                break;
            case 9:
                resourceId = R.drawable.caferistresto;
                break;
            case 10:
                resourceId = R.drawable.cafepicolo;
                break;
            case 11:
                resourceId = R.drawable.caferedeye;
                break;
            case 12:
                resourceId = R.drawable.cafemuoi;
                break;
            case 13:
                resourceId = R.drawable.cafetrung;
                break;
            case 14:
                resourceId = R.drawable.cafelongblack;
                break;
            case 15:
                resourceId = R.drawable.cafecotdua;
                break;

            default:
                resourceId = R.drawable.cafemacdinh; // Set ảnh mặc định nếu không khớp với bất kỳ trường hợp nào
                break;
        }
        imgdouong.setImageResource(resourceId);

        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
            }
        });

        btnDanhGia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String danhGiaText = edDanhGia.getText().toString();
                float rating = ratingBar.getRating();
                DanhGia danhGia = new DanhGia();
                DanhGiaDao danhGiaDao = new DanhGiaDao(getApplicationContext());

                KhachHang khachHang = new KhachHang();
                KhachHangDao khachHangDao = new KhachHangDao(getApplicationContext());
                khachHang = khachHangDao.getID(makh);

                danhGia.setNoiDung(danhGiaText);
                danhGia.setTenKhachHang(khachHang.getHoTen());
                danhGia.setSoSao(rating);
                danhGia.setMaDoUong(madouong);
                if (danhGiaDao.insert(danhGia) > 0) {
                    Toast.makeText(DanhGia_Activity.this, "Đánh gía thành công", Toast.LENGTH_SHORT).show();
                    onBackPressed();
                } else {
                    Toast.makeText(DanhGia_Activity.this, "Đánh gía chưa được", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}
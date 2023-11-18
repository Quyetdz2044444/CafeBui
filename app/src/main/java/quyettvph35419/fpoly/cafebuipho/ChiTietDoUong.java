package quyettvph35419.fpoly.cafebuipho;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import quyettvph35419.fpoly.cafebuipho.Dao.DoUongDao;
import quyettvph35419.fpoly.cafebuipho.Dao.LoaiDoUongDao;
import quyettvph35419.fpoly.cafebuipho.Model.DoUong;
import quyettvph35419.fpoly.cafebuipho.Model.LoaiDoUong;

public class ChiTietDoUong extends AppCompatActivity {

    private TextView tvten, tvgia, tvtenloai, tvtongtien, tvSelectedQuantity;
    private ImageView image; // ảnh sp
    private DoUongDao doUongDao;
    private DoUong doUong;
    private LoaiDoUongDao loaiDoUongDAO;
    private LoaiDoUong loaiDoUong;
    private Button btnaddgio, btnmuahang;
    private RadioGroup rdoGrSize, rdoGrthanhtoan; // phương thức thanh toán
    private RadioButton rdoBtnM, rdoBtnL, rdoBtnXL, rdoBtnCard, rdoBtnBanking;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_do_uong);

        anhXa();


        Intent intent = getIntent();
        int maDoUong = intent.getIntExtra("madouong", -1);
        doUong = new DoUong();
        loaiDoUong = new LoaiDoUong();
        doUongDao = new DoUongDao(this);
        loaiDoUongDAO = new LoaiDoUongDao(this);
        doUong = doUongDao.getID(String.valueOf(maDoUong));

        loaiDoUong = loaiDoUongDAO.getID(String.valueOf(doUong.getMaLoai()));
        tvtenloai.setText("Loại : " + loaiDoUong.getTenLoai());

        tvten.setText("Tên : " + doUong.getTenDoUong());
        tvgia.setText("Giá : " + doUong.getGia());

        int initialQuantity = 1;
        tvSelectedQuantity.setText(String.valueOf(initialQuantity));

        // Calculate and update the initial total price
        updateTotalPrice();


        int vitri = doUong.getImageId();
        int resourceId;
        switch (vitri) {
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
        image.setImageResource(resourceId);

        tvSelectedQuantity.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // Not needed in this case
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                updateTotalPrice();
            }

            @Override
            public void afterTextChanged(Editable editable) {
                // Not needed in this case
            }
        });

    }

    public void decreaseQuantity(View view) {
        int currentQuantity = Integer.parseInt(tvSelectedQuantity.getText().toString());
        if (currentQuantity > 1) {
            currentQuantity--;
            tvSelectedQuantity.setText(String.valueOf(currentQuantity));
        }

    }

    public void increaseQuantity(View view) {
        int currentQuantity = Integer.parseInt(tvSelectedQuantity.getText().toString());
        currentQuantity++;
        tvSelectedQuantity.setText(String.valueOf(currentQuantity));
    }

    private void updateTotalPrice() {
        int currentQuantity = Integer.parseInt(tvSelectedQuantity.getText().toString());
        tvtongtien.setText("Tổng tiền : " + doUong.getGia() * currentQuantity);
    }

    private void anhXa() {
        tvgia = findViewById(R.id.tvgia_doUongchitiet);
        tvten = findViewById(R.id.tvten_doUongchitiet);
        tvtenloai = findViewById(R.id.tvtenloai_doUongchitiet);
        tvtongtien = findViewById(R.id.tvtongtien);
        tvSelectedQuantity = findViewById(R.id.tvSelectedQuantity);

        image = findViewById(R.id.imgdouong_chitiet);
        rdoGrthanhtoan = findViewById(R.id.radioGrthanhtoan);
        rdoBtnBanking = findViewById(R.id.rdo_banking);
        rdoBtnCard = findViewById(R.id.rdo_card);

        rdoGrSize = findViewById(R.id.radioGrSize);
        rdoBtnM = findViewById(R.id.rdo_M);
        rdoBtnL = findViewById(R.id.rdo_L);
        rdoBtnXL = findViewById(R.id.rdo_XL);

        btnaddgio = findViewById(R.id.btn_addGioHang);
        btnmuahang = findViewById(R.id.btn_muahang);

    }


}
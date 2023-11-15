package quyettvph35419.fpoly.cafebuipho;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import quyettvph35419.fpoly.cafebuipho.Dao.DoUongDao;
import quyettvph35419.fpoly.cafebuipho.Dao.LoaiDoUongDAO;
import quyettvph35419.fpoly.cafebuipho.Dao.SizeDao;
import quyettvph35419.fpoly.cafebuipho.Model.DoUong;
import quyettvph35419.fpoly.cafebuipho.Model.LoaiDoUong;
import quyettvph35419.fpoly.cafebuipho.Model.Size;

public class ChiTietDoUong extends AppCompatActivity {

    private TextView tvten, tvgia, tvtenloai;
    private ImageView image; // ảnh sp
    private DoUongDao doUongDao;
    private DoUong doUong;
    private LoaiDoUongDAO loaiDoUongDAO;
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
        loaiDoUongDAO = new LoaiDoUongDAO(this);
        doUong = doUongDao.getID(String.valueOf(maDoUong));

        loaiDoUong = loaiDoUongDAO.getID(String.valueOf(doUong.getMaLoai()));
        tvtenloai.setText("Loại : " + loaiDoUong.getTenLoai());

        tvten.setText("Tên : " + doUong.getTenDoUong());
        tvgia.setText("Giá : " + doUong.getGia());

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
            default:
                resourceId = R.drawable.cafemacdinh; // Set ảnh mặc định nếu không khớp với bất kỳ trường hợp nào
                break;
        }
        image.setImageResource(resourceId);

    }

    private void anhXa() {
        tvgia = findViewById(R.id.tvgia_doUongchitiet);
        tvten = findViewById(R.id.tvten_doUongchitiet);
        tvtenloai = findViewById(R.id.tvtenloai_doUongchitiet);

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
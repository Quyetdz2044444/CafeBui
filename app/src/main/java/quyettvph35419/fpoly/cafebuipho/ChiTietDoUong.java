package quyettvph35419.fpoly.cafebuipho;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
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
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import quyettvph35419.fpoly.cafebuipho.Account.Login;
import quyettvph35419.fpoly.cafebuipho.Dao.DoUongDao;
import quyettvph35419.fpoly.cafebuipho.Dao.GioHangDao;
import quyettvph35419.fpoly.cafebuipho.Dao.LoaiDoUongDao;
import quyettvph35419.fpoly.cafebuipho.DatHang.XacNhanDatHang_TrangChu;
import quyettvph35419.fpoly.cafebuipho.Model.DoUong;
import quyettvph35419.fpoly.cafebuipho.Model.GioHang;
import quyettvph35419.fpoly.cafebuipho.Model.LoaiDoUong;

public class ChiTietDoUong extends AppCompatActivity {

    private TextView tvten, tvgia, tvtenloai, tvtongtien, tvSelectedQuantity, tvsoluong_incart;
    private ImageView image, imggiohang; // ảnh sp
    private DoUongDao doUongDao;
    private DoUong doUong;
    private Toolbar tlToolbar;
    private LoaiDoUongDao loaiDoUongDAO;
    private LoaiDoUong loaiDoUong;
    private Button btnaddgio, btnmuahang;
    private RadioGroup rdoGrSize;
    private RadioButton rdoBtnM, rdoBtnL, rdoBtnXL;
    private Login login;
    private GioHang gioHang;
    private GioHangDao gioHangDao;
    int sluong_incart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_do_uong);

        anhXa();
        setSupportActionBar(tlToolbar);

        // Hiển thị nút Back
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        tlToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });


        Intent intent = getIntent();
        int maDoUong = intent.getIntExtra("madouong", -1);
        String makh = intent.getStringExtra("makh");


        doUong = new DoUong();
        loaiDoUong = new LoaiDoUong();
        doUongDao = new DoUongDao(this);
        loaiDoUongDAO = new LoaiDoUongDao(this);
        doUong = doUongDao.getID(String.valueOf(maDoUong));

        gioHang = new GioHang();
        gioHangDao = new GioHangDao(getApplicationContext());

        sluong_incart = gioHangDao.getCount();
        tvsoluong_incart.setText("" + sluong_incart);

        loaiDoUong = loaiDoUongDAO.getID(String.valueOf(doUong.getMaLoai()));
        tvtenloai.setText("Loại : " + loaiDoUong.getTenLoai());

        tvten.setText("Tên : " + doUong.getTenDoUong());
        tvgia.setText("" + doUong.getGia());
        int initialQuantity = 1;
        tvSelectedQuantity.setText(String.valueOf(initialQuantity));
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
        imggiohang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ChiTietDoUong.this, QLGioHang.class);
                intent.putExtra("makh", makh);
                startActivity(intent);
            }
        });

        btnaddgio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isSizeSelected()) {
                    int luachonsize = rdoGrSize.getCheckedRadioButtonId();
                    RadioButton selectedSizeRadioButton = findViewById(luachonsize);
                    rdoGrSize.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(RadioGroup group, int checkedId) {
                            updateTotalPrice(); // Cập nhật giá tiền ngay lúc chọn size
                        }
                    });

                    if (selectedSizeRadioButton != null) {
                        String selectedSize = selectedSizeRadioButton.getText().toString();

                        int size;
                        if (selectedSize.equals("M")) {
                            size = 1;
                        } else if (selectedSize.equals("L")) {
                            size = 2;
                        } else {
                            size = 3;
                        }

                        gioHang.setMaDoUong(maDoUong);
                        gioHang.setSoLuong(Integer.parseInt(tvSelectedQuantity.getText().toString()));
                        gioHang.setMaSize(size);
                        gioHang.setTongTien(Integer.parseInt(tvtongtien.getText().toString()));

                        if (gioHangDao.insert(gioHang) > 0) {
                            showAlertDialog("Đã thêm vào giỏ hàng", "Đồ uống của bạn đã được thêm vào giỏ hàng");
                            login.thongBao("Thêm vào giỏ hàng thành công", ChiTietDoUong.this);
                            sluong_incart += 1;
                            tvsoluong_incart.setText("" + sluong_incart);
                        } else {
                            Toast.makeText(ChiTietDoUong.this, "Thêm vào giỏ không thành công", Toast.LENGTH_SHORT).show();
                        }

                    } else {
                        // Xử lý khi không có RadioButton được chọn
                    }
                } else {
                    Toast.makeText(ChiTietDoUong.this, "Vui lòng chọn size trước", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnmuahang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isSizeSelected()) {
                    Intent intent1 = new Intent(ChiTietDoUong.this, XacNhanDatHang_TrangChu.class);

                    int luachonsize = rdoGrSize.getCheckedRadioButtonId();
                    RadioButton selectedSizeRadioButton = findViewById(luachonsize);
                    if (selectedSizeRadioButton != null) {
                        String selectedSize = selectedSizeRadioButton.getText().toString();
                        intent1.putExtra("size", selectedSize);
                    }

                    intent1.putExtra("madouong", maDoUong);
                    intent1.putExtra("tendouong", tvten.getText().toString());
                    intent1.putExtra("giadouong", tvgia.getText().toString());
                    intent1.putExtra("soluong", tvSelectedQuantity.getText().toString());
                    intent1.putExtra("tongtien", tvtongtien.getText().toString());
                    intent1.putExtra("makh", makh);
                    startActivity(intent1);
                } else {
                    Toast.makeText(ChiTietDoUong.this, "Vui lòng lựa chọn size", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    public void decreaseQuantity(View view) {
        if (isSizeSelected()) {
            int currentQuantity = Integer.parseInt(tvSelectedQuantity.getText().toString());
            if (currentQuantity > 1) {
                currentQuantity--;
                tvSelectedQuantity.setText(String.valueOf(currentQuantity));
            }
        } else {
            Toast.makeText(this, "Chọn size trước khi chọn số lượng", Toast.LENGTH_SHORT).show();
        }

    }

    public void increaseQuantity(View view) {
        if (isSizeSelected()) {
            int currentQuantity = Integer.parseInt(tvSelectedQuantity.getText().toString());
            currentQuantity++;
            tvSelectedQuantity.setText(String.valueOf(currentQuantity));
        } else {
            Toast.makeText(this, "Chọn size trước khi chọn số lượng", Toast.LENGTH_SHORT).show();
        }

    }

    private void updateTotalPrice() {
        int currentQuantity = Integer.parseInt(tvSelectedQuantity.getText().toString());
        int gia = doUong.getGia();

        // Lấy ID của RadioButton kích thước đang được chọn
        int selectedSizeRadioButtonId = rdoGrSize.getCheckedRadioButtonId();

        if (selectedSizeRadioButtonId == R.id.rdo_M) {
            // Nếu size là M thì giá không thay đổi
        } else if (selectedSizeRadioButtonId == R.id.rdo_L) {
            // Nếu size là L thì giá tăng thêm 10000
            gia += 10000;
        } else if (selectedSizeRadioButtonId == R.id.rdo_XL) {
            // Nếu size là XL thì giá tăng thêm 15000
            gia += 15000;
        }
        tvgia.setText("" + gia);
        tvtongtien.setText("" + gia * currentQuantity);

    }


    private boolean isSizeSelected() {
        return rdoBtnM.isChecked() || rdoBtnL.isChecked() || rdoBtnXL.isChecked();
    }

    private void showAlertDialog(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(ChiTietDoUong.this);
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

    private void anhXa() {
        tvgia = findViewById(R.id.tvgia_doUongchitiet);
        tvten = findViewById(R.id.tvten_doUongchitiet);
        tvtenloai = findViewById(R.id.tvtenloai_doUongchitiet);
        tvtongtien = findViewById(R.id.tvtongtien);
        tvSelectedQuantity = findViewById(R.id.tvSelectedQuantity);
        tvsoluong_incart = findViewById(R.id.tvsluong_incart_chitiet);

        image = findViewById(R.id.imgdouong_chitiet);
        imggiohang = findViewById(R.id.imggiohang_chitietdouong);

        rdoGrSize = findViewById(R.id.radioGrSize);
        rdoBtnM = findViewById(R.id.rdo_M);
        rdoBtnL = findViewById(R.id.rdo_L);
        rdoBtnXL = findViewById(R.id.rdo_XL);

        btnaddgio = findViewById(R.id.btn_addGioHang);
        btnmuahang = findViewById(R.id.btn_muahang);

        tlToolbar = findViewById(R.id.toolbarchitiet);

        login = new Login();
    }
}

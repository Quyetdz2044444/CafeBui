package quyettvph35419.fpoly.cafebuipho.DatHang;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import quyettvph35419.fpoly.cafebuipho.Account.Login;
import quyettvph35419.fpoly.cafebuipho.Adapter.DoUongAdapter;
import quyettvph35419.fpoly.cafebuipho.Adapter.OnDataChangeListener;
import quyettvph35419.fpoly.cafebuipho.Dao.DoUongDao;
import quyettvph35419.fpoly.cafebuipho.Dao.DonHangChiTietDao;
import quyettvph35419.fpoly.cafebuipho.Dao.DonHangDao;
import quyettvph35419.fpoly.cafebuipho.Dao.KhachHangDao;
import quyettvph35419.fpoly.cafebuipho.Model.DoUong;
import quyettvph35419.fpoly.cafebuipho.Model.DonHang;
import quyettvph35419.fpoly.cafebuipho.Model.DonHangChiTiet;
import quyettvph35419.fpoly.cafebuipho.Model.KhachHang;
import quyettvph35419.fpoly.cafebuipho.R;

public class XacNhanDatHang_TrangChu extends AppCompatActivity {
    private TextView tvHoten, tvSdt, tvDiaChi, tvTenDoUong, tvSizeDoUong, tvSoLuong, tvGiaDoUong, tvTongTien;
    private RadioGroup radioGrThanhToan;
    private RadioButton rdoBanking, rdoCard;
    private Button btnDatHang;
    private Toolbar tlbarxndathang;
    private KhachHang khachHang;
    private KhachHangDao khachHangDao;
    private DonHang donHang;
    private DoUong doUong;
    private DoUongAdapter doUongAdapter;
    List<DoUong> doUongList;
    private DonHangDao donHangDao;
    private DoUongDao doUongDao;
    private DonHangChiTiet donHangChiTiet;
    private DonHangChiTietDao donHangChiTietDao;
    private Login login;
    private OnDataChangeListener onDataChangeListener;

    public void setOnDataChangeListener(OnDataChangeListener listener) {
        this.onDataChangeListener = listener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xac_nhan_dat_hang_trang_chu);

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

        String makh = intent.getStringExtra("makh");
        khachHang = new KhachHang();
        khachHangDao = new KhachHangDao(this);
        khachHang = khachHangDao.getID(makh);

        String tendouong = intent.getStringExtra("tendouong");
        int madouong = intent.getIntExtra("madouong", -1);
        int tonkho = intent.getIntExtra("tonkho", -1);
        String size = intent.getStringExtra("size");
        String giadouong = intent.getStringExtra("giadouong");
        String soluong = intent.getStringExtra("soluong");
        String tongtien = intent.getStringExtra("tongtien");

        doUong = doUongDao.getID(String.valueOf(madouong));

        tvTenDoUong.setText(tendouong);
        tvSizeDoUong.setText("Size : " + size);
        tvSoLuong.setText("Số lượng : " + soluong);
        tvGiaDoUong.setText("Giá : " + giadouong);

        tvTongTien.setText(tongtien);

        tvHoten.setText(khachHang.getHoTen());
        tvSdt.setText(khachHang.getSdt());
        tvDiaChi.setText(khachHang.getDiaChi());


        btnDatHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int selectedRadioButtonId = radioGrThanhToan.getCheckedRadioButtonId();

                if (selectedRadioButtonId == -1) {
                    // Không có radio button nào được chọn
                    Toast.makeText(XacNhanDatHang_TrangChu.this, "Vui lòng chọn phương thức thanh toán", Toast.LENGTH_SHORT).show();
                } else {
                    RadioButton selectedRadioButton = findViewById(selectedRadioButtonId);

                    String date = getCurrentDateTime();
                    donHang = new DonHang();
                    donHangDao = new DonHangDao(getApplicationContext());
                    donHang.setMaKH(makh);
                    donHang.setGia(Integer.parseInt(tongtien));
                    donHang.setSoLuong(1);
                    donHang.setTrangThai(1);
                    donHang.setNgay(date);

                    // Thêm đơn hàng vào cơ sở dữ liệu và lấy ID vừa thêm
                    long donHangID = donHangDao.insert(donHang);

                    if (donHangID > 0) {
                        donHangChiTiet = new DonHangChiTiet();
                        donHangChiTietDao = new DonHangChiTietDao(getApplicationContext());

                        int masize;
                        if (size.equals("M")) {
                            masize = 1;
                        } else if (size.equals("L")) {
                            masize = 2;
                        } else {
                            masize = 3;
                        }

                        donHangChiTiet.setMaDH((int) donHangID);
                        donHangChiTiet.setMaDoUong(madouong);
                        donHangChiTiet.setMaSize(masize);
                        donHangChiTiet.setSoLuong(Integer.parseInt(soluong));
                        donHangChiTiet.setNgay(date);
                        donHangChiTiet.setThanhToan(selectedRadioButton.getText().toString());
                        donHangChiTiet.setTongTien(Integer.parseInt(giadouong));
                        donHangChiTiet.setTrangthaidanhgia(0);

                        int soluongdat = Integer.parseInt(soluong);
                        int sltonkho = tonkho;

                        if (sltonkho >= soluongdat) {

                            if (donHangChiTietDao.insert(donHangChiTiet) > 0) {
                                doUong.setTonKho(sltonkho - soluongdat);
                                doUongDao.updatetonkho(doUong);
                                doUongList = new ArrayList<>();
                                doUongAdapter = new DoUongAdapter(doUongList, getApplicationContext(), makh);
                                doUongAdapter.setListTonKho(doUongList);
                                login.thongBao("Đơn hàng đã được đặt !", XacNhanDatHang_TrangChu.this);
                                showAlertDialog("Đặt hàng thành công", "Cảm ơn bạn đã ủng hộ shop chúng tôi !");

                                if (onDataChangeListener != null) {
                                    onDataChangeListener.onDataChanged();
                                }

                            }
                        }

                    }
                }
            }
        });
        Button btnSuaThongTin = findViewById(R.id.btnSuaThongTin);
        btnSuaThongTin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showEditDialog();
            }
        });


    }

    private String getCurrentDateTime() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss", Locale.getDefault());
        return dateFormat.format(calendar.getTime());
    }

    public void showAlertDialog(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(XacNhanDatHang_TrangChu.this);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                onBackPressed();
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
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
        doUongDao = new DoUongDao(this);
        login = new Login();


    }

    private void showEditDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Sửa thông tin");

        // Inflate layout của dialog
        View view = getLayoutInflater().inflate(R.layout.dialog_thongtin_dathang, null);
        builder.setView(view);

        // Ánh xạ các view trong dialog
        TextInputEditText edtHoTen = view.findViewById(R.id.edtHoTen);
        TextInputEditText edtSoDienThoai = view.findViewById(R.id.edtSoDienThoai);
        TextInputEditText edtDiaChi = view.findViewById(R.id.edtDiaChi);

        // Thiết lập giá trị hiện tại cho các EditText
        String hoTenHienTai = tvHoten.getText().toString();
        String soDienThoaiHienTai = tvSdt.getText().toString();
        String diaChiHienTai = tvDiaChi.getText().toString();

        edtHoTen.setText(hoTenHienTai);
        edtSoDienThoai.setText(soDienThoaiHienTai);
        edtDiaChi.setText(diaChiHienTai);

        builder.setPositiveButton("Lưu", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Lấy giá trị mới từ các EditText
                String hoTenMoi = edtHoTen.getText().toString();
                String soDienThoaiMoi = edtSoDienThoai.getText().toString();
                String diaChiMoi = edtDiaChi.getText().toString();

                // Cập nhật TextViews với giá trị mới
                tvHoten.setText(hoTenMoi);
                tvSdt.setText(soDienThoaiMoi);
                tvDiaChi.setText(diaChiMoi);

                khachHang.setHoTen(hoTenMoi);
                khachHang.setDiaChi(diaChiMoi);
                khachHang.setSdt(soDienThoaiMoi);
                khachHangDao.updatePass(khachHang);

            }
        });

        builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        builder.create().show();
    }

}
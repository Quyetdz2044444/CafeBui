package quyettvph35419.fpoly.cafebuipho.Fragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

import quyettvph35419.fpoly.cafebuipho.Dao.KhachHangDao;
import quyettvph35419.fpoly.cafebuipho.Model.DoUong;
import quyettvph35419.fpoly.cafebuipho.Model.KhachHang;
import quyettvph35419.fpoly.cafebuipho.R;


public class ThongTinAcc_Fragment extends Fragment {
    private TextView tvtendaydu, tvtentk, tvsdt, tvdiachi, tvemail;
    private KhachHangDao khachHangDao;
    private ArrayList<KhachHang> list;
    private KhachHang khachHang;
    private Button btnsua;


    public ThongTinAcc_Fragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_thong_tin_acc_, container, false);

        tvtendaydu = v.findViewById(R.id.tvtendaydu);
        tvemail = v.findViewById(R.id.tvemali);
        tvdiachi = v.findViewById(R.id.tvdiachi);
        tvsdt = v.findViewById(R.id.tvsdt);
        tvtentk = v.findViewById(R.id.tvTentk);

        btnsua = v.findViewById(R.id.btnSuaUser);
        btnsua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              showUpdateDialog();
            }
        });


        khachHangDao = new KhachHangDao(getContext());
        khachHang = new KhachHang();

        Bundle bundle = getArguments();
        if (bundle != null) {

            String user = bundle.getString("user");

            // Lấy thông tin từ CSDL và gán vào quanLy
            khachHang = khachHangDao.getID(user);

            // Gán giá trị từ quanLy vào TextView
            tvtentk.setText("Tên đăng nhập: " + khachHang.getmaKH());
            tvtendaydu.setText("Họ tên: " + khachHang.getHoTen());
            tvsdt.setText("Số điện thoại: " + khachHang.getSdt());
            tvdiachi.setText("Địa chỉ: " + khachHang.getDiaChi());
            tvemail.setText("Email: " + khachHang.getEmail());
        }

        return v;
    }
    public void updateUserData() {
        // Lấy lại dữ liệu từ CSDL hoặc nơi khác
        khachHang = khachHangDao.getID(khachHang.getmaKH());

        // Cập nhật dữ liệu trên TextView
        tvtentk.setText("Tên đăng nhập: " + khachHang.getmaKH());
        tvtendaydu.setText("Họ tên: " + khachHang.getHoTen());
        tvsdt.setText("Số điện thoại: " + khachHang.getSdt());
        tvdiachi.setText("Địa chỉ: " + khachHang.getDiaChi());
        tvemail.setText("Email: " + khachHang.getEmail());
    }

    private void showUpdateDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        View dialogView = getLayoutInflater().inflate(R.layout.dialog_suathongtin_kh, null);
        builder.setView(dialogView);
        final AlertDialog dialog = builder.create();

        final EditText edtHoTen = dialogView.findViewById(R.id.edttendaydu_tt);
        final EditText edtSdt = dialogView.findViewById(R.id.edtsdt_tt);
        final EditText edtDiaChi = dialogView.findViewById(R.id.edtdiachi_tt);
        final EditText edtEmail = dialogView.findViewById(R.id.edtemali_tt);

        // Hiển thị thông tin hiện tại trong Dialog
        edtHoTen.setText(khachHang.getHoTen());
        edtSdt.setText(khachHang.getSdt());
        edtDiaChi.setText(khachHang.getDiaChi());
        edtEmail.setText(khachHang.getEmail());

        Button btnCapNhat = dialogView.findViewById(R.id.btnluu);
        Button btnhuy = dialogView.findViewById(R.id.btnhuy);
        btnCapNhat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Thực hiện cập nhật thông tin người dùng
                String hoTenMoi = edtHoTen.getText().toString();
                String sdtMoi = edtSdt.getText().toString();
                String diaChiMoi = edtDiaChi.getText().toString();
                String emailMoi = edtEmail.getText().toString();

                // Kiểm tra xem có nhập đầy đủ thông tin không (có thể thêm các kiểm tra khác)
                if (!hoTenMoi.isEmpty() && !sdtMoi.isEmpty() && !diaChiMoi.isEmpty() && !emailMoi.isEmpty()) {
                    // Thực hiện cập nhật trong CSDL
                    khachHang.setHoTen(hoTenMoi);
                    khachHang.setSdt(sdtMoi);
                    khachHang.setDiaChi(diaChiMoi);
                    khachHang.setEmail(emailMoi);

                    long result = khachHangDao.updatett(khachHang);
                    if (result > 0) {
                        // Cập nhật thành công
                        // Có thể cập nhật lại thông tin trên TextView hoặc làm gì đó khác
                        Toast.makeText(requireContext(), "Cập nhật thành công", Toast.LENGTH_SHORT).show();
                        updateUserData();
                        dialog.dismiss();

                    } else {
                        // Cập nhật thất bại
                        Toast.makeText(requireContext(), "Cập nhật thất bại", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    // Hiển thị thông báo nếu không nhập đủ thông tin
                    Toast.makeText(requireContext(), "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnhuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });


        dialog.show();
    }

}
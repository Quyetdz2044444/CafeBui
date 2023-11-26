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

    private Button btnSuaUser;

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
        btnSuaUser =v.findViewById(R.id.btnSuaUser);
        

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
        btnSuaUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogUpdateUser(khachHang);
            }
        });



        return v;
    }
    @SuppressLint("MissingInflatedId")
    private void dialogUpdateUser(KhachHang tv){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = ((Activity)getContext()).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_sua_acc,null);
        builder.setView(view);
        Dialog dialog = builder.create();
        dialog.show();

        TextInputEditText txtTenDD2 = view.findViewById(R.id.txtTenDD2);
        TextInputEditText txtSdt2 = view.findViewById(R.id.txtSdt2);
        TextInputEditText txtDC2 = view.findViewById(R.id.txtDC2);
        TextInputEditText txtEmail2 = view.findViewById(R.id.txtEmail2);
        TextInputLayout txtTenDD = view.findViewById(R.id.txtTenDD);
        TextInputLayout txtSdt = view.findViewById(R.id.txtSdt);
        TextInputLayout txtDC = view.findViewById(R.id.txtDC);
        TextInputLayout txtEmail = view.findViewById(R.id.txtEmail);

        Button btn_add = view.findViewById(R.id.TV_Update);
        Button btn_cancel = view.findViewById(R.id.TV_Cancel);

        txtTenDD2.setText(tv.getHoTen());
        txtSdt2.setText(tv.getSdt());
        txtDC2.setText(tv.getDiaChi());
        txtEmail2.setText(tv.getEmail());

        txtTenDD2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(charSequence.length() == 0){
                    txtTenDD2.setError("Vui lòng nhập tên thành viên");
                }else{
                    txtTenDD2.setError(null);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        txtSdt2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(charSequence.length() == 0){
                    txtSdt2.setError("Vui lòng không để trống năm sinh");
                }else{
                    txtSdt2.setError(null);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        txtDC2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(charSequence.length() == 0){
                    txtDC2.setError("Vui lòng không để trống năm sinh");
                }else{
                    txtDC2.setError(null);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        txtEmail2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(charSequence.length() == 0){
                    txtEmail2.setError("Vui lòng không để trống năm sinh");
                }else{
                    txtEmail2.setError(null);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String hoten = txtTenDD2.getText().toString();
                String sdt = txtSdt2.getText().toString();
                String diachi = txtDC2.getText().toString();
                String email = txtEmail2.getText().toString();
                String id = tv.getmaKH();

                boolean check = khachHangDao.update(id, hoten, sdt, diachi, email);
                if(hoten.isEmpty() || sdt.isEmpty() || diachi.isEmpty() || email.isEmpty()){
                    if(hoten.equals("")){
                        txtTenDD2.setError("Vui lòng không để trống Họ Tên");
                    }else{
                        txtTenDD2.setError(null);
                    }

                    if(sdt.equals("")){
                        txtSdt2.setError("Vui lòng không để trống năm sinh");
                    }else{
                        txtSdt2.setError(null);
                    }

                    if(diachi.equals("")){
                        txtDC2.setError("Vui lòng không để trống năm sinh");
                    }else{
                        txtDC2.setError(null);
                    }

                    if(email.equals("")){
                        txtEmail2.setError("Vui lòng không để trống năm sinh");
                    }else{
                        txtEmail2.setError(null);
                    }
                }else{
                    if(check){

                        Toast.makeText(getContext(), "Cập nhật nhân viên thành công", Toast.LENGTH_SHORT).show();
                        loadData();

                        dialog.dismiss();
                    }else{
                        Toast.makeText(getContext(), "Cập nhật nhân viên thất bại", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

    }
    private void loadData() {
        // Kiểm tra xem list đã được khởi tạo chưa
        if (list != null) {
            list.addAll(khachHangDao.getKH());
            updateUI();
        }
    }
    private void updateUI() {
        // Cập nhật các TextViews với dữ liệu mới từ list hoặc khachHangDao.getKH()
        // Ví dụ:

            KhachHang latestKhachHang = list.get(list.size() - 1);
            tvtentk.setText("Tên đăng nhập: " + latestKhachHang.getmaKH());
            tvtendaydu.setText("Họ tên: " + latestKhachHang.getHoTen());
            tvsdt.setText("Số điện thoại: " + latestKhachHang.getSdt());
            tvdiachi.setText("Địa chỉ: " + latestKhachHang.getDiaChi());
            tvemail.setText("Email: " + latestKhachHang.getEmail());

    }
}
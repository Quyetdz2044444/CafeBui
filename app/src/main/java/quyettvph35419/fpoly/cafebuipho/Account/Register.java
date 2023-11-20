package quyettvph35419.fpoly.cafebuipho.Account;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import quyettvph35419.fpoly.cafebuipho.Dao.KhachHangDao;
import quyettvph35419.fpoly.cafebuipho.Model.KhachHang;
import quyettvph35419.fpoly.cafebuipho.R;

public class Register extends AppCompatActivity {
    private TextInputEditText edUserName, edPassword, edFullname, edSdt, edDiaChi, edEmail;
    private Button btnRegister, btnCancel;
    private KhachHangDao khachHangDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        edUserName = findViewById(R.id.edUserName_dk);
        edPassword = findViewById(R.id.edPassword_dk);
        edFullname = findViewById(R.id.edFullName);
        edSdt = findViewById(R.id.edSdt_dk);
        edDiaChi = findViewById(R.id.edDiaChi_dk);
        edEmail = findViewById(R.id.edEmail_dk);
        btnRegister = findViewById(R.id.btnRegister);
        btnCancel = findViewById(R.id.btnCancel_dk);
        khachHangDao = new KhachHangDao(this);
        TextView tvback = findViewById(R.id.tvBack);
        tvback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Register.this, Login.class);
                startActivity(intent);
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edUserName.setText("");
                edPassword.setText("");
                edFullname.setText("");
                edSdt.setText("");
                edEmail.setText("");
            }
        });
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkRegister();
            }
        });


    }

    public void checkRegister() {
        String tendki = edUserName.getText().toString();
        String mkdki = edPassword.getText().toString();
        String fullname = edFullname.getText().toString();
        String sdt = edSdt.getText().toString();
        String diachi = edDiaChi.getText().toString();
        String email = edEmail.getText().toString();

        if (tendki.isEmpty() && mkdki.isEmpty()) {
            Toast.makeText(Register.this, "Bạn chưa điền gì cả", Toast.LENGTH_SHORT).show();
        } else if (tendki.isEmpty()) {
            Toast.makeText(Register.this, "Bạn chưa điền tên tài khoản", Toast.LENGTH_SHORT).show();
        } else if (mkdki.isEmpty()) {
            Toast.makeText(Register.this, "Bạn chưa điền mật khẩu", Toast.LENGTH_SHORT).show();
        } else if (sdt.isEmpty()) {
            Toast.makeText(Register.this, "Bạn chưa điền số điện thoại", Toast.LENGTH_SHORT).show();
        }else if (diachi.isEmpty()) {
            Toast.makeText(Register.this, "Bạn chưa điền địa chỉ", Toast.LENGTH_SHORT).show();
        } else if (email.isEmpty()) {
            Toast.makeText(Register.this, "Bạn chưa điền email", Toast.LENGTH_SHORT).show();
        } else {
//            QuanLy nd = new QuanLy(maql, mkdki, null);
            KhachHang nd = new KhachHang(tendki, fullname, mkdki, sdt, diachi, email);
            if (khachHangDao.register(nd)) {
                Toast.makeText(Register.this, "Đăng kí thành công", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(Register.this, Login.class);
                startActivity(i);
            } else {
                Toast.makeText(Register.this, "Tên tài khoản đã tồn tại", Toast.LENGTH_SHORT).show();
            }

        }
    }

}
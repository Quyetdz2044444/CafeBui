package quyettvph35419.fpoly.cafebuipho;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import quyettvph35419.fpoly.cafebuipho.Dao.QuanLyDao;
import quyettvph35419.fpoly.cafebuipho.Model.QuanLy;

public class Register extends AppCompatActivity {
    TextInputEditText edUserName, edPassword, edFullname, edSdt, edEmail;
    Button btnRegister, btnCancel;
    QuanLyDao quanLyDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        edUserName = findViewById(R.id.edUserName_dk);
        edPassword = findViewById(R.id.edPassword_dk);
        edFullname = findViewById(R.id.edFullName);
        edSdt = findViewById(R.id.edSdt_dk);
        edEmail = findViewById(R.id.edEmail_dk);
        btnRegister = findViewById(R.id.btnRegister);
        btnCancel = findViewById(R.id.btnCancel_dk);
        quanLyDao = new QuanLyDao(this);
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
        String email = edEmail.getText().toString();

        if (tendki.isEmpty() && mkdki.isEmpty()) {
            Toast.makeText(Register.this, "Bạn chưa điền gì cả", Toast.LENGTH_SHORT).show();
        } else if (tendki.isEmpty()) {
            Toast.makeText(Register.this, "Bạn chưa điền tên tài khoản", Toast.LENGTH_SHORT).show();
        } else if (mkdki.isEmpty()) {
            Toast.makeText(Register.this, "Bạn chưa điền mật khẩu", Toast.LENGTH_SHORT).show();
        } else if (sdt.isEmpty()) {
            Toast.makeText(Register.this, "Bạn chưa điền số điện thoại", Toast.LENGTH_SHORT).show();
        } else if (email.isEmpty()) {
            Toast.makeText(Register.this, "Bạn chưa điền email", Toast.LENGTH_SHORT).show();
        } else {
//            QuanLy nd = new QuanLy(maql, mkdki, null);
            QuanLy nd = new QuanLy(tendki, fullname, mkdki, sdt, email);
            if (quanLyDao.register(nd)) {
                Toast.makeText(Register.this, "Đăng kí thành công", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(Register.this, Login.class);
                startActivity(i);
            } else {
                Toast.makeText(Register.this, "Tên tài khoản đã tồn tại", Toast.LENGTH_SHORT).show();
            }

        }
    }

}
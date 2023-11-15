package quyettvph35419.fpoly.cafebuipho;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import quyettvph35419.fpoly.cafebuipho.Dao.DoUongDao;
import quyettvph35419.fpoly.cafebuipho.Model.DoUong;

public class ChiTietDoUong extends AppCompatActivity {

    private TextView tvten, tvgia;
    DoUongDao doUongDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_do_uong);
        tvgia = findViewById(R.id.tvgiadouong);
        tvten = findViewById(R.id.tvtendouong);


        tvgia.setText("Xin chào");
        tvten.setText("Hello");







    }


}
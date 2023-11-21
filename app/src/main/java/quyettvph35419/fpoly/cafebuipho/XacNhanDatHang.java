package quyettvph35419.fpoly.cafebuipho;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;

public class XacNhanDatHang extends AppCompatActivity {
    private Toolbar tlbarxndathang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xac_nhan_dat_hang);
        tlbarxndathang = findViewById(R.id.toolbarxndathang);
        setSupportActionBar(tlbarxndathang);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        tlbarxndathang.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }
}
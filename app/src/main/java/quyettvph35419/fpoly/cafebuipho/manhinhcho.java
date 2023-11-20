package quyettvph35419.fpoly.cafebuipho;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;

import quyettvph35419.fpoly.cafebuipho.Account.Login;

public class manhinhcho extends AppCompatActivity {
    private TextView appname;
    private LottieAnimationView lottie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manhinhcho);

        appname = findViewById(R.id.appname);
        Typeface typeface = ResourcesCompat.getFont(this, R.font.font3);
        appname.setTypeface(typeface);

        lottie = findViewById(R.id.lottie);
        appname.animate().translationY(-1500).setDuration(2700).setStartDelay(200);
        lottie.animate().translationY(1800).setDuration(2700).setStartDelay(3500);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(manhinhcho.this, Login.class);
                startActivity(intent);
            }
        }, 3000);
    }
}
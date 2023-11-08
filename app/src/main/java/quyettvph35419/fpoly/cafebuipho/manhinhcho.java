package quyettvph35419.fpoly.cafebuipho;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;

public class manhinhcho extends AppCompatActivity {
    TextView appname;
    LottieAnimationView lottie;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manhinhcho);

        appname = findViewById(R.id.appname);
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
        },3000);
    }
}
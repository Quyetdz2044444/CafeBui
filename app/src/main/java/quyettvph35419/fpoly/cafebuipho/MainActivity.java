package quyettvph35419.fpoly.cafebuipho;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import quyettvph35419.fpoly.cafebuipho.Dao.QuanLyDao;
import quyettvph35419.fpoly.cafebuipho.Model.QuanLy;

public class MainActivity extends AppCompatActivity {
    DrawerLayout drawer;
    Toolbar toolbar;
    View mHeaderView;
    NavigationView nv;
    TextView tvUser;
    BottomNavigationView bottomNav;
    QuanLyDao quanLyDao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNav = findViewById(R.id.bottomNav);

        // anh xa
        drawer = findViewById(R.id.drawer_layout);
        toolbar = findViewById(R.id.toolbar1);
        nv = findViewById(R.id.nvView);
        // set toolbar thay actionbar
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,
                drawer,
                toolbar,
                R.string.open, R.string.close);

        drawer.addDrawerListener(toggle);
        toggle.syncState();

        // set mau icon ve ban goc
        nv.setItemIconTintList(null);

        // show user trên header
        mHeaderView = nv.getHeaderView(0);


        tvUser = mHeaderView.findViewById(R.id.tvUser); // vị trí của tên ng dùng thuộc headerLayout
        Intent i = getIntent();// lấy dữ liệu đc gửui từ login sang đến activity hiện tại
        String user = i.getStringExtra("user"); // lấy thông tin cua user gán vào user
        quanLyDao = new QuanLyDao(this); // tạo ra 1 lớp thuthuDao mới
        QuanLy quanLy = quanLyDao.getID(user); // lấy id dựa vào hàm gethoten();
        String username = quanLy.getHoTen(); //lấy họ tên
        tvUser.setText("Welcome " + username + "!"); // set tên ng dùng lên headerlayout

        // admin co full quyền
        if (user.equalsIgnoreCase("admin")) {
            nv.getMenu().findItem(R.id.sub_AddUser).setVisible(true);
        }
//        ko phải admin thì k đc thống kê, ko được ql nhân viên, k đc thêm người dùng
        if (user.equalsIgnoreCase("trinhpk")) {
            nv.getMenu().findItem(R.id.sub_DoanhThu).setVisible(false);
            nv.getMenu().findItem(R.id.nav_NhanVien).setVisible(false);
        }
    }

    public void replaceFrg(Fragment frg) {
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.flContent, frg).commit();
    }
}
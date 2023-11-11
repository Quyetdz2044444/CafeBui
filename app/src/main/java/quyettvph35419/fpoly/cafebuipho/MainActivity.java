package quyettvph35419.fpoly.cafebuipho;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import quyettvph35419.fpoly.cafebuipho.Dao.QuanLyDao;
import quyettvph35419.fpoly.cafebuipho.Fragment.AddUser_Fragment;
import quyettvph35419.fpoly.cafebuipho.Fragment.ChangePass_Fragment;
import quyettvph35419.fpoly.cafebuipho.Fragment.DoUong_Fragment;
import quyettvph35419.fpoly.cafebuipho.Fragment.DoanhThu_Fragment;
import quyettvph35419.fpoly.cafebuipho.Fragment.HoaDon_Fragment;
import quyettvph35419.fpoly.cafebuipho.Fragment.KhachHangFragment;
import quyettvph35419.fpoly.cafebuipho.Fragment.Top5_Fragment;
import quyettvph35419.fpoly.cafebuipho.Fragment.TrangChu_Fragment;
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
        toolbar = findViewById(R.id.tlbar);
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
        }

        TrangChu_Fragment trangChuFragment = new TrangChu_Fragment();
        replaceFrg(trangChuFragment);
        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                int id = item.getItemId();
                if (id == R.id.nav_KhachHang) {
                    toolbar.setTitle("Quản lý khách hàng");
                    KhachHangFragment khachHangFragment = new KhachHangFragment();
                    replaceFrg(khachHangFragment);

                } else if (id == R.id.nav_DoUong) {
                    toolbar.setTitle("Quản lý đồ uống");
                    DoUong_Fragment doUongFragment = new DoUong_Fragment();
                    replaceFrg(doUongFragment);
                } else if (id == R.id.nav_HoaDon) {
                    toolbar.setTitle("Quản lý hóa đơn");
                    HoaDon_Fragment hoaDonFragment = new HoaDon_Fragment();
                    replaceFrg(hoaDonFragment);
                } else if (id == R.id.sub_AddUser) {
                    toolbar.setTitle("Thêm tài khoản");
                    AddUser_Fragment addUserFragment = new AddUser_Fragment();
                    replaceFrg(addUserFragment);
                } else if (id == R.id.sub_DoanhThu) {
                    toolbar.setTitle("Doanh thu");
                    DoanhThu_Fragment doanhThuFragment = new DoanhThu_Fragment();
                    replaceFrg(doanhThuFragment);
                } else if (id == R.id.sub_Top) {
                    toolbar.setTitle("Top 5 đồ uống");
                    Top5_Fragment top5Fragment = new Top5_Fragment();
                    replaceFrg(top5Fragment);
                } else if (id == R.id.sub_Pass) {
                    toolbar.setTitle("Đổi mật khẩu");
                    ChangePass_Fragment changePassFragment = new ChangePass_Fragment();
                    replaceFrg(changePassFragment);
                } else if (id == R.id.sub_Logout) {
                    android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(MainActivity.this);
                    builder.setTitle("Đăng xuất");
                    builder.setMessage("Bạn chắc muốn đăng xuất chứ?");
                    builder.setCancelable(true);

                    builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent(MainActivity.this, Login.class);
                            Toast.makeText(MainActivity.this, "Đăng xuất thành công", Toast.LENGTH_SHORT).show();
                            startActivity(intent);
                        }
                    });
                    builder.setNegativeButton("không", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
                    AlertDialog alert = builder.create();
                    alert.show();

                }
                drawer.closeDrawers();
                return true;
            }
        });
        bottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if (id == R.id.bottom_trangchu) {
                    toolbar.setTitle("Trang chủ");
                    replaceFrg(trangChuFragment);
                } else if (id == R.id.bottom_douong) {
                    toolbar.setTitle("Quản lý đồ uống");
                    DoUong_Fragment doUongFragment = new DoUong_Fragment();
                    replaceFrg(doUongFragment);
                } else if (id == R.id.bottom_hoadon) {
                    toolbar.setTitle("Quản lý hóa đơn");
                    HoaDon_Fragment hoaDonFragment = new HoaDon_Fragment();
                    replaceFrg(hoaDonFragment);
                }
                return false;
            }
        });


    }


    public void replaceFrg(Fragment frg) {
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.flContent, frg).commit();
    }
}
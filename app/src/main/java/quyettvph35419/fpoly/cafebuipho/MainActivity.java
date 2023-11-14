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
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;

import quyettvph35419.fpoly.cafebuipho.Dao.KhachHangDao;
import quyettvph35419.fpoly.cafebuipho.Fragment.KhachHang_Fragment;
import quyettvph35419.fpoly.cafebuipho.Fragment.ChangePass_Fragment;
import quyettvph35419.fpoly.cafebuipho.Fragment.DoUong_Fragment;
import quyettvph35419.fpoly.cafebuipho.Fragment.DoanhThu_Fragment;
import quyettvph35419.fpoly.cafebuipho.Fragment.DonHang_Fragment;
import quyettvph35419.fpoly.cafebuipho.Fragment.GioHang_Fragment;
import quyettvph35419.fpoly.cafebuipho.Fragment.DonHang_Admin_Fragment;
import quyettvph35419.fpoly.cafebuipho.Fragment.LoaiDoUongFragment;
import quyettvph35419.fpoly.cafebuipho.Fragment.ThongTinAcc_Fragment;
import quyettvph35419.fpoly.cafebuipho.Fragment.Top5_Fragment;
import quyettvph35419.fpoly.cafebuipho.Fragment.TrangChu_Fragment;
import quyettvph35419.fpoly.cafebuipho.Model.KhachHang;

public class MainActivity extends AppCompatActivity {
    DrawerLayout drawer;
    Toolbar toolbar;
    View mHeaderView;
    NavigationView nv;
    private TextView tvUser;
    BottomNavigationView bottomNav;
    private KhachHangDao khachHangDao;


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

        Intent i = getIntent();
        String user = i.getStringExtra("user"); // lấy thông tin cua user gán vào user

        khachHangDao = new KhachHangDao(this);
        KhachHang khachHang = khachHangDao.getID(user);
        String username = khachHang.getHoTen();
        tvUser.setText("Welcome " + username + "!");

        if (user.equalsIgnoreCase("admin")) {
            nv.getMenu().findItem(R.id.sub_DoanhThu).setVisible(true);
            nv.getMenu().findItem(R.id.nav_KhachHang).setVisible(true);
            nv.getMenu().findItem(R.id.nav_HoaDon).setVisible(true);
            nv.getMenu().findItem(R.id.nav_LoaiDoUong).setVisible(true);
            nv.getMenu().findItem(R.id.nav_DoUong).setVisible(true);
            bottomNav.setVisibility(View.GONE);

//             set trang mở lên đầu tiên là trang quản lí đồ uống
            DoUong_Fragment doUongFragment = new DoUong_Fragment();
            replaceFrg(doUongFragment);
            toolbar.setTitle("Quản lý đồ uống");

        } else {
            nv.getMenu().findItem(R.id.nav_GioHang).setVisible(true);
            nv.getMenu().findItem(R.id.nav_trangchu).setVisible(true);
            nv.getMenu().findItem(R.id.nav_DonHang).setVisible(true);
            nv.getMenu().findItem(R.id.sub_InfoAccount).setVisible(true);

            TrangChu_Fragment trangChuFragment = new TrangChu_Fragment();
            replaceFrg(trangChuFragment);
            toolbar.setTitle("Trang chủ");
        }


        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                int id = item.getItemId();
                if (id == R.id.nav_KhachHang) {
                    toolbar.setTitle("Quản lý khách hàng");
                    KhachHang_Fragment addUserFragment = new KhachHang_Fragment();
                    replaceFrg(addUserFragment);

                } else if (id == R.id.nav_trangchu) {
                    toolbar.setTitle("Trang chủ");
                    TrangChu_Fragment trangChuFragment = new TrangChu_Fragment();
                    replaceFrg(trangChuFragment);

                } else if (id == R.id.nav_LoaiDoUong) {
                    toolbar.setTitle("Quản lý loại đồ uống");
                    LoaiDoUongFragment loaiDoUongFragment = new LoaiDoUongFragment();
                    replaceFrg(loaiDoUongFragment);

                } else if (id == R.id.nav_DoUong) {
                    toolbar.setTitle("Quản lý đồ uống");
                    DoUong_Fragment doUongFragment = new DoUong_Fragment();
                    replaceFrg(doUongFragment);
                } else if (id == R.id.nav_HoaDon) {
                    toolbar.setTitle("Quản lý hóa đơn");
                    DonHang_Admin_Fragment hoaDonFragment = new DonHang_Admin_Fragment();
                    replaceFrg(hoaDonFragment);
                } else if (id == R.id.nav_DonHang) {
                    toolbar.setTitle("Quản lý đơn hàng");
                    DonHang_Fragment donHangFragment = new DonHang_Fragment();
                    replaceFrg(donHangFragment);
                } else if (id == R.id.nav_GioHang) {
                    toolbar.setTitle("Quản lý giỏ hàng");
                    GioHang_Fragment gioHangFragment = new GioHang_Fragment();
                    replaceFrg(gioHangFragment);
                } else if (id == R.id.sub_InfoAccount) {
                    toolbar.setTitle("Thông tin tài khoản");

                    Bundle bundle = new Bundle();
                    bundle.putString("user", user);

                    // Khởi tạo fragment và đính kèm bundle
                    ThongTinAcc_Fragment thongTinAccFragment = new ThongTinAcc_Fragment();
                    thongTinAccFragment.setArguments(bundle);

                    replaceFrg(thongTinAccFragment);
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
        bottomNav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if (id == R.id.bottom_trangchu) {
                    toolbar.setTitle("Trang chủ");
                    TrangChu_Fragment trangChuFragment = new TrangChu_Fragment();
                    replaceFrg(trangChuFragment);
                } else if (id == R.id.bottom_giohang) {
                    toolbar.setTitle("Quản lý giỏ hàng");
                    GioHang_Fragment gioHangFragment = new GioHang_Fragment();
                    replaceFrg(gioHangFragment);
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
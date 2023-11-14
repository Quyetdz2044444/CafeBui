package quyettvph35419.fpoly.cafebuipho.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


import java.util.ArrayList;

import quyettvph35419.fpoly.cafebuipho.Dao.KhachHangDao;
import quyettvph35419.fpoly.cafebuipho.Fragment.KhachHang_Fragment;
import quyettvph35419.fpoly.cafebuipho.Model.KhachHang;
import quyettvph35419.fpoly.cafebuipho.R;

public class KhachHangAdapter extends ArrayAdapter<KhachHang> {

    private Context context;
    private KhachHang_Fragment fragment;
    private ArrayList<KhachHang> list;
    private TextView tvMaQL, tvTenQL, tvMatKhau, tvsdt, tvdiachi, tvemail;
    private ImageView imgDel;
    private KhachHangDao dao;

    public KhachHangAdapter(@NonNull Context context, KhachHang_Fragment fragment, ArrayList<KhachHang> list) {
        super(context, 0, list);
        this.context = context;
        this.list = list;
        this.fragment = fragment;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        dao = new KhachHangDao(getContext());
        if (v == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.item_khachhang, null);

        }

        final KhachHang item = list.get(position);
        if (item != null) {
            tvMaQL = v.findViewById(R.id.tvMaQL);
            tvMaQL.setText("Mã tài khoản: " + item.getmaKH());


            tvTenQL = v.findViewById(R.id.tvTenQL);
            tvTenQL.setText("Họ tên: " + item.getHoTen());

            tvsdt = v.findViewById(R.id.tvSdt);
            tvsdt.setText("Số điện thoại: " + item.getSdt());

            tvdiachi = v.findViewById(R.id.tvDiachi);
            tvdiachi.setText("Địa chỉ: " + item.getDiaChi());

            tvemail = v.findViewById(R.id.tvEmail);
            tvemail.setText("Email: " + item.getEmail());

            tvMatKhau = v.findViewById(R.id.tvMatKhau);
            tvMatKhau.setText("Mật khẩu: " + item.getMatKhau());


            imgDel = v.findViewById(R.id.imgDeleteLS);
        }

        imgDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // pt xoa
                fragment.xoa(item.getmaKH());
            }
        });
        return v;
    }
}

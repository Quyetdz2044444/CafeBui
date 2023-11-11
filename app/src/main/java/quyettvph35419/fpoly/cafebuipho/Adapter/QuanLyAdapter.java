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

import quyettvph35419.fpoly.cafebuipho.Dao.QuanLyDao;
import quyettvph35419.fpoly.cafebuipho.Fragment.AddUser_Fragment;
import quyettvph35419.fpoly.cafebuipho.Model.QuanLy;
import quyettvph35419.fpoly.cafebuipho.R;

public class QuanLyAdapter extends ArrayAdapter<QuanLy> {

    private Context context;
    AddUser_Fragment fragment;
    private ArrayList<QuanLy> list;
    TextView tvMaQL,tvTenQL,tvMatKhau;
    ImageView imgDel;
    QuanLyDao dao;

    public QuanLyAdapter(@NonNull Context context, AddUser_Fragment fragment, ArrayList<QuanLy> list) {
        super(context, 0,list);
        this.context = context;
        this.list = list;
        this.fragment = fragment;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        dao = new QuanLyDao(getContext());
        if (v == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.item_add_user,null);

        }

        final QuanLy item = list.get(position);
        if (item != null){
            tvMaQL = v.findViewById(R.id.tvMaQL);
            tvMaQL.setText("Mã QL: "+item.getMaQL());
            tvTenQL = v.findViewById(R.id.tvTenQL);
            tvTenQL.setText("Họ tên: "+item.getHoTen());
            tvMatKhau = v.findViewById(R.id.tvMatKhau);
            tvMatKhau.setText("Mật khẩu: "+item.getMatKhau());


            imgDel = v.findViewById(R.id.imgDeleteLS);
        }

        imgDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // pt xoa
                fragment.xoa(item.getMaQL());
            }
        });
        return v;
    }
}

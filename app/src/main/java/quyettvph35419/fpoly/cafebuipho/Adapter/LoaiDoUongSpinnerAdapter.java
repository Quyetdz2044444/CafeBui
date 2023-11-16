package quyettvph35419.fpoly.cafebuipho.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import quyettvph35419.fpoly.cafebuipho.R;



import java.util.ArrayList;

import quyettvph35419.fpoly.cafebuipho.Model.LoaiDoUong;

public class LoaiDoUongSpinnerAdapter extends ArrayAdapter<LoaiDoUong> {
    private Context context;
    ArrayList<LoaiDoUong> list;
    TextView tvMaLoaiSach, tvTenLoaiSach;

    public LoaiDoUongSpinnerAdapter(@NonNull Context context, ArrayList<LoaiDoUong> list) {
        super(context, 0, list);
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.item_loai_douong_spinner, null);

        }
        final LoaiDoUong item = list.get(position);
        if (item != null) {
            tvMaLoaiSach = v.findViewById(R.id.tvMaLoaiSachSp);
            tvMaLoaiSach.setText(item.getMaLoai() + ". ");

            tvTenLoaiSach = v.findViewById(R.id.tvTenLoaiSachSp);
            tvTenLoaiSach.setText(item.getTenLoai());
        }
        return v;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.item_loai_douong_spinner, null);

        }
        final LoaiDoUong item = list.get(position);
        if (item != null) {
            tvMaLoaiSach = v.findViewById(R.id.tvMaLoaiSachSp);
            tvMaLoaiSach.setText(item.getMaLoai() + ". ");

            tvTenLoaiSach = v.findViewById(R.id.tvTenLoaiSachSp);
            tvTenLoaiSach.setText(item.getTenLoai());
        }
        return v;
    }
}

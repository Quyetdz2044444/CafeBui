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

import quyettvph35419.fpoly.cafebuipho.Dao.LoaiDoUongDao;
import quyettvph35419.fpoly.cafebuipho.Fragment.QLDoUong_Fragment;
import quyettvph35419.fpoly.cafebuipho.Model.DoUong;
import quyettvph35419.fpoly.cafebuipho.Model.LoaiDoUong;
import quyettvph35419.fpoly.cafebuipho.R;

public class DoUongAdapter_Admin extends ArrayAdapter<DoUong> {
    private Context context;
    private ArrayList<DoUong> list;

    private QLDoUong_Fragment fragment;
    private TextView tvid, tvten, tvloai, tvgia;
    private ImageView imgDoUong;

    public DoUongAdapter_Admin(@NonNull Context context, QLDoUong_Fragment fragment, ArrayList<DoUong> list) {
        super(context, 0, list);
        this.context = context;
        this.list = list;
        this.fragment = fragment;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.item_douong_admin, null);
        }
        final DoUong item = list.get(position);
        if (item != null) {
            LoaiDoUongDao loaiDoUongDAO = new LoaiDoUongDao(context);
            LoaiDoUong loaiDoUong = loaiDoUongDAO.getID(String.valueOf(item.getMaLoai()));
            tvid = v.findViewById(R.id.tvma_doUong);
            tvten = v.findViewById(R.id.tvten_doUong);
            tvloai = v.findViewById(R.id.tvloai_doUong);
            tvgia = v.findViewById(R.id.tvgia_doUong);
            imgDoUong = v.findViewById(R.id.btnxoa_doUong);


            tvid.setText("Mã: " + item.getMaDoUong());
            tvten.setText("Tên: " + item.getTenDoUong());
            tvloai.setText("Loại: " + loaiDoUong.getTenLoai());
            tvgia.setText("Giá: " + item.getGia());

        }

        imgDoUong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                fragment.xoa(String.valueOf(item.getMaDoUong()));
                list.remove(position);
                notifyDataSetChanged();
            }
        });
        return v;
    }


}

package quyettvph35419.fpoly.cafebuipho.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

import quyettvph35419.fpoly.cafebuipho.Account.Login;
import quyettvph35419.fpoly.cafebuipho.Dao.LoaiDoUongDao;
import quyettvph35419.fpoly.cafebuipho.Fragment.QLDoUong_Fragment;
import quyettvph35419.fpoly.cafebuipho.MainActivity;
import quyettvph35419.fpoly.cafebuipho.Model.DoUong;
import quyettvph35419.fpoly.cafebuipho.Model.LoaiDoUong;
import quyettvph35419.fpoly.cafebuipho.R;

public class DoUongAdapter_Admin extends ArrayAdapter<DoUong> {
    private Context context;
    private ArrayList<DoUong> list;

    private QLDoUong_Fragment fragment;
    private TextView tvid, tvten, tvloai, tvgia;
    private ImageView imgDoUong, logoDoUong;

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
            logoDoUong = v.findViewById(R.id.logoDoUong);

            int vitri = item.getImageId();
            int resourceId;
            switch (vitri) {
                case 1:
                    resourceId = R.drawable.americano;
                    break;
                case 2:
                    resourceId = R.drawable.cafebacxiu;
                    break;
                case 3:
                    resourceId = R.drawable.capuchino;
                    break;
                case 4:
                    resourceId = R.drawable.cafetruyenthong;
                    break;
                case 5:
                    resourceId = R.drawable.macchiato;
                    break;
                case 6:
                    resourceId = R.drawable.irishcafe;
                    break;
                case 7:
                    resourceId = R.drawable.mochacafe;
                    break;

                case 8:
                    resourceId = R.drawable.cafelungo;
                    break;
                case 9:
                    resourceId = R.drawable.caferistresto;
                    break;
                case 10:
                    resourceId = R.drawable.cafepicolo;
                    break;
                case 11:
                    resourceId = R.drawable.caferedeye;
                    break;
                case 12:
                    resourceId = R.drawable.cafemuoi;
                    break;
                case 13:
                    resourceId = R.drawable.cafetrung;
                    break;
                case 14:
                    resourceId = R.drawable.cafelongblack;
                    break;
                case 15:
                    resourceId = R.drawable.cafecotdua;
                    break;

                default:
                    resourceId = R.drawable.cafemacdinh; // Set ảnh mặc định nếu không khớp với bất kỳ trường hợp nào
                    break;
            }
            logoDoUong.setImageResource(resourceId);
            tvid.setText("Mã: " + item.getMaDoUong());
            tvten.setText("Tên: " + item.getTenDoUong());
            tvloai.setText("Loại: " + loaiDoUong.getTenLoai());
            tvgia.setText("Giá: " + item.getGia());

        }

        imgDoUong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                fragment.xoa(String.valueOf(item.getMaDoUong()));
                android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(context);
                builder.setTitle("Xóa đồ uống");
                builder.setMessage("Bạn chắc muốn xóa chứ?");
                builder.setCancelable(true);
                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        list.remove(position);
                        Toast.makeText(context, "Xóa thành công", Toast.LENGTH_SHORT).show();
                        notifyDataSetChanged();
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
        });
        return v;
    }


}

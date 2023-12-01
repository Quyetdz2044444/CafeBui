package quyettvph35419.fpoly.cafebuipho.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Collections;
import java.util.List;

import quyettvph35419.fpoly.cafebuipho.Dao.DanhGiaDao;
import quyettvph35419.fpoly.cafebuipho.Dao.DoUongDao;
import quyettvph35419.fpoly.cafebuipho.Dao.KhachHangDao;
import quyettvph35419.fpoly.cafebuipho.Model.DanhGia;
import quyettvph35419.fpoly.cafebuipho.Model.DoUong;
import quyettvph35419.fpoly.cafebuipho.Model.KhachHang;
import quyettvph35419.fpoly.cafebuipho.R;

public class DanhGiaAdapter extends RecyclerView.Adapter<DanhGiaAdapter.DanhGiaViewHolder> {

    private Context context;
    private List<DanhGia> danhGiaList;

    public DanhGiaAdapter(Context context, List<DanhGia> danhGiaList) {
        this.context = context;
        this.danhGiaList = danhGiaList;
    }

    @NonNull
    @Override
    public DanhGiaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_review, parent, false);
        return new DanhGiaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DanhGiaViewHolder holder, int position) {
        DanhGia danhGia = danhGiaList.get(position);
        DoUong doUong = new DoUong();
        DoUongDao doUongDao = new DoUongDao(context);
        doUong = doUongDao.getID(String.valueOf(danhGia.getMaDoUong()));

        holder.tvTenKhachHang.setText(danhGia.getTenKhachHang());

        holder.tvNoiDung.setText(danhGia.getNoiDung());
        holder.tvTenSanPham.setText(doUong.getTenDoUong());
        float sao = danhGia.getSoSao();
        holder.ratingBar.setRating(sao);

    }

    @Override
    public int getItemCount() {
        return danhGiaList.size();
    }

    public class DanhGiaViewHolder extends RecyclerView.ViewHolder {
        TextView tvTenKhachHang, tvTenSanPham, tvNoiDung;
        RatingBar ratingBar;

        public DanhGiaViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTenKhachHang = itemView.findViewById(R.id.tvTenKhachHang);
            tvTenSanPham = itemView.findViewById(R.id.tvTenSanPham);
            ratingBar = itemView.findViewById(R.id.ratingBar);
            tvNoiDung = itemView.findViewById(R.id.tvNoiDung);
        }
    }
}

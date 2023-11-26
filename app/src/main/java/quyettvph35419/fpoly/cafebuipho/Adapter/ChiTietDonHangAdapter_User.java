package quyettvph35419.fpoly.cafebuipho.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import quyettvph35419.fpoly.cafebuipho.ChiTietDonHang;
import quyettvph35419.fpoly.cafebuipho.Model.DonHangChiTiet;
import quyettvph35419.fpoly.cafebuipho.R;

public class ChiTietDonHangAdapter_User extends RecyclerView.Adapter<ChiTietDonHangAdapter_User.ChiTietDonHangViewHolder> {

    private List<DonHangChiTiet> chitietlist;
    Context context;

    public ChiTietDonHangAdapter_User(List<DonHangChiTiet> chitietlist, Context context) {
        this.chitietlist = chitietlist;
        this.context = context;
    }

    @NonNull
    @Override
    public ChiTietDonHangViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_donhangchitiet_user, parent, false);
        return new ChiTietDonHangViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChiTietDonHangViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return chitietlist.size();
    }

    public class ChiTietDonHangViewHolder extends RecyclerView.ViewHolder {
        private TextView tvten, tvsoluong, tvgia, tvngay, tvtrangthai, tvtongtien;
        private ImageView imgdouong;

        public ChiTietDonHangViewHolder(@NonNull View itemView) {
            super(itemView);

            imgdouong = itemView.findViewById(R.id.imgDoUong_chitietdonhang);
            tvten = itemView.findViewById(R.id.tvTen_chitietdonhang);
            tvgia = itemView.findViewById(R.id.tvGia_chitietdonhang);
            tvsoluong = itemView.findViewById(R.id.tvSoLuong_chitietdonhang);
            tvngay = itemView.findViewById(R.id.tvNgay_chitietdonhang);
            tvtrangthai = itemView.findViewById(R.id.tvTrangThai_chitietdonhang);
            tvtongtien = itemView.findViewById(R.id.tvTongTien_chitietdonhang);

        }
    }
}

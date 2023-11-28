package quyettvph35419.fpoly.cafebuipho.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import quyettvph35419.fpoly.cafebuipho.ChiTietDonHang;
import quyettvph35419.fpoly.cafebuipho.Dao.DoUongDao;

import quyettvph35419.fpoly.cafebuipho.Dao.DonHangChiTietDao;
import quyettvph35419.fpoly.cafebuipho.Dao.DonHangDao;
import quyettvph35419.fpoly.cafebuipho.Dao.KhachHangDao;
import quyettvph35419.fpoly.cafebuipho.Model.DoUong;
import quyettvph35419.fpoly.cafebuipho.Model.DonHang;
import quyettvph35419.fpoly.cafebuipho.Model.DonHangChiTiet;
import quyettvph35419.fpoly.cafebuipho.Model.KhachHang;
import quyettvph35419.fpoly.cafebuipho.R;

public class DonHangAdapter extends RecyclerView.Adapter<DonHangAdapter.DonHangViewHolder> {

    private List<DonHang> donHangList;
    Context context;
    private DonHangChiTiet donHangChiTiet;
    private DonHangChiTietDao donHangChiTietDao;
    private DoUongDao doUongDao;
    private DonHangDao donHangDao;
    private DoUong doUong;
    private KhachHang khachHang;
    private KhachHangDao khachHangDao;

    public DonHangAdapter(List<DonHang> donHangList, Context context) {
        this.donHangList = donHangList;
        this.context = context;
    }

    @NonNull
    @Override
    public DonHangViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_donhang_user, parent, false);
        return new DonHangViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DonHangViewHolder holder, int position) {
        DonHang donHang = donHangList.get(position);
        doUong = new DoUong();
        doUongDao = new DoUongDao(context);
        donHangDao = new DonHangDao(context);
        khachHangDao = new KhachHangDao(context);
        khachHang = khachHangDao.getID(donHang.getMaKH());

        holder.tvSoLuong.setText("Số lượng đơn hàng : " + donHang.getSoLuong());
        holder.tvGia.setText("Tổng : " + donHang.getGia());
        holder.tvtenkh.setText("Tên khách hàng : " + khachHang.getHoTen());
        holder.tvmadh.setText("Mã hóa đơn : " + donHang.getMaDH());
        holder.tvNgay.setText("Thời gian : " + donHang.getNgay());

        String trangthai = "";
        if (donHang.getTrangThai() == 1) {
            trangthai = "Chờ xác nhận";
            holder.tvTrangThai.setTextColor(Color.MAGENTA);
        } else if (donHang.getTrangThai() == 2) {
            trangthai = "Đang giao";
            holder.btnHuyDon.setVisibility(View.GONE);
            holder.tvTrangThai.setTextColor(Color.BLUE);
        } else if (donHang.getTrangThai() == 3) {
            trangthai = "Đã giao";
            holder.tvTrangThai.setTextColor(Color.GREEN);
            holder.btnHuyDon.setVisibility(View.GONE);
        } else if (donHang.getTrangThai() == 4) {
            trangthai = "Đã hủy";
            holder.btnHuyDon.setVisibility(View.GONE);
            holder.tvTrangThai.setTextColor(Color.RED);

        }
        holder.tvTrangThai.setText("Trạng thái : " + trangthai);

        holder.tvchitiet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ChiTietDonHang.class);
                intent.putExtra("madonhang", donHang.getMaDH());
                intent.putExtra("makh", donHang.getMaKH());
                context.startActivity(intent);
            }
        });
        holder.btnHuyDon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                donHangDao = new DonHangDao(context);
                donHang.setTrangThai(4);
                donHangDao.update(donHang);

                notifyDataSetChanged();
            }
        });


    }

    @Override
    public int getItemCount() {
        return donHangList.size();
    }

    public class DonHangViewHolder extends RecyclerView.ViewHolder {
        private TextView tvmadh, tvtenkh, tvSoLuong, tvGia, tvNgay, tvTrangThai, tvchitiet;
        private Button btnHuyDon;

        public DonHangViewHolder(@NonNull View itemView) {
            super(itemView);
            tvSoLuong = itemView.findViewById(R.id.tvSoLuong_donhang);
            tvmadh = itemView.findViewById(R.id.tvmadh_donhang);
            tvGia = itemView.findViewById(R.id.tvgia_donhang);
            tvNgay = itemView.findViewById(R.id.tvngay_donhang);
            tvchitiet = itemView.findViewById(R.id.tvchitiet_donhang);
            tvtenkh = itemView.findViewById(R.id.tvtenkh_donhang);
            tvTrangThai = itemView.findViewById(R.id.tvtrangthai_donhang);

            btnHuyDon = itemView.findViewById(R.id.btnHuy_donhang);
        }
    }

}

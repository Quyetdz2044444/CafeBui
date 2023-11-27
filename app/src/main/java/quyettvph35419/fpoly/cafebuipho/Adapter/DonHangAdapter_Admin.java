package quyettvph35419.fpoly.cafebuipho.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import quyettvph35419.fpoly.cafebuipho.ChiTietDonHang_Admin;
import quyettvph35419.fpoly.cafebuipho.Dao.DoUongDao;
import quyettvph35419.fpoly.cafebuipho.Dao.DonHangChiTietDao;
import quyettvph35419.fpoly.cafebuipho.Dao.DonHangDao;
import quyettvph35419.fpoly.cafebuipho.Dao.KhachHangDao;
import quyettvph35419.fpoly.cafebuipho.Model.DoUong;
import quyettvph35419.fpoly.cafebuipho.Model.DonHang;
import quyettvph35419.fpoly.cafebuipho.Model.DonHangChiTiet;
import quyettvph35419.fpoly.cafebuipho.Model.KhachHang;
import quyettvph35419.fpoly.cafebuipho.R;

public class DonHangAdapter_Admin extends RecyclerView.Adapter<DonHangAdapter_Admin.DonHangViewHolder> {

    private List<DonHang> donHangList;
    Context context;
    private DonHangChiTiet donHangChiTiet;
    private DonHangChiTietDao donHangChiTietDao;
    private DoUongDao doUongDao;
    private DonHangDao donHangDao;
    private DoUong doUong;
    private KhachHang khachHang;
    private KhachHangDao khachHangDao;

    public DonHangAdapter_Admin(List<DonHang> donHangList, Context context) {
        this.donHangList = donHangList;
        this.context = context;
    }

    @NonNull
    @Override
    public DonHangViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_donhang_admin, parent, false);
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


        holder.tvchitiet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ChiTietDonHang_Admin.class);
                intent.putExtra("madonhang", donHang.getMaDH());
                intent.putExtra("makh", donHang.getMaKH());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return donHangList.size();
    }

    public class DonHangViewHolder extends RecyclerView.ViewHolder {
        private TextView tvmadh, tvtenkh, tvSoLuong, tvGia, tvNgay, tvchitiet;


        public DonHangViewHolder(@NonNull View itemView) {
            super(itemView);
            tvSoLuong = itemView.findViewById(R.id.tvSoLuong_donhang_admin);
            tvmadh = itemView.findViewById(R.id.tvmadh_donhang_admin);
            tvGia = itemView.findViewById(R.id.tvgia_donhang_admin);
            tvNgay = itemView.findViewById(R.id.tvngay_donhang_admin);
            tvchitiet = itemView.findViewById(R.id.tvchitiet_donhang_admin);
            tvtenkh = itemView.findViewById(R.id.tvtenkh_donhang_admin);
        }
    }

}

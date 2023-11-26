package quyettvph35419.fpoly.cafebuipho.Adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import quyettvph35419.fpoly.cafebuipho.ChiTietDonHang;
import quyettvph35419.fpoly.cafebuipho.Dao.DoUongDao;
import quyettvph35419.fpoly.cafebuipho.Dao.DonHangChiTietDao;
import quyettvph35419.fpoly.cafebuipho.Model.DoUong;
import quyettvph35419.fpoly.cafebuipho.Model.DonHangChiTiet;
import quyettvph35419.fpoly.cafebuipho.R;

public class ChiTietDonHangAdapter_User extends RecyclerView.Adapter<ChiTietDonHangAdapter_User.ChiTietDonHangViewHolder> {

    private List<DonHangChiTiet> chitietlist;
    Context context;
    private DonHangChiTiet donHangChiTiet;
    private DonHangChiTietDao donHangChiTietDao;
    private DoUong doUong;
    private DoUongDao doUongDao;

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
        DonHangChiTiet donHangChiTiet = chitietlist.get(position);

        holder.tvgia.setText("Giá : " + donHangChiTiet.getTongTien());
        String trangthai = "";
        if (donHangChiTiet.getTrangThai() == 1) {
            trangthai = "Chờ xác nhận";
            holder.tvtrangthai.setTextColor(Color.MAGENTA);
        } else if (donHangChiTiet.getTrangThai() == 2) {
            trangthai = "Đang giao";
            holder.tvtrangthai.setTextColor(Color.BLUE);
        } else if (donHangChiTiet.getTrangThai() == 3) {
            trangthai = "Đã giao";
            holder.tvtrangthai.setTextColor(Color.GREEN);
        } else if (donHangChiTiet.getTrangThai() == 4) {
            trangthai = "Đã hủy";
            holder.tvtrangthai.setTextColor(Color.RED);
        }
        doUongDao = new DoUongDao(context);


//        int vitri = doUong.getImageId();
//        int resourceId;
//        switch (vitri) {
//            case 1:
//                resourceId = R.drawable.americano;
//                break;
//            case 2:
//                resourceId = R.drawable.cafebacxiu;
//                break;
//            case 3:
//                resourceId = R.drawable.capuchino;
//                break;
//            case 4:
//                resourceId = R.drawable.cafetruyenthong;
//                break;
//            case 5:
//                resourceId = R.drawable.macchiato;
//                break;
//            case 6:
//                resourceId = R.drawable.irishcafe;
//                break;
//            case 7:
//                resourceId = R.drawable.mochacafe;
//                break;
//
//            case 8:
//                resourceId = R.drawable.cafelungo;
//                break;
//            case 9:
//                resourceId = R.drawable.caferistresto;
//                break;
//            case 10:
//                resourceId = R.drawable.cafepicolo;
//                break;
//            case 11:
//                resourceId = R.drawable.caferedeye;
//                break;
//            case 12:
//                resourceId = R.drawable.cafemuoi;
//                break;
//            case 13:
//                resourceId = R.drawable.cafetrung;
//                break;
//            case 14:
//                resourceId = R.drawable.cafelongblack;
//                break;
//            case 15:
//                resourceId = R.drawable.cafecotdua;
//                break;
//
//            default:
//                resourceId = R.drawable.cafemacdinh; // Set ảnh mặc định nếu không khớp với bất kỳ trường hợp nào
//                break;
//        }
//        holder.imgdouong.setImageResource(resourceId);

//        holder.tvten.setText("Tên : " + doUong.getTenDoUong());
        holder.tvtrangthai.setText("Trạng thái : " + trangthai);
        holder.tvthanhtoan.setText("Phương thức : " + donHangChiTiet.getThanhToan());
        holder.tvsoluong.setText("Số lượng : " + donHangChiTiet.getSoLuong());
        holder.tvngay.setText("Thời gian : " + donHangChiTiet.getNgay());
        holder.tvtongtien.setText("Tổng tiền : " + donHangChiTiet.getTongTien());

    }

    @Override
    public int getItemCount() {
        return chitietlist.size();
    }

    public class ChiTietDonHangViewHolder extends RecyclerView.ViewHolder {
        private TextView tvten, tvsoluong, tvgia, tvngay, tvtrangthai, tvthanhtoan, tvtongtien;
        private ImageView imgdouong;

        public ChiTietDonHangViewHolder(@NonNull View itemView) {
            super(itemView);

            imgdouong = itemView.findViewById(R.id.imgDoUong_chitietdonhang);
            tvten = itemView.findViewById(R.id.tvTen_chitietdonhang);
            tvgia = itemView.findViewById(R.id.tvGia_chitietdonhang);
            tvsoluong = itemView.findViewById(R.id.tvSoLuong_chitietdonhang);
            tvngay = itemView.findViewById(R.id.tvNgay_chitietdonhang);
            tvtrangthai = itemView.findViewById(R.id.tvTrangThai_chitietdonhang);
            tvthanhtoan = itemView.findViewById(R.id.tvThanhToan_chitietdonhang);
            tvtongtien = itemView.findViewById(R.id.tvTongTien_chitietdonhang);

        }
    }
}

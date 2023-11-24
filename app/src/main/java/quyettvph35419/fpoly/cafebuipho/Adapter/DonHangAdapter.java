package quyettvph35419.fpoly.cafebuipho.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import quyettvph35419.fpoly.cafebuipho.Dao.DoUongDao;

import quyettvph35419.fpoly.cafebuipho.Dao.DonHangDao;
import quyettvph35419.fpoly.cafebuipho.Model.DoUong;
import quyettvph35419.fpoly.cafebuipho.Model.DonHang;
import quyettvph35419.fpoly.cafebuipho.Model.DonHangChiTiet;
import quyettvph35419.fpoly.cafebuipho.R;

public class DonHangAdapter extends RecyclerView.Adapter<DonHangAdapter.DonHangViewHolder> {

    private List<DonHang> donHangList;
    Context context;
    private DonHangChiTiet donHangChiTiet;
    private DoUongDao doUongDao;
    private DonHangDao donHangDao;
    private DoUong doUong;

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

        doUong = doUongDao.getID(String.valueOf(donHang.getMaDO()));

        holder.tvTenDoUong.setText("Mã : " + doUong.getTenDoUong());
        holder.tvSoLuong.setText("Số lượng: " + donHang.getSoLuong());
        holder.tvGia.setText("Tổng: " + donHang.getGia());
        String trangthai = "";
        if (donHang.getTrangThai() == 1) {
            trangthai = "Chờ xác nhận";
        } else if (donHang.getTrangThai() == 2) {
            trangthai = "Đang giao";
        } else if (donHang.getTrangThai() == 3) {
            trangthai = "Đã giao";
        } else if (donHang.getTrangThai() == 4) {
            trangthai = "Đã hủy";
        }
        holder.tvTrangThai.setText("Trạng thái: " + trangthai);
        int vitri = doUong.getImageId();
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
        holder.imgdonhang.setImageResource(resourceId);

    }

    @Override
    public int getItemCount() {
        return donHangList.size();
    }

    public class DonHangViewHolder extends RecyclerView.ViewHolder {
        private TextView tvTenDoUong, tvSoLuong, tvGia, tvTrangThai;
        private ImageView imgdonhang;
        private Button btnHuyDon;

        public DonHangViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTenDoUong = itemView.findViewById(R.id.tvten_donhang);
            tvSoLuong = itemView.findViewById(R.id.tvsoluong_donhang);
            tvGia = itemView.findViewById(R.id.tvgia_donhang);
            imgdonhang = itemView.findViewById(R.id.imgdonhang_user);
            tvTrangThai = itemView.findViewById(R.id.tvtrangthai_donhang);
            btnHuyDon = itemView.findViewById(R.id.btnHuy_donhang);
        }
    }
}

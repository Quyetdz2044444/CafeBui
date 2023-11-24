package quyettvph35419.fpoly.cafebuipho.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import quyettvph35419.fpoly.cafebuipho.Dao.DoUongDao;
import quyettvph35419.fpoly.cafebuipho.Dao.GioHangDao;
import quyettvph35419.fpoly.cafebuipho.Dao.SizeDao;
import quyettvph35419.fpoly.cafebuipho.Model.DoUong;
import quyettvph35419.fpoly.cafebuipho.Model.GioHang;
import quyettvph35419.fpoly.cafebuipho.Model.Size;
import quyettvph35419.fpoly.cafebuipho.R;

public class DoUongAdapter_Xndathang_GioHang extends RecyclerView.Adapter<DoUongAdapter_Xndathang_GioHang.DoUongGioHangViewHolder> {

    private List<GioHang> gioHangList;
    private Context context;
    private GioHangDao gioHangDao;
    private DoUongDao doUongDao;
    private DoUong doUong;
    private SizeDao sizeDao;
    private Size size;

    public DoUongAdapter_Xndathang_GioHang(List<GioHang> gioHangList, Context context) {
        this.gioHangList = gioHangList;
        this.context = context;
    }

    @NonNull
    @Override
    public DoUongGioHangViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_douong_xnhandathang_giohang, parent, false);
        return new DoUongAdapter_Xndathang_GioHang.DoUongGioHangViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DoUongGioHangViewHolder holder, int position) {
        gioHangDao = new GioHangDao(context);
        doUongDao = new DoUongDao(context);

        GioHang gioHang = gioHangList.get(position);
        doUong = new DoUong();

        doUong = doUongDao.getID(String.valueOf(gioHang.getMaDoUong()));
        holder.tvten.setText("Tên : " + doUong.getTenDoUong());

        int masize = gioHang.getMaSize();
        sizeDao = new SizeDao(context);
        size = new Size();

        size = sizeDao.getID(String.valueOf(masize));

        holder.tvsize.setText("Size : " + size.getSize());

        int gia = doUong.getGia(); // Giá mặc định

        if (size.getSize().equalsIgnoreCase("L")) {
            gia += 10000; // Nếu size là L, tăng giá thêm 10000
        } else if (size.getSize().equalsIgnoreCase("XL")) {
            gia += 15000; // Nếu size là XL, tăng giá thêm 15000
        }

        holder.tvgia.setText("Giá : " + gia);
        holder.tvtongtien.setText("Tổng : " + gia * gioHang.getSoLuong());
        holder.tvsoluong.setText("" + gioHang.getSoLuong());


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
        holder.imgGiohang.setImageResource(resourceId);

    }

    @Override
    public int getItemCount() {
        return gioHangList.size();
    }

    public class DoUongGioHangViewHolder extends RecyclerView.ViewHolder {
        private ImageView imgGiohang;
        private TextView tvten, tvgia, tvsize, tvtongtien, tvsoluong;

        public DoUongGioHangViewHolder(@NonNull View itemView) {
            super(itemView);
            imgGiohang = itemView.findViewById(R.id.img_douong_xndathang_giohang);

            tvgia = itemView.findViewById(R.id.tvgia_douong_xndathang_giohang);
            tvten = itemView.findViewById(R.id.tvten_douong_xndathang_giohang);
            tvsize = itemView.findViewById(R.id.tvsize_giohang);
            tvtongtien = itemView.findViewById(R.id.tvtongtien_itemdouong_xndathang_giohang);
            tvsoluong = itemView.findViewById(R.id.tvSelectedQuantity_xngiohang);

        }
    }

}

package quyettvph35419.fpoly.cafebuipho.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import quyettvph35419.fpoly.cafebuipho.Model.LoaiDoUong;
import quyettvph35419.fpoly.cafebuipho.R;

public class DoUongAdapter_ngang extends RecyclerView.Adapter<DoUongAdapter_ngang.DoUongAdapter_ngangholder>{
    private List<LoaiDoUong> loaiDoUongList;
    private Context context;
    private OnItemClickListener onItemClickListener;

    public DoUongAdapter_ngang(List<LoaiDoUong> loaiDoUongList, Context context, OnItemClickListener onItemClickListener) {
        this.loaiDoUongList = loaiDoUongList;
        this.context = context;
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public DoUongAdapter_ngangholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_loaidouong_ngang, parent, false);
        return new DoUongAdapter_ngangholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DoUongAdapter_ngang.DoUongAdapter_ngangholder holder, int position) {
    LoaiDoUong loaiDoUong = loaiDoUongList.get(position);
    holder.tenloaidouong.setText(loaiDoUong.getTenLoai());

        // Gọi sự kiện click khi một item được click
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick(loaiDoUong);
                }
            }
        });
        int vitri = loaiDoUong.getImgloai();
        int resourceId;
        switch (vitri) {
            case 1:
                resourceId = R.drawable.alldouong;
                break;
            case 2:
                resourceId = R.drawable.loaitruyenthong;
                break;
            case 3:
                resourceId = R.drawable.loaicafesua;
                break;
            case 4:
                resourceId = R.drawable.cafemay;
                break;
            case 5:
                resourceId = R.drawable.cafedacbiet;
                break;

            default:
                resourceId = R.drawable.cafemacdinh; // Set ảnh mặc định nếu không khớp với bất kỳ trường hợp nào
                break;
        }
        holder.imgLoaiDoUong.setImageResource(resourceId);
    }

    @Override
    public int getItemCount() {
        return loaiDoUongList.size();
    }

    public class DoUongAdapter_ngangholder extends RecyclerView.ViewHolder {
        private ImageView imgLoaiDoUong;
        private TextView tenloaidouong;
        public DoUongAdapter_ngangholder(@NonNull View itemView) {
            super(itemView);
            imgLoaiDoUong = itemView.findViewById(R.id.imgLoaiDoUong_use);
            tenloaidouong = itemView.findViewById(R.id.tvtenLoai_doUong);
        }
    }
    // Interface để lắng nghe sự kiện click
    public interface OnItemClickListener {
        void onItemClick(LoaiDoUong loaiDoUong);
    }
}

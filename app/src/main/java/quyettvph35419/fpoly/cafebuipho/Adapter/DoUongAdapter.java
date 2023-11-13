package quyettvph35419.fpoly.cafebuipho.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import quyettvph35419.fpoly.cafebuipho.Model.DoUong;
import quyettvph35419.fpoly.cafebuipho.R;

public class DoUongAdapter extends RecyclerView.Adapter<DoUongAdapter.DoUongViewHolder> {

    List<DoUong> doUongList;
    Context context;

    public DoUongAdapter(List<DoUong> doUongList, Context context) {
        this.doUongList = doUongList;
        this.context = context;
    }

    @NonNull
    @Override
    public DoUongViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_douong_use, parent, false);
        return new DoUongViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DoUongViewHolder holder, int position) {
        holder.tendouong.setText("Tên: " + doUongList.get(position).getTenDoUong());
        holder.giaDoUong.setText("Giá: " + doUongList.get(position).getGia());
        holder.imgDoUong.setImageResource(doUongList.get(position).getImageId());

    }

    @Override
    public int getItemCount() {
        return doUongList.size();
    }

    public class DoUongViewHolder extends RecyclerView.ViewHolder {
        private ImageView imgDoUong;
        private TextView tendouong;
        private TextView giaDoUong;

        public DoUongViewHolder(@NonNull View itemView) {
            super(itemView);

            imgDoUong = itemView.findViewById(R.id.imgDoUong_use);
            tendouong = itemView.findViewById(R.id.tvten_doUong);
            giaDoUong = itemView.findViewById(R.id.tvgia_doUong);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int vitri=getAdapterPosition();
                    Toast.makeText(context, "Bạn đã chọn item " +vitri, Toast.LENGTH_SHORT).show();
                }
            });


        }
    }

}

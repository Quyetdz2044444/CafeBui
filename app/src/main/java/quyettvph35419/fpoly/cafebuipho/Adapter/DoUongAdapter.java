package quyettvph35419.fpoly.cafebuipho.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import quyettvph35419.fpoly.cafebuipho.ChiTietDoUong;
import quyettvph35419.fpoly.cafebuipho.Model.DoUong;
import quyettvph35419.fpoly.cafebuipho.R;

public class DoUongAdapter extends RecyclerView.Adapter<DoUongAdapter.DoUongViewHolder> implements Filterable {

    private List<DoUong> doUongList, listOld;
    Context context;

    public DoUongAdapter(List<DoUong> doUongList, Context context) {
        this.doUongList = doUongList;
        this.context = context;
        this.listOld = new ArrayList<>(doUongList);
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

        int vitri = doUongList.get(position).getImageId();
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
            default:
                resourceId = R.drawable.cafemacdinh; // Set ảnh mặc định nếu không khớp với bất kỳ trường hợp nào
                break;
        }

        holder.imgDoUong.setImageResource(resourceId);

    }

    @Override
    public int getItemCount() {
        return doUongList.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String strSearch = charSequence.toString();
                if (strSearch.isEmpty()) {
                    doUongList = new ArrayList<>(listOld);
                } else {
                    ArrayList<DoUong> listSearch = new ArrayList<>();
                    for (DoUong uong : listOld) {
                        if (uong.getTenDoUong().toLowerCase().contains(strSearch.toLowerCase())) {
                            listSearch.add(uong);
                        }
                    }
                    doUongList = listSearch;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = doUongList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                doUongList = (ArrayList<DoUong>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    } // hàm tìm kiếm đồ uống theo tên

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
                    int vitri = getAdapterPosition();
                    Intent intent = new Intent(context, ChiTietDoUong.class);
                    context.startActivity(intent);

                }
            });


        }
    }

}

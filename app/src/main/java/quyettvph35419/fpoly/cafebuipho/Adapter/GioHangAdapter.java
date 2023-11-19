package quyettvph35419.fpoly.cafebuipho.Adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import quyettvph35419.fpoly.cafebuipho.Dao.GioHangDao;
import quyettvph35419.fpoly.cafebuipho.Model.DoUong;
import quyettvph35419.fpoly.cafebuipho.Model.GioHang;
import quyettvph35419.fpoly.cafebuipho.R;

public class GioHangAdapter extends RecyclerView.Adapter<GioHangAdapter.GioHangViewHolder> {
    private List<GioHang> gioHangList;
    Context context;
    GioHangDao gioHangDao;

    public GioHangAdapter(List<GioHang> gioHangList, Context context) {
        this.gioHangList = gioHangList;
        this.context = context;
    }

    @NonNull
    @Override
    public GioHangViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_giohang, parent, false);
        return new GioHangViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GioHangViewHolder holder, int position) {
        gioHangDao = new GioHangDao(context);
        GioHang gioHang = gioHangList.get(position);

        holder.tvtengh.setText("Tên : " + gioHang.getMaDoUong());
        holder.tvsizegh.setText("Size : " + gioHang.getMaSize());
        holder.tvgiagh.setText("Giá : " + gioHang.getTongTien());
        holder.tvsoluonggh.setText("" + gioHang.getSoLuong());

        int vitri = 1;
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

        holder.btnxoagh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                xoa(String.valueOf(gioHang.getMaGH()));
            }
        });


    }

    @Override
    public int getItemCount() {
        return gioHangList.size();
    }

    public class GioHangViewHolder extends RecyclerView.ViewHolder {
        private ImageView imgGiohang, btnxoagh;
        private TextView tvtengh, tvgiagh, tvsizegh, tvsoluonggh;
        private ImageButton btngiam, btntang;

        public GioHangViewHolder(@NonNull View itemView) {
            super(itemView);
            imgGiohang = itemView.findViewById(R.id.img_giohang);

            tvgiagh = itemView.findViewById(R.id.tvgia_giohang);
            tvtengh = itemView.findViewById(R.id.tvten_giohang);
            tvsizegh = itemView.findViewById(R.id.tvsize_giohang);
            tvsoluonggh = itemView.findViewById(R.id.tvSelectedQuantity);

            btntang = itemView.findViewById(R.id.btnIncrease);
            btngiam = itemView.findViewById(R.id.btnDecrease);
            btnxoagh = itemView.findViewById(R.id.btnxoa_gioHang);


        }
    }

    public void xoa(final String Id) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Delete");
        builder.setMessage("Bạn có muốn xóa không?");
        builder.setCancelable(true);

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                gioHangDao.delete(Id);
                gioHangList.clear();
                gioHangList = gioHangDao.getAll();
                notifyDataSetChanged();


                dialog.cancel();
                Toast.makeText(context, "Đã xóa", Toast.LENGTH_SHORT).show();

            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
                Toast.makeText(context, "Không xóa", Toast.LENGTH_SHORT).show();

            }
        });
        AlertDialog alert = builder.create();
        builder.show();
    }

}

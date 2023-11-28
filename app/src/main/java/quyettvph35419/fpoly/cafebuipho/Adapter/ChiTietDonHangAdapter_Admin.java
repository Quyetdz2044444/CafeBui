package quyettvph35419.fpoly.cafebuipho.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import quyettvph35419.fpoly.cafebuipho.Dao.DoUongDao;
import quyettvph35419.fpoly.cafebuipho.Dao.DonHangChiTietDao;
import quyettvph35419.fpoly.cafebuipho.Dao.SizeDao;
import quyettvph35419.fpoly.cafebuipho.Model.DoUong;
import quyettvph35419.fpoly.cafebuipho.Model.DonHangChiTiet;
import quyettvph35419.fpoly.cafebuipho.Model.Size;
import quyettvph35419.fpoly.cafebuipho.QLGioHang;
import quyettvph35419.fpoly.cafebuipho.R;

public class ChiTietDonHangAdapter_Admin extends RecyclerView.Adapter<ChiTietDonHangAdapter_Admin.ChiTietDonHangViewHolder> {

    private List<DonHangChiTiet> chitietlist;
    Context context;
    private DonHangChiTiet donHangChiTiet;
    private DonHangChiTietDao donHangChiTietDao;
    private DoUong doUong;
    private DoUongDao doUongDao;
    private Size size;
    private SizeDao sizeDao;

    public ChiTietDonHangAdapter_Admin(List<DonHangChiTiet> chitietlist, Context context) {
        this.chitietlist = chitietlist;
        this.context = context;
    }

    @NonNull
    @Override
    public ChiTietDonHangViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_donhangchitiet_admin, parent, false);
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
        if (trangthai.equals("Chờ xác nhận")) {
            holder.btnupdate.setVisibility(View.VISIBLE);
        }
        if (trangthai.equals("Đã giao") || trangthai.equals("Đã hủy")) {
            holder.btnupdate.setVisibility(View.GONE);
        }
        doUongDao = new DoUongDao(context);
        doUong = doUongDao.getID(String.valueOf(donHangChiTiet.getMaDoUong()));
        sizeDao = new SizeDao(context);
        size = sizeDao.getID(String.valueOf(donHangChiTiet.getMaSize()));

        holder.tvten.setText("Tên : " + doUong.getTenDoUong());
        holder.tvtrangthai.setText("Trạng thái : " + trangthai);
        holder.tvthanhtoan.setText("Phương thức : " + donHangChiTiet.getThanhToan());
        holder.tvsoluong.setText("Số lượng : " + donHangChiTiet.getSoLuong());
        holder.tvsize.setText("Size : " + size.getSize());
        holder.tvngay.setText("Thời gian : " + donHangChiTiet.getNgay());
        holder.tvtongtien.setText("Tổng tiền : " + donHangChiTiet.getTongTien() * donHangChiTiet.getSoLuong());

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
        holder.imgdouong.setImageResource(resourceId);

        holder.btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showUpdateDialog(context, donHangChiTiet);
            }
        });

        holder.btnxacnhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                donHangChiTietDao = new DonHangChiTietDao(context);
                donHangChiTiet.setTrangThai(2);
                donHangChiTietDao.update(donHangChiTiet);
                holder.btnxacnhan.setVisibility(View.GONE);
                notifyDataSetChanged();
            }
        });

    }

    @Override
    public int getItemCount() {
        return chitietlist.size();
    }

    public class ChiTietDonHangViewHolder extends RecyclerView.ViewHolder {
        private TextView tvten, tvsoluong, tvsize, tvgia, tvngay, tvtrangthai, tvthanhtoan, tvtongtien;
        private ImageView imgdouong;
        private Button btnupdate, btnxacnhan;

        public ChiTietDonHangViewHolder(@NonNull View itemView) {
            super(itemView);

            imgdouong = itemView.findViewById(R.id.imgDoUong_chitietdonhang_admin);
            tvten = itemView.findViewById(R.id.tvTen_chitietdonhang_admin);
            tvgia = itemView.findViewById(R.id.tvGia_chitietdonhang_admin);
            tvsize = itemView.findViewById(R.id.tvSize_chitietdonhang_admin);
            tvsoluong = itemView.findViewById(R.id.tvSoLuong_chitietdonhang_admin);
            tvngay = itemView.findViewById(R.id.tvNgay_chitietdonhang_admin);
            tvtrangthai = itemView.findViewById(R.id.tvTrangThai_chitietdonhang_admin);
            tvthanhtoan = itemView.findViewById(R.id.tvThanhToan_chitietdonhang_admin);
            tvtongtien = itemView.findViewById(R.id.tvTongTien_chitietdonhang_admin);

            btnupdate = itemView.findViewById(R.id.btnUpdate_chitietdonhang_admin);
            btnxacnhan = itemView.findViewById(R.id.btnxacnhan_chitietdonhang_admin);

        }
    }

    private void showUpdateDialog(Context context, DonHangChiTiet donHangChiTiet) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dialog_capnhatdonhang, null);

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setView(view);
        builder.setTitle("Cập Nhật Trạng Thái Đơn Hàng");

        Spinner spinnerTrangThai = view.findViewById(R.id.spinnerTrangThai_update);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(context,
                R.array.trangthai_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerTrangThai.setAdapter(adapter);
        builder.setPositiveButton("Cập Nhật", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String newTrangThai = spinnerTrangThai.getSelectedItem().toString();
                donHangChiTietDao = new DonHangChiTietDao(context);

                int newTrangThai1;
                if (newTrangThai.equals("Chờ xác nhận")) {
                    newTrangThai1 = 1;
                } else if (newTrangThai.equals("Đang giao")) {
                    newTrangThai1 = 2;
                } else if (newTrangThai.equals("Đã giao")) {
                    newTrangThai1 = 3;
                } else {
                    newTrangThai1 = 4;
                }
                donHangChiTiet.setTrangThai(newTrangThai1);
                donHangChiTietDao.updateTrangThai(donHangChiTiet.getMaDHCT(), newTrangThai1);
                notifyDataSetChanged();
                showAlertDialog("Cập nhật thành công !", "Bạn đã cập nhật trạng thái đơn hàng thành công");
            }
        });

        builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });

        builder.create().show();
    }

    private void showAlertDialog(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}

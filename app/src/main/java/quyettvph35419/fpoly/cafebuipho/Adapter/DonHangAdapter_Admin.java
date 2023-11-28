package quyettvph35419.fpoly.cafebuipho.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
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

    private DoUongDao doUongDao;
    private DonHangDao donHangDao;
    private DonHang donHang;
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

        String trangthai = "";
        if (donHang.getTrangThai() == 1) {
            trangthai = "Chờ xác nhận";
            holder.btnxacnhan.setVisibility(View.VISIBLE);
            holder.tvTrangThai.setTextColor(Color.MAGENTA);
        } else if (donHang.getTrangThai() == 2) {
            trangthai = "Đang giao";
            holder.tvTrangThai.setTextColor(Color.BLUE);
        } else if (donHang.getTrangThai() == 3) {
            trangthai = "Đã giao";
            holder.btncapnhat.setVisibility(View.GONE);
            holder.tvTrangThai.setTextColor(Color.GREEN);
        } else if (donHang.getTrangThai() == 4) {
            trangthai = "Đã hủy";
            holder.btncapnhat.setVisibility(View.GONE);
            holder.tvTrangThai.setTextColor(Color.RED);
        }
        holder.tvTrangThai.setText(trangthai);

        holder.tvchitiet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ChiTietDonHang_Admin.class);
                intent.putExtra("madonhang", donHang.getMaDH());
                intent.putExtra("makh", donHang.getMaKH());
                context.startActivity(intent);
            }
        });

        holder.btncapnhat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showUpdateDialog(context, donHang);
            }
        });

        holder.btnxacnhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                donHang.setTrangThai(2);
                donHangDao.update(donHang);
                holder.btnxacnhan.setVisibility(View.GONE);
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return donHangList.size();
    }

    public class DonHangViewHolder extends RecyclerView.ViewHolder {
        private TextView tvmadh, tvtenkh, tvSoLuong, tvGia, tvTrangThai, tvNgay, tvchitiet;
        private Button btnxacnhan, btncapnhat;

        public DonHangViewHolder(@NonNull View itemView) {
            super(itemView);
            tvSoLuong = itemView.findViewById(R.id.tvSoLuong_donhang_admin);
            tvmadh = itemView.findViewById(R.id.tvmadh_donhang_admin);
            tvGia = itemView.findViewById(R.id.tvgia_donhang_admin);
            tvNgay = itemView.findViewById(R.id.tvngay_donhang_admin);
            tvchitiet = itemView.findViewById(R.id.tvchitiet_donhang_admin);
            tvtenkh = itemView.findViewById(R.id.tvtenkh_donhang_admin);
            tvTrangThai = itemView.findViewById(R.id.tvtrangthai_donhang_admin);
            btnxacnhan = itemView.findViewById(R.id.btnxacnhan_donhang_admin);
            btncapnhat = itemView.findViewById(R.id.btnUpdate_donhang_admin);

        }
    }

    private void showUpdateDialog(Context context, DonHang donHang) {
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
                donHangDao = new DonHangDao(context);

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
                donHang.setTrangThai(newTrangThai1);
                donHangDao.updateTrangThai(donHang.getMaDH(), newTrangThai1);
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

    public void filterByStatus(int status) {
        List<DonHang> filteredList = new ArrayList<>();
        donHangList = donHangDao.getAll();
        for (DonHang donHang : donHangList) {
            if (donHang.getTrangThai() == status) {
                filteredList.add(donHang);
            }
        }

        // Cập nhật danh sách và thông báo sự thay đổi
        donHangList = filteredList;
        notifyDataSetChanged();
    }
    public void showAllItems() {
        donHangList = donHangDao.getAll(); // Khôi phục danh sách gốc
        notifyDataSetChanged();
    }

}

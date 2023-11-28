package quyettvph35419.fpoly.cafebuipho.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.List;

import quyettvph35419.fpoly.cafebuipho.Adapter.DonHangAdapter;
import quyettvph35419.fpoly.cafebuipho.Adapter.DonHangAdapter_Admin;
import quyettvph35419.fpoly.cafebuipho.Dao.DonHangDao;
import quyettvph35419.fpoly.cafebuipho.Model.DonHang;
import quyettvph35419.fpoly.cafebuipho.R;


public class DonHang_Admin_Fragment extends Fragment {

    private RecyclerView rcldonhang;
    private Spinner spinnerTrangThai;
    private DonHangAdapter_Admin donHangAdapter;
    private DonHangDao donHangDao;
    private List<DonHang> donHangList;


    public DonHang_Admin_Fragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_don_hang_admin, container, false);
        rcldonhang = v.findViewById(R.id.rclDonHang_admin);
        spinnerTrangThai = v.findViewById(R.id.spinnerTrangThai);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        rcldonhang.setLayoutManager(layoutManager);
        donHangDao = new DonHangDao(getContext());

        donHangList = donHangDao.getAll();
        donHangAdapter = new DonHangAdapter_Admin(donHangList, getContext());
        rcldonhang.setAdapter(donHangAdapter);

        // Sự kiện chọn một phần tử trên Spinner
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
                R.array.trangthai_array_locdonhang, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTrangThai.setAdapter(adapter);
        spinnerTrangThai.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // Lấy chuỗi trạng thái tương ứng với vị trí được chọn
                String newTrangThai = spinnerTrangThai.getSelectedItem().toString();
                if (newTrangThai.equals("Tất cả")) {
                    donHangAdapter.showAllItems();
                } else {
                    int statusInt = getStatusInt(newTrangThai);

                    // Lọc danh sách theo trạng thái được chọn và cập nhật RecyclerView
                    donHangAdapter.filterByStatus(statusInt);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Không làm gì khi không có phần tử nào được chọn
            }
        });

        return v;
    }

    private int getStatusInt(String status) {
        switch (status) {
            case "Chờ xác nhận":
                return 1;
            case "Đang giao":
                return 2;
            case "Đã giao":
                return 3;
            case "Đã hủy":
                return 4;
            default:
                return 0; // Giá trị mặc định hoặc xử lý nếu có giá trị không xác định
        }
    }
}
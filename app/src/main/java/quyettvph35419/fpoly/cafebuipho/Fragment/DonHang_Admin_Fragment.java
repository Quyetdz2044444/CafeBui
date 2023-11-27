package quyettvph35419.fpoly.cafebuipho.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import quyettvph35419.fpoly.cafebuipho.Adapter.DonHangAdapter;
import quyettvph35419.fpoly.cafebuipho.Adapter.DonHangAdapter_Admin;
import quyettvph35419.fpoly.cafebuipho.Dao.DonHangDao;
import quyettvph35419.fpoly.cafebuipho.Model.DonHang;
import quyettvph35419.fpoly.cafebuipho.R;


public class DonHang_Admin_Fragment extends Fragment {

    private RecyclerView rcldonhang;
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
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        rcldonhang.setLayoutManager(layoutManager);
        donHangDao = new DonHangDao(getContext());

        donHangList = donHangDao.getAll();
        donHangAdapter = new DonHangAdapter_Admin(donHangList, getContext());
        rcldonhang.setAdapter(donHangAdapter);

        return v;
    }
}
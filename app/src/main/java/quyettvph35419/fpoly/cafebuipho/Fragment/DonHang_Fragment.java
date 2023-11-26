package quyettvph35419.fpoly.cafebuipho.Fragment;

import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import quyettvph35419.fpoly.cafebuipho.Adapter.DonHangAdapter;
import quyettvph35419.fpoly.cafebuipho.Adapter.GioHangAdapter;
import quyettvph35419.fpoly.cafebuipho.Dao.DonHangDao;
import quyettvph35419.fpoly.cafebuipho.Model.DonHang;
import quyettvph35419.fpoly.cafebuipho.Model.GioHang;
import quyettvph35419.fpoly.cafebuipho.R;


public class DonHang_Fragment extends Fragment {


    private RecyclerView rcldonhang;
    private DonHangAdapter donHangAdapter;
    private DonHangDao donHangDao;
    private List<DonHang> donHangList;

    public DonHang_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_don_hang_user, container, false);
        rcldonhang = v.findViewById(R.id.rclDonHang_user);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        rcldonhang.setLayoutManager(layoutManager);
        donHangDao = new DonHangDao(getContext());

        donHangList = donHangDao.getAll();
        donHangAdapter = new DonHangAdapter(donHangList, getContext());
        rcldonhang.setAdapter(donHangAdapter);

        return v;
    }
}
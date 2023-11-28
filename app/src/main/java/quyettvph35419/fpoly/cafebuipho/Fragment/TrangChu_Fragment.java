package quyettvph35419.fpoly.cafebuipho.Fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import quyettvph35419.fpoly.cafebuipho.Adapter.DoUongAdapter;
import quyettvph35419.fpoly.cafebuipho.Adapter.DoUongAdapter_ngang;
import quyettvph35419.fpoly.cafebuipho.Dao.DoUongDao;
import quyettvph35419.fpoly.cafebuipho.Dao.LoaiDoUongDao;
import quyettvph35419.fpoly.cafebuipho.Model.DoUong;
import quyettvph35419.fpoly.cafebuipho.Model.LoaiDoUong;
import quyettvph35419.fpoly.cafebuipho.R;


public class TrangChu_Fragment extends Fragment {

    private List<DoUong> list;
    private List<LoaiDoUong> listngang;
    private RecyclerView rclViewDoUong, recyclerViewngang;
    private DoUongAdapter doUongAdapter;
    private DoUongAdapter_ngang loaiDoUongAdapter;
    private SearchView searchView;
    private DoUongDao doUongDao;

    public TrangChu_Fragment() {
        // Required empty public constructor
    }


    @SuppressLint("FragmentLiveDataObserve")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_trang_chu, container, false);
        searchView = v.findViewById(R.id.SearchView);

        rclViewDoUong = v.findViewById(R.id.rclViewDoUong_kh);
        recyclerViewngang = v.findViewById(R.id.rclViewLoaiDoUong_kh_ngang);

        recyclerViewngang.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        LoaiDoUongDao loaiDoUongDao = new LoaiDoUongDao(getContext());
        listngang = loaiDoUongDao.getAll();
        loaiDoUongAdapter = new DoUongAdapter_ngang(listngang, getContext(), new DoUongAdapter_ngang.OnItemClickListener() {
            @Override
            public void onItemClick(LoaiDoUong loaiDoUong) {
                // Gọi phương thức cập nhật danh sách sản phẩm khi có sự kiện click
                updateDoUongList(String.valueOf(loaiDoUong.getMaLoai()));
            }
        });

        recyclerViewngang.setAdapter(loaiDoUongAdapter);


        rclViewDoUong.setLayoutManager(new GridLayoutManager(getContext(), 2));
        doUongDao = new DoUongDao(getContext());

        list = doUongDao.getAll();
        // Tạo danh sách mới với các trường cần thiết
        List<DoUong> list2 = new ArrayList<>();
        for (DoUong doUong : list) {
            list2.add(new DoUong(doUong.getMaDoUong(), doUong.getTenDoUong(),
                    doUong.getGia(), doUong.getImageId(), doUong.getMaLoai(), doUong.getTonKho()));
        }

        Bundle bundle = getArguments();
        if (bundle != null) {
            String makh = bundle.getString("user");
            doUongAdapter = new DoUongAdapter(list2, getContext(), makh);
            rclViewDoUong.setAdapter(doUongAdapter);

        }
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                doUongAdapter.getFilter().filter(newText);
                return false;
            }
        });
        return v;

    }

    private void updateDoUongList(String maLoai) {
        DoUongDao doUongDao = new DoUongDao(getContext());
        List<DoUong> doUongTheoLoai = doUongDao.getDoUongByMaLoai((maLoai));
        doUongAdapter.setDoUongList(doUongTheoLoai);
    }

}
package quyettvph35419.fpoly.cafebuipho.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import quyettvph35419.fpoly.cafebuipho.Adapter.DoUongAdapter;
import quyettvph35419.fpoly.cafebuipho.Model.DoUong;
import quyettvph35419.fpoly.cafebuipho.R;


public class TrangChu_Fragment extends Fragment {

    List<DoUong> list;
    RecyclerView rclViewDoUong;
    DoUongAdapter doUongAdapter;

    public TrangChu_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_trang_chu, container, false);
        rclViewDoUong = v.findViewById(R.id.rclViewDoUong_kh);
        rclViewDoUong.setLayoutManager(new GridLayoutManager(getContext(), 2));

        list = new ArrayList<>();

        list.add(new DoUong("Bạc xỉu", 40000, R.drawable.cafebacxiu));
        list.add(new DoUong("Bạc xỉu", 40000, R.drawable.truyenthong));
        list.add(new DoUong("Bạc xỉu", 40000, R.drawable.americano));
        list.add(new DoUong("Bạc xỉu", 40000, R.drawable.capuchino));
        list.add(new DoUong("Bạc xỉu", 40000, R.drawable.cafebacxiu));

        doUongAdapter = new DoUongAdapter(list, getContext());
        rclViewDoUong.setAdapter(doUongAdapter);


        return v;
    }
}
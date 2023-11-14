package quyettvph35419.fpoly.cafebuipho.Fragment;

import android.os.Bundle;

import androidx.appcompat.widget.SearchView;
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

    private List<DoUong> list;
    private RecyclerView rclViewDoUong;
    private DoUongAdapter doUongAdapter;
    private SearchView searchView;

    public TrangChu_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_trang_chu, container, false);
        searchView = v.findViewById(R.id.SearchView);
        rclViewDoUong = v.findViewById(R.id.rclViewDoUong_kh);
        rclViewDoUong.setLayoutManager(new GridLayoutManager(getContext(), 2));

        list = new ArrayList<>();

        list.add(new DoUong("Cafe bạc xỉu", 40000, R.drawable.cafebacxiu));
        list.add(new DoUong("Macchiato", 50000, R.drawable.macchiato));
        list.add(new DoUong("Americano", 30000, R.drawable.americano));
        list.add(new DoUong("Capuchino", 20000, R.drawable.capuchino));
        list.add(new DoUong("Espresso", 45000, R.drawable.espresso));

        doUongAdapter = new DoUongAdapter(list, getContext());
        rclViewDoUong.setAdapter(doUongAdapter);

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
}
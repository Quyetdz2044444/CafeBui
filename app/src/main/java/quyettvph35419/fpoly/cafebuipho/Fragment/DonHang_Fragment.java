package quyettvph35419.fpoly.cafebuipho.Fragment;

import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import quyettvph35419.fpoly.cafebuipho.Adapter.GioHangAdapter;
import quyettvph35419.fpoly.cafebuipho.R;


public class DonHang_Fragment extends Fragment {


    private RecyclerView rcldonhang;

    public DonHang_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_don_hang_user, container, false);
        rcldonhang = v.findViewById(R.id.rclDonHang_user);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());


        return v;
    }
}
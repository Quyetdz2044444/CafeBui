package quyettvph35419.fpoly.cafebuipho.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

import quyettvph35419.fpoly.cafebuipho.Dao.DonHangChiTietDao;
import quyettvph35419.fpoly.cafebuipho.R;

public class Top5_Fragment extends Fragment {
    private ListView listViewTopSellingDrinks;

    public Top5_Fragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_top5_, container, false);

        listViewTopSellingDrinks = view.findViewById(R.id.listViewTopSellingDrinks);
        DonHangChiTietDao donHangChiTietDao = new DonHangChiTietDao(getContext());
        List<String> top5DrinksData = donHangChiTietDao.getTop5BestSellingDrinksWithStatus3();

        ArrayAdapter<String> adapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_list_item_1, top5DrinksData);

        listViewTopSellingDrinks.setAdapter(adapter);

        return view;
    }
}
package quyettvph35419.fpoly.cafebuipho.Fragment;

import android.app.Dialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import quyettvph35419.fpoly.cafebuipho.Adapter.DoUongAdapter_Admin;
import quyettvph35419.fpoly.cafebuipho.Dao.DoUongDao;
import quyettvph35419.fpoly.cafebuipho.Model.DoUong;
import quyettvph35419.fpoly.cafebuipho.R;


public class DoUong_Fragment extends Fragment {
    ListView listView;
    ArrayList<DoUong> list;
    static DoUongDao dao;
    DoUongAdapter_Admin adapter;
    DoUong item;
    FloatingActionButton fab;
    Dialog dialog;


    public DoUong_Fragment() {
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_do_uong, container, false);
        listView = v.findViewById(R.id.lvdouong_doUongAdmin);
        fab = v.findViewById(R.id.fab_doUongAdmin);
        dao = new DoUongDao(getActivity());
        capNhatLv();

        return  v;
    }
    void capNhatLv() {
        list = (ArrayList<DoUong>) dao.getAll();
        adapter = new DoUongAdapter_Admin(getActivity(), this, list);
        listView.setAdapter(adapter);
    }
}
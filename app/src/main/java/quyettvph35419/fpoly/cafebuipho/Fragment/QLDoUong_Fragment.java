package quyettvph35419.fpoly.cafebuipho.Fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import quyettvph35419.fpoly.cafebuipho.Adapter.DoUongAdapter_Admin;
import quyettvph35419.fpoly.cafebuipho.Adapter.LoaiDoUongSpinnerAdapter;
import quyettvph35419.fpoly.cafebuipho.Dao.DoUongDao;
import quyettvph35419.fpoly.cafebuipho.Dao.LoaiDoUongDAO;
import quyettvph35419.fpoly.cafebuipho.Model.DoUong;
import quyettvph35419.fpoly.cafebuipho.Model.LoaiDoUong;
import quyettvph35419.fpoly.cafebuipho.R;


public class QLDoUong_Fragment extends Fragment {
    private ListView listView;
    private ArrayList<DoUong> list;
    static DoUongDao dao;
    private DoUongAdapter_Admin adapter;
    private DoUong item;
    private FloatingActionButton fab;
    private Dialog dialog;
    private EditText edMaDoUong, edTenDoUong, edGia;
    private Spinner spinner;
    private Button btnSave, btnCancel;

    private LoaiDoUongSpinnerAdapter spinnerAdapter;
    private ArrayList<LoaiDoUong> listLoaiSach;
    private LoaiDoUongDAO loaiDoUongDAO;
    private int maLoaiDoUong, position;


    public QLDoUong_Fragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_do_uong, container, false);
        listView = v.findViewById(R.id.lvdouong_doUongAdmin);
        fab = v.findViewById(R.id.fab_doUongAdmin);
        dao = new DoUongDao(getActivity());
        capNhatLv();

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog(getActivity(), 0);
            }
        });
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                item = list.get(position);
                openDialog(getActivity(), 1);
                return false;
            }
        });


        return v;
    }

    void capNhatLv() {
        list = (ArrayList<DoUong>) dao.getAll();
        adapter = new DoUongAdapter_Admin(getActivity(), this, list);
        listView.setAdapter(adapter);
    }

    public void xoa(final String Id) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Delete");
        builder.setMessage("Bạn có muốn xóa không?");
        builder.setCancelable(true);

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dao.delete(Id);
                capNhatLv();
                dialog.cancel();
                Toast.makeText(getContext(), "Đã xóa", Toast.LENGTH_SHORT).show();

            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
                Toast.makeText(getContext(), "Không xóa", Toast.LENGTH_SHORT).show();

            }
        });
        AlertDialog alert = builder.create();
        builder.show();
    }

    protected void openDialog(final Context context, final int type) {
        dialog = new Dialog(context);
        dialog.setContentView(R.layout.dialog_douong_admin);

        edMaDoUong = dialog.findViewById(R.id.edMaDoUong);
        edTenDoUong = dialog.findViewById(R.id.edTenDoUong);
        edGia = dialog.findViewById(R.id.edGia);
        spinner = dialog.findViewById(R.id.spLoaiDoUong);

        btnCancel = dialog.findViewById(R.id.btnCancelSach);
        btnSave = dialog.findViewById(R.id.btnSaveSach);

        listLoaiSach = new ArrayList<LoaiDoUong>();
        loaiDoUongDAO = new LoaiDoUongDAO(context);
        listLoaiSach = (ArrayList<LoaiDoUong>) loaiDoUongDAO.getAll();

        spinnerAdapter = new LoaiDoUongSpinnerAdapter(context, listLoaiSach);
        spinner.setAdapter(spinnerAdapter);
        // lay maLoaiSach
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                maLoaiDoUong = listLoaiSach.get(position).getMaLoai();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        // kiem tra tupe insert hay update
        edMaDoUong.setEnabled(false);
        if (type != 0) {
            edMaDoUong.setText(String.valueOf(item.getMaDoUong()));
            edTenDoUong.setText(item.getTenDoUong());
            edGia.setText(String.valueOf(item.getGia()));

            for (int i = 0; i < listLoaiSach.size(); i++)
                if (item.getMaLoai() == (listLoaiSach.get(i).getMaLoai())) {
                    position = i;
                }
            spinner.setSelection(position);
        }

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                item = new DoUong();
                item.setTenDoUong(edTenDoUong.getText().toString());
                item.setGia(parseInt(edGia.getText().toString(), 0));
                item.setMaLoai(maLoaiDoUong);

                if (validate() > 0) {
                    if (type == 0) {
                        if (dao.insert(item) > 0) {
                            Toast.makeText(context, "Thêm thành công", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(context, "Thêm thất bại", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        item.setMaDoUong(Integer.parseInt(edMaDoUong.getText().toString()));
                        if (dao.update(item) > 0) {
                            Toast.makeText(context, "Sửa thành công", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(context, "Sứa thất bại", Toast.LENGTH_SHORT).show();
                        }
                    }
                    capNhatLv();
                    dialog.dismiss();
                }

            }
        });
        dialog.show();
    }

    public int validate() {
        int check = 1;
        if (edTenDoUong.getText().length() == 0 || edGia.getText().length() == 0) {
            Toast.makeText(getContext(), "Bạn phải nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            check = -1;
        }
        return check;
    }

    public static int parseInt(String string, int defaultValue) {
        try {
            return Integer.parseInt(string);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

}
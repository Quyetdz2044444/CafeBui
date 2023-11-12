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
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import quyettvph35419.fpoly.cafebuipho.Adapter.KhachHangAdapter;
import quyettvph35419.fpoly.cafebuipho.Dao.KhachHangDao;
import quyettvph35419.fpoly.cafebuipho.Model.KhachHang;
import quyettvph35419.fpoly.cafebuipho.R;


public class KhachHang_Fragment extends Fragment {


    ListView lvQuanLy;
    ArrayList<KhachHang> list;
    FloatingActionButton fab;
    Dialog dialog;
    TextView tvTitle;
    EditText edUser,edsdts,eddiachis,edemails,edHoTen, edPass, edRePass;
    Button btnSaveTT, btnCancleTT;
    static KhachHangDao dao;
    KhachHangAdapter adapter;
    KhachHang item;

    public KhachHang_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_khach_hang_, container, false);
        lvQuanLy = v.findViewById(R.id.lvQuanLy);
        fab = v.findViewById(R.id.fab);
        dao = new KhachHangDao(getActivity());
        capNhatLv();
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog(getActivity(), 0);
            }
        });

        lvQuanLy.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                item = list.get(position);
                openDialog(getActivity(), 1);// update
                return false;
            }
        });
        return v;
    }



    protected void openDialog(final Context context, final int type) {
        dialog = new Dialog(context);
        dialog.setContentView(R.layout.dialog_add_kh);
        tvTitle = dialog.findViewById(R.id.tvTitleTaiKhoan);

        edUser = dialog.findViewById(R.id.edUser);

        edHoTen = dialog.findViewById(R.id.edHoTen);
        edsdts = dialog.findViewById(R.id.edSdts);
        eddiachis = dialog.findViewById(R.id.eddiachis);
        edemails = dialog.findViewById(R.id.edemails);
        edPass = dialog.findViewById(R.id.edPass);
        edRePass = dialog.findViewById(R.id.edRePass);
        btnCancleTT = dialog.findViewById(R.id.btnCancelTT);
        btnSaveTT = dialog.findViewById(R.id.btnSaveTT);

        if (type != 0) {

            edUser.setText(item.getmaKH());
            edUser.setEnabled(false);

            edHoTen.setText(item.getHoTen());
            edsdts.setText(item.getSdt());
            eddiachis.setText(item.getDiaChi());
            edemails.setText(item.getEmail());
            edPass.setText(item.getMatKhau());
            edRePass.setText(item.getMatKhau());
            tvTitle.setText("Sửa tài khoản");
        }

        btnCancleTT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        btnSaveTT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                item = new KhachHang();


                item.setmaKH(edUser.getText().toString());

                item.setHoTen(edHoTen.getText().toString());
                item.setSdt(edsdts.getText().toString());
                item.setDiaChi(eddiachis.getText().toString());
                item.setEmail(edemails.getText().toString());
                item.setMatKhau(edPass.getText().toString());
                if (validate() > 0) {
                    if (type == 0) {
                        if (dao.insert(item) > 0) {
                            Toast.makeText(context, "Thêm thành công", Toast.LENGTH_SHORT).show();
                            edUser.setText("");
                            edHoTen.setText("");
                            edPass.setText("");
                            edRePass.setText("");
                        } else {
                            Toast.makeText(context, "Thêm thất bại", Toast.LENGTH_SHORT).show();
                        }
                    } else {

                        item.setmaKH(edUser.getText().toString());
                        if (dao.updatePass(item) > 0) {
                            Toast.makeText(context, "Sửa thành công", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(context, "Sửa thất bại", Toast.LENGTH_SHORT).show();
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
        if (edUser.getText().length() == 0 || edHoTen.getText().length() == 0 || edPass.getText().length() == 0 || edRePass.getText().length() == 0) {
            Toast.makeText(getContext(), "Bạn phải nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            check = -1;
        } else {
            String pass = edPass.getText().toString();
            String repass = edRePass.getText().toString();
            if (!pass.equals(repass)) {
                Toast.makeText(getActivity(), "Mật khẩu không khớp", Toast.LENGTH_SHORT).show();
                check = -1;
            }
        }
        return check;
    }
    void capNhatLv() {
        list = (ArrayList<KhachHang>) dao.getAll();
        adapter = new KhachHangAdapter(getActivity(), this, list);
        lvQuanLy.setAdapter(adapter);
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
}
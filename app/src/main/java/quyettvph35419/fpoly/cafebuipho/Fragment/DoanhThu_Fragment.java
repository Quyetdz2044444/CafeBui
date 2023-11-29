package quyettvph35419.fpoly.cafebuipho.Fragment;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

import quyettvph35419.fpoly.cafebuipho.Dao.DonHangDao;
import quyettvph35419.fpoly.cafebuipho.R;


public class DoanhThu_Fragment extends Fragment {
    private EditText edTuNgay, edDenNgay;
    private Button btnTuNgay, btnDenNgay, btnDoanhThu;
    private TextView tvDoanhThu;
    private Calendar calendar;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
    int mYear,mMonth,mDay;
    private DonHangDao donHangDao;

    public DoanhThu_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_doanh_thu, container, false);
        tvDoanhThu = v.findViewById(R.id.tvDoanhThu);
        edTuNgay = v.findViewById(R.id.edTuNgay);
        edDenNgay = v.findViewById(R.id.edDenNgay);
        btnTuNgay = v.findViewById(R.id.btnTuNgay);
        btnDenNgay = v.findViewById(R.id.btnDenNgay);
        btnDoanhThu = v.findViewById(R.id.btnDoanhThu);

        btnTuNgay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog d = new DatePickerDialog(getActivity(),0,mDateTuNgay,mYear,mMonth,mDay);
                d.show();
            }
        });

        btnDenNgay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog d = new DatePickerDialog(getActivity(),0,mDateDenNgay,mYear,mMonth,mDay);
                d.show();
            }
        });

        btnDoanhThu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Gọi phương thức xử lý khi người dùng nhấn nút Doanh Thu
                handleDoanhThu();
            }
        });


        return v;
    }

    DatePickerDialog.OnDateSetListener mDateTuNgay = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            mYear = year;
            mMonth = month;
            mDay = dayOfMonth;
            GregorianCalendar c = new GregorianCalendar(mYear,mMonth,mDay);
            edTuNgay.setText(sdf.format(c.getTime()));
        }
    };

    DatePickerDialog.OnDateSetListener mDateDenNgay = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            mYear = year;
            mMonth = month;
            mDay = dayOfMonth;
            GregorianCalendar c = new GregorianCalendar(mYear,mMonth,mDay);
            edDenNgay.setText(sdf.format(c.getTime()));
        }
    };
    private void handleDoanhThu() {
        // Lấy ngày bắt đầu và ngày kết thúc từ EditText
        String startDate = edTuNgay.getText().toString();
        String endDate = edDenNgay.getText().toString();

        // Gọi phương thức tính doanh thu từ Database hoặc nơi khác
         donHangDao = new DonHangDao(getContext());

        // Nếu bạn chỉ quan tâm đến đơn hàng đã giao (Trạng thái = 3), thì sử dụng mã trạng thái tương ứng.
        int trangThaiDaGiao = 3;

        // Kiểm tra xem bạn có cần lọc theo thời gian hay không
        // Log để kiểm tra giá trị của các biến
        Log.d("DoanhThu_Fragment", "startDate: " + startDate);
        Log.d("DoanhThu_Fragment", "endDate: " + endDate);
        if (startDate.isEmpty() && endDate.isEmpty()) {
            int doanhThu = donHangDao.getDoanhThu(trangThaiDaGiao, startDate, endDate);
            // Hiển thị kết quả lên TextView
            tvDoanhThu.setText(String.valueOf(doanhThu)+" "+"vnđ");
         }
        else {
            // Nếu không cần lọc theo thời gian, sử dụng null cho ngày bắt đầu và kết thúc
            int doanhThu = donHangDao.getDoanhThu(trangThaiDaGiao, null, null);
            // Hiển thị kết quả lên TextView
            tvDoanhThu.setText(String.valueOf(doanhThu)+" "+"vnđ");
        }
    }

}
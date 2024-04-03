package binhpdph44989.group1.group1.fragmentAdmin;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import binhpdph44989.group1.group1.R;
import binhpdph44989.group1.group1.adapterAdmin.GiayAdapter;
import binhpdph44989.group1.group1.dao.GiayDao;
import binhpdph44989.group1.group1.model.Giay;

public class QLGiayFragment extends Fragment {
    GiayDao giayDao;
    RecyclerView recyclerViewGiay;
    GiayAdapter giayAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ql_giay, container, false);
        FloatingActionButton floatAdd = view.findViewById(R.id.floatAddGiay);
        recyclerViewGiay = view.findViewById(R.id.recyclerGiay);
        giayDao = new GiayDao(getContext());
        floatAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Tạo và hiển thị dialog khi người dùng nhấn vào nút Floating Action Button
                showDialogToAddGiay();
            }
        });

        loadData();
        return view;
    }
    private void loadData() {
        ArrayList<Giay> giayList = giayDao.getDSGiay();

        // Khởi tạo adapter và đặt danh sách giày vào adapter
        giayAdapter = new GiayAdapter(getContext(), giayList);

        // Thiết lập layout manager cho RecyclerView
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerViewGiay.setLayoutManager(layoutManager);

        // Đặt adapter vào RecyclerView
        recyclerViewGiay.setAdapter(giayAdapter);
    }
    private void showDialogToAddGiay() {
        // Tạo dialog mới
        Dialog dialog = new Dialog(getContext());
        dialog.setContentView(R.layout.dialog_themgiay);

        // Tìm và lấy các thành phần UI từ dialog layout
        EditText edtTenGiay = dialog.findViewById(R.id.edtTenGiay);
        EditText edtHinhAnh = dialog.findViewById(R.id.edtHinhAnh);
        EditText edtGiaBan = dialog.findViewById(R.id.edtGiaBan);
        EditText edtSoLuong = dialog.findViewById(R.id.edtSoLuong);
        EditText edtSize = dialog.findViewById(R.id.edtSize);
        Button btnThem = dialog.findViewById(R.id.btnThem);
        Button btnHuy = dialog.findViewById(R.id.btnHuy);

        // Bắt sự kiện click cho nút "Thêm"
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Lấy thông tin từ các EditText
                String tenGiay = edtTenGiay.getText().toString();
                String hinhAnh = edtHinhAnh.getText().toString();
                int size = Integer.parseInt(edtSize.getText().toString());
                int giaBan = Integer.parseInt(edtGiaBan.getText().toString());
                int soLuong = Integer.parseInt(edtSoLuong.getText().toString());

                // Tạo đối tượng Giay từ thông tin đã lấy
                Giay giay = new Giay(hinhAnh, tenGiay, size, giaBan, soLuong);



                // Thêm giày vào cơ sở dữ liệu bằng cách gọi phương thức themGiay trong GiayDAO
                boolean isInserted = giayDao.themGiay(giay);

                if (isInserted) {
                    // Nếu thêm thành công, cập nhật lại RecyclerView hoặc thực hiện các hành động khác
                    loadData();

                    // Đóng dialog sau khi thêm thành công
                    dialog.dismiss();
                } else {
                    // Hiển thị thông báo thất bại hoặc xử lý lỗi khác tùy thuộc vào yêu cầu của bạn
                    Toast.makeText(getContext(), "Thêm giày không thành công!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Bắt sự kiện click cho nút "Hủy"
        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Đóng dialog khi người dùng nhấn nút "Hủy"
                dialog.dismiss();
            }
        });

        // Hiển thị dialog
        dialog.show();
    }


}

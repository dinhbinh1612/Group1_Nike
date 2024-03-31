package binhpdph44989.group1.group1.fragmentAdmin;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.HashMap;

import binhpdph44989.group1.group1.R;
import binhpdph44989.group1.group1.adapterAdmin.LoaiGiayAdapter;
import binhpdph44989.group1.group1.dao.LoaiGiayDAO;
import binhpdph44989.group1.group1.model.ItemClick;
import binhpdph44989.group1.group1.model.LoaiGiay;

public class QLLoaIGiayFragment extends Fragment implements ItemClick {
    LoaiGiayDAO loaiGiayDAO;
    RecyclerView recyclerLoaiGiay;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ql_loai_giay, container, false);
        FloatingActionButton floatAdd = view.findViewById(R.id.floatAdd);
        recyclerLoaiGiay = view.findViewById(R.id.recyclerLoaiGiay);
        loaiGiayDAO = new LoaiGiayDAO(getContext());
        SearchView svLoaiGiay = view.findViewById(R.id.svLoaiGiay);
        loadData();

        floatAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog();
            }
        });
        svLoaiGiay.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // Xử lý khi người dùng nhấn nút tìm kiếm trên bàn phím
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                // Xử lý khi người dùng thay đổi nội dung trong SearchView
                filter(newText); // Phương thức filter sẽ lọc dữ liệu dựa trên newText
                return false;
            }
        });
        return view;
    }

    private void loadData() {
        ArrayList<LoaiGiay> list = loaiGiayDAO.getDSLoaiGiay();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerLoaiGiay.setLayoutManager(linearLayoutManager);
        LoaiGiayAdapter adapter = new LoaiGiayAdapter(getContext(), list, getDSLoaiSach(), loaiGiayDAO);
        recyclerLoaiGiay.setAdapter(adapter);
    }

    private void showDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View view = inflater.inflate(R.layout.dialog_loai_giay, null);
        builder.setView(view);
        EditText edtTenLoaiGiay = view.findViewById(R.id.edtTenLoaiGiay);
        Spinner spnTrangThai = view.findViewById(R.id.spnTrangThai);

        ArrayList<String> trangThaiList = new ArrayList<>();
        trangThaiList.add("Còn hàng");
        trangThaiList.add("Hết hàng");

        // Tạo một ArrayAdapter từ ArrayList
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, trangThaiList);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnTrangThai.setAdapter(spinnerAdapter);

        builder.setNegativeButton("Thêm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String tenLoai = edtTenLoaiGiay.getText().toString();
                int trangThaiIndex = spnTrangThai.getSelectedItemPosition();
                int trangthailoai = (trangThaiIndex == 0) ? 0 : 1; // Sửa lại để lưu trạng thái đúng
                // Kiểm tra xem tên loại giày đã tồn tại chưa
                if (loaiGiayDAO.kiemTraTenLoaiGiayTonTai(tenLoai)) {
                    Toast.makeText(getContext(), "Tên loại giày đã tồn tại!", Toast.LENGTH_SHORT).show();
                    return;
                }
                boolean check = loaiGiayDAO.themLoaiGiay(tenLoai, trangthailoai);
                if (check){
                    Toast.makeText(getContext(), "Thêm loại giày thành công", Toast.LENGTH_SHORT).show();
                    loadData();
                } else {
                    Toast.makeText(getContext(), "Thêm loại giày thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        });

        builder.setPositiveButton("Hủy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.setCancelable(false);
        alertDialog.show();
    }

    private ArrayList<HashMap<String, Object>> getDSLoaiSach() {
        ArrayList<LoaiGiay> list = loaiGiayDAO.getDSLoaiGiay();
        ArrayList<HashMap<String, Object>> listHM = new ArrayList<>();
        for (LoaiGiay loai : list) {
            HashMap<String, Object> hs = new HashMap<>();
            hs.put("maloai", loai.getMaloai());
            hs.put("tenloai", loai.getTenloai());
            hs.put("trangthailoai", loai.getTrangthailoai());
            listHM.add(hs);
        }
        return listHM;
    }
    private void filter(String keyword) {
        // Tạo danh sách mới để lưu các phần tử được lọc
        ArrayList<LoaiGiay> filteredList = new ArrayList<>();
        // Duyệt qua tất cả các mục trong danh sách loại giày
        for (LoaiGiay item : loaiGiayDAO.getDSLoaiGiay()) {
            // Nếu tên loại giày chứa từ khóa tìm kiếm, thêm vào danh sách lọc
            if (item.getTenloai().toLowerCase().contains(keyword.toLowerCase())) {
                filteredList.add(item);
            }
        }
        // Cập nhật RecyclerView với danh sách loại giày đã được lọc
        LoaiGiayAdapter adapter = new LoaiGiayAdapter(getContext(), filteredList, loaiGiayDAO);
        recyclerLoaiGiay.setAdapter(adapter);
    }
    @Override
    public void onClickLoaiSach(LoaiGiay loaiGiay) {
    }
}
package binhpdph44989.group1.group1.fragmentAdmin;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import binhpdph44989.group1.group1.R;
import binhpdph44989.group1.group1.adapterAdmin.DonHangAdapter;
import binhpdph44989.group1.group1.dao.DonHangDAO;
import binhpdph44989.group1.group1.model.DonHang;

public class QLDonHangFragment extends Fragment {
    DonHangDAO donHangDAO;
    RecyclerView recyclerDonHang;
    DonHangAdapter donHangAdapter;
    SearchView searchView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ql_don_hang, container, false);
        recyclerDonHang = view.findViewById(R.id.recyclerDonHang);
        donHangDAO = new DonHangDAO(getContext());
        searchView = view.findViewById(R.id.svDonHang);
        loadData();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
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
        ArrayList<DonHang> list = donHangDAO.getDSDonHang();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerDonHang.setLayoutManager(linearLayoutManager);
        donHangAdapter = new DonHangAdapter(getContext(), list, donHangDAO);
        recyclerDonHang.setAdapter(donHangAdapter);
    }

    private void filter(String keyword) {
        // Tạo danh sách mới để lưu các phần tử được lọc
        ArrayList<DonHang> filteredList = new ArrayList<>();
        // Duyệt qua tất cả các mục trong danh sách loại giày
        for (DonHang item : donHangDAO.getDSDonHang()) {
            // Nếu tên loại giày chứa từ khóa tìm kiếm, thêm vào danh sách lọc
            if (item.getHoTen().toLowerCase().contains(keyword.toLowerCase()) || String.valueOf(item.getMaDH()).contains(keyword) || String.valueOf(item.getTenGiay()).contains(keyword)){
                filteredList.add(item);
            }
        }
        // Cập nhật RecyclerView với danh sách loại giày đã được lọc
        DonHangAdapter adapter = new DonHangAdapter(getContext(), filteredList, donHangDAO);
        recyclerDonHang.setAdapter(adapter);
    }


}

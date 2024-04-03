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
import binhpdph44989.group1.group1.adapterAdmin.TopAdapter;
import binhpdph44989.group1.group1.dao.TopDAO;
import binhpdph44989.group1.group1.model.Top;

public class QLTopFragment extends Fragment {
    TopDAO dao;
    RecyclerView recycler;
    TopAdapter adapter;
    SearchView searchView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_top_ban_chay, container, false);
        recycler = view.findViewById(R.id.recyclerTop);
        dao = new TopDAO(getContext());
        searchView = view.findViewById(R.id.svTop);
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
        ArrayList<Top> list = dao.getDSTop();
        ArrayList<Top> limitedList = new ArrayList<>();

        // Chỉ lấy 10 phần tử đầu tiên trong danh sách dữ liệu
        int limit = Math.min(list.size(), 10);
        for (int i = 0; i < limit; i++) {
            limitedList.add(list.get(i));
        }

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recycler.setLayoutManager(linearLayoutManager);
        adapter = new TopAdapter(getContext(), limitedList, dao);
        recycler.setAdapter(adapter);
    }

    private void filter(String keyword) {
        // Tạo danh sách mới để lưu các phần tử được lọc
        ArrayList<Top> filteredList = new ArrayList<>();
        // Duyệt qua tất cả các mục trong danh sách loại giày
        for (Top item : dao.getDSTop()) {
            // Nếu tên loại giày chứa từ khóa tìm kiếm, thêm vào danh sách lọc
            if (item.getTenGiay().toLowerCase().contains(keyword.toLowerCase())){
                filteredList.add(item);
            }
        }
        // Cập nhật RecyclerView với danh sách loại giày đã được lọc
        TopAdapter adapter = new TopAdapter(getContext(), filteredList, dao);
        recycler.setAdapter(adapter);
    }
}

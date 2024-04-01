package binhpdph44989.group1.group1.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.SearchView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import binhpdph44989.group1.group1.R;
import binhpdph44989.group1.group1.adapterAdmin.KhachHangAdapter;
import binhpdph44989.group1.group1.adapterAdmin.QuanLyAdapter;
import binhpdph44989.group1.group1.dao.KhachHangDAO;
import binhpdph44989.group1.group1.dao.QuanLyDAO;
import binhpdph44989.group1.group1.fragmentAdmin.QLThanhVienFragment;
import binhpdph44989.group1.group1.model.KhachHang;
import binhpdph44989.group1.group1.model.QuanLy;

public class TKKhachHangActivity extends AppCompatActivity {
    KhachHangDAO dao;
    RecyclerView recycler;
    KhachHangAdapter adapter;
    SearchView searchView;
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_khach_hang);
        recycler = findViewById(R.id.recyclerKhachHang);
        dao = new KhachHangDAO(this);
        searchView = findViewById(R.id.svKhachHang);
        imageView = findViewById(R.id.ivBack);

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
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        loadData();
    }

    private void loadData() {
        ArrayList<KhachHang> list = dao.getDSKhachHang();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recycler.setLayoutManager(linearLayoutManager);
        adapter = new KhachHangAdapter(this,list,dao);
        recycler.setAdapter(adapter);
    }
    private void filter(String keyword) {
        // Tạo danh sách mới để lưu các phần tử được lọc
        ArrayList<KhachHang> filteredList = new ArrayList<>();
        // Duyệt qua tất cả các mục trong danh sách loại giày
        for (KhachHang item : dao.getDSKhachHang()) {
            // Nếu tên loại giày chứa từ khóa tìm kiếm, thêm vào danh sách lọc
            if (item.getHoten().toLowerCase().contains(keyword.toLowerCase())){
                filteredList.add(item);
            }
        }
        // Cập nhật RecyclerView với danh sách loại giày đã được lọc
        KhachHangAdapter adapter = new KhachHangAdapter(this, filteredList, dao);
        recycler.setAdapter(adapter);
    }
}
package binhpdph44989.group1.group1.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import binhpdph44989.group1.group1.R;
import binhpdph44989.group1.group1.adapterAdmin.DonHangAdapter;
import binhpdph44989.group1.group1.adapterAdmin.QuanLyAdapter;
import binhpdph44989.group1.group1.dao.QuanLyDAO;
import binhpdph44989.group1.group1.fragmentAdmin.QLThanhVienFragment;
import binhpdph44989.group1.group1.model.DonHang;
import binhpdph44989.group1.group1.model.KhachHang;
import binhpdph44989.group1.group1.model.QuanLy;

public class TKQuanLyActivity extends AppCompatActivity {
    QuanLyDAO quanLyDAO;
    RecyclerView recyclerAdmin;
    QuanLyAdapter quanLyAdapter;
    SearchView searchView;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tkquan_ly);
        recyclerAdmin = findViewById(R.id.recyclerQuanLy);
        quanLyDAO = new QuanLyDAO(this);
        searchView = findViewById(R.id.svQuanLY);
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
                imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        onBackPressed();
                    }
                });
            }
        });

        loadData();
    }

    private void loadData() {
        ArrayList<QuanLy> list = quanLyDAO.getDSQuanLy();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerAdmin.setLayoutManager(linearLayoutManager);
        quanLyAdapter = new QuanLyAdapter(this,list,quanLyDAO);
        recyclerAdmin.setAdapter(quanLyAdapter);
    }
    private void filter(String keyword) {
        // Tạo danh sách mới để lưu các phần tử được lọc
        ArrayList<QuanLy> filteredList = new ArrayList<>();
        // Duyệt qua tất cả các mục trong danh sách loại giày
        for (QuanLy item : quanLyDAO.getDSQuanLy()) {
            // Nếu tên loại giày chứa từ khóa tìm kiếm, thêm vào danh sách lọc
            if (item.getHoten().toLowerCase().contains(keyword.toLowerCase())){
                filteredList.add(item);
            }
        }
        // Cập nhật RecyclerView với danh sách loại giày đã được lọc
        QuanLyAdapter adapter = new QuanLyAdapter(this, filteredList, quanLyDAO);
        recyclerAdmin.setAdapter(adapter);
    }
}
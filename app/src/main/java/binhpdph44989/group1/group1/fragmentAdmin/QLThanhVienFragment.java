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
import binhpdph44989.group1.group1.adapterAdmin.ThanhVienAdapter;
import binhpdph44989.group1.group1.dao.KhachHangDAO;
import binhpdph44989.group1.group1.dao.QuanLyDAO;
import binhpdph44989.group1.group1.model.KhachHang;
import binhpdph44989.group1.group1.model.QuanLy;

public class QLThanhVienFragment extends Fragment {
    QuanLyDAO quanLyDAO;
    KhachHangDAO khachHangDAO;
    RecyclerView recyclerThanhVien;
    ThanhVienAdapter adapter;
    ArrayList<QuanLy> listQuanLy;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ql_thanh_vien,container,false);
        recyclerThanhVien = view.findViewById(R.id.recyclerThanhVien);
        quanLyDAO = new QuanLyDAO(getContext());
        khachHangDAO = new KhachHangDAO(getContext());
        SearchView svThanhVien = view.findViewById(R.id.svThanhVien);
        loadData();

        svThanhVien.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        return view;
    }

    private void loadData() {
        ArrayList<QuanLy> listQuanLy = quanLyDAO.getDSQuanLy();
        ArrayList<KhachHang> listKhachHang = khachHangDAO.getDSKhachHang();

        // Tạo một danh sách chung chứa cả QuanLy và KhachHang
        ArrayList<Object> combinedList = new ArrayList<>();
        combinedList.addAll(listQuanLy);
        combinedList.addAll(listKhachHang);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerThanhVien.setLayoutManager(layoutManager);

        adapter = new ThanhVienAdapter(getContext(), combinedList);
        recyclerThanhVien.setAdapter(adapter);
    }


}

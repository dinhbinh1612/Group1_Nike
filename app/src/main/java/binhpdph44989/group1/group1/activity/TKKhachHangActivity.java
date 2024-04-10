package binhpdph44989.group1.group1.fragmentAdmin;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import binhpdph44989.group1.group1.R;
import binhpdph44989.group1.group1.adapterAdmin.GiayAdapter;
import binhpdph44989.group1.group1.dao.GiayDao;
import binhpdph44989.group1.group1.model.Giay;

public class QLGiayFragment extends Fragment {
    private static final int REQUEST_CODE_SELECT_IMAGE = 100;
    private ArrayList<Giay> giayList;
    private GiayDao giayDao;
    private RecyclerView recyclerViewGiay;
    private GiayAdapter giayAdapter;
    private int currentMaGiay = 13;
    private Uri selectedImageUri;
    private ImageView ivCreateImg; // Khai báo biến ivCreateImg như một biến toàn cục

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
                showDialogToAddGiay();
            }
        });

        loadData();
        return view;
    }

    private void loadData() {
        giayList = giayDao.getDSGiay();

        // Khởi tạo adapter và đặt danh sách giày vào adapter
        giayAdapter = new GiayAdapter(getContext(), giayList);

        // Thiết lập layout manager cho RecyclerView
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerViewGiay.setLayoutManager(layoutManager);

        // Đặt adapter vào RecyclerView
        recyclerViewGiay.setAdapter(giayAdapter);
    }

    private void showDialogToAddGiay() {
        // Lấy mã giày lớn nhất từ cơ sở dữ liệu
        int maxMaGiay = giayDao.getMaxMaGiay();
        // Tăng giá trị của mã giày lớn nhất lên một đơn vị để sử dụng khi thêm giày mới
        currentMaGiay = maxMaGiay + 1;

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View view = inflater.inflate(R.layout.dialog_themgiay, null);
        builder.setView(view);

        EditText edtTenGiay = view.findViewById(R.id.edtTenGiay);
        EditText edtGiaBan = view.findViewById(R.id.edtGiaBan);
        EditText edtSoLuongKho = view.findViewById(R.id.edtSoLuongKho);
        EditText edtSize = view.findViewById(R.id.edtMaLoai);
        ivCreateImg = view.findViewById(R.id.ivCreateImg); // Gán tham chiếu đến ImageView

        ivCreateImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Tạo Intent để mở thư viện ảnh của thiết bị
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, REQUEST_CODE_SELECT_IMAGE);
            }
        });


        builder.setPositiveButton("Thêm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Lấy thông tin từ các EditText
                String tenGiay = edtTenGiay.getText().toString();
                if (tenGiay.isEmpty() || edtGiaBan.getText().toString().isEmpty() ||
                        edtSoLuongKho.getText().toString().isEmpty() || edtSize.getText().toString().isEmpty()) {
                    Toast.makeText(getContext(), "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                    return;
                }

                int giaBan, soLuongKho, size;
                try {
                    giaBan = Integer.parseInt(edtGiaBan.getText().toString());
                    soLuongKho = Integer.parseInt(edtSoLuongKho.getText().toString());
                    size = Integer.parseInt(edtSize.getText().toString());
                } catch (NumberFormatException e) {
                    Toast.makeText(getContext(), "Vui lòng nhập số hợp lệ", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Kiểm tra xem người dùng đã chọn ảnh hay chưa
                if (selectedImageUri != null) {
                    String imagePath = selectedImageUri.toString(); // Convert URI thành String

                    // Tạo một đối tượng mới từ thông tin đã nhập và thêm vào cơ sở dữ liệu
                    Giay newGiay = new Giay(tenGiay, imagePath, giaBan, size, soLuongKho);
                    newGiay.setMagiay(currentMaGiay); // Gán mã giày
                    newGiay.setSize(size);

                    giayDao.themGiay(newGiay);

                    // Tăng giá trị của mã giày lên cho lần thêm tiếp theo
                    currentMaGiay++;

                    // Thêm giày mới vào cơ sở dữ liệu và cập nhật RecyclerView
                    loadData();

                    Toast.makeText(getContext(), "Đã thêm giày mới", Toast.LENGTH_SHORT).show();
                } else {
                    // Hiển thị thông báo lỗi nếu người dùng không chọn ảnh
                    Toast.makeText(getContext(), "Vui lòng chọn ảnh trước khi thêm", Toast.LENGTH_SHORT).show();
                }
            }
        });

        builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.setCancelable(false);
        alertDialog.show();
    }
    @Override
    public void onResume() {
        super.onResume();
        loadData(); // Load lại dữ liệu khi Fragment được hiển thị cho người dùng
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_SELECT_IMAGE && resultCode == Activity.RESULT_OK && data != null) {
            selectedImageUri = data.getData();
            if (selectedImageUri != null) {
                // Load ảnh vào ImageView sử dụng Glide
                Glide.with(this)
                        .load(selectedImageUri)
                        .into(ivCreateImg);
            }
        }
    }
}

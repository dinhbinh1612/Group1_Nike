package binhpdph44989.group1.group1.manHinh;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import binhpdph44989.group1.group1.R;
import binhpdph44989.group1.group1.TrangChuActivity;
import binhpdph44989.group1.group1.database.DbHelper;

public class DoiMatKhau extends Fragment {
    EditText edMKCu, edMKMoi, edNhapLaiMKMoi;
    private DbHelper dbHelper;
    Button btnLuu;
    String taiKhoan;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_doimk, container, false);
        taiKhoan = getActivity().getIntent().getStringExtra("TAI_KHOAN");
        // Ánh xạ các thành phần UI
        edMKCu = view.findViewById(R.id.ed_mkcu_nd);
        edMKMoi = view.findViewById(R.id.ed_mkmoi_nd);
        edNhapLaiMKMoi = view.findViewById(R.id.ed_re_mkmoi_nd);
        btnLuu = view.findViewById(R.id.btn_luu_nd);

        dbHelper = new DbHelper(getContext());

        // Xử lý sự kiện khi người dùng nhấn nút "Lưu"
        btnLuu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mkCu = edMKCu.getText().toString().trim();
                String mkMoi = edMKMoi.getText().toString().trim();
                String nhapLaiMKMoi = edNhapLaiMKMoi.getText().toString().trim();

                // Kiểm tra mật khẩu cũ có đúng không
                if (TextUtils.isEmpty(mkCu) || TextUtils.isEmpty(mkMoi) || TextUtils.isEmpty(nhapLaiMKMoi)) {
                    Toast.makeText(getContext(), "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!mkMoi.equals(nhapLaiMKMoi)) {
                    Toast.makeText(getContext(), "Mật khẩu mới không khớp", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (dbHelper.kiemTraDangNhap(taiKhoan, mkCu)) {
                    // Cập nhật mật khẩu mới vào cơ sở dữ liệu
                    dbHelper.capNhatMatKhau(taiKhoan, mkMoi);
                    Toast.makeText(getContext(), "Đổi mật khẩu thành công", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(), "Mật khẩu cũ không đúng", Toast.LENGTH_SHORT).show();


                }
            }
        });
        return view;
    }
}

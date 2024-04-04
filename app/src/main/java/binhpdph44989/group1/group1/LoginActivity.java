package binhpdph44989.group1.group1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import binhpdph44989.group1.group1.database.DbHelper;
import binhpdph44989.group1.group1.manHinh.ManDangKy;

public class LoginActivity extends AppCompatActivity {
    private EditText edTaiKhoan, edMatKhau;
    private Button btnDangNhap;
    private DbHelper dbHelper;

    private TextView tvDangKy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        dbHelper = new DbHelper(this);
        edTaiKhoan = findViewById(R.id.edEmail);
        edMatKhau = findViewById(R.id.edMatKhau);
        btnDangNhap = findViewById(R.id.btnDangNhap);
        tvDangKy = findViewById(R.id.tvDangKi);
        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String taiKhoan = edTaiKhoan.getText().toString().trim();
                String matKhau = edMatKhau.getText().toString().trim();

                checkLoginAndNavigate(taiKhoan, matKhau);
                if (taiKhoan.isEmpty() || matKhau.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                    if (taiKhoan.isEmpty()) {
                        edTaiKhoan.setError("!");
                    }
                    if (matKhau.isEmpty()) {
                        edMatKhau.setError("!");
                    }
                }
            }
        });

        tvDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, ManDangKy.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private boolean checkLoginAndNavigate(String taiKhoan, String matKhau) {
        boolean result = dbHelper.kiemTraDangNhap(taiKhoan, matKhau); // Gọi phương thức kiểm tra đăng nhập từ DbHelper

        if (result) {
            String loaiTaiKhoan = dbHelper.getLoaiTaiKhoan(taiKhoan); // Lấy loại tài khoản từ DbHelper

            if (loaiTaiKhoan != null) {
                if (loaiTaiKhoan.equals("Admin")) {
                    // Chuyển sang TrangChuActivity cho quản trị viên
                    Intent intentAdmin = new Intent(LoginActivity.this, TrangChuActivity.class);
                    startActivity(intentAdmin);
                } else if (loaiTaiKhoan.equals("user")) {
                    // Chuyển sang MainActivityUser cho người dùng
                    Intent intentUser = new Intent(LoginActivity.this, MainActivityUser.class);
                    startActivity(intentUser);
                } else {
                    // Xử lý giá trị loaiTaiKhoan không mong muốn (để ghi log hoặc thông báo lỗi)
                    Toast.makeText(LoginActivity.this, "Loại tài khoản không hợp lệ", Toast.LENGTH_SHORT).show();
                }
                return true;
            } else {
                // Xử lý khi loaiTaiKhoan là null (để ghi log hoặc thông báo lỗi)
                Toast.makeText(LoginActivity.this, "Không thể xác định loại tài khoản", Toast.LENGTH_SHORT).show();
            }
        }else {
            Toast.makeText(this, "Kiểm tra lại tài khoản mật khẩu ", Toast.LENGTH_SHORT).show();
        }
        return false;
    }
}
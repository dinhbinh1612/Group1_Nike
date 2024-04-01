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
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import binhpdph44989.group1.group1.database.DbHelper;
import binhpdph44989.group1.group1.manHinh.ManDangKy;

public class MainActivity extends AppCompatActivity {
    private EditText edTaiKhoan, edMatKhau;
    private Button btnDangNhap;
    private DbHelper dbHelper;

    private TextView tvDangKy;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
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

//                 Kiểm tra đăng nhập
                if (dbHelper.kiemTraDangNhap(taiKhoan, matKhau)) {
                    Intent intent = new Intent(MainActivity.this, TrangChuActivity.class);
                    intent.putExtra("TAI_KHOAN", taiKhoan);
                    Toast.makeText(MainActivity.this, "Đăng nhập thành công!", Toast.LENGTH_SHORT).show();
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(MainActivity.this, "Tên người dùng hoặc mật khẩu không đúng", Toast.LENGTH_SHORT).show();
                }
            }
        });

        tvDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ManDangKy.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
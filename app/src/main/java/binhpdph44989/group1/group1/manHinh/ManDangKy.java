package binhpdph44989.group1.group1.manHinh;

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

import binhpdph44989.group1.group1.MainActivity;
import binhpdph44989.group1.group1.R;
import binhpdph44989.group1.group1.database.DbHelper;

public class ManDangKy extends AppCompatActivity {
    TextView tvDangNhap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_man_dang_ky);
        tvDangNhap = findViewById(R.id.tvDangNhap);
        tvDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ManDangKy.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        Button btnDangKy = findViewById(R.id.btnDangKi);
        btnDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText edHoTen = findViewById(R.id.edDKHoTen);
                EditText edSdt = findViewById(R.id.edDKSdt);
                EditText edUser = findViewById(R.id.edDKUser);
                EditText edDiaChi = findViewById(R.id.edDkAddress);
                EditText edMatKhau = findViewById(R.id.edDKMatKhau);
                EditText edNhapLaiMK = findViewById(R.id.edDKNhapLaiMK);

                String hoTen = edHoTen.getText().toString();
                String sdt = edSdt.getText().toString();
                String user = edUser.getText().toString();
                String diaChi = edDiaChi.getText().toString();
                String matKhau = edMatKhau.getText().toString();
                String nhapLaiMK = edNhapLaiMK.getText().toString();
                if (!matKhau.equals(nhapLaiMK)) {
                    Toast.makeText(ManDangKy.this, "Mật khẩu nhập lại không đúng", Toast.LENGTH_SHORT).show();
                    return;
                }
                DbHelper dbHelper = new DbHelper(ManDangKy.this);
                dbHelper.addUser(hoTen, sdt, user, diaChi, matKhau);

                Intent intent = new Intent(ManDangKy.this, MainActivity.class);
                Toast.makeText(ManDangKy.this, "Đăng ký thành công!", Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }
        });
    }
}
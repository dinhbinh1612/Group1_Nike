package binhpdph44989.group1.group1.manHinh;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import binhpdph44989.group1.group1.LoginActivity;
import binhpdph44989.group1.group1.R;
import binhpdph44989.group1.group1.database.DbHelper;

public class ManDangKy extends AppCompatActivity {
    EditText edHoTen, edSdt, edUser, edDiaChi, edMatKhau, edNhapLaiMK;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_man_dang_ky);

        edHoTen = findViewById(R.id.edtHoTen);
        edSdt = findViewById(R.id.edtSDT);
        edUser = findViewById(R.id.edtTK);
        edDiaChi = findViewById(R.id.edtAddress);
        edMatKhau = findViewById(R.id.edtPass);
        edNhapLaiMK = findViewById(R.id.edtRePass);

        Button btnDangKy = findViewById(R.id.btnDangKi);
        TextView tvDangNhap = findViewById(R.id.tvDangNhap);

        tvDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMainActivity();
            }
        });

        btnDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });
    }

    private void openMainActivity() {
        Intent intent = new Intent(ManDangKy.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    private void registerUser() {
        String hoTen = edHoTen.getText().toString().trim();
        String sdt = edSdt.getText().toString().trim();
        String user = edUser.getText().toString().trim();
        String diaChi = edDiaChi.getText().toString().trim();
        String matKhau = edMatKhau.getText().toString().trim();
        String nhapLaiMK = edNhapLaiMK.getText().toString().trim();

        if (hoTen.isEmpty() || sdt.isEmpty() || user.isEmpty() || diaChi.isEmpty() || matKhau.isEmpty() || nhapLaiMK.isEmpty()) {
            Toast.makeText(ManDangKy.this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!matKhau.equals(nhapLaiMK)) {
            Toast.makeText(ManDangKy.this, "Mật khẩu nhập lại không đúng", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!isValidPhoneNumber(sdt)) {
            Toast.makeText(ManDangKy.this, "Số điện thoại không hợp lệ", Toast.LENGTH_SHORT).show();
            return;
        }

        DbHelper dbHelper = new DbHelper(ManDangKy.this);

        if (dbHelper.TrungTK(user)) {
            Toast.makeText(ManDangKy.this, "Tài khoản đã tồn tại", Toast.LENGTH_SHORT).show();
            return;
        }

        dbHelper.addUser(hoTen, sdt, user, diaChi, matKhau);
        Toast.makeText(ManDangKy.this, "Đăng ký thành công!", Toast.LENGTH_SHORT).show();
        openMainActivity();
    }

    private boolean isValidPhoneNumber(String phoneNumber) {
        return phoneNumber.matches("^0\\d{9}$");
    }
}

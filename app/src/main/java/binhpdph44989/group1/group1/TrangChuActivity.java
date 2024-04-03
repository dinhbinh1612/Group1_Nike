package binhpdph44989.group1.group1;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.bumptech.glide.Glide;
import com.google.android.material.navigation.NavigationView;

import binhpdph44989.group1.group1.fragmentAdmin.QLDonHangFragment;
import binhpdph44989.group1.group1.fragmentAdmin.QLGiayFragment;
import binhpdph44989.group1.group1.fragmentAdmin.QLLoaIGiayFragment;
import binhpdph44989.group1.group1.fragmentAdmin.QLThanhVienFragment;
import binhpdph44989.group1.group1.fragmentAdmin.QLThongKeFragment;
import binhpdph44989.group1.group1.fragmentAdmin.QLTopFragment;
import binhpdph44989.group1.group1.manHinh.DoiMatKhau;


public class TrangChuActivity extends AppCompatActivity {
    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trang_chu);

        ImageView ivLogo = findViewById(R.id.ivLogo);
        Glide.with(this).load(R.drawable.g1).into(ivLogo);


        Toolbar toolbar = findViewById(R.id.toolBar);
        FrameLayout frameLayout = findViewById(R.id.frameLayout);
        NavigationView navigationView = findViewById(R.id.navigationView);
        drawerLayout = findViewById(R.id.drawlayoutTrangChu);
        View hearderLayout = navigationView.getHeaderView(0);
        TextView txtTen = hearderLayout.findViewById(R.id.txtTen);

        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.menu);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                Fragment fragment = null;
                if (menuItem.getItemId() == R.id.mLoaiGiay) {
                    fragment = new QLLoaIGiayFragment();
                }else if (menuItem.getItemId() == R.id.mGiay){
                    fragment = new QLGiayFragment();
                }else if (menuItem.getItemId() == R.id.mThanhVien){
                    fragment = new QLThanhVienFragment();
                }else if (menuItem.getItemId() == R.id.mDonHang){
                    fragment = new QLDonHangFragment();
                }else if (menuItem.getItemId() == R.id.mTop){
                    fragment = new QLTopFragment();
                }else if (menuItem.getItemId() == R.id.mDoanhThu){
                    fragment = new QLThongKeFragment();
                }else if (menuItem.getItemId() == R.id.mDoiMatKhau){
                    fragment = new DoiMatKhau();
                }else if (menuItem.getItemId() == R.id.mDangXuat){
                    Intent intent = new Intent(TrangChuActivity.this, LoginActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                }
                if (fragment != null){
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.frameLayout,fragment).commit();
                    toolbar.setTitle(menuItem.getTitle());
                }
                drawerLayout.closeDrawer(GravityCompat.START);
                return false;
            }
        });
        SharedPreferences sharedPreferences = getSharedPreferences("THONGTIN",MODE_PRIVATE);
        String hoten = sharedPreferences.getString("hoten","");
        txtTen.setText("Xin chào, "+ hoten);
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            drawerLayout.openDrawer(GravityCompat.START);
        }
        return super.onOptionsItemSelected(item);
    }

}
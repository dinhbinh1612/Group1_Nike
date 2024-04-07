package binhpdph44989.group1.group1;

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

import binhpdph44989.group1.group1.fragmentAdmin.QLGiayFragment;
import binhpdph44989.group1.group1.manHinh.DoiMatKhau;



public class TrangChuActivity extends AppCompatActivity {
    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trang_chu);

//        ImageView ivLogo = findViewById(R.id.ivLogo);
//        Glide.with(this).load(R.drawable.g1).into(ivLogo);


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

                }else if (menuItem.getItemId() == R.id.mGiay){
                    fragment = new QLGiayFragment();
                }else if (menuItem.getItemId() == R.id.mThanhVien){

                }else if (menuItem.getItemId() == R.id.mDonHang){

                }else if (menuItem.getItemId() == R.id.mTop){

                }else if (menuItem.getItemId() == R.id.mDoanhThu){
                }else if (menuItem.getItemId() == R.id.mDoiMatKhau){
                    fragment = new DoiMatKhau();
                }else if (menuItem.getItemId() == R.id.mDangXuat){

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
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            drawerLayout.openDrawer(GravityCompat.START);
        }
        return super.onOptionsItemSelected(item);
    }

}
package binhpdph44989.group1.group1.manHinh;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import binhpdph44989.group1.group1.LoginActivity;
import binhpdph44989.group1.group1.R;

public class ManChao extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_man_chao);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(ManChao.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        },3000);
    }
}
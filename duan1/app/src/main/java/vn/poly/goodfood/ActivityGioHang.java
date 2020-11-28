package vn.poly.goodfood;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import static vn.poly.goodfood.R.drawable.boder;

public class ActivityGioHang extends AppCompatActivity {
    ImageView imgBack;
    Button btnGioHang;

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gio_hang);
        imgBack = findViewById(R.id.img_BackGioHang);
        btnGioHang = findViewById(R.id.btn_gioHang);
        BackGH();
    }

    public void BackGH(){
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
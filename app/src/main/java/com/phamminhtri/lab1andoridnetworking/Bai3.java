package com.phamminhtri.lab1andoridnetworking;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Bai3 extends AppCompatActivity implements Listener, View.OnClickListener {
    private ImageView imgload;
    private TextView tvmessage;
    private Button btnload;
    private Button btnbai4;

    public static final String IMAGE_URL = "https://photo-2-baomoi.zadn.vn/w300_r3x2/2019_07_10_180_31403014/c139b81fcd5f24017d4e.jpg";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bai3);
        imgload = (ImageView) findViewById(R.id.imgload);
        tvmessage = (TextView) findViewById(R.id.tvmessage);
        btnload = (Button) findViewById(R.id.btnload);
        btnbai4 = (Button) findViewById(R.id.btnbai4);

        btnbai4.setOnClickListener(this);
        btnload.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnload:
                new LoadImageTask(this, this).execute(IMAGE_URL);
                Log.e("TAG", "onClick: " + IMAGE_URL);
                break;
            case R.id.btnbai4:
                startActivity(new Intent(Bai3.this, Bai4.class));
                break;
        }

    }

    @Override
    public void onImageLoaded(Bitmap bitmap) {
        imgload.setImageBitmap(bitmap);
        tvmessage.setText("Image Dowloaded ");
    }

    @Override
    public void onError() {
        tvmessage.setText("Image Dowload error");

    }
}

package com.phamminhtri.lab1andoridnetworking;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView imgAndroid;
    private TextView tvMessage;
    private Button btnLoadimage;
    private Button btnBai2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imgAndroid = (ImageView) findViewById(R.id.img_android);
        tvMessage = (TextView) findViewById(R.id.tv_message);
        btnLoadimage = (Button) findViewById(R.id.btn_loadimage);
        btnBai2 = (Button) findViewById(R.id.btn_bai2);

        btnLoadimage.setOnClickListener(this);
        btnBai2.setOnClickListener(this);
    }

    private Bitmap loadImageFormNetWork(String link) {
        Bitmap bmp = null;
        URL url;
        try {
            url = new URL(link);
            bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bmp;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_loadimage:

                final Thread mythread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        final Bitmap bitmap = loadImageFormNetWork("https://photo-2-baomoi.zadn.vn/w300_r3x2/2019_07_09_329_31398838/3fa20df478b491eac8a5.jpg");
                        imgAndroid.post(new Runnable() {
                            @Override
                            public void run() {
                                tvMessage.setText("image dowloaded");
                                imgAndroid.setImageBitmap(bitmap);
                            }
                        });
                    }
                });
                mythread.start();
                break;
            case R.id.btn_bai2:
                startActivity(new Intent(MainActivity.this, Splashscreen.class));
                break;
        }


    }

}

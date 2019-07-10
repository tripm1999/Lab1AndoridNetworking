package com.phamminhtri.lab1andoridnetworking;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Bai2 extends AppCompatActivity implements View.OnClickListener {
    private ImageView imgLoad;
    private TextView tvMessage;
    private Button btnLoad;
    private Button btnBai3;

    private Bitmap bitmap = null;
    private ProgressDialog progressDialog;
    private String  url = "https://photo-atm-baomoi.zadn.vn/w300_r3x2/adtima-media.zadn.vn/2019/07/50a8d224-100f-4fee-adcb-86bbf6f8dbde.jpg";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bai2);
        imgLoad = (ImageView) findViewById(R.id.img_load);
        tvMessage = (TextView) findViewById(R.id.tv_message);
        btnLoad = (Button) findViewById(R.id.btn_load);
        btnBai3 = (Button) findViewById(R.id.btn_bai3);

        btnLoad.setOnClickListener(this);
        btnBai3.setOnClickListener(this);

    }

    private  Handler messageHandler = new Handler() {

        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Bundle bundle = msg.getData();
            String message = bundle.getString("message");
            tvMessage.setText(message);
            imgLoad.setImageBitmap(bitmap);
            progressDialog.dismiss();

        }
    };

    private Bitmap downloadBitmap(String link) {

        try {
            URL url = new URL(link);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.connect();
            InputStream inputStream = connection.getInputStream();
            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
            return bitmap;

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_load:
                progressDialog = ProgressDialog.show(Bai2.this, "", "Downloading...");
                Runnable aRunnabe = new Runnable() {
                    @Override
                    public void run() {
                        bitmap = downloadBitmap(url);
                        Message msg = messageHandler.obtainMessage();
                        Bundle bundle = new Bundle();
                        String threadMessage = "Image downloaded";
                        bundle.putString("message", threadMessage);
                        msg.setData(bundle);
                        messageHandler.sendMessage(msg);
                    }
                };
                Thread aThread = new Thread(aRunnabe);
                aThread.start();
                break;

            case R.id.btn_bai3:
                startActivity(new Intent(Bai2.this, Bai3.class));
                break;
        }

    }
}

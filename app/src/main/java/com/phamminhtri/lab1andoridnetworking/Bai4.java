package com.phamminhtri.lab1andoridnetworking;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Bai4 extends AppCompatActivity implements View.OnClickListener {
    private EditText edtTime;
    private Button btnRun;
    private TextView tvResult;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bai4);

        edtTime = (EditText) findViewById(R.id.edt_time);
        btnRun = (Button) findViewById(R.id.btn_run);
        tvResult = (TextView) findViewById(R.id.tvResult);

        btnRun.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_run:
                AsyncTaskRunner asyncTaskRunner = new AsyncTaskRunner(this, tvResult, edtTime);
                String sleepTime = edtTime.getText().toString();
                asyncTaskRunner.execute(sleepTime);
                break;
        }

    }
}

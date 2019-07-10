package com.phamminhtri.lab1andoridnetworking;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.EditText;
import android.widget.TextView;

public class AsyncTaskRunner extends AsyncTask<String, String, String> {
    private String resp;
    ProgressDialog dialog;
    TextView tvResult;
    EditText time;
    Context context;

    public AsyncTaskRunner(  Context context,TextView tvResult,EditText time) {
        this.tvResult = tvResult;
        this.time = time;
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        dialog = ProgressDialog.show(context, "ProgressDialog", "Wait for" + time.getText().toString() + " seconds");
    }

    @Override
    protected String doInBackground(String... params) {
        publishProgress("Sleeping....");
        try {
            int time = Integer.parseInt(params[0]) * 1000;
            Thread.sleep(time);
            resp = "Slept for " + params[0] + " seconds";

        } catch (InterruptedException e) {
            e.printStackTrace();
            resp = e.getMessage();
        }


        return resp;
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        if (dialog.isShowing()) {
            dialog.dismiss();
        }
        tvResult.setText(result);
    }
}

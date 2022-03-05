package com.gavriden.sulzerservice;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class CheckDocActivity extends Activity {

    private View decorView;

    Button btnCancel;
    Button btnRegister;

    Spinner spinner_1, spinner_2, spinner_3, spinner_4, spinner_5, spinner_6, spinner_7, spinner_8, spinner_9, spinner_10, spinner_11;

    TextView userText;
    TextView reportText;
    TextView userBuffer;
    TextView pumpBuffer;

    DBHelper databaseHelper;

    Cursor resultUserId;
    Cursor resultReportId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_doc);

        btnCancel = findViewById(R.id.btnLogin);
        btnRegister = findViewById(R.id.btnRegister);
        userText = findViewById(R.id.userText);
        reportText = findViewById(R.id.reportText);
        userBuffer = findViewById(R.id.userBuffer);
        pumpBuffer = findViewById(R.id.pumpBuffer);

        spinner_1 = findViewById(R.id.spinner_1);
        spinner_2 = findViewById(R.id.spinner_2);
        spinner_3 = findViewById(R.id.spinner_3);
        spinner_4 = findViewById(R.id.spinner_4);
        spinner_5 = findViewById(R.id.spinner_5);
        spinner_6 = findViewById(R.id.spinner_6);
        spinner_7 = findViewById(R.id.spinner_7);
        spinner_8 = findViewById(R.id.spinner_8);
        spinner_9 = findViewById(R.id.spinner_9);
        spinner_10 = findViewById(R.id.spinner_10);
        spinner_11 = findViewById(R.id.spinner_11);

        Bundle bundle = getIntent().getExtras();
        String user = bundle.getString("email");
        String report = bundle.getString("report");
        userText.setText(user);
        reportText.setText(report);

        databaseHelper = new DBHelper(this);

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                switch (v.getId()){

                    case R.id.btnRegister:

                        String emailValue = userText.getText().toString();
                        resultUserId = databaseHelper.getUserId(emailValue);
                        while (resultUserId.moveToNext()) {
                            String report = resultUserId.getString(resultUserId.getColumnIndex(DBHelper.USERID));
                            userBuffer.setText(report);
                        }

                        String reportNum = reportText.getText().toString();
                        resultReportId = databaseHelper.getPumpReport(reportNum);
                        while (resultReportId.moveToNext()) {
                            String report = resultReportId.getString(resultReportId.getColumnIndex(DBHelper.RPUMPID));
                            pumpBuffer.setText(report);
                        }

                        String posnumValue = spinner_1.getSelectedItem().toString();
                        String plateValue = spinner_2.getSelectedItem().toString();
                        String avdocValue = spinner_3.getSelectedItem().toString();
                        String tdsValue = spinner_4.getSelectedItem().toString();
                        String curveValue = spinner_5.getSelectedItem().toString();
                        String secdrawValue = spinner_6.getSelectedItem().toString();
                        String spareValue = spinner_7.getSelectedItem().toString();
                        String dimdrawValue = spinner_8.getSelectedItem().toString();
                        String manualValue = spinner_9.getSelectedItem().toString();
                        String safetyValue = spinner_10.getSelectedItem().toString();
                        String certificateValue = spinner_11.getSelectedItem().toString();

                        String reportIdValue = reportText.getText().toString();
                        String reportValue = pumpBuffer.getText().toString();
                        String userValue = userBuffer.getText().toString();

                        Boolean insert = databaseHelper.insertDocReport(reportIdValue, posnumValue, plateValue, avdocValue, tdsValue, curveValue,
                                secdrawValue, spareValue, dimdrawValue, manualValue, safetyValue, certificateValue);

                        if (insert == true){
                            Toast.makeText(CheckDocActivity.this, R.string.data_added, Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(CheckDocActivity.this, CheckInstallActivity.class);
                            intent.putExtra("userId", userValue);
                            intent.putExtra("pumpId", reportValue);
                            startActivity(intent);
                            overridePendingTransition(R.anim.right_in, R.anim.alpha);
                        }else{
                            Toast.makeText(CheckDocActivity.this, R.string.registration_failed, Toast.LENGTH_SHORT).show();
                        }

                        finish();

                        break;

                }
            }
        };

        btnRegister.setOnClickListener(onClickListener);


        decorView = getWindow().getDecorView();
        decorView.setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener() {
            @Override
            public void onSystemUiVisibilityChange(int visibility) {
                if(visibility == 0)
                    decorView.setSystemUiVisibility(hideSystemBars());
            }
        });

    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);

        if(hasFocus){
            decorView.setSystemUiVisibility(hideSystemBars());
        }

    }

    private int hideSystemBars(){
        return  View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
    }
}

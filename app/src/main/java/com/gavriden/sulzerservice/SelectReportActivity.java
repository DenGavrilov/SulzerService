package com.gavriden.sulzerservice;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SelectReportActivity extends Activity {
    private View decorView;
    Button btnSavedReports;
    Button btnSentReports;
    Button btnNewReport;
    TextView emailText;
    TextView savedRep;
    TextView sentRep;
    int result;
    int result2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_report);

        btnSavedReports = findViewById(R.id.btnSavedReports);
        btnSentReports = findViewById(R.id.btnSentReports);
        btnNewReport = findViewById(R.id.btnNewReport);
        emailText = findViewById(R.id.emailText);
        savedRep = findViewById(R.id.savedRep);
        sentRep = findViewById(R.id.sentRep);

        Bundle bundle = getIntent().getExtras();
        final String email = bundle.getString("email");
        emailText.setText(email);

        final DBHelper helper = new DBHelper(this);
        result = helper.getCountFault();
        savedRep.setText(Integer.toString(result));

        result2 = helper.getCountOk();
        sentRep.setText(Integer.toString(result2));


        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                switch (v.getId()){

                    case R.id.btnSavedReports:
                        Intent b = new Intent(SelectReportActivity.this, UnsentReportActivity.class);
                        b.putExtra("email", email);
                        startActivity(b);
                        overridePendingTransition(R.anim.right_in, R.anim.alpha);
                        finish();
                        break;

                    case R.id.btnSentReports:
                        Intent ps = new Intent(SelectReportActivity.this, VideoInstallationImpeller.class);
                        startActivity(ps);
                        overridePendingTransition(R.anim.right_in, R.anim.alpha);
                        finish();
                        break;

                    case R.id.btnNewReport:
                        String emailValue = emailText.getText().toString();
                        Intent nr = new Intent(SelectReportActivity.this, PumpRegistrationActivity.class);
                        nr.putExtra("email", emailValue);
                        startActivity(nr);
                        overridePendingTransition(R.anim.right_in, R.anim.alpha);
                        finish();
                        break;
                }

            }
        };

        btnSavedReports.setOnClickListener(onClickListener);
        //btnSentReports.setOnClickListener(onClickListener);
        btnNewReport.setOnClickListener(onClickListener);

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

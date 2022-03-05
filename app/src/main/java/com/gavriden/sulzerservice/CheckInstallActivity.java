package com.gavriden.sulzerservice;

import android.app.Activity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.transition.Fade;
import android.transition.Transition;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.blogspot.atifsoftwares.circularimageview.CircularImageView;

public class CheckInstallActivity extends Activity {

    Button btnDocumentation;
    Button btnPipelines;
    Button btnFundation;
    Button btnFlanges;
    Button btnShaftSeal;
    Button btnLubrication;
    Button btnCoupling;
    Button btnMotor;
    Button btnCondition;
    Button btnSave;
    TextView userText;
    TextView reportText;
    TextView textBuferPump;
    TextView textBuferUser;
    DBHelper databaseHelper;
    Cursor resultReportId;
    Cursor resultUserId;

    private CircularImageView docSign, pipeSign, fundSign, flangeSign, sealSign, lubSign, coupSign, motorSign, conditionSign;
    private View decorView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_install);

        btnDocumentation = (Button)findViewById(R.id.btnDocumentation);
        btnPipelines = (Button)findViewById(R.id.btnPipelines);
        btnFundation = (Button)findViewById(R.id.btnFundation);
        btnFlanges = (Button)findViewById(R.id.btnFlanges);
        btnShaftSeal = (Button)findViewById(R.id.btnShaftSeal);
        btnLubrication = (Button)findViewById(R.id.btnLubrication);
        btnCoupling = (Button)findViewById(R.id.btnCoupling);
        btnMotor = (Button)findViewById(R.id.btnMotor);
        btnCondition = findViewById(R.id.btnCondition);
        btnSave = findViewById(R.id.btnSave);
        docSign = findViewById(R.id.docSign);
        pipeSign = findViewById(R.id.pipeSign);
        fundSign = findViewById(R.id.fundSign);
        flangeSign = findViewById(R.id.flangeSign);
        sealSign = findViewById(R.id.sealSign);
        lubSign = findViewById(R.id.lubSign);
        coupSign = findViewById(R.id.coupSign);
        motorSign = findViewById(R.id.motorSign);
        conditionSign = findViewById(R.id.conditionSign);
        userText = findViewById(R.id.userText);
        reportText = findViewById(R.id.reportText);
        textBuferPump = findViewById(R.id.textBuferPump);
        textBuferUser = findViewById(R.id.textBuferUser);
        setDrawable();
        changeDrawable();


        Bundle bundle = getIntent().getExtras();
        String userIdB = bundle.getString("userId");
        String pumpIdB = bundle.getString("pumpId");
        textBuferUser.setText(userIdB);
        textBuferPump.setText(pumpIdB);
        String userId = textBuferUser.getText().toString();
        String pumpId = textBuferPump.getText().toString();

        final DBHelper helper = new DBHelper(this);
        resultReportId = helper.getReportId(pumpId);
        resultUserId = helper.getEmail(userId);

        while (resultReportId.moveToNext()) {
        String report = resultReportId.getString(resultReportId.getColumnIndex(DBHelper.REPORTID));
        reportText.setText(report);
         }

        while (resultUserId.moveToNext()) {
        String report = resultUserId.getString(resultUserId.getColumnIndex(DBHelper.USEREMAIL));
        userText.setText(report);
        }

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String emailValue = userText.getText().toString();
                String reportValue = reportText.getText().toString();

                switch (v.getId()){
                    case R.id.btnDocumentation:
                        Intent c = new Intent(CheckInstallActivity.this, CheckDocActivity.class);
                        c.putExtra("email", emailValue);
                        c.putExtra("report", reportValue);
                        startActivity(c);
                        overridePendingTransition(R.anim.right_in, R.anim.alpha);

                        finish();

                        break;

                    case R.id.btnCoupling:
                        Intent couplingIntent = new Intent(CheckInstallActivity.this, CheckCouplingActivity.class);
                        couplingIntent.putExtra("email", emailValue);
                        couplingIntent.putExtra("report", reportValue);
                        startActivity(couplingIntent);
                        overridePendingTransition(R.anim.right_in, R.anim.alpha);

                        finish();
                        break;

                    case R.id.btnPipelines:
                        Intent pipeIntent = new Intent(CheckInstallActivity.this, CheckPipeActivity.class);
                        pipeIntent.putExtra("email", emailValue);
                        pipeIntent.putExtra("report", reportValue);
                        startActivity(pipeIntent);
                        overridePendingTransition(R.anim.right_in, R.anim.alpha);

                        finish();
                        break;

                    case R.id.btnFundation:
                        Intent foundIntent = new Intent(CheckInstallActivity.this, CheckFoundationActivity.class);
                        foundIntent.putExtra("email", emailValue);
                        foundIntent.putExtra("report", reportValue);
                        startActivity(foundIntent);
                        overridePendingTransition(R.anim.right_in, R.anim.alpha);

                        finish();
                        break;

                    case R.id.btnFlanges:
                        Intent flangeIntent = new Intent(CheckInstallActivity.this, CheckFlangesActivity.class);
                        flangeIntent.putExtra("email", emailValue);
                        flangeIntent.putExtra("report", reportValue);
                        startActivity(flangeIntent);
                        overridePendingTransition(R.anim.right_in, R.anim.alpha);

                        finish();

                        break;

                    case R.id.btnShaftSeal:

                        Intent sealIntent = new Intent(CheckInstallActivity.this, CheckSealActivity.class);
                        sealIntent.putExtra("email", emailValue);
                        sealIntent.putExtra("report", reportValue);
                        startActivity(sealIntent);
                        overridePendingTransition(R.anim.right_in, R.anim.alpha);

                        finish();

                        break;

                    case R.id.btnLubrication:

                        Intent lubIntent = new Intent(CheckInstallActivity.this, CheckLubricationActivity.class);
                        lubIntent.putExtra("email", emailValue);
                        lubIntent.putExtra("report", reportValue);
                        startActivity(lubIntent);
                        overridePendingTransition(R.anim.right_in, R.anim.alpha);

                        finish();

                        break;

                    case R.id.btnMotor:

                        Intent mIntent = new Intent(CheckInstallActivity.this, CheckMotorActivity.class);
                        mIntent.putExtra("email", emailValue);
                        mIntent.putExtra("report", reportValue);
                        startActivity(mIntent);
                        overridePendingTransition(R.anim.right_in, R.anim.alpha);

                        finish();

                        break;

                    case R.id.btnCondition:

                        Intent conIntent = new Intent(CheckInstallActivity.this, CheckConditionsActivity.class);
                        conIntent.putExtra("email", emailValue);
                        conIntent.putExtra("report", reportValue);
                        startActivity(conIntent);
                        overridePendingTransition(R.anim.right_in, R.anim.alpha);

                        finish();

                        break;



                    case R.id.btnSave:

                        helper.insertSyncStatus(reportValue, DBHelper.SYNC_STATUS_FAILED);
                        Intent sendReport = new Intent(CheckInstallActivity.this, SendReportActivity.class);
                        startActivity(sendReport);
                        overridePendingTransition(R.anim.right_in, R.anim.alpha);

                        finish();

                        break;

                }

            }
        };

        btnDocumentation.setOnClickListener(onClickListener);
        btnPipelines.setOnClickListener(onClickListener);
        btnFundation.setOnClickListener(onClickListener);
        btnCoupling.setOnClickListener(onClickListener);
        btnFlanges.setOnClickListener(onClickListener);
        btnShaftSeal.setOnClickListener(onClickListener);
        btnLubrication.setOnClickListener(onClickListener);
        btnMotor.setOnClickListener(onClickListener);
        btnCondition.setOnClickListener(onClickListener);
        btnSave.setOnClickListener(onClickListener);

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
    public void onRestart(){
        super.onRestart();
        changeDrawable();
    }

    @Override
    public void onResume(){
        super.onResume();
        changeDrawable();
    }

    private void setDrawable(){
        //docSign.setImageResource(R.drawable.circle_check_red);
        //pipeSign.setImageResource(R.drawable.circle_check_red);
        //fundSign.setImageResource(R.drawable.circle_check_red);
        //flangeSign.setImageResource(R.drawable.circle_check_red);
        //sealSign.setImageResource(R.drawable.circle_check_red);
        //lubSign.setImageResource(R.drawable.circle_check_red);
        //coupSign.setImageResource(R.drawable.circle_check_red);
        //motorSign.setImageResource(R.drawable.circle_check_red);
        conditionSign.setImageResource(R.drawable.circle_check_red);
    }

    private void changeDrawable(){
        final DBHelper databaseHelper = new DBHelper(this);
        String pumpId = textBuferPump.getText().toString();
        resultReportId = databaseHelper.getReportId(pumpId);
        while (resultReportId.moveToNext()) {
            String report = resultReportId.getString(resultReportId.getColumnIndex(DBHelper.REPORTID));
            reportText.setText(report);
        }
        String reportValue = reportText.getText().toString();

        Boolean checkStatus = databaseHelper.checkcoupling(reportValue);
        if (checkStatus==true){
            coupSign.setImageResource(R.drawable.circle_check_green);
        }else {
            Boolean checkYellow = databaseHelper.checkYellowCoupling(reportValue);
            if(checkYellow==true){
                coupSign.setImageResource(R.drawable.circle_check_yellow);
            }else {
                coupSign.setImageResource(R.drawable.circle_check_red);
            }

        }

        Boolean docStatus = databaseHelper.checkdoc(reportValue);
        if (docStatus==true){
            docSign.setImageResource(R.drawable.circle_check_green);
        }else {
            Boolean checkYellowDoc = databaseHelper.checkYellowDoc(reportValue);
            if(checkYellowDoc==true){
                docSign.setImageResource(R.drawable.circle_check_yellow);
            }else {
                docSign.setImageResource(R.drawable.circle_check_red);
            }

        }

        Boolean pipeStatus = databaseHelper.checkpipe(reportValue);
        if (pipeStatus==true){
            pipeSign.setImageResource(R.drawable.circle_check_green);
        }else {
            Boolean checkYellowPipe = databaseHelper.checkYellowPipe(reportValue);
            if(checkYellowPipe==true){
                pipeSign.setImageResource(R.drawable.circle_check_yellow);
            }else {
                pipeSign.setImageResource(R.drawable.circle_check_red);
            }

        }

        Boolean foundStatus = databaseHelper.checkfoundation(reportValue);
        if (foundStatus==true){
            fundSign.setImageResource(R.drawable.circle_check_green);
        }else {
            Boolean checkYellowFoundation = databaseHelper.checkYellowfoundation(reportValue);
            if (checkYellowFoundation==true){
                fundSign.setImageResource(R.drawable.circle_check_yellow);
            }else {
                fundSign.setImageResource(R.drawable.circle_check_red);
            }
        }

        Boolean flangeStatus = databaseHelper.checkflanges(reportValue);
        if (flangeStatus==true){
            flangeSign.setImageResource(R.drawable.circle_check_green);
        }else {
            Boolean checkYellowFlange = databaseHelper.checkYellowFlanges(reportValue);
            if (checkYellowFlange==true){
                flangeSign.setImageResource(R.drawable.circle_check_yellow);
            }else {
                flangeSign.setImageResource(R.drawable.circle_check_red);
            }
        }

        Boolean sealStatus = databaseHelper.checkseal(reportValue);
        if (sealStatus==true){
            sealSign.setImageResource(R.drawable.circle_check_green);
        }else {
            Boolean checkYellowSeal = databaseHelper.checkYellowSeal(reportValue);
            if (checkYellowSeal==true){
                sealSign.setImageResource(R.drawable.circle_check_yellow);
            }else {
                sealSign.setImageResource(R.drawable.circle_check_red);
            }
        }

        Boolean lubStatus = databaseHelper.checklubricant(reportValue);
        if (lubStatus==true){
            lubSign.setImageResource(R.drawable.circle_check_green);
        }else {
            Boolean checkYellowLub = databaseHelper.checkYellowLubricant(reportValue);
            if (checkYellowLub==true){
                lubSign.setImageResource(R.drawable.circle_check_yellow);
            }else {
                lubSign.setImageResource(R.drawable.circle_check_red);
            }
        }

        Boolean motorStatus = databaseHelper.checkmotor(reportValue);
        if (motorStatus==true){
            motorSign.setImageResource(R.drawable.circle_check_green);
        }else {
            Boolean checkYellowMotor = databaseHelper.checkYellowMotor(reportValue);
            if (checkYellowMotor==true){
                motorSign.setImageResource(R.drawable.circle_check_yellow);
            }else {
                motorSign.setImageResource(R.drawable.circle_check_red);
            }
        }

        Boolean conStatus = databaseHelper.checkcond(reportValue);
        if (conStatus==true){
            conditionSign.setImageResource(R.drawable.circle_check_green);
        }else {
            Boolean checkYellowCond = databaseHelper.checkYellowCond(reportValue);
            if (checkYellowCond==true){
                conditionSign.setImageResource(R.drawable.circle_check_yellow);
            }else {
                conditionSign.setImageResource(R.drawable.circle_check_red);
            }
        }

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

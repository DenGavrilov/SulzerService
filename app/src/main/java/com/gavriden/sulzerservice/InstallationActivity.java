package com.gavriden.sulzerservice;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class InstallationActivity extends Activity {

    private View decorView;
    Button btnSafety;
    Button btnPipework;
    Button btnSealingUnit;
    Button btnCouplingUnit;
    Button btnBaseplate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_installation);
        btnSafety = findViewById(R.id.btnSafety);
        btnPipework = findViewById(R.id.btnPipework);
        btnSealingUnit = findViewById(R.id.btnSealingUnit);
        btnCouplingUnit = findViewById(R.id.btnCouplingUnit);
        btnBaseplate = findViewById(R.id.btnBaseplate);

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){

                    case R.id.btnBaseplate:

                        Intent base = new Intent(InstallationActivity.this, VideoInstallBaseframe.class);
                        startActivity(base);
                        overridePendingTransition(R.anim.left_out, R.anim.alpha);
                        finish();

                        break;


                }
            }
        };

        btnBaseplate.setOnClickListener(onClickListener);


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

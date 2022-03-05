package com.gavriden.sulzerservice;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SelectBUActivity extends Activity {

    private View decorView;
    Button btnDisassemblyWet;
    Button btnReassemblyWet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_wet);

        btnDisassemblyWet = findViewById(R.id.btnDisassemblyWet);
        btnReassemblyWet = findViewById(R.id.btnReassemblyWet);

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                switch (v.getId()){

                    case R.id.btnDisassemblyWet:
                        Intent b = new Intent(SelectBUActivity.this, VideoActivity.class);
                        startActivity(b);
                        overridePendingTransition(R.anim.right_in, R.anim.alpha);
                        finish();
                        break;

                    case R.id.btnReassemblyWet:
                        Intent ps = new Intent(SelectBUActivity.this, VideoInstallationImpeller.class);
                        startActivity(ps);
                        overridePendingTransition(R.anim.right_in, R.anim.alpha);
                        finish();
                        break;



                }

            }
        };

        btnDisassemblyWet.setOnClickListener(onClickListener);
        btnReassemblyWet.setOnClickListener(onClickListener);

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

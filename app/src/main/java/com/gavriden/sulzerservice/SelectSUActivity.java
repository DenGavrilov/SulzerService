package com.gavriden.sulzerservice;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SelectSUActivity extends Activity {

    private View decorView;
    Button btnDisassemblySU;
    Button btnReassemblySU;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_s_u);

        btnDisassemblySU = findViewById(R.id.btnDisassemblySU);
        btnReassemblySU = findViewById(R.id.btnReassemblySU);

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                switch (v.getId()){

                    case R.id.btnDisassemblySU:
                        Intent b = new Intent(SelectSUActivity.this, VideoDisassemblyDynamicP1.class);
                        startActivity(b);
                        overridePendingTransition(R.anim.right_in, R.anim.alpha);
                        finish();
                        break;

                    case R.id.btnReassemblySU:
                        Intent ps = new Intent(SelectSUActivity.this, VideoAssemblyDynamicP1.class);
                        startActivity(ps);
                        overridePendingTransition(R.anim.right_in, R.anim.alpha);
                        finish();
                        break;



                }

            }
        };

        btnDisassemblySU.setOnClickListener(onClickListener);
        btnReassemblySU.setOnClickListener(onClickListener);

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

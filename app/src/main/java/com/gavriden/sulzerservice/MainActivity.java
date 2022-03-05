package com.gavriden.sulzerservice;


import android.app.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MainActivity extends Activity {

    private View decorView;
    Button btnStartService;
    Button btnQuestion;
    Button btnInfo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnStartService = (Button) findViewById(R.id.btnStartService);
        btnQuestion =(Button) findViewById(R.id.btnQuestion);
        btnInfo = (Button) findViewById(R.id.btnInfo);


        View.OnClickListener onClickListener = new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                switch (v.getId()) {

                    case R.id.btnStartService:
                        Intent s = new Intent(MainActivity.this, ServiceActivity.class);
                        startActivity(s);
                        overridePendingTransition(R.anim.right_in, R.anim.alpha);

                        break;
                    case R.id.btnQuestion:
                        Intent q = new Intent(MainActivity.this, QuestionsActivity.class);
                        startActivity(q);
                        overridePendingTransition(R.anim.right_in, R.anim.alpha);
                        break;
                    case R.id.btnInfo:
                        Intent i = new Intent(MainActivity.this, InfoActivity.class);
                        startActivity(i);
                        overridePendingTransition(R.anim.right_in, R.anim.alpha);
                        break;

                }

            }
        };

        btnStartService.setOnClickListener(onClickListener);
        btnQuestion.setOnClickListener(onClickListener);
        btnInfo.setOnClickListener(onClickListener);



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

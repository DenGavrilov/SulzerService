package com.gavriden.sulzerservice;



import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.transition.Fade;
import android.transition.Transition;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageView;

public class ServiceActivity extends Activity {

    private View decorView;
    Button btnRepair;
    Button btnCheckInstallation;
    Button btnStartInstallation;
    ImageView imageTools;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);

        btnRepair = (Button) findViewById(R.id.btnRepair);
        btnCheckInstallation = (Button) findViewById(R.id.btnCheckInstallation);
        btnStartInstallation = findViewById(R.id.btnStartInstallation);
        imageTools = (ImageView) findViewById(R.id.imageTools);

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                switch (v.getId()){

                    case R.id.btnRepair:
                        Intent r = new Intent(ServiceActivity.this, RepairActivity.class);
                        startActivity(r);
                        overridePendingTransition(R.anim.right_in, R.anim.alpha);
                        break;

                    case R.id.btnCheckInstallation:
                        Intent login = new Intent(ServiceActivity.this, LoginActivity.class);
                        startActivity(login);
                        overridePendingTransition(R.anim.right_in, R.anim.alpha);
                        break;

                    case R.id.btnStartInstallation:
                        Intent install = new Intent(ServiceActivity.this, InstallationActivity.class);
                        startActivity(install);
                        overridePendingTransition(R.anim.right_in, R.anim.alpha);
                        break;

                }

            }
        };

        btnRepair.setOnClickListener(onClickListener);
        btnCheckInstallation.setOnClickListener(onClickListener);
        btnStartInstallation.setOnClickListener(onClickListener);

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

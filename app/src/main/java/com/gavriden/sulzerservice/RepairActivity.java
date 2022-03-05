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

public class RepairActivity extends Activity {

    private View decorView;
    Button btnListOfTools;
    Button btnPumpSection;
    Button btnWetEnd;
    Button btnSealingUnit;
    Button btnBearingUnit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repair);

        btnListOfTools = (Button) findViewById(R.id.btnListOfTools);
        btnPumpSection = (Button) findViewById(R.id.btnPumpSection);
        btnWetEnd = (Button) findViewById(R.id.btnWetEnd);
        btnSealingUnit = (Button) findViewById(R.id.btnSealingUnit);
        btnBearingUnit = (Button) findViewById(R.id.btnBearingUnit);

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                switch (v.getId()){

                    case R.id.btnListOfTools:
                        Intent lt = new Intent(RepairActivity.this, ListToolsActivity.class);
                        startActivity(lt);
                        overridePendingTransition(R.anim.right_in, R.anim.alpha);
                        finish();
                        break;

                    case R.id.btnBearingUnit:
                        Intent b = new Intent(RepairActivity.this, SelectBUActivity.class);
                        startActivity(b);
                        overridePendingTransition(R.anim.right_in, R.anim.alpha);
                        finish();
                        break;

                    case R.id.btnPumpSection:
                        Intent ps = new Intent(RepairActivity.this, SelectPumpUnitActivity.class);
                        startActivity(ps);
                        overridePendingTransition(R.anim.right_in, R.anim.alpha);
                        finish();
                        break;

                    case R.id.btnWetEnd:
                        Intent w = new Intent(RepairActivity.this, SelectWetActivity.class);
                        startActivity(w);
                        overridePendingTransition(R.anim.right_in, R.anim.alpha);
                        finish();
                        break;

                    case R.id.btnSealingUnit:
                        Intent seal = new Intent(RepairActivity.this, SelectSUActivity.class);
                        startActivity(seal);
                        overridePendingTransition(R.anim.right_in, R.anim.alpha);
                        finish();
                        break;



                }

            }
        };

        btnListOfTools.setOnClickListener(onClickListener);
        btnPumpSection.setOnClickListener(onClickListener);
        btnWetEnd.setOnClickListener(onClickListener);
        btnSealingUnit.setOnClickListener(onClickListener);
        btnBearingUnit.setOnClickListener(onClickListener);

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

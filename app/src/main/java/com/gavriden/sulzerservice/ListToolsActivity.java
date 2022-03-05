package com.gavriden.sulzerservice;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class ListToolsActivity extends Activity {

    ImageButton btn_close;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_tools);

        btn_close = findViewById(R.id.btn_close);

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                switch (v.getId()){

                    case R.id.btn_close:

                        Intent intent = new Intent(ListToolsActivity.this, RepairActivity.class);
                        startActivity(intent);
                        overridePendingTransition(R.anim.right_in, R.anim.alpha);
                        finish();
                        break;

                }

            }
        };

        btn_close.setOnClickListener(onClickListener);


    }
}

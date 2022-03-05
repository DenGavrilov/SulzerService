package com.gavriden.sulzerservice;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class QuestionsActivity extends Activity {

    private View decorView;
    Button btnSendQuestion;
    Button btnReturnToMain;
    EditText serviceQuestionText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);

        btnSendQuestion = (Button)findViewById(R.id.btnSendQuestion);
        btnReturnToMain = (Button)findViewById(R.id.btnReturnToMain);
        serviceQuestionText = (EditText)findViewById(R.id.serviceQuestionText);

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                switch (v.getId()){

                    case R.id.btnSendQuestion:

                        if(!serviceQuestionText.getText().toString().isEmpty()){

                            Intent intent = new Intent(Intent.ACTION_VIEW);
                            String subject = "Question from App";
                            Uri uri = Uri.parse("mailto:denis.gavrilov@sulzer.com");
                            intent.setData(uri);
                            intent.putExtra(Intent.EXTRA_SUBJECT, subject);
                            intent.putExtra(Intent.EXTRA_TEXT, serviceQuestionText.getText().toString());
                            startActivity(intent);
                            setResult(RESULT_OK, intent);
                            finish();

                        } else {
                            Toast.makeText(QuestionsActivity.this, R.string.enter_question , Toast.LENGTH_SHORT).show();
                        }

                        break;

                    case R.id.btnReturnToMain:

                        Intent intent = new Intent(QuestionsActivity.this, MainActivity.class);
                        startActivity(intent);
                        overridePendingTransition(R.anim.right_in, R.anim.alpha);
                        break;

                }

            }
        };



        btnSendQuestion.setOnClickListener(onClickListener);
        btnReturnToMain.setOnClickListener(onClickListener);

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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // запишем в лог значения requestCode и resultCode
        // если пришло ОК
        if (resultCode == RESULT_OK) {
            Intent intent = new Intent(QuestionsActivity.this, MainActivity.class);
            Toast.makeText(QuestionsActivity.this, R.string.question_sent, Toast.LENGTH_LONG).show();
            startActivity(intent);
            overridePendingTransition(R.anim.right_in, R.anim.alpha);


        } else {
            Toast.makeText(this, "Wrong result", Toast.LENGTH_SHORT).show();
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

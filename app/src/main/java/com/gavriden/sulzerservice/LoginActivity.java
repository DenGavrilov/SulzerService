package com.gavriden.sulzerservice;


import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.transition.Fade;
import android.transition.Transition;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class LoginActivity extends Activity {

    private View decorView;
    Button btnLogin;
    Button btnRegister;
    EditText email;
    EditText passwordText;
    DBHelper databaseHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnRegister = (Button) findViewById(R.id.btnRegister);
        email = (EditText) findViewById(R.id.emailText);
        passwordText = (EditText) findViewById(R.id.passwordText);
        databaseHelper = new DBHelper(this);


        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                switch (v.getId()){

                    case R.id.btnLogin:
                        String emailValue = email.getText().toString();
                        String passwordValue = passwordText.getText().toString();

                        if (emailValue.equals("")||passwordValue.equals(""))
                            Toast.makeText(LoginActivity.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                        else {
                            Boolean checkuserpass = databaseHelper.checkemailpassword(emailValue, passwordValue);
                            if (checkuserpass==true){
                                Toast.makeText(LoginActivity.this, "Sign in successfull", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(LoginActivity.this, SelectReportActivity.class);
                                intent.putExtra("email", emailValue);
                                startActivity(intent);
                                overridePendingTransition(R.anim.right_in, R.anim.alpha);
                            }else {
                                Toast.makeText(LoginActivity.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                            }
                        }



                        break;

                    case R.id.btnRegister:
                        Intent reg = new Intent(LoginActivity.this, RegisterActivity.class);
                        startActivity(reg);
                        overridePendingTransition(R.anim.right_in, R.anim.alpha);
                        break;



                }

            }
        };

        btnLogin.setOnClickListener(onClickListener);
        btnRegister.setOnClickListener(onClickListener);


        decorView = getWindow().getDecorView();
        decorView.setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener() {
            @Override
            public void onSystemUiVisibilityChange(int visibility) {
                if(visibility == 0)
                    decorView.setSystemUiVisibility(hideSystemBars());
            }
        });

    }

    public void showToast(String message) {

        Toast.makeText(this, message, Toast.LENGTH_LONG).show();

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
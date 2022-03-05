package com.gavriden.sulzerservice;


import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.transition.Fade;
import android.transition.Transition;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class RegisterActivity extends Activity {

    private View decorView;
    Button btnRegister;
    Button btnCancel;
    EditText userNameText;
    EditText userSurnameText;
    EditText passwordText;
    EditText email;
    DBHelper databaseHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        btnRegister = (Button) findViewById(R.id.btnRegister);
        btnCancel = (Button) findViewById(R.id.btnCancel);
        userNameText = (EditText) findViewById(R.id.userNameText);
        userSurnameText = (EditText) findViewById(R.id.userSurnameText);
        passwordText = (EditText) findViewById(R.id.passwordText);
        email = (EditText) findViewById(R.id.email);

        databaseHelper = new DBHelper(this);



        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                switch (v.getId()){

                    case R.id.btnRegister:
                        String usernameValue = userNameText.getText().toString();
                        String userSurnameValue = userSurnameText.getText().toString();
                        String passwordValue = passwordText.getText().toString();
                        String emailValue = email.getText().toString();

                        if (usernameValue.equals("")||userSurnameValue.equals("")||passwordValue.equals("")||emailValue.equals(""))
                            Toast.makeText(RegisterActivity.this,"Please enter all the fields", Toast.LENGTH_SHORT).show();

                        else {
                            Boolean checkuser = databaseHelper.checkemail(emailValue);
                            if (checkuser==false){
                                Boolean insert = databaseHelper.insertData(usernameValue, userSurnameValue, passwordValue, emailValue);
                                if (insert==true){
                                    Toast.makeText(RegisterActivity.this, "Registered successfully", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                                    startActivity(intent);
                                    overridePendingTransition(R.anim.right_in, R.anim.alpha);
                                }else {
                                    Toast.makeText(RegisterActivity.this, "Registration failed!", Toast.LENGTH_SHORT).show();
                                }
                            }else {
                                Toast.makeText(RegisterActivity.this, "User already exists! Please sign in", Toast.LENGTH_SHORT).show();
                            }
                        }

                        break;

                    case R.id.btnCancel:
                        Intent r = new Intent(RegisterActivity.this, LoginActivity.class);
                        startActivity(r);
                        overridePendingTransition(R.anim.right_in, R.anim.alpha);
                        break;



                }

            }
        };

        btnRegister.setOnClickListener(onClickListener);
        btnCancel.setOnClickListener(onClickListener);


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
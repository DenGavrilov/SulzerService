package com.gavriden.sulzerservice;

import androidx.annotation.CallSuper;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PumpRegistrationActivity extends Activity {

    private View decorView;


    TextView nameText;
    TextView surnameText;
    Button btnCancelPumpReg;
    Cursor result;
    Cursor result2;
    Cursor c;
    TextView dateText;
    TextView emailText;
    TextView numberText;
    TextView fillPositionText;
    Spinner spinBS;
    Spinner spinDisch;
    Button btnPumpRegister;
    DBHelper databaseHelper;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pump_registration);

        nameText = (TextView) findViewById(R.id.NameText);
        surnameText = (TextView) findViewById(R.id.SurnameText);
        btnCancelPumpReg = (Button) findViewById(R.id.btnCancelPumpReg);
        btnPumpRegister = (Button) findViewById(R.id.btnPumpRegister);
        dateText = (TextView) findViewById(R.id.dateText);
        emailText = (TextView) findViewById(R.id.emailText);
        spinBS = (Spinner) findViewById(R.id.spinBS);
        spinDisch = (Spinner) findViewById(R.id.spinDisch);
        numberText = (TextView) findViewById(R.id.numberText);
        fillPositionText = (TextView) findViewById(R.id.fillPositionText);

        String currentDateTimeString = DateFormat.getDateInstance().format(new Date());
        dateText.setText(currentDateTimeString);

        Bundle bundle = getIntent().getExtras();
        String email = bundle.getString("email");
        emailText.setText(email);
        String emailValue = emailText.getText().toString();

        final DBHelper helper = new DBHelper(this);
        result = helper.getName(emailValue);
        result2 = helper.getSurname(emailValue);


        while (result.moveToNext()){
            String name = result.getString(result.getColumnIndex(DBHelper.USERNAME));
            nameText.setText(name);
        }

        while (result2.moveToNext()){
            String surname = result2.getString(result2.getColumnIndex(DBHelper.USERSURNAME));
            surnameText.setText(surname);
        }

        databaseHelper = new DBHelper(this);

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                switch (v.getId()){

                    case R.id.btnPumpRegister:
                        String pumpNumValue = numberText.getText().toString();
                        String pumpPosValue = fillPositionText.getText().toString();
                        String bearingValue = spinBS.getSelectedItem().toString();
                        String dischargeValue = spinDisch.getSelectedItem().toString();
                        String emailValue = emailText.getText().toString();

                        if (pumpNumValue.equals("")||pumpPosValue.equals(""))
                            Toast.makeText(PumpRegistrationActivity.this, R.string.enter_pump_number, Toast.LENGTH_SHORT).show();

                        else {
                            Boolean checkpump = databaseHelper.checknumber(pumpNumValue);
                            if(checkpump==false){
                                Boolean insert = databaseHelper.insertPump(pumpNumValue, pumpPosValue, bearingValue, dischargeValue);
                                if(insert==true){
                                    Toast.makeText(PumpRegistrationActivity.this, R.string.pump_registered, Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(PumpRegistrationActivity.this, CustomerRegActivity.class);
                                    intent.putExtra("email", emailValue);
                                    intent.putExtra("number", pumpNumValue);
                                    startActivity(intent);
                                    overridePendingTransition(R.anim.right_in, R.anim.alpha);
                                }else {
                                    Toast.makeText(PumpRegistrationActivity.this, R.string.registration_failed, Toast.LENGTH_SHORT).show();
                                }
                            }else {
                                Toast.makeText(PumpRegistrationActivity.this, R.string.pump_exists, Toast.LENGTH_SHORT).show();
                            }
                        }

                        break;

                    case R.id.btnCancelPumpReg:
                        Intent r = new Intent(PumpRegistrationActivity.this, LoginActivity.class);
                        startActivity(r);
                        overridePendingTransition(R.anim.right_in, R.anim.alpha);
                        break;

                }

            }
        };

        btnPumpRegister.setOnClickListener(onClickListener);
        btnCancelPumpReg.setOnClickListener(onClickListener);


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

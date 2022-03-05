package com.gavriden.sulzerservice;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.blogspot.atifsoftwares.circularimageview.CircularImageView;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CustomerRegActivity extends Activity {

    private View decorView;
    private CircularImageView mainImg;

    private static final int CAMERA_REQUEST_CODE = 100;
    private static final int STORAGE_REQUEST_CODE = 101;

    private static final int IMAGE_PICK_CAMERA_CODE = 102;
    private static final int IMAGE_PICK_GALLERY_CODE = 103;

    private String[] cameraPermissions;
    private String[] storagePermissions;

    private Uri mainImageUri;
    private Uri blankImage = Uri.parse("android.resource://com.gavriden.sulzerservice/"+ R.drawable.ahlstar_first);
    private Uri resultImage;

    TextView dateText;
    TextView nameText;
    TextView surnameText;
    TextView emailText;
    TextView textBS;
    TextView textDisch;
    TextView numText;
    TextView filledPosText;
    EditText newCustomerAdd;
    EditText newDepartmenAdd;
    Cursor result;
    Cursor result2;
    Cursor resultBS;
    Cursor resultDischarge;
    Cursor resultPosition;
    Cursor resultId;
    Cursor resultId2;
    Cursor resultUserId;
    Cursor resultPumpId;
    Cursor resultDepId;
    DBHelper databaseHelper;
    Button btnCustRegister;
    Button btnCancelCustReg;
    Button btnAddCustomer;
    Button btnAddDepartment;
    Button btnTakeMainPicture;
    Spinner spinnerCustomer;
    Spinner spinnerDepartments;
    TextView cusId;
    TextView cusId2;
    TextView userIdText;
    TextView departmentIdText;
    TextView pumpIdText;

    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_reg);

        dateText = (TextView) findViewById(R.id.dateText);
        nameText = (TextView) findViewById(R.id.NameText);
        surnameText = (TextView) findViewById(R.id.SurnameText);
        emailText = (TextView) findViewById(R.id.emailText);
        textBS = (TextView) findViewById(R.id.textBS);
        textDisch = (TextView) findViewById(R.id.textDisch);
        numText = (TextView) findViewById(R.id.numText);
        filledPosText = (TextView) findViewById(R.id.filledPosText);
        userIdText = (TextView) findViewById(R.id.userIdText);
        departmentIdText = (TextView) findViewById(R.id.departmentIdText);
        pumpIdText = (TextView) findViewById(R.id.pumpIdText);
        btnCustRegister = (Button) findViewById(R.id.btnCustRegister);
        btnCancelCustReg = (Button) findViewById(R.id.btnCancelCustReg);
        btnAddCustomer = (Button) findViewById(R.id.btnAddCustomer);
        btnAddDepartment = (Button) findViewById(R.id.btnAddDepartment);
        btnTakeMainPicture = (Button) findViewById(R.id.btnTakeMainPicture);
        mainImg = findViewById(R.id.mainImg);

        spinnerCustomer = (Spinner) findViewById(R.id.spinnerCustomer);
        spinnerDepartments = (Spinner) findViewById(R.id.spinnerDepartments);
        cusId = (TextView) findViewById(R.id.cusId);
        cusId2 = (TextView) findViewById(R.id.cusId2);

        cameraPermissions = new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE};
        storagePermissions = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE};

        SpinnerData();
        //SpinnerDepartment();

        spinnerCustomer.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String customerValue = parent.getItemAtPosition(position).toString();
                resultId = databaseHelper.getCustomerId(customerValue);
                SpinnerDepartment();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });

        spinnerDepartments.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String departmentId = spinnerDepartments.getSelectedItem().toString();
                resultDepId = databaseHelper.getDepId(departmentId);
                String userId = emailText.getText().toString();
                resultUserId = databaseHelper.getUserId(userId);
                String pumpId = numText.getText().toString();
                resultPumpId = databaseHelper.getPumpId(pumpId);
                while (resultDepId.moveToNext()){
                    String r_departmentId = resultDepId.getString(resultDepId.getColumnIndex(databaseHelper.DEPARTMENTID));
                    departmentIdText.setText(r_departmentId);
                }

                while (resultUserId.moveToNext()){
                    String r_userId = resultUserId.getString(resultUserId.getColumnIndex(databaseHelper.USERID));
                    userIdText.setText(r_userId);
                }

                while (resultPumpId.moveToNext()){
                    String r_pumpId = resultPumpId.getString(resultPumpId.getColumnIndex(databaseHelper.PUMPID));
                    pumpIdText.setText(r_pumpId);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        String currentDateTimeString = DateFormat.getDateInstance().format(new Date());
        dateText.setText(currentDateTimeString);

        Bundle bundle = getIntent().getExtras();
        String email = bundle.getString("email");
        String number = bundle.getString("number");
        emailText.setText(email);
        numText.setText(number);
        String emailValue = emailText.getText().toString();
        String numValue = numText.getText().toString();








        final DBHelper helper = new DBHelper(this);
        result = helper.getName(emailValue);
        result2 = helper.getSurname(emailValue);
        resultBS = helper.getBS(numValue);
        resultDischarge = helper.getDischarge(numValue);
        resultPosition = helper.getPosition(numValue);




        //resultPumpId = databaseHelper.getPumpId(pumpId);
        //resultDepId = databaseHelper.getDepId(departmentId);
        //resultId = helper.getCustomerId(customerValue);

        while (result.moveToNext()){
            String name = result.getString(result.getColumnIndex(DBHelper.USERNAME));
            nameText.setText(name);
        }

        while (result2.moveToNext()){
            String surname = result2.getString(result2.getColumnIndex(DBHelper.USERSURNAME));
            surnameText.setText(surname);
        }

        while (resultBS.moveToNext()){
            String bearingsize = resultBS.getString(resultBS.getColumnIndex(DBHelper.SIZEBU));
            textBS.setText(bearingsize);
        }

        while (resultDischarge.moveToNext()){
            String discharge = resultDischarge.getString(resultDischarge.getColumnIndex(DBHelper.DISCHARGE));
            textDisch.setText(discharge);
        }

        while (resultPosition.moveToNext()){
            String position = resultPosition.getString(resultPosition.getColumnIndex(DBHelper.POSITION));
            filledPosText.setText(position);
        }

        /*while (resultId.moveToNext()){
            String customerId = resultId.getString(resultId.getColumnIndex(DBHelper.CUSTOMERID));
            cusId.setText(customerId);
        }*/


        databaseHelper = new DBHelper(this);

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                switch (v.getId()){

                    case R.id.btnCustRegister:

                        inputReportData();

                        finish();

                        break;

                    case R.id.btnAddCustomer:

                        AddCustomerClick(v);

                        break;

                    case R.id.btnAddDepartment:

                        addDepartmentClick(v);

                        break;

                    case R.id.btnTakeMainPicture:

                        imagePickDialog();
                }

            }
        };

        btnCustRegister.setOnClickListener(onClickListener);
        btnAddCustomer.setOnClickListener(onClickListener);
        btnAddDepartment.setOnClickListener(onClickListener);
        btnTakeMainPicture.setOnClickListener(onClickListener);



        decorView = getWindow().getDecorView();
        decorView.setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener() {
            @Override
            public void onSystemUiVisibilityChange(int visibility) {
                if(visibility == 0)
                    decorView.setSystemUiVisibility(hideSystemBars());
            }
        });
    }

    private void inputReportData() {



        String addedTime = dateText.getText().toString();
        String userIdValue = userIdText.getText().toString();
        String pumpIdValue = pumpIdText.getText().toString();
        String departmentIdValue = departmentIdText.getText().toString();
        String zeropoint = "-";
        int zerostatus = DBHelper.SYNC_STATUS_FAILED;

        if (mainImageUri!=null){
            resultImage = mainImageUri;
        }else {
            resultImage = blankImage;
        }


        Boolean insert = databaseHelper.insertReportData(resultImage, addedTime, userIdValue, pumpIdValue, departmentIdValue,
                zeropoint, zeropoint, zeropoint, zeropoint, zeropoint, zeropoint, zeropoint, zeropoint, zeropoint, zeropoint, zeropoint,
                zeropoint, zeropoint, zeropoint, zeropoint, zeropoint, zeropoint, zeropoint, zeropoint, zeropoint, zeropoint, zeropoint,
                zeropoint, zeropoint, zeropoint, zeropoint, zeropoint, zeropoint, zeropoint, zeropoint, zeropoint, zeropoint, zeropoint,
                zeropoint, zeropoint, zeropoint, zeropoint, zeropoint, zeropoint, zeropoint, zeropoint, zeropoint, zeropoint, zeropoint,
                zeropoint, zeropoint, zeropoint, zeropoint, zeropoint, zeropoint, zeropoint, zeropoint, zerostatus);

        if(insert==true){
            Toast.makeText(CustomerRegActivity.this, R.string.data_added, Toast.LENGTH_SHORT).show();
            Intent c = new Intent(CustomerRegActivity.this, CheckInstallActivity.class);
            c.putExtra("userId", userIdValue);
            c.putExtra("pumpId", pumpIdValue);
            startActivity(c);
            overridePendingTransition(R.anim.right_in, R.anim.alpha);
            //SpinnerData();
            //SpinnerDepartment();
            //Intent intent = new Intent(CustomerRegActivity.this, CheckCouplingActivity.class);
            //intent.putExtra("email", emailValue);
            //intent.putExtra("number", pumpNumValue);
            //startActivity(intent);
            //overridePendingTransition(R.anim.right_in, R.anim.alpha);
        }else {
            Toast.makeText(CustomerRegActivity.this, R.string.registration_failed, Toast.LENGTH_SHORT).show();
        }

    }

    private void imagePickDialog() {

        String[] options = {"Camera", "Gallery"};

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Pick Image From");

        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (which==0){
                    if (!checkCameraPermissions()){
                        requestCameraPermission();
                    }
                    else
                    {
                        pickFromCamera();
                    }
                }
                else if (which==1){
                    if (!checkStoragePermission()){
                        requestStoragePermission();
                    }
                    else
                    {
                        pickFromGallery();

                    }
                }
            }
        });

        builder.create().show();

    }

    private void pickFromGallery() {

        Intent galleryIntent = new Intent(Intent.ACTION_PICK);
        galleryIntent.setType("image/*");
        startActivityForResult(galleryIntent, IMAGE_PICK_GALLERY_CODE);

    }

    private void pickFromCamera() {

        ContentValues values = new ContentValues();
        values.put(MediaStore.Images.Media.TITLE, "Image title");
        values.put(MediaStore.Images.Media.DESCRIPTION, "Image description");
        mainImageUri = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);

        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, mainImageUri);
        startActivityForResult(cameraIntent, IMAGE_PICK_CAMERA_CODE);

    }

    private boolean checkStoragePermission(){

        boolean result = ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE) == (PackageManager.PERMISSION_GRANTED);

        return result;

    }

    private void requestStoragePermission(){
        ActivityCompat.requestPermissions(this, storagePermissions, STORAGE_REQUEST_CODE);
    }

    private boolean checkCameraPermissions(){

        boolean result = ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE) == (PackageManager.PERMISSION_GRANTED);

        boolean result1 = ContextCompat.checkSelfPermission(this,
                Manifest.permission.CAMERA) == (PackageManager.PERMISSION_GRANTED);

        return result && result1;

    }

    private void requestCameraPermission(){
        ActivityCompat.requestPermissions(this, cameraPermissions, CAMERA_REQUEST_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode){

            case CAMERA_REQUEST_CODE:{

                if (grantResults.length>0){

                    boolean cameraAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    boolean storageAccepted = grantResults[1] == PackageManager.PERMISSION_GRANTED;

                    if (cameraAccepted && storageAccepted){
                        pickFromCamera();
                    }
                    else
                    {
                        Toast.makeText(this, "Camera and Storage permissions are required", Toast.LENGTH_SHORT).show();
                    }

                }

            }

            break;
            case STORAGE_REQUEST_CODE:{

                if (grantResults.length>0){

                    boolean storageAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    if (storageAccepted){
                        pickFromGallery();
                    }
                    else
                    {
                        Toast.makeText(this, "Storage permissions is required...", Toast.LENGTH_SHORT).show();
                    }

                }

            }
            break;

        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (resultCode==RESULT_OK){

            if (requestCode==IMAGE_PICK_GALLERY_CODE){

                CropImage.activity(data.getData())
                        .setGuidelines(CropImageView.Guidelines.ON)
                        .setAspectRatio(1, 1)
                        .start(this);

            }
            else if (requestCode==IMAGE_PICK_CAMERA_CODE){


                CropImage.activity(mainImageUri)
                        .setGuidelines(CropImageView.Guidelines.ON)
                        .setAspectRatio(1, 1)
                        .start(this);

            }
            else if (requestCode==CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE){

                CropImage.ActivityResult result = CropImage.getActivityResult(data);
                if (resultCode==RESULT_OK){
                    Uri resultUri = result.getUri();
                    mainImageUri = resultUri;
                    mainImg.setImageURI(resultUri);
                }
                else if (resultCode==CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE){
                    Exception error = result.getError();

                    Toast.makeText(this, ""+error, Toast.LENGTH_SHORT).show();

                }

            }

        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    private void SpinnerData(){
        DBHelper db= new DBHelper(getApplicationContext());
        List<String> customers= db.getAllCustomers();
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, customers);
        spinnerCustomer.setAdapter(dataAdapter);

    }

    private void SpinnerDepartment(){
        DBHelper db = new DBHelper(getApplicationContext());
        final String customerValue2 = spinnerCustomer.getSelectedItem().toString();
        resultId2 = db.getCustomerId(customerValue2);
        while (resultId2.moveToNext()){
            String customerId2 = resultId2.getString(resultId2.getColumnIndex(DBHelper.CUSTOMERID));
            cusId2.setText(customerId2);
        }
        String customerId = cusId2.getText().toString();
        List<String> departments = db.getDepartments(customerId);
        ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, departments);
        spinnerDepartments.setAdapter(dataAdapter2);


    }

    public void AddCustomerClick(View v){

        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle(R.string.add_new_customer);
        LayoutInflater inflater = this.getLayoutInflater();
        final View view = inflater.inflate(R.layout.add_new_customer_layout, null);
        dialog.setView(view);

        dialog.setPositiveButton(R.string.save_customer, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                newCustomerAdd = view.findViewById(R.id.new_customer_add);
                String customValue = newCustomerAdd.getText().toString();

                if (customValue.equals(""))
                    Toast.makeText(CustomerRegActivity.this, R.string.enter_customer, Toast.LENGTH_SHORT).show();

                else{
                    Boolean insert = databaseHelper.insertCustomer(customValue);
                    if(insert==true){
                        Toast.makeText(CustomerRegActivity.this, R.string.customer_registered, Toast.LENGTH_SHORT).show();
                        SpinnerData();
                        //SpinnerDepartment();
                        //Intent intent = new Intent(CustomerRegActivity.this, CheckCouplingActivity.class);
                        //intent.putExtra("email", emailValue);
                        //intent.putExtra("number", pumpNumValue);
                        //startActivity(intent);
                        //overridePendingTransition(R.anim.right_in, R.anim.alpha);
                    }else {
                        Toast.makeText(CustomerRegActivity.this, R.string.registration_failed, Toast.LENGTH_SHORT).show();
                    }
                }

            }
        })

                .setNegativeButton(R.string.cancel, null)
                .create();


        dialog.show();

    }

    public void addDepartmentClick(View v){

        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle(R.string.add_new_department);
        LayoutInflater inflater = this.getLayoutInflater();
        final View view = inflater.inflate(R.layout.add_new_department_layout, null);
        dialog.setView(view);

        final String customerValue = spinnerCustomer.getSelectedItem().toString();
        resultId = databaseHelper.getCustomerId(customerValue);
        while (resultId.moveToNext()){
            String customerId = resultId.getString(resultId.getColumnIndex(DBHelper.CUSTOMERID));
            cusId.setText(customerId);
        }

        dialog.setPositiveButton(R.string.save_department, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                newDepartmenAdd = view.findViewById(R.id.new_department_add);
                String departmentValue = newDepartmenAdd.getText().toString();
                String customerId = cusId.getText().toString();

                if (departmentValue.equals(""))
                    Toast.makeText(CustomerRegActivity.this, R.string.enter_customer, Toast.LENGTH_SHORT).show();

                else{
                    Boolean insert = databaseHelper.insertDepartment(departmentValue, customerId);
                    if(insert==true){
                        Toast.makeText(CustomerRegActivity.this, R.string.customer_registered, Toast.LENGTH_SHORT).show();
                        //SpinnerData();
                        SpinnerDepartment();
                        //Intent intent = new Intent(CustomerRegActivity.this, CheckCouplingActivity.class);
                        //intent.putExtra("email", emailValue);
                        //intent.putExtra("number", pumpNumValue);
                        //startActivity(intent);
                        //overridePendingTransition(R.anim.right_in, R.anim.alpha);
                    }else {
                        Toast.makeText(CustomerRegActivity.this, R.string.registration_failed, Toast.LENGTH_SHORT).show();
                    }
                }

            }
        })

                .setNegativeButton(R.string.cancel, null)
                .create();


        dialog.show();

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

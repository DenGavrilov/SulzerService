package com.gavriden.sulzerservice;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.blogspot.atifsoftwares.circularimageview.CircularImageView;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

public class CheckConditionsActivity extends Activity {

    private CircularImageView conditionImg;

    private View decorView;

    private static final int CAMERA_REQUEST_CODE = 100;
    private static final int STORAGE_REQUEST_CODE = 101;

    private static final int IMAGE_PICK_CAMERA_CODE = 102;
    private static final int IMAGE_PICK_GALLERY_CODE = 103;

    private String[] cameraPermissions;
    private String[] storagePermissions;

    private Uri imageUri;

    Button btnTakePicture;
    Button btnCancel;
    Button btnRegister;
    Spinner spinner_1, spinner_2, spinner_3, spinner_4;
    TextView userText;
    TextView reportText;
    TextView userBuffer;
    TextView pumpBuffer;
    TextView commentsGenText;
    Cursor resultUserId;
    Cursor resultReportId;
    DBHelper databaseHelper;

    @SuppressLint("WrongConstant")

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_conditions);

        btnCancel = findViewById(R.id.btnLogin);
        btnRegister = findViewById(R.id.btnRegister);
        btnTakePicture = findViewById(R.id.btnTakePicture);
        conditionImg = findViewById(R.id.conditionImg);
        userText = findViewById(R.id.userText);
        reportText = findViewById(R.id.reportText);
        userBuffer = findViewById(R.id.userBuffer);
        pumpBuffer = findViewById(R.id.pumpBuffer);
        commentsGenText = (EditText) findViewById(R.id.commentsGenText);

        spinner_1 = findViewById(R.id.spinner_1);
        spinner_2 = findViewById(R.id.spinner_2);
        spinner_3 = findViewById(R.id.spinner_3);
        spinner_4 = findViewById(R.id.spinner_4);

        Bundle bundle = getIntent().getExtras();
        String user = bundle.getString("email");
        String report = bundle.getString("report");
        userText.setText(user);
        reportText.setText(report);

        cameraPermissions = new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE};
        storagePermissions = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE};

        btnTakePicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                imagePickDialog();

            }
        });

        databaseHelper = new DBHelper(this);

        View.OnClickListener onClickListener= new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                switch (v.getId()){

                    case R.id.btnRegister:

                        String emailValue = userText.getText().toString();
                        resultUserId = databaseHelper.getUserId(emailValue);
                        while (resultUserId.moveToNext()) {
                            String report = resultUserId.getString(resultUserId.getColumnIndex(DBHelper.USERID));
                            userBuffer.setText(report);
                        }

                        String reportNum = reportText.getText().toString();
                        resultReportId = databaseHelper.getPumpReport(reportNum);
                        while (resultReportId.moveToNext()) {
                            String report = resultReportId.getString(resultReportId.getColumnIndex(DBHelper.RPUMPID));
                            pumpBuffer.setText(report);
                        }

                        String paintValue = spinner_1.getSelectedItem().toString();
                        String groutValue = spinner_2.getSelectedItem().toString();
                        String damageValue = spinner_3.getSelectedItem().toString();
                        String rotationValue = spinner_4.getSelectedItem().toString();
                        String reportIdValue = reportText.getText().toString();
                        String reportValue = pumpBuffer.getText().toString();
                        String userValue = userBuffer.getText().toString();
                        String commentValue = commentsGenText.getText().toString();

                        Boolean insert = databaseHelper.insertCondReport(reportIdValue, paintValue, groutValue, damageValue, rotationValue, commentValue, imageUri);

                        if(insert==true){
                            Toast.makeText(CheckConditionsActivity.this, R.string.data_added, Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(CheckConditionsActivity.this, CheckInstallActivity.class);
                            intent.putExtra("userId", userValue);
                            intent.putExtra("pumpId", reportValue);
                            startActivity(intent);
                            overridePendingTransition(R.anim.right_in, R.anim.alpha);
                        }else {
                            Toast.makeText(CheckConditionsActivity.this, R.string.registration_failed, Toast.LENGTH_SHORT).show();
                        }

                        finish();

                        break;

                }

            }
        };

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
        imageUri = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);

        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
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


                CropImage.activity(imageUri)
                        .setGuidelines(CropImageView.Guidelines.ON)
                        .setAspectRatio(1, 1)
                        .start(this);

            }
            else if (requestCode==CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE){

                CropImage.ActivityResult result = CropImage.getActivityResult(data);
                if (resultCode==RESULT_OK){
                    Uri resultUri = result.getUri();
                    imageUri = resultUri;
                    conditionImg.setImageURI(resultUri);
                }
                else if (resultCode==CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE){
                    Exception error = result.getError();

                    Toast.makeText(this, ""+error, Toast.LENGTH_SHORT).show();

                }

            }

        }

        super.onActivityResult(requestCode, resultCode, data);
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

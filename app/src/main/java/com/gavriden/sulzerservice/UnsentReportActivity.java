package com.gavriden.sulzerservice;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.itextpdf.io.font.PdfEncodings;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;


import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;

public class UnsentReportActivity extends Activity {

    private View decorView;
    RecyclerView recyclerView;
    DBHelper dbHelper;
    CustomAdapter customAdapter;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<UnsentReports> unsentReports = new ArrayList<>();
    String pumpNumber;
    String dataReport;
    String userName;
    String userSurname;
    String mainImage;
    Bitmap bitmap;
    Image mImage;
    String customer;
    String department;
    String pump;
    String pos;
    String size;
    String dis;
    String posnum;
    String typelubrication;
    String plate;
    String levellubrication;
    String avdoc;
    String tds;
    String curve;
    String secdraw;
    String couplingsize;
    String spare;
    String couplingtype;
    String dimdraw;
    String couplinggap;
    String manual;
    String couplingscrew;
    String safety;
    String couplingalign;
    String certificate;
    String couplingguard;
    String genpipe;
    String installmotor;
    String bends;
    String bracketsmotor;
    String inletpipe;
    String shimsmotor;
    String locpipe;
    String supportpipe;
    String instalpipe;
    String painting;
    String gengrouting;
    String damages;
    String execution;
    String rotation;
    String grouting;
    String motorstand;
    String liftarm;
    String fasteners;
    String flanges;
    String gaskets;
    String typeseal;
    String swjoints;
    String swsettings;
    String swcontrolunit;
    TextView emailText;
    private static final String TAG = "MyApp";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unsent_report);

        emailText = findViewById(R.id.emailText);

        Bundle bundle = getIntent().getExtras();
        final String email = bundle.getString("email");
        emailText.setText(email);

        dbHelper = new DBHelper(UnsentReportActivity.this);

        displayData();
        try {
            buildRecyclerView();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        decorView = getWindow().getDecorView();
        decorView.setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener() {
            @Override
            public void onSystemUiVisibilityChange(int visibility) {
                if(visibility == 0)
                    decorView.setSystemUiVisibility(hideSystemBars());
            }
        });

    }

    public void getPump(int position){
        pumpNumber = unsentReports.get(position).getPump();
        dataReport = unsentReports.get(position).getDate();

        Cursor cursor = dbHelper.getReportIdByPump(pumpNumber);
        while (cursor.moveToNext()) {
            String id = cursor.getString(cursor.getColumnIndex(DBHelper.REPORTID));

            Cursor cursorUsername = dbHelper.getUsername(id);
            Cursor cursorSurname = dbHelper.getUsersurname(id);
            Cursor cursorMImage = dbHelper.getMainImage(id);
            Cursor cursorDepartment = dbHelper.getDepartment(id);
            Cursor cursorNum = dbHelper.getPumpNumber(id);
            Cursor cursorCoupling = dbHelper.getCoupling(id);

            while (cursorUsername.moveToNext()&&cursorSurname.moveToNext()&&cursorMImage.moveToNext()&&cursorDepartment.moveToNext()&&cursorNum.moveToNext()&&cursorCoupling.moveToNext()){

                userName = cursorUsername.getString(cursorUsername.getColumnIndex(DBHelper.USERNAME));
                userSurname = cursorSurname.getString(cursorSurname.getColumnIndex(DBHelper.USERSURNAME));
                mainImage = cursorMImage.getString(cursorMImage.getColumnIndex(DBHelper.MAINIMAGE));
                pump = cursorNum.getString(cursorNum.getColumnIndex(DBHelper.PUMPNUMBER));
                posnum = cursorCoupling.getString(cursorCoupling.getColumnIndex(DBHelper.POSNUM));
                typelubrication = cursorCoupling.getString(cursorCoupling.getColumnIndex(DBHelper.TYPELUBRICATION));
                plate = cursorCoupling.getString(cursorCoupling.getColumnIndex(DBHelper.PLATE));
                levellubrication = cursorCoupling.getString(cursorCoupling.getColumnIndex(DBHelper.LEVELLUBRICATION));
                avdoc = cursorCoupling.getString(cursorCoupling.getColumnIndex(DBHelper.AVDOC));
                tds = cursorCoupling.getString(cursorCoupling.getColumnIndex(DBHelper.TDS));
                curve = cursorCoupling.getString(cursorCoupling.getColumnIndex(DBHelper.CURVE));
                secdraw = cursorCoupling.getString(cursorCoupling.getColumnIndex(DBHelper.SECDRAW));
                couplingsize = cursorCoupling.getString(cursorCoupling.getColumnIndex(DBHelper.COUPLINGSIZE));
                spare = cursorCoupling.getString(cursorCoupling.getColumnIndex(DBHelper.SPARE));
                couplingtype = cursorCoupling.getString(cursorCoupling.getColumnIndex(DBHelper.COUPLINGTYPE));
                dimdraw = cursorCoupling.getString(cursorCoupling.getColumnIndex(DBHelper.DIMDRAW));
                couplinggap = cursorCoupling.getString(cursorCoupling.getColumnIndex(DBHelper.COUPLINGGAP));
                manual = cursorCoupling.getString(cursorCoupling.getColumnIndex(DBHelper.MANUAL));
                couplingscrew = cursorCoupling.getString(cursorCoupling.getColumnIndex(DBHelper.COUPLINGSCREW));
                safety = cursorCoupling.getString(cursorCoupling.getColumnIndex(DBHelper.SAFETY));
                couplingalign = cursorCoupling.getString(cursorCoupling.getColumnIndex(DBHelper.COUPLINGALIGN));
                certificate = cursorCoupling.getString(cursorCoupling.getColumnIndex(DBHelper.CERTIFICATE));
                couplingguard = cursorCoupling.getString(cursorCoupling.getColumnIndex(DBHelper.COUPLINGGUARD));
                genpipe = cursorCoupling.getString(cursorCoupling.getColumnIndex(DBHelper.GENPIPE));
                installmotor = cursorCoupling.getString(cursorCoupling.getColumnIndex(DBHelper.INSTALLMOTOR));
                bends = cursorCoupling.getString(cursorCoupling.getColumnIndex(DBHelper.BENDS));
                bracketsmotor = cursorCoupling.getString(cursorCoupling.getColumnIndex(DBHelper.BRACKETSMOTOR));
                inletpipe = cursorCoupling.getString(cursorCoupling.getColumnIndex(DBHelper.INLETPIPE));
                shimsmotor = cursorCoupling.getString(cursorCoupling.getColumnIndex(DBHelper.SHIMSMOTOR));
                locpipe = cursorCoupling.getString(cursorCoupling.getColumnIndex(DBHelper.LOCPIPE));
                supportpipe = cursorCoupling.getString(cursorCoupling.getColumnIndex(DBHelper.SUPPORTPIPE));
                instalpipe = cursorCoupling.getString(cursorCoupling.getColumnIndex(DBHelper.INSTALPIPE));
                painting = cursorCoupling.getString(cursorCoupling.getColumnIndex(DBHelper.PAINTING));
                gengrouting = cursorCoupling.getString(cursorCoupling.getColumnIndex(DBHelper.GENGROUTING));
                damages = cursorCoupling.getString(cursorCoupling.getColumnIndex(DBHelper.DAMAGES));
                execution = cursorCoupling.getString(cursorCoupling.getColumnIndex(DBHelper.EXECUTION));
                rotation = cursorCoupling.getString(cursorCoupling.getColumnIndex(DBHelper.ROTATION));
                grouting = cursorCoupling.getString(cursorCoupling.getColumnIndex(DBHelper.GROUTING));
                motorstand = cursorCoupling.getString(cursorCoupling.getColumnIndex(DBHelper.MOTORSTAND));
                liftarm = cursorCoupling.getString(cursorCoupling.getColumnIndex(DBHelper.LIFTARM));
                fasteners = cursorCoupling.getString(cursorCoupling.getColumnIndex(DBHelper.FASTENERS));
                flanges = cursorCoupling.getString(cursorCoupling.getColumnIndex(DBHelper.FLANGES));
                gaskets = cursorCoupling.getString(cursorCoupling.getColumnIndex(DBHelper.GASKETS));
                typeseal = cursorCoupling.getString(cursorCoupling.getColumnIndex(DBHelper.TYPESEAL));
                swjoints = cursorCoupling.getString(cursorCoupling.getColumnIndex(DBHelper.SWJOINTS));
                swsettings = cursorCoupling.getString(cursorCoupling.getColumnIndex(DBHelper.SWSETTINGS));
                swcontrolunit = cursorCoupling.getString(cursorCoupling.getColumnIndex(DBHelper.SWCONTROLUNIT));

                Cursor cursorPumpDescription = dbHelper.getPumpDescription(pump);
                while (cursorPumpDescription.moveToNext()){
                    pos = cursorPumpDescription.getString(cursorPumpDescription.getColumnIndex(DBHelper.PUMPPOSITION));
                    size = cursorPumpDescription.getString(cursorPumpDescription.getColumnIndex(DBHelper.SIZEBU));
                    dis = cursorPumpDescription.getString(cursorPumpDescription.getColumnIndex(DBHelper.DISCHARGE));
                }

                try {
                    bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), Uri.parse(mainImage));
                } catch (IOException e) {

                    e.printStackTrace();
                }
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
                byte[] bitmapData = stream.toByteArray();

                ImageData imageData = ImageDataFactory.create(bitmapData);
                mImage = new Image(imageData);

                department = cursorDepartment.getString(cursorDepartment.getColumnIndex(DBHelper.DEPARTMENTNAME));
                Cursor cursorCustomer = dbHelper.getCustomer(department);

                while (cursorCustomer.moveToNext()){

                    customer = cursorCustomer.getString(cursorCustomer.getColumnIndex(DBHelper.CUSTOMERNAME));

                }

                }

        }

    }

    void displayData(){
        Cursor cursor = dbHelper.readUnsentReports();
        if (cursor.getCount() == 0){
            Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();
        }
        else {

            while (cursor.moveToNext()){

                String number = cursor.getString(cursor.getColumnIndex(DBHelper.PUMPNUMBER));
                String date = cursor.getString(cursor.getColumnIndex(DBHelper.ADDEDTIME));

                unsentReports.add(new UnsentReports(number, date));

            }

        }
    }

    public void buildRecyclerView() throws MalformedURLException {

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        customAdapter = new CustomAdapter(unsentReports);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(layoutManager);

        Drawable drawable = getDrawable(R.drawable.sulzer_logo_blue);
        Bitmap bitmap = ((BitmapDrawable)drawable).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] bitmapData = stream.toByteArray();

        ImageData imageData = ImageDataFactory.create(bitmapData);
        final Image image = new Image(imageData);


        customAdapter.setOnItemClickListener(new CustomAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(int position) {

                getPump(position);
                try {

                    Log.i(TAG, "Was sended:" + pumpNumber+ dataReport+ userSurname+ image+""+ " 1111 "+ mImage);
                    new PrintPDF(pumpNumber, dataReport, userName, userSurname, image, mImage, department, customer, pos, size, dis, posnum, typelubrication, plate,
                            levellubrication, avdoc, tds, curve, secdraw, couplingsize, spare, couplingtype, dimdraw, couplinggap, manual, couplingscrew, safety,
                            couplingalign, certificate, couplingguard, genpipe, installmotor, bends, bracketsmotor, inletpipe, shimsmotor, locpipe, supportpipe,
                            instalpipe, painting, gengrouting, damages, execution, rotation, grouting, motorstand, liftarm, fasteners, flanges, gaskets, typeseal,
                            swjoints, swsettings, swcontrolunit).getPDF();
                    String myPath = PrintPDF.mFilePath;


                    Intent intent = new Intent(UnsentReportActivity.this, ShowPDF.class);
                    intent.putExtra("filePath", myPath);
                    startActivity(intent);
                    overridePendingTransition(R.anim.right_in, R.anim.alpha);
                    finish();

                } catch (IOException e) {
                    e.printStackTrace();
                }

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

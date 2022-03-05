package com.gavriden.sulzerservice;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class SendReportActivity extends Activity {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    RecyclerAdapter adapter;
    ArrayList<Report> arrayList = new ArrayList<>();
    BroadcastReceiver broadcastReceiver;
    Button btnSend;
    Button btnToStart;
    Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_report);

        btnSend = findViewById(R.id.btnSend);
        btnToStart = findViewById(R.id.btnToStart);
        recyclerView = findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        adapter = new RecyclerAdapter(arrayList);
        recyclerView.setAdapter(adapter);
        readFromLocalStorage();

        final DBHelper dbHelper = new DBHelper(this);

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){

                    case R.id.btnSend:
                        SQLiteDatabase database = dbHelper.getReadableDatabase();
                        Cursor cursor = dbHelper.readFromLocalDatabase(database);
                        while (cursor.moveToNext()){
                            String id = cursor.getString(cursor.getColumnIndex(DBHelper.REPORTID));

                            //if(dbHelper.checkStatus(id)){

                                Cursor cursorNum = dbHelper.getPumpNumber(id);
                                Cursor cursorUser = dbHelper.getUserIdReport(id);
                                Cursor cursorRUserId = dbHelper.getRUserId(id);
                                Cursor cursorUsername = dbHelper.getUsername(id);
                                Cursor cursorUsersurname = dbHelper.getUsersurname(id);
                                Cursor cursorUserpassword = dbHelper.getUserPassword(id);
                                Cursor cursorUserEmail = dbHelper.getUserMail(id);
                                Cursor cursorMainImage = dbHelper.getMainImage(id);
                                Cursor cursorDepartment = dbHelper.getDepartment(id);
                                Cursor cursorDate = dbHelper.getDate(id);
                                Cursor cursorCoupling = dbHelper.getCoupling(id);

                                while (cursorNum.moveToNext()&&cursorUser.moveToNext()&&cursorRUserId.moveToNext()&&cursorUsername.moveToNext()&&cursorUsersurname.moveToNext()&&cursorMainImage.moveToNext()
                                        &&cursorUserpassword.moveToNext()&&cursorUserEmail.moveToNext()&&cursorDepartment.moveToNext()&&cursorDate.moveToNext()&&cursorCoupling.moveToNext())
                                {
                                    String pump = cursorNum.getString(cursorNum.getColumnIndex(DBHelper.PUMPNUMBER));
                                    String userid = cursorUser.getString(cursorUser.getColumnIndex(DBHelper.USERID));
                                    String r_userId = cursorRUserId.getString(cursorRUserId.getColumnIndex(DBHelper.RUSERID));
                                    String username = cursorUsername.getString(cursorUsername.getColumnIndex(DBHelper.USERNAME));
                                    String usersurname = cursorUsersurname.getString(cursorUsersurname.getColumnIndex(DBHelper.USERSURNAME));
                                    String userpassword = cursorUserpassword.getString(cursorUserpassword.getColumnIndex(DBHelper.USERPASSWORD));
                                    String useremail = cursorUserEmail.getString(cursorUserEmail.getColumnIndex(DBHelper.USEREMAIL));
                                    String main_image = cursorMainImage.getString(cursorMainImage.getColumnIndex(DBHelper.MAINIMAGE));
                                    String departmentname = cursorDepartment.getString(cursorDepartment.getColumnIndex(DBHelper.DEPARTMENTNAME));
                                    String datestamp = cursorDate.getString(cursorDate.getColumnIndex(DBHelper.ADDEDTIME));
                                    String couplingsize = cursorCoupling.getString(cursorCoupling.getColumnIndex(DBHelper.COUPLINGSIZE));
                                    String couplingtype = cursorCoupling.getString(cursorCoupling.getColumnIndex(DBHelper.COUPLINGTYPE));
                                    String couplinggap = cursorCoupling.getString(cursorCoupling.getColumnIndex(DBHelper.COUPLINGGAP));
                                    String couplingscrew = cursorCoupling.getString(cursorCoupling.getColumnIndex(DBHelper.COUPLINGSCREW));
                                    String couplingalign = cursorCoupling.getString(cursorCoupling.getColumnIndex(DBHelper.COUPLINGALIGN));
                                    String couplingguard = cursorCoupling.getString(cursorCoupling.getColumnIndex(DBHelper.COUPLINGGUARD));
                                    String couplingcomment = cursorCoupling.getString(cursorCoupling.getColumnIndex(DBHelper.COUPLINGCOMMENT));

                                    String posnum = cursorCoupling.getString(cursorCoupling.getColumnIndex(DBHelper.POSNUM));
                                    String plate = cursorCoupling.getString(cursorCoupling.getColumnIndex(DBHelper.PLATE));
                                    String avdoc = cursorCoupling.getString(cursorCoupling.getColumnIndex(DBHelper.AVDOC));
                                    String tds = cursorCoupling.getString(cursorCoupling.getColumnIndex(DBHelper.TDS));
                                    String curve = cursorCoupling.getString(cursorCoupling.getColumnIndex(DBHelper.CURVE));
                                    String secdraw = cursorCoupling.getString(cursorCoupling.getColumnIndex(DBHelper.SECDRAW));
                                    String spare = cursorCoupling.getString(cursorCoupling.getColumnIndex(DBHelper.SPARE));
                                    String dimdraw = cursorCoupling.getString(cursorCoupling.getColumnIndex(DBHelper.DIMDRAW));
                                    String manual = cursorCoupling.getString(cursorCoupling.getColumnIndex(DBHelper.MANUAL));
                                    String safety = cursorCoupling.getString(cursorCoupling.getColumnIndex(DBHelper.SAFETY));
                                    String certificate = cursorCoupling.getString(cursorCoupling.getColumnIndex(DBHelper.CERTIFICATE));

                                    String genpipe = cursorCoupling.getString(cursorCoupling.getColumnIndex(DBHelper.GENPIPE));
                                    String bends = cursorCoupling.getString(cursorCoupling.getColumnIndex(DBHelper.BENDS));
                                    String inletpipe = cursorCoupling.getString(cursorCoupling.getColumnIndex(DBHelper.INLETPIPE));
                                    String locpipe = cursorCoupling.getString(cursorCoupling.getColumnIndex(DBHelper.LOCPIPE));
                                    String supportpipe = cursorCoupling.getString(cursorCoupling.getColumnIndex(DBHelper.SUPPORTPIPE));
                                    String instalpipe = cursorCoupling.getString(cursorCoupling.getColumnIndex(DBHelper.INSTALPIPE));
                                    String pipecomment = cursorCoupling.getString(cursorCoupling.getColumnIndex(DBHelper.PIPECOMMENT));

                                    String execution = cursorCoupling.getString(cursorCoupling.getColumnIndex(DBHelper.EXECUTION));
                                    String grouting = cursorCoupling.getString(cursorCoupling.getColumnIndex(DBHelper.GROUTING));
                                    String motorstand = cursorCoupling.getString(cursorCoupling.getColumnIndex(DBHelper.MOTORSTAND));
                                    String liftarm = cursorCoupling.getString(cursorCoupling.getColumnIndex(DBHelper.LIFTARM));
                                    String foundationcomment = cursorCoupling.getString(cursorCoupling.getColumnIndex(DBHelper.FOUNDATIONCOMMENT));

                                    String fasteners = cursorCoupling.getString(cursorCoupling.getColumnIndex(DBHelper.FASTENERS));
                                    String flanges = cursorCoupling.getString(cursorCoupling.getColumnIndex(DBHelper.FLANGES));
                                    String gaskets = cursorCoupling.getString(cursorCoupling.getColumnIndex(DBHelper.GASKETS));
                                    String flangescomments = cursorCoupling.getString(cursorCoupling.getColumnIndex(DBHelper.FLANGESCOMMENTS));

                                    String typeseal = cursorCoupling.getString(cursorCoupling.getColumnIndex(DBHelper.TYPESEAL));
                                    String swjoints = cursorCoupling.getString(cursorCoupling.getColumnIndex(DBHelper.SWJOINTS));
                                    String swsettings = cursorCoupling.getString(cursorCoupling.getColumnIndex(DBHelper.SWSETTINGS));
                                    String swcontrolunit = cursorCoupling.getString(cursorCoupling.getColumnIndex(DBHelper.SWCONTROLUNIT));
                                    String sealcomments = cursorCoupling.getString(cursorCoupling.getColumnIndex(DBHelper.SEALCOMMENTS));
                                    String typelubrication = cursorCoupling.getString(cursorCoupling.getColumnIndex(DBHelper.TYPELUBRICATION));
                                    String levellubrication = cursorCoupling.getString(cursorCoupling.getColumnIndex(DBHelper.LEVELLUBRICATION));
                                    String commentlubrication = cursorCoupling.getString(cursorCoupling.getColumnIndex(DBHelper.COMMENTLUBRICATION));

                                    String installmotor = cursorCoupling.getString(cursorCoupling.getColumnIndex(DBHelper.INSTALLMOTOR));
                                    String bracketsmotor = cursorCoupling.getString(cursorCoupling.getColumnIndex(DBHelper.BRACKETSMOTOR));
                                    String shimsmotor = cursorCoupling.getString(cursorCoupling.getColumnIndex(DBHelper.SHIMSMOTOR));
                                    String commentmotor = cursorCoupling.getString(cursorCoupling.getColumnIndex(DBHelper.COMMENTMOTOR));

                                    String painting = cursorCoupling.getString(cursorCoupling.getColumnIndex(DBHelper.PAINTING));
                                    String gengrouting = cursorCoupling.getString(cursorCoupling.getColumnIndex(DBHelper.GENGROUTING));
                                    String damages = cursorCoupling.getString(cursorCoupling.getColumnIndex(DBHelper.DAMAGES));
                                    String rotation = cursorCoupling.getString(cursorCoupling.getColumnIndex(DBHelper.ROTATION));
                                    String commentcond = cursorCoupling.getString(cursorCoupling.getColumnIndex(DBHelper.COMMENTCOND));

                                    Cursor cursorCustomer = dbHelper.getCustomer(departmentname);
                                    String custName = "";
                                    while (cursorCustomer.moveToNext()){
                                        String cust = cursorCustomer.getString(cursorCustomer.getColumnIndex(DBHelper.CUSTOMERNAME));
                                        custName = cust;
                                    }
                                    String customername = custName;
                                    Cursor cursorPumpDescription = dbHelper.getPumpDescription(pump);
                                    String pumppositio = "";
                                    String bearingsiz = "";
                                    String discharg = "";
                                    while (cursorPumpDescription.moveToNext()){
                                        String position = cursorPumpDescription.getString(cursorPumpDescription.getColumnIndex(DBHelper.PUMPPOSITION));
                                        String size = cursorPumpDescription.getString(cursorPumpDescription.getColumnIndex(DBHelper.SIZEBU));
                                        String dis = cursorPumpDescription.getString(cursorPumpDescription.getColumnIndex(DBHelper.DISCHARGE));
                                        pumppositio = position;
                                        bearingsiz = size;
                                        discharg = dis;
                                    }
                                    String pumpposition = pumppositio;
                                    String bearingsize = bearingsiz;
                                    String discharge = discharg;

                                    try {
                                        bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), Uri.parse(main_image));
                                    } catch (IOException e) {

                                        e.printStackTrace();
                                    }
                                    saveToAppServer(id, pump, r_userId, userid, username, usersurname, userpassword, useremail, customername,
                                            departmentname, datestamp, couplingsize, couplingtype, couplinggap, couplingscrew, couplingalign, couplingguard, couplingcomment,
                                            pumpposition, bearingsize, discharge, bitmap, posnum, plate, avdoc, tds, curve, secdraw, spare, dimdraw, manual, safety, certificate, genpipe,
                                            bends, inletpipe,locpipe, supportpipe, instalpipe, pipecomment, execution, grouting, motorstand, liftarm, foundationcomment, fasteners,
                                            flanges, gaskets, flangescomments, typeseal, swjoints, swsettings, swcontrolunit, sealcomments, typelubrication, levellubrication, commentlubrication,
                                            installmotor, bracketsmotor, shimsmotor, commentmotor, painting, gengrouting, damages, rotation, commentcond);
                                }

                            //}
                            //else
                            //{

                                Toast.makeText(SendReportActivity.this, R.string.data_saved, Toast.LENGTH_LONG).show();

                           // }
                            /*Cursor cursorNum = dbHelper.getPumpNumber(id);
                            Cursor cursorUser = dbHelper.getUserIdReport(id);
                            Cursor cursorRUserId = dbHelper.getRUserId(id);
                            Cursor cursorUsername = dbHelper.getUsername(id);
                            Cursor cursorUsersurname = dbHelper.getUsersurname(id);
                            Cursor cursorUserpassword = dbHelper.getUserPassword(id);
                            Cursor cursorUserEmail = dbHelper.getUserMail(id);
                            Cursor cursorMainImage = dbHelper.getMainImage(id);
                            Cursor cursorDepartment = dbHelper.getDepartment(id);
                            Cursor cursorDate = dbHelper.getDate(id);
                            Cursor cursorCoupling = dbHelper.getCoupling(id);

                            while (cursorNum.moveToNext()&&cursorUser.moveToNext()&&cursorRUserId.moveToNext()&&cursorUsername.moveToNext()&&cursorUsersurname.moveToNext()&&cursorMainImage.moveToNext()
                            &&cursorUserpassword.moveToNext()&&cursorUserEmail.moveToNext()&&cursorDepartment.moveToNext()&&cursorDate.moveToNext()&&cursorCoupling.moveToNext())
                            {
                                String pump = cursorNum.getString(cursorNum.getColumnIndex(DBHelper.PUMPNUMBER));
                                String userid = cursorUser.getString(cursorUser.getColumnIndex(DBHelper.USERID));
                                String r_userId = cursorRUserId.getString(cursorRUserId.getColumnIndex(DBHelper.RUSERID));
                                String username = cursorUsername.getString(cursorUsername.getColumnIndex(DBHelper.USERNAME));
                                String usersurname = cursorUsersurname.getString(cursorUsersurname.getColumnIndex(DBHelper.USERSURNAME));
                                String userpassword = cursorUserpassword.getString(cursorUserpassword.getColumnIndex(DBHelper.USERPASSWORD));
                                String useremail = cursorUserEmail.getString(cursorUserEmail.getColumnIndex(DBHelper.USEREMAIL));
                                String main_image = cursorMainImage.getString(cursorMainImage.getColumnIndex(DBHelper.MAINIMAGE));
                                String departmentname = cursorDepartment.getString(cursorDepartment.getColumnIndex(DBHelper.DEPARTMENTNAME));
                                String datestamp = cursorDate.getString(cursorDate.getColumnIndex(DBHelper.ADDEDTIME));
                                String couplingsize = cursorCoupling.getString(cursorCoupling.getColumnIndex(DBHelper.COUPLINGSIZE));
                                String couplingtype = cursorCoupling.getString(cursorCoupling.getColumnIndex(DBHelper.COUPLINGTYPE));
                                String couplinggap = cursorCoupling.getString(cursorCoupling.getColumnIndex(DBHelper.COUPLINGGAP));
                                String couplingscrew = cursorCoupling.getString(cursorCoupling.getColumnIndex(DBHelper.COUPLINGSCREW));
                                String couplingalign = cursorCoupling.getString(cursorCoupling.getColumnIndex(DBHelper.COUPLINGALIGN));
                                String couplingguard = cursorCoupling.getString(cursorCoupling.getColumnIndex(DBHelper.COUPLINGGUARD));
                                String couplingcomment = cursorCoupling.getString(cursorCoupling.getColumnIndex(DBHelper.COUPLINGCOMMENT));
                                Cursor cursorCustomer = dbHelper.getCustomer(departmentname);
                                String custName = "";
                                while (cursorCustomer.moveToNext()){
                                    String cust = cursorCustomer.getString(cursorCustomer.getColumnIndex(DBHelper.CUSTOMERNAME));
                                    custName = cust;
                                }
                                String customername = custName;
                                Cursor cursorPumpDescription = dbHelper.getPumpDescription(pump);
                                String pumppositio = "";
                                String bearingsiz = "";
                                String discharg = "";
                                while (cursorPumpDescription.moveToNext()){
                                    String position = cursorPumpDescription.getString(cursorPumpDescription.getColumnIndex(DBHelper.PUMPPOSITION));
                                    String size = cursorPumpDescription.getString(cursorPumpDescription.getColumnIndex(DBHelper.SIZEBU));
                                    String dis = cursorPumpDescription.getString(cursorPumpDescription.getColumnIndex(DBHelper.DISCHARGE));
                                    pumppositio = position;
                                    bearingsiz = size;
                                    discharg = dis;
                                }
                                String pumpposition = pumppositio;
                                String bearingsize = bearingsiz;
                                String discharge = discharg;

                                try {
                                    bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), Uri.parse(main_image));
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                saveToAppServer(id, pump, r_userId, userid, username, usersurname, userpassword, useremail, customername,
                                        departmentname, datestamp, couplingsize, couplingtype, couplinggap, couplingscrew, couplingalign, couplingguard, couplingcomment,
                                        pumpposition, bearingsize, discharge, bitmap);
                            }*/

                        }
                        break;

                    case R.id.btnToStart:

                        Intent intent = new Intent(SendReportActivity.this, SelectReportActivity.class);
                        startActivity(intent);
                        overridePendingTransition(R.anim.left_out, R.anim.alpha);
                        finish();

                        break;

                }
            }
        };

        btnSend.setOnClickListener(onClickListener);
        btnToStart.setOnClickListener(onClickListener);




        broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {

                readFromLocalStorage();

            }
        };

    }

    private void submitPump(View view){

        DBHelper dbHelper = new DBHelper(this);
        SQLiteDatabase database = dbHelper.getReadableDatabase();
        Cursor cursor = dbHelper.readFromLocalDatabase(database);
        String pump = cursor.getString(cursor.getColumnIndex(DBHelper.REPORTID));
        //saveToAppServer(pump);

    }

    private void readFromLocalStorage (){

        arrayList.clear();

        DBHelper dbHelper = new DBHelper(this);
        SQLiteDatabase database = dbHelper.getReadableDatabase();

        Cursor cursor = dbHelper.readFromLocalDatabase(database);

        while (cursor.moveToNext())
        {
            String pump = cursor.getString(cursor.getColumnIndex(DBHelper.REPORTID));
            Cursor cursorNum = dbHelper.getPumpNumber(pump);
            while (cursorNum.moveToNext())
            {
                String pumpNum = cursorNum.getString(cursorNum.getColumnIndex(DBHelper.PUMPNUMBER));
                int sync_status = cursor.getInt(cursor.getColumnIndex(DBHelper.SYNC_STATUS));
                arrayList.add(new Report(pumpNum, sync_status));
                Collections.reverse(arrayList);

            }

        }

        adapter.notifyDataSetChanged();
        cursor.close();
        dbHelper.close();

    }

    private void saveToAppServer(final String id, final String pump, final String r_userId, final String userid, final String username, final String usersurname,
                                 final String userpassword, final String useremail, final String customername, final String departmentname, final String datestamp,
                                 final String couplingsize, final String couplingtype, final String couplinggap, final String couplingscrew, final String couplingalign,
                                 final String couplingguard, final String couplingcomment, final String pumpposition, final String bearingsize, final String discharge, final Bitmap bitmap,
                                 final String posnum, final String plate, final String avdoc, final String tds, final String curve, final String secdraw, final String spare, final String dimdraw,
                                 final String manual, final String safety, final String certificate, final String genpipe, final String bends, final String inletpipe, final String locpipe,
                                 final String supportpipe, final String instalpipe, final String pipecomment, final String execution, final String grouting, final String motorstand, final String liftarm,
                                 final String foundationcomment, final String fasteners, final String flanges, final String gaskets, final String flangescomments, final String typeseal, final String swjoints,
                                 final String swsettings, final String swcontrolunit, final String sealcomments, final String typelubrication, final String levellubrication, final String commentlubrication,
                                 final String installmotor, final String bracketsmotor, final String shimsmotor, final String commentmotor, final String painting, final String gengrouting,
                                 final String damages, final String rotation, final String commentcond)
    {
        if (checkNetworkConnection())
        {

            StringRequest stringRequest = new StringRequest(Request.Method.POST, DBHelper.SERVER_URL, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {

                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        String Response = jsonObject.getString("response");

                        if (Response.equals("OK"))
                        {

                            saveToLocalStorage(id, DBHelper.SYNC_STATUS_OK);

                        }

                        else
                        {
                            //saveToLocalStorage(id, DBHelper.SYNC_STATUS_FAILED);
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                    //saveToLocalStorage(id, DBHelper.SYNC_STATUS_FAILED);

                }
            })
            {
                @Override
                protected Map<String,String> getParams() throws AuthFailureError{
                    Map<String, String> params = new HashMap<>();
                    params.put("id", id);
                    params.put("pump", pump);
                    params.put("userid", userid);
                    params.put("r_userId", r_userId);
                    params.put("username", username);
                    params.put("usersurname", usersurname);
                    params.put("userpassword", userpassword);
                    params.put("useremail", useremail);
                    params.put("customername", customername);
                    params.put("departmentname", departmentname);
                    params.put("datestamp", datestamp);
                    params.put("couplingsize", couplingsize);
                    params.put("couplingtype", couplingtype);
                    params.put("couplinggap", couplinggap);
                    params.put("couplingscrew", couplingscrew);
                    params.put("couplingalign", couplingalign);
                    params.put("couplingguard", couplingguard);
                    params.put("couplingcomment", couplingcomment);
                    params.put("pumpposition", pumpposition);
                    params.put("bearingsize", bearingsize);
                    params.put("discharge", discharge);
                    params.put("main_image", imageToString(bitmap));
                    params.put("posnum", posnum);
                    params.put("plate", plate);
                    params.put("avdoc", avdoc);
                    params.put("tds", tds);
                    params.put("curve", curve);
                    params.put("secdraw", secdraw);
                    params.put("spare", spare);
                    params.put("dimdraw", dimdraw);
                    params.put("manual", manual);
                    params.put("safety", safety);
                    params.put("certificate", certificate);
                    params.put("genpipe", genpipe);
                    params.put("bends", bends);
                    params.put("inletpipe", inletpipe);
                    params.put("locpipe", locpipe);
                    params.put("supportpipe", supportpipe);
                    params.put("instalpipe", instalpipe);
                    params.put("pipecomment", pipecomment);
                    params.put("execution", execution);
                    params.put("grouting", grouting);
                    params.put("motorstand", motorstand);
                    params.put("liftarm", liftarm);
                    params.put("foundationcomment",foundationcomment);
                    params.put("fasteners", fasteners);
                    params.put("flanges", flanges);
                    params.put("gaskets", gaskets);
                    params.put("flangescomments", flangescomments);
                    params.put("typeseal", typeseal);
                    params.put("swjoints", swjoints);
                    params.put("swsettings", swsettings);
                    params.put("swcontrolunit", swcontrolunit);
                    params.put("sealcomments", sealcomments);
                    params.put("typelubrication",typelubrication);
                    params.put("levellubrication", levellubrication);
                    params.put("commentlubrication", commentlubrication);
                    params.put("installmotor", installmotor);
                    params.put("bracketsmotor", bracketsmotor);
                    params.put("shimsmotor", shimsmotor);
                    params.put("commentmotor", commentmotor);
                    params.put("painting", painting);
                    params.put("gengrouting", gengrouting);
                    params.put("damages", damages);
                    params.put("rotation", rotation);
                    params.put("commentcond", commentcond);
                    return params;
                }
            }

                    ;

            MySingleton.getInstance(SendReportActivity.this).addToRequestQue(stringRequest);

        }
        else
        {

            saveToLocalStorage(id, DBHelper.SYNC_STATUS_FAILED);

        }
    }

    public boolean checkNetworkConnection(){

        ConnectivityManager connectivityManager = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return (networkInfo!=null && networkInfo.isConnected());

    }

    private void saveToLocalStorage(String pump, int sync){

        DBHelper dbHelper = new DBHelper(this);
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        dbHelper.insertSyncStatus(pump, sync);
        readFromLocalStorage();
        dbHelper.close();

    }

    @Override
    protected void onStart() {
        super.onStart();
        registerReceiver(broadcastReceiver, new IntentFilter(DBHelper.UI_UPDATE_BROADCAST));
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(broadcastReceiver);
    }

    private String imageToString(Bitmap bitmap){

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 50, byteArrayOutputStream);
        byte[] imgBytes = byteArrayOutputStream.toByteArray();
        return Base64.encodeToString(imgBytes, Base64.DEFAULT);

    }

}

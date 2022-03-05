package com.gavriden.sulzerservice;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.strictmode.SqliteObjectLeakedViolation;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class NetworkMonitor extends BroadcastReceiver {
    @Override
    public void onReceive(final Context context, Intent intent) {

        if (checkNetworkConnection(context))
        {
            final DBHelper dbHelper = new DBHelper(context);
            SQLiteDatabase database = dbHelper.getWritableDatabase();

            Cursor cursor = dbHelper.readFromLocalDatabase(database);

            while (cursor.moveToNext())
            {
                int sync_status = cursor.getInt(cursor.getColumnIndex(DBHelper.SYNC_STATUS));

                if (sync_status==DBHelper.SYNC_STATUS_FAILED)
                {
                    final String Id = cursor.getString(cursor.getColumnIndex(DBHelper.REPORTID));
                    Cursor cursorNum = dbHelper.getPumpNumber(Id);
                    String noPump = "";
                    while (cursorNum.moveToNext())
                    {

                        String pump = cursorNum.getString(cursorNum.getColumnIndex(DBHelper.PUMPNUMBER));
                        noPump = pump;

                    }
                    final String Pump = noPump;
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, DBHelper.SERVER_URL,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {

                                    try {
                                        JSONObject jsonObject = new JSONObject(response);
                                        String Response = jsonObject.getString("response");
                                        if (Response.equals("OK"))
                                        {
                                            dbHelper.insertSyncStatus(Id, DBHelper.SYNC_STATUS_OK);
                                            context.sendBroadcast(new Intent(DBHelper.UI_UPDATE_BROADCAST));
                                        }

                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }

                                }
                            }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }
                    })
                    {
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String, String> params = new HashMap<>();
                            params.put("id", Id);
                            params.put("pump", Pump);
                            return params;
                        }
                    }

                            ;

                    MySingleton.getInstance(context).addToRequestQue(stringRequest);
                }

            }

            dbHelper.close();

        }

    }

    public boolean checkNetworkConnection(Context context){

        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return (networkInfo!=null && networkInfo.isConnected());

    }
}

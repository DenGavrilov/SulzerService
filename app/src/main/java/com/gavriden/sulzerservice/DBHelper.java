package com.gavriden.sulzerservice;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DBNAME = "Ahlstars.db";
    public static final String TABLENAME = "users";
    public static final String TABLENAME2 = "pumps";
    public static final String TABLENAME3 = "customers";
    public static final String TABLENAME4 = "departments";

    public static final String TABLEDIS = "dischargesize";
    public static final String USERNAME = "username";
    public static final String USERSURNAME = "usersurname";
    public static final String SIZEBU = "bearingsize";
    public static final String DISCHARGE = "discharge";
    public static final String POSITION = "pumpposition";
    public static final String CUSTOMERID  = "customerId";
    public static final String CUSTOMERDEPID = "customerdepId";
    public static final String CUSTOMERS = "customers";
    public static final String DEPARTMENTS = "departments";
    public static final String DEPARTMENTNAME = "departmentname";
    public static final String USERID = "userId";
    public static final String PUMPID = "pumpId";
    public static final String DEPARTMENTID = "departmentId";
    public static final String REPORTID = "reportId";
    public static final String USEREMAIL = "useremail";
    public static final String RPUMPID = "r_pumpId";
    public static final String SYNC_STATUS = "syncstatus";
    public static final String REPORTS = "reports";
    public static final String PUMPNUMBER = "pumpnumber";
    public static final String PUMPPOSITION = "pumpposition";
    public static final String RUSERID = "r_userId";
    public static final String MAINIMAGE = "mainImage";
    public static final String USERPASSWORD = "userpassword";
    public static final String CUSTOMERNAME = "customername";
    public static final String ADDEDTIME = "addedTime";
    public static final String COUPLINGSIZE = "couplingsize";
    public static final String COUPLINGTYPE = "couplingtype";
    public static final String COUPLINGGAP = "couplinggap";
    public static final String COUPLINGSCREW = "couplingscrew";
    public static final String COUPLINGALIGN = "couplingalign";
    public static final String COUPLINGGUARD = "couplingguard";
    public static final String COUPLINGCOMMENT = "couplingcomment";
    public static final String POSNUM = "posnum";
    public static final String PLATE = "plate";
    public static final String AVDOC = "avdoc";
    public static final String TDS = "tds";
    public static final String CURVE = "curve";
    public static final String SECDRAW = "secdraw";
    public static final String SPARE = "spare";
    public static final String DIMDRAW = "dimdraw";
    public static final String MANUAL = "manual";
    public static final String SAFETY = "safety";
    public static final String CERTIFICATE = "certificate";
    public static final String GENPIPE = "genpipe";
    public static final String BENDS = "bends";
    public static final String INLETPIPE = "inletpipe";
    public static final String LOCPIPE = "locpipe";
    public static final String SUPPORTPIPE = "supportpipe";
    public static final String INSTALPIPE = "instalpipe";
    public static final String PIPECOMMENT = "pipecomment";
    public static final String EXECUTION = "execution";
    public static final String GROUTING = "grouting";
    public static final String MOTORSTAND = "motorstand";
    public static final String LIFTARM = "liftarm";
    public static final String FOUNDATIONCOMMENT = "foundationcomment";
    public static final String FASTENERS = "fasteners";
    public static final String FLANGES = "flanges";
    public static final String GASKETS = "gaskets";
    public static final String FLANGESCOMMENTS = "flangescomments";
    public static final String TYPESEAL = "typeseal";
    public static final String SWJOINTS = "swjoints";
    public static final String SWSETTINGS = "swsettings";
    public static final String SWCONTROLUNIT = "swcontrolunit";
    public static final String SEALCOMMENTS = "sealcomments";
    public static final String TYPELUBRICATION = "typelubrication";
    public static final String LEVELLUBRICATION = "levellubrication";
    public static final String COMMENTLUBRICATION = "commentlubrication";
    public static final String INSTALLMOTOR = "installmotor";
    public static final String BRACKETSMOTOR = "bracketsmotor";
    public static final String SHIMSMOTOR = "shimsmotor";
    public static final String COMMENTMOTOR = "commentmotor";
    public static final String PAINTING ="painting";
    public static final String GENGROUTING = "gengrouting";
    public static final String DAMAGES = "damages";
    public static final String ROTATION = "rotation";
    public static final String COMMENTCOND = "commentcond";


    public static final int SYNC_STATUS_OK = 0;
    public static final int SYNC_STATUS_FAILED = 1;
    public static final String NOT_DATA ="";
    public static final String EMPTY = "";

    public static final String SERVER_URL = "https://verseauservice.ru/connectAhlstar.php";
    public static final String UI_UPDATE_BROADCAST = "com.gavriden.sulzerservice.uiupdatebroadcast";

    public DBHelper(Context context) {
        super(context, "Ahlstars.db", null, 48);
    }

    @Override
    public void onCreate(SQLiteDatabase MyDB) {

        MyDB.execSQL("create Table users (userId integer primary key autoincrement, username TEXT, usersurname TEXT, userpassword TEXT, useremail TEXT) ");
        MyDB.execSQL("create Table pumps (pumpId integer primary key autoincrement, pumpnumber TEXT, pumpposition TEXT, bearingsize TEXT, discharge TEXT)");
        MyDB.execSQL("create Table customers (customerId integer primary key autoincrement, customername TEXT)");
        MyDB.execSQL("create Table departments (departmentId integer primary key autoincrement, departmentname TEXT, customer_id integer, foreign key(customer_id) references customers(customerId))");
        MyDB.execSQL("create Table reports (reportId integer primary key autoincrement, mainImage TEXT, addedTime TEXT, r_userId integer, r_pumpId integer, r_departmentId integer, couplingsize TEXT," +
                "couplingtype TEXT, couplinggap TEXT, couplingscrew TEXT, couplingalign TEXT, couplingguard TEXT, couplingcomment TEXT, couplingImage TEXT," +
                "posnum TEXT, plate TEXT, avdoc TEXT, tds TEXT, curve TEXT, secdraw TEXT, spare TEXT, dimdraw TEXT, manual TEXT, safety TEXT, certificate TEXT, " +
                "genpipe TEXT, bends TEXT, inletpipe TEXT, locpipe TEXT, supportpipe TEXT, instalpipe TEXT, pipecomment TEXT, pipeImage TEXT, " +
                "execution TEXT, grouting TEXT, motorstand TEXT, liftarm TEXT, foundationcomment TEXT, foundationImage TEXT, " +
                "fasteners TEXT, flanges TEXT, gaskets TEXT, flangescomments TEXT, flangesImage TEXT," +
                "typeseal TEXT, swjoints TEXT, swsettings TEXT, swcontrolunit TEXT, sealcomments TEXT, sealImage TEXT," +
                "typelubrication TEXT, levellubrication TEXT, commentlubrication TEXT, lubricationImage TEXT," +
                "installmotor TEXT, bracketsmotor TEXT, shimsmotor TEXT, commentmotor TEXT, motorImage TEXT," +
                "painting TEXT, gengrouting TEXT, damages TEXT, rotation TEXT, commentcond TEXT, conditionImage TEXT," +
                "syncstatus integer," +
                "foreign key(r_userId) references users(userId), foreign key(r_pumpId) references pumps(pumpId), foreign key(r_departmentId) references departments(departmentId))");
    }


    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int oldVersion, int newVersion) {

        MyDB.execSQL("drop Table if exists users");
        MyDB.execSQL("drop Table if exists pumps");
        MyDB.execSQL("drop Table if exists customers");
        MyDB.execSQL("drop Table if exists departments");
        MyDB.execSQL("drop Table if exists reports");
        onCreate(MyDB);

    }

    public Boolean insertData(String username, String usersurname, String userpassword, String useremail){

        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        contentValues.put("usersurname", usersurname);
        contentValues.put("userpassword", userpassword);
        contentValues.put("useremail", useremail);

        long results = MyDB.insert("users", null, contentValues);
        if (results==-1) return false;
        else
            return true;

    }

    public Boolean insertPump(String pumpnumber, String pumpposition, String bearingsize, String discharge){

        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("pumpnumber", pumpnumber);
        contentValues.put("pumpposition", pumpposition);
        contentValues.put("bearingsize", bearingsize);
        contentValues.put("discharge", discharge);

        long results = MyDB.insert("pumps", null, contentValues);
        if (results==-1) return false;
        else
            return true;

    }

    public Boolean insertCustomer(String customername){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("customername", customername);

        long results = MyDB.insert("customers", null, contentValues);
        if (results==-1) return false;
        else
            return true;
    }

    public Boolean insertDepartment(String departmentname, String customer_id){

        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("departmentname", departmentname);
        contentValues.put("customer_id", customer_id);

        long results = MyDB.insert("departments", null, contentValues);
        if (results==-1) return false;
        else
            return true;

    }

    public Boolean insertReportData(Uri mainImage, String addedTime, String r_userId, String r_pumpId, String r_departmentId,
                                    String posnum, String plate, String avdoc, String tds, String curve, String secdraw,
                                    String spare, String dimdraw, String manual, String safety, String certificate,
                                    String reportId, String couplingsize, String couplingtype, String couplinggap, String couplingscrew,
                                    String couplingalign, String couplingguard, String couplingcomment, String genpipe, String bends,
                                    String inletpipe, String locpipe, String supportpipe, String instalpipe, String pipecomment,
                                    String execution, String grouting, String motorstand, String liftarm, String foundationcomment,
                                    String fasteners, String flanges, String gaskets, String flangescomments, String typeseal, String swjoints,
                                    String swsettings, String swcontrolunit, String sealcomments, String typelubrication, String levellubrication,
                                    String commentlubrication, String installmotor, String bracketsmotor, String shimsmotor, String commentmotor,
                                    String painting, String gengrouting, String damages, String rotation, String commentcond, int syncstatus){

        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("mainImage", String.valueOf(mainImage));
        contentValues.put("addedTime", addedTime);
        contentValues.put("r_userId", r_userId);
        contentValues.put("r_pumpId", r_pumpId);
        contentValues.put("r_departmentId", r_departmentId);

        contentValues.put("posnum", posnum);
        contentValues.put("plate", plate);
        contentValues.put("avdoc", avdoc);
        contentValues.put("tds", tds);
        contentValues.put("curve", curve);
        contentValues.put("secdraw", secdraw);
        contentValues.put("spare", spare);
        contentValues.put("dimdraw", dimdraw);
        contentValues.put("manual", manual);
        contentValues.put("safety", safety);
        contentValues.put("certificate", certificate);

        contentValues.put("couplingsize", couplingsize);
        contentValues.put("couplingtype", couplingtype);
        contentValues.put("couplinggap", couplinggap);
        contentValues.put("couplingscrew", couplingscrew);
        contentValues.put("couplingalign", couplingalign);
        contentValues.put("couplingguard", couplingguard);
        contentValues.put("couplingcomment", couplingcomment);

        contentValues.put("genpipe", genpipe);
        contentValues.put("bends", bends);
        contentValues.put("inletpipe", inletpipe);
        contentValues.put("locpipe", locpipe);
        contentValues.put("supportpipe", supportpipe);
        contentValues.put("instalpipe", instalpipe);
        contentValues.put("pipecomment", pipecomment);

        contentValues.put("execution", execution);
        contentValues.put("grouting", grouting);
        contentValues.put("motorstand", motorstand);
        contentValues.put("liftarm", liftarm);
        contentValues.put("foundationcomment", foundationcomment);

        contentValues.put("fasteners", fasteners);
        contentValues.put("flanges", flanges);
        contentValues.put("gaskets", gaskets);
        contentValues.put("flangescomments", flangescomments);

        contentValues.put("typeseal", typeseal);
        contentValues.put("swjoints", swjoints);
        contentValues.put("swsettings", swsettings);
        contentValues.put("swcontrolunit", swcontrolunit);
        contentValues.put("sealcomments", sealcomments);

        contentValues.put("typelubrication", typelubrication);
        contentValues.put("levellubrication", levellubrication);
        contentValues.put("commentlubrication", commentlubrication);

        contentValues.put("installmotor", installmotor);
        contentValues.put("bracketsmotor", bracketsmotor);
        contentValues.put("shimsmotor", shimsmotor);
        contentValues.put("commentmotor", commentmotor);

        contentValues.put("painting", painting);
        contentValues.put("gengrouting", gengrouting);
        contentValues.put("damages", damages);
        contentValues.put("rotation", rotation );
        contentValues.put("commentcond", commentcond);

        contentValues.put("syncstatus", syncstatus);


        long result = MyDB.insert("reports", null, contentValues);
        if (result==-1) return false;
        else
            return true;
    }

    public Boolean checkStatus(String reportId){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select syncstatus from reports where reportId = ? and syncstatus = 1", new String[] {reportId});
        if (cursor.getCount()>0)
            return true;
        else
            return false;
    }

    public Boolean insertCouplingReport(String reportId, String couplingsize, String couplingtype, String couplinggap, String couplingscrew, String couplingalign, String couplingguard, String couplingcomment, Uri couplingImage){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("couplingsize", couplingsize);
        contentValues.put("couplingtype", couplingtype);
        contentValues.put("couplinggap", couplinggap);
        contentValues.put("couplingscrew", couplingscrew);
        contentValues.put("couplingalign", couplingalign);
        contentValues.put("couplingguard", couplingguard);
        contentValues.put("couplingcomment", couplingcomment);
        contentValues.put("couplingImage", String.valueOf(couplingImage));

        long results = MyDB.update("reports", contentValues, "reportId = ?", new String[] {reportId});
        if (results==-1) return false;
        else
            return true;
    }

    public Boolean insertDocReport(String reportId, String posnum, String plate, String avdoc, String tds, String curve, String secdraw,
                                   String spare, String dimdraw, String manual, String safety, String certificate){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("posnum", posnum);
        contentValues.put("plate", plate);
        contentValues.put("avdoc", avdoc);
        contentValues.put("tds", tds);
        contentValues.put("curve", curve);
        contentValues.put("secdraw", secdraw);
        contentValues.put("spare", spare);
        contentValues.put("dimdraw", dimdraw);
        contentValues.put("manual", manual);
        contentValues.put("safety", safety);
        contentValues.put("certificate", certificate);

        long results = MyDB.update("reports", contentValues, "reportId = ?", new String[]{reportId});
        if (results==-1) return false;
        else
            return true;
    }

    public Boolean insertPipeReport(String reportId, String genpipe, String bends, String inletpipe, String locpipe, String supportpipe, String instalpipe, String pipecomment, Uri pipeImage){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("genpipe", genpipe);
        contentValues.put("bends", bends);
        contentValues.put("inletpipe", inletpipe);
        contentValues.put("locpipe", locpipe);
        contentValues.put("supportpipe", supportpipe);
        contentValues.put("instalpipe", instalpipe);
        contentValues.put("pipecomment", pipecomment);
        contentValues.put("pipeImage", String.valueOf(pipeImage));

        long results = MyDB.update("reports", contentValues, "reportId = ?", new String[] {reportId});
        if (results==-1) return false;
        else
            return true;
    }

    public Boolean insertFoundationReport(String reportId, String execution, String grouting, String motorstand, String liftarm, String foundationcomment, Uri foundationImage){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("execution", execution);
        contentValues.put("grouting", grouting);
        contentValues.put("motorstand", motorstand);
        contentValues.put("liftarm", liftarm);
        contentValues.put("foundationcomment", foundationcomment);
        contentValues.put("foundationImage", String.valueOf(foundationImage));

        long results = MyDB.update("reports", contentValues, "reportId = ?", new String[] {reportId});
        if (results==-1) return false;
        else
            return true;
    }

    public Boolean insertFlangesReport(String reportId, String fasteners, String flanges, String gaskets, String flangescomments, Uri flangesImage){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("fasteners", fasteners);
        contentValues.put("flanges", flanges);
        contentValues.put("gaskets", gaskets);
        contentValues.put("flangescomments", flangescomments);
        contentValues.put("flangesImage", String.valueOf(flangesImage));

        long results = MyDB.update("reports", contentValues, "reportId = ?", new String[] {reportId});
        if (results==-1) return false;
        else
            return true;
    }

    public Boolean insertSealReport(String reportId, String typeseal, String swjoints, String swsettings, String swcontrolunit, String sealcomments, Uri sealImage){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("typeseal", typeseal);
        contentValues.put("swjoints", swjoints);
        contentValues.put("swsettings", swsettings);
        contentValues.put("swcontrolunit", swcontrolunit);
        contentValues.put("sealcomments", sealcomments);
        contentValues.put("sealImage", String.valueOf(sealImage));

        long results = MyDB.update("reports", contentValues, "reportId = ?", new String[] {reportId});
        if (results==-1) return false;
        else
            return true;
    }

    public Boolean insertLubReport(String reportId, String typelubrication, String levellubrication, String commentlubrication, Uri lubricationImage){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("typelubrication", typelubrication);
        contentValues.put("levellubrication", levellubrication);
        contentValues.put("commentlubrication", commentlubrication);
        contentValues.put("sealImage", String.valueOf(lubricationImage));

        long results = MyDB.update("reports", contentValues, "reportId = ?", new String[] {reportId});
        if (results==-1) return false;
        else
            return true;
    }

    public Boolean insertMotorReport(String reportId, String installmotor, String bracketsmotor, String shimsmotor, String commentmotor, Uri motorImage){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("installmotor", installmotor);
        contentValues.put("bracketsmotor", bracketsmotor);
        contentValues.put("shimsmotor", shimsmotor);
        contentValues.put("commentmotor", commentmotor);
        contentValues.put("motorImage", String.valueOf(motorImage));

        long results = MyDB.update("reports", contentValues, "reportId = ?", new String[] {reportId});
        if (results==-1) return false;
        else
            return true;
    }

    public Boolean insertCondReport(String reportId, String painting, String gengrouting, String damages, String rotation, String commentcond, Uri conditionImage){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("painting", painting);
        contentValues.put("gengrouting", gengrouting);
        contentValues.put("damages", damages);
        contentValues.put("rotation", rotation);
        contentValues.put("commentcond", commentcond);
        contentValues.put("conditionImage", String.valueOf(conditionImage));

        long results = MyDB.update("reports", contentValues, "reportId = ?", new String[] {reportId});
        if (results==-1) return false;
        else
            return true;
    }


    public Boolean checkemail(String useremail){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from users where useremail = ?", new String[] {useremail});
        if (cursor.getCount()>0)
            return true;
        else
            return false;
    }

    public Boolean checknumber(String pumpnumber){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from pumps where pumpnumber = ?", new String[] {pumpnumber});
        if (cursor.getCount()>0)
            return true;
        else
            return false;
    }


    public Boolean checkemailpassword(String useremail, String userpassword){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from users where useremail = ? and userpassword = ?", new String[] {useremail, userpassword});
        if (cursor.getCount()>0)
            return true;
        else
            return false;
    }

    public int getCountFault(){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        String query = "Select count(*) from reports where syncstatus = 1";
        Cursor result = MyDB.rawQuery(query, null);
        result.moveToFirst();
        int count = result.getInt(0);
        return count;
    }

    public int getCountOk(){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        String query = "Select count(*) from reports where syncstatus = 0";
        Cursor result = MyDB.rawQuery(query, null);
        result.moveToFirst();
        int count = result.getInt(0);
        return count;
    }


    public Cursor getUser(){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        String query = "Select * from users";
        Cursor result = MyDB.rawQuery(query, null);
        return result;
    }

    public Cursor getName(String username){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor result = MyDB.rawQuery( "Select username from users where useremail = ?", new String[] {username});
        return result;
    }

    public Cursor getSurname(String usersurname){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor result2 = MyDB.rawQuery( "Select usersurname from users where useremail = ?", new String[] {usersurname});
        return result2;
    }

    public Cursor getBS(String bearingsize){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor resultBS = MyDB.rawQuery("Select bearingsize from pumps where pumpnumber = ?", new String[] {bearingsize});
        return resultBS;
    }

    public Cursor getDischarge(String discharge){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor resultDischarge = MyDB.rawQuery("Select discharge from pumps where pumpnumber = ?", new String[] {discharge});
        return resultDischarge;
    }

    public Cursor getPosition(String pumpposition){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor resultPosition = MyDB.rawQuery("Select pumpposition from pumps where pumpnumber = ?", new String[] {pumpposition});
        return resultPosition;
    }


    public Cursor getCustomerId(String customerId){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor resultId = MyDB.rawQuery("Select customerId from customers where customername = ?", new String[] {customerId});
        return resultId;
    }

    public Cursor getUserId(String userId){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor resultUserId = MyDB.rawQuery("Select userId from users where useremail = ?", new String[] {userId});
        return resultUserId;
    }

    public Cursor getPumpId(String pumpId){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor resultPumpId = MyDB.rawQuery("Select pumpId from pumps where pumpnumber = ?", new String[] {pumpId});
        return resultPumpId;
    }

    public Cursor getDepId(String departmentId){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor resultDepId = MyDB.rawQuery("Select departmentId from departments where departmentname = ?", new String[] {departmentId});
        return resultDepId;
    }

    public Cursor getReportId(String reportId){
       SQLiteDatabase MyDB = this.getWritableDatabase();
       Cursor resultReportId = MyDB.rawQuery("Select reportId from reports where r_pumpId = ?", new String[] {reportId});
       return resultReportId;
    }

    public Cursor getReportIdByPump(String pumpnumber){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor resultReport = MyDB.rawQuery("Select reports.reportId from reports, pumps where reports.r_pumpId = pumps.pumpId and pumps.pumpnumber = ?", new String[] {pumpnumber});
        return resultReport;
    }

    public Cursor getPumpReport(String reportId){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor resultReportId = MyDB.rawQuery("Select r_pumpId from reports where reportId = ?", new String[] {reportId});
        return resultReportId;
    }

    public List<String> getAllCustomers(){
        List<String> customers = new ArrayList<String>();
        String selectQuery = "SELECT * FROM " + CUSTOMERS;
        SQLiteDatabase MyDB = this.getReadableDatabase();
        Cursor cursor2 = MyDB.rawQuery(selectQuery, null);
        if(cursor2.moveToFirst()){
            do {
                customers.add(cursor2.getString(1));
            }while (cursor2.moveToNext());
        }
        cursor2.close();
        MyDB.close();
        return customers;
    }

    public List<String> getDepartments(String customerId){
        List<String> departments = new ArrayList<String>();
        SQLiteDatabase MyDB = this.getReadableDatabase();
        Cursor cursor3 = MyDB.rawQuery("Select * from departments where customer_id = ?", new String[] {customerId});
        if (cursor3.moveToFirst()){
            do {
                departments.add(cursor3.getString(1));
            }while (cursor3.moveToNext());
        }
        cursor3.close();
        MyDB.close();
        return departments;
    }

    public Cursor getEmail(String userId) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor resultUserId = MyDB.rawQuery("Select useremail from users where userId = ?", new String[] {userId});
        return resultUserId;
    }

    public Boolean checkcoupling(String reportValue) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select couplingsize, couplingtype, couplinggap, couplingscrew, couplingalign, couplingguard from reports where reportId = ? and (couplingsize = 'Ok' or couplingsize = 'NA') " +
                "and (couplingtype = 'Ok' or couplingtype = 'NA') " +
                "and (couplinggap = 'Ok' or couplinggap = 'NA') " +
                "and (couplingscrew = 'Ok' or couplingscrew = 'NA')" +
                "and (couplingalign = 'Ok' or couplingalign = 'NA')" +
                "and (couplingguard = 'Ok' or couplingguard = 'NA') ", new String[] {reportValue});
        if (cursor.getCount()>0)
            return true;
        else
            return false;
    }

    public Boolean checkYellowCoupling(String reportValue) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select couplingsize, couplingtype, couplinggap, couplingscrew, couplingalign, couplingguard from reports where reportId = ? " +
                "and (couplingsize != NULL or couplingsize != 'Problem') " +
                "and (couplingtype != NULL or couplingtype != 'Problem') " +
                "and (couplinggap != NULL or couplinggap != 'Problem') " +
                "and (couplingscrew != NULL or couplingscrew != 'Problem') " +
                "and (couplingalign != NULL or couplingalign != 'Problem') " +
                "and (couplingguard != NULL or couplingguard != 'Problem') " +
                "and (couplingsize != '-')" +
                "and (couplingtype != '-')" +
                "and (couplinggap != '-')" +
                "and (couplingscrew != '-')" +
                "and (couplingalign != '-')" +
                "and (couplingguard != '-')",new String[] {reportValue});
        if (cursor.getCount()>0)
            return true;
        else
            return false;
    }

    public Boolean checkdoc(String reportValue) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select posnum, plate, avdoc, tds, curve, secdraw, spare, dimdraw, manual, safety, certificate from reports where reportId = ? " +
                "and (posnum = 'Ok' or posnum = 'NA') " +
                "and (plate = 'Ok' or plate = 'NA') " +
                "and (avdoc = 'Ok' or avdoc = 'NA') " +
                "and (tds = 'Ok' or tds = 'NA')" +
                "and (curve = 'Ok' or curve = 'NA')" +
                "and (secdraw = 'Ok' or secdraw = 'NA')" +
                "and (spare = 'Ok' or spare = 'NA')" +
                "and (dimdraw = 'Ok' or dimdraw = 'NA')" +
                "and (manual = 'Ok' or manual = 'NA')" +
                "and (safety = 'Ok' or safety = 'NA')" +
                "and (certificate = 'Ok' or certificate = 'NA')", new String[] {reportValue});
        if (cursor.getCount()>0)
            return true;
        else
            return false;
    }

    public Boolean checkYellowDoc(String reportValue) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select posnum, plate, avdoc, tds, curve, secdraw, spare, dimdraw, manual, safety, certificate from reports where reportId = ? " +
                "and (posnum != NULL or posnum != 'Problem') " +
                "and (plate != NULL or plate != 'Problem') " +
                "and (avdoc != NULL or avdoc != 'Problem') " +
                "and (tds != NULL or tds != 'Problem')" +
                "and (curve != NULL or curve != 'Problem')" +
                "and (secdraw != NULL or secdraw != 'Problem')" +
                "and (spare != NULL or spare != 'Problem')" +
                "and (dimdraw != NULL or dimdraw != 'Problem')" +
                "and (manual != NULL or manual != 'Problem')" +
                "and (safety != NULL or safety != 'Problem')" +
                "and (certificate != NULL or certificate != 'Problem')"+
                "and (posnum != '-') " +
                "and (plate != '-') " +
                "and (avdoc != '-') " +
                "and (tds != '-')" +
                "and (curve != '-')" +
                "and (secdraw != '-')" +
                "and (spare != '-')" +
                "and (dimdraw != '-')" +
                "and (manual != '-')" +
                "and (safety != '-')" +
                "and (certificate != '-')", new String[] {reportValue});
        if (cursor.getCount()>0)
            return true;
        else
            return false;
    }

    public Boolean checkpipe(String reportValue) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select genpipe, bends, inletpipe, locpipe, supportpipe, instalpipe from reports where reportId = ? " +
                "and (genpipe = 'Ok' or genpipe = 'NA') " +
                "and (bends = 'Ok' or bends = 'NA') " +
                "and (inletpipe = 'Ok' or inletpipe = 'NA') " +
                "and (locpipe = 'Ok' or locpipe = 'NA')" +
                "and (supportpipe = 'Ok' or supportpipe = 'NA')" +
                "and (instalpipe = 'Ok' or instalpipe = 'NA') ", new String[] {reportValue});
        if (cursor.getCount()>0)
            return true;
        else
            return false;
    }

    public Boolean checkYellowPipe(String reportValue) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select genpipe, bends, inletpipe, locpipe, supportpipe, instalpipe from reports where reportId = ? " +
                "and (genpipe != NULL or genpipe != 'Problem') " +
                "and (bends != NULL or bends != 'Problem') " +
                "and (inletpipe != NULL or inletpipe != 'Problem') " +
                "and (locpipe != NULL or locpipe != 'Problem')" +
                "and (supportpipe != NULL or supportpipe != 'Problem')" +
                "and (instalpipe != NULL or instalpipe != 'Problem') "+
                "and (genpipe != '-') " +
                "and (bends != '-') " +
                "and (inletpipe != '-') " +
                "and (locpipe != '-')" +
                "and (supportpipe != '-')" +
                "and (instalpipe != '-') ", new String[] {reportValue});
        if (cursor.getCount()>0)
            return true;
        else
            return false;
    }

    public Boolean checkfoundation(String reportValue) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select execution, grouting, motorstand, liftarm from reports where reportId = ? " +
                "and (execution = 'Ok' or execution = 'NA') " +
                "and (grouting = 'Ok' or grouting = 'NA') " +
                "and (motorstand = 'Ok' or motorstand = 'NA') " +
                "and (liftarm = 'Ok' or liftarm = 'NA') ", new String[] {reportValue});
        if (cursor.getCount()>0)
            return true;
        else
            return false;
    }

    public Boolean checkYellowfoundation(String reportValue) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select execution, grouting, motorstand, liftarm from reports where reportId = ? " +
                "and (execution != NULL or execution != 'Problem') " +
                "and (grouting != NULL or grouting != 'Problem') " +
                "and (motorstand != NULL or motorstand != 'Problem') " +
                "and (liftarm != NULL or liftarm != 'Problem') " +
                "and (execution != '-') " +
                "and (grouting != '-') " +
                "and (motorstand != '-') " +
                "and (liftarm != '-') ", new String[] {reportValue});
        if (cursor.getCount()>0)
            return true;
        else
            return false;
    }

    public Boolean checkflanges(String reportValue) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select fasteners, flanges, gaskets from reports where reportId = ? " +
                "and (fasteners = 'Ok' or fasteners = 'NA') " +
                "and (flanges = 'Ok' or flanges = 'NA') " +
                "and (gaskets = 'Ok' or gaskets = 'NA') ", new String[] {reportValue});
        if (cursor.getCount()>0)
            return true;
        else
            return false;
    }

    public Boolean checkYellowFlanges(String reportValue) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select fasteners, flanges, gaskets from reports where reportId = ? " +
                "and (fasteners != NULL or fasteners != 'Problem') " +
                "and (flanges != NULL or flanges != 'Problem') " +
                "and (gaskets != NULL or gaskets != 'Problem') " +
                "and (fasteners != '-') " +
                "and (flanges != '-') " +
                "and (gaskets != '-') ", new String[] {reportValue});
        if (cursor.getCount()>0)
            return true;
        else
            return false;
    }

    public Boolean checkseal(String reportValue) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select swjoints, swsettings, swcontrolunit from reports where reportId = ? " +
                "and (swjoints = 'Ok' or swjoints = 'NA') " +
                "and (swsettings = 'Ok' or swsettings = 'NA') " +
                "and (swcontrolunit = 'Ok' or swcontrolunit = 'NA') ", new String[] {reportValue});
        if (cursor.getCount()>0)
            return true;
        else
            return false;
    }

    public Boolean checkYellowSeal(String reportValue) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select swjoints, swsettings, swcontrolunit from reports where reportId = ? " +
                "and (swjoints != NULL or swjoints != 'Problem') " +
                "and (swsettings != NULL or swsettings != 'Problem') " +
                "and (swcontrolunit != NULL or swcontrolunit != 'Problem') " +
                "and (swjoints != '-') " +
                "and (swsettings != '-') " +
                "and (swcontrolunit != '-') ", new String[] {reportValue});
        if (cursor.getCount()>0)
            return true;
        else
            return false;
    }

    public Boolean checklubricant(String reportValue) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select levellubrication from reports where reportId = ? " +
                "and (levellubrication = 'Ok' or levellubrication = 'NA') " , new String[] {reportValue});
        if (cursor.getCount()>0)
            return true;
        else
            return false;
    }

    public Boolean checkYellowLubricant(String reportValue) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select levellubrication from reports where reportId = ? " +
                "and (levellubrication != NULL or levellubrication != 'Problem') " +
                "and (levellubrication != '-') ", new String[] {reportValue});
        if (cursor.getCount()>0)
            return true;
        else
            return false;
    }

    public Boolean checkmotor(String reportValue) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select installmotor, bracketsmotor, shimsmotor from reports where reportId = ? " +
                "and (installmotor = 'Ok' or installmotor = 'NA') " +
                "and (bracketsmotor = 'Ok' or bracketsmotor = 'NA') " +
                "and (shimsmotor = 'Ok' or shimsmotor = 'NA') ", new String[] {reportValue});
        if (cursor.getCount()>0)
            return true;
        else
            return false;
    }

    public Boolean checkYellowMotor(String reportValue) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select installmotor, bracketsmotor, shimsmotor from reports where reportId = ? " +
                "and (installmotor != NULL or installmotor != 'Problem') " +
                "and (bracketsmotor != NULL or bracketsmotor != 'Problem') " +
                "and (shimsmotor != NULL or shimsmotor != 'Problem') " +
                "and (installmotor != '-') " +
                "and (bracketsmotor != '-') " +
                "and (shimsmotor != '-') ", new String[] {reportValue});
        if (cursor.getCount()>0)
            return true;
        else
            return false;
    }

    public Boolean checkcond(String reportValue) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select painting, gengrouting, damages, rotation from reports where reportId = ? " +
                "and (painting = 'Ok' or painting = 'NA') " +
                "and (gengrouting = 'Ok' or gengrouting = 'NA') " +
                "and (rotation = 'Ok' or rotation = 'NA') " +
                "and (damages = 'Ok' or damages = 'NA') ", new String[] {reportValue});
        if (cursor.getCount()>0)
            return true;
        else
            return false;
    }

    public Boolean checkYellowCond(String reportValue) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select painting, gengrouting, damages, rotation from reports where reportId = ? " +
                "and (painting != NULL or painting != 'Problem') " +
                "and (gengrouting != NULL or gengrouting != 'Problem') " +
                "and (rotation != NULL or rotation != 'Problem') " +
                "and (damages != NULL or damages != 'Problem') " +
                "and (painting != '-') " +
                "and (gengrouting != '-') " +
                "and (rotation != '-') " +
                "and (damages != '-') ", new String[] {reportValue});
        if (cursor.getCount()>0)
            return true;
        else
            return false;
    }

    public Boolean insertSyncStatus(String reportId, int syncstatus){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("syncstatus", syncstatus);

        long results = MyDB.update("reports", contentValues, "reportId = ?", new String[] {reportId});
        if (results==-1) return false;
        else
            return true;

    }

    public Cursor readFromLocalDatabase(SQLiteDatabase MyDB){

        String[] projection = {REPORTID, SYNC_STATUS};

        return (MyDB.query(REPORTS, projection, null, null, null, null, null));

    }

    Cursor readUnsentReports(){

        String query = "Select pumps.pumpnumber, reports.addedTime from reports, pumps where pumps.pumpId = reports.r_pumpId and reports.syncstatus = 1";
        SQLiteDatabase MyDB = this.getReadableDatabase();

        Cursor cursor = null;
        if (MyDB != null){
            cursor = MyDB.rawQuery(query, null);
        }

        return cursor;

    }


    public Cursor getPumpNumber(String reportId) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor resultPumpNumber = MyDB.rawQuery("Select pumps.pumpnumber from pumps, reports where pumps.pumpId = reports.r_pumpId and reports.reportId = ?", new String[] {reportId});
        return resultPumpNumber;
    }

    public Cursor getRUserId(String reportId){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor resultRUserId = MyDB.rawQuery("Select r_userId from reports where reportId=?", new String[] {reportId});
        return resultRUserId;
    }

    public Cursor getUserIdReport(String reportId) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor resultIdUser = MyDB.rawQuery("Select users.userId, reports.r_userId from users,reports where users.userId = reports.r_userId and reports.reportId = ?", new String[] {reportId});
        return resultIdUser;
    }

    public Cursor getUsername(String reportId){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor resultUsername = MyDB.rawQuery("Select users.username from users, reports where users.userId = reports.r_userId and reports.reportId = ?", new String[] {reportId});
        return resultUsername;
    }

    public Cursor getUsersurname(String reportId){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor resultUsersurname = MyDB.rawQuery("Select users.usersurname from users, reports where users.userId = reports.r_userId and reports.reportId = ?", new String[] {reportId});
        return resultUsersurname;
    }

    public Cursor getUserPassword(String reportId){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor resultUserPassword = MyDB.rawQuery("Select users.userpassword from users, reports where users.userId = reports.r_userId and reports.reportId = ?", new String[] {reportId});
        return resultUserPassword;
    }

    public Cursor getUserMail(String reportId){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor resultUserEmail = MyDB.rawQuery("Select users.useremail from users, reports where users.userId = reports.r_userId and reports.reportId = ?", new String[] {reportId});
        return resultUserEmail;
    }

    public Cursor getMainImage(String reportId){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor resultMainImage = MyDB.rawQuery("Select mainImage from reports where reportId = ?", new String[] {reportId});
        return resultMainImage;
    }

    public Cursor getDepartment(String reportId){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor resultDepartment = MyDB.rawQuery("Select departments.departmentname from departments, reports where departments.departmentId = reports.r_departmentId and reports.reportId = ?", new String[]{reportId});
        return resultDepartment;
    }

    public Cursor getCustomer(String departmentname){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor resultCustomer = MyDB.rawQuery("Select customers.customername from customers, departments where customers.customerId = departments.customer_id and departments.departmentname = ?", new String[] {departmentname});
        return resultCustomer;
    }

    public Cursor getDate(String reportId){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor resultDate = MyDB.rawQuery("Select addedTime from reports where reportId =?", new String[] {reportId});
        return resultDate;
    }

    public Cursor getCoupling(String reportId){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor resultCoupling = MyDB.rawQuery("Select * from reports where reportId = ?", new String[] {reportId});
        return resultCoupling;
    }

    public Cursor getPumpDescription(String pump){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor resultPD = MyDB.rawQuery("Select * from pumps where pumpnumber = ?", new String[] {pump});
        return resultPD;
    }


}

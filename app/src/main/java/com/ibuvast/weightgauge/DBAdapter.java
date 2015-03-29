package com.ibuvast.weightgauge;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.ibuvast.weightgauge.data.HistoryContract;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by calistus on 3/21/2015.
 */
public class DBAdapter{

    Result r = new Result();
    MySQLiteHelper helper;
    public  DBAdapter(Context context){
        helper = new MySQLiteHelper(context);
    }
    public long insertData(){
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(HistoryContract.HistoryEntry.DATE, HistoryContract.HistoryEntry._ID);
        contentValues.put(HistoryContract.HistoryEntry.BMI_VALUE,Result.result);
        contentValues.put(HistoryContract.HistoryEntry.STATUS,Result.BMIStatus);
        long id = db.insert(HistoryContract.HistoryEntry.TABLE_NAME,null,contentValues);
        return id;
    }
    static class MySQLiteHelper extends SQLiteOpenHelper{

        private static final String DB_NAME = "bmi_history.db";
        private static final int DB_VERSION = 1;

        private static final String DROP_TABLE = "DROP TABLE IF EXISTS"+HistoryContract.HistoryEntry.TABLE_NAME    ;
        private Context context;
        final String CREATE_TABLE =
                "CREATE TABLE "+ HistoryContract.HistoryEntry.TABLE_NAME+
                        "("+HistoryContract.HistoryEntry.UID+" INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        ""+HistoryContract.HistoryEntry.DATE+" VARCHAR(100)," +
                        ""+HistoryContract.HistoryEntry.TIME+" VARCHAR(100)," +
                        " "+HistoryContract.HistoryEntry.BMI_VALUE+" DOUBLE," +
                        ""+HistoryContract.HistoryEntry.STATUS+" TEXT);";


        MySQLiteHelper(Context context) {
            super(context, DB_NAME, null, DB_VERSION);
            this.context = context;
        }


        @Override
        public void onCreate(SQLiteDatabase db){

            db.execSQL(CREATE_TABLE);

        }

        @Override
        public void onUpgrade(SQLiteDatabase db,int oldVersion, int newVersion){

            db.execSQL(DROP_TABLE);
            db.execSQL(CREATE_TABLE);

        }
/*
    public boolean insertResult  (String date, String time, double values, String Status)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("Date", date);
        contentValues.put("Time", time);
        contentValues.put("Values", values);
        contentValues.put("Status", Status);

        db.insert("History", null, contentValues);
        return true;
    }
    public Cursor getData(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from History where id="+id+"", null );
        return res;
    }
    public int numberOfRows(){
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, TABLE_NAME);
        return numRows;
    }
    public boolean updateHistory (Integer id, String date, String time, double values, String Status)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Date", date);
        contentValues.put("Time", time);
        contentValues.put("Values", values);
        contentValues.put("Status", Status);
        db.update("History", contentValues, "id = ? ", new String[] { Integer.toString(id) } );
        return true;
    }

    public Integer deleteHistory (Integer id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("History",
                "id = ? ",
                new String[] { Integer.toString(id) });
    }
    public ArrayList getAllHistory()
    {
        ArrayList array_list = new ArrayList();
        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from History", null );
        res.moveToFirst();
        while(res.isAfterLast() == false){
            array_list.add(res.getString(res.getColumnIndex(UID)));
            res.moveToNext();
        }
        return array_list;
    }*/
    }
}

package com.example.mosabtoobasi.studentnotifications;

/**
 * Created by MSI-PC on 29-Apr-17.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "outletdb2";
    public static final int DATABASE_VERSION = 1;
    public static final String TABLE_OUTLET = "tbloutletdata";
    public static final String CREATE_TABLE_OUTLET= "CREATE TABLE IF NOT EXISTS "+ TABLE_OUTLET+ "(outlet_id INTEGER PRIMARY KEY AUTOINCREMENT, outlet_course_name TEXT NULL, outlet_exam TEXT NULL ,outlet_mark TEXT NULL,outlet_mark_from TEXT NULL)";
    public static final String DELETE_TABLE_OUTLET="DROP TABLE IF EXISTS " + TABLE_OUTLET;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }
    public void onCreate(SQLiteDatabase db) {



        db.execSQL(CREATE_TABLE_OUTLET);

    }
    //Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL(DELETE_TABLE_OUTLET);
        //Create tables again
        onCreate(db);

    }

    public void insertData(String course_name,String exam , String mark ,String mark_from){

        // Open the database for writing
        SQLiteDatabase db = this.getWritableDatabase();
        // Start the transaction.
        db.beginTransaction();
        ContentValues values;

        try
        {
            values = new ContentValues();
            values.put("outlet_course_name",course_name);
            values.put("outlet_exam",exam);
            values.put("outlet_mark",mark);
            values.put("outlet_mark_from",mark_from);
            // Insert Row
            long i = db.insert(TABLE_OUTLET, null, values);
            Log.i("Insert", i + "");
            // Insert into database successfully.
            db.setTransactionSuccessful();

        }
        catch (SQLiteException e)
        {
            e.printStackTrace();

        }
        finally
        {
            db.endTransaction();
            // End the transaction.
            //db.delete(TABLE_OUTLET,null,null);

            db.close();
            // Close database

        }

    }


}


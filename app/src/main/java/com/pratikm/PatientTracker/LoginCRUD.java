package com.pratikm.PatientTracker;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.pratikm.PatientTracker.LoginContract.*;
/**
 * Created by pratikmishra on 10/04/17.
 */

public class LoginCRUD {
    DbHelperClass helper;
    Context context;

    public LoginCRUD(Context context){
        helper = new DbHelperClass(context);
    }

    public static String createTable() {
        return "CREATE TABLE " + LoginEntry.TABLE_NAME + " ( " +
                LoginEntry.COLUMN_ID  + " INT PRIMARY KEY NOT NULL, " +
                LoginEntry.COLUMN_NAME + " TEXT NOT NULL, " +
                LoginEntry.COLUMN_USERNAME + " TEXT NOT NULL, " +
                LoginEntry.COLUMN_EMAIL + " TEXT NOT NULL, " +
                LoginEntry.COLUMN_PASSWORD + " TEXT NOT NULL);";
    }
    public static String deleteTable() {
        return "DROP TABLE IF EXISTS " + LoginEntry.TABLE_NAME;
    }
    public void insertContact(LoginContract c) {
        SQLiteDatabase db = helper.getReadableDatabase();
        ContentValues values = new ContentValues();
        String query = "SELECT * FROM " + LoginEntry.TABLE_NAME + ";";
        Cursor cursor = db.rawQuery(query, null);
        int id = cursor.getCount();
        values.put(LoginEntry.COLUMN_ID, id);
        values.put(LoginEntry.COLUMN_NAME, c.getName());
        values.put(LoginEntry.COLUMN_USERNAME, c.getUsername());
        values.put(LoginEntry.COLUMN_EMAIL, c.getEmail());
        values.put(LoginEntry.COLUMN_PASSWORD, c.getPassword());
        db.insert(LoginEntry.TABLE_NAME, null, values);
        cursor.close();
        db.close();
    }

    public String searchPass(String uname) {
        SQLiteDatabase db = this.helper.getReadableDatabase();
        String query = "SELECT username, password from " + LoginEntry.TABLE_NAME + ";";
        Cursor cursor = db.rawQuery(query, null);
        String a, b;
        b = "Not Found";
        if(cursor.moveToFirst()){
            do {
                a = cursor.getString(0);
                if(a.equals(uname)){
                    b = cursor.getString(1);
                    break;
                }
            }while(cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return b;
    }
}

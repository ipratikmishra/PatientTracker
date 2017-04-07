package com.pratikm.PatientTracker;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by pratikmishra on 07/04/17.
 */

public class DbHelperClass extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "contacts.db";
    private static final String TABLE_NAME = "contacts";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_USERNAME = "username";
    private static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_PASSWORD = "password";

    SQLiteDatabase db;

    private static final String query = "CREATE TABLE " + TABLE_NAME + " ( " +
            COLUMN_ID  + " INT PRIMARY KEY NOT NULL, " +
            COLUMN_NAME + " TEXT NOT NULL, " +
            COLUMN_USERNAME + " TEXT NOT NULL, " +
            COLUMN_EMAIL + " TEXT NOT NULL, " +
            COLUMN_PASSWORD + " TEXT NOT NULL);";

    public DbHelperClass(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(query);
        this.db = sqLiteDatabase;
    }

    public void insertContact(Contacts c) {
        db = getWritableDatabase();
        ContentValues values = new ContentValues();
        String query = "SELECT * FROM " + TABLE_NAME + ";";
        Cursor cursor = db.rawQuery(query, null);
        int id = cursor.getCount();
        values.put(COLUMN_ID, id);
        values.put(COLUMN_NAME, c.getName());
        values.put(COLUMN_USERNAME, c.getUsername());
        values.put(COLUMN_EMAIL, c.getEmail());
        values.put(COLUMN_PASSWORD, c.getPassword());
        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public String searchPass(String uname) {
        db = this.getReadableDatabase();
        String query = "SELECT username, password from " + TABLE_NAME + ";";
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
        return b;

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}

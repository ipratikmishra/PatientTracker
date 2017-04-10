package com.pratikm.PatientTracker;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.pratikm.PatientTracker.LoginContract.*;
/**
 * Created by pratikmishra on 07/04/17.
 */

public class DbHelperClass extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "contacts.db";

    SQLiteDatabase db;

    private static final String query = "CREATE TABLE " + LoginEntry.TABLE_NAME + " ( " +
            LoginEntry.COLUMN_ID  + " INT PRIMARY KEY NOT NULL, " +
            LoginEntry.COLUMN_NAME + " TEXT NOT NULL, " +
            LoginEntry.COLUMN_USERNAME + " TEXT NOT NULL, " +
            LoginEntry.COLUMN_EMAIL + " TEXT NOT NULL, " +
            LoginEntry.COLUMN_PASSWORD + " TEXT NOT NULL);";

    public DbHelperClass(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(query);
        this.db = sqLiteDatabase;
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + LoginEntry.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}

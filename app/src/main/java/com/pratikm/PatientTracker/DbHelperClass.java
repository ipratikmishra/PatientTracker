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

    public DbHelperClass(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(LoginCRUD.createTable());
        sqLiteDatabase.execSQL(PatientContactCRUD.createTable());
        sqLiteDatabase.execSQL(PatientHealthCRUD.createTable());
        this.db = sqLiteDatabase;
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(LoginCRUD.deleteTable());
        sqLiteDatabase.execSQL(PatientContactCRUD.deleteTable());
        sqLiteDatabase.execSQL(PatientHealthCRUD.deleteTable());
        onCreate(sqLiteDatabase);
    }
}

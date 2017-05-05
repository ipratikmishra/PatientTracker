package com.pratikm.PatientTracker;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.pratikm.PatientTracker.PatientContactContract.*;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by pratikmishra on 10/04/17.
 */

public class PatientContactCRUD {
    DbHelperClass helper;
    Context context;

    public PatientContactCRUD(Context context){
        helper = new DbHelperClass(context);
    }

    public static String createTable() {
        return "CREATE TABLE " + PatientContactEntry.TABLE_NAME + " ( " +
                PatientContactEntry.COLUMN_ID  + " INT, " +
                PatientContactEntry.COLUMN_FIRST_NAME + " TEXT, " +
                PatientContactEntry.COLUMN_LAST_NAME + " TEXT, " +
                PatientContactEntry.COLUMN_SEX + " TEXT, " +
                PatientContactEntry.COLUMN_MOBILE + " TEXT, " +
                PatientContactEntry.COLUMN_EMAIL + " TEXT PRIMARY KEY NOT NULL, " +
                PatientContactEntry.COLUMN_ADDRESS + " TEXT );";
    }
    public static String deleteTable() {
        return "DROP TABLE IF EXISTS " + PatientContactEntry.TABLE_NAME;
    }
    public void insertPatientContact(PatientContactContract c) {
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        String query = "SELECT * FROM " + PatientContactEntry.TABLE_NAME + ";";
        Cursor cursor = db.rawQuery(query, null);
        int id = cursor.getCount();
        values.put(PatientContactEntry.COLUMN_ID, id);
        values.put(PatientContactEntry.COLUMN_FIRST_NAME, c.getFirstName());
        values.put(PatientContactEntry.COLUMN_LAST_NAME, c.getLastName());
        values.put(PatientContactEntry.COLUMN_SEX, c.getSex());
        values.put(PatientContactEntry.COLUMN_MOBILE, c.getMobile());
        values.put(PatientContactEntry.COLUMN_EMAIL, c.getEmailAddress());
        values.put(PatientContactEntry.COLUMN_ADDRESS, c.getPostalAddress());

        db.insert(PatientContactEntry.TABLE_NAME, null, values);
        cursor.close();
        db.close();
    }

    public ArrayList<PatientContactContract> getPatientList() {
        //Open connection to read only
        SQLiteDatabase db = helper.getReadableDatabase();
        String selectQuery =  "SELECT  " +
                PatientContactEntry.COLUMN_FIRST_NAME + "," +
                PatientContactEntry.COLUMN_LAST_NAME + "," +
                PatientContactEntry.COLUMN_SEX + "," +
                PatientContactEntry.COLUMN_MOBILE + "," +
                PatientContactEntry.COLUMN_EMAIL + "," +
                PatientContactEntry.COLUMN_ADDRESS +
                " FROM " + PatientContactEntry.TABLE_NAME;

        ArrayList<PatientContactContract> patientList = new ArrayList<>();

        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                PatientContactContract patient = new PatientContactContract();
                patient.setFirstName(cursor.getString(cursor.getColumnIndex(PatientContactEntry.COLUMN_FIRST_NAME)));
                patient.setLastName(cursor.getString(cursor.getColumnIndex(PatientContactEntry.COLUMN_LAST_NAME)));
                patient.setSex(cursor.getString(cursor.getColumnIndex(PatientContactEntry.COLUMN_SEX)));
                patient.setEmailAddress(cursor.getString(cursor.getColumnIndex(PatientContactEntry.COLUMN_EMAIL)));
                patient.setPostalAddress(cursor.getString(cursor.getColumnIndex(PatientContactEntry.COLUMN_ADDRESS)));
                patient.setMobile(cursor.getString(cursor.getColumnIndex(PatientContactEntry.COLUMN_MOBILE)));
                patientList.add(patient);

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return patientList;

    }

    public PatientContactContract getPatientById(int Id){
        SQLiteDatabase db = helper.getReadableDatabase();
        String selectQuery =  "SELECT  " +
                PatientContactEntry.COLUMN_FIRST_NAME + "," +
                PatientContactEntry.COLUMN_LAST_NAME + "," +
                PatientContactEntry.COLUMN_SEX + "," +
                PatientContactEntry.COLUMN_MOBILE + "," +
                PatientContactEntry.COLUMN_EMAIL + "," +
                PatientContactEntry.COLUMN_ADDRESS +
                " FROM " + PatientContactEntry.TABLE_NAME
                + " WHERE " +
                PatientContactEntry.COLUMN_ID + "=?";// It's a good practice to use parameter ?, instead of concatenate string

        int iCount =0;
        PatientContactContract patient = new PatientContactContract();

        Cursor cursor = db.rawQuery(selectQuery, new String[] { String.valueOf(Id) } );

        if (cursor.moveToFirst()) {
            do {
                patient.firstName =cursor.getString(cursor.getColumnIndex(PatientContactEntry.COLUMN_FIRST_NAME));
                patient.lastName =cursor.getString(cursor.getColumnIndex(PatientContactEntry.COLUMN_LAST_NAME));
                patient.sex  =cursor.getString(cursor.getColumnIndex(PatientContactEntry.COLUMN_SEX));
                patient.mobile =cursor.getString(cursor.getColumnIndex(PatientContactEntry.COLUMN_MOBILE));
                patient.emailAddress =cursor.getString(cursor.getColumnIndex(PatientContactEntry.COLUMN_EMAIL));
                patient.postalAddress =cursor.getString(cursor.getColumnIndex(PatientContactEntry.COLUMN_ADDRESS));

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return patient;
    }

    public void deletePatientByEmail(String Email){
        SQLiteDatabase db = helper.getWritableDatabase();
        db.delete(PatientContactEntry.TABLE_NAME, PatientContactEntry.COLUMN_EMAIL + " = ?",
                new String[] { String.valueOf(Email) });
        db.close();
    }
}

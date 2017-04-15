package com.pratikm.PatientTracker;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.pratikm.PatientTracker.PatientHealthContract.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by pratikmishra on 10/04/17.
 */

public class PatientHealthCRUD {

    DbHelperClass helper;
    Context context;

    public PatientHealthCRUD(Context context) {
        helper = new DbHelperClass(context);
    }

    public static String createTable() {
        return "CREATE TABLE " + PatientHealthEntry.TABLE_NAME + " ( " +
                PatientHealthEntry.COLUMN_ID + " INT NOT NULL, " +
                PatientHealthEntry.COLUMN_EMAIL + " TEXT NOT NULL, " +
                PatientHealthEntry.COLUMN_AGE + " INT NOT NULL, " +
                PatientHealthEntry.COLUMN_BLOOD_GROUP + " TEXT NOT NULL, " +
                PatientHealthEntry.COLUMN_CONDITION + " TEXT NOT NULL, " +
                PatientHealthEntry.COLUMN_MEDICATION + " TEXT NOT NULL, " +
                PatientHealthEntry.COLUMN_NOTES + " TEXT, " +
                PatientHealthEntry.COLUMN_DATE_OF_VISIT + " DATE NOT NULL, " +
                " FOREIGN KEY (" + PatientHealthEntry.COLUMN_EMAIL +
                ") REFERENCES " + PatientHealthContract.PatientHealthEntry.TABLE_NAME + "(" +
                PatientContactContract.PatientContactEntry.COLUMN_EMAIL + ") " + ");";
    }

    public static String deleteTable() {
        return "DROP TABLE IF EXISTS " + PatientHealthEntry.TABLE_NAME;
    }

    public void insertPatientHealth(PatientHealthContract h) {
        SQLiteDatabase db = helper.getReadableDatabase();
        ContentValues values = new ContentValues();
        String query = "SELECT * FROM " + PatientHealthEntry.TABLE_NAME + ";";
        Cursor cursor = db.rawQuery(query, null);
        int id = cursor.getCount();
        values.put(PatientHealthEntry.COLUMN_ID, id);
        values.put(PatientHealthEntry.COLUMN_EMAIL, h.getEmail());
        values.put(PatientHealthEntry.COLUMN_AGE, h.getAge());
        values.put(PatientHealthEntry.COLUMN_BLOOD_GROUP, h.getBloodGroup());
        values.put(PatientHealthEntry.COLUMN_CONDITION, h.getCondition());
        values.put(PatientHealthEntry.COLUMN_MEDICATION, h.getMedication());
        values.put(PatientHealthEntry.COLUMN_NOTES, h.getNotes());
        values.put(PatientHealthEntry.COLUMN_DATE_OF_VISIT, h.getDateVisit());

        db.insert(PatientHealthEntry.TABLE_NAME, null, values);
        cursor.close();
        db.close();
    }

    public ArrayList<PatientHealthContract> getHealthList(String email) {

        //Open connection to read only
        SQLiteDatabase db = helper.getReadableDatabase();
        String selectQuery = "SELECT  " +
                PatientHealthEntry.COLUMN_AGE + "," +
                PatientHealthEntry.COLUMN_BLOOD_GROUP + "," +
                PatientHealthEntry.COLUMN_MEDICATION + "," +
                PatientHealthEntry.COLUMN_CONDITION + "," +
                PatientHealthEntry.COLUMN_NOTES + "," +
                PatientHealthEntry.COLUMN_DATE_OF_VISIT +
                " FROM " + PatientHealthEntry.TABLE_NAME +
                " WHERE " + PatientHealthEntry.COLUMN_EMAIL + " = " + "'" + email + "'" +
                " ORDER BY  date" + "(" + PatientHealthEntry.COLUMN_DATE_OF_VISIT + ")" +
                " DESC" + ";";

        //PatientHealthEntry patient = new PatientHealthEntry();
        ArrayList<PatientHealthContract> healthList = new ArrayList<>();

        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list

        if (cursor.moveToFirst()) {
            do {
                PatientHealthContract patient = new PatientHealthContract();
                patient.setAge(cursor.getString(cursor.getColumnIndex(PatientHealthEntry.COLUMN_AGE)));
                patient.setBloodGroup(cursor.getString(cursor.getColumnIndex(PatientHealthEntry.COLUMN_BLOOD_GROUP)));
                patient.setCondition(cursor.getString(cursor.getColumnIndex(PatientHealthEntry.COLUMN_CONDITION)));
                patient.setMedication(cursor.getString(cursor.getColumnIndex(PatientHealthEntry.COLUMN_MEDICATION)));
                patient.setNotes(cursor.getString(cursor.getColumnIndex(PatientHealthEntry.COLUMN_NOTES)));
                patient.setDateVisit(cursor.getString(cursor.getColumnIndex(PatientHealthEntry.COLUMN_DATE_OF_VISIT)));
                healthList.add(patient);

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return healthList;

    }

    public ArrayList<PatientHealthContract> getHealthListByDate(String date) {

        //Open connection to read only
        SQLiteDatabase db = helper.getReadableDatabase();
        String selectQuery = "SELECT  " +
                PatientHealthEntry.COLUMN_AGE + "," +
                PatientHealthEntry.COLUMN_EMAIL + "," +
                PatientHealthEntry.COLUMN_BLOOD_GROUP + "," +
                PatientHealthEntry.COLUMN_MEDICATION + "," +
                PatientHealthEntry.COLUMN_CONDITION + "," +
                PatientHealthEntry.COLUMN_NOTES + "," +
                PatientHealthEntry.COLUMN_DATE_OF_VISIT +
                " FROM " + PatientHealthEntry.TABLE_NAME +
                " WHERE " + PatientHealthEntry.COLUMN_DATE_OF_VISIT + " = " + "'" + date + "'" + ";";

        //PatientHealthEntry patient = new PatientHealthEntry();
        ArrayList<PatientHealthContract> healthList = new ArrayList<>();

        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list

        if (cursor.moveToFirst()) {
            do {
                PatientHealthContract patient = new PatientHealthContract();
                patient.setAge(cursor.getString(cursor.getColumnIndex(PatientHealthEntry.COLUMN_AGE)));
                patient.setBloodGroup(cursor.getString(cursor.getColumnIndex(PatientHealthEntry.COLUMN_BLOOD_GROUP)));
                patient.setCondition(cursor.getString(cursor.getColumnIndex(PatientHealthEntry.COLUMN_CONDITION)));
                patient.setMedication(cursor.getString(cursor.getColumnIndex(PatientHealthEntry.COLUMN_MEDICATION)));
                patient.setNotes(cursor.getString(cursor.getColumnIndex(PatientHealthEntry.COLUMN_NOTES)));
                patient.setDateVisit(cursor.getString(cursor.getColumnIndex(PatientHealthEntry.COLUMN_DATE_OF_VISIT)));
                patient.setEmail(cursor.getString(cursor.getColumnIndex(PatientHealthEntry.COLUMN_EMAIL)));
                healthList.add(patient);

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return healthList;
    }
}

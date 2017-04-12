package com.pratikm.PatientTracker;

import android.provider.BaseColumns;

/**
 * Created by pratikmishra on 10/04/17.
 */

public class PatientHealthContract {
    public static final class PatientHealthEntry implements BaseColumns {
        static final String TABLE_NAME = "patientHealth";
        static final String COLUMN_ID = "_id";
        static final String COLUMN_EMAIL = "email";
        static final String COLUMN_AGE = "age";
        static final String COLUMN_BLOOD_GROUP = "bloodGroup";
        static final String COLUMN_CONDITION = "condition";
        static final String COLUMN_MEDICATION = "medication";
        static final String COLUMN_NOTES = "notes";
        static final String COLUMN_DATE_OF_VISIT = "dateVisit";

    }

    private String email, age, bloodGroup, condition, medication, notes, dateVisit;


    public void setEmail(String email) {
        this.email = email;
    }
    public String getEmail() {
        return this.email;
    }
    public void setAge(String age) {
        this.age = age;
    }
    public String getAge() {
        return this.age;
    }
    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }
    public String getBloodGroup() {
        return this.bloodGroup;
    }
    public void setCondition(String condition) {
        this.condition = condition;
    }
    public String getCondition() {
        return this.condition;
    }
    public void setMedication(String medication) {
        this.medication = medication;
    }
    public String getMedication() {
        return this.medication;
    }
    public void setNotes(String notes) {
        this.notes = notes;
    }
    public String getNotes() {
        return this.notes;
    }
    public void setDateVisit(String dateVisit) {
        this.dateVisit = dateVisit;
    }
    public String getDateVisit() {
        return this.dateVisit;
    }
}


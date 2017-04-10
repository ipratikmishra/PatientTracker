package com.pratikm.PatientTracker;

import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

/**
 * Created by pratikmishra on 10/04/17.
 */

public class PatientDetailsContract {

    public static final class PatientDetailsEntry implements BaseColumns {

        public static final String TABLE_NAME = "patientDetails";
        public static final String COLUMN_FIRST_NAME = "firstName";
        public static final String COLUMN_LAST_NAME = "lastName";
        public static final String COLUMN_SEX = "sex";
        public static final String COLUMN_MOBILE = "mobile";
        public static final String COLUMN_EMAIL = "email";
        public static final String COLUMN_POSTAL_ADDRESS = "postalAddress";
        public static final String COLUMN_AGE = "age";
        public static final String COLUMN_BLOOD_GROUP = "bloodGroup";
        public static final String COLUMN_CONDITION = "condition";
        public static final String COLUMN_NOTE = "note";
        public static final String COLUMN_DATE_VISIT = "dateVisit";

    }

}

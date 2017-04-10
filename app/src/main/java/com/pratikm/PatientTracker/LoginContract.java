package com.pratikm.PatientTracker;

import android.provider.BaseColumns;

/**
 * Created by pratikmishra on 10/04/17.
 */

public class LoginContract {
    public static final class LoginEntry implements BaseColumns {

        static final String TABLE_NAME = "contacts";
        static final String COLUMN_ID = "_id";
        static final String COLUMN_NAME = "name";
        static final String COLUMN_USERNAME = "username";
        static final String COLUMN_EMAIL = "email";
        static final String COLUMN_PASSWORD = "password";

    }
}

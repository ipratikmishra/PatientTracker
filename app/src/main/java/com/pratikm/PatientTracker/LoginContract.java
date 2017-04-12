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

        private String name, username, email, password;


        public void setName(String name) {
            this.name = name;
        }
        public String getName() {
            return this.name;
        }
        public void setUsername(String username) {
            this.username = username;
        }
        public String getUsername() {
            return this.username;
        }
        public void setEmail(String email) {
            this.email = email;
        }
        public String getEmail() {
            return this.email;
        }
        public void setPassword(String password) {
            this.password = password;
        }
        public String getPassword() {
            return this.password;
        }
}

package com.pratikm.PatientTracker;

import android.provider.BaseColumns;

/**
 * Created by pratikmishra on 10/04/17.
 */

public class PatientContactContract {

    public static final class PatientContactEntry implements BaseColumns{
        static final String TABLE_NAME = "patientContact";
        static final String COLUMN_ID = "_id";
        static final String COLUMN_FIRST_NAME = "firstName";
        static final String COLUMN_LAST_NAME = "lastName";
        static final String COLUMN_SEX = "sex";
        static final String COLUMN_MOBILE = "mobile";
        static final String COLUMN_EMAIL = "emailAddress";
        static final String COLUMN_ADDRESS = "postalAddress";

    }

    public String firstName, lastName, sex, mobile, emailAddress, postalAddress;

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getFirstName() {
        return this.firstName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getLastName() {
        return this.lastName;
    }
    public void setSex(String sex) {
        this.sex = sex;
    }
    public String getSex() {
        return this.sex;
    }
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
    public String getMobile() {
        return this.mobile;
    }
    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }
    public String getEmailAddress() {
        return this.emailAddress;
    }
    public void setPostalAddress(String postalAddress) {
        this.postalAddress = postalAddress;
    }
    public String getPostalAddress() {
        return this.postalAddress;
    }
}
